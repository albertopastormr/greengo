package business.rental;

import business.client.TClient;
import business.vehicle.TVehicle;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class TRentalDetails {
    private TClient client;
    private TRental rental;
    private TVehicle vehicle;

    public TRentalDetails(TClient client, TRental rental, TVehicle vehicle) {
        this.client = client;
        this.rental = rental;
        this.vehicle = vehicle;
    }

    //TODO implement all the necessary getters.


    public TClient getClient() {
        return client;
    }

    public TRental getRental() {
        return rental;
    }

    public TVehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString(){

        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        GregorianCalendar dateFromCal = new GregorianCalendar();
        dateFromCal.setTimeInMillis(rental.getDateFrom().getTime());
        GregorianCalendar dateToCal = new GregorianCalendar();
        dateToCal.setTimeInMillis(rental.getDateTo().getTime());

        fmt.setCalendar(dateFromCal);
        String dateFromS = fmt.format(dateFromCal.getTime());
        fmt.setCalendar(dateToCal);
        String dateToS = fmt.format(dateToCal.getTime());

        String ret = "";
        ret += String.format("%-13s %13s %n", "Id: ", rental.getId());
        ret += String.format("%-13s %13s %n", "Client id: ", client.getId());
        ret += String.format("%-13s %13s %n", "Vehicle id: ", vehicle.getId());
        ret += String.format("%-13s %13s %n", "Km rented: ", rental.getNumKmRented());
        ret += String.format("%-13s %13s %n", "From: ", dateFromS);
        ret += String.format("%-13s %13s %n", "To: ", dateToS);
        ret += String.format("%-13s %13s %n", "Active: ", rental.isActive());

        return ret;
    }
}
