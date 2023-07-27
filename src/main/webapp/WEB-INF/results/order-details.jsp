<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.OrderProductBean" %>
<%@ page import="Model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/order_details.css">
    <title>Dettagli Ordine</title>
</head>
<body>
<%@ include file="/menu.jsp"%>
<% @SuppressWarnings("unchecked")
ArrayList<OrderProductBean> products = (ArrayList<OrderProductBean>) request.getAttribute("prodottiOrdine");
    @SuppressWarnings("unchecked")
    ArrayList<ProductBean> catalog = (ArrayList<ProductBean>) request.getAttribute("catalog");
%>
<div class="headerTable">
    <div>Nome Prodotto</div>
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
<%@ include file="/footer.jsp"%>
</body>
</html>
