package business.vehicle.factory.imp;

import business.vehicle.as.ASVehicle;
import business.vehicle.as.imp.ASVehicleImp;
import business.vehicle.factory.ASVehicleFactory;

public class ASVehicleFactoryImp extends ASVehicleFactory {
    @Override
    public ASVehicle generateASVehicle() {
        return new ASVehicleImp();
    }
}
