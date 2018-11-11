package integration.Transaction.imp;

import integration.Transaction.Transaction;
import integration.TransactionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionMariaDB implements Transaction {


    protected static final String connectionChain = "jdbc:mariadb://localhost:3306/GreenGo?user=manager&password=manager_if";

    private Connection connec;

    public TransactionMariaDB() throws TransactionException {
        try {
            connec =DriverManager.getConnection("jdbc:mariadb://localhost:3306/GreenGo", "manager", "manager_if");
        } catch (SQLException e) {
            throw new TransactionException(e.getMessage());
        }
    }


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
    public Object getResource() {
        return connec;
    }

    @Override
    public String getConnectionChain() {
        return connectionChain;
    }

    public void driverIdentify() throws TransactionException {
        try {Class.forName("org.mariadb.jdbc.Driver");}
        catch (ClassNotFoundException e){
            throw new TransactionException("org.mariadb.jdbc.Driver");
        }
    }
}
