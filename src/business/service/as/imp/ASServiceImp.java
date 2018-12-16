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
        checkValuesForCreate(tService);

        try {

            Service serviceObject = new Service(tService);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("Service.findBytype", Service.class);
            query.setParameter("type", serviceObject.getType());

            List serviceList = query.getResultList();
            if (!serviceList.isEmpty()) {
                transaction.rollback();
                throw new ASException("ERROR: There is a service with the same type " +
                        "(" + tService.getType() + ") (duplication)");
            }

            em.persist(serviceObject);
            transaction.commit();
            id = serviceObject.getId();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return id;
    }

    @Override
    public Integer drop(Integer idService) throws IncorrectInputException, ASException {

        if (idService == null)
            throw new IncorrectInputException("ERROR: service's id not specified");
        if (idService <= 0)
            throw new IncorrectInputException("ERROR: service's id must be a positive integer greater than zero");

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
            if (!service.isActive()) {
                transaction.rollback();
                throw new ASException("ERROR: The service is already disabled");
            }

            Query query = em.createNamedQuery("Contract.findByservice", Contract.class);
            query.setParameter("service", service);
            List<Contract> contractslist = query.getResultList();

            for (Contract contract : contractslist) {
                if (contract.isActive()) {
                    transaction.rollback();
                    throw new ASException("There are active contracts");
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
        return id;
    }

    @Override
    public Integer update(TService tService) throws ASException, IncorrectInputException {
        Integer id;
        checkValuesForUpdate(tService);

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Service serviceBD = em.find(Service.class, tService.getId());
            if (serviceBD == null) {
                transaction.rollback();
                throw new ASException("ERROR: The service doesn´t exist");
            }

            Query query = em.createNamedQuery("Service.findBytype", Service.class);
            query.setParameter("type", tService.getType());

            List<Service> typeServiceList = query.getResultList();
            if (!typeServiceList.isEmpty() && !typeServiceList.get(0).getId().equals(serviceBD.getId())) {
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

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }
        return id;
    }

    @Override
    public TService show(Integer idService) throws ASException, IncorrectInputException {
        if (idService == null)
            throw new IncorrectInputException("Error: service's id not specified");
        if (idService <= 0)
            throw new IncorrectInputException("Error: service's id must be a positive integer greater than zero");

        TService tService;
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Service service = em.find(Service.class, idService);
            if (service == null) {
                transaction.rollback();
                throw new ASException("ERROR: The service doesn´t exist");
            }
            tService = new TService(service.getId(), service.getCapacity(), service.isActive(), service.getType(),
                    service.getAddress(), service.getNumVehiclesAttended());

            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }
        return tService;
    }

    @Override
    public Collection<TService> showAll() throws ASException {
        Collection<TService> tServicesList = new ArrayList<>();
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("Service.findByActive", Contract.class);
            query.setParameter("active", true);

            Collection<Service> servicesList = query.getResultList();
            for (Service service : servicesList) {
                tServicesList.add(new TService(service.getId(), service.getCapacity(), service.isActive(),
                        service.getType(), service.getAddress(), service.getNumVehiclesAttended()));
            }

            transaction.commit();
            em.close();
            emf.close();
        } catch (PersistenceException | EclipseLinkException e) {
            throw new ASException(e.getMessage());
        }

        return tServicesList;
    }


    @Override
    public Collection<TService> showServicesByLevel(Integer level) throws ASException, IncorrectInputException {

        if (level == null)
            throw new IncorrectInputException("level not specified");

        if (level <= 0)
            throw new IncorrectInputException("level must be a positive integer greater than zero");

        Collection<TService> tServicesList = new ArrayList<>();
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Query query = em.createNamedQuery("Contract.findByservice_level", Contract.class);
            query.setParameter("serviceLevel", level);

            List<Contract> listContracts = query.getResultList();

            for (Contract contract : listContracts) {
                TService newService = new TService(contract.getService().getId(), contract.getService().getCapacity(),
                        contract.getService().isActive(), contract.getService().getType(),
                        contract.getService().getAddress(), contract.getService().getNumVehiclesAttended());
                if(!tServicesList.contains(newService))
                    tServicesList.add(newService);
            }

            transaction.commit();
            em.close();
            emf.close();

        } catch (PersistenceException | EclipseLinkException e) {
            e.printStackTrace();
            throw new ASException(e.getMessage());
        }

        return tServicesList;
    }

    private static void checkValuesForCreate(TService tService) throws IncorrectInputException {
        if (tService.getType() == null) throw new IncorrectInputException("Type not specified");
        if (tService.getAddress() == null) throw new IncorrectInputException("Address not specified");
        if (tService.getNumVehiclesAttended() == null) throw new IncorrectInputException("Number of vehicles " +
                "attended not specified");
        if (tService.getNumVehiclesAttended() < 0)throw new IncorrectInputException("Number of vehicles attended" +
                " must be a positive integer greater than zero");
        if (tService.getCapacity() == null) throw new IncorrectInputException("Capacity not specified");
        if (tService.getCapacity() <= 0) throw new IncorrectInputException("Capacity must be a positive" +
                " integer greater than zero");
        if (tService.isActive() == null) throw new IncorrectInputException("service state is not specified");
    }

    private static void checkValuesForUpdate(TService tService) throws IncorrectInputException {
        if (tService.getId() == null)
            throw new IncorrectInputException("ID not specified");

        if (tService.getId() <= 0) throw new IncorrectInputException("ID must be a positive integer greater than zero");

        if (tService.getType() == null) throw new IncorrectInputException("Type not specified");

        if (tService.getAddress() == null) throw new IncorrectInputException("Address not specified");

        if (tService.getNumVehiclesAttended() == null) throw new IncorrectInputException("Number of vehicles" +
                " attended not specified");

        if (tService.getNumVehiclesAttended() < 0) throw new IncorrectInputException("Number of vehicles attended " +
                "must be a positive integer greater than zero");

        if (tService.getCapacity() == null) throw new IncorrectInputException("Capacity not specified");

        if (tService.getCapacity() <= 0) throw new IncorrectInputException("Capacity must be a " +
                    "positive integer greater than zero");

        if (!tService.isActive()) throw new IncorrectInputException("Active must be true");
    }
}
