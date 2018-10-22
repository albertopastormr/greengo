package business.city.as.imp;

import business.city.TCity;
import business.city.as.ASCity;
import business.client.TClient;

import java.util.Collection;

public class ASCityImp implements ASCity {

    @Override
    public Integer create(TCity city) {
        return null;
    }

    @Override
    public Integer drop(Integer city) {
        return null;
    }

    @Override
    public Integer update(TCity city) {
        return null;
    }

    @Override
    public TCity show(Integer id) {
        return null;
    }

    @Override
    public Collection<TCity> showAll() {
        return null;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) {
        return null;
    }
}
