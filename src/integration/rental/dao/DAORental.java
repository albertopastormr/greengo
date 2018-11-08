package integration.rental.dao;

import business.client.TClient;
import business.rental.TRental;

import java.util.Collection;

public interface DAORental {
    Integer create(TRental rental);

    Integer update(TRental rental);

    TRental readById(Integer id) ;

    Collection<TRental> showRentalsByClient(Integer id);

    Collection<TRental> showRentalsByVehicle(Integer id);

    Collection<TRental> readAll();

    void deleteAll();

}
