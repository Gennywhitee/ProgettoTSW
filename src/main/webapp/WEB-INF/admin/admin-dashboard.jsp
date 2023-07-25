<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/dashboard-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/menu-style.css">
    <title>Dashboard</title>
</head>
<body>
 <%@ include file="/menu.jsp"%>
 <div class="main-container">
     <div class="action-container">
         <div class="action-card" id="add-product-card" onclick="location.href='${pageContext.request.contextPath}/show-users-servlet'">
             <img class="image-card" src="${pageContext.request.contextPath}/assets/user.png">
             <p class="text-card">Gestione Utenti</p>
         </div>
         <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/add-product-servlet'">
             <img class="image-card" src="${pageContext.request.contextPath}/assets/box.png">
             <p class="text-card">Aggiungi Prodotto</p>
         </div>
         <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/show-order-servlet'">
             <img class="image-card" src="${pageContext.request.contextPath}/assets/purchase-order.png">
             <p class="text-card">Visualizza Ordini</p>
         </div>

     </div>

</body>
</html>
