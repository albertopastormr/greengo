package integration.city.factory.imp;

import integration.city.dao.DAOCity;
import integration.city.dao.imp.DAOCityImp;
import integration.city.factory.DAOCityFactory;

public class DAOCityFactoryImp extends DAOCityFactory {
    @Override
    public DAOCity generateDAOCity() {
        return new  DAOCityImp();
    }
}
