package business.rental.as;

import business.rental.TRental;

import java.util.Collection;

public interface ASRental {
    Integer create(TRental rental);

    Integer drop(TRental rental);

    Integer update(TRental rental);

    TRental show (Integer id);

    Collection<TRental> showAll ();
}
