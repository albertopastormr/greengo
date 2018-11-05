package integration.Transaction.imp;

import integration.Transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionMariaDB implements Transaction {
    private Connection connec;

    @Override
    public void start() {

    }

    @Override
    public void commit() {
        try {
            connec.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connec.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getResource() {
        return connec;
    }
}
