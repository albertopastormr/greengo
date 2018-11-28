package business.employee;

import business.ASException;
import business.employee.as.ASEmployee;
import business.employee.as.imp.ASEmployeeImp;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMain_Office;
import business.mainoffice.as.imp.ASMain_OfficeImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ASEmployeeTest {

    private static ASEmployee asEmployee = new ASEmployeeImp();
    private static ASMain_Office asMainOffice = new ASMain_OfficeImp();

    private static TPermanentEmployee tEmployee;
    private static TTemporaryEmployee tEmployee2;
    private static TMainOffice tMainOffice;

    @BeforeEach
    void setUp() {
        // deleteAll

        tEmployee = new TPermanentEmployee(1, "ABCD", 1350f,
                true, 1, 20f);
        tEmployee2 = new TTemporaryEmployee(2, "KIKK", 1350f,
                true, 1, 20);
        tMainOffice = new TMainOffice(1,"Madrid","C/Villamayor",true);

        asMainOffice.create(tMainOffice);
    }

    boolean checkValuesTEmployee(TPermanentEmployee expected, TPermanentEmployee actual){
        return expected.getId().equals(actual.getId()) && expected.getIdCardNumber().equals(actual.getIdCardNumber())
                && expected.getSalary().equals(actual.getSalary())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.getApportionment().equals(actual.getApportionment())
                && expected.getType().equals(actual.getType())
                && expected.getActive().equals(actual.getActive());
    }

    boolean checkValuesTEmployee(TTemporaryEmployee expected, TTemporaryEmployee actual){
        return expected.getId().equals(actual.getId()) && expected.getIdCardNumber().equals(actual.getIdCardNumber())
                && expected.getSalary().equals(actual.getSalary())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.getNumWorkedHours().equals(actual.getNumWorkedHours())
                && expected.getType().equals(actual.getType())
                && expected.getActive().equals(actual.getActive());
    }

    // ----------------- CREATE -------------------

    @Test
    void createOK(){
        assertTrue( asEmployee.create(tEmployee) > 0);
    }

    @Test
    void createIncorrectMainOfficeId(){
        tEmployee.setIdMainOffice(tEmployee.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectMainOfficeActive(){
        asMainOffice.drop(tEmployee.getIdMainOffice());
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectIdCardNumber(){
        asEmployee.create(tEmployee);
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }


    // ------------------- UPDATE ------------------

    @Test
    void updateOK(){
        asEmployee.create(tEmployee);
        tEmployee.setSalary(tEmployee.getSalary() + 10);
        assertTrue( asEmployee.update(tEmployee) > 0);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void updateIncorrectMainOfficeId(){
        asEmployee.create(tEmployee);
        tEmployee.setIdMainOffice(tEmployee.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectMainOfficeActive(){
        asEmployee.create(tEmployee);
        asMainOffice.drop(tEmployee.getIdMainOffice());

        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectIdCardNumber(){
        asEmployee.create(tEmployee);
        asEmployee.create(tEmployee2);
        tEmployee.setIdCardNumber(tEmployee2.getIdCardNumber());

        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }


    // -------------------- SET SALARY ------------

    @Test
    void setSalaryOK(){
        asEmployee.create(tEmployee);
        assertTrue( asEmployee.setSalary(tEmployee.getId(), tEmployee.getSalary() + 10) > 0);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void setSalaryIncorrectId(){
        assertThrows(ASException.class, () -> asEmployee.setSalary(tEmployee.getId()+100, 0f));
    }


    // ---------------------- DROP ----------------

    @Test
    void dropOK(){
        asEmployee.create(tEmployee);
        tEmployee.setActive(false);
        asEmployee.drop(tEmployee.getId());

        assertTrue( asEmployee.drop(tEmployee.getId()) > 0);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void dropIncorrectId(){
        assertThrows(ASException.class, () -> asEmployee.drop(tEmployee.getId()+100));
    }

    @Test
    void dropIncorrectActive(){
        asEmployee.create(tEmployee);
        asEmployee.drop(tEmployee.getId());

        assertThrows(ASException.class, () -> asEmployee.drop(tEmployee.getId()));
    }


    // --------------------- SHOW --------------------

    @Test
    void showOK(){
        asEmployee.create(tEmployee);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void showIncorrectId(){
        assertThrows(Exception.class, () -> asEmployee.show(tEmployee.getId() + 100));
    }


    // --------------------- SHOW ALL ----------------

    @Test
    void showAllOK(){
        asEmployee.create(tEmployee);
        asEmployee.create(tEmployee2);
        ArrayList<TEmployee> result = (ArrayList<TEmployee>) asEmployee.showAll();
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) result.get(0)));
        assertTrue(checkValuesTEmployee(tEmployee2, (TTemporaryEmployee) result.get(1)));
    }

    @Test
    void showAllIncorrectActive(){
        asEmployee.create(tEmployee);
        tEmployee2.setActive(false);
        asEmployee.create(tEmployee2);
        asEmployee.drop(tEmployee2.getId());
        ArrayList<TEmployee> result = (ArrayList<TEmployee>) asEmployee.showAll();
        assertEquals(1, result.size());
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) result.get(0)));
    }
}