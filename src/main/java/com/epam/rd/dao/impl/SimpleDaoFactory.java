package com.epam.rd.dao.impl;

import com.epam.rd.dao.DaoFactory;
import com.epam.rd.services.impl.SimpleServiceFactory;

public class SimpleDaoFactory implements DaoFactory {
    private static final SimpleDaoFactory factory = new SimpleDaoFactory();
    private final UserDao userDao = new UserDao();
    private final CarDao carDao = new CarDao();
    private final TripDao tripDao = new TripDao();

    public static SimpleDaoFactory getDaoFactory() {
        return factory;
    }


    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public CarDao getCarDao() {
        return carDao;
    }

    @Override
    public TripDao getTripDao() {
        return tripDao;
    }
}
