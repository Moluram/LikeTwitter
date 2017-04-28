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
    <title>Title</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Site made with Mobirise Website Builder v3.12.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="https://www.seeklogo.net/wp-content/uploads/2016/11/twitter-icon-circle-blue-logo-preview.png" type="image/x-icon">
    <title>Sign In</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<section class="mbr-navbar mbr-navbar--xs mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
    <div class="mbr-navbar__section mbr-section">
        <div class="mbr-section__container container">
            <div class="mbr-navbar__container">
                <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                        <span class="mbr-brand__logo"><a href="<c:url value="/"/>"><img src="https://www.seeklogo.net/wp-content/uploads/2016/11/twitter-icon-circle-blue-logo-preview.png" class="mbr-navbar__brand-img mbr-brand__img" alt="LikeTwitter"></a></span>
                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white" href="<c:url value="/"/>">LikeTwitter</a></span>
                    </span>
                </div>
                <div class="mbr-navbar__hamburger mbr-hamburger"><span class="mbr-hamburger__line"></span></div>
                <div class="mbr-navbar__column mbr-navbar__menu">
                    <nav class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
                        <div class="mbr-navbar__column">
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons btn-inverse mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white"
                                        href="<c:url value="/about"/> ">ABOUT
                                </a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white" href="<c:url value="/contact"/>">CONTACT</a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn btn-default text-white"
                                        href="<c:url value="/logout"/>">LOGOUT</a></li>
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
        <div class="row">
            <div class="col-md-3">
                <div class='container'>
                    <div class='row'>
                        <div class='col-md-3'>
                            <img class='img-circle img-responsive' src='/files/${owner.photoMin}' />
                            <h2>${owner.username}</h2>
                            <c:if test="${isOwner}">
                            <div>
                                <form method="POST" enctype="multipart/form-data"
                                      action="/${owner.username}/upload-photo">
                                    <table>
                                        <tr>
                                            <td>File to upload:</td>
                                            <td><input type="file" name="file"/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><input type="submit" value="Upload"/></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class='row'>
                    <p class="lead">Shop Name</p>
                    <div class="list-group">
                        <a href="#" class="list-group-item">Category 1</a>
                        <a href="#" class="list-group-item">Category 2</a>
                        <a href="#" class="list-group-item">Category 3</a>
                    </div>
                </div>
            </div>

            <div class="col-md-9">
                <div class="row">
                <c:if test="${isOwner}">
                    <form:form modelAttribute="tweet" method="POST" acceptCharset="UTF-8">
                        <div class="row">
                        <div class="widget-area blank">
                            <div class="status-upload">
                                <spring:message  code="label.form.text" var="inputText"/>
                                <form:input path="text" cssClass="form-control input-lg"
                                            placeholder="${inputText}" />
                                <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Share</button>
                            </div><!-- Status Upload  -->
                        </div><!-- Widget Area -->
                      </div>
                    </form:form>
                </c:if>
                </div>
                <div class="row">
                <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
                    <c:forEach items="${tweets}" var="t">
                        <div class="well">
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-circle img-responsive"
                                         src="/files/${t.photoMin}">
                                </a>
                                <div class="media-body">
                                    <ul class="list-inline">
                                        <span>${t.ownerUsername}</span>
                                        <li>|</li>
                                        <li><span><i class="glyphicon glyphicon-calendar"></i>
                                            ${t.date}
                                    </span></li>
                                    </ul>
                                    <p>${t.text}</p>
                                    <span><i class="glyphicon glyphicon-comment"></i> 0 comments</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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

<img src="/files/${owner.photoOriginal}">
<img src="/files/${owner.photoMin}">


<div class="bs-example">
    <h2>Type your favorite car name</h2>
    <input type="text" class="typeahead tt-query" name="searcher" autocomplete="off"
           spellcheck="false">
</div>

<h1>
    <spring:message code="label.form.title.reset"></spring:message>
</h1>
<button type="submit" value="<c:url value="/${ownerUsername.username}/reset-password"/>" formmethod="post">
    <spring:message code="label.form.reset"></spring:message>
</button>

<a href="<c:url value="/logout" />">Logout</a>

<c:if test="${!isEnabled}">
    <br>
    <h1>
        <spring:message code="label.form.resendRegistrationToken"></spring:message>
    </h1>
    <button value="<c:url value="/signup/resendRegistrationToken"/>" formmethod="get">
        <spring:message code="button.form.resendRegistrationToken"></spring:message>
    </button>
</c:if>


<div>

</div>
</body>
</html>
