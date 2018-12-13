package business.employee.as;

import business.ASException;
import business.IncorrectInputException;
import business.employee.TEmployee;

import java.util.Collection;

public interface ASEmployee {

    Integer create(TEmployee client) throws IncorrectInputException, ASException;

    Integer drop(Integer id) throws IncorrectInputException, ASException;

    Integer update(TEmployee client) throws IncorrectInputException, ASException;

    TEmployee show (Integer id) throws IncorrectInputException, ASException;

    Collection<TEmployee> showAll () throws ASException;
}
