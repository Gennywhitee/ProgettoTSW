package Controller;

import Model.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "showProductServlet", value = "/show-product-servlet")
public class ShowProduct extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        ProductDAO productDAO = new ProductDAO();

    }
}
