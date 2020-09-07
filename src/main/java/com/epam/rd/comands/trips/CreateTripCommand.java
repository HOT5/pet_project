package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Car;
import com.epam.rd.models.Trip;
import com.epam.rd.models.buiders.TripBuilder;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateTripCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(CreateTripCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")) {
            Trip trip = createTripFromRequest(request);
            SimpleServiceFactory.getServiceFactory()
                    .getTripService()
                    .create(trip);

            logger.info("New trip created!");
            return ConfigManager.getProperty("path.page.trips");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }

    private Trip createTripFromRequest(HttpServletRequest request) {
        return new TripBuilder()
                .name(request.getParameter("tripname"))
                .status(Trip.Status.OPEN)
                .type(Car.CarType.valueOf(request.getParameter("tripcartype")))
                .createTrip();
    }

}
