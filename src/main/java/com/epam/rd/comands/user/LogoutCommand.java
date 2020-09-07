package com.epam.rd.comands.user;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            session.removeAttribute("role");
            session.removeAttribute("userId");
            session.removeAttribute("firstname");
            session.removeAttribute("lastname");
        }
        return ConfigManager.getProperty("path.page.index");
    }
}
