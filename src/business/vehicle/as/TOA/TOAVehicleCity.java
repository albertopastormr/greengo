package business.vehicle.as.TOA;

import business.city.TCity;
import business.city.factory.ASCityFactory;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;

import java.util.ArrayList;
import java.util.Collection;

public class TOAVehicleCity {

    public TVehicleDetails getVehicleDetails(Integer vehicleID){
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(vehicleID);
        TCity city = ASCityFactory.getInstance().generateASCity().show(vehicle.getCity());

        return new TVehicleDetails(vehicle,city);
    }

    public Collection<TVehicleDetails> getAllVehiclesDetails(){
        Collection<TVehicle> vehicles = ASVehicleFactory.getInstance().generateASVehicle().showAll();
        Collection<TVehicleDetails> details = new ArrayList<>();

        for(TVehicle vehicle : vehicles){
            Integer cityID = vehicle.getCity();
            TCity city = ASCityFactory.getInstance().generateASCity().show(cityID);
            details.add(new TVehicleDetails(vehicle,city));
        }

        return details;
    }
}
