package business.client.as.imp;

import business.ASException;
import business.client.TClient;
import business.client.as.ASClient;
import integration.Transaction.Transaction;
import integration.client.factory.DAOClientFactory;
import integration.transactionManager.TransactionManager;

import java.util.Collection;

public class ASClientImp implements ASClient {
    @Override
    public Integer create(TClient client) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
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
        }
        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(id);
            if (tl != null && tl.isActive()) {//the client exists and is active
                idc = DAOClientFactory.getInstance().generateDAOClient().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                if(tl == null) throw new ASException("The client doesn't exists");
                else if ( tl!= null && !tl.isActive()) throw new ASException("The client is already disabled");
            }
        }
        return idc;
    }

    @Override
    public Integer update(TClient client) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(client.getId());
            TClient tn = DAOClientFactory.getInstance().generateDAOClient().readByIdCard(client.getIdCardNumber());

            if (tl != null && tn == null  ) {//the client exists and dont exists other client with the same idCard
                idc = DAOClientFactory.getInstance().generateDAOClient().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                if(tl == null) throw new ASException("The client doesn't exists");
                else if ( tl!= null && tn != null ) throw new ASException("Exists other client with the same idCard");
            }
        }
        return idc;
    }

    @Override
    public TClient show(Integer id) throws ASException {
        TClient client = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            client = DAOClientFactory.getInstance().generateDAOClient().readById(id);
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
            if(client== null ) throw new ASException("The client doesn't exists");
        }
        return client;
    }

    @Override
    public Collection<TClient> showAll(){
        Collection<TClient> clientsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            clientsList = DAOClientFactory.getInstance().generateDAOClient().readAll();
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return clientsList;
    }

    @Override
    public Collection<TClient> showAllWithMoreThanNRentals(Integer N) {
        Collection<TClient> clientsList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            clientsList = DAOClientFactory.getInstance().generateDAOClient().readByNRentals(N);
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return clientsList;
    }
}
