package Controller;

import Model.*;
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
        HttpSession session = request.getSession();

        UserBean currentUser = (UserBean) session.getAttribute("user");

        ProductDAO productDAO = new ProductDAO();
        ArrayList<ProductBean> productList = productDAO.doRetrieveAll();

        request.setAttribute("prodotti",productList);
        if(currentUser != null && currentUser.isAdmin().equalsIgnoreCase("true")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin/show-products.jsp");
            dispatcher.forward(request,response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
            dispatcher.forward(request,response);
        }


    }
}
