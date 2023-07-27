<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="Model.CartBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="style/cart.css">
</head>
<body>
<%@ include file="menu.jsp"%>
<% CartBean cart = (CartBean) request.getSession().getAttribute("cart");
    if (cart == null || cart.getNumberObject() == 0) { %>

<div class="empty-cart">
    <p>Il carrello è vuoto</p>
    <a href="show-catalog-servlet">Vai al Catalogo</a>
</div>

<% } else {
    double total = 0;
    JSONArray arrayProducts = (JSONArray) request.getAttribute("array");
    for (int i = 0; i < arrayProducts.length(); i++) {
        JSONObject object = arrayProducts.getJSONObject(i);
        total += object.getDouble("price");
%>

<div class="cart-item">
    <div class="cart-item-image">
        <img src="<%= object.getString("image") %>">
    </div>
    <div class="cart-item-info">
        <p class="info-title"><%= object.getString("name") %></p>
        <p class="info-text">Prezzo: <%= object.getDouble("price") %></p>
        <p class="info-text">Quantità: <%= object.getInt("quantity") %></p>
        <p class="info-button">
            <a href="show-product-servlet?productId=<%= object.getInt("productID") %>">Dettagli</a>
        </p>
        <p class="info-button">
            <a href="remove-from-cart-servlet?productId=<%= object.getInt("productID") %>">Rimuovi</a>
        </p>
        <p class="info-button">
            <a href="payment-servlet">Procedi all'acquisto</a>
        </p>
    </div>
</div>

    <% }%>
<div class="info-total" style="color: #141414; justify-content: end; font-size: 30px">Totale:&euro; <%=total%></div>
<%} %>
</body>
<%@ include file="footer.jsp"%>
</html>
