package business.vehicle.factory;

import business.vehicle.as.ASVehicle;
import business.vehicle.factory.imp.ASVehicleFactoryImp;

public abstract class ASVehicleFactory {
    private static ASVehicleFactory instance;

    public static ASVehicleFactory getInstance(){
        if(instance == null)
            instance = new ASVehicleFactoryImp();
        return  instance;
    }

    public abstract ASVehicle generateASVehicle();
}
