package business.employee.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.employee.*;
import business.employee.as.ASEmployee;
import business.mainoffice.MainOffice;
import integration.DAOException;
import integration.TransactionException;
import integration.transaction.Transaction;
import integration.transaction.imp.TransactionMariaDB;
import integration.transactionManager.TransactionManager;
import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASEmployeeImp implements ASEmployee {

    @Override
    public Integer create(TEmployee tEmployee) throws ASException, IncorrectInputException {
        Integer id = null;
        checkValuesToCreate(tEmployee);

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("Employee.findByidCardNumber", Employee.class);
            query.setParameter("idCardNumber", tEmployee.getIdCardNumber());

            List<Employee> employeeList = query.getResultList();

            MainOffice mainOffice = em.find(MainOffice.class, tEmployee.getIdMainOffice());

            if (!employeeList.isEmpty()) {
                transaction.rollback();
                throw new ASException("ERROR: There is an employee with the same idCardNumber " +
                        "(" + tEmployee.getIdCardNumber() + ") (duplication)");
            } else if (mainOffice == null) {
                transaction.rollback();
                throw new ASException("ERROR: The Main Office doesn't exist");
            } else if (!mainOffice.isActive()) {
                transaction.rollback();
                throw new ASException("ERROR: The Main Office is disabled");
            }


            if (tEmployee.getType().equals("Permanent")) {
                Permanent permanent = new Permanent((TPermanentEmployee) tEmployee);
                permanent.setMainOffice(mainOffice);
                permanent.setApportionment( ((TPermanentEmployee)tEmployee).getApportionment());
                em.persist(permanent);
                transaction.commit();
                id = permanent.getId();
            } else if (tEmployee.getType().equals("Temporary")) {
                Temporary temporary = new Temporary((TTemporaryEmployee) tEmployee);
                temporary.setMainOffice(mainOffice);
                temporary.setNumWorkedHours( ((TTemporaryEmployee) tEmployee).getNumWorkedHours());
                em.persist(temporary);
                transaction.commit();
                id = temporary.getId();
            }
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return id;
    }

    @Override
    public Integer drop(Integer idEmployee) throws ASException, IncorrectInputException {
        Integer id;

        if(idEmployee == null) throw  new IncorrectInputException("Id field can't be empty");
        if(idEmployee < 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Employee employee = em.find(Employee.class, idEmployee);

            if(employee == null){
                transaction.rollback();
                throw  new ASException("ERROR: The employee doesn't exist");
            }

            if(!employee.isActive()){
                transaction.rollback();
                throw  new ASException("ERROR: The employee is already disabled");
            }

            id = employee.getId();
            employee.setActive(false);
            transaction.commit();

            em.close();
            emf.close();

        }catch (PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }

        return id;
    }

    @Override
    public Integer update(TEmployee tEmployee) throws IncorrectInputException, ASException {
        Integer id;
        checkValuesToUpdate(tEmployee);

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Employee employee = em.find(Employee.class, tEmployee.getId());

            if(employee == null){
                transaction.rollback();
                throw  new ASException("ERROR: The employee doesn't exist");
            }

            Query query = em.createNamedQuery("Employee.findByidCardNumber", Employee.class);
            query.setParameter("idCardNumber", tEmployee.getIdCardNumber());

            List<Employee> employeeList = query.getResultList();

            MainOffice mainOffice = em.find(MainOffice.class, tEmployee.getIdMainOffice());

            if (!employeeList.isEmpty() && !(employeeList.get(0).getId().equals(tEmployee.getId()))) {
                transaction.rollback();
                throw new ASException("ERROR: There is an employee with the same idCardNumber" +
                        "(" + tEmployee.getIdCardNumber() + ") (duplication)");
            }
            if (mainOffice == null) {
                transaction.rollback();
                throw new ASException("ERROR: The Main Office doesn't exist");
            }
            if (!mainOffice.isActive()) {
                transaction.rollback();
                throw new ASException("ERROR: The Main Office is disabled");
            }


            if (tEmployee.getType().equals("Permanent")) {
                Permanent permanent = em.find(Permanent.class, tEmployee.getId());
                permanent.setMainOffice(mainOffice);
                permanent.setApportionment( ((TPermanentEmployee)tEmployee).getApportionment());
                em.persist(permanent);
            } else if (tEmployee.getType().equals("Temporary")) {
                Temporary temporary = em.find(Temporary.class, tEmployee.getId());
                temporary.setMainOffice(mainOffice);
                temporary.setNumWorkedHours( ((TTemporaryEmployee) tEmployee).getNumWorkedHours());
                em.persist(temporary);
            }

            employee.setIdCardNumber(tEmployee.getIdCardNumber());
            employee.setSalary(tEmployee.getSalary());
            employee.setMainOffice(mainOffice);

            transaction.commit();
            id = employee.getId();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return id;
    }

    @Override
    public TEmployee show(Integer idEmployee) throws  ASException, IncorrectInputException{

        if(idEmployee == null) throw  new IncorrectInputException("Id field can't be empty");
        if(idEmployee < 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        TEmployee tEmployee;

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Employee employee = em.find(Employee.class, idEmployee);

            if(employee == null){
                transaction.rollback();
                throw new ASException("ERROR: The employee doesn't exist");
            }

            tEmployee = new TEmployee(employee.getId(), employee.getIdCardNumber(), employee.getSalary(),
                    employee.isActive(), employee.getMainOffice().getId(), employee.getType());

            if(tEmployee.getType().equals("Temporary")){
                Temporary temporary = em.find(Temporary.class,idEmployee);
                tEmployee = new TTemporaryEmployee(tEmployee.getId(), tEmployee.getIdCardNumber(), tEmployee.getSalary(),
                        tEmployee.isActive(), tEmployee.getIdMainOffice(), temporary.getNumWorkedHours());
            }
            else{
                Permanent permanent = em.find(Permanent.class, idEmployee);
                tEmployee = new TPermanentEmployee(tEmployee.getId(), tEmployee.getIdCardNumber(), tEmployee.getSalary(),
                        tEmployee.isActive(), tEmployee.getIdMainOffice(), permanent.getApportionment());
            }
            transaction.commit();

            em.close();
            emf.close();

        }catch (PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }

        return tEmployee;
    }

    @Override
    public Collection<TEmployee> showAll() throws ASException {
        Collection<TEmployee> tEmployeeArrayList =  new ArrayList<>();
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            Query query = em.createNamedQuery("Employee.findByActive", Employee.class);
            query.setParameter("active", true);

            Collection<Employee> employeeList = query.getResultList();


            for (Employee employee : employeeList) {
                if(employee.getType().equals("Temporary")){
                    Temporary temporary = em.find(Temporary.class, employee.getId());
                    tEmployeeArrayList.add( new TTemporaryEmployee(employee.getId(), employee.getIdCardNumber(), employee.getSalary(),
                            employee.isActive(), employee.getMainOffice().getId(), temporary.getNumWorkedHours()));
                }
                else{
                    Permanent permanent = em.find(Permanent.class, employee.getId());
                    tEmployeeArrayList.add( new TPermanentEmployee(employee.getId(), employee.getIdCardNumber(), employee.getSalary(),
                            employee.isActive(), employee.getMainOffice().getId(), permanent.getApportionment()));
                }
            }

            transaction.commit();
            em.close();
            emf.close();

        }catch(PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }
        return tEmployeeArrayList;
    }

    private static void checkValuesToCreate(TEmployee employee) throws IncorrectInputException {
        if(employee.getSalary() == null) throw new IncorrectInputException("Salary field can't be empty");
        if(employee.getSalary() < 0) throw new IncorrectInputException("Salary field must be a positive integer");
        if(employee.getIdMainOffice() == null) throw new IncorrectInputException("Id main office field can't be empty");
        if(employee.getIdMainOffice() <= 0) throw new IncorrectInputException("Id main office must be a positive " +
                "integer greater than zero");
        if(employee.getIdCardNumber() == null) throw new IncorrectInputException("IdCardNumber field can't be empty");
        if(employee.isActive() == null) throw new IncorrectInputException("Active field can't be empty");
        if(employee.getType() == null) throw new IncorrectInputException("Type field can't be empty");
        if (employee.getType().equals("Permanent")) {
            TPermanentEmployee tPermanentEmployee = (TPermanentEmployee) employee;
            if(tPermanentEmployee.getApportionment() == null) throw new IncorrectInputException("Apportionment " +
                    "field can't be null");
            if(tPermanentEmployee.getApportionment() < 0) throw new IncorrectInputException("Apportionment field" +
                    " must be a positive integer");
        }
        else{
            TTemporaryEmployee tTemporaryEmployee = (TTemporaryEmployee) employee;
            if(tTemporaryEmployee.getNumWorkedHours() == null) throw new IncorrectInputException("NumWorkedHours " +
                    "field can't be null");
            if(tTemporaryEmployee.getNumWorkedHours() < 0) throw new IncorrectInputException("NumWorkedHours field " +
                    "must be a positive integer");
        }
    }

    private static void checkValuesToUpdate(TEmployee employee) throws IncorrectInputException {
        if(employee.getId() == null) throw new IncorrectInputException("Id is null");
        if(employee.getId() <= 0) throw new IncorrectInputException("Id field must be a positive " +
                "integer greater than zero");
        if(employee.getSalary() == null) throw new IncorrectInputException("Salary field can't be empty");
        if(employee.getSalary() < 0) throw new IncorrectInputException("Salary field must be a positive integer");
        if(employee.getIdMainOffice() == null) throw new IncorrectInputException("Id main office field can't be empty");
        if(employee.getIdMainOffice() <= 0) throw new IncorrectInputException("Id main office must be a positive " +
                "integer greater than zero");
        if(employee.getIdCardNumber() == null) throw new IncorrectInputException("IdCardNumber field can't be empty");
        if(employee.isActive() == null) throw new IncorrectInputException("Active field can't be empty");
        if(employee.getType() == null) throw new IncorrectInputException("Type field can't be empty");
        if (employee.getType().equals("Permanent")) {
            TPermanentEmployee tPermanentEmployee = (TPermanentEmployee) employee;
            if(tPermanentEmployee.getApportionment() == null) throw new IncorrectInputException("Apportionment " +
                    "field can't be null");
            if(tPermanentEmployee.getApportionment() < 0) throw new IncorrectInputException("Apportionment field" +
                    " must be a positive integer");
        }
        else{
            TTemporaryEmployee tTemporaryEmployee = (TTemporaryEmployee) employee;
            if(tTemporaryEmployee.getNumWorkedHours() == null) throw new IncorrectInputException("NumWorkedHours " +
                    "field can't be null");
            if(tTemporaryEmployee.getNumWorkedHours() < 0) throw new IncorrectInputException("NumWorkedHours field " +
                    "must be a positive integer");
        }
    }
}
