package business.city.as.imp;

import business.ASException;
import business.city.TCity;
import business.city.as.ASCity;
import business.client.TClient;
import integration.Transaction.Transaction;
import integration.city.factory.DAOCityFactory;
import integration.transactionManager.TransactionManager;

import java.util.Collection;

public class ASCityImp implements ASCity {

    @Override
    public Integer create(TCity city) {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            idc = DAOCityFactory.getInstance().generateDAOCity().create(city);
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TCity tl = DAOCityFactory.getInstance().generateDAOCity().readById(id);
            if (tl != null && tl.isActive()) {//the city exists and is active
                idc = DAOCityFactory.getInstance().generateDAOCity().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                if(tl == null) throw new ASException("The city doesn't exists");
                else if (!tl.isActive()) throw new ASException("The city is already disabled");
            }
        }
        return idc;
    }

    @Override
    public Integer update(TCity city) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            tr.start();
            TCity tl = DAOCityFactory.getInstance().generateDAOCity().readById(city.getId());

            if (tl != null) {//the city exists
                idc = DAOCityFactory.getInstance().generateDAOCity().update(tl);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            } else {
                tr.rollback();
                TransactionManager.getInstance().removeTransaction();
                throw new ASException("The client doesn't exists");
            }
        }
        return idc;
    }

    @Override
    public TCity show(Integer id) throws ASException {
        TCity city = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            city = DAOCityFactory.getInstance().generateDAOCity().readById(id);
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
            if(city == null ) throw new ASException("The client doesn't exists");
        }
        return city;
    }

    @Override
    public Collection<TCity> showAll() {
        Collection<TCity> cityList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            cityList = DAOCityFactory.getInstance().generateDAOCity().readAll();
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return cityList;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) {
        Collection<TClient> clientList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            tr.start();
            clientList = DAOCityFactory.getInstance().generateDAOCity().showClientsByCity(idCity);
            tr.commit();
            TransactionManager.getInstance().removeTransaction();
        }
        return clientList;
    }
}
