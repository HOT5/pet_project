package com.epam.rd.dao.impl;

import com.epam.rd.dao.ConnectionPool;
import com.epam.rd.dao.Dao;
import com.epam.rd.models.User;
import com.epam.rd.models.buiders.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {
    private ConnectionPool pool = ConnectionPool.getConnectionPool();

    public UserDao() {
    }

    @Override
    public void setParams(User entity, PreparedStatement statement, boolean isUpdate) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getLogin());
        statement.setString(4, entity.getEmail());
        statement.setString(5, entity.getPassword());
        if (isUpdate) {
            statement.setInt(6, entity.getId());
        } else {
            User.Role role;
            if (entity.getRole() == null) {
                role = User.Role.CLIENT;
            } else {
                role = entity.getRole();
            }
            statement.setString(6,role.name());
            entity.setRole(User.Role.CLIENT);
        }
    }

    @Override
    public User getResult(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .id(resultSet.getInt("uId"))
                .firstName(resultSet.getString("firstname"))
                .lastName(resultSet.getString("lastname"))
                .login(resultSet.getString("login"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(User.Role.valueOf(resultSet.getString("role")))
                .createUser();
    }

    public User getByLogin(String login) {
        User user = null;
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                user = getResult(rs);
            }
        } catch (SQLException e) {
        }
        return user;
    }


    public boolean create(User entity) {
        String createQuery = "INSERT INTO users (firstname, lastname,  login, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        return super.create(entity, createQuery);
    }

    public User read(int id) {
        String readQuery = "SELECT * FROM users WHERE uId = ?";
        return super.read(id, readQuery);
    }

    public boolean update(User entity) {
        String updateQuery = "UPDATE users SET firstname = ?, lastname = ?, login = ?, email = ?, password = ? WHERE uId = ?";
        return super.update(entity, updateQuery);
    }

    public boolean delete(int id) {
        String deleteQuery = "DELETE FROM users WHERE uId=?";
        return super.delete(id, deleteQuery);
    }

    public List<User> getAll() {
        String getAllQuery = "SELECT * FROM users";
        return super.getAll(getAllQuery);
    }

    public int getCount() {
        String countQuery = "SELECT COUNT(*) FROM users";
        return super.getCount(countQuery);
    }
    public List<User> getRangeOfUsers(int startIndex, int count) {
        List<User> entityList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users LIMIT ?, ?")) {
            statement.setInt(1, startIndex);
            statement.setInt(2, count);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User entity = getResult(rs);
                    entityList.add(entity);
                }
            }
        } catch (SQLException ex) {
        }
        return entityList;
    }

    public void deleteAllUsers() {
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException ex) {
        }
    }

}
