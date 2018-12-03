package business.rental.factory;

import business.rental.as.ASRental;
import business.rental.factory.imp.ASRentalFactoryImp;

public abstract class ASRentalFactory {
    private static ASRentalFactory instance;

    public static synchronized ASRentalFactory getInstance(){
        if(instance == null)
            instance = new ASRentalFactoryImp();
        return instance;
    }

    public abstract ASRental generateASRental();
}
