package business.city.factory;

import business.city.as.ASCity;
import business.city.factory.imp.ASCityFactoryImp;
public abstract class ASCityFactory {

    private static ASCityFactory instance;

    public static ASCityFactory getInstance(){
        if(instance == null)
            instance = new ASCityFactoryImp();
        return instance;
    }

    public abstract ASCity generateASCity();
}
