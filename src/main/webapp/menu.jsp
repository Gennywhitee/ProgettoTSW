<%@ page import="Model.UserBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="style/menu.css">
</head>
<body>
<%UserBean myProfile = (UserBean) request.getSession().getAttribute("user");
    String name, url;

    if(myProfile == null){
        name = "Login";
        url = "location.href='login.jsp'";
    }else{
        name= myProfile.getNome();
        url = "location.href='WEB-INF/user/user-profile.jsp'";
    }
%>
<div class="nav-menu">
        <a href="index.jsp"><img src="${pageContext.request.contextPath}/photo/template/LogoScrittaBianca.png"></a>
        <div class="nav-menu-buttons">
            <input type="button" onclick="location.href='index.jsp'" value="HomePage">
            <input type="button" onclick="location.href='catalog.jsp'" value="Catalogo">
            <input type="button" onclick="location.href='chiSiamo.jsp'" value="Chi Siamo">
            <input type="button" onclick="location.href='cart.jsp'" value="Carrello">
            <input type="button" onclick="<%=url%>" value="<%=name%>">
        </div>
</div>

</body>
</html>
