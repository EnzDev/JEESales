package fr.enzomallard.app.servlets;

import fr.enzomallard.app.JspHelper;
import fr.enzomallard.app.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="Admin", value="/admin")
public class Admin extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Admin.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (JspHelper.getSessionBoolean(request, "isLoggedIn"))
            if (JspHelper.<User>getSessionObject(request, "user").isAdministrateur())
                request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            else
                response.sendRedirect("/");
        else
            response.sendRedirect("/login");
    }
}
