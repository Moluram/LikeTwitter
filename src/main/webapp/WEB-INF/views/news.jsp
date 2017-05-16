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

    <!-- Site made with Mobirise Website Builder v3.12.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png" type="image/x-icon">
    <title><spring:message code="title.news"/></title>
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
    <script src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/typeahead.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/smooth-scroll/smooth-scroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jarallax/jarallax.js"></script>
    <script src="${pageContext.request.contextPath}/resources/mobirise/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/resources/formoid/formoid.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-filestyle.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<section class="mbr-navbar mbr-navbar--xs mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
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
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons btn-inverse mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                <li class="mbr-navbar__item">
                                    <div class="input-group typeahead" role="search">
                                        <input type="search" id="q" name="q"
                                               placeholder="Search for user"
                                               autocomplete="off"
                                               class="form-control input-sm">
                                    </div>

                                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                </li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white"
                                        href="<c:url
                                        value="/news"/>"><spring:message code="navbar.button.news"/>
                                </a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white"
                                        href="<c:url
                                        value="/${sessionScope.get('user').getUsername()}"/>"><spring:message code="navbar.button.home"/>
                                </a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white"
                                        href="<c:url value="/about"/> "><spring:message code="navbar.button.about"/>
                                </a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn text-white" href="<c:url value="/contact"/>"><spring:message code="navbar.button.contact"/></a></li>
                                <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn btn-default text-white"
                                        href="<c:url value="/logout"/>"><spring:message code="navbar.button.logout"/></a></li>
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
        <div class="row text-center">
            <h2><spring:message code="title.news"/></h2>
            <button onclick="updateNews('${sessionScope.get('user').getUsername()}', '<spring:message code="label.button.comments.hide"/>',
            '<spring:message code="label.button.comments.show"/>')" class="mbr-buttons__link btn btn-default text-white">
            <spring:message code="button.news.update"/></button>
        </div>
        <hr class="colorgraph">
        <div class="row" id="news">
            <c:forEach items="${news}" var="t">
                <div class="row animated fadeInUp delay tweet" id="tweet_${t.id}">
                    <link rel="stylesheet"
                          href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

                    <div class="well">
                        <div class="media">
                            <div class="col-md-3">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-rounded img-thumbnail img-responsive"
                                         src="/files/${t.photoMin}">
                                </a>
                            </div>
                            <div class="col-md-9">
                                <div class="row">
                                    <div class="col-md-2">
                                        @${t.ownerUsername}
                                    </div>
                                    <div class="col-md-10">|
                                        <span><i class="glyphicon glyphicon-calendar"></i>
                                                ${t.date}
                                        </span>
                                    </div>
                                </div>
                                <div class="row">
                                        ${t.text}
                                </div>
                                <div class="row">
                                    <button class="btn btn-sm btn-success"
                                            onclick="like(${t.id},'${sessionScope.get('user').getUsername()}')">
                                            <span id="likes${t.id}" class="glyphicon glyphicon-thumbs-up">
                                                    ${t.numberOfLikes}
                                            </span>
                                    </button>
                                </div>
                            </div>

                            <button class="btn btn-default" onclick="loadComments(${t.id})"
                                    id="show-comments-btn"><spring:message code="label.button.comments.show"/>
                            </button>
                            <button class="btn btn-default hidden" onclick="hideComments(${t.id})"
                                    id="hide-comments-btn"><spring:message code="label.button.comments.hide"/>
                            </button>
                            <div id="comments"></div>
                            <div class="add-new-comment">
                                <form action="/comments" method="POST"
                                      class="add-comment-form hidden">
                                    <input type="text" name="text" placeholder="Input comment"
                                           class="new-comment-text" autocomplete="off">
                                    <input type="hidden" name="author" value="${user.username}">
                                    <input type="hidden" name="tweetId" value="${t.id}">
                                    <input type="hidden" name="parentId" value="">
                                    <button type="submit" class="btn btn-success" disabled="true">Add</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
</body>
</html>