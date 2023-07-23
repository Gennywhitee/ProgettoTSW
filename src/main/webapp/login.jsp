<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="script/validateForm.js"></script>

</head>
<body>
<%@include file="menu.jsp"%>

<%
    if (myProfile != null) {
        response.sendRedirect("index.jsp");
    }
%>
    <div class="login-form">
        <form action="login-servlet" method="post" id="loginForm">
            <label for="email" id="email">Email</label>
            <input type="email" name="email" placeholder="Email" required>
            <label for="password" id="password">Password</label>
            <input type="password" name="password" placeholder="Password" required>
            <input type="button" name="Accedi" value="Accedi" onclick="validateLogin()"></form></br>
            <span>Se non hai un account: <a href="register.jsp">Registrati</a></span>
        </form>
    </div>
<%@include file="footer.jsp"%>
</body>
</html>
