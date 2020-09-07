package com.epam.rd.comands.user;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.models.User;
import com.epam.rd.models.buiders.UserBuilder;
import com.epam.rd.resource.ConfigManager;
import com.epam.rd.resource.MessageManager;
import com.epam.rd.services.impl.SimpleServiceFactory;
import com.epam.rd.util.HashPassword;
import com.epam.rd.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {
    private final Logger logger = LoggerFactory.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        User user = createUserFromRequest(request);
        String validUser = validateUser(user);
        if (!validUser.equals("valid")) {
            request.setAttribute("errorMessage", validUser);
            logger.info("User validation failure.");
            page = ConfigManager.getProperty("path.page.registration");
            return page;
        }
        user.setPassword(HashPassword.hashPassword(user.getPassword()));

        if (Boolean.parseBoolean(request.getParameter("isManager"))) {
            user.setRole(User.Role.MANAGER);
        }

        boolean createUser = SimpleServiceFactory.getServiceFactory()
                .getUserService()
                .create(user);
        if (!createUser) {
            request.setAttribute("errorMessage", MessageManager.getProperty("message.user.creation.failure"));
            logger.info("Insert user to database failed");
            page = ConfigManager.getProperty("path.page.dberror");
            return page;
        }

        page = ConfigManager.getProperty("path.page.success");
        logger.info("New user added to database.");
        HttpSession session = request.getSession();
        setUserInfo(user, session);
        return page;
    }

    static User createUserFromRequest(HttpServletRequest request) {
        return new UserBuilder()
                .firstName(request.getParameter("firstname"))
                .lastName(request.getParameter("lastname"))
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .createUser();
    }

    static String validateUser(User user) {
        Validator validator = Validator.getInstance();
        if (!validator.isPasswordValid(user.getPassword())) {
            return MessageManager.getProperty("message.invalid.password");
        }
        if (!validator.isEmailValid(user.getEmail())) {
            return MessageManager.getProperty("message.invalid.email");
        }
        if (!validator.isLoginValid(user.getLogin())) {
            return MessageManager.getProperty("message.invalid.login");
        }
        if (!validator.isFirstNameValid(user.getFirstName())) {
            return MessageManager.getProperty("message.invalid.firstname");
        }
        if (!validator.isLastNameValid(user.getLastName())) {
            return MessageManager.getProperty("message.invalid.lastname");
        }
        return "valid";
    }

    private void setUserInfo(User user, HttpSession session) {
        session.setAttribute("firstname", user.getFirstName());
        session.setAttribute("lastname", user.getLastName());
    }

}
