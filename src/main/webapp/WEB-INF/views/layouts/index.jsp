<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<html lang="en">
<head>
    <title><tiles:getAsString name="title"/></title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--=================================== AJAX =======================================--%>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/ajax/products/ajax_products_CURD.js"></script>
<%--=================================== CSS =======================================--%>
    <%@include file="../../library/library_css.jsp" %>
    <link type="text/css" href="/resources/tool/jquery.datetimepicker.min.css" rel="stylesheet">
    <%--=================================== BASE CSS =======================================--%>
    <link type="text/css" href="/resources/css/baseFragments/menu.css" rel="stylesheet">
    <link type="text/css" href="/resources/css/baseFragments/header.css" rel="stylesheet">
    <%--=================================== HOME CSS =======================================--%>
    <link type="text/css" href="/resources/css/bodyFragments/product.css" rel="stylesheet">
    <link type="text/css" href="/resources/css/bodyFragments/home.css" rel="stylesheet">
<%--=================================== CREATE CATEGORY ================================--%>
    <%--============================================================================--%>

</head>

<body>

<div class="package">

    <tiles:insertAttribute name="menu"/>

    <div class="col-7 col-sm-8 col-md-9 col-lg-9 col-xl-10 display-main">

        <tiles:insertAttribute name="header"/>


        <div class="main set-height">

            <div class="container body-main ">
                <tiles:insertAttribute name="body"/>
            </div>
            <!-- FOOTER -->
            <div id="footer">
                BKSoftware - 2019
            </div>

        </div>

    </div>
</div>

<!----------------------Script------------------------------------ -->
<%@include file="../../library/library_js.jsp" %>
<script src="/resources/tool/jquery.datetimepicker.full.js"></script>
<script src="/resources/tool/jquery.datetimepicker.full.min.js"></script>
<script src="/resources/tool/jquery.datetimepicker.min.js"></script>
<script src="/resources/js/checkDate.js"></script>
<script src="/resources/js/home.js"></script>
</body>

</html>
