package fr.enzomallard.app.servlets.offer;

import fr.enzomallard.app.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyOffersServlet", value = "/offers")
public class MyOffersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 08/03/18 Add a new offer as logged user
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (JspHelper.getSessionBoolean(request, "isLoggedIn")){
            // Serve if logged
            request.getRequestDispatcher("mysales.jsp");
        }else{ // Else, redirect to the login page
            response.sendRedirect("/login");
        }
    }
}
