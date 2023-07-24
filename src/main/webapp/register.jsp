
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="photo/template/LogoNoBack.png">
    <title>Registrazione</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" defer></script>
    <script type="text/javascript" src="script/validateForm.js" defer></script>
</head>
<body>
<%@include file="menu.jsp"%>
<div class="login-form">
    <form action="register-servlet" method="post" id="loginForm">
        <label for="nome" name="nome" >Nome</label>
        <input type="text" placeholder="Nome" id="nome" name="nome" required>
        <label for="cognome" name="cognome" >Cognome</label>
        <input type="text" placeholder="Cognome" name="cognome" id="cognome" required>
        <label for="dDNascita" name="dDNascita" >Data di Nascita</label>
        <input type="date" name="dDNascita" id="dDNascita" required>
        </br>
        <label for="email" name="email" >Email</label>
        <input type="email" placeholder="Email" id="email" name="email" required>
        <label for="password" name="password" >Password</label>
        <input type="password" placeholder="Password" id="password" name="password" required>
        <label for="telefono" name="telefono" >Telefono</label>
        <input type="tel" placeholder="+39" name="telefono" id="telefono">
        </br>
        <label for="citta" name="citta" >Citta</label>
        <input type="text" placeholder="citta" id="citta" name="citta">
        <label for="provincia" name="provincia" >Provincia</label>
        <input type="text" placeholder="provincia" id="provincia" name="provincia">
        <label for="indirizzo" name="indirizzo" >Indirizzo</label>
        <input type="text" placeholder="indirizzo" id="indirizzo" name="indirizzo">
        <label for="cap" name="cap" >CAP</label>
        <input type="text" placeholder="CAP" id="cap" name="cap">
        </br>
        <div class="register-button">
            <input type="button" value="Crea un account" onclick="validateRegistration()" class="submit-register">
        </div>

    </form>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
