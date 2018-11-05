package integration.Transaction.imp;

import integration.Transaction.Transaction;

import java.sql.Connection;

public class TransactionMariaDB implements Transaction {


    protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";


    @Override
    public void start() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public Connection getResource() {
        return null;
    }

    @Override
    public String getConnectionChain() {
        return connectionChain;
    }
}
