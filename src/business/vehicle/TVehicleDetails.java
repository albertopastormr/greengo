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

    @Override
    public String toString() {
        String ret = "";
        ret += String.format("%-13s %13s %n", "Id: ", vehicle.getId());
        ret += String.format("%-13s %13s %n", "Type: ", vehicle.getType());
        ret += String.format("%-13s %13s %n", "Brand: ", vehicle.getBrand());
        ret += String.format("%-13s %13s %n", "Estimated duration: ", vehicle.getEstimatedDuration());
        ret += String.format("%-13s %13s %n", "Km travelled: ", vehicle.getNumKmTravelled());
        ret += String.format("%-13s %13s %n", "City id: ", city.getId() + "(" + city.getName() + " )");
        ret += String.format("%-13s %13s %n", "Occupied: ", vehicle.isOccupied());
        ret += String.format("%-13s %13s %n", "Active: ", vehicle.isActive());

        return ret;
    }
}
