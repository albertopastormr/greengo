package business.service.as;

import business.service.TService;

import java.util.Collection;

public interface ASService {
    Integer create(TService service);

    Integer drop(Integer service);

    Integer update(TService service);

    TService show (Integer id);

    Collection<TService> showAll ();

    Collection<TService> showServicesFromLevel(Integer level);

}
