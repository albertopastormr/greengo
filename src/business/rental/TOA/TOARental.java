package business.rental.TOA;

import business.ASException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.rental.factory.DAORentalFactory;

import java.util.ArrayList;
import java.util.Collection;

public class TOARental {

    public TRentalDetails getRentalDetails(Integer idRental) throws ASException {
        TRental rental = DAORentalFactory.getInstance().generateDAORental().readById(idRental);
        TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();

        return new TRentalDetails(client,rental,vehicle);
    }

    public Collection<TRentalDetails> getAllRentalsDetails() throws ASException {
        Collection<TRental> rentals = DAORentalFactory.getInstance().generateDAORental().readAll();
        Collection<TRentalDetails> details = new ArrayList<>();

        for(TRental rental : rentals){
            TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
            TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();
            details.add(new TRentalDetails(client,rental,vehicle));
        }

        return details;
    }
}
