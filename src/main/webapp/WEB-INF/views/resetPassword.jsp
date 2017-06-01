<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png" type="image/x-icon">
    <title><spring:message code="label.form.title.reset"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css">

    <script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>


</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/gnome-backgrounds_3.jpg);">
<c:import url="includes/simpleHeader.jsp"/>

<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar">
    <div class="mbr-section__container mbr-section__container--std-padding container text-white" style="padding-top: 100px; padding-bottom: 93px;">
        <div class="row animated fadeInUp delay">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <font color="white"><h1 align="center"><spring:message code="label.reset.title"/></h1></font>
                <hr class="colorgraph">
                <div class="form-group animated fadeInUp delay">
                    <spring:message code="label.user.username" var="username"/>
                    <input type="text" name="username" id="username"
                                class="form-control input-lg" placeholder="${username}" tabindex="1"/>
                </div>
                <hr class="colorgraph">
                <div class="row mbr-buttons btn-inverse mbr-buttons--left pull-left">
                    <div class="col-md-6">
                        <a class="btn btn-success btn-block btn-lg animated fadeInUp delay pull-left" onclick="postUsername(
                            '<spring:message code="label.form.title.reset.success"/>', '<spring:message code="label.form.title.reset.unsuccess"/>'
                        )" tabindex="2">
                            <spring:message code="label.form.submit"/>
                        </a>
                    </div>
                    <div class="col-md-6">
                        <label id="resetPassword" class="label pull-right text-white"><h4><spring:message code="label.reset.submit"/></h4></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/resources/web/assets/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/smooth-scroll/smooth-scroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/jarallax/jarallax.js"></script>
<script src="${pageContext.request.contextPath}/resources/mobirise/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/formoid/formoid.min.js"></script>


</body>
</html>