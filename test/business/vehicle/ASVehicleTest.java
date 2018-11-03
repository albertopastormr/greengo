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
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0,
            false,null,false,"car");
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

    //create method
    @Test
    public void createVehicleSuccessful(){
        Integer idC = asCity.create(tCity);

        // City exist and active
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(as.show(idV).isActive());
        assertTrue(idV > 0);
    }

    @Test
    public void createVehicleIncorrectInputID(){
        tv.setId(1); //id must be null
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputEstimatedDuration1(){
        tv.setEstimatedDuration(-1); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputEstimatedDuration2(){
        tv.setEstimatedDuration(0); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputNumKmTravelled1(){
        tv.setNumKmTravelled(-1); //numKmTravelled must be >= 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputNumKmTravelled2(){
        tv.setNumKmTravelled(-1); //numKmTravelled must be < estimatedDuration
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputOccupied(){
        tv.setOccupied(true); //occupied must be false
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputCity(){
        tv.setCity(null); //city can't be null
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputActive(){
        tv.setActive(true); //active must be false
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputType(){
        tv.setType("bike"); //type only can be car or bicycle on this version
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleCityNotExists(){
        tv.setCity(1);
        assertThrows(ASException.class, () -> {as.create(tv);});
    }

    //TODO vehiculo con matricula o numero de serie que ya existe


    //Drop method
    @Test
    public void dropSuccessful(){
        Integer id = as.create(tv);
        Integer idC = asCity.create(tCity);

        // Compruebas si existen y estan activos
        assertTrue(id > 0);
        assertTrue(as.show(id).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

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
    public void dropVehicleAlreadyInactive(){
        Integer id = as.create(tv);
        Integer idC = asCity.create(tCity);

        // Compruebas si existen y estan activos
        assertTrue(id > 0);
        assertTrue(as.show(id).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setId(id);
        tv.setCity(idC);
        as.drop(id);

        assertThrows(ASException.class, () -> {as.drop(id);});
    }

    @Test
    public void dropVehicleWithActiveRentals(){
        Integer idC = asClient.create(tClient);
        Integer idV = as.create(tv);

        // Compruebas si existen y estan activos
        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        Integer idR = asR.create(tr);
        assertTrue(idR > 0);
        assertTrue(asR.show(idR).isActive());

        assertThrows(ASException.class,()->{as.drop(idV);});
    }

    //Show method
    @Test
    public void showVehicleSuccessful(){
        Integer idC = asCity.create(tCity);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        tv.setId(idV);
        TVehicle out = as.show(idV);

        assertTrue(checkTransferValues(out,tv));
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
    public void showAllVehicleSuccessful() {
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());

        TVehicle tv2 = new TVehicle(null, "Audi", 1000, 300,
                false, 1, false, "car");

        Integer idV2 = as.create(tv2);

        assertTrue(idV2 > 0);
        assertTrue(as.show(idV2).isActive());

        Collection<TVehicle> collec = as.showAll();
        for (TVehicle tmp : collec) {
            if (tmp.getId().equals(idV))
                assertTrue(checkTransferValues(tmp,tv));
            else
                assertTrue(checkTransferValues(tmp, tv2));
        }
    }

    @Test
    public void showAllVehicleSuccessful2(){
        Collection<TVehicle> c = as.showAll();
        assertTrue(c.isEmpty());
    }

    //Update method

    @Test
    public void updateVehicleSuccessful(){
        Integer idC = asCity.create(tCity);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        TVehicle updatedVehicle = new TVehicle(idV, "Audi", 1000, 300, false, idC, false, "car");
        Integer idOut = as.update(updatedVehicle);

        assertTrue(idOut > 0);
        assertTrue(asCity.show(idOut).isActive());

        TVehicle out = as.show(idOut);

        assertEquals(out.getId(),idV);
        assertTrue(checkTransferValues(out,updatedVehicle));
    }

    @Test
    public void updateVehicleIncorrectInputID(){
        //id can't be null and must be > 0
        tv.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputEstimatedDuration1(){
        tv.setId(1);
        tv.setEstimatedDuration(-1); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputEstimatedDuration2(){
        tv.setId(1);
        tv.setEstimatedDuration(0); //estimated duration must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputEstimatedDuration3(){
        tv.setId(1);
        tv.setNumKmTravelled(100);
        tv.setEstimatedDuration(1); //estimated duration must be > num km travelled
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputNumKmTravelled1(){
        tv.setId(1);
        tv.setNumKmTravelled(-1); //num km travelled must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputNumKmTravelled2(){
        tv.setId(1);
        tv.setNumKmTravelled(0); //num km travelled must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputNumKmTravelled3(){
        tv.setId(1);
        tv.setEstimatedDuration(1);
        tv.setNumKmTravelled(100); //num km travelled must be < estimated duration
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputCity1(){
        tv.setId(1);
        tv.setCity(-1); //city must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputCity2(){
        tv.setId(1);
        tv.setCity(0); //city must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputActive(){
        tv.setId(1);
        tv.setActive(false); //active must be true on update operation,
                            // to deactivate the entity, it's necessary to use the drop operation
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputType(){
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());

        tv.setId(idV);
        tv.setType("bicycle"); //change the type of the vehicle it's not allowed
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }


    @Test
    public void updateVehicleNotExists(){
        tv.setId(1);
        assertThrows(ASException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleCurrentlyOccupied(){
        Integer idC = asCity.create(tCity);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        tv.setId(idV);

        tv.setOccupied(true);
        as.update(tv);

        tv.setNumKmTravelled(1);
        assertThrows(ASException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleCityNotExists(){
        Integer idC = asCity.create(tCity);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        tv.setId(idV);

        tv.setCity(2);
        assertThrows(ASException.class, () -> {as.update(tv);});
    }

    //private methods
    private boolean checkTransferValues(TVehicle tmp, TVehicle tv){
        return  tmp.getBrand().equals(tv.getBrand())
                && tmp.getEstimatedDuration().equals(tv.getEstimatedDuration())
                && tmp.getNumKmTravelled().equals(tv.getNumKmTravelled())
                && tmp.getCity().equals(tv.getCity())
                && tmp.getType().equals(tv.getType())
                && tmp.isActive().equals(tv.isActive());
    }
}