package com.epam.rd.dto;

import com.epam.rd.models.Car;

import java.io.Serializable;
import java.util.Objects;

public class CarDto implements  Serializable {

    private int id;
    private String brand;
    private String model;
    private Car.CarType type;
    private boolean serviceable;

    public CarDto() {

    }

    public CarDto(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.type = car.getType();
        this.serviceable = car.isServiceable();
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Car.CarType getType() {
        return type;
    }

    public boolean isServiceable() {
        return serviceable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return id == carDto.id &&
                serviceable == carDto.serviceable &&
                Objects.equals(brand, carDto.brand) &&
                Objects.equals(model, carDto.model) &&
                type == carDto.type;
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
