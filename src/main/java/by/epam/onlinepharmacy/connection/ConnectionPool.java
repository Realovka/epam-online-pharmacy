package by.epam.onlinepharmacy.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    private static final int DEFAULT_POOL_SIZE = 32;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        logger.log(Level.INFO, "Try to create connection pool");
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "coudn't create connection to data base: " + e.getMessage());
            }
        }
        if (freeConnections.size() == 0) {
            logger.log(Level.FATAL, "connections poll don't created, pool size: " + freeConnections.size());
            throw new RuntimeException("connections poll don't created");
        }
        logger.log(Level.INFO, "Connection pool was created");
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method getConnection ", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        }
    }

    public void destroyPool() {
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {

            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQLException in method deregisterDrivers() ", e);
            }
        });
    }

}
