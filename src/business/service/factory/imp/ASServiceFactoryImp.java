package business.service.factory.imp;

import business.service.as.ASService;
import business.service.as.imp.ASServiceImp;
import business.service.factory.ASServiceFactory;

public class ASServiceFactoryImp extends ASServiceFactory {
    @Override
    public ASService generateASService() {
        return new ASServiceImp();
    }
}
