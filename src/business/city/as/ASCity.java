package business.city.as;

import business.city.TCity;
import business.client.TClient;

import java.util.Collection;

public interface ASCity {
    Integer create(TCity city);

    Integer drop(TCity city);

    Integer update(TCity city);

    TCity show (Integer id);

    Collection<TCity> showAll ();

    Collection<TClient> showClientsByCity (Integer idCity);
}
