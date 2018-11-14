package integration.transaction;

import integration.DAOException;
import integration.TransactionException;

public interface Transaction {

     void start() throws TransactionException, DAOException;

     void commit() throws TransactionException;

     void rollback() throws TransactionException;

     Object getResource();

     String getConnectionChain();
}
