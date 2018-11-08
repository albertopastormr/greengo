package integration.vehicle.dao.imp;

import business.vehicle.TVehicle;
import integration.vehicle.dao.DAOVehicle;

import java.util.Collection;

public class DAOVehicleImp  implements DAOVehicle {
    @Override
    public Integer create(TVehicle vehicle) {
        return null;
    }

    @Override
    public Integer update(TVehicle vehicle) {
        return null;
    }

    @Override
    public TVehicle readById(Integer id) {
        return null;
    }

    @Override
    public Collection<TVehicle> readAll() {
        return null;
    }

    @Override
    public Collection<TVehicle> showAllActiveVehicles() {
        return null;
    }

    @Override
    public Collection<TVehicle> showVehiclesByCity(Integer id) {
        return null;
    }

    @Override
    public TVehicle showByPlateOrSerial(String plate) {
        return null;
    }
    @Override
    public void deleteAll() {

    }

}
