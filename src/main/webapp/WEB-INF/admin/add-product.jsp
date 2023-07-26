<%@ page import="Model.CategoryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/add-product.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/validateForm.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/2f266e86a2.js" crossorigin="anonymous"></script>


    <title>Aggiungi Prodotto</title>

    <script>
        function imageUpload(event) {
            $(".fa-file-circle-xmark").addClass("fa-solid fa-file-circle-check").removeClass("fa-file-circle-xmark");
            let src = URL.createObjectURL(event.target.files[0]);
            $(".preview").attr("src", src);
        }
    </script>

    <%  @SuppressWarnings("unchecked")
    ArrayList<CategoryBean> listCategories = (ArrayList<CategoryBean>) request.getAttribute("categories"); %>
</head>
<body>
    <%@include file="/menu.jsp"%>
    <div class="return-products"><a href="${pageContext.request.contextPath}/show-dashboard-servlet">Indietro</a></div>

    <div class="containerProductDiv">
        <form action="${pageContext.request.contextPath}/add-product-servlet" method="post" enctype="multipart/form-data" id="add-product-form">
            <div class="containerProduct">
                <div class="product-name">
                    <label for="name">Nome</label>
                    <input type="text" id="name" name="name" class="product" placeholder="Nome" maxlength="20" required>
                </div>

                <div class="product-description">
                    <label for="description">Descrizione</label>
                    <textarea id="description" name="description" class="product" placeholder="Descrizione" rows="6" maxlength="255" required></textarea>
                </div>

                <div class="product-price">
                    <label for="price">Prezzo</label>
                    <input type="text" id="price" class="product" name="price" placeholder="Prezzo" pattern="^[1-9]\d*(\.\d+)?$" required>
                </div>

                <div class="product-quantity">
                    <label for="quantity">Quantità</label>
                    <input type="text" id="quantity" name="quantity" class="product" placeholder="Quantità" pattern="[0-9]+" required>
                </div>

                <div class="product-image">
                    <label for="image" id="imageUpload">Immagine
                        <i class="fa-solid fa-file-circle-xmark"></i>
                        <input type="file" id="image" name="image" class="product" onchange="imageUpload(event)" accept="image/*" required></label>
                    <img src="" alt="" class="preview">
                </div>

                <div class="product-category">
                    <label for="category">Categoria</label>
                    <input list="category_name" id="category" name="category" class="product" placeholder="Categoria" maxlength="20" required>
                    <datalist id="category_name">
                        <% for (CategoryBean category: listCategories) { %>
                        <option value="<%= category.getNome() %>">
                                <% } %>
                    </datalist>
                </div>

                <div class="add-product">
                    <input type="button" onclick="validateAddProduct()" value="Inserisci">
                </div>
            </div>
        </form>
    </div>
</body>
</html>
