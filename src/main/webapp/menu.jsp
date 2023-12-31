<%@ page import="Model.UserBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="photo/template/LogoScrittaBianca.png">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="style/menu.css">
    <script src='https://kit.fontawesome.com/c6b30e1924.js' crossorigin='anonymous'></script>
</head>
<body>
<%UserBean myProfile = (UserBean) request.getSession().getAttribute("user");
    String name, url;

    if(myProfile == null){
        name = "Login";
        url = "location.href='login.jsp'";
    }else{
        name= myProfile.getNome();
        url = "location.href='user-profile-servlet'";
    }
%>
<% if (myProfile != null && myProfile.isAdmin().equalsIgnoreCase("true")){ %>
<div class="nav-menu">
    <a href="index.jsp"><img src="${pageContext.request.contextPath}/photo/template/LogoScrittaBianca.png"></a>
    <div class="nav-menu-buttons">
        <input type="button" onclick="location.href='index.jsp'" value="HomePage">
        <i class="fa solid fa-home" onclick="location.href='index.jsp'"></i>
        <input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog-servlet'" value="Catalogo">
        <i class="fa solid fa-book-open" onclick="location.href='${pageContext.request.contextPath}/show-catalog-servlet'"></i>
        <input type="button" onclick="location.href='${pageContext.request.contextPath}/show-dashboard-servlet'" value="Dashboard">
        <i class="fa solid fa-cart-shopping" onclick="location.href='${pageContext.request.contextPath}/show-order-servlet'"></i>
        <input type="button" onclick="<%=url%>" value="<%=name%>">
        <i class="fa solid fa-user" onclick="<%=url%>"></i>
    </div>
</div>
<%} else {%>
<div class="nav-menu">
        <a href="index.jsp"><img src="${pageContext.request.contextPath}/photo/template/LogoScrittaBianca.png"></a>
        <div class="nav-menu-buttons">
            <input type="button" onclick="location.href='index.jsp'" value="HomePage">
            <i class="fa solid fa-home" onclick="location.href='index.jsp'"></i>
            <input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog-servlet'" value="Catalogo">
            <i class="fa solid fa-book-open" onclick="location.href='${pageContext.request.contextPath}show-catalog-servlet'"></i>
            <input type="button" onclick="location.href='chiSiamo.jsp'" value="Chi Siamo">
            <i class="fa solid fa-circle-info" onclick="location.href='chiSiamo.jsp'"></i>
            <input type="button" onclick="location.href='show-cart-servlet'" value="Carrello">
            <i class="fa solid fa-cart-shopping" onclick="location.href='show-cart-servlet'"></i>
            <input type="button" onclick="<%=url%>" value="<%=name%>">
            <i class="fa solid fa-user" onclick="<%=url%>"></i>
        </div>
</div>
<%}%>

</body>
</html>
