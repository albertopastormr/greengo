package business.rental;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.city.as.ASCity;
import business.city.factory.ASCityFactory;
import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.as.ASRental;
import business.rental.factory.ASRentalFactory;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.rental.dao.DAORental;
import integration.rental.factory.DAORentalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ASRentalTest {
    private static Date dFrom = new Date(1540373530000L);
    private static Date dTo = new Date(1543051930000L);
    private static ASClient asClient = ASClientFactory.getInstance().generateASClient();
    private static ASCity asCity = ASCityFactory.getInstance().generateASCity();
    private static TCity tCity = new TCity(null,"Madrid",false);
    private static TClient tClient = new TClient(null,"00000000X",0,false);
    private static ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0,
            false,null,false,"Car");
    private static ASRental as = ASRentalFactory.getInstance().generateASRental();
    private static TRental tr = new TRental(null,null,false,10,null,dFrom,dTo);

    @BeforeEach
    private void setUp() throws Exception{
        DAORental dao = DAORentalFactory.getInstance().generateDAORental();
        dao.deleteAll();
    }

    //create method
    @Test
    public void createRentalSuccessful() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idClient = asClient.create(tClient);
        tr.setIdClient(idClient);
        tr.setIdVehicle(idV);

        Integer idR = as.create(tr);

        assertTrue(idR > 0);
    }

    @Test
    public void createRentalIncorrectInputID() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idClient = asClient.create(tClient);
        tr.setIdClient(idClient);
        tr.setIdVehicle(idV);

        tr.setId(0); //id must be null

        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle1() throws ASException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle2() throws ASException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(0);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle3() throws ASException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(-1);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputActive() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setActive(true); //active must be false
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputNumKmRented1() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setNumKmRented(0); //num km rented must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

     @Test
    public void createRentalIncorrectInputNumKmRented2() throws ASException {
         Integer idCity = asCity.create(tCity);
         tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setNumKmRented(-1); //num km rented must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(null); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient2() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(0); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient3() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(-1); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDateFrom() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setDateFrom(null); //date from must be a valid date
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDateTo() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setDateTo(null); //date to must be a valid date
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDates() throws ASException{
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        //DateFrom must be < DateTo
        tr.setDateFrom(dTo);
        tr.setDateTo(dFrom);

        assertThrows(IncorrectInputException.class, () -> as.create(tr));
    }

    @Test
    public void createRentalVehicleNotExists() throws ASException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        tr.setIdVehicle(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalVehicleNotActive() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        asV.drop(idV);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalDatesNotValidForVehicle() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        as.create(tr);

        Integer idTMPvehicle = asV.create(tv);
        tr.setIdVehicle(idTMPvehicle);

        assertThrows(ASException.class,() -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotExists() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotActive() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        asClient.drop(idC);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalDatesNotValidForClient() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        as.create(tr);

        tClient.setIdCardNumber("11111111X");
        Integer idTMPClient = asClient.create(tClient);
        tr.setIdClient(idTMPClient);

        assertThrows(ASException.class,() -> {as.create(tr);});
    }

    @Test
    public void createRentalKmRentedExceedVehicleEstimatedDuration() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        // num km rented must be <= estimated Duration of the vehicle
        tr.setNumKmRented(tv.getEstimatedDuration());
        assertThrows(ASException.class,() -> {as.create(tr);});
    }
    //Drop method

    @Test
    public void dropRentalSuccessful() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        Integer idR = as.create(tr);

        assertEquals(as.show(idR).getRental().isActive(), false);
    }

    @Test
    public void dropRentalIncorrectInputID1(){
        //id must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.drop(null);});
    }

    @Test
    public void dropRentalIncorrectInputID2(){
        //id must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.drop(0);});
    }

    @Test
    public void dropRentalIncorrectInputID3(){
        //id must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.drop(-1);});
    }

    @Test
    public void dropRentalNotExists(){
        assertThrows(ASException.class, () -> {as.drop(1);});
    }

    @Test
    public void dropRentalAlreadyInactive() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        Integer idR = as.create(tr);
        as.drop(idR);

        assertThrows(ASException.class, () -> {as.drop(idR);});
    }

    //update method
    @Test
    void updateRentalSuccessful() throws ASException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idVehicle = asV.create(tv);
        Integer idClient = asClient.create(tClient);

        tr.setIdVehicle(idVehicle);
        tr.setIdClient(idClient);
        Integer idRental = as.create(tr);
        tr.setId(idRental);

        tr.setNumKmRented(1);
        tr.setDateFrom(new Date(1_536_624_000_000L));
        tr.setDateTo(new Date(1_541_894_400_000L));
        Integer tmpID = as.update(tr);

        assertEquals(idRental,tmpID);

        TRental updtRental = as.show(tmpID).getRental();

        assertTrue(checkValues(tr,updtRental));
    }

    //TODO incorrectInput errors
    //TODO bussiness rules errors

    //TODO implement missing methods from ASRental

    private boolean checkValues(TRental expected,TRental actual){
        return expected.getId().equals(actual.getId()) &&
                expected.getIdVehicle().equals(actual.getIdVehicle()) &&
                expected.getNumKmRented().equals(actual.getNumKmRented()) &&
                expected.getIdClient().equals(actual.getIdClient()) &&
                expected.getDateFrom().equals(actual.getDateFrom()) &&
                expected.getDateTo().equals(actual.getDateTo()) &&
                expected.isActive().equals(expected.isActive());
    }
}
