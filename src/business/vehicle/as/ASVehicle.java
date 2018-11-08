package business.vehicle.as;

import business.ASException;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;

import java.util.Collection;

public interface ASVehicle {
    Integer create(TVehicle vehicle) throws ASException;

    Integer drop(Integer vehicle) throws ASException;

    Integer update(TVehicle vehicle) throws ASException;

    TVehicleDetails show (Integer id) throws ASException;

    Collection<TVehicleDetails> showAll () throws ASException;

    Collection<TVehicleDetails> showAllAvailableVehicles() throws ASException;

    TVehicleDetails getVehicleDetails(Integer vehicleID) throws ASException;

    Collection<TVehicleDetails> getAllVehiclesDetails() throws ASException;
}
