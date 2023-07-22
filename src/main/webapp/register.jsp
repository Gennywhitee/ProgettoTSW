
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
</head>
<body>
<div class="login-form">
    <form action="register-servlet" method="post" id="register-form">
        <label for="nome" name="nome" id="nome">Nome</label>
        <input type="text" placeholder="Nome" name="nome" required>
        <label for="cognome" name="cognome" id="cognome">Cognome</label>
        <input type="text" placeholder="Cognome" name="cognome" required>
        <label for="dDNascita" name="dDNascita" id="dDNascita">Data di Nascita</label>
        <input type="date" name="dDNascita" required>
        </br>
        <label for="email" name="email" id="email">Email</label>
        <input type="email" placeholder="Email" name="email" required>
        <label for="password" name="password" id="password">Password</label>
        <input type="password" placeholder="Password" name="password" required>
        <label for="telefono" name="telefono" id="telefono">Telefono</label>
        <input type="text" placeholder="+39" name="telefono">
        </br>
        <label for="citta" name="citta" id="citta">Citta</label>
        <input type="text" placeholder="citta" name="citta">
        <label for="provincia" name="provincia" id="provincia">Provincia</label>
        <input type="text" placeholder="provincia" name="provincia">
        <label for="indirizzo" name="indirizzo" id="indirizzo">Indirizzo</label>
        <input type="text" placeholder="indirizzo" name="indirizzo">
        <label for="cap" name="cap" id="cap">CAP</label>
        <input type="text" placeholder="CAP" name="cap">
        </br>
        <div class="register-button">
            <input type="submit" value="Crea un account" class="submit-register">
        </div>

    </form>
</div>
</body>
</html>
