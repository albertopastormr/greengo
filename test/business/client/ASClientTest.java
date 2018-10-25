package business.client;

import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.rental.as.ASRental;
import business.rental.factory.ASRentalFactory;
import business.vehicle.TVehicle;
import business.vehicle.as.ASVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.client.dao.DAOClient;
import integration.client.factory.DAOClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    //Drop method
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

        ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
        TVehicle tv = new TVehicle(null,"Audi",6000,0,
                false,1,false);
        Integer idV = asV.create(tv);

        ASRental asR = ASRentalFactory.getInstance().generateASRental();
        TRental tr = new TRental(null,idV,false,10,idC,dFrom,dTo);
        asR.create(tr);

        as.drop(idC);
    }


    //Show method

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

    //showAll method
    @Test
    public void showAllClientsSuccessful() {
        TClient tc1 = new TClient(null, "00000000X", 0, false);
        Integer idC1 = as.create(tc1);
        TClient tc2 = new TClient(null, "11111111X", 0, false);
        as.create(tc2);

        Collection<TClient> collec = as.showAll();
        for (TClient tmp : collec) {
            if (tmp.getId().equals(idC1))
                assertTrue(checkTransferValues(tmp, tc1.getId_card_number(), tc1.getRentals_number()));
           else
                assertTrue(checkTransferValues(tmp, tc2.getId_card_number(), tc2.getRentals_number()));
        }
    }

    @Test
    public void showAllClientsSuccessful2(){
        Collection<TClient> c = as.showAll();
        assertTrue(c.isEmpty());
    }
    private boolean checkTransferValues(TClient tc,String id_card_number,Integer rentals_number){
        return  tc.getId_card_number().equals(id_card_number) &&
                tc.getRentals_number().equals(rentals_number) && tc.isActive();
    }
    //showAllWithMoreNrentals method

    @Test
    public void showAllClientsWithMoreNrentalsSuccessful(){
        final Integer N = 2;
        TClient tc1 = new TClient(null, "00000000X", 3, false);
        as.create(tc1);
        TClient tc2 = new TClient(null, "11111111X", 2, false);
        Integer idC2 = as.create(tc2);
        TClient tc3 = new TClient(null, "11121111Y", 0, false);
        Integer idC3 = as.create(tc3);

        Collection<TClient> collec = as.showAllWithMoreThanNRentals(N);
        for(TClient tmp : collec){
            assertTrue(tmp.getRentals_number() > N);
            assertNotEquals(tmp.getId(),idC2);
            assertNotEquals(tmp.getId(),idC3);
        }
    }
    //Update method
    @Test
    public void updateClientSuccessful(){
        TClient oldClient = new TClient(null,"00000000X",0,false);
        Integer idC = as.create(oldClient);

        TClient updtClient = new TClient(idC,"11111111X",1,true);
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
        TClient updtClient = new TClient(1,"11111111X",0,false);
        as.update(updtClient);
    }

    @Test (expected = IncorrectInputException.class)
    public void updateClientNotExists(){
        //active must be true
        TClient updtClient = new TClient(1,"11111111X",0,true);
        as.update(updtClient);
    }
}
