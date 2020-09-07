package com.epam.rd.comands.user;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.converter.UserConverter;
import com.epam.rd.dto.UserDto;
import com.epam.rd.models.User;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsersCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(GetAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("ADMIN")) {
            logger.info("Getting all users");
            List<UserDto> userList = UserConverter.usersView(SimpleServiceFactory.getServiceFactory()
                    .getUserService()
                    .getAll());


            logger.info(userList.toString());

            session.setAttribute("users", userList);
            return ConfigManager.getProperty("path.page.users");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
