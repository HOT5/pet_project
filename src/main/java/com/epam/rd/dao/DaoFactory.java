package com.epam.rd.dao;

import com.epam.rd.dao.impl.CarDao;
import com.epam.rd.dao.impl.TripDao;
import com.epam.rd.dao.impl.UserDao;

public interface DaoFactory {

    UserDao getUserDao();

    CarDao getCarDao();

    TripDao getTripDao();

}
