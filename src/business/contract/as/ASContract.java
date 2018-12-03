package business.contract.as;

import business.ASException;
import business.IncorrectInputException;
import business.contract.TContract;
import java.util.Collection;

public interface ASContract {

    /*This is related to JPA*/

    Integer create(TContract contract) throws ASException, IncorrectInputException;

    Integer drop(Integer id)  throws ASException, IncorrectInputException;

    Integer update(TContract contract)  throws ASException, IncorrectInputException;

    TContract show (Integer id)  throws ASException, IncorrectInputException;

    Collection<TContract> showAll ()  throws ASException;

}
