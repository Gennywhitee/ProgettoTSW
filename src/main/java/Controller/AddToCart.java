package Controller;

import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "addToCartServlet", value = "/add-to-cart-servlet")
public class AddToCart extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cart");
        CartDAO cartDAO = new CartDAO();

        UserBean userBean = (UserBean) session.getAttribute("user");


        request.setAttribute("cart",cartBean);
    }
}
