package business.vehicle.as;

import business.ASException;
import business.IncorrectInputException;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;

import java.util.Collection;

public interface ASVehicle {
    Integer create(TVehicle vehicle) throws ASException, IncorrectInputException;

    Integer drop(Integer vehicle) throws ASException, IncorrectInputException;

    Integer update(TVehicle vehicle) throws ASException, IncorrectInputException;

    TVehicleDetails show (Integer id) throws ASException, IncorrectInputException;

    Collection<TVehicleDetails> showAll () throws ASException;

    Collection<TVehicleDetails> showAllAvailableVehicles() throws ASException;

    //TVehicleDetails getVehicleDetails(Integer vehicleID) throws ASException, IncorrectInputException;

    //Collection<TVehicleDetails> getAllVehiclesDetails() throws ASException, IncorrectInputException;
}
