package integration.rental.factory;

import integration.rental.dao.DAORental;
import integration.rental.factory.imp.DAORentalFactoryImp;

public abstract class DAORentalFactory {
    private static DAORentalFactory instance;

    public static DAORentalFactory getInstance(){
        if(instance == null)
            instance = new DAORentalFactoryImp();
        return instance;
    }

    public abstract DAORental generateDAORental();
}
