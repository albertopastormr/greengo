package business.vehicle;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.city.as.ASCity;
import business.city.factory.ASCityFactory;
import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.rental.as.ASRental;
import business.rental.factory.ASRentalFactory;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Date;

public class ASVehicleTest {

    private static Date dFrom = new Date(1540373530000L);
    private static Date dTo = new Date(1543051930000L);
    private static ASVehicle as = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0, false,null,false,"car");
    private static ASCity asCity = ASCityFactory.getInstance().generateASCity();
    private static TCity tCity = new TCity(null,"Madrid",false);
    private static ASClient asClient = ASClientFactory.getInstance().generateASClient();
    private static TClient tClient = new TClient(null,"00000000X",0,false);
    private static ASRental asR = ASRentalFactory.getInstance().generateASRental();
    private static TRental tr = new TRental(null,null,false,10,null,dFrom,dTo);

    @BeforeEach
    public void setUp(){
        DAOVehicle dao = DAOVehicleFactory.getInstance().generateDAOVehicle();
        dao.deleteAll();
    }

    @Test
    public void createVehicleSuccessful(){
        Integer idCity = asCity.create(tCity);

        tv.setCity(idCity);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
    }

    @Test
    public void createVehicleIncorrectID(){
        tv.setId(1); //id must be null
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectEstimatedDuration1(){
        tv.setEstimatedDuration(-1); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectEstimatedDuration2(){
        tv.setEstimatedDuration(0); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    //Drop methods
    @Test
    public void dropSuccessful(){
        Integer id = as.create(tv);
        Integer idC = asCity.create(tCity);

        tv.setId(id);
        tv.setCity(idC);

        as.drop(id);

        assertTrue(as.showAll().isEmpty());
        assertTrue(asCity.show(idC).isActive());
    }

    @Test
    public void dropVehicleIncorrectInput0(){
        assertThrows(IncorrectInputException.class, () -> {as.drop(0);} ); //id must be > 0
    }

    @Test
    public void dropVehicleIncorrectInput1(){
        assertThrows(IncorrectInputException.class, () -> {as.drop(-1);} ); //id must be > 0
    }

    @Test
    public void dropVehicletNotExists(){
        assertThrows(ASException.class, () -> {as.drop(50);});
    }

    @Test
    public void dropVehicleWithActiveRentals(){
        Integer idC = asClient.create(tClient);
        Integer idV = as.create(tv);

        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        asR.create(tr);

        assertThrows(ASException.class,()->{as.drop(idV);});
    }

    //Show method

    @Test
    public void showVehicleSuccessful(){
        Integer idV = as.create(tv);
        Integer idCity = asCity.create(tCity);

        tv.setCity(idCity);

        TVehicle out = as.show(idV);

        assertEquals(out.getId(),idV);
        assertEquals(out.getBrand(), tv.getBrand());
        assertEquals(out.getCity(), tv.getCity());
        assertEquals(out.getType(), tv.getType());
        assertEquals(out.getEstimatedDuration(), tv.getEstimatedDuration());
        assertEquals(out.getNumKmTravelled(), tv.getNumKmTravelled());
        assertEquals(out.isActive(), tv.isActive());
    }

    @Test
    public void showVehicleIncorrectInputID1(){
        assertThrows(IncorrectInputException.class, () -> {as.show(0);}); //id must be > 0
    }

    @Test
    public void showVehicleIncorrectInputID2(){
        assertThrows(IncorrectInputException.class, () -> {as.show(-1); }); //id must be > 0
    }

    @Test
    public void showVehicleNotExists(){
        assertThrows(ASException.class, () -> {as.show(50);});
    }


    //showAll method
    @Test
    public void showAllVechicleSuccessful() {
        Integer idV = as.create(tv);

        TVehicle tv2 = new TVehicle(null, "Audi", 1000, 300, false, 1, false, "car");

        as.create(tv2);

        Collection<TVehicle> collec = as.showAll();
        for (TVehicle tmp : collec) {
            if (tmp.getId().equals(idV))
                assertTrue(checkTransferValues(tmp, tv.getBrand(), tv.getEstimatedDuration(), tv.getCity(), tv.getNumKmTravelled() ,tv.getType()));
            else
                assertTrue(checkTransferValues(tmp, tv2.getBrand(), tv2.getEstimatedDuration(), tv2.getCity(), tv2.getNumKmTravelled(), tv2.getType()));
        }
    }

    private boolean checkTransferValues(TVehicle tv, String brand, Integer estimatedDuration, Integer city, Integer numKmTravelled, String type){
        return  tv.getBrand().equals(brand) && tv.getEstimatedDuration().equals(estimatedDuration) && tv.getCity().equals(city)
                && tv.getNumKmTravelled().equals(numKmTravelled) && tv.getType().equals(type) && tv.isActive();
    }

    @Test
    public void showAllVehicleSuccessful2(){
        Collection<TVehicle> c = as.showAll();
        assertTrue(c.isEmpty());
    }

    //Update method
    @Test
    public void updateVehicletSuccessful(){
        Integer idV = as.create(tv);

        TVehicle updateVehicle = new TVehicle(idV, "Audi", 1000, 300, false, 1, false, "car");
        Integer idOut = as.update(updateVehicle);

        TVehicle out = as.show(idOut);

        assertEquals(out.getId(),idV);
        assertEquals(out.getBrand(), updateVehicle.getBrand());
        assertEquals(out.getNumKmTravelled(),updateVehicle.getNumKmTravelled());
        assertEquals(out.getEstimatedDuration(),updateVehicle.getEstimatedDuration());
        assertEquals(out.getType(),updateVehicle.getType();
        assertEquals(out.getCity(),updateVehicle.getCity();
        assertEquals(out.isActive(),updateVehicle.isActive());
    }

    @Test
    public void updateClientIncorrectInputID(){
        //id can't be null and must be > 0
        tv.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateClientNotExists(){
        tv.setId(1);
        assertThrows(ASException.class, () -> {as.update(tv);});
    }
    
}
