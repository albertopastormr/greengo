package integration.vehicle.dao.imp;

import business.city.TCity;
import business.vehicle.TVehicle;
import integration.DAOException;
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

    Connection connect = TransactionManager.getInstance().getTransaction().getResource();

        if(connect == null){
        try {
            driverIdentify();
            connect = DriverManager.getConnection(TransactionManager.getInstance().getTransaction()
                    .getConnectionChain());
        }
        catch(SQLException ex){
            throw new DAOException("ERROR: acceso a la conexion a DB para 'create' vehicle no logrado\n");
        }
        queryTail = "";
    }

        try { // Tratamiento db
        PreparedStatement ps = connect.prepareStatement("INSERT INTO vehicle(brand,estimatedDuration," +
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

        ps = connect.prepareStatement("SELECT LAST_INSERT_ID() FROM vehicle");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("LAST_INSERT_ID()");
        }
        else
            throw new DAOException("ERROR: LAST_INSERT_ID() devolvio vacio");
        ps.close();
    }
        catch (SQLException e){
        throw new DAOException("ERROR: tratamiento DB para 'create' vehicle no logrado\n");
    }

        finally {
        if(queryTail.equals("")) {
            try {
                connect.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: cerrando conexion a DB para 'create' vehicle no logrado\n");
            }
        }
    }

        return id;
    }

    @Override
    public Integer update(TVehicle vehicle) throws DAOException{

    Integer id;

    String queryTail = " FOR UPDATE";

    Connection connect = TransactionManager.getInstance().getTransaction().getResource();

        if(connect == null){
        try {
            driverIdentify();
            connect = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
        }
        catch(SQLException ex){
            throw new DAOException("ERROR: acceso a la conexion a DB para 'update' vehicle no logrado\n");
        }
        queryTail = "";
    }
 //brand,estimatedDuration," +
        //                "numKmTravelled,occupied,city,active,type
        try { // Tratamiento db
        PreparedStatement ps = connect.prepareStatement("UPDATE vehicle SET brand = ?, estimatedDuration = ?," +
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

        ps = connect.prepareStatement("SELECT id FROM vehicle WHERE id = ?");
        ps.setInt(1, vehicle.getId());
        ps.executeQuery();
        ps.close();

        if (rs.next()) {
            id = rs.getInt("id");
        }
        else
            throw new DAOException("ERROR: entidad no existente en BD en 'update' vehicle");
        ps.close();
    }
        catch (SQLException e){
        throw new DAOException("ERROR: tratamiento DB para 'update' vehicle no logrado\n");
    }

        finally {
        if(queryTail.equals("")) {
            try {
                connect.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: cerrando conexion a DB para 'update' vehicle no logrado\n");
            }
        }
    }

        return id;

}

    @Override
    public TVehicle readById(Integer id) {
        return null;
    }

    @Override
    public Collection<TVehicle> readAll() {
        return null;
    }

    @Override
    public Collection<TVehicle> showAllActiveVehicles() {
        return null;
    }

    @Override
    public Collection<TVehicle> showVehiclesByCity(Integer id) {
        return null;
    }

    @Override
    public TVehicle showByPlateOrSerial(String plate) {
        return null;
    }

    private void driverIdentify() throws DAOException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new DAOException("Error al registrar el driver de mariadb: " + ex);
        }
    }


    @Override
    public void deleteAll() throws DAOException {
        Collection<TVehicle> readVehicleCollect = new ArrayList<>();
        Connection connect;
        try {
            driverIdentify();
            connect = DriverManager.getConnection(TransactionManager.getInstance().getTransaction()
                    .getConnectionChain());

        } catch (SQLException ex) {
            throw new DAOException("ERROR: acceso a la conexion a DB para 'deleteAll' city no logrado\n");
        }

        try { // Tratamiento db
            PreparedStatement ps = connect.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            ps.execute();
            ps.close();
            ps = connect.prepareStatement("TRUNCATE TABLE *");
            ps.execute();
            ps.close();
            ps = connect.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new DAOException("ERROR: tratamiento DB para 'deleteAll' city no logrado\n");
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' vehicle no logrado\n");
            }
        }
    }

}
