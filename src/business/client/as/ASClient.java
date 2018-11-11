package business.client.as;

import business.ASException;
import business.IncorrectInputException;
import business.client.TClient;
import org.xml.sax.SAXException;

import java.util.Collection;

public interface ASClient {
    Integer create(TClient client) throws ASException, IncorrectInputException;

    Integer drop(Integer client) throws ASException, IncorrectInputException;

    Integer update(TClient client) throws ASException, IncorrectInputException;

    TClient show (Integer id) throws ASException, IncorrectInputException;

    Collection<TClient> showAll () throws ASException;

    Collection<TClient> showAllWithMoreThanNRentals (Integer N) throws ASException, IncorrectInputException;

}
