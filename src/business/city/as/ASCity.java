package business.city.as;

import business.ASException;
import business.city.TCity;
import business.client.TClient;

import java.util.Collection;

public interface ASCity {
    Integer create(TCity city) throws ASException;

    Integer drop(Integer city) throws ASException;

    Integer update(TCity city) throws ASException;

    TCity show (Integer id) throws ASException;

    Collection<TCity> showAll () throws ASException;

    Collection<TClient> showClientsByCity (Integer idCity) throws ASException;
}
