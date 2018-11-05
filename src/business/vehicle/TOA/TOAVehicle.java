package business.vehicle.TOA;

import business.ASException;
import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.vehicle.TVehicle;
import business.vehicle.TVehicleDetails;
import business.vehicle.factory.ASVehicleFactory;

import java.util.ArrayList;
import java.util.Collection;

public class TOAVehicle {

    public TVehicleDetails getVehicleDetails(Integer vehicleID) throws ASException {
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(vehicleID).getVehicle();
        TCity city = ASCityFactory.getInstance().generateASCity().show(vehicle.getCity());

        return new TVehicleDetails(vehicle,city);
    }

    public Collection<TVehicleDetails> getAllVehiclesDetails() throws ASException {
        Collection<TVehicleDetails> vehicles = ASVehicleFactory.getInstance().generateASVehicle().showAll();
        Collection<TVehicleDetails> details = new ArrayList<>();

        for(TVehicleDetails vehicle : vehicles){
            Integer cityID = vehicle.getVehicle().getCity();
            TCity city = ASCityFactory.getInstance().generateASCity().show(cityID);
            details.add(new TVehicleDetails(vehicle.getVehicle(),city));
        }

        return details;
    }
}
