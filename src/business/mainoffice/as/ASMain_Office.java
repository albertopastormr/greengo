package business.mainoffice.as;

import business.ASException;
import business.IncorrectInputException;
import business.mainoffice.TMainOffice;

import java.util.Collection;

public interface ASMain_Office {

    /*This is related to JPA*/

   Integer create(TMainOffice mainoffice) throws IncorrectInputException, ASException;

    Integer drop(Integer id) throws ASException, IncorrectInputException;

    Integer update(TMainOffice mainoffice) throws ASException, IncorrectInputException;

    TMainOffice show (Integer id) throws ASException, IncorrectInputException;

    Collection<TMainOffice> showAll () throws ASException;

    Float showSalary(Integer id) throws ASException, IncorrectInputException;
}
