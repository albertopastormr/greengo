package business.client.as;

import business.client.TClient;

import java.util.Collection;

public interface ASClient {
    Integer create(TClient client);

    Integer drop(Integer client);

    Integer update(TClient client);

    TClient show (Integer id);

    Collection<TClient> showAll ();

    Collection<TClient> showAllNRentals (Integer N);

}
