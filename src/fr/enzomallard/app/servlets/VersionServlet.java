package fr.enzomallard.app.servlets;

import fr.enzomallard.app.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/VersionServlet",name = "deleteMe")
public class VersionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("version", Constants.VERSION);
        request
                .getRequestDispatcher("version.jsp")
                .forward(request, response);
    }
}
