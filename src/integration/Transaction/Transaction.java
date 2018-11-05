package integration.Transaction;

import java.sql.Connection;

public interface Transaction {

    public void start();

    public void commit();

    public void rollback();

    public Connection getResource();

    public String getConnectionChain();
}
