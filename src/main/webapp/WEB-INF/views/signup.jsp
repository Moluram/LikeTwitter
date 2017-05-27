<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
<c:import url="includes/simpleHeader.jsp"/>

<section
        class="mbr-box mbr-section mbr-section--relative mbr-section--full-height mbr-section--bg-adapted mbr-parallax-background" id="header1-6" style="background-image: url(${pageContext.request.contextPath}/resources/images/joy.png); background-size: 100%;">
    <div class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-box__magnet--center-left">
        <div class="mbr-box__container mbr-section__container container">
            <div class="mbr-box mbr-box--stretched">
                <div class="mbr-box__magnet mbr-box__magnet--center-left">
                    <div class="row animated fadeInUp">
                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                            <spring:message code="error.exist" var="errorExist"/>
                            <form:form id="f"
                                       onsubmit="validateSubmit(['username', 'password', 'email', 'confirmPass'], '${errorExist}');" modelAttribute="user" method="POST" enctype="utf8" role="form">
                                <span style="color: white; "><h1 align="center"><spring:message code="label.signup"/></h1></span>
                                <hr class="colorgraph">
                                <div class="form-group animated fadeInUp delay" id="usernameDiv">
                                    <spring:message code="username.exist" var="usernameExist"/>
                                    <spring:message code="NotEmpty.username" var="notEmptyUsername"/>
                                    <spring:message code="label.user.username" var="username"/>
                                    <form:errors cssClass="alert alert-danger" path="username" element="div"/>
                                    <form:input path="username" type="username" name="user_name" id="usernameInput"
                                                class="form-control input-lg" placeholder="${username}"
                                                onblur="if (validate('username', this, '${notEmptyUsername}')) {
                                                validateUsername('username', this, '${usernameExist}') }"
                                                tabindex="1"/>
                                </div>
                                <div class="form-group animated fadeInUp delay" id="emailDiv">
                                    <spring:message code="email.not.valid" var="notValidEmail"/>
                                    <spring:message code="NotEmpty.email" var="notEmptyEmail"/>
                                    <spring:message code="label.user.email" var="email"/>
                                    <form:errors cssClass="alert alert-danger" path="email" element="div"/>
                                    <form:input path="email" type="email" name="email" id="emailInput"
                                                class="form-control input-lg" placeholder="${email}"
                                                onblur="if (validate('email', this, '${notEmptyEmail}')) {
                                                 validateEmail('email', this, '${notValidEmail}') }"
                                                tabindex="2"/>
                                </div>
                                <div class="form-group animated fadeInUp delay" id="passwordDiv">
                                    <spring:message code="NotEmpty.password" var="notEmptyPassword"/>
                                    <spring:message code="label.user.password" var="password"/>
                                    <form:errors cssClass="alert alert-danger" path="password" element="div"/>
                                    <form:input path="password" type="password" name="password" id="passwordInput"
                                                class="form-control input-lg" placeholder="${password}"
                                                onblur="validate('password', this, '${notEmptyPassword}')"
                                                tabindex="3"/>
                                </div>
                                <div class="form-group animated fadeInUp delay" id="confirmPassDiv">
                                    <spring:message code="NotEmpty.password" var="notEmptyPassword"/>
                                    <spring:message code="passwords.not.matches" var="notMatchesPassword"/>
                                    <spring:message code="label.user.confirmPass" var="confirmPass"/>
                                    <form:errors cssClass="alert alert-danger" path="matchingPassword" element="div"/>
                                    <form:input path="matchingPassword" type="password" name="confirmPass" id="confirmPassInput"
                                                class="form-control input-lg" placeholder="${confirmPass}"
                                                onblur="if (validate('confirmPass', this, '${notEmptyPassword}')) validatePasswords('confirmPass', 'password', this, '${notMatchesPassword}')"
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