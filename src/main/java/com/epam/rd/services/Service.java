package com.epam.rd.services;

import com.epam.rd.models.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    boolean create(T entity);

    T read(int id);

    boolean update(T entity);

    boolean delete(int id);

    List<T> getAll();

    int getCount();
}

