package integration.city.dao;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.client.TClient;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import integration.DAOException;
import integration.city.factory.DAOCityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DAOCityTest {

    private static DAOCity dao = DAOCityFactory.getInstance().generateDAOCity();
    private static TCity tc1 = new TCity(null,"Madrid",false);
    private static TCity tc2 = new TCity(null,"Barcelona",false);
    private static ASClient asClient = ASClientFactory.getInstance().generateASClient();
    private static TClient tclient = new TClient(null,"00000000X",0,false);

    @BeforeEach
    private void setUp() throws Exception {
        dao.deleteAll();
    }

    private boolean checkValues(TCity expected,TCity actual){
        return expected.getId().equals(actual.getId())
                && expected.getName().equals(actual.getName())
                && expected.isActive().equals(actual.isActive());
    }

    //create method
    @Test
    void createCity() throws DAOException {
        Integer idM = dao.create(tc1);
        Integer idB = dao.create(tc2);

        assertEquals((Integer)1,idM);
        assertEquals((Integer)2,idB);
    }



    //Update method
    @Test
    void updateCitySuccessfulName() throws DAOException {
        Integer idM = dao.create(tc1);
        tc1.setId(idM);
        tc1.setName("Valencia");

        Integer id = dao.update(tc1);
        assertEquals(idM,id);

        TCity tInDB = dao.readById(id);
        assertEquals(tc1.getName(),tInDB.getName());
        assertEquals(tc1.isActive(),tInDB.isActive());
    }

    @Test
    void updateCitySuccessfulActive() throws DAOException {
        Integer idM = dao.create(tc1);
        tc1.setId(idM);
        tc1.setActive(false);

        Integer id = dao.update(tc1);
        assertEquals(idM,id);

        TCity tInDB = dao.readById(id);
        assertEquals(tc1.getName(),tInDB.getName());
        assertEquals(tc1.isActive(),tInDB.isActive());
    }

    @Test
    void updateCityNotExists() throws DAOException {
        Integer idM = dao.create(tc1);
        tc1.setId(2);
        tc1.setName("Valencia");

        assertThrows(DAOException.class, () -> {dao.update(tc1);});
    }

    //ReadByID method
    @Test
    void readByIdSuccessful() throws DAOException {
        Integer idM = dao.create(tc1);

        TCity tInDB = dao.readById(idM);
        assertTrue(checkValues(tc1,tInDB));
    }

    @Test
    void readAllSuccessful1() throws DAOException {
        Integer idM = dao.create(tc1);
        Integer idB = dao.create(tc2);

        Collection<TCity> cities = dao.readAll();

        for(TCity c : cities){
            if(tc1.getId().equals(c.getId()))
               assertTrue(checkValues(tc1,c));
            else
                assertTrue(checkValues(tc2,c));
        }
    }

    @Test
    void readAllSuccessful2() throws DAOException {
        Collection<TCity> cities = dao.readAll();
        assertTrue(cities.isEmpty());
    }


    //Show clients by city method
    @Test
    void showClientsByCity1() throws ASException, DAOException, IncorrectInputException {
        Integer idM = dao.create(tc1);
        Integer idClient = asClient.create(tclient);

        Collection<TClient> clients = dao.showClientsByCity(idM);
        for(TClient c : clients) {
            assertEquals(idClient, c.getId());
            assertEquals(tclient.getIdCardNumber(), c.getIdCardNumber());
            assertEquals(tclient.getNumRentals(), c.getNumRentals());
            assertEquals(tclient.isActive(), c.isActive());
        }
    }

    @Test
    void showClientsByCity2() throws DAOException {
        Integer idM = dao.create(tc1);

        Collection<TClient> clients = dao.showClientsByCity(idM);
        assertTrue(clients.isEmpty());
    }




}
