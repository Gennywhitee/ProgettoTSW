package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "addToCartServlet", value = "/add-to-cart-servlet")
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductDAO service = new ProductDAO();
        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cart");
        CartDAO serviceCart = new CartDAO();
        UserBean user = (UserBean) session.getAttribute("user");

        if (cartBean == null) //utente non registrato
            cartBean = new CartBean();

        ProductBean productBean = service.doRetrieveById(productId);

        if (productBean.getQuantity() >= quantity) {
            cartBean.addProduct(productId, quantity); //aggiungo nel carrello il prodotto
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
            request.setAttribute("type","success");
            request.setAttribute("msg","Oggetto inserito nel carrello");
            request.setAttribute("redirect","/show-catalog-servlet");
            dispatcher.forward(request,response);
        }
        else
            response.sendError(400);

        if (user != null) {
            serviceCart.doDelete(user.getId());
            for (ProductCartBean product : cartBean.getCartList()) {
                serviceCart.doSave(user.getId(), product.getId(), product.getQuantity());
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
            request.setAttribute("type","success");
            request.setAttribute("msg","Inserimento avvenuto con successo");
            request.setAttribute("redirect","/show-catalog-servlet");
            dispatcher.forward(request,response);
            session.setAttribute("cart", cartBean);
        }

    }
}