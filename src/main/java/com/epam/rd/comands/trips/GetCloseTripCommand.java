package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Trip;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetCloseTripCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(GetCloseTripCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("role").equals("CLIENT")) {

            Integer tripId = Integer.valueOf(request.getParameter("updateTripId"));

            SimpleServiceFactory.getServiceFactory().getTripService().updateTripStatus(tripId, Trip.Status.CLOSED.toString());

            logger.info(String.format("Trip %d status set to %s ", tripId, Trip.Status.CLOSED.toString()));

            return ConfigManager.getProperty("path.page.usertrips");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }

    }

}

