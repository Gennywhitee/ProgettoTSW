package Controller;

import Model.CartBean;
import Model.CartDAO;
import Model.UserBean;
import Model.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "registerServlet",value = "/register-servlet")
public class Register extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        UserDAO userDAO = new UserDAO();

        String email = request.getParameter("email");


        try {
            if(userDAO.isAlreadyRegistered(email)){ //eMail già esistente
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
                request.setAttribute("type","error-registration");
                request.setAttribute("msg","Email già presente");
                request.setAttribute("redirect", "/login.jsp");
                dispatcher.forward(request,response);

            } else{ //Controllo lato server tramite espressioni regolari definite in basso
                int count = 0;
                String nome = request.getParameter("nome");
                Matcher matcher = info_string.matcher(nome);
                boolean matchFound = matcher.find();
                if(matchFound)
                    count++;

                String cognome = request.getParameter("cognome");
                matcher = info_string.matcher(cognome);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String dDNascita = request.getParameter("dDNascita");
                if(dDNascita != null)
                    count++;

                email = request.getParameter("email");
                matcher = email_string.matcher(email);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String passwd = request.getParameter("password");
                matcher = password_string.matcher(passwd);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String telefono = request.getParameter("telefono");
                matcher = phone_string.matcher(telefono);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String citta =request.getParameter("citta");
                matcher = info_string.matcher(citta);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String provincia = request.getParameter("provincia");
                matcher = province_string.matcher(provincia);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String cap = request.getParameter("cap");
                matcher = cap_string.matcher(cap);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String indirizzo = request.getParameter("indirizzo");
                matcher = address_string.matcher(provincia);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                if(count == 10) { //controllo se tutti i campi rispettano la regolarità delle espressioni regolari
                                  //creazione e registrazione di un nuovo user nel database
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


                    userDAO.doSave(userBean);

                    CartBean cart =  new CartBean();

                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
                    request.setAttribute("user",userBean);
                    request.setAttribute("cart",cart);
                    request.setAttribute("type","success-registration");
                    request.setAttribute("msg","Registrazione effettuata con successo");
                    request.setAttribute("redirect","/index.jsp");
                    dispatcher.forward(request,response);
                }
                else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
                    request.setAttribute("type","error-registration");
                    request.setAttribute("msg","Errore nella registrazione");
                    request.setAttribute("redirect","/index.jsp");
                    dispatcher.forward(request,response);
                }
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
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
