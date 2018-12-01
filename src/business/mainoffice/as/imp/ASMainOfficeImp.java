package business.mainoffice.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.contract.Contract;
import business.employee.Employee;
import business.mainoffice.MainOffice;
import business.mainoffice.TMainOffice;
import business.mainoffice.as.ASMainOffice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASMainOfficeImp implements ASMainOffice {
	@Override
	public Integer create(TMainOffice tMainOffice) throws IncorrectInputException, ASException {
		Integer id;
		if(tMainOffice.getId() == null && tMainOffice.getAddress() != null && tMainOffice.getCity()!= null && tMainOffice.isActive()!= null){
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
					throw new ASException("ERROR: There is a tMainOffice with the parameter 'address'= " + tMainOffice.getAddress() + " (duplication)");
				} else {
					em.persist(mainOfficeObject);
					transaction.commit();
					id = mainOfficeObject.getId();
				}
				em.close();
				emf.close();
			}catch(PersistenceException e){
				throw new ASException("Error in Database");
			}
		}
		else throw new IncorrectInputException("\"Parameters mustn`t be null");
		return id;
	}

	@Override
	public Integer drop(Integer idMainOffice) throws ASException, IncorrectInputException {
		Integer id = null;
		if(idMainOffice != null && idMainOffice > 0) {
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
				EntityManager em = emf.createEntityManager();
				EntityTransaction transaction = em.getTransaction();

				transaction.begin();

				MainOffice mainOffice = em.find(MainOffice.class, idMainOffice);

				Query query = em.createNamedQuery("Contract.findBymain_Office", Contract.class);
				query.setParameter("mainOffice", mainOffice);

				List<Contract> contractslist = query.getResultList();

				Query queryEmp = em.createNamedQuery("MainOffice.findByemployee", Contract.class);
				queryEmp.setParameter("employee", mainOffice);

				List<Employee> employeeList = query.getResultList();

				if (mainOffice == null) {
					transaction.rollback();
					throw new ASException("The service doesn`t exist");
				} else if (!mainOffice.isActive()) {
					transaction.rollback();
					throw new ASException("The service is already disabled");
				}
				//TODO Comprobar esto en interfaz, se creo antes de hacer empleado y contrato
				for (Contract contract : contractslist) {
					if (contract.isActive()) {
						transaction.rollback();
						throw new ASException("There are contracts activated");
					}
				}
				for (Employee employee : employeeList) {
					if (employee.isActive()) {
						transaction.rollback();
						throw new ASException("There are employees activated");
					}
				}
				id = mainOffice.getId();
				mainOffice.setActive(false);
				transaction.commit();
				em.close();
				emf.close();

			} catch (PersistenceException e) {
				throw new ASException("Error in Database");
			}
		}
		else throw new IncorrectInputException("The idMainOffice mustn`t be null and positive");
		return id;
	}

	@Override
	public Integer update(TMainOffice tMainoffice) throws ASException, IncorrectInputException {
		Integer id = null;
		if(tMainoffice.getId()!= null && tMainoffice.getAddress()!= null && tMainoffice.getCity()!=null  && tMainoffice.getId() > 0 ) {
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
				EntityManager em = emf.createEntityManager();
				EntityTransaction transaction = em.getTransaction();

				transaction.begin();

				MainOffice mainOffice = new MainOffice(tMainoffice);

				MainOffice mainOfficeBD = em.find(MainOffice.class, tMainoffice.getId());

				Query query = em.createNamedQuery("MainOffice.findByaddress",MainOffice.class);
				query.setParameter("address", tMainoffice.getAddress());

				List<MainOffice> addressMainOfficeList = query.getResultList();

				if(mainOfficeBD == null){
					transaction.rollback();
					throw new ASException("The MainOffice doesn´t exist");
				}else if(!addressMainOfficeList.isEmpty() && !addressMainOfficeList.get(0).getId().equals(mainOfficeBD.getId())){
					transaction.rollback();
					throw new ASException("ERROR: There is a MainOffice with the parameter 'type'= "+tMainoffice.getAddress()+" (duplication)");
				}

				mainOfficeBD.setAddress(tMainoffice.getAddress());
				mainOfficeBD.setCity(tMainoffice.getCity());
				mainOfficeBD.setActive(true);
				transaction.commit();
				em.close();
				emf.close();

			} catch (PersistenceException e) {
				throw new ASException("Error in Database");
			}
		}
		else throw new IncorrectInputException("Parameters mustn`t be null and their numbers must be positive");

		return id;
	}

	@Override
	public TMainOffice show(Integer idMainOffice) throws ASException, IncorrectInputException {
		TMainOffice tMainOffice;
		if(idMainOffice!=null && idMainOffice > 0){
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
				EntityManager em = emf.createEntityManager();
				EntityTransaction transaction = em.getTransaction();

				transaction.begin();

				MainOffice mainOffice = em.find(MainOffice.class, idMainOffice);
				if (mainOffice == null) {
					transaction.rollback();
					throw new ASException("The MainOffice doesn´t exist");
				}
				tMainOffice = new TMainOffice(mainOffice.getId(), mainOffice.getCity(), mainOffice.getAddress(), mainOffice.isActive());
				transaction.commit();
				em.close();
				emf.close();
			}catch(PersistenceException e){
				throw new ASException("Error in Database");
			}
		}
		else throw new IncorrectInputException("The idMainOffice mustn`t be null and positive");

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
				tMainOfficesList.add(new TMainOffice(mainOffice.getId(), mainOffice.getCity(), mainOffice.getAddress(), mainOffice.isActive()));
			}

			transaction.commit();
			em.close();
			emf.close();
		}catch(PersistenceException e){
			throw new ASException("Error in Database");
		}
		return tMainOfficesList;
	}

	@Override
	public Float showSalary(Integer idMainOffice) throws ASException, IncorrectInputException {
		Float result = 0f;
		if(idMainOffice!=null && idMainOffice > 0 ) {
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
				EntityManager em = emf.createEntityManager();
				EntityTransaction transaction = em.getTransaction();

				transaction.begin();

				Query query = em.createNamedQuery("MainOffice.findByemployee",MainOffice.class);
				query.setParameter("employee", idMainOffice);

				Collection<Employee> employeesList = query.getResultList();

				for(Employee employee: employeesList){
					result+= employee.getSalary();
				}

				transaction.commit();
				em.close();
				emf.close();

			}catch(PersistenceException e){
				throw new ASException("Error in Database");
			}
		}
		else throw new IncorrectInputException("The idMainOffice mustn`t be null and positive");

		return result;
	}



}
