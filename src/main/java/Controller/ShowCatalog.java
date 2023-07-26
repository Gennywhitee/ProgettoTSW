package Controller;

import Model.CategoryBean;
import Model.CategoryDAO;
import Model.ProductBean;
import Model.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "showCatalogServlet", value = "/show-catalog-servlet")
public class ShowCatalog extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ProductDAO productDAO = new ProductDAO();
        ArrayList<ProductBean> productList = productDAO.doRetrieveAll();

        request.setAttribute("prodotti",productList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request,response);
    }
}
