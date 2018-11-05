package business.rental.as;

import business.ASException;
import business.rental.TRental;
import business.rental.TRentalDetails;

import java.util.Collection;

public interface ASRental {
    Integer create(TRental rental) throws ASException;

    Integer drop(Integer rental) throws ASException;

    Integer update(TRental rental) throws ASException;

    TRentalDetails show (Integer id) throws ASException;

    Collection<TRentalDetails> showAll () throws ASException;
}
