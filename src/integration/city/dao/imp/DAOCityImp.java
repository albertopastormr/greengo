package integration.city.dao.imp;

import business.city.TCity;
import business.client.TClient;
import integration.city.dao.DAOCity;

import java.util.Collection;

public class DAOCityImp implements DAOCity {
    @Override
    public Integer create(TCity city) {
        return null;
    }

    @Override
    public Integer update(TCity city) {
        return null;
    }

    @Override
    public TCity readById(Integer id) {
        return null;
    }

    @Override
    public Collection<TCity> readAll() {
        return null;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) {
        return null;
    }
}
