package Controller;

import Model.UserBean;
import Model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "setAdminServlet", value = "/set-admin-servlet")
public class SetAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        int idToGet = Integer.parseInt(request.getParameter("userId"));
        UserDAO userDAO = new UserDAO();
        UserBean userToGet = userDAO.doRetriveById(idToGet);

        JSONObject responseText = new JSONObject();
        String esit = "";
        boolean status = false;
        if(currentUser == null || currentUser.isAdmin().equalsIgnoreCase("false")){
            responseText.put("not-succes",esit);
            responseText.put("status",status);
        } else{
            if(userToGet != null && userToGet.getId() != currentUser.getId()){
                boolean flag = userToGet.isAdmin().equalsIgnoreCase("true");
                responseText.put("set-success",esit);
                userDAO.changeAdminStatus(!flag,userToGet.getId());
                responseText.put("status",!flag);
            }
        }
        Writer output = response.getWriter();
        output.write(responseText.toString());
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
