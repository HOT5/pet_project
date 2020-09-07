package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class GetSetUserCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(GetSetUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Integer tripId = Integer.valueOf(request.getParameter("updateTripId"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        if (session.getAttribute("role").equals("CLIENT")) {
            SimpleServiceFactory.getServiceFactory().getTripService().updateUserTrip(tripId, userId);
            logger.info("User set successful.");
            return ConfigManager.getProperty("path.page.user");

        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}

