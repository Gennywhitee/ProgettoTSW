<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
</head>
<body>
<h1>Carrello</h1>
<%= if ((cartBean.getNumberObject() == 0)%>
    <p>Il carrello è vuoto. <a href="/catalogo">Vai al catalogo</a></p>

<c:if test="${not cartBean.getNumberObject() == 0}">
    <ul>
        <c:forEach items="${cartController.cartItems}" var="item">
            <li>${item.itemName} - ${item.price} €</li>
        </c:forEach>
    </ul>
    <p>Totale: ${cartTotal()}</p>
    <!-- Altri dettagli o azioni sul carrello -->
</c:if>
</body>
</html>
