package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetCreateTripCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetCreateTripCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")) {
            logger.info("Redirect to create trip command");

            return ConfigManager.getProperty("path.page.trip");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}

