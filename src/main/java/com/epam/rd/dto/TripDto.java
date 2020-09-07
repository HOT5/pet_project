package com.epam.rd.dto;

import com.epam.rd.models.Car;
import com.epam.rd.models.Trip;
import com.epam.rd.models.User;
import com.epam.rd.models.buiders.TripBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TripDto implements Serializable {

    private int id;
    private String name;
    private Date created;
    private Trip.Status status;
    private Car.CarType type;
    private Car car;
    private User user;


    public TripDto() {

    }



    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.name =trip.getName();
        this.created = trip.getCreated();
        this.status = trip.getStatus();
        this.type = trip.getType();
        this.car = trip.getCar();
        this.user = trip.getUser();
    }



    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Trip.Status getStatus() {
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
        TripDto tripDto = (TripDto) o;
        return id == tripDto.id &&
                Objects.equals(name, tripDto.name) &&
                Objects.equals(created, tripDto.created) &&
                status == tripDto.status &&
                type == tripDto.type &&
                Objects.equals(car, tripDto.car) &&
                Objects.equals(user, tripDto.user);
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
