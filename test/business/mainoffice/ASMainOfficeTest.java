package business.mainoffice;

import business.ASException;
import business.IncorrectInputException;
import business.contract.as.ASContract;
import business.contract.TContract;
import business.contract.as.imp.ASContractImp;
import business.employee.TEmployee;
import business.employee.TPermanentEmployee;
import business.employee.as.ASEmployee;
import business.employee.as.imp.ASEmployeeImp;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import business.service.TService;
import business.service.as.ASService;
import business.service.as.imp.ASServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ASMainOfficeTest {

    //Application services:
    private ASMain_Office asMainOffice = new ASMain_OfficeImp();
    private ASService asService = new ASServiceImp();
    private ASEmployee asEmployee = new ASEmployeeImp();
    private ASContract asContract = new ASContractImp();

    //Transfers:
    private TMainOffice tMainOffice;
    private TPermanentEmployee tPermanentEmployee;
    private TContract tContract;
    private TService tService;

    @BeforeEach
    private void setUp(){
        tMainOffice = new TMainOffice(null,"Madrid","C/Villamayor",true);
        tPermanentEmployee = new TPermanentEmployee(null, "ABCD", 1350f,
                true, null, 20f);
        tContract = new TContract(null, 2, null, null, true);
        tService = new TService(null, 100, true, "ABC", "c/Example", 0);
    }

    // === CREATE === //
    @Test
    void createMainOfficeSuccessful(){
        assertTrue(asMainOffice.create(tMainOffice)>0);
    }

    @Test
    void createMainOfficeIncorrectInput(){//id must be null
        tMainOffice.setId(0);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.create(tMainOffice));
    }

    @Test
    void createMainOfficeIncorrectInput2(){//address mustn't be null
        tMainOffice.setAdress(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.create(tMainOffice));
    }

    @Test
    void createMainOfficeIncorrectInput3(){//active mustn't be null
        tMainOffice.setActive(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.create(tMainOffice));
    }

    @Test
    void createMainOfficeErrorAddress(){//couldn´t exit other main_office with the same address
        asMainOffice.create(tMainOffice);
        assertThrows(ASException.class, () -> asMainOffice.create(tMainOffice));
    }

    // === DROP === //
    @Test
    void dropMainOfficeSuccessful(){
        Integer idMO = asMainOffice.create(tMainOffice);
        tMainOffice.setId(idMO);
        asMainOffice.drop(idMO);

        assertTrue(!asMainOffice.show(tMainOffice.getId()).isActive());
    }

    @Test
    void dropMainOfficeSuccesfulDisabledEmployees(){ //dropping a main Office without active employees is allowed.
        Integer idMO = asMainOffice.create(tMainOffice);
        tMainOffice.setId(idMO);

        tPermanentEmployee.setIdMainOffice(idMO);
        Integer idEmp = asEmployee.create(tPermanentEmployee);
        asEmployee.drop(idEmp);

        asMainOffice.drop(idMO);

        assertTrue(!asMainOffice.show(idMO).isActive());

    }

    @Test
    void dropMainOfficeSucessfulDisabledContracts(){ //dropping a main Office without active contracts is allowed.
        Integer idMO = asMainOffice.create(tMainOffice);
        Integer idS = asService.create(tService);

        tMainOffice.setId(idMO);
        tService.setId(idS);

        tContract.setIdMainOffice(idMO);
        tContract.setServiceLevel(idS);

        Integer idCon = asContract.create(tContract);
        asContract.drop(idCon);

        asMainOffice.drop(idMO);

        assertTrue(!asMainOffice.show(idMO).isActive());
    }

    @Test
    void dropMainOfficeIncorrectInput(){//id mustn`t be null
        tMainOffice.setId(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.drop(tMainOffice.getId()));
    }

    @Test
    void dropMainOfficeIncorrectInput2(){//id must be > 0
        tMainOffice.setId(-1);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.drop(tMainOffice.getId()));
    }

    @Test
    void dropMainOfficeErrorNotExists(){//shouldn't drop a main_office which doesn't exists
        tMainOffice.setId(100);
        assertThrows(ASException.class, () -> asMainOffice.drop(tMainOffice.getId()));
    }

    @Test
    void dropMainOfficeErrorNotActive(){//shouldn't drop a disbled main_office
        tMainOffice.setActive(false);
        tMainOffice.setId(asMainOffice.create(tMainOffice));
        tMainOffice.setAdress("XXX");
        assertThrows(ASException.class, () -> asMainOffice.drop(tMainOffice.getId()));
    }

    @Test
    void dropMainOfficeErrorActiveEmployees(){ //dropping main office with active employees is forbidden.
        Integer idMO = asMainOffice.create(tMainOffice);
        tMainOffice.setId(idMO);

        tPermanentEmployee.setIdMainOffice(idMO);
        asEmployee.create(tPermanentEmployee);

        assertThrows(ASException.class, () -> asMainOffice.drop(idMO));
    }

    @Test
    void dropMainOfficeErrorActiveContracts(){ //dropping main office with active contracts is forbidden.
        Integer idMO = asMainOffice.create(tMainOffice);
        Integer idS = asService.create(tService);

        tMainOffice.setId(idMO);
        tService.setId(idS);

        tContract.setIdMainOffice(idMO);
        tContract.setServiceLevel(idS);

        asContract.create(tContract);
        assertThrows(ASException.class, () -> asMainOffice.drop(idMO));
    }

    // === SHOW === //
    @Test
    void showMainOfficeSuccessful(){
        Integer idMO = asMainOffice.create(tMainOffice);
        tMainOffice.setId(idMO);

        TMainOffice toCompare = asMainOffice.show(idMO);

        assertTrue(checkValues(tMainOffice, toCompare));
    }

    @Test
    void showMainOfficeErrorNotExists(){
        tMainOffice.setId(100);
        assertThrows(ASException.class, () -> asMainOffice.show(tMainOffice.getId()));
    }

    @Test
    void showMainOfficeIncorrectInput(){//id mustn`t be null
        tMainOffice.setId(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.show(tMainOffice.getId()));
    }

    @Test
    void showMainOfficeIncorrectInput2(){//id must be > 0
        tMainOffice.setId(-1);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.show(tMainOffice.getId()));
    }

	// === SHOWALL === //
    @Test
    void showAllMainOfficeSuccessful(){
        TMainOffice tmo2 = new TMainOffice(null, "Barcelona", "calle manuao", true);

        Integer idMO1 = asMainOffice.create(tMainOffice);
        Integer idMO2 = asMainOffice.create(tmo2);

        tMainOffice.setId(idMO1);
        tmo2.setId(idMO2);

        Collection<TMainOffice> toCompare = asMainOffice.showAll();

        for(TMainOffice x: toCompare){
            if(x.getId().equals(idMO1)){
                assertTrue(checkValues(x, tMainOffice));
            }
            else assertTrue(checkValues(x, tmo2));
        }
    }

    @Test
    void showAllMainOfficesActiveSuccessful(){
        TMainOffice tmo2 = new TMainOffice(null, "Barcelona", "calle manuao", false);

        Integer idMO1 = asMainOffice.create(tMainOffice);
        Integer idMO2 = asMainOffice.create(tmo2);

        tMainOffice.setId(idMO1);
        tmo2.setId(idMO2);

        Collection<TMainOffice> toCompare = asMainOffice.showAll();

        assertEquals(toCompare.size(),1);
    }

    @Test
    void showAllMainOfficeEmptySuccessful(){
        assertTrue(asMainOffice.showAll().isEmpty());
    }

    // === UPDATE === //
    @Test
    void updateMainOfficeSuccesful(){
        Integer idMO = asMainOffice.create(tMainOffice);
        tMainOffice.setId(idMO);
        tMainOffice.setAdress("EFGH");
        tMainOffice.setActive(!asMainOffice.show(idMO).isActive());

        asMainOffice.update(tMainOffice);

        assertTrue(checkValues(tMainOffice, asMainOffice.show(idMO)));
    }

    @Test
    void updateMainOfficeIncorrectInput(){//id mustn`t be null
        tMainOffice.setId(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeIncorrectInput2(){//id must be > 0
        tMainOffice.setId(-1);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeIncorrectInput3(){ //Address must be a defined value
        tMainOffice.setAdress(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeIncorrectInput4(){ //Active must be a defined value
        tMainOffice.setActive(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeIncorrectInput5(){ //City must be a defined value.
        tMainOffice.setCity(null);
        assertThrows(IncorrectInputException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeErrorNotExists(){ //Main office must exists
        tMainOffice.setId(100);
        assertThrows(ASException.class, () -> asMainOffice.update(tMainOffice));
    }

    @Test
    void updateMainOfficeErrorAddress(){ //shouldn't exists other main_office with the same address
        asMainOffice.create(tMainOffice);
        assertThrows(ASException.class, () -> asMainOffice.update(tMainOffice));
    }

    // === TOTAL SALARY === //
    @Test
    void totalSalaryMainOfficeSuccessful(){
    	Integer idMO = asMainOffice.create(tMainOffice);
    	tMainOffice.setId(idMO);

    	tPermanentEmployee.setIdMainOffice(idMO);
    	asEmployee.create(tPermanentEmployee);

		Float totalSalary = tPermanentEmployee.getSalary() + tPermanentEmployee.getApportionment();

        assertEquals(totalSalary, asMainOffice.showSalary(tMainOffice.getId()), 0.0);
	}

	@Test
    void totalSalaryMainOfficeIncorrectInput(){//id mustn`t be null
		tMainOffice.setId(null);
		assertThrows(IncorrectInputException.class, () -> asMainOffice.showSalary(tMainOffice.getId()));
	}

	@Test
    void totalSalaryMainOfficeIncorrectInput2(){//id must be > 0
		tMainOffice.setId(-1);
		assertThrows(IncorrectInputException.class, () -> asMainOffice.showSalary(tMainOffice.getId()));
	}

	@Test
    void totalSalaryMainOfficeErrorNotExists(){ //Main office must exists
    	tMainOffice.setId(100);
    	assertThrows(ASException.class, () -> asMainOffice.showSalary(tMainOffice.getId()));
	}

	@Test
    void totalSalaryMainOfficeErrorActive(){ //Main office must be active
    	tMainOffice.setActive(false);
    	Integer idMO = asMainOffice.create(tMainOffice);

    	assertThrows(ASException.class, () -> asMainOffice.showSalary(idMO));
	}


    //Auxiliary methods
    private static boolean checkValues(TMainOffice t1, TMainOffice t2){
        return t1.getId().equals(t2.getId()) &&
                t1.getAddress().equals(t2.getAddress()) &&
                t1.getCity().equals(t2.getCity()) &&
                t1.isActive().equals(t2.isActive());
    }
}
