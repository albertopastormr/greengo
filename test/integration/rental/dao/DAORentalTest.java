package integration.rental.dao;

import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.DAOException;
import integration.rental.factory.DAORentalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
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
    void setUp() throws DAOException {
        dao.deleteAll();
    }

    //create method
    @Test
    void createRental() throws DAOException {
        Integer id1 = dao.create(tr);
        tr.setIdClient(2);
        tr.setIdVehicle(2);
        Integer id2 = dao.create(tr);

        assertEquals((Integer)1,id1);
        assertEquals((Integer)2,id2);
    }


    //update method
    @Test
    void update() throws DAOException {
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
    void readById() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);
        TRental read = dao.readById(id);

        assertEquals(id,tr.getId());
        assertEquals(tr.getIdVehicle(),read.getIdVehicle());
        assertEquals(tr.isActive(),read.isActive());
        assertEquals(tr.getNumKmRented(),read.getNumKmRented());
        assertEquals(tr.getIdClient(),read.getIdClient());
        assertEquals(tr.getDateFrom(),read.getDateTo());
    }

    @Test
    void showRentalsByClient() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);

        Collection<TRental> read = dao.showRentalsByClient(1);

        for(TRental r : read){
            assertEquals(tr.getId(),r.getId());
            assertEquals(tr.getIdVehicle(),r.getIdVehicle());
            assertEquals(tr.isActive(),r.isActive());
            assertEquals(tr.getNumKmRented(),r.getNumKmRented());
            assertEquals(tr.getIdClient(),r.getIdClient());
            assertEquals(tr.getDateFrom(),r.getDateTo());
        }
    }

    @Test
    void readAll() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);

        Collection<TRental> read = dao.readAll();

        for(TRental r : read){
            assertEquals(tr.getId(),r.getId());
            assertEquals(tr.getIdVehicle(),r.getIdVehicle());
            assertEquals(tr.isActive(),r.isActive());
            assertEquals(tr.getNumKmRented(),r.getNumKmRented());
            assertEquals(tr.getIdClient(),r.getIdClient());
            assertEquals(tr.getDateFrom(),r.getDateTo());
        }
    }
}