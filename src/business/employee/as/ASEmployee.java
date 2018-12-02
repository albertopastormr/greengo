package business.employee.as;

import business.ASException;
import business.IncorrectInputException;
import business.employee.TEmployee;

import java.util.Collection;

public interface ASEmployee {

    Integer create(TEmployee client) throws IncorrectInputException, ASException;

    Integer drop(Integer id);

    Integer update(TEmployee client);

    TEmployee show (Integer id);

    Collection<TEmployee> showAll ();

    Integer setSalary (Integer idEmployee, Float salary);
}
