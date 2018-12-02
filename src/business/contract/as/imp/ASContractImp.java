package business.contract.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.contract.Contract;
import business.contract.TContract;
import business.contract.as.ASContract;
import business.mainoffice.MainOffice;
import business.service.Service;
import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASContractImp implements ASContract {
    /*This is related to JPA*/

    @Override
    public Integer create(TContract tContract) throws ASException, IncorrectInputException {
        Integer id;
        checkValuesToCreate(tContract);

        try{

            Contract contractObject = new Contract(tContract);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            Query serviceQuery = em.createNamedQuery("Service.findByid", Service.class);
            serviceQuery.setParameter("id", tContract.getIdService());

            Query mainOfficeQuery = em.createNamedQuery("MainOffice.findByid", MainOffice.class);
            mainOfficeQuery.setParameter("id", tContract.getIdMainOffice());


            List<Service> serviceList = serviceQuery.getResultList();
            List<MainOffice> mainOfficeList = mainOfficeQuery.getResultList();

            if(serviceList.isEmpty()){
                transaction.rollback();
                throw new ASException("ERROR: The service doesn't exist");
            }
            else if(!serviceList.get(0).isActive()){
                transaction.rollback();
                throw new ASException("ERROR: The service is already disabled");
            }

            if(mainOfficeList.isEmpty()){
                transaction.rollback();
                throw new ASException("ERROR: The main office doesn't exist");
            }
            else if(!mainOfficeList.get(0).isActive()){
                transaction.rollback();
                throw new ASException("ERROR: The main office is already disabled");
            }

            contractObject.setMainOffice(mainOfficeList.get(0));
            contractObject.setService(serviceList.get(0));

            em.persist(contractObject);
            transaction.commit();
            id = contractObject.getId();

            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }

        return id;
    }

    @Override
    public Integer drop(Integer id) throws ASException, IncorrectInputException {
        Integer idRet;

        if(id == null) throw new IncorrectInputException("Id field can't be empty");
        if(id <= 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            Contract contract = em.find(Contract.class, id);

            if(contract == null){
                transaction.rollback();
                throw new ASException("The contract doesn't exist");
            }
            else if(!contract.isActive()){
                transaction.rollback();
                throw new ASException("The contract is already disabled");
            }

            idRet = contract.getId();
            contract.setActive(false);
            transaction.commit();

            em.close();
            emf.close();

            return idRet;

        }
        catch (PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }
    }

    @Override
    public Integer update(TContract contract) {
        return null;
    }

    @Override
    public TContract show(Integer id) throws ASException, IncorrectInputException{
        if(id == null) throw new IncorrectInputException("Id field can't be empty");
        if(id <= 0) throw new IncorrectInputException("Id field must be a positive integer greater than zero");

        TContract tContract;

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Contract contract = em.find(Contract.class, id);
            if(contract == null){
                transaction.rollback();
                throw new ASException("The contract doesn't exist");
            }
            tContract = new TContract(contract.getId(), contract.getServiceLevel(),
                    contract.getMainOffice().getId(), contract.getService().getId(), contract.isActive());

            transaction.commit();
            em.close();
            emf.close();
        } catch(PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }

        return tContract;

    }

    //TODO es necesario hacer transaccion?
    @Override
    public Collection<TContract> showAll() throws ASException {
        Collection<TContract> tContractList = new ArrayList<>();
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            Query query = em.createNamedQuery("Contract.findAllContracts", Contract.class);

            Collection<Contract> contractList = query.getResultList();

            for(Contract c: contractList){
                tContractList.add(new TContract(c.getId(), c.getServiceLevel(),
                        c.getMainOffice().getId(), c.getService().getId(), c.isActive()));
            }

            transaction.commit();
            em.close();
            emf.close();
        }
        catch(PersistenceException | EclipseLinkException e){
            throw new ASException(e.getMessage());
        }

        return tContractList;
    }

    private static void checkValuesToCreate(TContract contract) throws IncorrectInputException {
        if(contract.getId() != null) throw new IncorrectInputException("Id field must be empty");
        if(contract.getServiceLevel() == null) throw new IncorrectInputException("Service level field can't be empty");
        if(contract.getServiceLevel() < 0) throw new IncorrectInputException("Service level field must be a positive integer");
        if(contract.getIdMainOffice() == null) throw new IncorrectInputException("Id main office field can't be empty");
        if(contract.getIdMainOffice() <= 0) throw new IncorrectInputException("Id main office must be a positive integer greater than zero");
        if(contract.getIdService() == null) throw new IncorrectInputException("Id service field can't be empty");
        if(contract.getIdService() <= 0) throw new IncorrectInputException("Id service field must be a positive integer greater than zero");
        if(contract.isActive() == null) throw new IncorrectInputException("Active field can't be empty");
    }

}
