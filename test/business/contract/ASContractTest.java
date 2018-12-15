package business.contract;

import business.ASException;
import business.IncorrectInputException;
import business.contract.as.ASContract;
import business.contract.as.imp.ASContractImp;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMainOffice;
import business.mainoffice.as.imp.ASMainOfficeImp;
import business.service.TService;
import business.service.as.ASService;
import business.service.as.imp.ASServiceImp;
import integration.DAOException;
import integration.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ASContractTest {

    private static ASContract asContract = new ASContractImp();
    private static ASMainOffice asMainOffice = new ASMainOfficeImp();
    private static ASService asService = new ASServiceImp();

    private static TMainOffice tMainOffice;
    private static TService tService;
    private static TContract tContract;
    private static TContract tContract2;

    @BeforeEach
    @Test
    void setUp() throws ASException, IncorrectInputException {


        try {
            Util.deleteAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        tMainOffice = new TMainOffice(1,"manu","C/algomas",true);
        tService = new TService(1,1,true,"tipo planta", "~", 1);
        tContract = new TContract(1,1,1,true);
        tContract2 = new TContract(4,1,1,true);

        asMainOffice.create(tMainOffice);
        asService.create(tService);
    }

    @Test
    boolean checkValuesTContract(TContract expected, TContract actual){
        return  expected.getServiceLevel().equals(actual.getServiceLevel())
                && expected.getIdService().equals(actual.getIdService())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.isActive().equals(actual.isActive());
    }


    // ----------- CREATE ---------------
   void createOK() throws ASException, IncorrectInputException {
        assertTrue( asContract.create(tContract) != null);
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
    void createIncorrectMainOfficeActive() throws ASException, IncorrectInputException {
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
    void dropOK() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        assertTrue( asContract.drop(tContract.getIdMainOffice(),tContract.getIdService()) != null );
    }

    @Test
    void dropIncorrectId(){
        assertThrows(ASException.class, () -> asContract.drop(tContract.getIdMainOffice() + 100,tContract.getIdService() +100));
    }

    @Test
    void dropIncorrectActive() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        asContract.drop(tContract.getIdMainOffice(),tContract.getIdService());
        assertThrows(ASException.class, () -> asContract.drop(tContract.getIdMainOffice(),tContract.getIdService()));
    }


    // ------------------- UPDATE ---------------

    @Test
    void updateOK() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        assertTrue( asContract.update(tContract)!= null );
    }

    @Test
    void updateIncorrectId() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        tContract.setIdService(tContract.getIdService() + 100);

        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeId() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectMainOfficeActive() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        asMainOffice.drop(tContract.getIdMainOffice());
        assertThrows(ASException.class, () -> asContract.update(tContract));
    }

    @Test
    void updateIncorrectServiceId() throws ASException, IncorrectInputException {
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
    void updateIncorrectMainOfficeServiceId() throws ASException, IncorrectInputException {
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
    void showOK() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        assertTrue(checkValuesTContract(tContract, asContract.show(tContract.getIdMainOffice(),tContract.getIdService())));
    }

    @Test
    void showIncorrectId(){
        assertThrows(ASException.class, () -> asContract.show(tContract.getIdMainOffice() + 100,tContract.getIdService()+ 100));
    }


    // ------------------- SHOW ALL -----------------

    @Test
    void showAllOK() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        asContract.create(tContract2);
        ArrayList<TContract> result = (ArrayList<TContract>) asContract.showAll();
        assertTrue(checkValuesTContract(tContract, result.get(0)));
        assertTrue(checkValuesTContract(tContract2, result.get(1)));
    }

    @Test
    void showAllIncorrectActive() throws ASException, IncorrectInputException {
        asContract.create(tContract);
        tContract2.setActive(false);
        asContract.create(tContract2);
        asContract.drop(tContract2.getIdMainOffice(),tContract2.getIdService());
        ArrayList<TContract> result = (ArrayList<TContract>) asContract.showAll();
        assertEquals(1, result.size());
        assertTrue(checkValuesTContract(tContract, result.get(0)));
    }
}