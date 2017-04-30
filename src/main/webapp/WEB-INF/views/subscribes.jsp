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

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-filestyle.min.js"></script>
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
                                            href="<c:url
                                        value="/${sessionScope.get('user').getUsername()}"/>">HOME
                                    </a></li>
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
        <div class="row text-center">
            <h2>Your subscribes</h2>
        </div>
        <hr class="colorgraph">
        <div class="row">
                <c:forEach items="${users}" var="t">
                    <div class="row animated fadeInUp delay">
                        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

                        <div class="well">
                            <div class="media">
                                <div class="col-md-3">
                                    <a class="pull-left" href="#">
                                        <img class="media-object img-rounded img-thumbnail img-responsive"
                                             src="/files/${t.photoMin}">
                                    </a>
                                </div>
                                <div class="col-md-6">
                                    <div class="media-body">
                                        <div class="row">
                                            <div class="col-md-2">
                                                @${t.username}

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <form action="<c:url value="/${t.username}/subscribe"/>"
                                          method="post">
                                        <button class="btn btn-default pull-left">UnSubscribe
                                        </button>
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
                    return '<a href="' + '/' + data +
                        '" class="list-group-item pull-left">' + data +
                        '</a>'
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