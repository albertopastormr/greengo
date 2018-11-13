package business.vehicle.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.city.dao.DAOCity;
import integration.city.factory.DAOCityFactory;
import integration.rental.factory.DAORentalFactory;
import integration.rental.factory.imp.DAORentalFactoryImp;
import integration.transactionManager.TransactionManager;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.ArrayList;
import java.util.Collection;

//TODO separar las excepciones tanto AS como InputError para que aporten informacion relevante, corregir mensajes
// de las excepciones
public class ASVehicleImp implements ASVehicle {
    @Override
    public Integer create(TVehicle vehicle) throws ASException, IncorrectInputException {
        Integer idv = null;

        if(!vehicle.getBrand().equals("") && !vehicle.getBrand().equals("") && vehicle.getEstimatedDuration() != null && vehicle.getEstimatedDuration() > 0
                && vehicle.getNumKmTravelled() != null && vehicle.getNumKmTravelled()>= 0 &&vehicle.getCity() != null && !vehicle.getType().equals("")
                && vehicle.getNumKmTravelled() <= vehicle.getEstimatedDuration() && (vehicle.getType().equals("Bicycle") || vehicle.getType().equals("Car")))
        {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();

                if(tr!=null) {
                    tr.start();
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(vehicle.getCity());
                    if(tc == null) throw  new ASException("ERROR: The city doesn`t exist");

                    if (vehicle.getType().equals("Bicycle")) {
                        if(!verifyNotRepeatedIdentifier(vehicle)){
                            idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(vehicle);
                        }
                        else{
                            throw  new ASException("ERROR: Exists another bicycle with the same serial number.");
                        }
                    }
                    else {
                        if(verifyNotRepeatedIdentifier(vehicle)){
                            idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(vehicle);
                        }
                        else{
                            throw  new ASException("ERROR: Exists another car with the same plate.");
                        }
                    }
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                }else
                    throw new ASException("ERROR: The vehicle wasn't created correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: The data of the vehicle wasn't inserted correctly.\n");

        return idv;
    }

    @Override
    public Integer drop(Integer idVehicle) throws ASException, IncorrectInputException {

        Integer idv = null;
        if(idVehicle > 0){
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(idVehicle);

                    if (tv != null && tv.isActive()) {//the vehicle exists and is active

                        Collection<TRental> rentalList = DAORentalFactory.getInstance().generateDAORental().showRentalsByVehicle(idVehicle);

                        for (TRental tRental : rentalList) // If exists vehicles actives not update
                            if (tRental.isActive())
                                throw new ASException("ERROR: Exists active rentals belonged to this vehicle ");

                            tv.setActive(false);
                            idv = DAOVehicleFactory.getInstance().generateDAOVehicle().update(tv);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();

                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tv == null) throw new ASException("ERROR: The vehicle doesn't exists");
                        else if (!tv.isActive()) throw new ASException("ERROR: The vehicle is already disabled");
                    }
                }
                else {
                    throw new ASException("ERROR: The vehicle doesn't delete correctly.\n");

                }
            }catch (DAOException | ASException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: The id must be >= 0.\n");

        return idv;
    }


    @Override
    public Integer update(TVehicle tVehicle) throws ASException, IncorrectInputException {

        Integer idv = null;

        if(tVehicle.getId() != null && tVehicle.getId() > 0 && !tVehicle.getBrand().equals("") && tVehicle.getEstimatedDuration() != null
                && tVehicle.getNumKmTravelled() != null && tVehicle.getCity() != null && tVehicle.getNumKmTravelled() < tVehicle.getEstimatedDuration()
                && tVehicle.getEstimatedDuration() > 0 && tVehicle.getNumKmTravelled() >= 0)
        {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(tVehicle.getId());

                    if (tv != null && tv.isActive() && !tv.isOccupied() && verifyNotRepeatedIdentifier(tVehicle)) { //the vehicle exists, is active and ins`t occupied
                        idv = DAOVehicleFactory.getInstance().generateDAOVehicle().update(tVehicle);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if ( tv == null ) throw new ASException("ERROR: The vehicle doesn't exists");
                        else if ( tVehicle.isActive() ) throw new ASException("ERROR: The active field must be true");
                        else if ( tv.isOccupied()) throw new ASException("ERROR: The vehicle is occupied");
                    }
                } else
                    throw new ASException("ERROR: The vehicle doesn't update correctly.\n");
                } catch (DAOException | ASException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: The data of vehicle isn't insert correctly.\n");

        return idv;
    }

    @Override
    public TVehicleDetails show(Integer idVehicle) throws ASException, IncorrectInputException {

        TVehicleDetails tvd = null;

        if(idVehicle > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(idVehicle);
                    if (tv == null) throw new ASException("ERROR: The vehicle doesn't exists");

                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(tv.getCity());
                    tvd = new TVehicleDetails(tv, tc);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();

                } else
                    throw new ASException("ERROR: The vehicle doesn't show correctly.\n");
            } catch (TransactionException | ASException | DAOException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: The data of vehicle isn't insert correctly.\n");

        return tvd;
    }

    @Override
    public Collection<TVehicleDetails> showAll() throws ASException {
        Collection<TVehicleDetails> retList = new ArrayList<>();
        Collection<TVehicle> vehicleList = null;

        try {
            Transaction tr = TransactionManager.getInstance().createTransaction();
            if(tr != null) {
                tr.start();
                vehicleList = DAOVehicleFactory.getInstance().generateDAOVehicle().readAll();
                DAOCity daoCity = DAOCityFactory.getInstance().generateDAOCity();

                for(TVehicle tv : vehicleList) {
                    TCity tc = daoCity.readById(tv.getCity());
                    retList.add(new TVehicleDetails(tv, tc));
                }

                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }else
                throw new ASException("ERROR: The cities doesn't list correctly.\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        }
        return retList;
    }

    @Override
    public Collection<TVehicleDetails> showAllAvailableVehicles() throws ASException {

        Collection<TVehicleDetails> retList = new ArrayList<>();
        Collection<TVehicle> vehicleList = null;

        try {
            Transaction tr = TransactionManager.getInstance().createTransaction();

            if(tr != null) {
                tr.start();
                vehicleList = DAOVehicleFactory.getInstance().generateDAOVehicle().readAllAvailableVehicles();
                DAOCity daoCity = DAOCityFactory.getInstance().generateDAOCity();

                for(TVehicle tv : vehicleList) {
                    TCity tc = daoCity.readById(tv.getCity());
                    retList.add(new TVehicleDetails(tv, tc));
                }
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }else
                throw new ASException("ERROR: The vehicles availables doesn't list correctly.\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        }
        return retList;
    }

    public TVehicleDetails getVehicleDetails(Integer vehicleID) throws ASException, IncorrectInputException {
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(vehicleID).getVehicle();
        TCity city = ASCityFactory.getInstance().generateASCity().show(vehicle.getCity());

        return new TVehicleDetails(vehicle,city);
    }

    public Collection<TVehicleDetails> getAllVehiclesDetails() throws ASException, IncorrectInputException {
        Collection<TVehicleDetails> vehicles = ASVehicleFactory.getInstance().generateASVehicle().showAll();
        Collection<TVehicleDetails> details = new ArrayList<>();

        for(TVehicleDetails vehicle : vehicles){
            Integer cityID = vehicle.getVehicle().getCity();
            TCity city = ASCityFactory.getInstance().generateASCity().show(cityID);
            details.add(new TVehicleDetails(vehicle.getVehicle(),city));
        }

        return details;
    }

    private boolean verifyNotRepeatedIdentifier(TVehicle vehicle) throws DAOException{
        //already exists a vehicle with vehicle's brand or serial number
        if (DAOVehicleFactory.getInstance().generateDAOVehicle().readByPlateOrSerialNumber(vehicle) != null)
            return false;

        return true;
    }
}
