package Controller;

import Model.ProductBean;
import Model.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "showProductServlet", value = "/show-product-servlet")
public class ShowProduct extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int productId = Integer.parseInt(request.getParameter("productId"));


        ProductDAO productDAO = new ProductDAO();
        ProductBean product = productDAO.doRetrieveById(productId);
        request.setAttribute("prodotto",product);

        response.sendRedirect("/WEB-INF/product.jsp");

    }
}
