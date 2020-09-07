package com.epam.rd.comands.trips;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetSetStatusCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetSetStatusCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("MANAGER") || session.getAttribute("role").equals("ADMIN")) {
            request.getSession().setAttribute("updateTripId",request.getParameter("updateTripId"));
            logger.info("Redirect to set status command");

            return ConfigManager.getProperty("path.page.setstatus");
        }
        else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
