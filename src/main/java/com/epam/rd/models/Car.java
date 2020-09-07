package com.epam.rd.models;

import com.epam.rd.models.Entity;
import com.epam.rd.models.buiders.CarBuilder;

import java.io.Serializable;

public class Car implements Entity, Serializable {

    private int id;
    private String brand;
    private String model;
    private CarType type;
    private boolean serviceable;

    public enum CarType {
        Sedan, Wagon, Coupe, SUV, Electric
    }

    public Car() {

    }

    public Car(CarBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.type = builder.type;
        this.serviceable = builder.serviceable;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setServiceable(boolean serviceable) {
        this.serviceable = serviceable;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public boolean isServiceable() {
        return serviceable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return type == car.type;
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type=" + type +
                '}';
    }
}
