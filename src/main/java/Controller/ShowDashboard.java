package Controller;

import Model.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "showDashboardServlet", value = "/show-dashboard-servlet")
public class ShowDashboard extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if(user != null && user.isAdmin().equalsIgnoreCase("true")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin/admin-dashboard.jsp");
            dispatcher.forward(request,response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/errorPage.jsp");
            request.setAttribute("type","error");
            request.setAttribute("msg","Errore");
            request.setAttribute("redirect","index.jsp");
            dispatcher.forward(request,response);
        }

    }
}
