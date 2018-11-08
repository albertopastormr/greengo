package business.vehicle.as;

import business.ASException;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;

import java.util.Collection;

public interface ASVehicle {
    Integer create(TVehicle vehicle) throws ASException;

    Integer drop(Integer vehicle);

    Integer update(TVehicle vehicle);

    TVehicleDetails show (Integer id);

    Collection<TVehicleDetails> showAll ();

    Collection<TVehicleDetails> showAllActiveVehicles();

}
