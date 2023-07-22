package Controller;

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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", value = "/user-servet")
public class User extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean admin = (UserBean) session.getAttribute("user"); //Creo un nuovo UserBean

        if(admin.isAdmin().equalsIgnoreCase("true")){// se user è admin ->
            UserDAO userDAO= new UserDAO();

            ArrayList<UserBean> listUsers = userDAO.doRetriveAllUsers(); //chiedo al model tutti gli utenti persenti nel DB

            request.setAttribute("listUsers",listUsers); //creo un nuovo attributo nella risposta con la lista
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/results/users.jsp"); //imposto il path
            dispatcher.forward(request,response); //faccio un redirect al path inserito precedentemente con stessa richiesta e risposta
        }
        else{//se non è admin
            response.sendRedirect("index.jsp"); //vai nella homepage
        }
    }
}
