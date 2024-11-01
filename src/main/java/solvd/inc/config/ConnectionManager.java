package solvd.inc.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.Configuration;
import solvd.inc.dao.CityMapper;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.sql.Connection;
import java.util.logging.Logger;

public class ConnectionManager {
    private static ConnectionManager instance;
    private final BlockingQueue<Connection> availableConnections;
    private final int poolSize = 5;
    private static SqlSessionFactory sqlSessionFactory;
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/navigator";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private ConnectionManager() {
        this.availableConnections = new ArrayBlockingQueue<>(poolSize);
        try {
            for (int i = 0; i < poolSize; i++) {
                availableConnections.add(createConnection());
            }

            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(URL);
            dataSource.setUsername(USERNAME);
            dataSource.setPassword(PASSWORD);

            Configuration configuration = new Configuration();
            configuration.addMapper(CityMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (SQLException e) {
            logger.severe("Error initializing the connection pool: " + e.getMessage());
            throw new RuntimeException("Error initializing the connection pool", e);
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public CompletableFuture<Connection> acquireConnection() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return availableConnections.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Error acquiring connection from the pool", e);
            }
        });
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.severe("Failed to release connection: " + e.getMessage());
            }
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void closePool() {
        for (Connection connection : availableConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.severe("Error closing connection: " + e.getMessage());
            }
        }
    }

    public SqlSessionFactory getSessionFactory() {
        return sqlSessionFactory;
    }
}
