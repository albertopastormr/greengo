package integration.rental.dao;

import business.city.TCity;
import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.city.dao.DAOCity;
import integration.city.factory.DAOCityFactory;
import integration.client.dao.DAOClient;
import integration.client.factory.DAOClientFactory;
import integration.rental.factory.DAORentalFactory;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DAORentalTest {

    private static DAORental dao = DAORentalFactory.getInstance().generateDAORental();
    private static DAOVehicle daoVehicle = DAOVehicleFactory.getInstance().generateDAOVehicle();
    private static DAOClient daoClient = DAOClientFactory.getInstance().generateDAOClient();
    private static DAOCity daoCity = DAOCityFactory.getInstance().generateDAOCity();
    private static Date dFrom = new Date(117,10,4);
    private static Date dTo = new Date(117,11,4);
    private static ASClient asC = ASClientFactory.getInstance().generateASClient();
    private static TClient tc = new TClient(1,"00000000X",0,false);
    private static ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TCarVehicle(1,"Audi",6000,0,
            false,1,false,"0000 XXX");
    private static TCity tc1 = new TCity(1,"Madrid",false);
    private static TRental tr = new TRental(1,1,true,10,1,dFrom,dTo);


    @BeforeEach
    void setUp() throws DAOException {
        dao.deleteAll();
        daoCity.create(tc1);
        daoVehicle.create(tv);
        daoClient.create(tc);

        asC = ASClientFactory.getInstance().generateASClient();
        tc = new TClient(1,"00000000X",0,false);
        asV = ASVehicleFactory.getInstance().generateASVehicle();
        tv = new TCarVehicle(1,"Audi",6000,0,
                false,1,false,"0000 XXX");
        tc1 = new TCity(1,"Madrid",false);
        tr = new TRental(1,1,true,10,1,dFrom,dTo);

    }

    //create method
    @Test
    void createRental() throws DAOException {
        Integer id1 = dao.create(tr);

        assertEquals((Integer)1,id1);

        TRental read = dao.readById(id1);

        assertTrue(checkValues(tr, read));
    }


    //update method
    @Test
    void update() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);

        tr.setNumKmRented(tr.getNumKmRented() + 10);
        Integer idUpdt = dao.update(tr);
        TRental read = dao.readById(idUpdt);

        assertTrue(checkValues(tr, read));
    }

    @Test
    void readById() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);
        TRental read = dao.readById(id);

        assertTrue(checkValues(tr, read));
    }

    @Test
    void showRentalsByClient() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);

        Collection<TRental> read = dao.showRentalsByClient(1);

        assertTrue(checkValues(tr, (TRental) read.toArray()[0]));
    }

    @Test
    void readAll() throws DAOException {
        Integer id = dao.create(tr);
        tr.setId(id);

        Collection<TRental> read = dao.readAll();

        assertTrue(checkValues(tr, (TRental) read.toArray()[0]));
    }

    private boolean checkValues(TRental expected, TRental actual) {
        return expected.getId().equals(actual.getId())
                && expected.getIdClient().equals(actual.getIdClient())
                && expected.getIdVehicle().equals(actual.getIdVehicle())
                && expected.getDateTo().equals(actual.getDateTo())
                && expected.getDateFrom().equals(actual.getDateFrom())
                && expected.getNumKmRented().equals(actual.getNumKmRented());
    }
}