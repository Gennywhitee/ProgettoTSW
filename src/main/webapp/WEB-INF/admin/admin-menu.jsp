<%@ page import="Model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<%
    UserBean myProfile = (UserBean) request.getSession().getAttribute("user");
    String name, url;

    name = myProfile.getNome();
    url = "WEB-INF/admin-profile.jsp";
%>
<div class="nav-menu">
    <a href="index.jsp"><img src="${pageContext.request.contextPath}/photo/template/LogoScrittaBianca.png"></a>
    <div class="nav-menu-buttons">
        <input type="button" onclick="location.href='index.jsp'" value="HomePage">
        <i class="fa solid fa-home" onclick="location.href='index.jsp'"></i>
        <input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog-servlet'" value="Catalogo">
        <i class="fa solid fa-book-open" onclick="location.href='${pageContext.request.contextPath}show-catalog-servlet'"></i>
        <input type="button" onclick="location.href='add-product-servlet'" value="Aggiunugi prodotto">
        <i class="fa solid fa-circle-info" onclick="location.href='add-product-servlet'"></i>
        <input type="button" onclick="location.href='${pageContext.request.contextPath}show-order-servlet'" value="Carrello">
        <i class="fa solid fa-cart-shopping" onclick="location.href='${pageContext.request.contextPath}show-order-servlet'"></i>
        <input type="button" onclick="<%=url%>" value="<%=name%>">
        <i class="fa solid fa-user" onclick="<%=url%>"></i>
    </div>
</div>

</body>
</html>
