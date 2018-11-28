package business.contract;

import business.contract.as.ASContract;
import business.contract.as.imp.ASContractImp;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import business.service.TService;
import business.service.as.ASService;
import business.service.as.imp.ASServiceImp;
import org.junit.jupiter.api.BeforeEach;

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
    void setUp() {

        tMainOffice = new TMainOffice(1,"manu","C/algomas",true);
        tService = new TService(1,1,true,"tipo planta", "~", 1);
        tContract = new TContract(1,1,1,1,true);
        tContract2 = new TContract(2,4,1,1,true);

        asMainOffice.create(tMainOffice);
        asService.create(tService);
    }

    boolean checkValuesTContract(TContract expected, TContract actual){
        return expected.getId().equals(actual.getId()) && expected.getServiceLevel().equals(actual.getServiceLevel())
                && expected.getIdService().equals(actual.getIdService())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.isActive().equals(actual.isActive());
    }


    // ----------- CREATE ---------------
    void createOK(){
        assertTrue( asContract.create(tContract) > 0);
    }

    void createIncorrectMainOfficeId(){
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    void createIncorrectMainOfficeActive(){
        asMainOffice.drop(tContract.getIdMainOffice());
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    void createIncorrectServiceId(){
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    void createIncorrectServiceActive(){
        asService.drop(tContract.getIdService());
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    void createIncorrectMainOfficeServiceId(){
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    void createIncorrectMainOfficeServiceActive(){
        asMainOffice.drop(tContract.getIdMainOffice());
        asService.drop(tContract.getIdService());
        assertThrows(Exception.class, () -> asContract.create(tContract));
    }

    // ------------------- DROP -----------------

    void dropOK(){
        asContract.create(tContract);
        assertTrue( asContract.drop(tContract.getId()) > 0);
    }

    void dropIncorrectId(){
        asContract.create(tContract);
        tContract.setId(tContract.getId() + 100);
        assertThrows(Exception.class, () -> asContract.drop(tContract.getId()));
    }

    void dropIncorrectActive(){
        asContract.create(tContract);
        asContract.drop(tContract.getId());
        assertThrows(Exception.class, () -> asContract.drop(tContract.getId()));
    }


    // ------------------- UPDATE ---------------

    void updateOK(){
        asContract.create(tContract);
        assertTrue( asContract.update(tContract) > 0);
    }

    void updateIncorrectId(){
        asContract.create(tContract);
        tContract.setId(tContract.getId() + 100);
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectMainOfficeId(){
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectMainOfficeActive(){
        asContract.create(tContract);
        asMainOffice.drop(tContract.getIdMainOffice());
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectServiceId(){
        asContract.create(tContract);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectServiceActive(){
        asContract.create(tContract);
        asService.drop(tContract.getIdService());
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectMainOfficeServiceId(){
        asContract.create(tContract);
        tContract.setIdMainOffice(tContract.getIdMainOffice() + 100);
        tContract.setIdService(tContract.getIdService() + 100);
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }

    void updateIncorrectMainOfficeServiceActive(){
        asContract.create(tContract);
        asMainOffice.drop(tContract.getIdMainOffice());
        asService.drop(tContract.getIdService());
        assertThrows(Exception.class, () -> asContract.update(tContract));
    }


    // ------------------ SHOW -------------

    void showOK(){
        asContract.create(tContract);
        assertTrue(checkValuesTContract(tContract, asContract.show(tContract.getId())));
    }

    void showIncorrectId(){
        asContract.create(tContract);
        assertThrows(Exception.class, () -> asContract.show(tContract.getId() + 100));
    }

    void showAllOK(){
        asContract.create(tContract);
        asContract.create(tContract2);
        ArrayList<TContract> result = (ArrayList<TContract>) asContract.showAll();
        assertTrue(checkValuesTContract(tContract, result.get(0)));
        assertTrue(checkValuesTContract(tContract2, result.get(1)));
    }

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