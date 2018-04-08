package fr.enzomallard.app.servlets;

import fr.enzomallard.app.JspHelper;
import fr.enzomallard.app.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Logout.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = JspHelper.getSessionObject(request, "user");
        logger.info("Logout (GET) for email \"" + (user == null ? "NOT LOGGED" : user.getId()) + "\"");
        request.getSession().invalidate();
        response.sendRedirect("/");
    }
}
