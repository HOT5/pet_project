package com.epam.rd.services;

import com.epam.rd.services.impl.CarService;
import com.epam.rd.services.impl.TripService;
import com.epam.rd.services.impl.UserService;

public interface ServiceFactory {

    UserService getUserService();

    CarService getCarService();

    TripService getTripService();

}
