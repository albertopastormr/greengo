package business.rental.factory.imp;

import business.rental.as.ASRental;
import business.rental.as.imp.ASRentalImp;
import business.rental.factory.ASRentalFactory;

public class ASRentalFactoryImp extends ASRentalFactory {
    @Override
    public ASRental generateASRental() {
        return new ASRentalImp();
    }
}
