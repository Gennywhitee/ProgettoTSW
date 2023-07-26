<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Model.ProductBean" %>
<%@ page import="java.util.ArrayList" %>
<html lang="en">
<head>
  <link rel="icon" href="${pageContext.request.contextPath}/photo/template/LogoNoBack.png">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalogo.css">
  <title>Catalogo</title>
</head>

<body >
<% ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) request.getAttribute("prodotti");%>
<%@include file="/menu.jsp"%>
<%if( prodotti == null || prodotti.size() == 0){%>
<div>Catalogo vuoto</div>
<%} else{%>


<%for(ProductBean product : prodotti){%>
<div class="prodotto-body">

  <div class="prodotto">
      <p class="product-images"><img src="<%=product.getImage()%>" class="imgProdotto"></p>
      <p class="product-text"><%=product.getName()%></p>
      <p class="product-text"><%=product.getPrice()%>&euro;</p>
      <p class="product-text"><%=product.getCategory()%></p>
    <button class="prodotto-pulsante" type="button" value="Aggiungi" onclick="location.href='${pageContext.request.contextPath}/add-to-cart-servlet?productId=<%=product.getId()%>'">Add to cart</button>
    <button class="prodotto-pulsante" type="button" value="Dettagli" onclick="location.href='${pageContext.request.contextPath}/show-product-servlet?productId=<%=product.getId()%>'">Dettagli</button>
  </div>
</div>
    <%}%>
<%}%>

<%@include file="footer.jsp"%>

</body>
</html>
