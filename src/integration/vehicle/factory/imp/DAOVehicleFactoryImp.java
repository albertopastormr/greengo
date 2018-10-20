package integration.vehicle.factory.imp;

import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.dao.imp.DAOVehicleImp;
import integration.vehicle.factory.DAOVehicleFactory;

public class DAOVehicleFactoryImp extends DAOVehicleFactory {
    @Override
    public DAOVehicle generateDAOVehicle() {
        return new DAOVehicleImp();
    }
}
