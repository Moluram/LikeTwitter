<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Moluram
  Date: 3/29/2017
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title><spring:message code="title.subscribes"/></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png" type="image/x-icon">
    <title><spring:message code="title.settings"/></title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/custom.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<c:import url="includes/header.jsp"/>
<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-9" >
    <div class="mbr-section__container mbr-section__container--std-padding container" style="padding-top: 50px; padding-bottom: 93px;">
        <div class="row text-center">
            <h2><spring:message code="title.settings"/></h2>
        </div>
        <div class="row">
            <spring:message code="error.exist" var="errorExist"/>
            <form onsubmit="if (validateSubmit('email', '${errorExist}'))
                    send('email', '<spring:message code="settings.email.success"/>')"
                  class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <spring:message code="email.not.valid" var="notValidEmail"/>
                <spring:message code="NotEmpty.email" var="notEmptyEmail"/>
                <spring:message code="label.user.email" var="email"/>
                <label class="text-center h3">${email}</label>
                <div class="form-group animated fadeInUp delay" id="emailDiv">
                    <input type="text" name="email" id="emailInput"
                                class="form-control" placeholder="${email}"
                                onblur="if (validate('email', this, '${notEmptyEmail}')) {
                                                 validateEmail('email', this, '${notValidEmail}') }"
                                tabindex="2"/>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                    <button type="submit" id="emailBtn"
                            class="btn btn-success animated fadeInUp delay" tabindex="5">
                        <spring:message code="label.form.submit"/>
                    </button>
                </div>
            </form>
            <form onsubmit="if (validateSubmit('status', '${errorExist}'))
                    send('status', '<spring:message code="settings.status.success"/>')"
                  class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <spring:message code="NotEmpty.String" var="emptyInput"/>
                <spring:message code="label.user.status" var="var"/>
                <label class="text-center h3">${var}</label>
                <div class="form-group" id="statusDiv">
                    <input type="text" id="statusInput"
                           class="form-control" placeholder="${var}"
                           onblur="validate('status', this, '${emptyInput}')"/>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                    <button type="submit" id="statusBtn"
                            class="btn btn-success animated fadeInUp delay" tabindex="5">
                        <spring:message code="label.form.submit"/>
                    </button>
                </div>
            </form>
        </div>
        <div class="row">
            <form onsubmit="if (validateSubmit('lastName', '${errorExist}'))
                    send('lastName', '<spring:message code="settings.lastName.success"/>')"
                  class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <spring:message code="NotEmpty.String" var="emptyInput"/>
                <spring:message code="label.user.lastName" var="var"/>
                <label class="text-center h3">${var}</label>
                <div class="form-group" id="lastNameDiv">
                    <input type="text" id="lastNameInput"
                                class="form-control" placeholder="${var}"
                                onblur="validate('lastName', this, '${emptyInput}')"/>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                    <button type="submit" id="lastNameBtn"
                            class="btn btn-success animated fadeInUp delay" tabindex="5">
                        <spring:message code="label.form.submit"/>
                    </button>
                </div>
            </form>
            <form onsubmit="if (validateSubmit('firstName', '${errorExist}'))
                    send('firstName', '<spring:message code="settings.firstName.success"/>')"
                  class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <spring:message code="NotEmpty.String" var="emptyInput"/>
                <spring:message code="label.user.firstName" var="var"/>
                <label class="text-center h3">${var}</label>
                <div class="form-group" id="firstNameDiv">
                    <input type="text" name="firstName" id="firstNameInput"
                           class="form-control" placeholder="${var}"
                           onblur="validate('firstName', this, '${emptyInput}')"/>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                    <button type="submit" id="firstNameBtn"
                            class="btn btn-success animated fadeInUp delay" tabindex="5">
                        <spring:message code="label.form.submit"/>
                    </button>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <div class="row">
                    <label id="resetPasswordLabel" class="h3" for="resetPassword"> <spring:message
                            code="label.form.title.reset"/></label>
                </div>
                <div class="row">
                    <a class="btn btn btn-success" id="resetPassword" onclick="resetPassword('<spring:message
                            code="label.form.title.reset.success"/>', '${user.username}')">
                        <spring:message code="label.form.reset"/>
                    </a>
                </div>
            </div>
            <div class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <form enctype="multipart/form-data" method="post"
                      action="/${user.username}/upload-photo">
                    <label class="h3" for="file"><spring:message code="label.settings.upload.title"/></label>
                    <input type="file" name="file" id="file" data-size="sm"
                           data-buttonBefore="true" class="btn-group-sm filestyle"
                           data-buttonName="btn-primary"/>
                    <button type="submit" class="btn btn-success pull-right"
                            id="uploadFile"><spring:message code="label.settings.upload.button"/>
                    </button>
                </form>
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
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-filestyle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/typeahead.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/validations.js"></script>

</body>
</html>