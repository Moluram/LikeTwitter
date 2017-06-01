<%--
  Created by IntelliJ IDEA.
  User: nborsuk
  Date: 16.05.2017
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.users"/></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Site made with Mobirise Website Builder v3.12.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png"
          type="image/x-icon">
    <title><spring:message code="title.users"/></title>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/mobirise/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/mobirise/css/mbr-additional.css"
          type="text/css">
    <link href="${pageContext.request.contextPath}/resources/animate.css/animate.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css"
          rel="stylesheet" type="text/css"/>
</head>
<body onload="pagination(${pageCount},${currentPage})">
<c:import url="includes/header.jsp"/>
<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar"
         id="form1-9">
    <div class="mbr-section__container mbr-section__container--std-padding container"
         style="padding-top: 50px; padding-bottom: 93px;">
        <hr class="colorgraph">
        <div class="row" id="users" class="user-list-wrapper">
            <c:forEach items="${users}" var="u">
                <div class="row animated fadeInUp delay tweet user-list-wrapper" id="user_${u.id}">
                    <link rel="stylesheet"
                          href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
                    <div class="well">
                        <div class="media">
                            <div class="col-md-2">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-rounded img-thumbnail img-responsive user-mini-photo"
                                         src="/files/${u.photoMin}">
                                </a>
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-md-12">
                                        <a href="/${u.username}">@${u.username}</a>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${user.id!=u.id}">
                                <c:choose>
                                    <c:when test="${u.baned}">
                                        <div class="col-md-2">
                                            <button class="btn btn-success" id="unban-btn" onclick="unBanUser(${u.id})">Unban</button>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-2">
                                            <button class="btn btn-danger" id="ban-btn" onclick="banUser(${u.id})">Ban</button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:forEach>
            <ul class="pagination animated fadeInUp delay text-center" id="user-pagination"></ul>
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

</body>

<c:import url="includes/footer.jsp"/>
</html>
