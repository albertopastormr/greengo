package integration.city.factory;

import integration.city.dao.DAOCity;
import integration.city.factory.imp.DAOCityFactoryImp;

public abstract class DAOCityFactory {
    private static DAOCityFactory instance;

    public static DAOCityFactory getInstance(){
        if(instance == null)
            instance = new DAOCityFactoryImp();
        return instance;
    }

    public abstract DAOCity generateDAOCity();
}
