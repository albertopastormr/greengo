package integration.vehicle.dao;

import business.vehicle.TVehicle;
import integration.DAOException;

import java.util.Collection;

public interface DAOVehicle {
    Integer create(TVehicle vehicle) throws DAOException;

    Integer update(TVehicle vehicle) throws DAOException;

    TVehicle readById(Integer id) throws DAOException;

    Collection<TVehicle> readAll() throws DAOException;

    Collection<TVehicle> readAllAvailableVehicles() throws DAOException;

    Collection<TVehicle> readVehiclesByCity(Integer id) throws DAOException;

    Integer readByPlateOrSerialNumber(TVehicle vehicle) throws DAOException;

    void deleteAll() throws DAOException;

}
