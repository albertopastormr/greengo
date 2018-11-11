
package integration.vehicle.dao.imp;

import business.city.TCity;
import business.vehicle.TVehicle;
import integration.DAOException;
import integration.TransactionException;
import integration.transactionManager.TransactionManager;
import integration.vehicle.dao.DAOVehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOVehicleImp  implements DAOVehicle {
    @Override
    public Integer create(TVehicle vehicle) throws DAOException{
        Integer id;
        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction()
                        .getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO vehicle(brand,estimatedDuration," +
                    "numKmTravelled,occupied,city,active,type) VALUES (?,?,?,?,?,?,?)" + queryTail);
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
            if(queryTail.equals("")) {
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

        String queryTail = " FOR UPDATE";

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }
        //brand,estimatedDuration," +
        //                "numKmTravelled,occupied,city,active,type
        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE vehicle SET brand = ?, estimatedDuration = ?," +
                    "numKmTravelled = ?, occupied = ?, city = ?, active = ?, type = ?" +
                    "WHERE id = ?" + queryTail);
            ps.setString(1, vehicle.getBrand());
            ps.setInt(2, vehicle.getEstimatedDuration());
            ps.setInt(3,vehicle.getNumKmTravelled());
            ps.setBoolean(4,vehicle.isOccupied());
            ps.setInt(5, vehicle.getCity());
            ps.setBoolean(6,vehicle.isActive());
            ps.setString(7,vehicle.getType());
            ResultSet rs = ps.executeQuery();

            ps = connec.prepareStatement("SELECT id FROM vehicle WHERE id = ?");
            ps.setInt(1, vehicle.getId());
            ps.executeQuery();
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
            if(queryTail.equals("")) {
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

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }

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

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAll' @vehicle unsuccessful\n");
            }
            queryTail = "";
        }

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

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAllAvailableVehicles' @vehicle " +
                        "unsuccessful\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM vehicle WHERE active = true " +
                    "AND occupied = false"  + queryTail);
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

        Connection connec = (Connection) TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().
                        getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readVehiclesByCity' @vehicle " +
                        "unsuccessful\n");
            }
            queryTail = "";
        }

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
        Connection connec;
        try {
            driverIdentify();
            connec = DriverManager.getConnection("jdbc:mariadb://localhost:3306/greengo?user=manager&password=manager_if");

        } catch (SQLException ex) {
            throw new DAOException("ERROR: access to DB at operation 'deleteAll' @vehicle unsuccessful\n");
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("TRUNCATE TABLE vehicle");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("TRUNCATE TABLE bicylceVehicle");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("TRUNCATE TABLE carVehicle");
            ps.execute();
            ps.close();
            ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new DAOException("ERROR: SQL statement execution at operation 'deleteAll' @vehicle unsuccessful\n");
        } finally {
            try {
                connec.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: closing connection to DB at operation 'deleteAll' @vehicle " +
                        "unsuccessful\n");
            }
        }
    }

    private void driverIdentify() throws DAOException {
        try {
            TransactionManager.getInstance().getTransaction().start();
        } catch (TransactionException ex) {
            throw new DAOException("ERROR: couldn't register MARIADB driver: " + ex);
        }
    }

}
