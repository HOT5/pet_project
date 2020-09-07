package com.epam.rd.services.impl;

import com.epam.rd.dao.impl.SimpleDaoFactory;
import com.epam.rd.models.Car;
import com.epam.rd.services.Service;

import java.util.List;

public class CarService implements Service<Car> {
    @Override
    public boolean create(Car entity) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().create(entity);
    }

    @Override
    public Car read(int id) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().read(id);
    }

    @Override
    public boolean update(Car entity) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().update(entity);
    }

    @Override
    public boolean delete(int id) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().delete(id);
    }

    @Override
    public List<Car> getAll() {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getAll();
    }

    @Override
    public int getCount() {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getCount();
    }
    public List<String> getCarTypes() {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getCarOption("type");
    }

    public List<String> getCarBrand() {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getCarOption("brand");
    }

    public List<Car> getRangeOfCars(int startIndex, int count) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getRangeOfCars(startIndex, count);
    }

    public List<Car> getAllWithType(String value) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getAllByOptionValue("type", value);
    }

    public List<Car> getAllWithBrand(String value) {
        return SimpleDaoFactory.getDaoFactory().getCarDao().getAllByOptionValue("brand", value);
    }
}
