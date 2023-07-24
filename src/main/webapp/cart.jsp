<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="Model.CartBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
</head>
<body>
<% CartBean cart = (CartBean) request.getAttribute("cart");
        if(cart == null || cart.getNumberObject() == 0){%>

    <a href="catalog.jsp"><p>Il carrello è vuoto</p></br>Vai al Catalogo</a>

<%}
        else {
            JSONArray arrayProducts = (JSONArray) request.getAttribute("array");

            JSONObject object = new JSONObject();

            for (int i = 0; i < arrayProducts.length(); i++) {
                object = arrayProducts.getJSONObject(i);
            }%>

        <div class="cart-item">
            <div class="class-item-image">
                <img src="<%=object.getString("image")%>">
            </div>
            <div class="cart-item-info">
                <p class="info-title"><%=object.getString("name")%></p>
                <p class="info-text"><%=object.getString("category")%></p>
                <p class="info-text"><%=object.getString("price")%></p>
                <p class="info-text"><%=object.getString("quantity")%></p>
                <p class="info-button"><a href="show-product-servlet?ProductBeanId"><%=object.getInt("productID")%></a></p>
                <p class="info-button"><a href="remove-from-cart-servlet?ProductCartBeanId"><%=object.getInt("cartID")%></a></p>
            </div>
        </div>

        <%}%>
</body>
</html>
