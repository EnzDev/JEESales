package fr.enzomallard.app.servlets.offer;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.JspHelper;
import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.Status;
import fr.enzomallard.app.beans.User;
import fr.enzomallard.app.dao.ISaleDao;
import fr.enzomallard.app.dao.SqlSaleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@WebServlet(name = "MyOfferEditorServlet", value = "/offer/edit")
public class MyOfferEditorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 08/03/18 Effectively edit the selected offer
        int id = 0;
        if (request.getParameter("id") != null) id = Integer.parseInt(request.getParameter("id"));
        boolean hasAll =
                request.getParameterMap().keySet().containsAll(Arrays.asList("id", "title", "description", "price", "do"))
                        && request.getParameterMap().size() == 5;
        Function<String, String> params = (String name)->request.getParameterMap().get(name)[0];
        if (hasAll && JspHelper.getSessionBoolean(request, "isLoggedIn")) { // Al modifiers are present (prevent manual injection)
            ISaleDao dao = DaoFactory.getInstance().getSaleDao();
            Sale offer = dao.get(Integer.parseInt(params.apply("id")));
            switch (params.apply("do")){
                case "Publish":
                    offer.setStatut(Status.PUBLISHED);
                case "Save":
                    offer.setTitre(params.apply("title"));
                    offer.setDescription(params.apply("description"));
                    offer.setPrix(Double.parseDouble(params.apply("price")));
                default:
                    dao.update(offer);
            }
        }
        response.sendRedirect("/offer/view?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String whattodo = request.getParameter("do");

        if (JspHelper.getSessionBoolean(request, "isLoggedIn")) {
            if (request.getParameter("id") != null) {
                int offerId = Integer.parseInt(request.getParameter("id"));
                User user = JspHelper.<User>getSessionObject(request, "user");
                String userId = user.getId();
                Sale offer = DaoFactory.getInstance().getSaleDao().get(offerId);

                if (offer != null && (userId.equals(offer.getVendeur().getId()) || user.isAdministrateur()) && (whattodo == null || whattodo.equals(""))) {
                    // Serve if the sale exist and sale's owner is the connected user
                    Sale s = DaoFactory.getInstance().getSaleDao().get(offerId);
                    request.setAttribute("sale", s);
                    request.getRequestDispatcher("/WEB-INF/saleeditor.jsp").forward(request, response);
                } else if (offer != null) {// Else, see whattodo
                    switch (whattodo) {
                        case "buy":
                            if (!userId.equals(offer.getVendeur().getId()) && offer.getStatus() != Status.SOLD) {
                                offer.setStatut(Status.SOLD);
                                offer.setAcheteur(JspHelper.getSessionObject(request, "user"));
                                offer.setAchat(new Date());
                                DaoFactory.getInstance().getSaleDao().update(offer);
                            }
                            break;
                        case "delete":
                            if (userId.equals(offer.getVendeur().getId())) {
                                offer.setStatut(Status.CANCELED);
                                DaoFactory.getInstance().getSaleDao().update(offer);
                            }
                            break;
                        case "publish":
                            if (userId.equals(offer.getVendeur().getId())) {
                                offer.setStatut(Status.PUBLISHED);
                                DaoFactory.getInstance().getSaleDao().update(offer);
                            }
                            break;
                        default: // Nothing to do

                    }
                    response.sendRedirect("/");
                } else
                    response.sendRedirect("/");
            }
        } else { // Else, redirect to the login page
            response.sendRedirect("/login");
        }
    }
}
