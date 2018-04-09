package fr.enzomallard.app.servlets;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.JspHelper;
import fr.enzomallard.app.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Enzo Mallard
 * The 08/04/2018
 */
@WebServlet(name = "AccountServlet", value = "/account")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (JspHelper.getSessionBoolean(request, "isLoggedIn")) {
            boolean validR = request.getParameterMap().keySet().containsAll(Arrays.asList("id", "name", "password", "phone", "admin"))
                    && request.getParameterMap().size() == 5;

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String[] password = request.getParameterValues("password");
            String phone = request.getParameter("phone");
            String admin = request.getParameter("admin");

            User loggeduser = JspHelper.getSessionObject(request, "user");
            User user = DaoFactory.getInstance().getUserDao().get(id);

            if (user != null && (loggeduser.isAdministrateur() || user.equals(loggeduser))) {
                if (name != null && !"".equals(name)) user.setNom(name);
                if (!"".equals(password[0]) && password[0] != null && password.length == 2 && password[0].equals(password[1])) // Change password if they match
                    user.setPassword(password[0]);
                if (phone != null && !"".equals(phone)) user.setTelephone(phone);
                if (loggeduser.isAdministrateur() && !loggeduser.getId().equals(id) && !user.getId().equals("admin@epsi.fr"))
                    // Change admin state if logged user is admin but not if the uid is admin@epsi.fr or his own id
                    user.setAdministrateur("on".equals(admin));

                DaoFactory.getInstance().getUserDao().update(user);

                if (user.equals(loggeduser)) request.getSession().setAttribute("user", user); // Update session user
                if (loggeduser.isAdministrateur())
                    response.sendRedirect("/admin");
                else
                    response.sendRedirect("/account?userid=" + id);
            } else
                response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (JspHelper.getSessionBoolean(request, "isLoggedIn")) {
            User loggeduser = JspHelper.getSessionObject(request, "user");
            if (request.getParameter("userid") != null && loggeduser.isAdministrateur()) {
                User editUser = DaoFactory.getInstance().getUserDao().get(request.getParameter("userid"));
                request.setAttribute("editUser", (editUser == null) ? loggeduser : editUser);
            } else {
                request.setAttribute("editUser", loggeduser);
            }
            request.getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);

        }else {
            response.sendRedirect("/login");
        }
    }
}
