package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Trip;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class GetAllTripsCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(GetAllTripsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sort = request.getParameter("sort");

        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")) {

            String sortStatus = request.getParameter("type");
            List<Trip> trips = getAllTrips(sortStatus);

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
            return ConfigManager.getProperty("path.page.trips");
        } else if (session.getAttribute("role").equals("CLIENT")) {
            List<Trip> trips = SimpleServiceFactory.getServiceFactory().getTripService().getTripsWithoutUser();

            session.setAttribute("trips", trips);
            logger.info(trips.toString());
            logger.info("Get all trips command finished.");
            return ConfigManager.getProperty("path.page.trips");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }

    static List<Trip> sortById(List<Trip> trips) {

        Comparator<Trip> idComparator = Comparator.comparing(Trip::getId);
        trips.sort(idComparator);
        return trips;
    }

    static List<Trip> sortByDate(List<Trip> trips) {

        Comparator<Trip> dateComparator = Comparator.comparing(Trip::getCreated);
        trips.sort(dateComparator);
        return trips;
    }

    private List<Trip> getAllTrips(String sortStatus) {
        List<Trip> trips;

        if (sortStatus != null) {
            logger.info("Getting all trips by status.");
            trips = SimpleServiceFactory.getServiceFactory().getTripService().getTripsByStatus(sortStatus);
        } else {
            logger.info("Getting all trips by default.");
            trips = SimpleServiceFactory.getServiceFactory().getTripService()
                    .getAll();
        }

        return trips;
    }
}
