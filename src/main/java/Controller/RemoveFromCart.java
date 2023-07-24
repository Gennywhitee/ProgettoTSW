package Controller;

import Model.ProductCartBean;
import Model.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "removeFromCartServlet", value = "/remove-from-cart-servlet")
public class RemoveFromCart extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        ProductDAO productDAO = new ProductDAO();
        ProductCartBean productCartBean = (ProductCartBean) session.getAttribute("cartID");
    }
}
