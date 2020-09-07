package com.epam.rd.models.buiders;

import com.epam.rd.models.Car;
import com.epam.rd.models.Trip;
import com.epam.rd.models.User;

import java.util.Date;

public class TripBuilder {
    public int id;
    public String name;
    public Date created;
    public Trip.Status status;
    public Car.CarType type;
    public Car car;
    public User user;

    public TripBuilder() {
    }

    public TripBuilder id(int id) {
        this.id = id;
        return this;
    }

    public TripBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TripBuilder created(Date created) {
        this.created = created;
        return this;
    }

    public TripBuilder status(Trip.Status status) {
        this.status = status;
        return this;
    }

    public TripBuilder type(Car.CarType type) {
        this.type = type;
        return this;
    }

    public TripBuilder car(Car car) {
        this.car = car;
        return this;
    }

    public TripBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Trip createTrip() {
        return new Trip(this);
    }

}
