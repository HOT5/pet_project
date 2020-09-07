package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Car;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.accessibility.Accessible;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetCarCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(SetCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")) {

            Integer tripId = Integer.valueOf(session.getAttribute("updateTripId").toString());
            Integer carId = Integer.valueOf(request.getParameter("carId"));
            SimpleServiceFactory.getServiceFactory().getTripService().updateTripCar(tripId, carId);

            logger.info(String.format("Trip %d sets cad %d ", tripId, carId));

            return ConfigManager.getProperty("path.page.trips");

        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
