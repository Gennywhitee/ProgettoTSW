<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it-IT">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Profilo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/profile.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/validateForm.js"></script>

</head>
<body>


<%@ include file="/menu.jsp"%>

<% UserBean profileJSP = (UserBean) request.getAttribute("profileJSP");%>

<div class="edit-form">
    <form action="edit-profile-servlet" method="post" id="edit-profile">
        <div class="container">
            <div class="personal-information">
                <label class="text-section">Dati personali</label>

                <label for="nome" class="login-text">Nome</label>
                <input type="text" class="register" id="nome" name="nome" placeholder="<%=profileJSP.getNome()%>" value="<%=profileJSP.getNome()%>">

                <label for="cognome" class="login-text">Cognome</label>
                <input type="text" class="register" id="cognome" name="cognome" placeholder=<%=profileJSP.getCognome()%>" value="<%=profileJSP.getCognome()%>">

                <label for="dDNascita" class="login-text">Data di nascita</label>
                <input type="text" class="register" id="dDNascita" name="dDNascita" readonly value="<%=profileJSP.getDdNascita()%>">

            </div>

            <div class="login-information">
                <label class="text-section">Dati accesso</label>

                <label for="email" class="login-text">Email</label>
                <input type="email" class="register" id="email" name="email" placeholder="<%=profileJSP.getEmail()%>" value="<%=profileJSP.getEmail()%>">

                <label for="password" class="login-text">Password</label>
                <input type="password" class="register" id="password" name="password" placeholder="Password" required>

                <label for="telefono" class="login-text">Telefono</label>
                <input type="tel" class="register" id="telefono" name="telefono" placeholder="<%=profileJSP.getTelefono()%>" value="<%=profileJSP.getTelefono()%>">
            </div>

            <div class="address-information">
                <label class="text-section">Indirizzo</label>

                <label for="citta" class="login-text">Città</label>
                <input type="text" class="register" id="citta" name="citta" placeholder="<%=profileJSP.getCitta()%>" value="<%=profileJSP.getCitta()%>">

                <label for="provincia" class="login-text">Provincia</label>
                <input type="text" class="register" id="provincia" name="province" placeholder="<%=profileJSP.getProvincia()%>" value="<%=profileJSP.getProvincia()%>">

                <label for="cap" class="login-text">Codice Postale</label>
                <input type="text" class="register" id="cap" name="cap" placeholder="<%=profileJSP.getCap()%>" value="<%=profileJSP.getCap()%>">

                <label for="indirizzo" class="login-text">Indirizzo</label>
                <input type="text" class="register" id="indirizzo" name="indirizzo" placeholder="<%=profileJSP.getIndirizzo()%>" value="<%=profileJSP.getIndirizzo()%>">
            </div>
        </div>
        <%UserBean currentUser = (UserBean) request.getSession().getAttribute("user");
        if(profileJSP.getId() == currentUser.getId()){%>

        <div class="login-button">
            <input type="button" onclick="validateEditProfile()" value="Salva">
        </div>
        <div class="login-button">
            <input type="button" onclick="location.href='logout-servlet'" value="Logout">
        </div>
        <div>
            <div class="login-button">
                <input type="button" onclick="location.href='show-orders-servlet'" value="Ordini">
            </div>
        </div>
        <% } else { %>
        <% if(profileJSP.isAdmin().equalsIgnoreCase("false")){ %>
        <div class="login-button">
            <input type="button" onclick="location.href='set-admin-servlet'" value="Rendi Amministratore">
        </div>
        <% } else { %>
        <div class="login-button">
            <input type="button" onclick="location.href='set-admin-servlet?userId=<%=profileJSP.getId()%>'" value="Rimuovi da Amministratore">
        </div>
        <%}%>
    <%}%>

    </form>
</div>


</body>
</html>
                        <!--       _
                        .__(.)< (MEOW)
                        \___)
                        ~~~~~~~~~~~~~~~~~~-->
