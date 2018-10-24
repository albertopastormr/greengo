package business.client;

import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.vehicle.TVehicle;
import integration.client.dao.DAOClient;
import integration.client.factory.DAOClientFactory;
import integration.rental.dao.DAORental;
import integration.rental.factory.DAORentalFactory;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

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
        TClient tc = new TClient(null,"000",0,false); //id_card_number
        // must have 8 numbers and 1 letter at the end
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

    //Drop methods
    @Test (expected = ASException.class)
    public void dropSuccessful(){
        TClient tc = new TClient(null,"00000000X",0,false);
        Integer id = as.create(tc);

        as.drop(id);
        as.show(id);
    }

    @Test (expected = IncorrectInputException.class)
    public void dropClientIncorrectInput0(){
        as.drop(0); //id must be > 0
    }

    @Test (expected = IncorrectInputException.class)
    public void dropClientIncorrectInput1(){
        as.drop(-1); //id must be > 0
    }

    @Test (expected = ASException.class)
    public void dropClientNotExists(){
        as.drop(1);
    }

    @Test (expected = ASException.class)
    public void dropClientWithActiveRentals(){
        Date dFrom = new Date(1540373530000L);
        Date dTo = new Date(1543051930000L);
        TClient tc = new TClient(null,"00000000X",0,false);
        Integer idC = as.create(tc);

        DAOVehicle dao = DAOVehicleFactory.getInstance().generateDAOVehicle();
        TVehicle tv = new TVehicle(null,"Audi",false,false,
                0,1000);
        Integer idV = dao.create(tv);

        DAORental dr = DAORentalFactory.getInstance().generateDAORental();
        TRental tr = new TRental(null,idV,false,10,idC,dFrom,dTo);
        dr.create(tr);

        as.drop(idC);
    }


    //TODO Show method

    @Test
    public void showClientSuccessful(){
        TClient tc = new TClient(null,"00000000X",0,false);
        Integer idC = as.create(tc);

        TClient out = as.show(idC);

        assertEquals(out.getId(),idC);
        assertEquals(out.getId_card_number(),tc.getId_card_number());
        assertEquals(out.getRentals_number(),tc.getRentals_number());
        assertEquals(out.isActive(),tc.isActive());
    }

    @Test (expected = IncorrectInputException.class)
    public void showClientIncorrectInputID1(){
        as.show(0); //id must be > 0
    }

    @Test (expected = IncorrectInputException.class)
    public void showClientIncorrectInputID2(){
        as.show(-1); //id must be > 0
    }

    @Test (expected = ASException.class)
    public void showClientNotExists(){
        as.show(1);
    }

    //TODO ShowAll method
    //TODO ShowAllNrentals method
    //TODO Update method

    @Test
    public void updateClientSuccessful(){
        TClient oldClient = new TClient(null,"00000000X",0,false);
        Integer idC = as.create(oldClient);

        TClient updtClient = new TClient(idC,"11111111X",0,true);
        Integer idOut = as.update(updtClient);

        TClient out = as.show(idOut);

        assertEquals(out.getId(),updtClient.getId());
        assertEquals(out.getId_card_number(),updtClient.getId_card_number());
        assertEquals(out.getRentals_number(),updtClient.getRentals_number());
        assertEquals(out.isActive(),updtClient.isActive());
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientIncorrectInputID(){
        //id can't be null
        TClient updtClient = new TClient(null,"11111111",0,true);
        as.update(updtClient);
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientIncorrectInputID_card_number1(){
        //id_card_number must have 8 numbers and 1 letter at the end
        TClient updtClient = new TClient(1,"1111X",0,true);
        as.update(updtClient);
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientIncorrectInputID_card_number2(){
        //id_card_number must have 8 numbers and 1 letter at the end
        TClient updtClient = new TClient(1,"11111111",0,true);
        as.update(updtClient);
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientIncorrectInputRentals_number(){
        //rentals number must be >= 0
        TClient updtClient = new TClient(1,"11111111X",-1,true);
        as.update(updtClient);
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientIncorrectInputActive(){
        //active must be true
        TClient updtClient = new TClient(1,"11111111X",-1,true);
        as.update(updtClient);
    }

    //todo update sobre cliente no existente
}
