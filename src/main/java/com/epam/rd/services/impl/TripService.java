package com.epam.rd.services.impl;

import com.epam.rd.dao.impl.SimpleDaoFactory;
import com.epam.rd.models.Trip;
import com.epam.rd.services.Service;

import java.util.List;

public class TripService implements Service<Trip> {
    @Override
    public boolean create(Trip entity) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().create(entity);
    }

    @Override
    public Trip read(int id) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().read(id);
    }

    @Override
    public boolean update(Trip entity) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().update(entity);
    }

    @Override
    public boolean delete(int id) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().delete(id);
    }

    @Override
    public List<Trip> getAll() {
        return SimpleDaoFactory.getDaoFactory().getTripDao().getAll();
    }

    @Override
    public int getCount() {
        return SimpleDaoFactory.getDaoFactory().getTripDao().getCount();
    }

    public List<Trip> getRangeOfTrips(int startIndex, int count) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().getRangeOfTrips(startIndex, count);
    }

    public List<Trip> getUserTrips(int userId) {
        return SimpleDaoFactory.getDaoFactory().getTripDao().getAllUserTrips(userId);
    }

    public void deleteAllTrips() {
        SimpleDaoFactory.getDaoFactory().getTripDao().deleteAllTrips();
    }

    public List<Trip> getTripsByStatus(String status) {
       return SimpleDaoFactory.getDaoFactory().getTripDao().getTripsByStatus(status);
    }
    public void updateTripStatus(int trip_id, String status) {
        SimpleDaoFactory.getDaoFactory().getTripDao().updateTripStatus(trip_id, status);
    }


    public void updateUserTrip(int trip_id, int user_id) {
        SimpleDaoFactory.getDaoFactory().getTripDao().updateTripUser(trip_id, user_id);
    }

    public void updateTripCar(int trip_id, int car_id){
        SimpleDaoFactory.getDaoFactory().getTripDao().updateTripCar(trip_id, car_id);
    }

    public List<Trip> getTripsWithoutUser() {
        return SimpleDaoFactory.getDaoFactory().getTripDao().getTripsWithoutUser();
    }

}
