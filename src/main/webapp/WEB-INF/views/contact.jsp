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
    <title><spring:message code="title.contact"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css">


</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/gnome-backgrounds_3.jpg);">

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
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn btn-success" href="<c:url
                                    value="/"/>"><spring:message code="navbar.button.home"/></a>
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
    <div class="contact-section text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <form:form modelAttribute="contacts" commandName="contacts" class="form-horizontal">
                        <div class="form-group">
                            <h2><spring:message code="label.contactus.title"/></h2>
                            <p><spring:message code="label.contactus.message"/></p>
                        </div>
                        <div class="form-group animated fadeInUp delay">
                            <spring:message code="label.user.name" var="name"/>
                            <form:errors cssClass="alert alert-danger" path="name" element="div"/>
                            <form:input path="name" type="text"
                                        class="form-control input-lg" placeholder="${name}"
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
                            <spring:message code="label.user.text" var="text"/>
                            <form:errors cssClass="alert alert-danger" path="text" element="div"/>
                            <form:textarea path="text" type="text" name="text" id="text"
                                        class="form-control input-lg" placeholder="${text}"
                                        tabindex="3"/>
                        </div>
                        <hr class="colorgraph">
                        <div class="row btn-inverse mbr-buttons--left ">
                            <div class="col-xs-6 col-md-6"><input type="submit" value="<spring:message code="label.form.submit"/>"
                                                                  class="btn btn-success btn-block btn-lg animated fadeInUp delay" tabindex="5"></div>
                        </div>
                    </form:form>
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