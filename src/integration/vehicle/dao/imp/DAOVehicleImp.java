
package integration.vehicle.dao.imp;

import business.city.TCity;
import business.vehicle.TVehicle;
import integration.DAOException;
import integration.Transaction.Transaction;
import integration.Util;
import integration.transactionManager.TransactionManager;
import integration.vehicle.dao.DAOVehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOVehicleImp  implements DAOVehicle {

    @Override
    public Integer create(TVehicle vehicle) throws DAOException{
        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @vehicle unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO vehicle(brand,estimatedDuration," +
                    "numKmTravelled,occupied,city,active,type) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, vehicle.getBrand());
            ps.setInt(2, vehicle.getEstimatedDuration());
            ps.setInt(3,vehicle.getNumKmTravelled());
            ps.setBoolean(4,vehicle.isOccupied());
            ps.setInt(5,vehicle.getCity());
            ps.setBoolean(6,vehicle.isActive());
            ps.setString(7,vehicle.getType());
            ps.execute();
            ps.close();

            ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM vehicle");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("LAST_INSERT_ID()");
            }
            else
                throw new DAOException("ERROR: LAST_INSERT_ID() returned empty after an insert operation\n");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'create' @vehicle unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'create' @city unsuccessful\n");
                }
            }
        }

        return id;
    }

    @Override
    public Integer update(TVehicle vehicle) throws DAOException{

        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @vehicle unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();
        //brand,estimatedDuration," +
        //                "numKmTravelled,occupied,city,active,type
        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE vehicle SET brand = ?, estimatedDuration = ?," +
                    "numKmTravelled = ?, occupied = ?, city = ?, active = ? " +
                    "WHERE id = ?");
            ps.setString(1, vehicle.getBrand());
            ps.setInt(2, vehicle.getEstimatedDuration());
            ps.setInt(3,vehicle.getNumKmTravelled());
            ps.setBoolean(4,vehicle.isOccupied());
            ps.setInt(5, vehicle.getCity());
            ps.setBoolean(6,vehicle.isActive());
            ps.setInt(7, vehicle.getId());
            ps.execute();

            ps = connec.prepareStatement("SELECT id FROM vehicle WHERE id = ?");
            ps.setInt(1, vehicle.getId());
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            else
                throw new DAOException("ERROR: trying to update a nonexistent entity at operation 'update' @vehicle");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'update' @vehicle unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'update' @vehicle" +
                            " unsuccessful\n");
                }
            }
        }

        return id;

    }

    @Override
    public TVehicle readById(Integer id) throws DAOException{
        TVehicle readVehicle;

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM vehicle WHERE id = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readVehicle = new TVehicle(rs.getInt("id"),rs.getString("brand"),
                        rs.getInt("estimatedDuration"),rs.getInt("numKmTravelled"),
                        rs.getBoolean("occupied"), rs.getInt("city"),
                        rs.getBoolean("active"), rs.getString("type"));
            }
            else
                readVehicle = null;
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readById' @vehicle unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readById' @vehicle " +
                            "unsuccessful\n");
                }
            }
        }

        return readVehicle;
    }

    @Override
    public Collection<TVehicle> readAll()throws DAOException {
        Collection<TVehicle> readVehicles = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAll' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM vehicle" +
                    queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readVehicles.add(new TVehicle(rs.getInt("id"),rs.getString("brand"),
                        rs.getInt("estimatedDuration"),rs.getInt("numKmTravelled"),
                        rs.getBoolean("occupied"), rs.getInt("city"),
                        rs.getBoolean("active"), rs.getString("type")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readAll' @vehicle unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readAll' @vehicle " +
                            "unsuccessful\n");
                }
            }
        }

        return readVehicles;
    }

    @Override
    public Collection<TVehicle> readAllAvailableVehicles() throws DAOException{
        Collection<TVehicle> readVehicles = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAllAvailableVehicles' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM vehicle WHERE (active = true " +
                    "AND occupied = false)"  + queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readVehicles.add(new TVehicle(rs.getInt("id"),rs.getString("brand"),
                        rs.getInt("estimatedDuration"),rs.getInt("numKmTravelled"),
                        rs.getBoolean("occupied"), rs.getInt("city"),
                        rs.getBoolean("active"), rs.getString("type")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readAllAvailable' @vehicle " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readAllAvailable' @vehicle " +
                            "unsuccessful\n");
                }
            }
        }

        return readVehicles;
    }

    @Override
    public Collection<TVehicle> readVehiclesByCity(Integer idCity) throws DAOException{
        Collection<TVehicle> readVehicles = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readVehiclesByCity' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM vehicle WHERE city = ?" + queryTail);
            ps.setInt(1, idCity);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readVehicles.add(new TVehicle(rs.getInt("id"),rs.getString("brand"),
                        rs.getInt("estimatedDuration"),rs.getInt("numKmTravelled"),
                        rs.getBoolean("occupied"), rs.getInt("city"),
                        rs.getBoolean("active"), rs.getString("type")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readVehiclesByCity' @vehicle " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readVehiclesByCity' @vehicle" +
                            "unsuccessful\n");
                }
            }
        }

        return readVehicles;

    }

    //TODO showByPlateOrSerial
    @Override
    public TVehicle showByPlateOrSerial(String plate) {
        return null;
    }

    @Override
    public void deleteAll() throws DAOException {
        Util.deleteAll();
    }

    private void driverIdentify() throws DAOException {
        Util.driverIdentify();
    }

}
