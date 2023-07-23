<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>

  <link rel="stylesheet" type="text/css" href="style/catalogo.css">
  <title>Catalogo</title>
</head>

<body >
<%@include file="/menu.jsp"%>

<div class="prodotto-body">

  <div class="prodotto">
    <a href="#" target="_blank" >
      <img src="https://distefanobiancheriaperlacasa.com/wp-content/uploads/2019/10/tenda-leveline-blu.jpg" class="imgProdotto">
    </a>
    <p>#nome#</p>
    <p>#prezzo# &euro;</p>
    <button type="submit" class="prodotto-pulsante" type="button" value="Aggiungi">Add to cart</button>
  </div>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
