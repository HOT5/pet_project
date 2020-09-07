package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.Car;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetSetCarCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetSetCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String carType = request.getParameter("carType");
        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")  ) {
            request.getSession().setAttribute("updateTripId",request.getParameter("updateTripId"));
            List<Car> cars = SimpleServiceFactory.getServiceFactory().getCarService().getAllWithType(carType);
            session.setAttribute("cars", cars);
            logger.info("Redirect to update trip car command");
            return ConfigManager.getProperty("path.page.setcar");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
