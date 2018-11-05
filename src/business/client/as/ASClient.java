package business.client.as;

import business.ASException;
import business.client.TClient;
import org.xml.sax.SAXException;

import java.util.Collection;

public interface ASClient {
    Integer create(TClient client) throws ASException;

    Integer drop(Integer client) throws ASException;

    Integer update(TClient client) throws ASException;

    TClient show (Integer id) throws ASException;

    Collection<TClient> showAll ();

    Collection<TClient> showAllWithMoreThanNRentals (Integer N);

}
