<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.OrderProductBean" %>
<%@ page import="Model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli Ordine</title>
</head>
<body>
<% @SuppressWarnings("unchecked")
        ArrayList<OrderProductBean> products = (ArrayList<OrderProductBean>) request.getAttribute("prodottiOrdine");
    @SuppressWarnings("unchecked")
        ArrayList<ProductBean> catalog = (ArrayList<ProductBean>) request.getAttribute("catalog");
%>
<div class="headerTable">
    <div>Nome</div>
    <div>Quantità</div>
</div>

<div class="bodyTable">
    <% for (OrderProductBean productOrder: products) { %>
    <div class="single-item">
        <% for (ProductBean productBean: catalog) { %>
        <% if (productBean.getId() == productOrder.getProduct()) { %>
        <div><%= productBean.getName() %></div>
        <div><%= productOrder.getQuantity() %></div>
        <% } %>
        <% } %>
    </div>
    <% } %>
</div>

</div>

</body>
</html>
