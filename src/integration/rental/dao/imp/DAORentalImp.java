package integration.rental.dao.imp;

import business.rental.TRental;
import integration.DAOException;
import integration.transaction.Transaction;
import integration.Util;
import integration.rental.dao.DAORental;
import integration.transactionManager.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAORentalImp implements DAORental {

    public Integer create(TRental rental) throws DAOException {
        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @rental unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO rental(idVehicle, idClient, numKmRented," +
                    "dateFrom, dateTo, active) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, rental.getIdVehicle());
            ps.setInt(2, rental.getIdClient());
            ps.setInt(3, rental.getNumKmRented());
            ps.setDate(4, new java.sql.Date(rental.getDateFrom().getTime()));
            ps.setDate(5, new java.sql.Date(rental.getDateTo().getTime()));
            ps.setBoolean(6, rental.isActive());
            ps.execute();
            ps.close();

            ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM rental");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("LAST_INSERT_ID()");
            }
            else
                throw new DAOException("ERROR: LAST_INSERT_ID() returned empty after an insert operation\n");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'create' @rental unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'create' @rental " +
                            "unsuccessful\n");
                }
            }
        }

        return id;
    }


    public Integer update(TRental rental) throws DAOException {

        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @rental unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE rental SET idVehicle = ?, idClient = ?," +
                    " numKmRented = ?, dateFrom = ?, dateTo = ?, active = ? WHERE id = ?");
            ps.setInt(1, rental.getIdVehicle());
            ps.setInt(2, rental.getIdClient());
            ps.setInt(3, rental.getNumKmRented());
            ps.setDate(4, new java.sql.Date(rental.getDateFrom().getTime()));
            ps.setDate(5, new java.sql.Date(rental.getDateTo().getTime()));
            ps.setBoolean(6, rental.isActive());
            ps.setInt(7, rental.getId());
            ps.executeQuery();

            ps = connec.prepareStatement("SELECT id FROM rental WHERE id = ?");
            ps.setInt(1, rental.getId());
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            else
                throw new DAOException("ERROR: trying to update a nonexistent entity at operation 'update' @rental");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'update' @rental unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'update' @rental " +
                            "unsuccessful\n");
                }
            }
        }

        return id;

    }


    public TRental readById(Integer id) throws DAOException {
        TRental readRental;

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE id = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readRental = new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo"));
            }
            else
                readRental = null;
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readById' @rental unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readById' @rental " +
                            "unsuccessful\n");
                }
            }
        }

        return readRental;
    }

    @Override
    public Collection<TRental> readByRentalsByClient(Integer id) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByRentalsByClient' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE idClient = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readRentals.add(new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByRentalsByClient' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByRentalsByClient' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    public Collection<TRental> readByRentalsByVehicle(Integer id) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByRentalsByVehicle' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE idVehicle = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readRentals.add(new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByRentalsByVehicle' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByRentalsByVehicle' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    @Override
    public Collection<TRental> readByIdVehicleAndDateRange(Integer id, java.util.Date dateFrom, java.util.Date dateTo) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByIdVehicleAndDateRange' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE idVehicle = ? AND " +
                    "((dateFrom >= ? AND dateFrom <= ?) OR (dateTo >= ? AND dateTo <= ?))" + queryTail);
            ps.setInt(1, id);
            ps.setDate(2, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(3, new java.sql.Date(dateTo.getTime()));
            ps.setDate(4, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(5, new java.sql.Date(dateTo.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readRentals.add(new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByIdVehicleAndDateRange' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByIdVehicleAndDateRange' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    public Collection<TRental> readByIdClientAndDateRange(Integer id, java.util.Date dateFrom, java.util.Date dateTo) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByIdClientAndDateRange' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE idClient = ? AND " +
                    "((dateFrom >= ? AND dateFrom <= ?) OR (dateTo >= ? AND dateTo <= ?) " +
                    " OR (dateFrom <= ? AND dateTo >= ?))" + queryTail);
            ps.setInt(1, id);
            ps.setDate(2, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(3, new java.sql.Date(dateTo.getTime()));
            ps.setDate(4, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(5, new java.sql.Date(dateTo.getTime()));
            ps.setDate(6, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(7, new java.sql.Date(dateTo.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readRentals.add(new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByIdClientAndDateRange' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByIdClientAndDateRange' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    public Collection<TRental> readAll() throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'checkAvailableDates' @rental unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE active = true" + queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readRentals.add(new TRental(rs.getInt("id"), rs.getInt("idVehicle"),
                        rs.getBoolean("active"), rs.getInt("numKmRented"),
                        rs.getInt("idClient"), rs.getDate("dateFrom"),
                        rs.getDate("dateTo")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readAll' @rental unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readAll' @rental " +
                            "unsuccessful\n");
                }
            }
        }

        return readRentals;
    }


    public void deleteAll() throws DAOException {
        Util.deleteAll();
    }

    private void driverIdentify() throws DAOException {
        Util.driverIdentify();
    }

}
