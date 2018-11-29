package business.service.as.imp;

import business.service.Service;
import business.service.TService;
import business.service.as.ASService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;

public class ASServiceImp implements ASService {

    @Override
    public Integer create(TService service) {


        //estructura general para enseñar a antonio, faltan reglas de negocio
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("greengo");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        Service objectBussinesService = new Service(service);

        try{
            em.persist(objectBussinesService);
            et.commit();
        }catch(Exception e){
            et.rollback();
        }
        em.close();
        emf.close();

        return objectBussinesService.getId();

    }

	@Override
	public Integer drop(Integer service) {
		return null;
	}
	
    @Override
    public Integer update(TService service) {
        return null;
    }

    @Override
    public TService show(Integer id) {
        return null;
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
