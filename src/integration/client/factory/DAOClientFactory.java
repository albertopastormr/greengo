package integration.client.factory;

import integration.client.dao.DAOClient;
import integration.client.factory.imp.DAOClientFactoryImp;

public abstract class DAOClientFactory {
    private static DAOClientFactory instance;

    public static DAOClientFactory getInstance(){
        if(instance == null)
            instance = new DAOClientFactoryImp();
        return instance;
    }

    public abstract DAOClient generateDAOClient();
}
