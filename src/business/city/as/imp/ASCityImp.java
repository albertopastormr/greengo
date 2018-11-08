package business.city.as.imp;

import business.ASException;
import business.city.TCity;
import business.city.as.ASCity;
import business.client.TClient;
import business.vehicle.TVehicle;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.city.factory.DAOCityFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.dao.DAOVehicle;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.Collection;

public class ASCityImp implements ASCity {

    @Override
    public Integer create(TCity city) throws ASException {
        Integer idc = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try {
                tr.start();
                idc = DAOCityFactory.getInstance().generateDAOCity().create(city);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The city doesn't create correctly.\n");

        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idc =null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

            if (tr != null) {
                try {
                    tr.start();
                    TCity tl = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                    if (tl != null && tl.isActive()) {//the city exists and is active

                        Collection<TVehicle> vehiclesList = DAOVehicleFactory.getInstance().generateDAOVehicle().showVehiclesByCity(id);
                        boolean active = false;
                        for (TVehicle tv : vehiclesList) // If exists vehicles actives not update
                            if (tv.isActive())
                                active = true;

                        if (!active) {
                            idc = DAOCityFactory.getInstance().generateDAOCity().update(tl);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();
                        }
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null) throw new ASException("The city doesn't exists");
                        else if (!tl.isActive()) throw new ASException("The city is already disabled");
                    }
                }catch (DAOException | ASException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            }
            else
                throw new ASException("ERROR: The city doesn't delete correctly.\n");


        return idc;
    }

    @Override
    public Integer update(TCity city) throws ASException {
        Integer idc = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();

        if(tr!=null) {
            try {
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
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The city doesn't update correctly.\n");

        return idc;
    }

    @Override
    public TCity show(Integer id) throws ASException {
        TCity city = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr != null) {
            try {
                tr.start();
                city = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
                if (city == null) throw new ASException("The city doesn't exists");
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The city doesn't show correctly.\n");
        return city;
    }

    @Override
    public Collection<TCity> showAll() throws ASException {
        Collection<TCity> cityList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            try {
                tr.start();
                cityList = DAOCityFactory.getInstance().generateDAOCity().readAll();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The cities doesn't create correctly.\n");

        return cityList;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) throws ASException {
        Collection<TClient> clientList = null;
        Transaction tr = TransactionManager.getInstance().createTransaction();
        if(tr!=null) {
            try {
                tr.start();
                clientList = DAOCityFactory.getInstance().generateDAOCity().showClientsByCity(idCity);
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new ASException("ERROR: The clients by city doesn't show correctly.\n");
        return clientList;
    }
}
