<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
</head>
<body>
    <div class="login-form">
        <form action="login-servlet" method="post">
            <label for="email" id="email">Email</label>
            <input type="email" name="email" placeholder="Email" required>
            <label for="password" id="password">Password</label>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" name="Accedi" value="Accedi"></form></br>
            <span>Se non hai un account: <a href="register.jsp">Registrati</a></span>
        </form>
    </div>
</body>
</html>
