package business.contract.as;

import business.contract.TContract;
import java.util.Collection;

public interface ASContract {

    /*This is related to JPA*/

    Integer create(TContract contract);

    Integer drop(Integer id);

    Integer update(TContract contract);

    TContract show (Integer id);

    Collection<TContract> showAll ();

}
