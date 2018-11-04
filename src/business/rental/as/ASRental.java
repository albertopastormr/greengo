package business.rental.as;

import business.rental.TRental;
import business.rental.TRentalDetails;

import java.util.Collection;

public interface ASRental {
    Integer create(TRental rental);

    Integer drop(Integer rental);

    Integer update(TRental rental);

    TRentalDetails show (Integer id);

    Collection<TRentalDetails> showAll ();
}
