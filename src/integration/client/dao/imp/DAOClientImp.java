package integration.client.dao.imp;

import business.client.TClient;
import integration.client.dao.DAOClient;

import java.util.Collection;

public class DAOClientImp implements DAOClient {

    @Override
    public Integer create(TClient client) {
        return null;
    }

    @Override
    public Integer update(TClient client) {
        return null;
    }

    @Override
    public TClient readById(Integer id) {
        return null;
    }

    @Override
    public TClient readByIdCard(String idCard) { return null; }

    @Override
    public Collection<TClient> readAll() {
        return null;
    }

    @Override
    public Collection<TClient> readByNRentals(Integer N) {
        return null;
    }
}
