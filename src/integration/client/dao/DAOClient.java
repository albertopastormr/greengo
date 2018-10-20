package integration.client.dao;


import business.client.TClient;
import business.rental.TRental;

import java.util.Collection;

public interface DAOClient {
    Integer create(TClient client);

    Integer update(TClient client);

    TClient readById(Integer id) ;

    Collection<TClient> readAll();

    Collection<TClient> readByNRentals(Integer N);


}
