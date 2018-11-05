package integration.city.dao.imp;

import business.city.TCity;
import business.client.TClient;
import integration.DAOException;
import integration.city.dao.DAOCity;
import integration.transactionManager.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOCityImp implements DAOCity {


    @Override
    public Integer create(TCity city) throws DAOException {
        Integer id;

        String queryTail = " FOR UPDATE";

        Connection connec = TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: acceso a la conexion a DB para 'create' city no logrado\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO city(name, active) VALUES (?,?)" + queryTail);
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
                throw new DAOException("ERROR: LAST_INSERT_ID() devolvio vacio");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: tratamiento DB para 'create' city no logrado\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'create' city no logrado\n");
                }
            }
        }

        return id;
    }

    @Override
    public Integer update(TCity city) throws DAOException {

        Integer id;

        String queryTail = " FOR UPDATE";

        Connection connec = TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: acceso a la conexion a DB para 'update' city no logrado\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE city SET name = ?, active = ?, WHERE id = ?" + queryTail);
            ps.setString(1, city.getName());
            ps.setBoolean(2, city.isActive());
            ps.setInt(3, city.getId());
            ResultSet rs = ps.executeQuery();

            ps = connec.prepareStatement("SELECT id FROM city WHERE id = ?");
            ps.setInt(1, city.getId());
            ps.executeQuery();
            ps.close();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            else
                throw new DAOException("ERROR: entidad no existente en BD en 'update' city");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: tratamiento DB para 'update' city no logrado\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'update' city no logrado\n");
                }
            }
        }

        return id;

    }

    @Override
    public TCity readById(Integer id) throws DAOException {
        TCity readCity;

        String queryTail = " FOR UPDATE";

        Connection connec = TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: acceso a la conexion a DB para 'readById' city no logrado\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM city WHERE id = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readCity = new TCity(rs.getInt("id"), rs.getString("name"), rs.getBoolean("active"));

            }
            else
                throw new DAOException("ERROR: entidad no existente en BD en 'readById' city");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: tratamiento DB para 'readById' city no logrado\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'readById' city no logrado\n");
                }
            }
        }

        return readCity;
    }

    @Override
    public Collection<TCity> readAll() throws DAOException {
        Collection<TCity> readCityCollec = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        Connection connec = TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: acceso a la conexion a DB para 'readAll' city no logrado\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM city WHERE active = true" + queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readCityCollec.add(new TCity(rs.getInt("id"), rs.getString("name"), rs.getBoolean("active")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: tratamiento DB para 'readAll' city no logrado\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'readAll' city no logrado\n");
                }
            }
        }

        return readCityCollec;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) throws DAOException {
        Collection<TClient> readClientCollec = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        Connection connec = TransactionManager.getInstance().getTransaction().getResource();

        if(connec == null){
            try {
                driverIdentify();
                connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: acceso a la conexion a DB para 'showClientsByCity' city no logrado\n");
            }
            queryTail = "";
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.
                    prepareStatement("SELECT * FROM (vehicle join rental ON vehicle.id = rental.idVehicle) " +
                            "join client ON rental.idClient = client.id  WHERE vehicle.city = ?" + queryTail);
            ps.setInt(1, idCity);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readClientCollec.add(new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: tratamiento DB para 'showClientsByCity' city no logrado\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'showClientsByCity' city no logrado\n");
                }
            }
        }

        return readClientCollec;
    }


    private void driverIdentify() throws DAOException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new DAOException("Error al registrar el driver de mariadb: " + ex);
        }
    }


    public void deleteAll() throws DAOException {
        Collection<TCity> readCityCollec = new ArrayList<>();
        Connection connec = null;
        try {
            driverIdentify();
            connec = DriverManager.getConnection(TransactionManager.getInstance().getTransaction().getConnectionChain());
        } catch (SQLException ex) {
            throw new DAOException("ERROR: acceso a la conexion a DB para 'deleteAll' city no logrado\n");
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
            throw new DAOException("ERROR: tratamiento DB para 'deleteAll' city no logrado\n");
        } finally {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: cerrando conexion a DB para 'deleteAll' city no logrado\n");
                }
        }
    }
}
