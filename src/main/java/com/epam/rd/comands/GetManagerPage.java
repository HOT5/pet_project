package com.epam.rd.comands;

import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetManagerPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role").equals("MANAGER")) {
            return ConfigManager.getProperty("path.page.manager");
        } else {
            return ConfigManager.getProperty("path.page.index");
        }
    }
}
