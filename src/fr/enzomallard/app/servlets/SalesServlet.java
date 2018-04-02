package fr.enzomallard.app.servlets;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.Sale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sales", name = "SingleSaleServlet")
public class SalesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int saleId = Integer.parseInt(request.getParameter("id"));
        Sale found = DaoFactory.getInstance().getSaleDao().get(saleId);

        request.setAttribute("sale", found);
        request.getRequestDispatcher("saleview.jsp").forward(request, response);
    }
}
