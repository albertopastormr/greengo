package integration.rental.factory.imp;

import integration.rental.dao.DAORental;
import integration.rental.dao.imp.DAORentalImp;
import integration.rental.factory.DAORentalFactory;

public class DAORentalFactoryImp extends DAORentalFactory {
    @Override
    public DAORental generateDAORental() {
        return new DAORentalImp();
    }
}
