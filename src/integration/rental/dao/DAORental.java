package integration.rental.dao;

import business.rental.TRental;

import java.util.Collection;

public interface DAORental {
    Integer create(TRental rental);

    Integer update(TRental rental);

    TRental readById(Integer id) ;

    Collection<TRental> readAll();

}
