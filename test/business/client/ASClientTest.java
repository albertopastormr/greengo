package business.client;

import business.client.as.ASClient;
import business.client.as.imp.ASClientImp;
import business.client.factory.ASClientFactory;
import integration.client.dao.DAOClient;
import integration.client.factory.DAOClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ASClientTest {
    private static ASClient as;

    @BeforeEach
    private void setUp(){
        DAOClient dao = DAOClientFactory.getInstance().generateDAOClient();
        dao.deleteAll();

        as = ASClientFactory.getInstance().generateASClient();
    }

    //Create method
    @Test
    public void createClientSuccessful(){
        TClient tc = new TClient(null,"00000000X",0,false);
        Integer id = as.create(tc);
        tc = as.show(id);
        assertTrue(tc.getId() > 0);
        assertTrue(id > 0);
        assertEquals(tc.getId(),id);
    }

    @Test (expected = IncorrectInputException.class)
    public void createClientIncorrectInput0(){
        TClient tc = new TClient(0,"00000000X",-1,false); //id must be null
                                                                                                // as input
        as.create(tc);
    }

    @Test (expected = IncorrectInputException.class)
    public void createClientIncorrectInput1(){
        TClient tc = new TClient(null,"000",0,false); //id_card_number must have
                                                                        // 9 digits, including a letter at the end.
        as.create(tc);
    }

    @Test (expected = IncorrectInputException.class)
    public void createClientIncorrectInput2(){
        TClient tc = new TClient(null,"00000000X",-1,false); //rentals_number > 0

        as.create(tc);
    }

    @Test (expected = IncorrectInputException.class)
    public void createClientIncorrectInput3(){
        TClient tc = new TClient(null,"00000000X",-1,true); //active must be true
                                                                                                  //as input
        as.create(tc);
    }
    //TODO Drop method
    //TODO Show method
    //TODO ShowAll method
    //TODO ShowAllNrentals method
    //TODO Update method

}
