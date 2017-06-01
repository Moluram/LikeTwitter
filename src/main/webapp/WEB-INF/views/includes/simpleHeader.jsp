<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-3">
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
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                <li class="mbr-navbar__item">
                                    <a href="?lang=ru_RU"><img src='
                                        ${pageContext.request.contextPath}/resources/images/russia-flag-16x16.png'/></a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a href="?lang=en_EN">
                                        <img src="${pageContext.request.contextPath}/resources/images/united-kingdom-flag-16x16.png"alt="LikeTwitter"></a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white"
                                       href="<c:url value="/about"/>"><spring:message code="navbar.button.about"/>
                                    </a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn text-white" href="<c:url
                                    value="/contact"/>"><spring:message code="navbar.button.contact"/></a>
                                </li>
                                <li class="mbr-navbar__item">
                                    <a class="mbr-buttons__link btn btn-success" href="<c:url
                                    value="/"/>"><spring:message code="navbar.button.home"/></a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>