package com.epam.rd.tags;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserDataTag extends TagSupport {
    private static final Logger logger = LoggerFactory.getLogger(UserDataTag.class);

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String firstname = session.getAttribute("firstname").toString();
        String lastname = session.getAttribute("lastname").toString();
        try {
            JspWriter writer = pageContext.getOut();
            writer.write(String.format("<b> %s %s </b>", firstname, lastname));
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
