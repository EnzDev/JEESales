package fr.enzomallard.app.servlets;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.User;
import fr.enzomallard.app.dao.IUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Register.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Register request (POST) with email \"" + request.getParameter("email") + "\"");

        IUserDao userDao = DaoFactory.getInstance().getUserDao();
        boolean isRight = false;
        User newUser = new User();

        String[] passwords = request.getParameterValues("password");
        if (passwords.length == 2 && passwords[0].equals(passwords[1]) && !"".equals(request.getParameter("email"))) {
            newUser.setId(request.getParameter("email"));
            newUser.setPassword(passwords[0]);
            newUser.setAdministrateur(false);

            isRight = userDao.create(newUser);
        }

        if (!isRight) {
            request.setAttribute("error", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", userDao.get(newUser.getId()));
            request.getSession().setAttribute("isLoggedIn", true);
            response.sendRedirect("/account");
        }
    }
}
