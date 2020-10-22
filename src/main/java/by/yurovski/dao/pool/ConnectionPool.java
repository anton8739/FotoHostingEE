package by.yurovski.dao.pool;

import by.yurovski.exception.DaoException;
import by.yurovski.manager.DBPropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private LinkedBlockingDeque<ProxyConnection> connectionQueue;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String URL = DBPropertyManager.getProperty("jdbc.url");
    private static final String USER = DBPropertyManager.getProperty("jdbc.username");
    private static final String PASSWORD = DBPropertyManager.getProperty("jdbc.password");
    private static final int POOL_SIZE = Integer.parseInt(DBPropertyManager.getProperty("poolSize"));

    private ConnectionPool (int poolSize) {
        connectionQueue = new LinkedBlockingDeque<ProxyConnection>();
        try {
            Class.forName(DBPropertyManager.getProperty("jdbc.driverClassName"));

        } catch (ClassNotFoundException e) {
            LOGGER.info("ConnectionPool(int poolSize)", e);
            throw new RuntimeException(e);
        }

        for (int i = 0; i < poolSize; i++){
            connectionQueue.offer(createConnection());
        }
    }
    public static ConnectionPool getInstance () {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool(POOL_SIZE);
    }
    private ProxyConnection createConnection () {
        ProxyConnection pc = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            pc = new ProxyConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("createConnection()", e);
        }
        return pc;
    }

    public ProxyConnection getConnection () throws DaoException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new DaoException("getConnection", e);
        }
        return connection;
    }

    public void closeConnection (ProxyConnection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("closeConnection(ProxyConnection conn)", e);
            connectionQueue.offer(createConnection());
        }
    }

}
