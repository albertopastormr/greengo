package business.vehicle;

import business.city.TCity;
import business.vehicle.TVehicle;

public class TVehicleDetails {
    private TVehicle vehicle;
    private TCity city;

    public TVehicleDetails(TVehicle vehicle, TCity city) {
        this.vehicle = vehicle;
        this.city = city;
    }

    //TODO implementar getters necesarios
    public TVehicle getVehicle(){
        return vehicle;
    }
}
