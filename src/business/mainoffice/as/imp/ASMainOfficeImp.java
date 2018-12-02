package business.mainoffice.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.contract.Contract;
import business.employee.Employee;
import business.mainoffice.MainOffice;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMainOffice;
import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASMainOfficeImp implements ASMainOffice {
    @Override
    public Integer create(TMainOffice tMainOffice) throws IncorrectInputException, ASException {
        Integer id;
        checkValuesToCreate(tMainOffice);

        MainOffice mainOfficeObject = new MainOffice(tMainOffice);
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("MainOffice.findByaddress", MainOffice.class);
            query.setParameter("address", mainOfficeObject.getAddress());

            List<MainOffice> mainOfficesList = query.getResultList();

            if (!mainOfficesList.isEmpty()) {
                transaction.rollback();
                throw new ASException("ERROR: There is a tMainOffice with the same address (" + tMainOffice.getAddress() + ") (duplication)");
            } else {
                em.persist(mainOfficeObject);
                transaction.commit();
                id = mainOfficeObject.getId();
            }
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }
        return id;
    }

    @Override
    public Integer drop(Integer idMainOffice) throws ASException, IncorrectInputException {
        Integer id;

        if (idMainOffice == null) throw new IncorrectInputException("Id field can't be empty");
        if (idMainOffice <= 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            MainOffice mainOffice = em.find(MainOffice.class, idMainOffice);

            Query query = em.createNamedQuery("Contract.findBymain_Office", Contract.class);
            query.setParameter("mainOffice", mainOffice);

            List<Contract> contractslist = query.getResultList();
            Collection<Employee> employeeList = mainOffice.getEmployee();

            if (mainOffice == null) {
                transaction.rollback();
                throw new ASException("The main office doesn't exist");
            } else if (!mainOffice.isActive()) {
                transaction.rollback();
                throw new ASException("The main office is already disabled");
            }
            //TODO Comprobar esto en interfaz, se creo antes de hacer empleado y contrato
            for (Contract contract : contractslist) {
                if (contract.isActive()) {
                    transaction.rollback();
                    throw new ASException("There are active contracts");
                }
            }
            for (Employee employee : employeeList) {
                if (employee.isActive()) {
                    transaction.rollback();
                    throw new ASException("There are active employees");
                }
            }
            id = mainOffice.getId();
            mainOffice.setActive(false);
            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }
        return id;
    }

    @Override
    public Integer update(TMainOffice tMainoffice) throws ASException, IncorrectInputException {
        Integer id = null;
        checkValuesToUpdate(tMainoffice);

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            MainOffice mainOfficeBD = em.find(MainOffice.class, tMainoffice.getId());

            Query query = em.createNamedQuery("MainOffice.findByaddress", MainOffice.class);
            query.setParameter("address", tMainoffice.getAddress());

            List<MainOffice> addressMainOfficeList = query.getResultList();

            if (mainOfficeBD == null) {
                transaction.rollback();
                throw new ASException("The MainOffice doesn't exist");
            } else if (!addressMainOfficeList.isEmpty() && !addressMainOfficeList.get(0).getId().equals(mainOfficeBD.getId())) {
                transaction.rollback();
                throw new ASException("ERROR: There is a tMainOffice with the same address (" + tMainoffice.getAddress() + ") (duplication)");
            }

            id = mainOfficeBD.getId();
            mainOfficeBD.setAddress(tMainoffice.getAddress());
            mainOfficeBD.setCity(tMainoffice.getCity());
            mainOfficeBD.setActive(true);
            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return id;
    }

    //TODO revisar si es necesaria abrir una transaccion en show
    @Override
    public TMainOffice show(Integer idMainOffice) throws ASException, IncorrectInputException {
        TMainOffice tMainOffice;

        if (idMainOffice == null) throw new IncorrectInputException("Id field can't be empty");
        if (idMainOffice <= 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            MainOffice mainOffice = em.find(MainOffice.class, idMainOffice);
            if (mainOffice == null) {
                transaction.rollback();
                throw new ASException("The main office doesn't exist");
            }
            tMainOffice = new TMainOffice(mainOffice.getId(), mainOffice.getCity(), mainOffice.getAddress(), mainOffice.isActive());
            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return tMainOffice;
    }

	@Override
	public Collection<TMainOffice> showAll() throws ASException {
		Collection<TMainOffice> tMainOfficesList =  new ArrayList<>();
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
			EntityManager em = emf.createEntityManager();
			EntityTransaction transaction = em.getTransaction();

			transaction.begin();
			Query query = em.createNamedQuery("Service.findAllMainOffices", MainOffice.class);

			Collection<MainOffice> mainOfficesList = query.getResultList();


			for (MainOffice mainOffice : mainOfficesList) {
				tMainOfficesList.add(new TMainOffice(mainOffice.getId(), mainOffice.getCity(),
                        mainOffice.getAddress(), mainOffice.isActive()));
			}

			transaction.commit();
			em.close();
			emf.close();

		}catch(PersistenceException | EclipseLinkException e){
			throw new ASException(e.getMessage());
		}
		return tMainOfficesList;
	}

	@Override
    public Float showSalary(Integer idMainOffice) throws ASException, IncorrectInputException {
        Float result = 0f;

        if (idMainOffice == null) throw new IncorrectInputException("Id field can't be empty");
        if (idMainOffice < 0) throw new IncorrectInputException("Id must be a positive integer greater than zero");

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("MainOffice.findByemployee", MainOffice.class);
            query.setParameter("employee", idMainOffice);

            Collection<Employee> employeesList = query.getResultList();

            for (Employee employee : employeesList) {
                result += employee.getSalary();
            }

            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return result;
    }

	private static void checkValuesToCreate(TMainOffice office) throws IncorrectInputException{
	    if(office.getId() != null) throw new IncorrectInputException("Id must be null");
	    if(office.getAddress() == null) throw new IncorrectInputException("Address field can't be empty");
	    if(office.getCity() == null) throw new IncorrectInputException("City field can't be empty");
	    if(office.isActive() == null) throw new IncorrectInputException("Active field can't be empty");
    }

    private static void checkValuesToUpdate(TMainOffice office) throws IncorrectInputException{
        if(office.getId() == null) throw new IncorrectInputException("Id field can't be empty");
        if(office.getAddress() == null) throw new IncorrectInputException("Address field can't be empty");
        if(office.getCity() == null) throw new IncorrectInputException("City field can't be empty");
        if(office.getId() <= 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");
    }



}
