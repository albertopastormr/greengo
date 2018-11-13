package business.rental.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.rental.as.ASRental;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.client.factory.DAOClientFactory;
import integration.rental.dao.DAORental;
import integration.rental.factory.DAORentalFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ASRentalImp implements ASRental {
    @Override
    public Integer create(TRental rental) throws ASException, IncorrectInputException {
        Integer idr =null;

        if(rental.getId() == null && rental.getIdVehicle()!= null && rental.getIdVehicle() > 0 && rental.getNumKmRented() > 0 && rental.getIdClient()!= null && rental.getIdClient() > 0 && rental.getDateFrom()!= null && rental.getDateTo() != null &&
        rental.getDateFrom().before(rental.getDateTo())) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));

                    if (tc != null && tv != null && tc.isActive() && tv.isActive() && tv.getEstimatedDuration() >= tv.getNumKmTravelled() +rental.getNumKmRented()) {//the client and vehicle exists and are active and free in the dates
                        Collection<TRental> vehicleRentals = DAORentalFactory.getInstance().generateDAORental().readByIdVehicleAndDateRange(rental.getIdVehicle(),rental.getDateFrom(),rental.getDateTo());
                        Collection<TRental> clientsRentals = DAORentalFactory.getInstance().generateDAORental().readByIdClientAndDateRange(rental.getIdClient(),rental.getDateFrom(),rental.getDateTo());
                        if(vehicleRentals.isEmpty() && clientsRentals.isEmpty()) {
                            idr = DAORentalFactory.getInstance().generateDAORental().create(rental);
                            tr.commit();
                        }else
                            throw new ASException("ERROR: There are vehicles or clients occupied for this dates");
                        TransactionManager.getInstance().removeTransaction();
                    } else {//exists
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tc == null || tv == null)
                            throw new ASException("ERROR: Vehicle or Client doesn't exists");
                        else if (!tc.isActive() || !tv.isActive())
                            throw new ASException("ERROR: Vehicle or Client is  disabled");
                        else
                            throw new ASException("ERROR: NumKmRentes must be less than EstimatedDuration from vehicle");
                    }
                }else
                    throw new ASException("ERROR: Transaction creation failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id's must be positive and dates not empty and correct \n");
        return idr;
    }

    @Override
    public Integer drop(Integer id) throws ASException, IncorrectInputException {
        Integer idr =null;
        if(id != null && id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(id);
                    if (tl != null && tl.isActive()) {//the rental exists and is active
                        tl.setActive(false);
                        idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null)
                            throw new ASException("ERROR: The rental doesn't exists");
                        else if (!tl.isActive())
                            throw new ASException("ERROR: The rental is disabled");
                    }

                } else
                    throw new ASException("ERROR: Transaction drop failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive and mustn`t be empty \n");

        return idr;
    }

    @Override
    public Integer update(TRental rental) throws ASException, IncorrectInputException {
        Integer idr = null;

        if(rental.getId() > 0 &&rental.getIdVehicle() > 0 && rental.getNumKmRented() > 0 && rental.getIdClient() > 0 && rental.getDateFrom()!= null
                && rental.getDateTo() != null) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();

                if (tr != null) {
                    tr.start();
                    TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(rental.getId());
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));
                    TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());


                    if (tl != null && tv != null && tc != null && tv.isActive() && tc.isActive() && rental.isActive()) {//the rental exists, the client and vehicle are actived, exists and free in the dates
                        Collection<TRental> vehicleRentals = DAORentalFactory.getInstance().generateDAORental().readByIdVehicleAndDateRange(rental.getIdVehicle(),rental.getDateFrom(),rental.getDateTo());
                        Collection<TRental> clientsRentals = DAORentalFactory.getInstance().generateDAORental().readByIdClientAndDateRange(rental.getIdClient(),rental.getDateFrom(),rental.getDateTo());
                        if(vehicleRentals.isEmpty() && clientsRentals.isEmpty()) {
                            idr = DAORentalFactory.getInstance().generateDAORental().update(rental);
                            tr.commit();
                        }
                        else
                            throw new ASException("ERROR: There are vehicles or clients occupied for this dates");
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null) throw new ASException("The rental doesn't exists");
                        else if (tv == null || tc == null)
                            throw new ASException("ERROR: The client or vehicle doesn't exists");
                        else if (!tv.isActive() || !tc.isActive())
                            throw new ASException("ERROR: The client or vehicle is disabled");
                        else if (rental.isActive())
                            throw new ASException("ERROR: Active field must be true in order to update it");
                    }
                }else
                    throw new ASException("ERROR: Transaction update failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive and dates not empty \n");

        return idr;
    }

    @Override
    public TRentalDetails show(Integer id) throws ASException, IncorrectInputException {
        TRental rental;
        TRentalDetails rDetails = null;

        if(id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    rental = DAORentalFactory.getInstance().generateDAORental().readById(id);
                    if (rental != null) {//if rental exists
                        rDetails = getRentalDetails(id);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {//doesn't exists
                        TransactionManager.getInstance().removeTransaction();
                        throw new ASException("ERROR: The rental doesn't exists");
                    }
                }else
                    throw new ASException("ERROR: Transaction show failed\n");
            } catch (IncorrectInputException | DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive\n");

        return rDetails;
    }

    @Override
    public Collection<TRentalDetails> showAll() throws ASException {
        Collection<TRentalDetails> rDetailsList = null;
        try{
            Transaction tr = TransactionManager.getInstance().createTransaction();
            if(tr!=null) {
                tr.start();
                rDetailsList = getAllRentalsDetails();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }else
                throw new ASException("ERROR: Transaction showALL failed\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        } catch (IncorrectInputException e) {
            e.printStackTrace();
        }
        return rDetailsList;
    }

    public TRentalDetails getRentalDetails(Integer idRental) throws ASException, DAOException, IncorrectInputException {
        TRental rental = DAORentalFactory.getInstance().generateDAORental().readById(idRental);
        TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();

        return new TRentalDetails(client,rental,vehicle);
    }

    public Collection<TRentalDetails> getAllRentalsDetails() throws ASException, DAOException, IncorrectInputException {
        Collection<TRental> rentals = DAORentalFactory.getInstance().generateDAORental().readAll();
        Collection<TRentalDetails> details = new ArrayList<>();

        for(TRental rental : rentals){
            TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
            TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();
            details.add(new TRentalDetails(client,rental,vehicle));
        }

        return details;
    }
}
