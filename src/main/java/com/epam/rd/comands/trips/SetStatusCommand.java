package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Trip;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetStatusCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(SetStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN") ) {

            Integer tripId = Integer.valueOf(session.getAttribute("updateTripId").toString());
            Trip.Status status = Trip.Status.valueOf(request.getParameter("tripstatus"));

            SimpleServiceFactory.getServiceFactory().getTripService().updateTripStatus(tripId, status.toString());

            logger.info(String.format("Trip %d status set to %s ", tripId,status));

            return ConfigManager.getProperty("path.page.trips");

        }
        else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
