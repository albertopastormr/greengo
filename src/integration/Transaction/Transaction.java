package integration.Transaction;

import integration.TransactionException;

import java.sql.Connection;

public interface Transaction {

    public void start();

    public void commit() throws TransactionException;

    public void rollback() throws TransactionException;

    public Connection getResource();

    public String getConnectionChain();
}
