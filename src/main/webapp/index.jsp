<%@ page import="Model.UserBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>La Sartoria delle Tende</title>
    <link rel="stylesheet" href="style/homepage.css">

    <script>

        let slideIndex = 0;
        function showSlides() {
            let i;
            let slides = document.getElementsByClassName("mySlides");
            let dots = document.getElementsByClassName("dot");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";
            dots[slideIndex-1].className += " active";
            setTimeout(showSlides, 5000);
        }
        </script>
</head>
<body onload="showSlides()">
<%@ include file="/menu.jsp"%>
<div class="margin">
    </br>
</div>
<div class="slideshow-container">
    <div class="mySlides fade">
        <img src="photo/template/template1.jpg">
    </div>

    <div class="mySlides fade">
        <img src="photo/template/template2.jpg">
    </div>

    <div class="mySlides fade">
        <img src="photo/template/template3.png">
    </div>
</div>
<div style="text-align:center">
    <span class="dot"></span>
    <span class="dot"></span>
    <span class="dot"></span>
</div>

<div class="product-show">
</div>
    </br>
<%@include file="footer.jsp"%>
</body>