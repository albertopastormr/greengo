package business.contract.as;

import business.ASException;
import business.IncorrectInputException;
import business.contract.ContractId;
import business.contract.TContract;
import java.util.Collection;

public interface ASContract {

    /*This is related to JPA*/

    ContractId create(TContract contract) throws ASException, IncorrectInputException;

    ContractId drop(Integer mainOfficeId, Integer serviceId)  throws ASException, IncorrectInputException;

    ContractId update(TContract contract)  throws ASException, IncorrectInputException;

    TContract show (Integer mainOfficeId, Integer serviceId)  throws ASException, IncorrectInputException;

    Collection<TContract> showAll ()  throws ASException;

}
