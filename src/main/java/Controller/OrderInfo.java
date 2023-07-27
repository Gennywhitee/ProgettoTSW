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

@WebServlet(name = "orderInfoServlet", value = "/order-info-servlet")
public class OrderInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
        String path = "";
        if(currentUser == null){
            path="/index.jsp";
            request.setAttribute("type","alert");
            request.setAttribute("msg","Non puoi visualizzare gli ordini se non sei loggato");
            request.setAttribute("redirect",path);
        }
        else{
            OrderDAO orderDAO = new OrderDAO();
            OrderBean orderBean = orderDAO.doRetrieveByIdOrder(orderId);

            if(currentUser.isAdmin().equalsIgnoreCase("false") || currentUser.getId() != orderBean.getUser()){
                path="/index.jsp";
                request.setAttribute("type","alert");
                request.setAttribute("msg","Non puoi visualizzare gli ordini di altri utenti");
                request.setAttribute("redirect",path);
            }
            else{
                if(orderBean == null){
                    path="/index.jsp";
                    request.setAttribute("type","alert");
                    request.setAttribute("msg","Ordine non trovato nel DB");
                    request.setAttribute("redirect",path);
                }
                else{
                    ProductDAO productDAO = new ProductDAO();
                    ArrayList<ProductBean> catalogo = productDAO.doRetrieveAll();
                    OrderProductDAO orderProductDAO = new OrderProductDAO();
                    ArrayList<OrderProductBean> prodottiOrdine = orderProductDAO.doRetrieveById(orderId);
                    path="/WEB-INF/results/order-details.jsp";
                    request.setAttribute("catalog",catalogo);
                    request.setAttribute("prodottiOrdine",prodottiOrdine);
                    request.setAttribute("type","success");
                    request.setAttribute("redirect",path);
                    }
                }
            }
        dispatcher.forward(request,response);
    }
}
