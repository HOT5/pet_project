package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Car;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.resource.MessageManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.epam.rd.comands.cars.CreateCarCommand.createCarFromRequest;

public class UpdateCarCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(UpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        Integer carId = Integer.valueOf(request.getParameter("updateCarId"));
        if (session.getAttribute("role").equals("ADMIN")) {
            Car car = createCarFromRequest(request);
            car.setId(carId);
            boolean updateRes = SimpleServiceFactory.getServiceFactory()
                    .getCarService()
                    .update(car);
            if (!updateRes) {
                request.setAttribute("errorMessage", MessageManager.getProperty("message.car.updating.failure"));
                logger.info("Insert car to database failed");
                page = ConfigManager.getProperty("path.page.dberror");
                return page;
            }
            page = ConfigManager.getProperty("path.page.admin");
            logger.info("Car updating successful.");
            return page;

        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}

