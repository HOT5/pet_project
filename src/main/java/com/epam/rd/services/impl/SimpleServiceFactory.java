package com.epam.rd.services.impl;

import com.epam.rd.services.ServiceFactory;

public class SimpleServiceFactory implements ServiceFactory {

    private static SimpleServiceFactory factory = new SimpleServiceFactory();

    public static SimpleServiceFactory getServiceFactory() {
        return factory;
    }


    @Override
    public UserService getUserService() {
        return new UserService();
    }

    @Override
    public CarService getCarService() {
        return new CarService();
    }

    @Override
    public TripService getTripService() {
        return new TripService();
    }
}
