package business.client.factory.imp;

import business.client.as.ASClient;
import business.client.as.imp.ASClientImp;
import business.client.factory.ASClientFactory;

public class ASClientFactoryImp extends ASClientFactory {

    @Override
    public ASClient generateASClient() {
        return new ASClientImp();
    }
}
