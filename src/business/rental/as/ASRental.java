package business.rental.as;

import business.ASException;
import business.IncorrectInputException;
import business.rental.TRental;
import business.rental.TRentalDetails;
import integration.DAOException;

import java.util.Collection;

public interface ASRental {
    Integer create(TRental rental) throws ASException, IncorrectInputException;

    Integer drop(Integer rental) throws ASException, IncorrectInputException;

    Integer update(TRental rental) throws ASException, IncorrectInputException;

    TRentalDetails show (Integer id) throws ASException, IncorrectInputException;

    Collection<TRentalDetails> showAll () throws ASException;

    TRentalDetails getRentalDetails(Integer idRental) throws ASException, DAOException, IncorrectInputException;

    Collection<TRentalDetails> getAllRentalsDetails() throws ASException, DAOException, IncorrectInputException;
}
