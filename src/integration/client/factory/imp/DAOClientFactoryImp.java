package integration.client.factory.imp;

import integration.client.dao.DAOClient;
import integration.client.dao.imp.DAOClientImp;
import integration.client.factory.DAOClientFactory;

public class DAOClientFactoryImp extends DAOClientFactory {

    @Override
    public DAOClient generateDAOClient() {
        return new DAOClientImp();
    }
}
