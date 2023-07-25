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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "showProductServlet", value = "/show-product-servlet")
public class ShowProduct extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        int prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
        ProductDAO productDAO = new ProductDAO();
        UserBean user = (UserBean) session.getAttribute("user");

        ProductBean prodotto = productDAO.doRetrieveById(prodottoId);

        if (prodotto == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("type", "alert");
            request.setAttribute("msg", "Prodotto non presente");
            request.setAttribute("redirect", "catalog.jsp");
            dispatcher.include(request, response);
        } else {
            session.setAttribute("prodotto", prodotto);
            if (user.isAdmin().equalsIgnoreCase("true")) {
                response.sendRedirect("WEB-INF/results/productInfo.jsp");
            } else {
                response.sendRedirect("WEB-INF/results/details.jsp");
            }
        }
    }
}