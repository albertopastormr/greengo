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

        if(!city.getName().equals("")) {

            Transaction tr = TransactionManager.getInstance().createTransaction();

            if (tr != null) {
                try {
                    tr.start();
                    idc = DAOCityFactory.getInstance().generateDAOCity().create(city);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } catch (DAOException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            } else
                throw new ASException("ERROR: The city doesn't create correctly.\n");
        }else
            throw new ASException("ERROR: The data of city isn't insert correctly.\n");

        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException {
        Integer idc =null;

        if(id > 0) {

            Transaction tr = TransactionManager.getInstance().createTransaction();

            if (tr != null) {
                try {
                    tr.start();
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                    if (tc != null && tc.isActive()) {//the city exists and is active

                        Collection<TVehicle> vehiclesList = DAOVehicleFactory.getInstance().generateDAOVehicle().showVehiclesByCity(id);
                        boolean isVehicleActive = false;
                        for (TVehicle tv : vehiclesList) // If exists vehicles actives not update
                            if (tv.isActive())
                                isVehicleActive = true;

                        if (!isVehicleActive) {
                            tc.setActive(false);
                            idc = DAOCityFactory.getInstance().generateDAOCity().update(tc);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();
                        }
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tc == null) throw new ASException("The city doesn't exist");
                        else if (!tc.isActive()) throw new ASException("The city is already disabled");
                    }
                } catch (DAOException | ASException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            } else
                throw new ASException("ERROR: The city doesn't delete correctly.\n");
        }else
            throw new ASException("ERROR: The data of city isn't insert correctly.\n");

        return idc;
    }

    @Override
    public Integer update(TCity city) throws ASException {
        Integer idc = null;

        if(city.getId() > 0 && !city.getName().equals("")) {

            Transaction tr = TransactionManager.getInstance().createTransaction();

            if (tr != null) {
                try {
                    tr.start();
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(city.getId());

                    if (tc != null) {//the city exists
                        idc = DAOCityFactory.getInstance().generateDAOCity().update(tc);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        throw new ASException("The city doesn't exist");
                    }
                } catch (DAOException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            } else
                throw new ASException("ERROR: The city doesn't update correctly.\n");
        }else
            throw new ASException("ERROR: The data of city isn't insert correctly.\n");

        return idc;
    }

    @Override
    public TCity show(Integer id) throws ASException {
        TCity city = null;

        if(id > 0) {

            Transaction tr = TransactionManager.getInstance().createTransaction();

            if (tr != null) {
                try {
                    tr.start();
                    city = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                    if (city == null) throw new ASException("The city doesn't exists");
                } catch (DAOException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            } else
                throw new ASException("ERROR: The city doesn't show correctly.\n");
        }else
            throw new ASException("ERROR: The number of city isn't insert correctly.\n");

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
            throw new ASException("ERROR: The cities doesn't list correctly.\n");

        return cityList;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) throws ASException {
        Collection<TClient> clientList = null;

        if(idCity > 0) {

            Transaction tr = TransactionManager.getInstance().createTransaction();
            if (tr != null) {
                try {
                    tr.start();
                    clientList = DAOCityFactory.getInstance().generateDAOCity().showClientsByCity(idCity);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } catch (DAOException | TransactionException e) {
                    throw new ASException(e.getMessage());
                }
            } else
                throw new ASException("ERROR: The clients by city doesn't show correctly.\n");
        }else
            throw new ASException("ERROR: The number of city isn't insert correctly.\n");

        return clientList;
    }
}
