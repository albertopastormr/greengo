package integration.Transaction.imp;

import integration.DAOException;
import integration.Transaction.Transaction;
import integration.TransactionException;
import integration.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionMariaDB implements Transaction {


    protected static final String connectionChain = Util.getConnectionChain();

    private Connection connec;

    public TransactionMariaDB() throws TransactionException {
        try {
            connec =DriverManager.getConnection("jdbc:mariadb://localhost:3306/greengo", "manager", "manager_if");
        } catch (SQLException e) {
            throw new TransactionException(e.getMessage());
        }
    }


    @Override
    public void start() throws DAOException {
        Util.driverIdentify();
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connec.commit();
        } catch (SQLException e) {
            throw new TransactionException("Error: commit couldn't be accomplished");
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connec.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Error: rollback couldn't be accomplished");
        }

    }

    @Override
    public Object getResource() {
        return connec;
    }

    @Override
    public String getConnectionChain() {
        return connectionChain;
    }

}
