package com.epam.rd.comands;

import com.epam.rd.resource.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigManager.getProperty("path.page.index");
    }
}
