package integration.city.dao;

import business.city.TCity;
import business.client.TClient;
import business.vehicle.TVehicle;
import integration.DAOException;

import java.util.Collection;

public interface DAOCity {

    Integer create(TCity city) throws DAOException;

    Integer update(TCity city) throws DAOException;

    TCity readById(Integer id) throws DAOException;

    Collection<TCity> readAll() throws DAOException;

    Collection<TClient> showClientsByCity(Integer idCity) throws DAOException;

    void deleteAll() throws DAOException;
}
