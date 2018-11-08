package business.vehicle.as.imp;

import business.ASException;
import business.city.TCity;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import business.vehicle.as.ASVehicle;
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
    public Integer create(TVehicle vehicle) throws ASException {
        Integer idv = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try {
                tr.start();
                TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(vehicle.getCity());

                TVehicle tVehicle = null;

                if(vehicle.getType().equals("Bicycle")) {
                    TBicycleVehicle tb = (TBicycleVehicle) vehicle;
                    tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlateOrSerial(tb.getSerialNumber());
                }
                else{
                    TCarVehicle tv = (TCarVehicle) vehicle;
                    tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlateOrSerial(tv.getPlate());
                }

                idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(tVehicle);

                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The vehicle doesn't create correctly.\n");

        return idv;
    }

    @Override
    public Integer drop(Integer idVehicle) throws ASException {

        Integer idv = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if (tr != null) {
            try {
                tr.start();
                TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(idVehicle);

                if (tv != null && tv.isActive()) {//the city exists and is active

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
                    if (tv == null) throw new ASException("The vehicle doesn't exists");
                    else if (!tv.isActive()) throw new ASException("The vehicle is already disabled");
                }
            }catch (DAOException | ASException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        else
            throw new ASException("ERROR: The vehicle doesn't delete correctly.\n");


        return idv;
    }


    @Override
    public Integer update(TVehicle tVehicle) throws ASException {

        Integer idv = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr != null) {
            try {
                tr.start();
                TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(tVehicle.getId());

                if (tv != null) { //the city exists
                    idv = DAOVehicleFactory.getInstance().generateDAOVehicle().update(tv);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    throw new ASException("The vehicle doesn't exists");
                }
            }catch (DAOException | ASException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The vehicle doesn't update correctly.\n");

        return idv;
    }

    @Override
    public TVehicleDetails show(Integer idVehicle) throws ASException {

        TVehicleDetails tvd = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr != null) {
            try {
                tr.start();
                TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById(idVehicle);
                TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(tv.getCity());

                tvd = new TVehicleDetails(tv, tc);

                tr.commit();
                TransactionManager.getInstance().removeTransaction();
                if (tv == null) throw new ASException("The vehicle doesn't exists");
            }catch (DAOException | TransactionException | ASException | DAOException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The vehicle doesn't show correctly.\n");
        return tvd;
    }

    @Override
    public Collection<TVehicleDetails> showAll() throws ASException {
        Collection<TVehicleDetails> retList = new ArrayList<>();
        Collection<TVehicle> vehicleList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr != null) {
            try {
                tr.start();
                vehicleList = DAOVehicleFactory.getInstance().generateDAOVehicle().readAll();

                DAOCity daoCity = DAOCityFactory.getInstance().generateDAOCity();

                for(TVehicle tv : vehicleList) {
                    TCity tc = daoCity.readById(tv.getCity());
                    retList.add(new TVehicleDetails(tv, tc));
                }

                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The cities doesn't create correctly.\n");

        return retList;
    }

    @Override
    public Collection<TVehicleDetails> showAllAvaiableVehicles() {
        return null;
    }
}
