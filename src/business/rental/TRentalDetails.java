package business.rental;

import business.client.TClient;
import business.vehicle.TVehicle;

public class TRentalDetails {
    private TClient client;
    private TRental rental;
    private TVehicle vehicle;

    public TRentalDetails(TClient client, TRental rental, TVehicle vehicle) {
        this.client = client;
        this.rental = rental;
        this.vehicle = vehicle;
    }

    //TODO implemetar getter necesarios
}
