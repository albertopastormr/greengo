package business.city.factory.imp;

import business.city.as.ASCity;
import business.city.as.imp.ASCityImp;
import business.city.factory.ASCityFactory;

public class ASCityFactoryImp extends ASCityFactory {

    @Override
    public ASCity generateASCity(){
        return new ASCityImp();
    }
}
