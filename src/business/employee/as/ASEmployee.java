package business.employee.as;

import business.ASException;
import business.IncorrectInputException;
import business.employee.TEmployee;

import java.util.Collection;

public interface ASEmployee {

    Integer create(TEmployee tEmployee) throws IncorrectInputException, ASException;

    Integer drop(Integer id) throws IncorrectInputException, ASException;

    Integer update(TEmployee tEmployee) throws IncorrectInputException, ASException;

    TEmployee show (Integer id) throws IncorrectInputException, ASException;

    Collection<TEmployee> showAll () throws ASException;
}
