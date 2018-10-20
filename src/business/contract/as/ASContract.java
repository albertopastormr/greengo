package business.contract.as;

import business.contract.TContract;

import java.util.Collection;

public interface ASContract {
    Integer create(TContract contract);

    Integer drop(TContract contract);

    Integer update(TContract contract);

    TContract show (Integer id);

    Collection<TContract> showAll ();

}
