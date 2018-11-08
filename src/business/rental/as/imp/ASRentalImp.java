package business.rental.as.imp;

import business.ASException;
import business.client.TClient;
import business.client.factory.ASClientFactory;
import business.rental.TOA.TOARental;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.rental.as.ASRental;
import business.vehicle.TVehicle;
import business.vehicle.factory.ASVehicleFactory;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.client.factory.DAOClientFactory;
import integration.rental.dao.DAORental;
import integration.rental.factory.DAORentalFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ASRentalImp implements ASRental {
    @Override
    public Integer create(TRental rental) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            try{
                TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());
                TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));

                //TODO NO PUEDE DARSE DE ALTA UN ALQUILER CON FECHAS EN LAS QUE EL VEHICULO O EL CLIENTE TENGA OTRO

                if (tc != null && tv !=null && tc.isActive() && tv.isActive()) {//the client and vehicle exists and are active
                    idr = DAORentalFactory.getInstance().generateDAORental().create(rental);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {//exists
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    if(tc == null || tv == null) throw new ASException("Vehicle or Client doesn't exists");
                    if(!tc.isActive() ||  !tv.isActive()) throw new ASException("Vehicle or Client is  disabled");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return idr;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try{
                tr.start();
                TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(id);
                if (tl != null && tl.isActive()) {//the rental exists and is active
                    tl.setActive(false);
                    idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    if(tl == null) throw new ASException("The rental doesn't exists");
                    else if (!tl.isActive()) throw new ASException("The rental is disabled");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return idr;
    }

    @Override
    public Integer update(TRental rental) throws ASException {
        Integer idr =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        //TODO NO PUEDE MODIFICARSE UN ALQUILER SI LAS FECHAS NUEVAS OCUPADAS YA POR ESE VEHICULO O CLIENTE

        if(tr!=null) {
            try{
                tr.start();
                TRental tl = DAORentalFactory.getInstance().generateDAORental().readById(rental.getId());
                TVehicle tv = DAOVehicleFactory.getInstance().generateDAOVehicle().readById((rental.getIdVehicle()));
                TClient tc = DAOClientFactory.getInstance().generateDAOClient().readById(rental.getIdClient());

                if (tl != null && tv!= null && tc!=null && tv.isActive() && tc.isActive()) {//the rental exists, the client and vehicle are actived and exists
                    idr = DAORentalFactory.getInstance().generateDAORental().update(tl);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    if(tl == null) throw new ASException("The rental doesn't exists");
                    else if(tv == null || tc == null) throw  new ASException("The client or vehicle doesn't exists");
                    else if (!tv.isActive() || !tc.isActive())  throw  new ASException("The client or vehicle is disabled");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
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
            try{
                tr.start();
                rental = DAORentalFactory.getInstance().generateDAORental().readById(id);
                if(rental!=null) {//if rental exists
                    rDetails = getRentalDetails(id);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                }
                else {//doesn't exists
                    TransactionManager.getInstance().removeTransaction();
                    throw new ASException("The rental doesn't exists");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return rDetails;
    }

    @Override
    public Collection<TRentalDetails> showAll() throws ASException {
        Collection<TRentalDetails> rDetailsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            try{
                tr.start();
                rDetailsList = getAllRentalsDetails();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return rDetailsList;
    }

    public TRentalDetails getRentalDetails(Integer idRental) throws ASException {
        TRental rental = DAORentalFactory.getInstance().generateDAORental().readById(idRental);
        TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
        TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();

        return new TRentalDetails(client,rental,vehicle);
    }

    public Collection<TRentalDetails> getAllRentalsDetails() throws ASException {
        Collection<TRental> rentals = DAORentalFactory.getInstance().generateDAORental().readAll();
        Collection<TRentalDetails> details = new ArrayList<>();

        for(TRental rental : rentals){
            TClient client = ASClientFactory.getInstance().generateASClient().show(rental.getIdClient());
            TVehicle vehicle = ASVehicleFactory.getInstance().generateASVehicle().show(rental.getIdVehicle()).getVehicle();
            details.add(new TRentalDetails(client,rental,vehicle));
        }

        return details;
    }
}
