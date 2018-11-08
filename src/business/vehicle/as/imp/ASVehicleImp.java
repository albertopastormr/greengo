package business.vehicle.as.imp;

import business.ASException;
import business.city.TCity;
import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import business.vehicle.as.ASVehicle;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.city.factory.DAOCityFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;

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
                    tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlate(tb.getSerialNumber());
                }
                else{
                    TCarVehicle tv = (TCarVehicle) vehicle;
                    tVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle().showByPlate(tv.getPlate());
                }
                
                idv = DAOVehicleFactory.getInstance().generateDAOVehicle().create(tVehicle);

                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The city doesn't create correctly.\n");

        return idv;
    }

    @Override
    public Integer drop(Integer vehicle) {
        return null;
    }


    @Override
    public Integer update(TVehicle vehicle) {
        return null;
    }

    @Override
    public TVehicleDetails show(Integer id) {
        return null;
    }

    @Override
    public Collection<TVehicleDetails> showAll() {
        return null;
    }

    @Override
    public Collection<TVehicleDetails> showAllActiveVehicles() {
        return null;
    }
}
