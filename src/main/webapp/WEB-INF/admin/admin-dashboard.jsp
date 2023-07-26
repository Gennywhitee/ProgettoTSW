<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/menu-style.css">
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <title>Dashboard</title>
</head>
<body>
 <%@ include file="/menu.jsp"%>
 <div class="main-container">
     <div class="action-container">
         <div class="action-card" id="add-product-card" onclick="location.href='${pageContext.request.contextPath}/show-users-servlet'">
             <i class="fa-solid fa-users" style="color: #000000;"></i>
             <p class="text-card">Gestione Utenti</p>
         </div>
         <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/add-product-servlet'">
             <i class="fa-solid fa-square-plus" style="color: #000000;"></i>
             <p class="text-card">Aggiungi Prodotto</p>
         </div>
         <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/show-order-servlet'">
             <i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
             <p class="text-card">Visualizza Ordini</p>
         </div>

     </div>
 </div>
</body>
</html>
