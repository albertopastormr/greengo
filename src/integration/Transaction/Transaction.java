package integration.Transaction;

import integration.TransactionException;

import java.sql.Connection;

public interface Transaction {

     void start();

     void commit() throws TransactionException;

     void rollback() throws TransactionException;

     Object getResource();

     String getConnectionChain();

     void driverIdentify() throws TransactionException;

}
