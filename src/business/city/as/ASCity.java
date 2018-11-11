package business.city.as;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.client.TClient;

import java.util.Collection;

public interface ASCity {
    Integer create(TCity city) throws ASException, IncorrectInputException;

    Integer drop(Integer city) throws ASException, IncorrectInputException;

    Integer update(TCity city) throws ASException, IncorrectInputException;

    TCity show (Integer id) throws ASException, IncorrectInputException;

    Collection<TCity> showAll () throws ASException;

    Collection<TClient> showClientsByCity (Integer idCity) throws ASException, IncorrectInputException;
}
