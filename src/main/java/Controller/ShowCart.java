package Controller;

import Model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "showCartServlet", value = "/show-cart-servlet")
public class ShowCart extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        CartDAO cartDAO = new CartDAO();
        ArrayList<ProductCartBean> cartList = cartDAO.getCart(user.getId());
        ProductDAO productDAO = new ProductDAO();

        JSONArray array = new JSONArray();

        for (ProductCartBean product: cartList) {

            ProductBean productCatalog = productDAO.doRetrieveById(product.getId());
            JSONObject obj = new JSONObject();

            obj.put("productID", productCatalog.getId());
            obj.put("cartID", product.getId());
            obj.put("name", productCatalog.getName());
            obj.put("image", productCatalog.getImage());
            obj.put("price", productCatalog.getPrice());
            obj.put("quantity", product.getQuantity());

            array.put(obj);
        }
        request.setAttribute("cart",cartList);
        request.setAttribute("array",array);
    }

}