package Controller;

import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "editProfileServlet", value = "/edit-profile-servlet")
public class EditProfile extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

            int count = 0;
            String nome = request.getParameter("nome");
            Matcher matcher = info_string.matcher(nome);
            boolean matchFound = matcher.find();
            if (matchFound)
                count++;

            String cognome = request.getParameter("cognome");
            matcher = info_string.matcher(cognome);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String dDNascita = request.getParameter("dDNascita");
            if (dDNascita != null)
                count++;

            String email = request.getParameter("email");
            matcher = email_string.matcher(email);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String passwd = request.getParameter("password");
            matcher = password_string.matcher(passwd);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String telefono = request.getParameter("telefono");
            matcher = phone_string.matcher(telefono);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String citta = request.getParameter("citta");
            matcher = info_string.matcher(citta);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String provincia = request.getParameter("provincia");
            matcher = province_string.matcher(provincia);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String cap = request.getParameter("cap");
            matcher = cap_string.matcher(cap);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String indirizzo = request.getParameter("indirizzo");
            matcher = address_string.matcher(provincia);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            if (count == 10) { //controllo se tutti i campi rispettano la regolarità delle espressioni regolari
                               //update nel DB
                UserBean userBean = new UserBean();

                userBean.setNome(nome);
                userBean.setCognome(cognome);
                userBean.setEmail(email);
                userBean.setPassword(passwd);
                userBean.setDdNascita(dDNascita);
                userBean.setTelefono(telefono);
                userBean.setCitta(citta);
                userBean.setProvincia(provincia);
                userBean.setCap(cap);
                userBean.setIndirizzo(indirizzo);
                userBean.setRegistrazione();
                userBean.setStato("true");
                userBean.setAdmin("false");


                UserDAO userDAO = new UserDAO();

                userDAO.updateUser(userBean);

                request.setAttribute("profileJSP", userBean); //settiamo una variabile nella risposta "profileJSP" che rappresenta userBean

                if(userBean.isAdmin().equalsIgnoreCase("true")){
                    response.sendRedirect("WEB-INF/admin/profile-admin.jsp"); //Se admin, rimanda alla jsp del profilo admin
                }else{
                    response.sendRedirect("WEB-INF/user/user-profile.jsp");
                }
            }else{ //Caso in cui count != 10
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp"); //redirezione a error.jsp
                request.setAttribute("type", "alert"); //settiamo il tipo e l'oggetto da passare alla pagina di errore
                request.setAttribute("msg", "Errore modifica"); //settiamo il messagio con il contenuto
                request.setAttribute("redirect", "user-profile-servlet"); //settiamo il redirect una volta finito il count di errorPage
                dispatcher.include(request, response);
            }


    }
        final Pattern info_string = Pattern.compile("^([a-zA-Z\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]\\s?){2,20}$");
        final Pattern email_string = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
        final Pattern password_string = Pattern.compile("^[a-zA-Z\\d\\-\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]{6,16}");
        final Pattern phone_string = Pattern.compile("^\\d{10}$");
        final Pattern province_string = Pattern.compile("^([a-zA-Z]{2})$");
        final Pattern cap_string = Pattern.compile("^\\d{5}$");
        final Pattern address_string = Pattern.compile("^([a-zA-Z\\d\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27\\x2C]\\s?){2,20}$");
}

