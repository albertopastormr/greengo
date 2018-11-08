package integration.Transaction.imp;

import integration.Transaction.Transaction;
import integration.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionMariaDB implements Transaction {


    protected static final String connectionChain = "jdbc:mariadb://localhost:3306/ifesoft?user=manager&password=manager-if";


    @Override
    public void start() {

    }

    @Override
    public void commit() throws TransactionException {
        try {
            connec.commit();
        } catch (SQLException e) {
            throw new TransactionException("");
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connec.rollback();
        } catch (SQLException e) {
            throw new TransactionException("");
        }

    }

    @Override
    public Connection getResource() {
        return connec;
    }

    @Override
    public String getConnectionChain() {
        return connectionChain;
    }
}
