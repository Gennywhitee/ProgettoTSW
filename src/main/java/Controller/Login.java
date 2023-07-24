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

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class Login extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String passwd = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        UserBean userBean = userDAO.doRetriveByEmailPasswd(email,passwd);

       if(userBean != null && userBean.getStato().equalsIgnoreCase("true")){
           HttpSession session = request.getSession();
           session.setAttribute("user", userBean);


           if(userBean.isAdmin().equalsIgnoreCase("true")){ //Admin fa il login
               response.sendRedirect("WEB-INF/Admin/adminHome.jsp");

           }
           else{

               CartBean cartBean = new CartBean();
               CartDAO serviceCart = new CartDAO();

               cartBean.setCartList(serviceCart.getCart(userBean.getId()));

               session.setAttribute("cart", cartBean);
               session.setAttribute("user",userBean);
               response.sendRedirect("index.jsp");
           }
       }

       else if(userBean == null){ //caso in cui l'user non ha un account o password/email errata
           RequestDispatcher dispatcher;
           dispatcher = request.getRequestDispatcher("errorPage.jsp");
           request.setAttribute("type","alert");
           request.setAttribute("msg","E-Mail o Password errati");
           request.setAttribute("redirect","errorPage.jsp");
           dispatcher.include(request,response);
       }
       else{
            response.sendRedirect("index.jsp");
        }

    }
}
