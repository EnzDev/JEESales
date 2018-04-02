package fr.enzomallard.app.servlets.offer;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.Sale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OfferViewerServlet", value = "/offer/view")
public class OfferViewerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 08/03/18 Display the selected offer
        // NOTE: 08/03/18 Check the ability for the user to see the offer
        int offerId = Integer.parseInt(request.getParameter("id"));
        if(offerId == 0){
            response.sendRedirect("/");
        }else{
            Sale s = DaoFactory.getInstance().getSaleDao().get(offerId);
            request.setAttribute("sale", s);
            request.getRequestDispatcher("/WEB-INF/sales.jsp").forward(request, response);
        }
    }
}
