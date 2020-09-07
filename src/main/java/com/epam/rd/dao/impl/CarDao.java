package com.epam.rd.dao.impl;

import com.epam.rd.dao.ConnectionPool;
import com.epam.rd.dao.Dao;
import com.epam.rd.models.Car;
import com.epam.rd.models.buiders.CarBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao extends Dao<Car> {

    private ConnectionPool pool = ConnectionPool.getConnectionPool();

    @Override
    public void setParams(Car entity, PreparedStatement statement, boolean isUpdate) throws SQLException {
        statement.setString(1, entity.getBrand());
        statement.setString(2, entity.getModel());
        statement.setString(3, entity.getType().toString());
        statement.setBoolean(4, entity.isServiceable());
        if (isUpdate) {
            statement.setInt(5, entity.getId());
        }
    }

    @Override
    public Car getResult(ResultSet resultSet) throws SQLException {
        return new CarBuilder()
                .id(resultSet.getInt("cId"))
                .brand(resultSet.getString("brand"))
                .model(resultSet.getString("model"))
                .type(Car.CarType.valueOf(resultSet.getString("type")))
                .serviceable(resultSet.getBoolean("serviceable"))
                .createCar();
    }

    public List<Car> getRangeOfCars(int startIndex, int count) {
        List<Car> entityList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement prst = connection.prepareStatement("SELECT * FROM cars LIMIT ?, ?")) {
            prst.setInt(1, startIndex);
            prst.setInt(2, count);
            try (ResultSet rs = prst.executeQuery()) {
                while (rs.next()) {
                    Car entity = getResult(rs);
                    entityList.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    public List<Car> getAllByOptionValue(String option, String value) {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement prst = connection.prepareStatement(String.format("SELECT * FROM cars WHERE %s = ?", option))) {
            prst.setString(1, value);
            try (ResultSet rs = prst.executeQuery()) {
                while (rs.next()) {
                    Car car = getResult(rs);
                    carList.add(car);
                }
            }
        } catch (SQLException ex) {

        }
        return carList;
    }

    public List<String> getCarOption(String carOption) {

        List<String> carOptionsList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(String.format("SELECT DISTINCT %s FROM cars", carOption))) {
            while (rs.next()) {
                String option = rs.getString(carOption);
                carOptionsList.add(option);
            }
        } catch (SQLException ex) {

        }
        return carOptionsList;
    }

    public boolean create(Car entity) {
        String createQuery = "INSERT INTO cars (brand, model, type, serviceable) VALUES (?, ?, ?, ?)";
        return super.create(entity, createQuery);
    }

    public Car read(int id) {
        String readQuery = "SELECT * FROM cars WHERE cId = ?";
        return super.read(id, readQuery);
    }

    public boolean update(Car entity) {
        String updateQuery = "UPDATE cars SET brand = ?, model = ?, type = ?, serviceable = ? WHERE cId = ?";
        return super.update(entity, updateQuery);
    }

    public boolean delete(int id) {
        String deleteQuery = "DELETE FROM cars WHERE cId=?";
        return super.delete(id, deleteQuery);
    }

    public List<Car> getAll() {
        String getAllQuery = "SELECT * FROM cars";
        return super.getAll(getAllQuery);
    }

    public int getCount() {
        String countQuery = "SELECT COUNT(*) FROM cars";
        return super.getCount(countQuery);
    }
}
