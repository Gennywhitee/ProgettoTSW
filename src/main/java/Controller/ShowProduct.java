package Controller;

import Model.ProductBean;
import Model.ProductDAO;
import Model.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "showProductServlet", value = "/show-product-servlet")
public class ShowProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int prodottoId = Integer.parseInt(request.getParameter("productId"));
        ProductDAO productDAO = new ProductDAO();

        ProductBean prodotto = productDAO.doRetrieveById(prodottoId);

        RequestDispatcher dispatcher;
        if (prodotto == null) {
            dispatcher = request.getRequestDispatcher("confirmPage.jsp");
            request.setAttribute("type", "alert");
            request.setAttribute("msg", "Prodotto non presente");
            request.setAttribute("redirect", "catalog.jsp");
            dispatcher.forward(request, response);
        }
        else { //Controllo se admin e invece di portare a add to cart, porta a edit-product

            request.setAttribute("prodotto",prodotto);
            dispatcher = request.getRequestDispatcher("/WEB-INF/results/details.jsp");
            dispatcher.forward(request,response);
        }
    }
}