package integration.vehicle.dao;

import business.city.TCity;
import business.vehicle.TVehicle;
import integration.DAOException;
import integration.city.dao.DAOCity;
import integration.city.factory.DAOCityFactory;
import integration.vehicle.factory.DAOVehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DAOVehicleTest {


    private static DAOVehicle dao = DAOVehicleFactory.getInstance().generateDAOVehicle();
    private static DAOCity daoCity = DAOCityFactory.getInstance().generateDAOCity();
    private static TVehicle tv1 = new TVehicle(1,"bmw",0,0,false,1,true,"bicycle");
    private static TVehicle tv2 = new TVehicle(2,"audi",0,0,false,1,true,"car");
    private static TCity tc1 = new TCity(1,"Madrid",false);

    @BeforeEach
    private void setUp() throws Exception {
        dao.deleteAll();
        daoCity.create(tc1);
    }

    private boolean checkValues(TVehicle expected,TVehicle actual) {
        return expected.getId().equals(actual.getId())
                && expected.getBrand().equals(actual.getBrand())
                && expected.getEstimatedDuration().equals(actual.getEstimatedDuration())
                && expected.getNumKmTravelled().equals(actual.getNumKmTravelled())
                && expected.isOccupied().equals(actual.isOccupied())
                && expected.getCity().equals(actual.getCity())
                && expected.isActive().equals(actual.isActive())
                && expected.getType().equals(actual.getType());
    }

    @Test
    void create() throws DAOException{
        Integer idM = dao.create(tv1);
        Integer idB = dao.create(tv2);

        assertEquals((Integer)1,idM);
        assertEquals((Integer)2,idB);
    }

    @Test
    void update() throws DAOException{
        Integer idM = dao.create(tv1);
        tv1.setId(idM);
        tv1.setBrand("seat");
        tv1.setEstimatedDuration(tv1.getEstimatedDuration()+1);
        tv1.setNumKmTravelled(tv1.getNumKmTravelled()+1);
        tv1.setOccupied(!tv1.isOccupied());

        Integer id = dao.update(tv1);
        assertEquals(idM,id);

        TVehicle tInDB = dao.readById(id);
        assertTrue(checkValues(tv1, tInDB));
    }



    @Test
    void readById() throws DAOException{
        Integer idM = dao.create(tv1);

        TVehicle tInDB = dao.readById(idM);
        assertTrue(checkValues(tv1,tInDB));
    }

    @Test
    void readAll() throws DAOException{
        Integer idM = dao.create(tv1);
        Integer idB = dao.create(tv2);

        Collection<TVehicle> clients = dao.readAll();

        for(TVehicle c : clients){
            if(tv1.getId().equals(c.getId()))
                assertTrue(checkValues(tv1,c));
            else
                assertTrue(checkValues(tv2,c));
        }

        dao.deleteAll();

        clients = dao.readAll();
        assertTrue(clients.isEmpty());
    }



    @Test
    void showAllActiveVehicles() throws Exception {
        tv1.setOccupied(false);
        tv2.setOccupied(false);

        Integer idM = dao.create(tv1);
        Integer idB = dao.create(tv2);

        Collection<TVehicle> clients = dao.readAllAvailableVehicles();

        for(TVehicle c : clients){
            if(tv1.getId().equals(c.getId()))
                assertTrue(checkValues(tv1,c));
            else
                assertTrue(checkValues(tv2,c));
        }

        setUp();

        tv1.setOccupied(true);
        tv2.setOccupied(true);

        Integer idM2 = dao.create(tv1);
        Integer idB2 = dao.create(tv2);

        clients = dao.readAllAvailableVehicles();
        assertTrue(clients.isEmpty());
    }
}