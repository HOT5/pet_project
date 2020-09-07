package com.epam.rd.dao;

import com.epam.rd.models.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T extends Entity> {

    private final Logger logger = LoggerFactory.getLogger(Dao.class);
    private ConnectionPool pool = ConnectionPool.getConnectionPool();

    public abstract void setParams(T entity, PreparedStatement statement, boolean isUpdate) throws SQLException;

    public abstract T getResult(ResultSet resultSet) throws SQLException;

    protected boolean create(T entity, String createQuery) {
        boolean result = true;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(createQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setParams(entity, statement, false);
            if (!(statement.executeUpdate() > 0)) {
                throw new SQLException(String.format("Error during creating object: %s", entity));
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                    logger.info(String.format("Object created successful: %s", entity));
                }
            }
        } catch (SQLException e) {
            logger.error("Create object failed.", e);
            result = false;
        }
        return result;
    }

    public T read(int id, String readQuery) {
        T entity = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(readQuery)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                entity = getResult(result);
                logger.info(String.format("Reading object with id: %d%n successful.", id));
            }
        } catch (SQLException e) {

        }
        return entity;
    }

    public boolean update(T entity, String updateQuery) {
        boolean result = true;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            setParams(entity, statement, true);
            if (!(statement.executeUpdate() > 0)) {
                throw new SQLException(String.format("Error during updating object: %s", entity));
            }
            logger.info(String.format("Updating object: %s successful.", entity));
        } catch (SQLException e) {
            logger.error("Update object failed.", e);
            result = false;
        }
        return result;
    }

    public boolean delete(int id, String deleteQuery) {
        boolean result = true;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            if (!(statement.executeUpdate() > 0)) {
                throw new SQLException(String.format("Error during deleting object with id: %d%n", id));
            }
            logger.info(String.format("Deleting object with id: %d%n successful.", id));

        } catch (SQLException e) {
            logger.error("Deleting object failed.", e);
            result = false;
        }
        return result;
    }

    public List<T> getAll(String getAllQuery) {
        List<T> entityList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getAllQuery)) {
            while (rs.next()) {
                T entity = getResult(rs);
                entityList.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    public int getCount(String countQuery) {
        int result = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(countQuery)) {
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error during creating entity list.", e);
            e.printStackTrace();
        }
        return result;
    }
}

