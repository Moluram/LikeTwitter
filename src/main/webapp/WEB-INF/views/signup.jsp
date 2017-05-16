<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Site made with Mobirise Website Builder v3.12.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png" type="image/x-icon">
    <meta name="description" content="Website Builder Description">
    <title><spring:message code="title.signup"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">



</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/joy.png); background-size: 100%;">
<section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
    <div class="mbr-navbar__section mbr-section">
        <div class="mbr-section__container container">
            <div class="mbr-navbar__container">
                <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                        <span class="mbr-brand__logo"><a href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png" class="mbr-navbar__brand-img mbr-brand__img" alt="LikeTwitter"></a></span>
                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white" href="<c:url value="/"/>">LikeTwitter</a></span>
                    </span>
                </div>
                <div class="mbr-navbar__hamburger mbr-hamburger"><span class="mbr-hamburger__line"></span></div>
                <div class="mbr-navbar__column mbr-navbar__menu">
                    <nav class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
                        <div class="mbr-navbar__column">
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white"
                                       href="<c:url value="/about"/>"><spring:message code="navbar.button.about"/>
                                    </a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white" href="<c:url
                                    value="/contact"/>"><spring:message code="navbar.button.contact"/></a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-9" >
    <div class="mbr-section__container mbr-section__container--std-padding container" style="padding-top: 50px; padding-bottom: 93px;">
        <div class="row animated fadeInUp">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <form:form modelAttribute="user" method="POST" enctype="utf8" role="form">
                    <span style="color: white; "><h1 align="center"><spring:message code="label.signup"/></h1></span>
                    <hr class="colorgraph">
                    <div class="form-group animated fadeInUp delay">
                        <spring:message code="label.user.username" var="username"/>
                        <form:errors cssClass="alert alert-danger" path="username" element="div"/>
                        <form:input path="username" type="username" name="user_name"
                               class="form-control input-lg" placeholder="${username}"
                                    tabindex="1"/>
                    </div>
                    <div class="form-group animated fadeInUp delay">
                        <spring:message code="label.user.email" var="email"/>
                        <form:errors cssClass="alert alert-danger" path="email" element="div"/>
                        <form:input path="email" type="email" name="email" id="email"
                                     class="form-control input-lg" placeholder="${email}"
                                    tabindex="2"/>
                    </div>
                    <div class="form-group animated fadeInUp delay">
                        <spring:message code="label.user.password" var="password"/>
                        <form:errors cssClass="alert alert-danger" path="password" element="div"/>
                        <form:input path="password" type="password" name="password" id="password"
                                     class="form-control input-lg" placeholder="${password}"
                                    tabindex="3"/>
                    </div>
                    <div class="form-group animated fadeInUp delay">
                        <spring:message code="label.user.confirmPass" var="confirmPass"/>
                        <form:errors cssClass="alert alert-danger" path="matchingPassword" element="div"/>
                        <form:input path="matchingPassword" type="password" name="confirmPass" id="confirmPass"
                                     class="form-control input-lg" placeholder="${confirmPass}"
                                    tabindex="4"/>
                    </div>

                    <hr class="colorgraph">
                    <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                        <div class="col-xs-6 col-md-6"><a href="<c:url value="/signin"/>"
                                                          class="btn btn-default btn-lg animated fadeInUp delay" tabindex="6"><spring:message code="button.signin"/></a></div>
                        <div class="col-xs-6 col-md-6"><input type="submit" value="<spring:message code="button.signup"/>"
                                                              class="btn btn-success btn-block btn-lg animated fadeInUp delay" tabindex="5"></div>
                    </div>
                </form:form>
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