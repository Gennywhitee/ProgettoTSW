<%@page import="Model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dettagli</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/details.css"
  <%ProductBean product = (ProductBean) request.getAttribute("prodotto");%>

</head>
<body>
<%@include file="/menu.jsp"%>
<div class="product-wrapper">
<form action="add-to-cart-servlet" method="get">
  <div class="product-details">
    <img src="<%=product.getImage()%>" alt="Immagine Prodotto">
    <input type="hidden" name="productId" value="<%=product.getId()%>">
    <div class="product-name"><%=product.getName()%></div>
    <div class="product-description"><%=product.getDescription()%></div>
    <div class="product-price"><%=product.getPrice()%></div>
    <label for="quantity"></label>
    <input type="number" name="quantity" id="quantity" placeholder="1" required>
    <div class="bottoni">
      <input class="button" type="submit" value="Aggiungi al Carrello">
      <input class="button" type="button" onclick="location.href='show-catalog-servlet'" value="Torna al catalogo">
    </div>
  </div>
</form>

</div>
<%@include file="/footer.jsp"%>

</body>
</html>
