package com.epam.rd.models.buiders;

import com.epam.rd.models.Car;

public class CarBuilder {
    public int id;
    public String brand;
    public String model;
    public Car.CarType type;
    public boolean serviceable;

    public CarBuilder() {
    }

    public CarBuilder id(int id) {
        this.id = id;
        return this;
    }

    public CarBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder type(Car.CarType type) {
        this.type = type;
        return this;
    }

    public CarBuilder serviceable(boolean serviceable){
        this.serviceable = serviceable;
        return this;
    }

    public Car createCar() {
        return new Car(this);
    }
}
