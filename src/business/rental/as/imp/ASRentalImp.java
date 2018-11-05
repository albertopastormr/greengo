package business.rental.as.imp;

import business.ASException;
import business.client.TClient;
import business.rental.TOA.TOARental;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.rental.as.ASRental;
import business.vehicle.TVehicle;
import integration.Transaction.Transaction;
import integration.client.factory.DAOClientFactory;
import integration.rental.dao.DAORental;
import integration.rental.factory.DAORentalFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.Collection;

public class ASRentalImp implements ASRental {
    @Override
    public Integer create(TRental rental) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());
            TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));
            if (tc != null && tv !=null && tc.isActive() && tv.isActive()) {//the client and vehicle exists and are active
                idr = DAORentalFactory.getInstance().generateDAORental().create(rental);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {//exists
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                if(tc == null || tv == null) throw new ASException("Vehicle or Client doesn't exists");
                if(!tc.isActive() ||  !tv.isActive()) throw new ASException("Vehicle or Client is disabled");
            }
        }
        return idr;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(id);
            if (tl != null && tl.isActive()) {//the rental exists and is active
                idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                if(tl == null) throw new ASException("The rental doesn't exists");
                else if (!tl.isActive()) throw new ASException("The rental is already disabled");
            }
        }
        return idr;
    }

    @Override
    public Integer update(TRental rental) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(rental.getId());

            if (tl != null) {//the rental exists
                idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                throw new ASException("The rental doesn't exists");
            }
        }
        return idr;
    }

    @Override
    public TRentalDetails show(Integer id) throws ASException {
        TRental rental;
        TRentalDetails rDetails = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            rental = DAORentalFactory.getInstance().generateDAORental().readById(id);
            if(rental!=null) {//if rental exists
                TOARental toaRental = new TOARental();
                rDetails = toaRental.getRentalDetails(rental.getId());
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }
            else {//doesn't exists
                TransactionManager.getInstance().removeTransaction();
                throw new ASException("The client doesn't exists");
            }
        }
        return rDetails;
    }

    @Override
    public Collection<TRentalDetails> showAll() throws ASException {
        Collection<TRentalDetails> rDetailsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            TOARental toaRental = new TOARental();
            rDetailsList = toaRental.getAllRentalsDetails();
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return rDetailsList;
    }
}
