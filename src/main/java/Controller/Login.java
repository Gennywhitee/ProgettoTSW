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
               RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
               dispatcher.forward(request,response);

           }
           else{

               CartBean cartBean = new CartBean();
               CartDAO serviceCart = new CartDAO();

               cartBean.setCartList(serviceCart.getCart(userBean.getId()));


               RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
               request.getSession().setAttribute("cart", cartBean);
               request.getSession().setAttribute("user", userBean);
               request.setAttribute("type","success");
               request.setAttribute("msg","Login avvenuto con successo");
               request.setAttribute("redirect","/login.jsp");
               dispatcher.forward(request,response);

           }
       }

       else if(userBean == null){ //caso in cui l'user non ha un account o password/email errata
           RequestDispatcher dispatcher;
           dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
           request.setAttribute("type","alert");
           request.setAttribute("msg","E-mail o Password errati");
           request.setAttribute("redirect","/login.jsp");
           dispatcher.forward(request,response);
       }
       else{
           RequestDispatcher dispatcher;
           dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
           request.setAttribute("type","alert");
           request.setAttribute("msg","Errore generico");
           request.setAttribute("redirect","/login.jsp");
           dispatcher.forward(request,response);
       }
    }
}
