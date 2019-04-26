<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<html lang="en">
<head>
    <title>login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--===================================CSS =======================================--%>
    <%@include file="../../library/library_css.jsp" %>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="/resources/css/login.css " rel="stylesheet">

</head>
<div class="img-background">
    <div class="container">
        <div class="row">
            <div class="col-8 col-sm-7 col-md-6 col-lg-5 img-logo">
                <img src="/resources/img/login/bksoftwareLogo.png" alt="" class="img-fluid">
            </div> <!--END_BACKGROUND-->
        </div>
        <div class="row">
            <div class="col-7 col-sm-6 col-md-5 col-lg-4 form-login">
                <div class="title">LOGIN HP</div>
                <form action="/admin/login/pass" method="post">
                    <div class="from-input">
                        <i class="fas fa-user-circle"></i>
                        <input type="text" class="input-login" name="username" placeholder="username">
                    </div><!--END_FROM_INPUT-->
                    <div class="from-input">
                        <i class="fas fa-lock"></i>
                        <input type="password" class="input-login" name="password" placeholder="password">
                    </div><!--END_FROM_INPUT-->
                    <a href="" class="forgot">Forgot Password?</a>
                    <div class="btn-login">
                        <button name="submit" type="submit" class="btn">LOGIN</button>
                    </div>
                </form>
            </div> <!--END_FROM_INPUT-->
        </div>
    </div> <!--END_CONTAINER-->
</div><!-- END_IMG_BACKGROUND -->
<body>
<!----------------------Script------------------------------------ -->
<%@include file="../../library/library_js.jsp" %>
</body>
<script src="/resources/js/login.js"></script>

</html>

