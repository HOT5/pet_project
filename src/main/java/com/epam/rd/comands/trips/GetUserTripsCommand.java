package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Trip;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.rd.comands.trips.GetAllTripsCommand.sortById;
import static com.epam.rd.comands.trips.GetAllTripsCommand.sortByDate;

public class GetUserTripsCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(GetUserTripsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("CLIENT")) {
            String sort = request.getParameter("sort");
            Integer userId = Integer.valueOf(session.getAttribute("userId").toString());
            List<Trip> trips = SimpleServiceFactory.getServiceFactory().getTripService().getUserTrips(userId);

            if (sort != null && sort.equals("id")) {
                logger.info("Trips sorting by id");
                trips = sortById(trips);
            }
            if (sort != null && sort.equals("date")) {
                logger.info("Trips sorting by date");
                trips = sortByDate(trips);
            }


            session.setAttribute("trips", trips);
            logger.info("Get all trips command finished.");

            return ConfigManager.getProperty("path.page.usertrips");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
