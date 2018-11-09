package integration.rental.dao;

import business.client.TClient;
import business.rental.TRental;
import integration.DAOException;

import java.util.Collection;
import java.util.Date;

public interface DAORental {
    Integer create(TRental rental) throws DAOException;

    Integer update(TRental rental) throws DAOException;

    TRental readById(Integer id) throws DAOException;

    Collection<TRental> showRentalsByClient(Integer id) throws DAOException;

    Collection<TRental> showRentalsByVehicle(Integer id) throws DAOException;

    Collection<TRental> readAll() throws DAOException;

    Boolean checkAvailableDates(TRental rental);

    void deleteAll() throws DAOException;

}
