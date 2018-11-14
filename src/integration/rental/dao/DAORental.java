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

    Collection<TRental> readByRentalsByClient(Integer id) throws DAOException;

    Collection<TRental> readByRentalsByVehicle(Integer id) throws DAOException;

    Collection<TRental> readAll() throws DAOException;

    Collection<TRental> readByIdClientAndDateRange(Integer id, Date dateFrom, Date dateTo) throws DAOException;

    Collection<TRental> readByIdVehicleAndDateRange(Integer id, Date dateFrom, Date dateTo) throws DAOException;

    void deleteAll() throws DAOException;

}
