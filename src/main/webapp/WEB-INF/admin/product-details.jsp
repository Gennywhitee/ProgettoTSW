<%@page import="Model.ProductBean" %>
<%@ page import="javax.management.Descriptor" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/product-details.css"
    <%ProductBean product = (ProductBean) request.getAttribute("prodotto");%>

</head>
<body>
<%@include file="/menu.jsp"%>
<div class="product-wrapper">
        <div class="product-details">
            <div class="spacer"></div>
            <img src="<%=product.getImage()%>" alt="Immagine Prodotto">
            <div class="product-name">
                <label for="name">Nome: </label>
                <input readonly name="name" id="name" type="text" value="<%=product.getName()%>">
            </div>

            <div class="product-description">
                <label for="description">Descrizione: </label>
                <input readonly name="description" id="description" type="text" value="<%=product.getDescription()%>">
            </div>


            <div class="product-price">
                <label for="price">Prezzo:</label>
                <input name="price" id="price" type="text" readonly value="<%=product.getPrice()%>">
            </div>

            <div class="product-name">
                <label for="category">Categoria: </label>
                <input id="category" name="category" type="text" readonly value="<%=product.getCategory()%>">
            </div>
            <div class="product-quantity">
            <label for="quantity">Rimanenti: </label>
            <input type="text" name="quantity" id="quantity" value="<%=product.getQuantity()%>" readonly>
            </div>

            <div class="bottoni">
                <input class="button" type="button" onclick="location.href='show-catalog-servlet'" value="Torna al catalogo">
            </div>
    </div>

</div>
<%@include file="/footer.jsp"%>

</body>
</html>

