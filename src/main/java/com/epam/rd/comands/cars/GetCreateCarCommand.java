package com.epam.rd.comands.cars;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class GetCreateCarCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigManager.getProperty("path.page.newcar");
    }
}
