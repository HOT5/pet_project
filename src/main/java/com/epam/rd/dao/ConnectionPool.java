package com.epam.rd.dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool pool = null;
    private BoneCP boneCp;

    private ConnectionPool() {
        Properties props = new Properties();
        try (InputStream is = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            props.load(is);

            Class.forName(props.getProperty("db.driver"));

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.password"));
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            boneCp = new BoneCP(config);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ConnectionPool getConnectionPool() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        if (pool != null) {
            return boneCp.getConnection();
        } else {
            throw new NullPointerException("ConnectionPool instance not found.");
        }
    }

    public void shutdown() {
        if (pool != null) {
            boneCp.shutdown();
        } else {
            throw new NullPointerException("ConnectionPool instance not found.");
        }
    }
}
