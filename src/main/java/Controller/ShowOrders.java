package Controller;

import Model.OrderBean;
import Model.OrderDAO;
import Model.UserBean;
import Model.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "showOrdersServlet", value = "/show-orders-servlet")
public class ShowOrders extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        RequestDispatcher dispatcher;

        if(currentUser != null && currentUser.isAdmin().equalsIgnoreCase("true")){
            System.out.println("Entrato nell'if "+ currentUser.getNome());
            OrderDAO serviceOrder = new OrderDAO();
            ArrayList<OrderBean> orderList = serviceOrder.doRetrieveAll();
            request.setAttribute("orderList",orderList);
            request.setAttribute("type","success");
            request.setAttribute("msg","Vedrai gli ordini");
            dispatcher = request.getRequestDispatcher("/WEB-INF/admin/show-orders.jsp");
            dispatcher.forward(request,response);
        }
        else {
            request.setAttribute("type","alert");
            request.setAttribute("msg","Errore");
            request.setAttribute("redirect","/index.jsp");
            dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
            dispatcher.forward(request,response);
        }

    }
}
