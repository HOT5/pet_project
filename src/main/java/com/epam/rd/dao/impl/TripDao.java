package com.epam.rd.dao.impl;

import com.epam.rd.dao.ConnectionPool;
import com.epam.rd.dao.Dao;
import com.epam.rd.models.Car;
import com.epam.rd.models.Trip;
import com.epam.rd.models.buiders.TripBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDao extends Dao<Trip> {
    private final Logger logger = LoggerFactory.getLogger(TripDao.class);
    private ConnectionPool pool = ConnectionPool.getConnectionPool();


    @Override
    public void setParams(Trip entity, PreparedStatement statement, boolean isUpdate) throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getStatus().name());
        statement.setString(3, entity.getType().toString());
    }

    @Override
    public Trip getResult(ResultSet resultSet) throws SQLException {
        return new TripBuilder()
                .id(resultSet.getInt("tId"))
                .name(resultSet.getString("name"))
                .created(resultSet.getDate("created"))
                .status(Trip.Status.valueOf(resultSet.getString("status")))
                .type(Car.CarType.valueOf(resultSet.getString("carType")))
                .car(SimpleDaoFactory.getDaoFactory().getCarDao().read(resultSet.getInt("carID")))
                .user(SimpleDaoFactory.getDaoFactory().getUserDao().read(resultSet.getInt(("userId"))))
                .createTrip();
    }

    public List<Trip> getRangeOfTrips(int startIndex, int count) {
        List<Trip> orderList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM trips LIMIT ?, ?")) {
            statement.setInt(1, startIndex);
            statement.setInt(2, count);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Trip entity = getResult(rs);
                    orderList.add(entity);
                }
            }
        } catch (SQLException e) {
            logger.error("Error during reading getting range of trips.", e);
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Trip> getTripsByStatus(String status) {
        List<Trip> tripsList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM trips WHERE status = ?")) {
            statement.setString(1,status);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Trip entity = getResult(rs);
                    tripsList.add(entity);
                }
            }
        } catch (SQLException e) {
            logger.error("Error during reading getting range of trips.", e);
            e.printStackTrace();
        }
        return tripsList;
    }

    public void updateTripStatus(int trip_id, String status) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE trips SET status = ? WHERE tId = ?")) {
            statement.setString(1,status);
            statement.setInt(2,trip_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTripUser(int trip_id, int user_id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE trips SET userId = ? WHERE tId = ?")) {
            statement.setInt(1,user_id);
            statement.setInt(2,trip_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTripCar(int trip_id, int car_id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE trips SET carID = ? WHERE tId = ?")) {
            statement.setInt(1,car_id);
            statement.setInt(2,trip_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trip> getAllUserTrips(int id) {
        logger.info(String.format("Getting trips with id %d%n ", id));
        List<Trip> trips = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM trips WHERE userId = ?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Trip trip = getResult(rs);
                    trips.add(trip);
                }
            }
        } catch (SQLException ex) {
            logger.info(String.format("Getting trips list with id=%d%n  failure", id), ex);
        }
        return trips;
    }

    public List<Trip> getTripsWithoutUser(){
        logger.info("Getting trips without users");
        List<Trip> trips = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM trips WHERE userId IS NULL")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Trip trip = getResult(rs);
                    trips.add(trip);
                }
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return trips;
    }

    public boolean create(Trip entity) {
        String createQuery = "INSERT INTO trips (name, status, carType) VALUES (?, ?, ?)";
        return super.create(entity, createQuery);
    }



    public Trip read(int id) {
        String readQuery = "SELECT * FROM trips WHERE tId = ?";
        return super.read(id, readQuery);
    }

    public boolean update(Trip entity) {
        String updateQuery = "UPDATE trip SET name = ?, created = ?, status = ?, carType = ?, carID = ?, userId = ?,  " +
                "WHERE tId = ?";
        return super.update(entity, updateQuery);
    }

    public boolean delete(int id) {
        String deleteQuery = "DELETE FROM trips WHERE tId=?";
        return super.delete(id, deleteQuery);
    }

    public List<Trip> getAll() {
        String getAllQuery = "SELECT * FROM trips";
        return super.getAll(getAllQuery);
    }

    public int getCount() {
        String countQuery = "SELECT COUNT(*) FROM trips";
        return super.getCount(countQuery);
    }

    public void deleteAllTrips() {
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM trips");
        } catch (SQLException ex) {
            logger.error("Deleting trips failed.", ex);
        }
    }
}
