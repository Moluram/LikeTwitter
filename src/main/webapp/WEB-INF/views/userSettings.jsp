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
        <%boolean row = true;%>
        <c:forEach items="${settings}" var="setting">
            <c:if test="<%=row%>">
                <div class="row">
            </c:if>
            <spring:message code="error.exist" var="errorExist"/>
            <div class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <c:if test="${setting == 'email'}">
                    <spring:message code="email.not.valid" var="notValidEmail"/>
                </c:if>
                <spring:message code="NotEmpty.commentDto.text" var="notEmptySetting"/>
                <spring:message code="label.user.${setting.id}" var="${setting.id}Label"/>
                <label class="text-center h3">
                    <spring:message code="label.user.${setting.id}"/></label>
                <div class="form-group animated fadeInUp delay" id="${setting.id}Div">
                    <input type="text" name="${setting.id}" id="${setting.id}Input"
                           class="form-control" placeholder="<spring:message code="label.user.${setting.id}"/>"
                           onblur="if (validate('${setting.id}', this, '${notEmptySetting}')) {
                               <c:if test="${setting == 'email'}">
                                   validateEmail('${setting.id}', this, '${notValidEmail}');
                               </c:if>
                           }" value="${setting.value}" tabindex="2"/>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
                    <button id="${setting.id}Btn" onclick="if (validateSubmit(['${setting.id}'], '${errorExist}')) {
                            send('${setting.id}', '<spring:message code="settings.${setting.id}.success"/>');}"
                            class="btn btn-success animated fadeInUp delay" tabindex="5">
                        <spring:message code="label.form.submit"/>
                    </button>
                </div>
            </div>
            <c:choose>
                <c:when test="<%=row%>">
                    <%row = false;%>
                </c:when>
                <c:otherwise>
                    </div>
                    <%row = true;%>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="<%=!row%>">
            </div>
        </c:if>
        <div class="row">
            <div class="animated fadeInUp delay col-md-6">
                <hr class="colorgraph">
                <div class="form-group animated fadeInUp delay">
                    <label id="resetPasswordLabel" class="h3" for="resetPassword"> <spring:message
                            code="label.form.title.reset"/></label>
                </div>
                <div class="row mbr-buttons btn-inverse mbr-buttons--left ">
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