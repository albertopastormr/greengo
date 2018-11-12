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
                    TVehicle tVehicle = null;

                    if (vehicle.getType().equals("Bicycle") && tc!=null) {
                        TBicycleVehicle tb = (TBicycleVehicle) vehicle;
                        tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlateOrSerial(tb.getSerialNumber());
                        if(tVehicle == null){
                            idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(tb);
                        }else{
                            throw  new ASException("ERROR: Exists other bicycle with this plate.");
                        }
                    } else {
                        TCarVehicle tv = (TCarVehicle) vehicle;
                        tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlateOrSerial(tv.getPlate());
                       if(tVehicle == null){
                           idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(tv);
                       }else{
                           throw  new ASException("ERROR: Exists other car with this plate.");
                       }
                    }
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                }else
                    throw new ASException("ERROR: The vehicle doesn't create correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: The data of vehicle isn't insert correctly.\n");

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
                        boolean isRentalActive = false;
                        for (TRental tRental : rentalList) // If exists vehicles actives not update
                            if (tRental.isActive())
                                isRentalActive = true;

                        if (!isRentalActive) {
                            tv.setActive(false);
                            idv = DAOVehicleFactory.getInstance().generateDAOVehicle().update(tv);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();
                        }
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

        if(tVehicle.getId() > 0 && !tVehicle.getBrand().equals("") && !tVehicle.getBrand().equals("") && tVehicle.getEstimatedDuration() != null
                && tVehicle.getNumKmTravelled() != null && tVehicle.getCity() != null && !tVehicle.getType().equals("") && tVehicle.getNumKmTravelled() < tVehicle.getEstimatedDuration()
                && tVehicle.getEstimatedDuration() > 0 && tVehicle.getNumKmTravelled() > 0)
        {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(tVehicle.getId());

                    if (tv != null && tVehicle.isActive()) { //the city exists
                        idv = DAOVehicleFactory.getInstance().generateDAOVehicle().update(tv);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if ( tv == null ) throw new ASException("ERROR: The vehicle doesn't exists");
                        else if ( tVehicle.isActive() ) throw new ASException("ERROR: The active field must be true");
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
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(tv.getCity());
                    tvd = new TVehicleDetails(tv, tc);

                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                    if (tv == null) throw new ASException("ERROR: The vehicle doesn't exists");
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
}
