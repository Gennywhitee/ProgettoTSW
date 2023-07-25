<%@ page import="Model.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utenti</title>

    <script>
        function changeAdminStatus(button){
            let idToSend = button.id;
            const link = "set-admin?userId=" + idToSend;
            $.get(link, function (data, status){
                    if (status === "success"){
                        let response = JSON.parse(data);
                        if (response.esit === "set-success"){
                            if (response.isAdmin)
                                button.style.color = "#adff2f";
                            else
                                button.style.color = "#e5383b";
                        }
                    }
                    else {
                        alert("Errore durante l'operazione!");
                    }
                }
            )
        }
    </script>
</head>
<body>
<div class="title-container" style="display: block">
    <h1 class="title">Gestione Utenti</h1>
    <p class="subtitle">Visualizza o modifica le informazioni degli utenti registrati.</p>
</div>
<div class="list-container">
    <div class="list-header">
        <div class="list-col">ID</div>
        <div class="list-col">NOME</div>
        <div class="list-col">COGNOME</div>
        <div class="list-col"></div>
    </div>
    <%
        List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
        if (userList != null && !userList.isEmpty()){
            for (UserBean tmpUser : userList){%>
    <div class="list-item">
        <div class="list-row">
            <div class="list-col"><%= tmpUser.getId()%></div>
            <div class="list-col"><%= tmpUser.getNome()%></div>
            <div class="list-col"><%= tmpUser.getCognome()%></div>
            <div class="list-col list-button-container">
                <%
                    String textColor = "";
                    if(tmpUser.isAdmin().equalsIgnoreCase("true")) {
                        textColor = "#adff2f";
                    } else {
                        textColor = "#e5383b";
                    }
                %>
                <div class="list-col list-button"><a onclick="changeAdminStatus(this)" id="<%=tmpUser.getId()%>" style="color: <%=textColor%>">Admin</a></div>
                <div class="list-col list-button"><a href="user-info-servlet?userId=<%=tmpUser.getId()%>">Info</a></div>
            </div>
        </div>
    </div>
    <%}}else {%>
    <div class="title-container">
        <h1 class="title">Nessun Utente Registrato</h1>
    </div>
    <%}%>

</div>
</body>
</html>
