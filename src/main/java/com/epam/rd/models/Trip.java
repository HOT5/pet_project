package com.epam.rd.models;

import com.epam.rd.models.buiders.TripBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Trip implements Entity, Serializable {

    private int id;
    private String name;
    private Date created;
    private Status status;
    private Car.CarType type;
    private Car car;
    private User user;



    public enum Status {
        OPEN, INPROGRESS, CLOSED, CANCELED
    }

    public Trip() {

    }



    public Trip(TripBuilder builder) {
        this.id = builder.id;
        this.name =builder.name;
        this.created = builder.created;
        this.status = builder.status;
        this.type = builder.type;
        this.car = builder.car;
        this.user = builder.user;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    public void setCreated(Date created) {
        this.created = created;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setType(Car.CarType type) {
        this.type = type;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }

    public Car.CarType getType() {
        return type;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                Objects.equals(name, trip.name) &&
                Objects.equals(created, trip.created) &&
                status == trip.status &&
                type == trip.type &&
                Objects.equals(car, trip.car) &&
                Objects.equals(user, trip.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, status, type, car, user);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", status=" + status +
                ", type=" + type +
                ", car=" + car +
                ", user=" + user +
                '}';
    }
}
