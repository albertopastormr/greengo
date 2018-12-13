package business.employee;

import business.ASException;
import business.IncorrectInputException;
import business.employee.as.ASEmployee;
import business.employee.as.imp.ASEmployeeImp;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMainOffice;
import business.mainoffice.as.imp.ASMainOfficeImp;
import integration.DAOException;
import integration.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ASEmployeeTest {

    private static ASEmployee asEmployee = new ASEmployeeImp();
    private static ASMainOffice asMainOffice = new ASMainOfficeImp();

    private static TPermanentEmployee tEmployee;
    private static TTemporaryEmployee tEmployee2;
    private static TMainOffice tMainOffice;

    @BeforeEach
    void setUp() throws ASException, IncorrectInputException {

        try {
            Util.deleteAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        tEmployee = new TPermanentEmployee(1, "ABCD", 1350f,
                true, 1, 20f);
        tEmployee2 = new TTemporaryEmployee(2, "KIKK", 1350f,
                true, 1, 20);
        tMainOffice = new TMainOffice(1,"Madrid","C/Villamayor",true);

        asMainOffice.create(tMainOffice);
    }

    private boolean checkValuesTEmployee(TPermanentEmployee expected, TPermanentEmployee actual){
        return expected.getId().equals(actual.getId()) && expected.getIdCardNumber().equals(actual.getIdCardNumber())
                && expected.getSalary().equals(actual.getSalary())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.getApportionment().equals(actual.getApportionment())
                && expected.getType().equals(actual.getType())
                && expected.isActive().equals(actual.isActive());
    }

    private boolean checkValuesTEmployee(TTemporaryEmployee expected, TTemporaryEmployee actual){
        return expected.getId().equals(actual.getId()) && expected.getIdCardNumber().equals(actual.getIdCardNumber())
                && expected.getSalary().equals(actual.getSalary())
                && expected.getIdMainOffice().equals(actual.getIdMainOffice())
                && expected.getNumWorkedHours().equals(actual.getNumWorkedHours())
                && expected.getType().equals(actual.getType())
                && expected.isActive().equals(actual.isActive());
    }

    // ----------------- CREATE -------------------

    @Test
    void createOK() throws ASException, IncorrectInputException {
        assertTrue( asEmployee.create(tEmployee) > 0);
    }

    @Test
    void createIncorrectMainOfficeNotExist() {
        tEmployee.setIdMainOffice(tEmployee.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectMainOfficePreviouslyDeactivated() throws ASException, IncorrectInputException {
        asMainOffice.drop(tEmployee.getIdMainOffice());
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectIdCardNumberNotExists() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        assertThrows(ASException.class, () -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputIDCardNumberNull(){//idCardNumber must not  be null
        tEmployee.setIdCardNumber(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputSalaryNull(){//salary must not be null
        tEmployee.setSalary(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputNegativeSalary() {//salary must be positive
        tEmployee.setSalary(-5f);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputActiveNull(){//active must not be null
        tEmployee.setActive(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputIDMainOfficeNull(){//idMainOffice must not be null
        tEmployee.setIdMainOffice(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputNegativeIDMainOffice(){//idMainOffice must be positive
        tEmployee.setIdMainOffice(-3);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputApportionmentNull(){//apportionment must not be null
        tEmployee.setApportionment(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    @Test
    void createIncorrectInputNegativeApportionment(){//apportionment must be positive
        tEmployee.setApportionment(-3f);
        assertThrows(IncorrectInputException.class,() -> asEmployee.create(tEmployee));
    }

    // ------------------- UPDATE ------------------

    @Test
    void updateOK() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        tEmployee.setSalary(tEmployee.getSalary() + 10);
        assertTrue( asEmployee.update(tEmployee) > 0);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void updateIncorrectMainOfficeNotExist() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        tEmployee.setIdMainOffice(tEmployee.getIdMainOffice() + 100);
        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectMainOfficePreviouslyDeactivated() throws ASException, IncorrectInputException {
        tEmployee.setActive(false);
        asEmployee.create(tEmployee);
        asMainOffice.drop(tEmployee.getIdMainOffice());

        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectIdCardNumberAlreadyExists() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        asEmployee.create(tEmployee2);
        tEmployee.setIdCardNumber(tEmployee2.getIdCardNumber());

        assertThrows(ASException.class, () -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputIDCardNumberNull(){//idCardNumber must not  be null
        tEmployee.setIdCardNumber(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputSalaryNull(){//salary must not be null
        tEmployee.setSalary(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputNegativeSalary(){//salary must be positive
        tEmployee.setSalary(-5f);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputActiveNull(){//active must not be null
        tEmployee.setActive(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputIDMainOfficeNull(){//idMainOffice must not be null
        tEmployee.setIdMainOffice(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputNegativeIDMainOffice(){//idMainOffice must be positive
        tEmployee.setIdMainOffice(-3);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputApportionmentNull(){//apportionment must not be null
        tEmployee.setApportionment(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputNegativeApportionment(){//apportionment must be positive
        tEmployee.setApportionment(-3f);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee));
    }

    @Test
    void updateIncorrectInputNumWorkedHoursNull(){//NumWorkedHours must not be null
        tEmployee2.setNumWorkedHours(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.update(tEmployee2));
    }

    @Test
    void updateIncorrectInputNegativeNumWorkedHours(){//NumWorkedHours must be positive
        tEmployee2.setNumWorkedHours(-10);
        assertThrows(ASException.class,() -> asEmployee.update(tEmployee));
    }

    // ---------------------- DROP ----------------

    @Test
    void dropOK() throws ASException, IncorrectInputException {
        asEmployee.drop(asEmployee.create(tEmployee));
        assertTrue(!asEmployee.show(tEmployee.getId()).isActive());
    }

    @Test
    void dropIncorrectIdNotExists(){
        assertThrows(ASException.class, () -> asEmployee.drop(tEmployee.getId()+100));
    }

    @Test
    void dropIncorrectAlreadyDeactivated() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        asEmployee.drop(tEmployee.getId());

        assertThrows(ASException.class, () -> asEmployee.drop(tEmployee.getId()));
    }

    @Test
    void dropIncorrectInputNegativeID(){//id must be positive
        tEmployee.setId(-5);
        assertThrows(IncorrectInputException.class,() -> asEmployee.drop(tEmployee.getId()));
    }

    @Test
    void dropIncorrectInputIDNull(){//id must not be null
        tEmployee.setId(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.drop(tEmployee.getId()));
    }


    // --------------------- SHOW --------------------

    @Test
    void showOK() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) asEmployee.show(tEmployee.getId())));
    }

    @Test
    void showIncorrectIdNotExist(){
        assertThrows(Exception.class, () -> asEmployee.show(tEmployee.getId() + 100));
    }

    @Test
    void showIncorrectInputNegativeID(){//id must be positive
        tEmployee.setId(-5);
        assertThrows(IncorrectInputException.class,() -> asEmployee.show(tEmployee.getId()));
    }

    @Test
    void showIncorrectInputIDNull(){//id must not be null
        tEmployee.setId(null);
        assertThrows(IncorrectInputException.class,() -> asEmployee.show(tEmployee.getId()));
    }

    // --------------------- SHOW ALL ----------------

    @Test
    void showAllOK() throws ASException, IncorrectInputException {
        asEmployee.create(tEmployee);
        tEmployee2.setActive(false);
        asEmployee.create(tEmployee2);
        ArrayList<TEmployee> result = (ArrayList<TEmployee>) asEmployee.showAll();
        assertEquals(1, result.size());
        assertTrue(checkValuesTEmployee(tEmployee, (TPermanentEmployee) result.get(0)));
    }

}