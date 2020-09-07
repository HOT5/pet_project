package com.epam.rd.controller;

import com.epam.rd.comands.ActionCommand;
import com.epam.rd.comands.ActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ManagerServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(ManagerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String key = getMethod(request) + ":" + getUri(request);
        ActionCommand command = ActionFactory.getActionFactory().defineCommand(key);
        String page = command.execute(request);
        logger.info(String.format("Servlet get page: %s", page));

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }

    private String getUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.toLowerCase();
    }
}
