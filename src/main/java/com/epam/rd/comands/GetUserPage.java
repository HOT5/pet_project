package com.epam.rd.comands;

import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUserPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("CLIENT")) {
            return ConfigManager.getProperty("path.page.user");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
