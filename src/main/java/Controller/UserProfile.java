package Controller;

import Model.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "userProfileServlet", value = "/user-profile-servlet") //SERVLET PER VEDERE IL PROPRIO PROFILO
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        request.setAttribute("profileJSP", user);

        if (user != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/user-profile.jsp");
            dispatcher.include(request, response);
        }
    }
}