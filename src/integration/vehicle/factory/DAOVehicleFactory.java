package integration.vehicle.factory;

import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.imp.DAOVehicleFactoryImp;

public abstract class DAOVehicleFactory {
    private static DAOVehicleFactory instance;

    public static DAOVehicleFactory getInstance(){
        if(instance ==null)
            instance = new DAOVehicleFactoryImp();
        return instance;
    }

    public abstract DAOVehicle generateDAOVehicle();
}
