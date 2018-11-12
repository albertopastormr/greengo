package business.client.as.imp;

import business.ASException;
import business.IncorrectInputException;
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
    public Integer create(TClient client) throws ASException, IncorrectInputException {
        Integer idc = null;

        if(client.getIdCardNumber() != null && !client.getIdCardNumber().equals("") && client.getNumRentals() != null && client.getNumRentals() >=0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                    if (tr != null) {
                        tr.start();
                        TClient tl = DAOClientFactory.getInstance().generateDAOClient().readByIdCard(client.getIdCardNumber());//search other client with same idCard
                        if (tl == null) {//don't exists
                            idc = DAOClientFactory.getInstance().generateDAOClient().create(client);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();
                        } else {//exists
                            tr.rollback();
                            TransactionManager.getInstance().removeTransaction();
                            throw new ASException("ERROR: Other client exists with the same idCard");
                        }
                    } else
                        throw new ASException("ERROR: The client doesn't create correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        } else
            throw new IncorrectInputException("ERROR: IdCardNumber can`t be empty and  numRentals must be >=0 \n");

        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException, IncorrectInputException {
        Integer idc = null;

        if(id!= null && id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(id);
                    Collection<TRental> listRentals = DAORentalFactory.getInstance().generateDAORental().showRentalsByClient(id);

                    for (TRental rental : listRentals) // If exists rentals actived couldn't drop client
                        if (rental.isActive())
                            throw new ASException("ERROR: Exists rentals active");

                    if (tl != null && tl.isActive() ) {//the client exists and is active, and the client hasn't got active rentals
                        tl.setActive(false);
                        idc = DAOClientFactory.getInstance().generateDAOClient().update(tl);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null) throw new ASException("ERROR: The client doesn't exists");
                        else if (!tl.isActive()) throw new ASException("ERROR: The client is already disabled");
                    }
                }else
                    throw new ASException("ERROR: The client doesn't delete correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id mustn't bre positive or empty \n");


        return idc;
    }

    @Override
    public Integer update(TClient client) throws ASException, IncorrectInputException {
        Integer idc =null;

        if(client.getId()!=null && client.getId() > 0 && !client.getIdCardNumber().equals("") && client.getNumRentals() >=0 && client.isActive() ) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TClient tl = DAOClientFactory.getInstance().generateDAOClient().readById(client.getId());
                    TClient tn = DAOClientFactory.getInstance().generateDAOClient().readByIdCard(client.getIdCardNumber());

                    if (tl != null && tn == null && client.isActive()) {//the client exists and don't exists other client with the same idCard
                        idc = DAOClientFactory.getInstance().generateDAOClient().update(client);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tl == null) throw new ASException("ERROR: The client doesn't exists");
                        else if (!client.isActive()) throw new ASException("ERROR: The client field is disabled, you must use Drop operation in order to disabled it");
                        else throw new ASException("ERROR: Exists other client with the same idCard");
                    }
                } else
                    throw new ASException("ERROR: The client doesn't update correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        } else if (client.getId() == null || client.getId() <= 0)
            throw new IncorrectInputException("ERROR: Id can't be null and must be > 0 and idCardNumber mustn`t be empty and  \n");
          else if (client.getIdCardNumber().equals(""))
            throw new IncorrectInputException("ERROR: idCardNumber mustn`t be empty  \n");
          else if (client.getNumRentals() <0 )
            throw new IncorrectInputException("ERROR: numRentals must be >= 0 \n");


        return idc;
    }

    @Override
    public TClient show(Integer id) throws ASException, IncorrectInputException {
        TClient client = null;

        if(id !=null && id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    client = DAOClientFactory.getInstance().generateDAOClient().readById(id);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                    if (client == null) throw new ASException("ERROR: The client doesn't exists");
                }else
                    throw new ASException("ERROR: The client doesn't show correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive \n");
        return client;
    }

    @Override
    public Collection<TClient> showAll() throws ASException {
        Collection<TClient> clientsList = null;
        try{
            Transaction tr = TransactionManager.getInstance().createTransaction();
            if(tr!=null) {
                tr.start();
                clientsList = DAOClientFactory.getInstance().generateDAOClient().readAll();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }else
                throw new ASException("ERROR: The clients doesn't list correctly.\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        }
        return clientsList;
    }

    @Override
    public Collection<TClient> showAllWithMoreThanNRentals(Integer N) throws ASException, IncorrectInputException {
        Collection<TClient> clientsList = null;

        if(N > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    clientsList = DAOClientFactory.getInstance().generateDAOClient().readByNRentals(N);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                }else
                    throw new ASException("ERROR: The client doesn't show correctly.\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: N must be positive \n");

        return clientsList;
    }
}
