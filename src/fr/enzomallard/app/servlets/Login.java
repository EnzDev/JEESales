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

// @WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Login.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login request (POST) with email " + request.getParameter("email"));
        User tryUser = new User();
        tryUser.setId(request.getParameter("email"));
        tryUser.setPassword(request.getParameter("password"));

        boolean isRight = false;
        IUserDao IUserDao = DaoFactory.getInstance().getUserDao();
        //try {
        isRight = IUserDao.check(tryUser);
        //} catch (SQLException ignored) {} finally {
        if (!isRight) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("userdao", IUserDao.get(tryUser.getId()));
            request.getSession().setAttribute("isLoggedIn", true);
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login page request (GET)");
        // Get on /login => Serve the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
