package com.epam.rd.comands.user;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.User;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.resource.MessageManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import com.epam.rd.util.HashPassword;
import com.epam.rd.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = SimpleServiceFactory.getServiceFactory()
                .getUserService()
                .getByLogin(login);

        if (user == null) {
            logger.info("User not found.");
            request.setAttribute("errorMessage", MessageManager.getProperty("message.login.usernotfound"));
            return ConfigManager.getProperty("path.page.login");
        }

        if (!HashPassword.checkPassword(password, user.getPassword())) {
            logger.info("Authentication failed. Incorrect password.");
            request.setAttribute("errorMessage", MessageManager.getProperty("message.login.error"));
            return ConfigManager.getProperty("path.page.login");
        }


        logger.info("Authorisation success.");
        HttpSession session = request.getSession();
        setUserInfo(user, session);
        if (user.getRole().equals(User.Role.ADMIN)) {
            page = ConfigManager.getProperty("path.page.admin");
        } else if (user.getRole().equals(User.Role.MANAGER)) {
            page = ConfigManager.getProperty("path.page.manager");
        } else {
            page = ConfigManager.getProperty("path.page.user");
        }

        return page;
    }

    private void setUserInfo(User user, HttpSession session) {
        session.setAttribute("firstname", user.getFirstName());
        session.setAttribute("lastname", user.getLastName());
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole().toString());
    }
}
