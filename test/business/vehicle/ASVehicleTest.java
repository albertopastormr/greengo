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
    public void dropVehicletNotExists(){
        assertThrows(ASException.class, () -> {as.drop(50);});
    }


    
}
