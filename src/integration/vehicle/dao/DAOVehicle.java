package integration.vehicle.dao;

import business.vehicle.TVehicle;
import integration.DAOException;

import java.util.Collection;

public interface DAOVehicle {
    Integer create(TVehicle vehicle) throws DAOException;

    Integer update(TVehicle vehicle);

    TVehicle readById(Integer id) ;

    Collection<TVehicle> readAll();

    Collection<TVehicle> showAllActiveVehicles();

    void deleteAll() throws DAOException;
    
    Collection<TVehicle> showVehiclesByCity(Integer id);

    TVehicle showByPlateOrSerial(String plate);

}
