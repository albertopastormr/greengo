package business.rental.as.imp;

import business.ASException;
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
    public Integer create(TRental rental) throws ASException {
        Integer idr =null;

        if(rental.getIdVehicle() > 0 && rental.getNumKmRented() > 0 && rental.getIdClient() > 0 && rental.getDateFrom()!= null && rental.getDateTo() != null) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));
                    Boolean avaiableRental = DAORentalFactory.getInstance().generateDAORental().checkAvailableDates(rental);

                    if (tc != null && tv != null && tc.isActive() && tv.isActive() && avaiableRental) {//the client and vehicle exists and are active and free in the dates
                        idr = DAORentalFactory.getInstance().generateDAORental().create(rental);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {//exists
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tc == null || tv == null)
                            throw new ASException("Vehicle or Client doesn't exists");
                        else if (!tc.isActive() || !tv.isActive())
                            throw new ASException("Vehicle or Client is  disabled");
                        else if (!avaiableRental)
                            throw new ASException("Vehicle or Client isn`t avaiable for the dates");
                    }
                }else
                    throw new ASException("ERROR: The rental doesn't create correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The data of rental isn't insert correctly.\n");

        return idr;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idr =null;

        if(id > 0) {
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
                            throw new ASException("The rental doesn't exists");
                        else if (!tl.isActive())
                            throw new ASException("The rental is disabled");
                    }

                } else
                    throw new ASException("ERROR: The rental doesn't create correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The id of renta; isn't insert correctly.\n");

        return idr;
    }

    @Override
    public Integer update(TRental rental) throws ASException {
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
                    Boolean trental = DAORentalFactory.getInstance().generateDAORental().checkAvailableDates(rental);

                    if (tl != null && tv != null && tc != null && tv.isActive() && tc.isActive() && trental!= null) {//the rental exists, the client and vehicle are actived, exists and free in the dates
                        idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null) throw new ASException("The rental doesn't exists");
                        else if (tv == null || tc == null)
                            throw new ASException("The client or vehicle doesn't exists");
                        else if (!tv.isActive() || !tc.isActive())
                            throw new ASException("The client or vehicle is disabled");
                        else if (trental != null)
                            throw new ASException("Vehicle or Client isn`t avaible for the dates");
                    }
                }else
                    throw new ASException("ERROR: The rental doesn't update correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The data of rental isn't insert correctly.\n");

        return idr;
    }

    @Override
    public TRentalDetails show(Integer id) throws ASException {
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
                        throw new ASException("The rental doesn't exists");
                    }
                }else
                    throw new ASException("ERROR: The rental doesn't show correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The number of rental isn't insert correctly.\n");

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
                throw new ASException("ERROR: The rentals doesn't list correctly.\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        }
        return rDetailsList;
    }

    public TRentalDetails getRentalDetails(Integer idRental) throws ASException, DAOException {
        TRental rental = DAORentalFactory.getInstance().generateDAORental().readById(idRental);
        TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();

        return new TRentalDetails(client,rental,vehicle);
    }

    public Collection<TRentalDetails> getAllRentalsDetails() throws ASException, DAOException {
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
