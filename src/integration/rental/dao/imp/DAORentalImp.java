package integration.rental.dao.imp;

import business.client.TClient;
import business.rental.TRental;
import integration.rental.dao.DAORental;

import java.util.Collection;
import java.util.Date;

public class DAORentalImp implements DAORental {
    @Override
    public Integer create(TRental rental) {
        return null;
    }

    @Override
    public Integer update(TRental rental) {
        return null;
    }

    @Override
    public TRental readById(Integer id) {
        return null;
    }

    @Override
    public Collection<TRental> showRentalsByClient(Integer id) {
        return null;
    }

    @Override
    public Collection<TRental> showRentalsByVehicle(Integer id) {
        return null;
    }

    @Override
    public Collection<TRental> readAll() {
        return null;
    }

    @Override
    public Boolean checkAvaiableDates(TRental rental) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
