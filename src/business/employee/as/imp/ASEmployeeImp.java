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
import java.util.Collection;
import java.util.List;

public class ASEmployeeImp implements ASEmployee {

    @Override
    public Integer create(TEmployee tEmployee) throws ASException, IncorrectInputException {
        Integer id;
        checkValuesToCreate(tEmployee);

        try {
            Employee employee = new Employee(tEmployee);
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
                throw new ASException("ERROR: There is a employee with the same type " +
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
                employee=permanent;
                employee.setMainOffice(mainOffice);
                em.persist(permanent);
            } else if (tEmployee.getType().equals("Temporary")) {
                Temporary temporary = new Temporary((TTemporaryEmployee) tEmployee);
                employee=temporary;
                employee.setMainOffice(mainOffice);
                em.persist(temporary);
            }
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
    public Integer update(TEmployee client) {
        return null;
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
    public Collection<TEmployee> showAll() {
        return null;
    }

    @Override
    public Integer setSalary(Integer idEmployee, Float salary) {
        return null;
    }

    private static void checkValuesToCreate(TEmployee employee) throws IncorrectInputException {
        if(employee.getId() != null) throw new IncorrectInputException("Id field must be empty");
        if(employee.getSalary() == null) throw new IncorrectInputException("Salary field can't be empty");
        if(employee.getSalary() < 0) throw new IncorrectInputException("Salary field must be a positive integer");
        if(employee.getIdMainOffice() == null) throw new IncorrectInputException("Id main office field can't be empty");
        if(employee.getIdMainOffice() <= 0) throw new IncorrectInputException("Id main office must be a positive integer greater than zero");
        if(employee.getIdCardNumber() == null) throw new IncorrectInputException("IdCardNumber field can't be empty");
        if(employee.isActive() == null) throw new IncorrectInputException("Active field can't be empty");
        if(employee.getType() == null) throw new IncorrectInputException("Type field can't be empty");
        if (employee.getType().equals("Permanent")) {
            TPermanentEmployee tPermanentEmployee = (TPermanentEmployee) employee;
            if(tPermanentEmployee.getApportionment() == null) throw new IncorrectInputException("Apportionment field can't be null");
            if(tPermanentEmployee.getApportionment() < 0) throw new IncorrectInputException("Apportionment field must be a positive integer");
        }
        else{
            TTemporaryEmployee tTemporaryEmployee = (TTemporaryEmployee) employee;
            if(tTemporaryEmployee.getNumWorkedHours() == null) throw new IncorrectInputException("NumWorkedHours field can't be null");
            if(tTemporaryEmployee.getNumWorkedHours() < 0) throw new IncorrectInputException("NumWorkedHours field must be a positive integer");
        }
    }
}
