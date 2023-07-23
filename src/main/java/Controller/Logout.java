package Controller;


import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "LogoutServlet", value = "/logout-servlet")
public class Logout extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        CartBean cart = (CartBean) session.getAttribute("cart");
        CartDAO cartDAO = new CartDAO();

        if(user.isAdmin().equalsIgnoreCase("false")){

            if(cart != null){ //controllo se il carrello non è vuoto, altrimenti verrebbe chiamato un'eccezione
                cartDAO.doDelete(user.getId());
                for(ProductCartBean product : cart.getCartList()){
                    cartDAO.doSave(user.getId(), product.getId(), product.getQuantity());
                }
            }
        }
        session.invalidate(); //cancello la sessione
        response.sendRedirect("index.jsp"); //rimando alla homepage

    }
}
