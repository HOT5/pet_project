package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCarCommand implements ActionCommand {

    private final Logger logger = LoggerFactory.getLogger(DeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("ADMIN")) {
            Integer carId = Integer.valueOf(request.getParameter("deleteCarId"));
            boolean deleteRes = SimpleServiceFactory.getServiceFactory()
                    .getCarService().delete(carId);
            if (!deleteRes) {
                logger.info("Deleting car from database failed");
                return ConfigManager.getProperty("path.page.dberror");
            }
            logger.info("Car deleted successful.");
            return ConfigManager.getProperty("path.page.admin");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
