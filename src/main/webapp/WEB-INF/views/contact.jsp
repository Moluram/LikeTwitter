<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
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
          rel="stylesheet" type="text/css">


</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/gnome-backgrounds_3.jpg);">

<section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
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
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white"
                                       href="<c:url value="/about"/>">ABOUT
                                    </a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white" href="<c:url
                                    value="/contact"/>">CONTACT</a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn btn-success" href="<c:url
                                    value="/"/>">HOME</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-9" >
    <div class="contact-section text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <h2>Contact Us</h2>
                            <p>Feel free to shout us by feeling the contact form.</p>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputName2">Name</label>
                            <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail2">Email</label>
                            <input type="email" class="form-control" id="exampleInputEmail2" placeholder="jane.doe@example.com">
                        </div>
                        <div class="form-group ">
                            <label for="exampleInputText">Your Message</label>
                            <textarea  class="form-control" placeholder="Description"></textarea>
                        </div>
                        <div class="form-group ">
                            <button type="submit" class="btn btn-success">Send Message</button>
                         </div>
                    </form>
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


</body>
</html>