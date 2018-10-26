package integration.Transaction.imp;

import integration.Transaction.Transaction;

import java.sql.Connection;

public class TransactionMariaDB implements Transaction {
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
}
