package business.client.factory;

import business.client.as.ASClient;
import business.client.factory.imp.ASClientFactoryImp;

public abstract class ASClientFactory {
    private static ASClientFactoryImp instance;

    public static ASClientFactory getInstance() {
        if (instance == null)
            instance = new ASClientFactoryImp();
        return instance;
    }

    public abstract ASClient generateASClient();
}
