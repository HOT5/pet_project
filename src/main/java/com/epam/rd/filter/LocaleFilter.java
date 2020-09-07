package com.epam.rd.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.LogRecord;

public class LocaleFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);
    private LocaleHolder localeHolder = new LocaleHolder();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = ((HttpServletRequest) req);
        httpReq.setCharacterEncoding("UTF-8");
        HttpSession session = httpReq.getSession();
        if (session.getAttribute("bundleFile") == null) {
            session.setAttribute("bundleFile", "messages.messages");
        }
        Locale locale = null;
        String localeName = req.getParameter("lang");
        if (localeName == null) {
            locale = (Locale) session.getAttribute("locale");
            if (locale == null) {
                locale = localeHolder.getDefaultLocale();
            }
        } else {
            locale = findLocale(localeName);
        }
        logger.info(String.format("User locale is: %s", locale.toString()));
        httpReq.setAttribute("locale", locale);
        session.setAttribute("locale", locale);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    private Locale findLocale(String localeName) {
        for (Locale locale : LocaleHolder.locales) {
            if (locale.getLanguage().equals(localeName)) {
                localeHolder.setCurrentLocale(locale);
                return locale;
            }
        }
        return localeHolder.getDefaultLocale();
    }
}
