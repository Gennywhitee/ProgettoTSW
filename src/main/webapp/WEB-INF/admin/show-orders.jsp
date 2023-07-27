<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.OrderBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>
    <title>Ordini</title>
</head>
<body>
<div class="order-container">
    <div class="headerTableAdmin">
        <div>UTENTE</div>
        <div>ID</div>
        <div>Totale</div>
    </div>
  <% @SuppressWarnings("unchecked")
      ArrayList<OrderBean> orderList = (ArrayList<OrderBean>) request.getAttribute("orderList");
  if (orderList == null || orderList.size() == 0){%>
        <div>
            <p>Non ci sono ordini da visualizzare</p>
        </div>
<%} else {
        for(OrderBean tmpOrder : orderList){%>

            <div class="order-item">
                <div><%=tmpOrder.getUser()%></div>
                <div><%=String.format("%.2f",tmpOrder.getTotal())%></div>
                <div><a href="${pageContext.request.contextPath}/order-info-servlet?orderId=<%=tmpOrder.getId()%>">Altro<i class="fas fa-info-circle"></i></a></div>
            </div>
        </div>
  <%}%>
  <%}%>
</body>
</html>
