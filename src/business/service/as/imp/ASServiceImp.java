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

<<<<<<< HEAD
        try {checkTOValuesForCreate(tService);}
        catch (IncorrectInputException e) {
            throw new IncorrectInputException(e.getMessage());
        }
=======
        Integer id;
        if(tService.getId() == null && tService.getType()!=null && tService.getAddress()!=null && tService.getNumVehiclesAttended()!=null && tService.getCapacity() !=null
        && tService.isActive()!=null && tService.getNumVehiclesAttended() >=0 && tService.getCapacity() > 0) {
            try {
                Service serviceObject = new Service(tService);
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17

        Integer id;
        try {
            Service serviceObject = new Service(tService);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

<<<<<<< HEAD
            transaction.begin();

            Query query = em.createNamedQuery("Service.findBytype", Service.class);
            query.setParameter("type", serviceObject.getId());

            List serviceslist = query.getResultList();
            if(!serviceslist.isEmpty()){
                transaction.rollback();
                throw new ASException("ERROR: There is a service with the same type " +
                        "(" + tService.getType() + ") (duplication)");
=======
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
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
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

        return id;
    }

	@Override
	public Integer drop(Integer idService) throws IncorrectInputException, ASException {

        if(idService == null)
            throw new IncorrectInputException("Error: service's id not specified");
        if(idService <= 0)
            throw new IncorrectInputException("Error: service's id must be a positive integer greater than zero");

        Integer id;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Service service = em.find(Service.class, idService);
            if (service == null) {
                transaction.rollback();
                throw new ASException("ERROR: The service doesn`t exist");
            }
            if (!service.getActive()) {
                transaction.rollback();
                throw new ASException("ERROR: The service is already disabled");
            }

            Query query = em.createNamedQuery("Contract.findByservice", Contract.class);
            query.setParameter("service",service);
            List <Contract> contractslist = query.getResultList();

<<<<<<< HEAD
            for (Contract contract : contractslist) {
                if (contract.isActive()) {
                    transaction.rollback();
                    throw new ASException("ERROR: There are active contracts");
=======
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
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
                }
            }
            id = service.getId();
            service.setActive(false);
            transaction.commit();

            em.close();
            emf.close();
            }
            catch (PersistenceException | EclipseLinkException e) {
                throw new ASException(e.getMessage());
            }
        return id;
	}
	
    @Override
    public Integer update(TService tService) throws ASException, IncorrectInputException {
<<<<<<< HEAD

        try {checkTOValuesForUpdate(tService);}
        catch (IncorrectInputException e){throw new IncorrectInputException(e.getMessage());}
=======
        Integer id = null ;
        if(tService.getId() != null && tService.getType() != null && tService.getAddress() != null && tService.getNumVehiclesAttended() != null && tService.getCapacity() != null
                && tService.getNumVehiclesAttended() >= 0 && tService.getCapacity() > 0) {
            try {
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17

        Integer id;
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service serviceBD  = em.find(Service.class,tService.getId());
                if(serviceBD == null){
                    transaction.rollback();
                    throw new ASException("ERROR: The service doesn´t exist");
                }

                Query query = em.createNamedQuery("Service.findBytype",Service.class);
                query.setParameter("type", tService.getType());

                List<Service> typeServiceList = query.getResultList();
                if(!typeServiceList.isEmpty() && !typeServiceList.get(0).getId().equals(serviceBD.getId())){
                    transaction.rollback();
                    throw new ASException("ERROR: There is a service with the same type " +
                            "(" + tService.getType() + ") (duplication)");
                }

                serviceBD.setActive(true);
                serviceBD.setAddress(tService.getAddress());
                serviceBD.setType(tService.getType());
                serviceBD.setNumVehiclesAttended(tService.getNumVehiclesAttended());
                id = serviceBD.getId();

                transaction.commit();
                em.close();
                emf.close();
<<<<<<< HEAD
            }

            catch (PersistenceException | EclipseLinkException e) {
                throw new ASException(e.getMessage());
            }
=======
            } catch (PersistenceException e) {
                throw new ASException("Error in database");
            }
        }
        else throw new IncorrectInputException("Parameters mustn`t be null and their numbers must be positive");

        return id;
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17

        return id;
    }

    //TODO Es necesario que haya transaccion?
    @Override
    public TService show(Integer idService) throws ASException, IncorrectInputException {
        if(idService == null)
            throw new IncorrectInputException("Error: service's id not specified");
        if(idService <= 0)
            throw new IncorrectInputException("Error: service's id must be a positive integer greater than zero");

        TService tService;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

<<<<<<< HEAD
            Service service = em.find(Service.class, idService);
            if (service == null) {
                transaction.rollback();
                throw new ASException("ERROR: The service doesn´t exist");
            }
            tService = new TService(service.getId(), service.getCapacity(), service.getActive(), service.getType(),
                    service.getAddress(), service.getNumVehiclesAttended());
            transaction.commit();
=======
                Service service = em.find(Service.class, idService);
                if (service == null) {
                    transaction.rollback();
                    throw new ASException("The tService doesn´t exist");
                }
                tService = new TService(service.getId(), service.getCapacity(), service.getActive(), service.getType(),
                        service.getAddress(), service.getNumVehiclesAttended());
                transaction.commit();
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17

            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return tService;
    }

    //TODO es necesario hacer transaccion?
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
    public Collection<TService> showServicesFromLevel(Integer level) throws ASException, IncorrectInputException {
<<<<<<< HEAD
        if(level == null)
            throw new IncorrectInputException("level not specified");

        if(level <= 0)
            throw new IncorrectInputException("level must be a positive integer greater than zero");

=======
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
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
                    tServicesList.add(new TService(contract.getService().getId(), contract.getService().getCapacity(),
                            contract.getService().getActive(), contract.getService().getType(),
                            contract.getService().getAddress(), contract.getService().getNumVehiclesAttended()));
                }

                transaction.commit();
                em.close();
                emf.close();
<<<<<<< HEAD

            } catch (PersistenceException | EclipseLinkException e) {
                e.printStackTrace();
                throw new ASException(e.getMessage());
=======
            } catch (Exception e) {
                throw new ASException("Error in Database");
>>>>>>> c872c5b46c12723453f4ea2e2ede5fa4828fda17
            }
        }
        else throw new IncorrectInputException("Parameter mustn`t be null and his number must be positive");
        return tServicesList;
    }

    private void checkTOValuesForCreate(TService tService) throws IncorrectInputException{
        if(tService.getId() != null)
            throw new IncorrectInputException("ID must be null");

        if(tService.getType() == null)
            throw new IncorrectInputException("Type not specified");

        if(tService.getAddress() == null)
            throw new IncorrectInputException("Address not specified");

        if(tService.getNumVehiclesAttended() == null)
            throw new IncorrectInputException("Number of vehicles attended not specified");

        if(tService.getNumVehiclesAttended() < 0)
            throw new IncorrectInputException("Number of vehicles attended must be a " +
                    "positive integer greater than zero");

        if(tService.getCapacity() == null)
            throw new IncorrectInputException("Capacity not specified");

        if(tService.getCapacity() <= 0)
            throw new IncorrectInputException("Capacity must be a " +
                    "positive integer greater than zero");

        if(tService.isActive() == null)
            throw new IncorrectInputException("service state is not specified");
    }

    private void checkTOValuesForUpdate(TService tService) throws IncorrectInputException{
        if(tService.getId() == null)
            throw new IncorrectInputException("ID not specified");

        if(tService.getId() <= 0)
            throw new IncorrectInputException("ID must be a positive integer greater than zero");

        if(tService.getType() == null)
            throw new IncorrectInputException("Type not specified");

        if(tService.getAddress() == null)
            throw new IncorrectInputException("Address not specified");

        if(tService.getNumVehiclesAttended() == null)
            throw new IncorrectInputException("Number of vehicles attended not specified");

        if(tService.getNumVehiclesAttended() < 0)
            throw new IncorrectInputException("Number of vehicles attended must be a " +
                    "positive integer greater than zero");

        if(tService.getCapacity() == null)
            throw new IncorrectInputException("Capacity not specified");

        if(tService.getCapacity() <= 0)
            throw new IncorrectInputException("Capacity must be a " +
                    "positive integer greater than zero");
    }
}
