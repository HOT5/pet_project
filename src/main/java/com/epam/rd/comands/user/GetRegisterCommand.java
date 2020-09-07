package com.epam.rd.comands.user;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class GetRegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        page = ConfigManager.getProperty("path.page.registration");
        return page;
    }
}
