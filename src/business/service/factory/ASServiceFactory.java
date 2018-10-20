package business.service.factory;

import business.service.as.ASService;
import business.service.factory.imp.ASServiceFactoryImp;

public abstract class ASServiceFactory {
    private static ASServiceFactory instance;

    public static  ASServiceFactory getInstance(){
        if (instance == null)
            instance = new ASServiceFactoryImp();
        return instance;
    }

    public abstract ASService generateASService();
}
