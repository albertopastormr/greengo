package integration.rental.dao.imp;

import business.rental.TRental;
import integration.DAOException;
import integration.TransactionException;
import integration.rental.dao.DAORental;
import integration.transactionManager.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAORentalImp implements DAORental {

    public Integer create(TRental rental) throws DAOException {
        Integer id;

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @rental unsuccessful\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO rental(idVehicle, idClient, numKmRented," +
                    "dateFrom, dateTo, active) VALUES (?,?,?,?,?,?)" + queryTail);
            ps.setInt(1, rental.getIdVehicle());
            ps.setInt(2, rental.getIdClient());
            ps.setInt(3, rental.getNumKmRented());
            ps.setDate(4, (java.sql.Date) rental.getDateFrom());
            ps.setDate(5, (java.sql.Date) rental.getDateTo());
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
            if(queryTail.equals("")) {
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

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @rental unsuccessful\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE rental SET idVehicle = ?, idClient = ?," +
                    " numKmRented = ?, dateFrom = ?, dateTo = ?, active = ? WHERE id = ?" + queryTail);
            ps.setInt(1, rental.getIdVehicle());
            ps.setInt(2, rental.getIdClient());
            ps.setInt(3, rental.getNumKmRented());
            ps.setDate(4, (java.sql.Date) rental.getDateFrom());
            ps.setDate(5, (java.sql.Date) rental.getDateTo());
            ps.setBoolean(6, rental.isActive());
            ps.setInt(7, rental.getId());
            ResultSet rs = ps.executeQuery();

            ps = connec.prepareStatement("SELECT id FROM rental WHERE id = ?");
            ps.setInt(1, rental.getId());
            ps.executeQuery();
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
            if(queryTail.equals("")) {
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

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @rental unsuccessful\n");
            }
            queryTail = "";
        }

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
    public Collection<TRental> showRentalsByClient(Integer id) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'showRentalsByClient' @rental unsuccessful\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM rental WHERE idClient ?" + queryTail);
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
            throw new DAOException("ERROR: SQL statement execution at operation 'showRentalsByClient' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'showRentalsByClient' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    public Collection<TRental> showRentalsByVehicle(Integer id) throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'showRentalsByVehicle' @rental " +
                        "unsuccessful\n");
            }
            queryTail = "";
        }

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
            throw new DAOException("ERROR: SQL statement execution at operation 'showRentalsByVehicle' @rental " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'showRentalsByVehicle' " +
                            "@rental unsuccessful\n");
                }
            }
        }

        return readRentals;
    }

    public Boolean checkAvailableDates(TRental rental) {
        return null; // TODO whole method
    }

    public Collection<TRental> readAll() throws DAOException {
        Collection<TRental> readRentals = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAll' @rental unsuccessful\n");
            }
            queryTail = "";
        }

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
        Connection connec;
        try {
            driverIdentify();
            connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                    getConnectionChain());
        } catch (SQLException ex) {
            throw new DAOException("ERROR: access to DB at operation 'deleteAll' @rental unsuccessful\n");
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("TRUNCATE TABLE *");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new DAOException("ERROR: SQL statement execution at operation 'delete All' @rental unsuccessful\n");
        } finally {
            try {
                connec.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: closing connection to DB at operation 'deleteAll' @rental " +
                        "unsuccessful\n");
            }
        }
    }

    private void driverIdentify() throws DAOException {
        try {
            TransactionManager.getInstance().getTransaction().driverIdentify();
        } catch (TransactionException ex) {
            throw new DAOException("ERROR: couldn't register MARIADB driver: " + ex);
        }
    }

}
