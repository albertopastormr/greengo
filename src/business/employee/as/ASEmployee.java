package business.employee.as;

import business.employee.TEmployee;

import java.util.Collection;

public interface ASEmployee {

    Integer create(TEmployee client);

    Integer drop(TEmployee client);

    Integer update(TEmployee client);

    TEmployee show (Integer id);

    Collection<TEmployee> showAll ();

    Integer setSalary (Integer idEmployee, Integer salary);
}
