package business.service.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.contract.Contract;
import business.service.Service;
import business.service.TService;
import business.service.as.ASService;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class ASServiceImp implements ASService {

    @Override
    public Integer create(TService service) throws ASException, IncorrectInputException {

        Integer id;
        if(service.getId() == null && service.getType()!=null && service.getAddress()!=null && service.getNumVehiclesAttended()!=null && service.getCapacity() !=null
        && service.isActive()!=null && service.getNumVehiclesAttended() >=0 && service.getCapacity() > 0) {
            try {
                Service serviceObject = new Service(service);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Query query = em.createNamedQuery("Service.findBytype", Service.class);
                query.setParameter("type", serviceObject.getId());

                List serviceslist = query.getResultList();
                if(!serviceslist.isEmpty()){
                    transaction.rollback();
                    throw new ASException("ERROR: There is a client with the parameter 'idCard'==\"+client.getIdCardNumber()+\" (duplication)");
                }
                else{
                    em.persist(serviceObject);
                    transaction.commit();
                    id = serviceObject.getId();
                }
                em.close();
                emf.close();
            }catch(Exception e){
                throw new ASException(e.getMessage());
            }
        }
        else{
            throw new IncorrectInputException("Parameters mustn`t be null and their  numbers must be positive");
        }
        return id;
    }

	@Override
	public Integer drop(Integer idService) throws IncorrectInputException, ASException {
        Integer id;
        if(idService != null && idService >0) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service service = em.find(Service.class, idService);

                Query query = em.createNamedQuery("Contract.findByservice", Contract.class);
                query.setParameter("service", idService);
                List <Contract>contractslist = query.getResultList();

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
            } catch (Exception e) {
                throw new ASException(e.getMessage());
            }
        }
        else throw new IncorrectInputException("Id parameter mustn`t be null and must be positive");
        return id;
	}
	
    @Override
    public Integer update(TService service) {
        return null;
    }

    @Override
    public TService show(Integer idService) throws ASException, IncorrectInputException {
        TService tService;
        if(idService!= null && idService > 0) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                Service service = em.find(Service.class, idService);
                if (service == null) {
                    transaction.rollback();
                    throw new ASException("The service doesn´t exist");
                }
                tService = new TService(service.getId(), service.getCapacity(), service.getActive(), service.getType(), service.getAddress(), service.getNumVehiclesAttended());
                em.close();
                emf.close();
            } catch (Exception e) {
                throw new ASException(e.getMessage());
            }
        }
        else throw new IncorrectInputException("The id mustn`t be null and positive");
        return tService;
    }

    @Override
    public Collection<TService> showAll() {
        return null;
    }

    @Override
    public Collection<TService> showServicesFromLevel(Integer level) {
        return null;
    }
}
