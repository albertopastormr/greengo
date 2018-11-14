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
import integration.DAOException;
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
            false,null,true,"Car");
    private static TCarVehicle tcar = new TCarVehicle(null,"Tesla",6000,0,
            false,null,true,"YYYYY");
    private static TBicycleVehicle tb = new TBicycleVehicle(null,"Tesla",6000,0,
            false,null,true,12345);
    private static ASCity asCity = ASCityFactory.getInstance().generateASCity();
    private static TCity tCity = new TCity(null,"Madrid",true);
    private static ASClient asClient = ASClientFactory.getInstance().generateASClient();
    private static TClient tClient = new TClient(null,"00000000X",0,false);
    private static ASRental asR = ASRentalFactory.getInstance().generateASRental();
    private static TRental tr = new TRental(null,null,false,10,null,dFrom,dTo);

    @BeforeEach
    public void setUp() throws DAOException {
        DAOVehicle dao = DAOVehicleFactory.getInstance().generateDAOVehicle();
        dao.deleteAll();
    }

    //create method
    @Test
    public void createVehicleSuccessful() throws ASException, IncorrectInputException {
        Integer idC = asCity.create(tCity);
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYYY");
        // City exist and active
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());


        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(as.show(idV).getVehicle().isActive());
        assertTrue(idV > 0);
    }

    @Test
    public void createVehicleIncorrectInputID(){
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYYY");
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
    public void createVehicleIncorrectInputCity(){
        tv.setCity(null); //city can't be null
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleIncorrectInputType(){
        tv.setType("bike"); //type only can be car or bicycle on this version
        assertThrows(IncorrectInputException.class, () -> {as.create(tv);});
    }

    @Test
    public void createVehicleCityNotExists() throws ASException, IncorrectInputException {
        TVehicle tv2 = new TVehicle(null, "Audi", 1000, 300,
                false, 100, false, "Car");
        assertThrows(ASException.class, () -> {as.create(tv2);});
    }

    //Drop method
    @Test
    public void dropSuccessful() throws ASException, IncorrectInputException {
        tv= new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYY");
        tCity = new TCity(null,"Madrid",true);

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer id = as.create(tv);
        // Compruebas si existen y estan activos
        assertTrue(id > 0);
        assertTrue(as.show(id).getVehicle().isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setId(id);
        tv.setCity(idC);

        as.drop(id);
        assertTrue(!as.show(id).getVehicle().isActive());
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
    public void dropVehicleAlreadyInactive() throws ASException, IncorrectInputException {
        tv= new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");
        TCity tCity1 = new TCity(null,"Madrid",true);
        Integer idC = asCity.create(tCity1);
        tv.setCity(idC);
        Integer id = as.create(tv);

        // Compruebas si existen y estan activos
        assertTrue(id > 0);
        assertTrue(as.show(id).getVehicle().isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setId(id);
        tv.setCity(idC);
        as.drop(id);

        assertThrows(ASException.class, () -> {as.drop(id);});
    }

    @Test
    public void dropVehicleWithActiveRentals() throws ASException, IncorrectInputException {
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYY");
        tCity = new TCity(null,"Madrid",true);
        TClient tClientAux = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idcity = asCity.create(tCity);
        tv.setCity(idcity);
        Integer idV = as.create(tv);

        Integer idC = asClient.create(tClientAux);

        // Compruebas si existen y estan activos
        assertTrue(idV > 0);
        assertTrue(as.show(idV).getVehicle().isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tr.setIdClient(idC);
        tr.setIdVehicle(idV);

        Integer idR = asR.create(tr);
        assertTrue(idR > 0);
        assertTrue(asR.show(idR).getRental().isActive());

        assertThrows(ASException.class,()->{as.drop(idV);});
    }

    //Show method
    @Test
    public void showVehicleSuccessful() throws ASException, IncorrectInputException {
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).getVehicle().isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        tv.setId(idV);
        TVehicle out = as.show(idV).getVehicle();

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
    public void showVehicleNotExists() throws ASException, IncorrectInputException {
        assertThrows(ASException.class, () -> {as.show(50);});
    }

    //showAll method
    @Test
    public void showAllVehicleSuccessful() throws ASException, IncorrectInputException {
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");
        tCity = new TCity(null,"Madrid",true);
        tClient = new TClient(null,"00000000X",0,true);
        tr = new TRental(null,null,true,10,null,dFrom,dTo);

        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).getVehicle().isActive());

        TVehicle tv2 = new TCarVehicle(null, "Audi", 1000, 300,
                false, 1, true, "YYYY");

        Integer idV2 = as.create(tv2);

        assertTrue(idV2 > 0);
        assertTrue(as.show(idV2).getVehicle().isActive());

        Collection<TVehicleDetails> collec = as.showAll();
        for (TVehicleDetails tmp : collec) {
            if (tmp.getVehicle().getId().equals(idV))
                assertTrue(checkTransferValues(tmp.getVehicle(),tv));
            else
                assertTrue(checkTransferValues(tmp.getVehicle(), tv2));
        }
    }

    @Test
    public void showAllVehicleSuccessful2() throws ASException {
        Collection<TVehicleDetails> c = as.showAll();
        assertTrue(c.isEmpty());
    }

    //Update method

    @Test
    public void updateVehicleSuccessful() throws ASException, IncorrectInputException {
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYY");
        tCity = new TCity(null,"Madrid",true);
        Integer idC = asCity.create(tCity);
        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).getVehicle().isActive());
        assertTrue(idC > 0);
        assertTrue(asCity.show(idC).isActive());

        tv.setCity(idC);
        TVehicle updatedVehicle = new TCarVehicle(idV,"Test",6000,0,
                false,idC,true,"TTTTT");
        Integer idOut = as.update(updatedVehicle);

        assertTrue(idOut > 0);
        assertTrue(asCity.show(idOut).isActive());

        TVehicle out = as.show(idOut).getVehicle();

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
    public void updateVehicleIncorrectInputNumKmTravelled3(){
        tv.setId(1);
        tv.setEstimatedDuration(1);
        tv.setNumKmTravelled(100); //num km travelled must be < estimated duration
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputCity1(){
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");
        tv.setId(1);
        tv.setCity(-1); //city must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputCity2(){
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");
        tv.setId(1);
        tv.setCity(0); //city must be > 0
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleIncorrectInputActive(){
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"YYYYY");
        tv.setId(1);
        tv.setActive(false); //active must be true on update operation,
                            // to deactivate the entity, it's necessary to use the drop operation
        assertThrows(IncorrectInputException.class, () -> {as.update(tv);});
    }

    @Test
    public void updateVehicleNotExists() throws ASException, IncorrectInputException {
        TCity tc2 = new TCity(null,"Madrid",true);
        TCarVehicle tcar = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXXX");
        tv = tcar;
        Integer idtCity = asCity.create(tc2);
        tv.setCity(idtCity);
        tv.setId(100);
        assertThrows(ASException.class, () -> {as.update(tv);});
    }


    @Test
    public void updateVehicleCityNotExists() throws ASException, IncorrectInputException {
        tv = new TCarVehicle(null,"Tesla",6000,0,
                false,null,true,"XXXX");
        TCity tc2 = new TCity(null,"Madrid",true);


        Integer idC = asCity.create(tc2);
        tv.setCity(idC);
        Integer idV = as.create(tv);

        assertTrue(idV > 0);
        assertTrue(as.show(idV).getVehicle().isActive());
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