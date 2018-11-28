package business.mainoffice;

import business.ASException;
import business.IncorrectInputException;
import business.contract.TContract;
import business.contract.as.ASContract;
import business.contract.factory.ASContractFactory;
import business.employee.TEmployee;
import business.employee.TPermanentEmployee;
import business.employee.TTemporaryEmployee;
import business.employee.as.ASEmployee;
import business.employee.as.imp.Employee;
import business.employee.factory.ASEmployeeFactory;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.factory.ASMain_OfficeFactory;
import business.service.TService;
import business.service.as.ASService;
import business.service.factory.ASServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ASMainOfficeTest {

    //Application services:
    private ASMain_Office as = ASMain_OfficeFactory.getInstance().generateASMain_Office();
    private ASService ass = ASServiceFactory.getInstance().generateASService();
    private ASEmployee asEmp = ASEmployeeFactory.getInstance().generateASEmployee();
    private ASContract asCon = ASContractFactory.getInstance().generateASContract();

    //Transfers:
    private TMainOffice tmo = new TMainOffice(null,"Madrid","C/Villamayor",true);
    private TEmployee perEmp = new TPermanentEmployee(null, "ABCD", 1350f, true, null, 20f);
    private TEmployee TempEmp = new TTemporaryEmployee(null, "ABCD", 1350f, true, null, 6);
    private TContract tCon = new TContract(null, 2, null, null, true);
    private TService tSer = new TService(null, 100, true, "ABC", "c/Example", 0);

    @BeforeEach
    private void setUp() throws Exception{


    }

    @Test
    public void createMainOfficeSuccessful(){
        assertTrue(as.create(tmo)>0);
    }

    @Test
    public void createMainOfficeIncorrectInput(){//id must be null
        tmo.setId(0);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeIncorrectInput2(){//address mustn't be null
        tmo.setAdress(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeIncorrectInput3(){//active mustn't be null
        tmo.setActive(null);
        assertThrows(IncorrectInputException.class, () -> {as.create(tmo);});
    }

    @Test
    public void createMainOfficeErrorAddress(){//couldn´t exit other main_office with the same address
        as.create(tmo);
        assertThrows(ASException.class, () -> {as.create(tmo);});
    }

    @Test
    public void dropMainOfficeSuccessful(){
        Integer idMO = as.create(tmo);
        tmo.setId(idMO);
        as.drop(idMO);

        assertTrue(!as.show(tmo.getId()).isActive());
    }

    @Test
    public void dropMainOfficeSuccesfulDisabledEmployees(){ //dropping a main Office without active employees is allowed.
        Integer idMO = as.create(tmo);
        tmo.setId(idMO);

        perEmp.setIdMainOffice(idMO);
        Integer idEmp = asEmp.create(perEmp);
        asEmp.drop(idEmp);

        as.drop(idMO);

        assertTrue(!as.show(idMO).isActive());

    }

    @Test
    public void dropMainOfficeSucessfulDisabledContracts(){ //dropping a main Office without active contracts is allowed.
        Integer idMO = as.create(tmo);
        Integer idS = ass.create(tSer);

        tmo.setId(idMO);
        tSer.setId(idS);

        tCon.setIdMainOffice(idMO);
        tCon.setServiceLevel(idS);

        Integer idCon = asCon.create(tCon);
        asCon.drop(idCon);

        as.drop(idMO);

        assertTrue(!as.show(idMO).isActive());
    }

    @Test
    public void dropMainOfficeIncorrectInput(){//id mustn`t be null
        tmo.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeIncorrectInput2(){//id must be > 0
        tmo.setId(-1);
        assertThrows(IncorrectInputException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeErrorNotExists(){//shouldn't drop a main_office which doesn't exists
        tmo.setId(100);
        assertThrows(ASException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeErrorNotActive(){//shouldn't drop a disbled main_office
        tmo.setActive(false);
        tmo.setId(as.create(tmo));
        tmo.setAdress("XXX");
        assertThrows(ASException.class, () -> {as.drop(tmo.getId());});
    }

    @Test
    public void dropMainOfficeErrorActiveEmployees(){ //dropping main office with active employees is forbidden.
        Integer idMO = as.create(tmo);
        tmo.setId(idMO);

        perEmp.setIdMainOffice(idMO);
        asEmp.create(perEmp);

        assertThrows(ASException.class, () -> as.drop(idMO));
    }

    @Test
    public void dropMainOfficeErrorActiveContracts(){ //dropping main office with active contracts is forbidden.
        Integer idMO = as.create(tmo);
        Integer idS = ass.create(tSer);

        tmo.setId(idMO);
        tSer.setId(idS);

        tCon.setIdMainOffice(idMO);
        tCon.setServiceLevel(idS);

        asCon.create(tCon);
        assertThrows(ASException.class, () -> as.drop(idMO));
    }

    @Test
    public void showMainOfficeSuccessful(){
        Integer idMO = as.create(tmo);
        tmo.setId(idMO);

        TMainOffice toCompare = as.show(idMO);

        assertTrue(checkValues(tmo, toCompare));
    }

    @Test
    public void showMainOfficeErrorNotExists(){
        tmo.setId(100);
        assertThrows(ASException.class, () -> as.show(tmo.getId()));
    }

    @Test
    public void showMainOfficeIncorrectInput(){//id mustn`t be null
        tmo.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.show(tmo.getId());});
    }

    @Test
    public void showMainOfficeIncorrectInput2(){//id must be > 0
        tmo.setId(-1);
        assertThrows(IncorrectInputException.class, () -> {as.show(tmo.getId());});
    }


    @Test
    public void showAllMainOfficeSuccessful(){
        TMainOffice tmo2 = new TMainOffice(null, "Barcelona", "calle manuao", true);

        Integer idMO1 = as.create(tmo);
        Integer idMO2 = as.create(tmo2);

        tmo.setId(idMO1);
        tmo2.setId(idMO2);

        Collection<TMainOffice> toCompare = as.showAll();

        for(TMainOffice x: toCompare){
            if(x.getId() == idMO1){
                assertTrue(checkValues(x,tmo));
            }
            else assertTrue(checkValues(x, tmo2));
        }
    }

    @Test
    public void showAllMainOfficeEmptySuccessful(){
        assertTrue(as.showAll().isEmpty());
    }

    @Test
    public void updateMainOfficeSuccesful(){
        Integer idMO = as.create(tmo);
        tmo.setId(idMO);
        tmo.setAdress("EFGH");
        tmo.setActive(!as.show(idMO).isActive());

        as.update(tmo);

        assertTrue(checkValues(tmo, as.show(idMO)));
    }

    @Test
    public void updateMainOfficeIncorrectInput(){//id mustn`t be null
        tmo.setId(null);
        assertThrows(IncorrectInputException.class, () -> {as.update(tmo);});
    }

    @Test
    public void updateMainOfficeIncorrectInput2(){//id must be > 0
        tmo.setId(-1);
        assertThrows(IncorrectInputException.class, () -> {as.update(tmo);});
    }

    @Test
    public void updateMainOfficeIncorrectInput3(){ //Address must be a defined value
        tmo.setAdress(null);
        assertThrows(IncorrectInputException.class, () -> as.update(tmo));
    }

    @Test
    public void updateMainOfficeIncorrectInput4(){ //Active must be a defined value
        tmo.setActive(null);
        assertThrows(IncorrectInputException.class, () -> as.update(tmo));
    }

    @Test
    public void updateMainOfficeIncorrectInput5(){ //City must be a defined value.
        tmo.setCity(null);
        assertThrows(IncorrectInputException.class, () -> as.update(tmo));
    }

    @Test
    public void updateMainOfficeErrorNotExists(){ //Main office must exists
        tmo.setId(100);
        assertThrows(ASException.class, () -> as.update(tmo));
    }

    @Test
    public void updateMainOfficeErrorAddress(){ //shouldn't exists other main_office with the same address
        as.create(tmo);
        assertThrows(ASException.class, () -> as.update(tmo));
    }


    //Auxiliary methods
    public static boolean checkValues(TMainOffice t1, TMainOffice t2){
        return t1.getId() == t2.getId() &&
                t1.getAddress().equals(t2.getAddress()) &&
                t1.getCity().equals(t2.getCity()) &&
                t1.isActive() == t2.isActive();
    }
}
