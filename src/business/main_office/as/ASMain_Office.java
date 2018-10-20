package business.main_office.as;

import business.main_office.TMain_Office;

import java.util.Collection;

public interface  ASMain_Office {
    Integer create(TMain_Office main_office);

    Integer drop(TMain_Office main_office);

    Integer update(TMain_Office main_office);

    TMain_Office show (Integer id);

    Collection<TMain_Office> showAll ();

    float showSalary(Integer id);
}
