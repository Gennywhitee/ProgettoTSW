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
        url = "login.jsp";
    }else{
        name= myProfile.getNome();
        url = "WEB-INF/user/user-profile.jsp";
    }
%>
    <div class="navigation-bar">
        <a href="index.jsp"><img src="${pageContext.request.contextPath}/photo/template/LogoScrittaBianca.png"></a>
    </div>

</body>
</html>
