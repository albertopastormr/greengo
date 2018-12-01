package business.contract;

import business.ASException;
import business.IncorrectInputException;
import business.contract.as.ASContract;
import business.contract.as.imp.ASContractImp;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import business.service.TService;
import business.service.as.ASService;
import business.service.as.imp.ASServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ASContractTest {

    private static ASContract asContract = new ASContractImp();
    private static ASMain_Office asMainOffice = new ASMain_OfficeImp();
    private static ASService asService = new ASServiceImp();

    private static TMainOffice tMainOffice;
    private static TService tService;
    private static TContract tContract;
    private static TContract tContract2;

    @BeforeEach
    @Test
    void setUp() throws ASException, IncorrectInputException {

        tMainOffice = new TMainOffice(1,"manu","C/algomas",true);
        tService = new TService(1,1,true,"tipo planta", "~", 1);
        tContract = new TContract(1,1,1,1,true);
        tContract2 = new TContract(2,4,1,1,true);

        asMainOffice.create(tMainOffice);
        asService.create(tService);
    }

    @Test
    boolean checkValuesTContract(TContract expected, TContract actual){
        return expected.getId().equals(actual.getId()) && expected.getServiceLevel().equals(actual.getServiceLevel())
                && expected.getIdService().equals(actual.getIdService())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.isActive().equals(actual.isActive());
    }


    // ----------- CREATE ---------------
    @Test
    void createOK(){
        assertTrue( asContract.create(tContract) > 0);
    }

    @Test
    void createIncorrectInputID(){
        tContract.setId(1); //id must be null at create method
        assertThrows(IncorrectInputException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectInputServiceLevel(){
        tContract.setServiceLevel(-1);
        assertThrows(IncorrectInputException.class, () -> asContract.create(tContract));
        tContract.setServiceLevel(0);
        assertThrows(IncorrectInputException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectInputMainOffice(){
        tContract.setIdMainOffice(null);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
        tContract.setIdMainOffice(-1);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
        tContract.setIdMainOffice(0);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
    }

    @Test
    void createIncorrectInputIdService(){
        tContract.setIdService(null);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
        tContract.setIdService(-1);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
        tContract.setIdService(0);
        assertThrows(IncorrectInputException.class, ()-> asContract.create(tContract));
    }

    @Test
    void createIncorrectMainOfficeId(){
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectMainOfficeActive(){
        asMainOffice.drop(tContract.getIdMainOffice());
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectServiceId(){
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectServiceActive() throws ASException, IncorrectInputException {
        asService.drop(tContract.getIdService());
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectMainOfficeServiceId(){
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    @Test
    void createIncorrectMainOfficeServiceActive() throws ASException, IncorrectInputException {
        asMainOffice.drop(tContract.getIdMainOffice());
        asService.drop(tContract.getIdService());
        assertThrows(ASException.class, () -> asContract.create(tContract));
    }

    // ------------------- DROP -----------------

    @Test
    void dropOK(){
        asContract.create(tContract);
        assertTrue( asContract.drop(tContract.getId()) > 0);
    }

    @Test
    void dropIncorrectId(){
        assertThrows(ASException.class, () -> asContract.drop(tContract.getId() + 100));
    }

    @Test
    void dropIncorrectActive(){
        asContract.create(tContract);
        asContract.drop(tContract.getId());
        assertThrows(ASException.class, () -> asContract.drop(tContract.getId()));
    }


    // ------------------- UPDATE ---------------

    @Test
    void updateOK(){
        asContract.create(tContract);
        assertTrue( asContract.update(tContract) > 0);
    }

    @Test
    void updateIncorrectId(){
        asContract.create(tContract);
        tContract.setId(tContract.getId() + 100);
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeId(){
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeActive(){
        asContract.create(tContract);
        asMainOffice.drop(tContract.getIdMainOffice());
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectServiceId(){
        asContract.create(tContract);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectServiceActive() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        asService.drop(tContract.getIdService());
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeServiceId(){
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeServiceActive() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        asMainOffice.drop(tContract.getIdMainOffice());
        asService.drop(tContract.getIdService());
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }


    // ------------------ SHOW -----------------

    @Test
    void showOK(){
        asContract.create(tContract);
        assertTrue(checkValuesTContract(tContract, asContract.show(tContract.getId())));
    }

    @Test
    void showIncorrectId(){
        assertThrows(ASException.class, () -> asContract.show(tContract.getId() + 100));
    }


    // ------------------- SHOW ALL -----------------

    @Test
    void showAllOK(){
        asContract.create(tContract);
        asContract.create(tContract2);
        ArrayList<TContract> result = (ArrayList<TContract>) asContract.showAll();
        assertTrue(checkValuesTContract(tContract, result.get(0)));
        assertTrue(checkValuesTContract(tContract2, result.get(1)));
    }

    @Test
    void showAllIncorrectActive(){
        asContract.create(tContract);
        tContract2.setActive(false);
        asContract.create(tContract2);
        asContract.drop(tContract2.getId());
        ArrayList<TContract> result = (ArrayList<TContract>) asContract.showAll();
        assertEquals(1, result.size());
        assertTrue(checkValuesTContract(tContract, result.get(0)));
    }

}