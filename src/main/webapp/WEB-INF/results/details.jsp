<%@page import="Model.ProductBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <%ProductBean product = (ProductBean) request.getAttribute("prodotto");%>
    <title>Dettagli <%=product.getName()%></title>
</head>
<body>
<div class="product-wrapper">

  <table class="details-table">
    <tr>
      <td>
        <div class="product-images">
          <img src="photo/<%=product.getImage()%>" alt="Not available!">
        </div>
      </td>
      <td>
        <div class="details">
          <div class="titolo-pagina"> <h1><%=product.getName()%></h1> </div>
          <br><p class="product-short-des"><%=product.getDescription()%></p>
          <br><span class="product-price"><%=product.getPrice()%>&euro;</span>
        </div>
      </td>
    </tr>
    <tr>
      <td>
        <form action="show-catalog-servlet" method="post">
          <input type="hidden" name="action" value="comeBack">
          <button type="submit" class="btn return-btn" type="button"  value="Come Back to Catalog">Come Back to Catalog</button>
        </form>
      </td>
      <td>
        <form action="add-to-cart-servlet" method="post">
          <input type="hidden" name="action" value="addC">
          <input type="hidden" name="productCode" value="<%= product.getId() %>">
          <button type="submit" class="btn cart-btn" type="button" value="Aggiungi">Add to cart</button>
        </form>
      </td>
    </tr>
  </table>


</div>
</div>

</body>
</html>
