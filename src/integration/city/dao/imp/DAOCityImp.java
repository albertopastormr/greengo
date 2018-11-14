package integration.city.dao.imp;

import business.city.TCity;
import business.client.TClient;
import integration.DAOException;
import integration.transaction.Transaction;
import integration.city.dao.DAOCity;
import integration.transactionManager.TransactionManager;
import integration.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOCityImp implements DAOCity {

    @Override
    public Integer create(TCity city) throws DAOException {
        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @city unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO city(name, active) VALUES (?,?)");
            ps.setString(1, city.getName());
            ps.setBoolean(2, city.isActive());
            ps.execute();
            ps.close();

            ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM city");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("LAST_INSERT_ID()");
            }
            else
                throw new DAOException("ERROR: LAST_INSERT_ID() returned empty after an insert operation\n");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'create' @city unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'create' @city " +
                            "unsuccessful\n");
                }
            }
        }

        return id;
    }

    @Override
    public Integer update(TCity city) throws DAOException {

        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @city unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE city SET name = ?, active = ? " +
                    "WHERE id = ?");
            ps.setString(1, city.getName());
            ps.setBoolean(2, city.isActive());
            ps.setInt(3, city.getId());
            ps.execute();

            ps = connec.prepareStatement("SELECT id FROM city WHERE id = ?");
            ps.setInt(1, city.getId());
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            else
                throw new DAOException("ERROR: trying to update a nonexistent entity at operation 'update' @city");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'update' @city unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'create' @city " +
                            "unsuccessful\n");
                }
            }
        }

        return id;

    }

    @Override
    public TCity readById(Integer id) throws DAOException {
        TCity readCity;

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @city unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM city WHERE id = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readCity = new TCity(rs.getInt("id"), rs.getString("name"),
                        rs.getBoolean("active"));

            }
            else
                readCity = null;
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readById' @city unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readById' @city " +
                            "unsuccessful\n");
                }
            }
        }

        return readCity;
    }

    @Override
    public Collection<TCity> readAll() throws DAOException {
        Collection<TCity> readCities = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAll' @city unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM city WHERE active = true" +
                    queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readCities.add(new TCity(rs.getInt("id"), rs.getString("name"),
                        rs.getBoolean("active")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readAll' @city unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readAll' @city " +
                            "unsuccessful\n");
                }
            }
        }

        return readCities;
    }

    @Override
    public Collection<TClient> readClientsByCity(Integer idCity) throws DAOException {
        Collection<TClient> readClients = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readClientsByCity' @city unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.
                    prepareStatement("SELECT * FROM (vehicle join rental ON vehicle.id = rental.idVehicle) " +
                            "join city ON rental.idClient = city.id" +" join client ON rental.idClient = client.id"+"  WHERE vehicle.city = ?" + queryTail);
            ps.setInt(1, idCity);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readClients.add(new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readClientsByCity' @city " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readClientsByCity' @city " +
                            "unsuccessful\n");
                }
            }
        }

        return readClients;
    }

    public void deleteAll() throws DAOException {
        Util.deleteAll();
    }

    private void driverIdentify() throws DAOException {
        Util.driverIdentify();
    }

}
