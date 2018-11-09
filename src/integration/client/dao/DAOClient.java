package integration.client.dao;


import business.client.TClient;
import integration.DAOException;

import java.util.Collection;

public interface DAOClient {
    Integer create(TClient client) throws DAOException;

    Integer update(TClient client) throws DAOException;

    TClient readById(Integer id) throws DAOException;

    TClient readByIdCard(String idCard) throws DAOException;

    Collection<TClient> readAll() throws DAOException;

    Collection<TClient> readByNRentals(Integer N) throws DAOException;

    void deleteAll() throws DAOException;
}
