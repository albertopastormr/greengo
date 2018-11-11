package business.client;


import business.ASException;
import business.IncorrectInputException;
import business.client.as.ASClient;
import business.client.factory.ASClientFactory;
import business.rental.TRental;
import business.rental.TRentalDetails;
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

import static org.junit.jupiter.api.Assertions.*;

public class ASClientTest {
    private static Date dFrom = new Date(1540373530000L);
    private static Date dTo = new Date(1543051930000L);
    private static ASClient as = ASClientFactory.getInstance().generateASClient();
    private static TClient tc = new TClient(null,"00000000X",0,false);
    private static ASVehicle asV = ASVehicleFactory.getInstance().generateASVehicle();
    private static TVehicle tv = new TVehicle(null,"Audi",6000,0,
            false,null,false,"car");
    private static ASRental asR = ASRentalFactory.getInstance().generateASRental();
    private static TRental tr = new TRental(null,null,false,10,null,dFrom,dTo);

   @BeforeEach
    private void setUp() throws Exception{
        DAOClient dao = DAOClientFactory.getInstance().generateDAOClient();
        dao.deleteAll();
    }

    //Create method
    @Test
    public void createClientSuccessful() throws ASException, IncorrectInputException {
       assertTrue(as.create(tc)>0);
    }



    @Test
    public void createClientIncorrectInput2(){
        tc.setNumRentals(-1); //rentals_number must be >= 0
        assertThrows(IncorrectInputException.class, () -> {as.create(tc);});
    }

    @Test
    public void createClientIncorrectInput3(){
        tc.setIdCardNumber(null); //idCardNumber can`t be empty
        assertThrows(IncorrectInputException.class, () -> {as.create(tc);});
    }

    //Drop method
    @Test
    public void dropSuccessful() throws ASException, IncorrectInputException {
        Integer idV = asV.create(tv);
        Integer id = as.create(tc);

        tr.setIdClient(id);
        tr.setIdVehicle(idV);
        Integer idR = asR.create(tr);

        as.drop(id);
        Collection<TRentalDetails> rentals = asR.showAll();
        for(TRentalDetails r : rentals) {
            if (r.getClient().getId().equals(id)) {
                assertTrue(r.getVehicle().isActive());
                assertFalse(r.getVehicle().isOccupied());
                assertFalse(r.getRental().isActive());
            }
        }
    }

    @Test
    public void dropClientIncorrectInput1(){
        assertThrows(IncorrectInputException.class, () -> {as.drop(null);} ); //id must be > 0
    }

    @Test
    public void dropClientIncorrectInput2(){
        assertThrows(IncorrectInputException.class, () -> {as.drop(0);} ); //id must be > 0
    }

    @Test
    public void dropClientIncorrectInput3(){
        assertThrows(IncorrectInputException.class, () -> {as.drop(-1);} ); //id must be > 0
    }

    @Test
    public void dropClientNotExists(){
        assertThrows(ASException.class, () -> {as.drop(50);});
    }

    @Test
    public void dropClientWithActiveRentals() throws ASException, IncorrectInputException {
        Integer idC = as.create(tc);
        Integer idV = asV.create(tv);

        tr.setIdClient(idC);
        tr.setIdVehicle(idV);
        asR.create(tr);

        assertThrows(ASException.class,()->{as.drop(idC);});
    }


    //Show method
    @Test
    public void showClientSuccessful() throws ASException, IncorrectInputException {
        Integer idC = as.create(tc);

        TClient out = as.show(idC);

        assertEquals(out.getId(),idC);
        assertEquals(out.getIdCardNumber(),tc.getIdCardNumber());
        assertEquals(out.getNumRentals(),tc.getNumRentals());
        assertEquals(out.isActive(),tc.isActive());
    }

    @Test
    public void showClientIncorrectInputID1(){
        assertThrows(IncorrectInputException.class, () -> {as.show(null);}); //id must be > 0
    }

    @Test
    public void showClientIncorrectInputID2(){
        assertThrows(IncorrectInputException.class, () -> {as.show(0); }); //id must be > 0
    }

    @Test
    public void showClientIncorrectInputID3(){
        assertThrows(IncorrectInputException.class, () -> {as.show(-1); }); //id must be > 0
    }

    @Test
    public void showClientNotExists(){
        assertThrows(ASException.class, () -> {as.show(1);});
    }

    //showAll method
    @Test
    public void showAllClientsSuccessful() throws ASException, IncorrectInputException {
        Integer idC = as.create(tc);

        TClient tc2 = new TClient(null, "11111111X", 0, false);
        as.create(tc2);

        Collection<TClient> collec = as.showAll();
        for (TClient tmp : collec) {
            if (tmp.getId().equals(idC))
                assertTrue(checkTransferValues(tmp, tc.getIdCardNumber(), tc.getNumRentals()));
           else
                assertTrue(checkTransferValues(tmp, tc2.getIdCardNumber(), tc2.getNumRentals()));
        }
    }

    @Test
    public void showAllClientsSuccessful2() throws ASException {
        Collection<TClient> c = as.showAll();
        assertTrue(c.isEmpty());
    }
    private boolean checkTransferValues(TClient tc,String id_card_number,Integer rentals_number){
        return  tc.getIdCardNumber().equals(id_card_number) &&
                tc.getNumRentals().equals(rentals_number) && tc.isActive();
    }

    //showAllWithMoreNrentals method
    @Test
    public void showAllClientsWithMoreNrentalsSuccessful() throws ASException, IncorrectInputException {
        final Integer N = 2;
        tc.setNumRentals(3);
        as.create(tc);

        TClient tc2 = new TClient(null, "11111111X", 2, false);
        Integer idC2 = as.create(tc2);

        TClient tc3 = new TClient(null, "11121111Y", 0, false);
        Integer idC3 = as.create(tc3);

        Collection<TClient> collec = as.showAllWithMoreThanNRentals(N);
        for(TClient tmp : collec){
            assertTrue(tmp.getNumRentals() > N);
            assertNotEquals(tmp.getId(),idC2);
            assertNotEquals(tmp.getId(),idC3);
        }
    }

    //Update method
    @Test
    public void updateClientSuccessful() throws ASException, IncorrectInputException {
        Integer idC = as.create(tc);

        TClient updtClient = new TClient(idC,"11111111X",1,true);
        Integer idOut = as.update(updtClient);

        TClient out = as.show(idOut);

        assertEquals(out.getId(),idC);
        assertEquals(out.getIdCardNumber(),updtClient.getIdCardNumber());
        assertEquals(out.getNumRentals(),updtClient.getNumRentals());
        assertEquals(out.isActive(),updtClient.isActive());
    }

    @Test
    public void updateClientIncorrectInputID1(){
        //id can't be null and must be > 0
        tc.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.update(tc);});
    }

    @Test
    public void updateClientIncorrectInputID2(){
        //id can't be null and must be > 0
        tc.setId(0);
        assertThrows(IncorrectInputException.class, () -> {as.update(tc);});
    }

    @Test
    public void updateClientIncorrectInputID3(){
        //id can't be null and must be > 0
        tc.setId(-1);
        assertThrows(IncorrectInputException.class, () -> {as.update(tc);});
    }


    @Test
    public void updateClientIncorrectInputRentals_number(){
        //rentals number must be >= 0
        tc.setNumRentals(-1);
        assertThrows(IncorrectInputException.class, () -> {as.update(tc);});
    }

    @Test
    public void updateClientIncorrectInputActive(){
        //active must be true on the update operation, to deactivate the entity,
        //it's necessary to use the drop operation
        tc.setActive(false);
        assertThrows(IncorrectInputException.class, () -> {as.update(tc);});
    }

    @Test
    public void updateClientNotExists(){
        tc.setId(1);
        assertThrows(ASException.class, () -> {as.update(tc);});
    }

    @Test
    public void showAllWithMoreThanNRentalsIncorrectInput(){
       //N must be >=0
        int N = -1;
        assertThrows(ASException.class, () -> {as.showAllWithMoreThanNRentals(N);});
    }
}
