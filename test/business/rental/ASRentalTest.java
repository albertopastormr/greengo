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
import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
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
    private static Date dFrom = new Date(117,10,4);
    private static Date dTo = new Date(117,11,4);
    private static ASClient asClient = ASClientFactory.getInstance().generateASClient();
    private static ASCity asCity = ASCityFactory.getInstance().generateASCity();
    private static TCity tCity = new TCity(null,"Madrid",false);
    private static TClient tClient = new TClient(null,"00000000X",0,true);
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
    public void createRentalSuccessful() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

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
    public void createRentalIncorrectInputID() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

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
    public void createRentalIncorrectInputIDVehicle1() throws ASException, IncorrectInputException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle2() throws ASException, IncorrectInputException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(0);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle3() throws ASException, IncorrectInputException {
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(-1);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputNumKmRented1() throws ASException, IncorrectInputException {
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
    public void createRentalIncorrectInputNumKmRented2() throws ASException, IncorrectInputException {
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
    public void createRentalIncorrectInputIDClient() throws ASException, IncorrectInputException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(null); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient2() throws ASException, IncorrectInputException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(0); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient3() throws ASException, IncorrectInputException {
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(-1); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDateFrom() throws ASException, IncorrectInputException {
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
    public void createRentalIncorrectInputDateTo() throws ASException, IncorrectInputException {
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
    public void createRentalIncorrectInputDates() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tr = new TRental(null,null,false,10,null,dFrom,dTo);
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
    public void createRentalVehicleNotExists() throws ASException, IncorrectInputException {
        tr = new TRental(null,null,false,10,null,dFrom,dTo);

        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);

        tr.setIdVehicle(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalVehicleNotActive() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Avila",true);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,false,10,null,dFrom,dTo);

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
    public void createRentalDatesNotValidForVehicle() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Avila",true);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,false,10,null,dFrom,dTo);
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);

        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        as.create(tr);

        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,idC,true,123455);
        Integer idTMPvehicle = asV.create(tv);
        tr.setIdVehicle(idTMPvehicle);

        assertThrows(ASException.class,() -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotExists() throws ASException, IncorrectInputException {
        tr = new TRental(null,null,false,10,null,dFrom,dTo);
        tv = new TCarVehicle(null,"Audi",6000,0,
                false,null,false,"WWWW");
        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotActive() throws ASException, IncorrectInputException {
        tr = new TRental(null,null,false,10,null,dFrom,dTo);
        tv = new TCarVehicle(null,"Audi",6000,0,
                false,null,false,"WWWW");

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
    public void createRentalDatesNotValidForClient() throws ASException, IncorrectInputException {
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
    public void createRentalKmRentedExceedVehicleEstimatedDuration() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",true);
        tr = new TRental(null,null,false,10000,null,dFrom,dTo);

        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        // num km rented must be <= estimated Duration of the vehicle
        assertThrows(ASException.class,() -> {as.create(tr);});
    }
    //Drop method

    @Test
    public void dropRentalSuccessful() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idV = asV.create(tv);
        Integer idC = asClient.create(tClient);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        Integer idR = as.create(tr);
        as.drop(idR);

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
    public void dropRentalAlreadyInactive() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

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
    void updateRentalSuccessful() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idCity = asCity.create(tCity);
        tv.setCity(idCity);
        Integer idVehicle = asV.create(tv);
        Integer idClient = asClient.create(tClient);

        tr.setIdVehicle(idVehicle);
        tr.setIdClient(idClient);
        Integer idRental = as.create(tr);
        tr.setId(idRental);

        tr.setNumKmRented(1);
        tr.setDateFrom(new Date(127,10,4));
        tr.setDateTo(new Date(127,11,4));
        Integer tmpID = as.update(tr);

        assertEquals(idRental,tmpID);

        TRental updtRental = as.show(tmpID).getRental();

        assertTrue(checkValues(tr,updtRental));
    }

    //TODO bussiness rules errors
    //comprobar uno con client, uno con vehiculo y otro ambos vacios
    //ifual update

    @Test
    void createRentalVehicleOccupied() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = asV.create(tv);
        Integer idClient = asClient.create(tClient);

        tr.setIdClient(idClient);
        tr.setIdVehicle(idV);

        as.create(tr);

        tClient = new TClient(null,"00000023X",0,true);


        tr.setIdClient(asClient.create(tClient));
        assertThrows(ASException.class,()->as.create(tr));

    }


    @Test
    void createRentalClientOccupied() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = asV.create(tv);
        Integer idClient = asClient.create(tClient);

        tr.setIdClient(idClient);
        tr.setIdVehicle(idV);

        as.create(tr);

        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,idC,true,213);


        tr.setIdVehicle(asV.create(tv));

        assertThrows(ASException.class,()->as.create(tr));

    }


    @Test
    void updateRentalClientOccupied() throws ASException, IncorrectInputException {
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,null,true,12345);
        tCity = new TCity(null,"Madrid",false);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = asV.create(tv);
        Integer idClient = asClient.create(tClient);

        tr.setIdClient(idClient);
        tr.setIdVehicle(idV);

        Integer idR = as.create(tr);

        tr.setId(idR);
        tv = new TBicycleVehicle(null,"Tesla",6000,0,
                false,idC,true,213);


        tr.setId(asV.create(tv));

        assertThrows(ASException.class,()->as.update(tr));
    }


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
