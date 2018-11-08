package integration.vehicle.dao;

import business.vehicle.TVehicle;

import java.util.Collection;

public interface DAOVehicle {
    Integer create(TVehicle vehicle);

    Integer update(TVehicle vehicle);

    TVehicle readById(Integer id) ;

    Collection<TVehicle> readAll();

    Collection<TVehicle> showAllActiveVehicles();

    Collection<TVehicle> showVehiclesByCity(Integer id);

    TVehicle showByPlate(String plate);

}
