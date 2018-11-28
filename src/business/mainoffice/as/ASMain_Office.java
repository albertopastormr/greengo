package business.mainoffice.as;

import business.mainoffice.TMainOffice;

import java.util.Collection;

public interface ASMain_Office {

    /*This is related to JPA*/

   Integer create(TMainOffice mainoffice);

    Integer drop(Integer id);

    Integer update(TMainOffice mainoffice);

    TMainOffice show (Integer id);

    Collection<TMainOffice> showAll ();

    float showSalary(Integer id);
}
