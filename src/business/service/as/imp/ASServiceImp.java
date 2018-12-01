package business.service.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.contract.Contract;
import business.service.Service;
import business.service.TService;
import business.service.as.ASService;
import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ASServiceImp implements ASService {

    @Override
    public Integer create(TService tService) throws ASException, IncorrectInputException {

        Integer id;
        if(tService.getId() == null && tService.getType()!=null && tService.getAddress()!=null && tService.getNumVehiclesAttended()!=null && tService.getCapacity() !=null
        && tService.isActive()!=null && tService.getNumVehiclesAttended() >=0 && tService.getCapacity() > 0) {
            try {
                Service serviceObject = new Service(tService);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Query query = em.createNamedQuery("Service.findBytype", Service.class);
                query.setParameter("type", serviceObject.getType());

                List serviceslist = query.getResultList();
                if(!serviceslist.isEmpty()){
                    transaction.rollback();
                    throw new ASException("ERROR: There is a tService with the parameter 'type'= "+tService.getType()+" (duplication)");
                }
                else {
                    em.persist(serviceObject);
                    transaction.commit();
                    id = serviceObject.getId();
                }
                em.close();
                emf.close();
            } catch (PersistenceException | EclipseLinkException e){
                throw new ASException(e.getMessage());
            }
        }
        else{
            throw new IncorrectInputException("ERROR: Parameters can`t be null and their values must be positive integers");
        }
        return id;
    }

	@Override
	public Integer drop(Integer idService) throws IncorrectInputException, ASException {
        Integer id;
        if(idService != null && idService > 0) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service service = em.find(Service.class, idService);

                Query query = em.createNamedQuery("Contract.findByservice", Contract.class);
                query.setParameter("service",service);
                List <Contract> contractslist = query.getResultList();

                if (service == null) {
                    transaction.rollback();
                    throw new ASException("The service doesn`t exist");
                } else if (!service.getActive()) {
                    transaction.rollback();
                    throw new ASException("The service is already disabled");
                }
                for (Contract contract : contractslist) {
                    if (contract.isActive()) {
                        transaction.rollback();
                        throw new ASException("There are contracts activated");
                    }
                }
                id = service.getId();
                service.setActive(false);
                transaction.commit();

                em.close();
                emf.close();

            } catch (PersistenceException | EclipseLinkException e) {
                throw new ASException(e.getMessage());
            }
        }
        else throw new IncorrectInputException("ERROR: Parameters can`t be null and their values must be positive integers");

        return id;
	}
	
    @Override
    public Integer update(TService tService) throws ASException {
        Integer id = null ;
        if(tService.getId() != null && tService.getType() != null && tService.getAddress() != null && tService.getNumVehiclesAttended() != null && tService.getCapacity() != null
                && tService.getNumVehiclesAttended() >= 0 && tService.getCapacity() > 0) {
            try {

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service serviceBD  = em.find(Service.class,tService.getId());

                Query query = em.createNamedQuery("Service.findBytype",Service.class);
                query.setParameter("type", tService.getType());

                List<Service> typeServiceList = query.getResultList();

                if(serviceBD == null){
                    transaction.rollback();
                    throw new ASException("ERROR: The service doesn´t exist");
                }else if(!typeServiceList.isEmpty() && !typeServiceList.get(0).getId().equals(serviceBD.getId())){
                    transaction.rollback();
                    throw new ASException("ERROR: There is a service with the same type (" + tService.getType() + ") (duplication)");
                }

                serviceBD.setActive(true);
                serviceBD.setAddress(tService.getAddress());
                serviceBD.setType(tService.getType());
                serviceBD.setNumVehiclesAttended(tService.getNumVehiclesAttended());
                id = serviceBD.getId();

                transaction.commit();
                em.close();
                emf.close();
            } catch (PersistenceException e) {
                throw new ASException("Error in database");
            }
        }
        else throw new IncorrectInputException("Parameters mustn`t be null and their numbers must be positive");

        return id;

    }

    @Override
    public TService show(Integer idService) throws ASException, IncorrectInputException {
        TService tService;
        if(idService != null && idService > 0) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service service = em.find(Service.class, idService);
                if (service == null) {
                    transaction.rollback();
                    throw new ASException("The tService doesn´t exist");
                }
                tService = new TService(service.getId(), service.getCapacity(), service.getActive(), service.getType(),
                        service.getAddress(), service.getNumVehiclesAttended());
                transaction.commit();

                em.close();
                emf.close();

            } catch (PersistenceException | EclipseLinkException e) {
                throw new ASException(e.getMessage());
            }
        }
        else throw new IncorrectInputException("ERROR: Parameters can`t be null and their values must be positive integers");
        return tService;
    }

    @Override
    public Collection<TService> showAll() throws ASException {
        Collection<TService> tServicesList = new ArrayList<>();
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("Service.findAllServices", Contract.class);

            Collection<Service> servicesList = query.getResultList();
            for(Service service: servicesList){
                tServicesList.add(new TService(service.getId(), service.getCapacity(), service.getActive(),
                        service.getType(), service.getAddress(), service.getNumVehiclesAttended()));
            }

            transaction.commit();
            em.close();
            emf.close();
        }catch(Exception e){
            throw new ASException("Error in Database");
        }

        return tServicesList;
    }

    @Override
    public Collection<TService> showServicesFromLevel(Integer level) throws ASException {
        Collection<TService> tServicesList = new ArrayList<>();
        if(level != null && level > 0) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Query query = em.createNamedQuery("Contract.findByservice_level", Contract.class);
                query.setParameter("serviceLevel", level);

                List<Contract> listContracts = query.getResultList();
                Collection<Service> servicesList = new ArrayList<>();

                for (Contract contract : listContracts) {
                    tServicesList.add(new TService(contract.getService().getId(), contract.getService().getCapacity(), contract.getService().getActive(),
                            contract.getService().getType(), contract.getService().getAddress(), contract.getService().getNumVehiclesAttended()));
                }

                transaction.commit();
                em.close();
                emf.close();
            } catch (Exception e) {
                throw new ASException("Error in Database");
            }
        }
        else throw new IncorrectInputException("Parameter mustn`t be null and his number must be positive");
        return tServicesList;
    }
}
