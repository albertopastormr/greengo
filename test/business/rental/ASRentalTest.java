package business.rental;

import business.ASException;
import business.IncorrectInputException;
import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.as.ASRental;
import business.rental.factory.ASRentalFactory;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.client.dao.DAOClient;
import integration.client.factory.DAOClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ASRentalTest {
    private static Date dFrom = new Date(1540373530000L);
    private static Date dTo = new Date(1543051930000L);
    private static ASClient asC = ASClientFactory.getInstance().generateASClient();
    private static TClient tc = new TClient(null,"00000000X",0,false);
    private static ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0,
            false,null,false,"car");
    private static ASRental as = ASRentalFactory.getInstance().generateASRental();
    private static TRental tr = new TRental(null,null,false,10,null,dFrom,dTo);

    @BeforeEach
    private void setUp() throws Exception{
        DAOClient dao = DAOClientFactory.getInstance().generateDAOClient();
        dao.deleteAll();

    }


    //create method
    @Test
    public void createRentalSuccessful(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        Integer idR = as.create(tr);

        assertTrue(idR > 0);
    }

    @Test
    public void createRentalIncorrectInputID(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setId(0); //id must be null

        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle1(){
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle2(){
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(0);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDVehicle3(){
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);

        //id vehicle must be > 0
        tr.setIdVehicle(-1);
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputActive(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setActive(true); //active must be false
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputNumKmRented1(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setNumKmRented(0); //num km rented must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

     @Test
    public void createRentalIncorrectInputNumKmRented2(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setNumKmRented(-1); //num km rented must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient() {
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(null); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient2() {
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(0); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputIDClient3() {
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(-1); //ID cliente must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDateFrom(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setDateFrom(null); //date from must be a valid date
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalIncorrectInputDateTo(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        tr.setDateTo(null); //date to must be a valid date
        assertThrows(IncorrectInputException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalVehicleNotExists(){
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);

        tr.setIdVehicle(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalVehicleNotActive(){
        Integer idV = asV.create(tv);
        asV.drop(idV);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalDatesNotValidForVehicle(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        as.create(tr);

        Integer idTMPvehicle = asV.create(tv);
        tr.setIdVehicle(idTMPvehicle);

        assertThrows(ASException.class,() -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotExists(){
        Integer idV = asV.create(tv);
        tr.setIdVehicle(idV);

        tr.setIdClient(1);
        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalClientNotActive(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        asC.drop(idC);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        assertThrows(ASException.class, () -> {as.create(tr);});
    }

    @Test
    public void createRentalDatesNotValidForClient(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        as.create(tr);

        tc.setIdCardNumber("11111111X");
        Integer idTMPClient = asC.create(tc);
        tr.setIdClient(idTMPClient);

        assertThrows(ASException.class,() -> {as.create(tr);});
    }

    @Test
    public void createRentalKmRentedExceedVehicleEstimatedDuration(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        // num km rented must be <= estimated Duration of the vehicle
        tr.setNumKmRented(tv.getEstimatedDuration());
        assertThrows(ASException.class,() -> {as.create(tr);});
    }
    //Drop method

    @Test
    public void dropRentalSuccessful(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        Integer idR = as.create(tr);

        assertEquals(as.show(idR).isActive(), false);
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
    public void dropRentalAlreadyInactive(){
        Integer idV = asV.create(tv);
        Integer idC = asC.create(tc);
        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        Integer idR = as.create(tr);
        as.drop(idR);

        assertThrows(ASException.class, () -> {as.drop(idR);});
    }
}
