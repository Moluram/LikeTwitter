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

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v3.12.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png"
          type="image/x-icon">
    <title><spring:message code="title.home"/></title>

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
<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar"
        id="form1-9">
    <div class="mbr-section__container mbr-section__container--std-padding container"
         style="padding-top: 50px; padding-bottom: 93px;">
        <div class="row">
            <div class="col-md-3">
                <div class='row animated fadeInUp'>
                    <a class="pull-left" href="#">
                        <img class='user-photo img-circle'
                             src='/files/${owner.photoMin}'/>
                    </a>
                    <h2>@${owner.username}</h2>
                    <c:if test="${!isOwner}">
                        <button id="subscribe${owner.username}" onclick="subscribe('${owner.username}')"
                                class="btn btn-success <c:if test="${isSubscribes}"> hidden </c:if>  pull-left">
                            <spring:message code="label.button.subscribe"/>
                        </button>
                        <button id="unsubscribe${owner.username}" onclick="subscribe('${owner.username}')"
                                class="btn btn-default <c:if test="${!isSubscribes}"> hidden </c:if> pull-left">
                            <spring:message code="label.button.unsubscribe"/>
                        </button>
                    </c:if>
                </div>
                <div class='row animated fadeInUp'>
                    <p class="lead"><spring:message code="label.settings.title"/></p>
                    <div class="list-group" style="margin-right: 20px">
                        <c:if test="${isOwner}">
                            <div class="list-group-item row">
                                <label id="resetPasswordLabel" for="resetPassword"> <spring:message
                                        code="label.form.title.reset"/></label>
                                <a class="btn btn-default pull-right" id="resetPassword" onclick="resetPassword('<spring:message
                                        code="label.form.title.reset.success"/>', '${user.username}')">
                                    <spring:message code="label.form.reset"/>
                                </a>
                            </div>
                            <div class="list-group-item row">
                                <form enctype="multipart/form-data" method="post"
                                      action="/${user.username}/upload-photo">
                                    <label for="file"><spring:message code="label.settings.upload.title"/></label>
                                    <input type="file" name="file" id="file" data-size="sm"
                                           data-buttonBefore="true" class="btn-group-sm filestyle"
                                           data-buttonName="btn-primary"/>
                                    <button type="submit" class="btn btn-success pull-right"
                                            id="uploadFile"><spring:message code="label.settings.upload.button"/>
                                    </button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="col-md-9">
                <c:if test="${isOwner}">
                    <form:form modelAttribute="tweet" method="POST" acceptCharset="UTF-8">
                        <div class="row">
                            <div class="widget-area blank rounded border animated fadeInUp">
                                <div class="form-group">
                                    <spring:message code="label.form.text" var="inputText"/>
                                    <form:input type="text" path="text"
                                                cssClass="form-control input-lg"
                                                placeholder="${inputText}"/>

                                    <button type="submit" class="btn btn-success pull-right"><i
                                            class="fa fa-share"></i><spring:message code="label.tweets.share"/>
                                    </button>
                                </div>
                            </div><!-- Widget Area -->
                        </div>
                    </form:form>
                </c:if>
                <hr class="colorgraph">
                <c:forEach items="${tweets}" var="t">
                    <div class="row animated fadeInUp delay tweet" id="tweet_${t.id}">
                        <link rel="stylesheet"
                              href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

                        <div class="well">
                            <div class="media">
                                <div class="col-md-3">
                                    <a class="pull-left" href="#">
                                    <img class="user-photo img-circle"
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
                                <div id="add-new-comment">
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
</html>