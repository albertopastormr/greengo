package integration.city.dao;

import business.city.TCity;
import business.client.TClient;
import java.util.Collection;

public interface DAOCity {
    Integer create(TCity city);

    Integer update(TCity city);

    TCity readById(Integer id) ;

    Collection<TCity> readAll();

    Collection<TClient> showClientsByCity(Integer idCity);
}
