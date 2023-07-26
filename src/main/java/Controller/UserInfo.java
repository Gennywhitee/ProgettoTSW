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

@WebServlet(name = "UserInfoServlet", value="/user-info-servlet")
public class UserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        UserBean admin = (UserBean) session.getAttribute("user");

        if(admin.isAdmin().equalsIgnoreCase("true")){ //se si è amministratori

            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("userId"));
            UserBean user = userDAO.doRetriveById(id);


            if(user == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
                request.setAttribute("type","alert");
                request.setAttribute("msg","Utente non trovato nel DB");
                request.setAttribute("redirect",request.getContextPath());
                dispatcher.forward(request,response);
            }else {
                request.setAttribute("ProfileJSP", user); //setta un attributo nella richiesta con l'utente scelto
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/user/user-profile.jsp"); //crea il dispatcher con il path alla jsp che deve essere visualizzata
                dispatcher.forward(request, response); //forward al path precendentemente inserito
            }
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
            request.setAttribute("type","alert");
            request.setAttribute("msg","Errore");
            request.setAttribute("redirect","/index.jsp");
            dispatcher.forward(request,response);
        }
    }
}
