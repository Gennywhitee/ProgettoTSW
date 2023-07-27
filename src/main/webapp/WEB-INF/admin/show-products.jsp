<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Model.ProductBean" %>
<%@ page import="java.util.ArrayList" %>
<html lang="en">
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/photo/template/LogoNoBack.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/catalogo.css">
    <title>Lista Prodotti</title>
</head>

<body >

<%  @SuppressWarnings("unchecked")
ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) request.getAttribute("prodotti");%>
<%@include file="/menu.jsp"%>
<%if( prodotti == null || prodotti.size() == 0){%>
<div>Catalogo vuoto</div>
<%} else{%>



<div class="prodotto-body">
    <%for(ProductBean product : prodotti){%>
    <div class="prodotto">
        <p class="product-images"><img src="<%=product.getImage()%>" class="imgProdotto"></p>
        <p class="product-text">Nome: <%=product.getName()%></p>
        <p class="product-text">Prezzo: <%=product.getPrice()%>&euro;</p>
        <p class="product-text">Categoria: <%=product.getCategory()%></p>
        <p class="product-text" >Quantita: <%=product.getQuantity()%></p>
        <button class="prodotto-pulsante" type="button" value="Dettagli" onclick="location.href='${pageContext.request.contextPath}/show-product-servlet?productId=<%=product.getId()%>'">Dettagli</button>
    </div>
    <%}%>
</div>

<%}%>

<%@include file="/footer.jsp"%>

</body>
</html>