package fr.enzomallard.app.servlets.offer;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.JspHelper;
import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.Status;
import fr.enzomallard.app.dao.ISaleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Enzo Mallard
 * The 08/04/2018
 */

@WebServlet(name = "CreateOfferServlet", value = "/offer/create")
public class CreateOfferServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (JspHelper.getSessionBoolean(request, "isLoggedIn")) {
            ISaleDao daoSale = DaoFactory.getInstance().getSaleDao();
            Sale newSale = new Sale();
            newSale.setStatut(Status.TEMPORARY);
            newSale.setAchat(null);
            newSale.setId(daoSale.size()+1);
            newSale.setNbVues(0);
            newSale.setTitre("");
            newSale.setCreation(new Date());
            newSale.setModification(new Date());
            newSale.setPrix(0d);
            newSale.setDescription("");
            newSale.setVendeur(JspHelper.getSessionObject(request, "user"));
            daoSale.create(newSale);

            response.sendRedirect("/offer/edit?id=" + newSale.getId());
        }else{
            response.sendRedirect("/");
        }
    }
}
