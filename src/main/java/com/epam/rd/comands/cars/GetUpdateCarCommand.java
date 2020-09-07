package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUpdateCarCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetUpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String carId = request.getParameter("updateCarId");
        if (session.getAttribute("role").equals("ADMIN") && carId != null) {
            request.getSession().setAttribute("updateCarId", carId);
            logger.info("Redirect to update car command");
            return ConfigManager.getProperty("path.page.updatecar");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }    }
}
