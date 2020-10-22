package by.yurovski.dao.util;

import by.yurovski.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    public static void rollbackConnection(Connection connection, String nameOfMethod) throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e1) {
            throw new DaoException("Exception in " + nameOfMethod + " in connection.rollback() method", e1);
        }
    }

    public static void closeConnection (Connection connection, String nameOfMethod) throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in " + nameOfMethod + " in method connection.close()", e);
        }
    }
}