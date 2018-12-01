package business.service.as;

import business.ASException;
import business.IncorrectInputException;
import business.service.TService;

import java.util.Collection;

public interface ASService {
    Integer create(TService service) throws ASException, IncorrectInputException;

    Integer drop(Integer service) throws IncorrectInputException, ASException;

    Integer update(TService service) throws ASException;

    TService show (Integer id) throws ASException, IncorrectInputException;

    Collection<TService> showAll () throws ASException;

    Collection<TService> showServicesFromLevel(Integer level) throws ASException;

}
