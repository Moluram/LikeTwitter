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
<body>
<c:import url="includes/simpleHeader.jsp"/>
<section
        class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--full-height mbr-section--bg-adapted mbr-parallax-background" id="header1-6" style="background-image:
        url(${pageContext.request.contextPath}/resources/images/wallpaper-young-people-in-party.jpg); background-size: 100%;">
    <div class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-box__magnet--center-left mbr-after-navbar">
        <div class="mbr-box__container mbr-section__container container">
            <div class="mbr-box mbr-box--stretched">
                <div class="mbr-box__magnet mbr-box__magnet--center-left">
                    <div class="row animated fadeInUp delay">
                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                            <spring:message code="error.exist" var="errorExist"/>
                            <form name="f" onsubmit="validateSubmit(['password', 'username'], '${errorExist}');" method="POST" href="<c:url value="/signin"/>" role="form">
                                <h1>${error}</h1>
                                <font color="white"><h1 align="center"><spring:message code="label.signin.title"/></h1></font>
                                <hr class="colorgraph">
                                <div class="form-group animated fadeInUp delay" id="usernameDiv">
                                    <spring:message code="NotEmpty.username" var="notEmptyUsername"/>
                                    <spring:message code="label.user.username" var="username"/>
                                    <input type="text" name="username" id="username"
                                           class="form-control input-lg" placeholder="${username}"
                                           onblur="validate('username', this, '${notEmptyUsername}')" tabindex="1">
                                </div>
                                <div class="form-group animated fadeInUp delay" id="passwordDiv">
                                    <spring:message code="NotEmpty.password" var="notEmptyPassword"/>
                                    <spring:message code="label.user.password" var="password"/>
                                    <input type="password" name="password" id="password"
                                           class="form-control input-lg" placeholder="${password}"
                                           onblur="validate('password', this, '${notEmptyPassword}')" tabindex="2">
                                </div>
                                <hr class="colorgraph">
                                <div class="row mbr-buttons btn-inverse mbr-buttons--left">
                                    <div class="col-xs-4 col-md-4"><a href="<c:url value="/signup"/>"
                                                                      class="btn btn-default btn-md animated fadeInUp delay"
                                                                      tabindex="6"><spring:message code="button.signup"/> </a></div>
                                    <div class="col-xs-4 col-md-4"><a href="<c:url value="/settings/reset-password"/>"
                                                                      class="btn btn-default btn-md animated fadeInUp delay"
                                                                      tabindex="6"><spring:message code="button.forgotPassword"/> </a></div>
                                    <div class="col-xs-4 col-md-4"><input type="submit" value="<spring:message code="button.signin"/>"
                                                                          class="btn btn-success btn-block btn-md animated fadeInUp delay"
                                                                          tabindex="5"></div>
                                </div>
                            </form>
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