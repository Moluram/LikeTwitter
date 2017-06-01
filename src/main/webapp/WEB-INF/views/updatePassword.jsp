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
    <title><spring:message code="title.signin"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css">


</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/gnome-backgrounds_3.jpg);">
<c:import url="includes/simpleHeader.jsp"/>

<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar">
    <div class="mbr-section__container mbr-section__container--std-padding container text-white" style="padding-top: 100px; padding-bottom: 93px;">
        <div class="row animated fadeInUp delay">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <spring:message code="error.exist" var="errorExist"/>
                <form:form action='/settings/change-password' cssClass="form" modelAttribute="passwords" method="POST" enctype="utf8"
                    onsubmit="validateSubmit(['password', 'confirmPass'], '${errorExist}');">
                    <font color="white"><h1 align="center"><spring:message code="label.title.update.password"/></h1></font>
                    <hr class="colorgraph">
                    <div class="form-group animated fadeInUp delay" id="passwordDiv">
                        <spring:message code="NotEmpty.password" var="notEmptyPassword"/>
                        <spring:message code="label.user.password" var="password"/>
                        <form:errors cssClass="alert alert-danger" path="password" element="div"/>
                        <form:input path="password" type="password" name="password" id="passwordInput"
                                    class="form-control input-lg" placeholder="${password}"
                                    onblur="validate('password', this, '${notEmptyPassword}')"
                                    tabindex="1"/>
                    </div>
                    <div class="form-group animated fadeInUp delay" id="confirmPassDiv">
                        <spring:message code="passwords.not.matches" var="notMatchesPassword"/>
                        <spring:message code="label.user.confirmPass" var="confirmPass"/>
                        <form:errors cssClass="alert alert-danger" path="matchingPassword" element="div"/>
                        <form:input path="matchingPassword" type="password" name="confirmPass" id="confirmPassInput"
                                    class="form-control input-lg" placeholder="${confirmPass}"
                                    onblur="validatePasswords('confirmPass', 'password', this, '${notMatchesPassword}')"
                                    tabindex="2"/>
                    </div>
                    <form:input path="username" cssClass="hidden" value="${passwords.username}"/>
                    <hr class="colorgraph">
                    <div class="row mbr-buttons btn-inverse mbr-buttons--left pull-left">
                        <button type="submit" class="btn btn-success btn-block btn-lg animated fadeInUp delay pull-left" tabindex="3">
                            <spring:message code="label.form.submit"/>
                        </button>
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
<script src="${pageContext.request.contextPath}/resources/js/validations.js"></script>


</body>
</html>