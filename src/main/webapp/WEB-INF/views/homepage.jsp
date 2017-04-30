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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.11.1/typeahead.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/smooth-scroll/smooth-scroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jarallax/jarallax.js"></script>
    <script src="${pageContext.request.contextPath}/resources/mobirise/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/resources/formoid/formoid.min.js"></script>
    <style>
        input[type=text] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 3px solid #ccc;
            -webkit-transition: 0.5s;
            transition: 0.5s;
            outline: none;
        }

        input[type=text]:focus {
            border: 3px solid #555;
        }
    </style>
</head>
<body>
<section class="mbr-navbar mbr-navbar--xs mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
    <div class="mbr-navbar__section mbr-section">
        <c:if test="${!isEnabled}">
            <div class="row">
        </c:if>
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
            <c:if test="${!isEnabled}">
            </div>

            <div class="row">
                <div style="background:#FFE4B5;">
                    <div class="container">
                        <div class="mbr-navbar__column mbr-navbar__menu pull-right">
                            <div class="mbr-navbar__column center-block pull-right">
                                <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons mbr-buttons--freeze mbr-buttons--center btn-decorator mbr-buttons--active list-inline">
                                    <li class="mbr-navbar__item" style="margin-top: 8px"><small class="text-center h3" >You're not confirm your email!</small></li>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn btn-warning"
                                            href="<c:url value="/logout"/>">RESEND EMAIL</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
    </div>
</section>
<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-9" >
    <div class="mbr-section__container mbr-section__container--std-padding container" style="padding-top: 50px; padding-bottom: 93px;">
        <div class="row">
            <div class="col-md-3">
                    <div class='row animated fadeInUp'>
                        <a class="pull-left" href="#">
                            <img class='media-object img-rounded img-thumbnail img-responsive'
                                 src='/files/${owner.photoMin}' />
                        </a>
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
                <div class='row animated fadeInUp'>
                    <p class="lead">Shop Name</p>
                    <div class="list-group" style="margin-right: 5px">
                        <div class="form-group list-group-item">
                            <label for="resetPassword"> <spring:message code="label.form.title.reset"/></label>
                            <button type="submit" class="btn btn-default" id="resetPassword" value="<c:url
                        value="/${owner.username}/reset-password"/>" formmethod="post">
                                <spring:message code="label.form.reset"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-9">
                    <c:if test="${isOwner}">
                        <form:form modelAttribute="tweet" method="POST" acceptCharset="UTF-8">
                            <div class="row">
                                <div class="widget-area blank rounded border animated fadeInUp">
                                    <div class="form-group">
                                        <spring:message  code="label.form.text" var="inputText"/>
                                        <form:input type="text" path="text"
                                                    cssClass="form-control input-lg"
                                                    placeholder="${inputText}" />

                                        <button type="submit" class="btn btn-success pull-right"><i
                                                class="fa fa-share"></i> Share</button>
                                    </div>
                                </div><!-- Widget Area -->
                             </div>
                        </form:form>
                    </c:if>

                <hr class="colorgraph">
                <c:forEach items="${tweets}" var="t">
                <div class="row animated fadeInUp delay">
                    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

                        <div class="well">
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-rounded img-thumbnail img-responsive"
                                         src="/files/${t.photoMin}">
                                </a>
                                <div class="media-body">
                                    <ul class="list-inline">
                                        <h4>@${t.ownerUsername}</h4>
                                        <li>|</li>
                                        <li><span><i class="glyphicon glyphicon-calendar"></i>
                                            ${t.date}
                                    </span></li>
                                    </ul>
                                    <h5>${t.text}</h5>
                                    <span><i class="glyphicon glyphicon-comment"></i> 0 comments</span>
                                </div>
                            </div>
                        </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>



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
<button type="submit" value="<c:url value="/${owner.username}/reset-password"/>" formmethod="post">
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
<script type="text/javascript">
    var productsearcher;
    jQuery(document).ready(function($) {
        productsearcher = new Bloodhound({
            datumTokenizer: function (d) {
                return Bloodhound.tokenizers.whitespace(d.value);
            },
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            replace: function (url, uriEncodedQuery) {
                return url + "#" + uriEncodedQuery;
                // the part after the hash is not sent to the server
            },
            remote: {
                url: '/search' ,
                ajax: {
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({
                        partialSearchString: 'fire',
                        category: 'all'
                    }),
                    success: function (data) {
                        console.log("Got data successfully");
                        console.log(data);
                    }
                }
            }
        });

        // initialize the bloodhound suggestion engine
        productsearcher.initialize();

        // instantiate the typeahead UI
        $('#q').typeahead({
            hint: true,
            highlight: true,
            minLength: 1
        }, {
            source: productsearcher.ttAdapter(),

            // This will be appended to "tt-dataset-" to form the class name of the suggestion menu.
            name: 'usersList',

            // the key from the array we want to display (name,id,email,etc...)
            templates: {
                empty: [
                    '<div class="list-group search-results-dropdown"><div class="list-group-item">Nothing found.</div></div>'
                ],
                header: [
                    '<div class="list-group search-results-dropdown">'
                ],
                suggestion: function (data) {
                    return '<a href="' + data + '" class="list-group-item">' + data + '</a>'
                }
            }
        })
        // Set the Options for "Bloodhound" suggestion engine

    });

    function goToPage() {
        var url = document.getElementById("q").value;
        document.location.href = "/" + url.value;
    }
</script>