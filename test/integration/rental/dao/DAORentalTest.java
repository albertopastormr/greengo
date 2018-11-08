package integration.rental.dao;

import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.rental.factory.DAORentalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DAORentalTest {

    private static DAORental dao = DAORentalFactory.getInstance().generateDAORental();
    private static Date dFrom = new Date(1540373530000L);
    private static Date dTo = new Date(1543051930000L);
    private static ASClient asC = ASClientFactory.getInstance().generateASClient();
    private static TClient tc = new TClient(null,"00000000X",0,false);
    private static ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0,
            false,null,false,"car");
    private static TRental tr = new TRental(null,1,false,10,1,dFrom,dTo);


    @BeforeEach
    void setUp() {
        dao.deleteAll();
    }

    //create method
    @Test
    void createRental() {
        Integer id1 = dao.create(tr);
        tr.setIdClient(2);
        tr.setIdVehicle(2);
        Integer id2 = dao.create(tr);

        assertEquals((Integer)1,id1);
        assertEquals((Integer)2,id2);
    }


    //update method
    @Test
    void update() {
        Integer id = dao.create(tr);
        tr.setId(id);

        TRental updt = new TRental(id,2,false,10,2,dFrom,dTo);
        Integer idUpdt = dao.update(updt);
        tr = dao.readById(idUpdt);

        assertEquals(id,tr.getId());
        assertEquals(updt.getIdVehicle(),tr.getIdVehicle());
        assertEquals(updt.isActive(),tr.isActive());
        assertEquals(updt.getNumKmRented(),tr.getNumKmRented());
        assertEquals(updt.getIdClient(),tr.getIdClient());
        assertEquals(updt.getDateFrom(),tr.getDateTo());
    }

    @Test
    void readById() {
    }

    @Test
    void showRentalsByClient() {
    }

    @Test
    void readAll() {
    }
}