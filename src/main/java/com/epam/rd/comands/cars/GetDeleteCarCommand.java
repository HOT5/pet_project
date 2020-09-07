package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetDeleteCarCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetDeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String carId = request.getParameter("deleteCarId");
        if (session.getAttribute("role").equals("ADMIN") && carId != null) {
            request.getSession().setAttribute("deleteCarId", carId);
            logger.info("Redirect to delete car command");
            return ConfigManager.getProperty("path.page.deletecar");
        } else {
            return ConfigManager.getProperty("path.page.cars");
        }
    }
}

