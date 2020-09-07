package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.comands.trips.CreateTripCommand;
import com.epam.rd.dao.impl.SimpleDaoFactory;
import com.epam.rd.models.Car;
import com.epam.rd.models.User;
import com.epam.rd.models.buiders.CarBuilder;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.resource.MessageManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateCarCommand implements ActionCommand {

    private final static Logger logger = LoggerFactory.getLogger(CreateCarCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("ADMIN")) {
            Car car = createCarFromRequest(request);
            boolean createRes = SimpleServiceFactory.getServiceFactory()
                    .getCarService()
                    .create(car);
            if (!createRes) {
                request.setAttribute("errorMessage", MessageManager.getProperty("message.car.creating.failure"));
                logger.info("Insert car to database failed");
                page = ConfigManager.getProperty("path.page.dberror");
                return page;
            }
            page = ConfigManager.getProperty("path.page.admin");
            logger.info("New carr added to database.");
            return page;
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }

    static Car createCarFromRequest(HttpServletRequest request) {
        return new CarBuilder()
                .brand(request.getParameter("carbrand"))
                .model(request.getParameter("carmodel"))
                .type(Car.CarType.valueOf(request.getParameter("cartype")))
                .serviceable(isService(request.getParameter("isServiceable")))
                .createCar();
    }

    private static boolean isService(String service) {
        logger.info(service);
        return Boolean.parseBoolean(service);
    }
}
