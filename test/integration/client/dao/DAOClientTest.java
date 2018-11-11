package integration.client.dao;

import business.client.TClient;
import integration.DAOException;
import integration.client.factory.DAOClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DAOClientTest {

    private static DAOClient dao = DAOClientFactory.getInstance().generateDAOClient();
    private static TClient tc1 = new TClient(1,"idcard1",0,false);
    private static TClient tc2 = new TClient(2,"idcard2",0,false);

    @BeforeEach
    private void setUp() throws Exception {
        dao.deleteAll();
    }

    private boolean checkValues(TClient expected,TClient actual){
        return expected.getId().equals(actual.getId())
                && expected.getIdCardNumber().equals(actual.getIdCardNumber())
                && expected.getNumRentals().equals(actual.getNumRentals())
                && expected.isActive().equals(actual.isActive());
    }

    @Test
    void create() throws DAOException {
        Integer idM = dao.create(tc1);
        Integer idB = dao.create(tc2);

        assertEquals((Integer)1,idM);
        assertEquals((Integer)2,idB);
    }

    @Test
    void update() throws DAOException {
        Integer idM = dao.create(tc1);
        tc1.setId(idM);
        tc1.setIdCardNumber("idc");
        tc1.setNumRentals(tc1.getNumRentals()+1);

        Integer id = dao.update(tc1);
        assertEquals(idM,id);

        TClient tInDB = dao.readById(id);
        assertTrue(checkValues(tc1, tInDB));
    }

    @Test
    void readByIdCard() throws DAOException {
        Integer idM = dao.create(tc1);

        TClient tInDB = dao.readByIdCard(tc1.getIdCardNumber());
        assertTrue(checkValues(tc1,tInDB));
    }


    @Test
    void readById() throws DAOException {
        Integer idM = dao.create(tc1);

        TClient tInDB = dao.readById(idM);
        assertTrue(checkValues(tc1,tInDB));
    }

    @Test
    void readAll() throws DAOException {
        Integer idM = dao.create(tc1);
        Integer idB = dao.create(tc2);

        Collection<TClient> clients = dao.readAll();

        for(TClient c : clients){
            if(tc1.getId().equals(c.getId()))
                assertTrue(checkValues(tc1,c));
            else
                assertTrue(checkValues(tc2,c));
        }

        dao.deleteAll();

        clients = dao.readAll();
        assertTrue(clients.isEmpty());
    }


    @Test
    void readByNRentals() throws DAOException {
        int Nrentals = 50;
        tc1.setNumRentals(Nrentals+1);
        tc2.setNumRentals(Nrentals+1);

        Integer idM = dao.create(tc1);
        Integer idB = dao.create(tc2);

        Collection<TClient> clients = dao.readByNRentals(Nrentals);

        for(TClient c : clients){
            if(tc1.getId().equals(c.getId()))
                assertTrue(checkValues(tc1,c));
            else
                assertTrue(checkValues(tc2,c));
        }

        dao.deleteAll();

        tc1.setNumRentals(Nrentals - 1);
        tc2.setNumRentals(Nrentals - 1);

        Integer idM2 = dao.create(tc1);
        Integer idB2 = dao.create(tc2);

        clients = dao.readByNRentals(Nrentals);
        assertTrue(clients.isEmpty());
    }
}