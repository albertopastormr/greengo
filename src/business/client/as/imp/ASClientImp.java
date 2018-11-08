package business.client.as.imp;

import business.ASException;
import business.client.TClient;
import business.client.as.ASClient;
import business.rental.TRental;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.client.factory.DAOClientFactory;
import integration.rental.factory.DAORentalFactory;
import integration.transactionManager.TransactionManager;

import java.util.Collection;

public class ASClientImp implements ASClient {
    @Override
    public Integer create(TClient client) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try{
            tr.start();
            TClient tl = DAOClientFactory.getInstance().generateDAOClient().readByIdCard(client.getIdCardNumber());//search other client with same idCard
            if (tl == null) {//don't exists
                idc = DAOClientFactory.getInstance().generateDAOClient().create(client);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {//exists
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                throw new ASException("Other client exists with the same idCard");
            }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try{
                tr.start();
                TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(id);
                Collection<TRental> listRentals = DAORentalFactory.getInstance().generateDAORental().showRentalsByClient(id);
                boolean activeRentals = false;

                for (TRental rental : listRentals) // If exists rentals actived couldn't drop client
                    if (rental.isActive())
                        activeRentals = true;

                if (tl != null && tl.isActive() && !activeRentals) {//the client exists and is active, and the client hasn't got active rentals
                    idc = DAOClientFactory.getInstance().generateDAOClient().update(tl);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    if(tl == null) throw new ASException("The client doesn't exists");
                    else if (!tl.isActive()) throw new ASException("The client is already disabled");
                    else if (!activeRentals)  throw new ASException("The client has got enabled rentals");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return idc;
    }

    @Override
    public Integer update(TClient client) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try{
                tr.start();
                TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(client.getId());
                TClient tn = DAOClientFactory.getInstance().generateDAOClient().readByIdCard(client.getIdCardNumber());

                if (tl != null && tn == null  ) {//the client exists and don't exists other client with the same idCard
                    idc = DAOClientFactory.getInstance().generateDAOClient().update(tl);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else {
                    tr.rollback();
                    TransactionManager.getInstance().removeTransaction();
                    if(tl == null) throw new ASException("The client doesn't exists");
                    else throw new ASException("Exists other client with the same idCard");
                }
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return idc;
    }

    @Override
    public TClient show(Integer id) throws ASException {
        TClient client = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            try{
                tr.start();
                client = DAOClientFactory.getInstance().generateDAOClient().readById(id);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
                if(client== null ) throw new ASException("The client doesn't exists");
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return client;
    }

    @Override
    public Collection<TClient> showAll() throws ASException {
        Collection<TClient> clientsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
                try{
                tr.start();
                clientsList = DAOClientFactory.getInstance().generateDAOClient().readAll();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return clientsList;
    }

    @Override
    public Collection<TClient> showAllWithMoreThanNRentals(Integer N) throws ASException {
        Collection<TClient> clientsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            try{
                tr.start();
                clientsList = DAOClientFactory.getInstance().generateDAOClient().readByNRentals(N);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        return clientsList;
    }
}
