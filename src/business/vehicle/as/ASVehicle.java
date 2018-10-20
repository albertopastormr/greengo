package business.vehicle.as;

import business.vehicle.TVehicle;

import java.util.Collection;

public interface ASVehicle {
    Integer create(TVehicle vehicle);

    Integer drop(TVehicle vehicle);

    Integer update(TVehicle vehicle);

    TVehicle show (Integer id);

    Collection<TVehicle> showAll ();

    Collection<TVehicle> showAllActiveVehicles();

}
