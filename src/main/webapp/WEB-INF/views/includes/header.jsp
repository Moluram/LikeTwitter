<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section
        class="mbr-navbar mbr-navbar--xs mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse"
        id="ext_menu-3">
    <div class="mbr-navbar__section mbr-section">
            <div class="mbr-section__container container">
                <div class="mbr-navbar__container">
                    <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                        <span class="mbr-brand__logo"><a href="<c:url value="/"/>"><img
                                src="${pageContext.request.contextPath}/resources/images/twitter-icon-circle-blue-logo-preview.png"
                                class="mbr-navbar__brand-img mbr-brand__img" alt="LikeTwitter"></a></span>
                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white"
                                                         href="<c:url value="/"/>">LikeTwitter</a></span>
                    </span>
                    </div>
                    <div class="mbr-navbar__hamburger mbr-hamburger"><span
                            class="mbr-hamburger__line"></span></div>
                    <div class="mbr-navbar__column mbr-navbar__menu">
                        <nav class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
                            <div class="mbr-navbar__column">
                                <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons btn-inverse mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                                    <li class="mbr-navbar__item input-group typeahead">
                                        <input type="search" id="q" name="q"
                                               placeholder="Search for user"
                                               autocomplete="off"
                                               class="form-control input-sm">
                                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                    </li>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn text-white"
                                            href="<c:url
                                        value="/subscribe"/>"><spring:message code="navbar.button.subscribes"/>
                                    </a></li>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn text-white"
                                            href="<c:url
                                        value="/news"/>"><spring:message code="navbar.button.news"/>
                                    </a></li>
                                    <c:if test="${user.role.name=='ROLE_ADMIN'}">
                                        <li class="mbr-navbar__item"><a
                                                class="mbr-buttons__link btn text-white"
                                                href="<c:url
                                        value="/admin/users?page=1"/>"><spring:message code="navbar.button.users"/>
                                        </a></li>
                                    </c:if>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn text-white"
                                            href="<c:url value="/about"/> "><spring:message code="navbar.button.about"/>
                                    </a></li>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn text-white"
                                            href="<c:url value="/contact"/>"><spring:message
                                            code="navbar.button.contact"/></a></li>
                                    <c:if test="${!isEnabled}">
                                    <li class="mbr-navbar__item"><a
                                        class="mbr-buttons__link btn btn-warning" id="resendEmail"
                                        onclick="resendEmail('<spring:message code="navbar.text.resendEmail"/>')">
                                        <spring:message code="navbar.email.resendEmail"/></a></li>
                                    </c:if>
                                    <li class="mbr-navbar__item"><a
                                            class="mbr-buttons__link btn btn-default text-white"
                                            href="<c:url value="/logout"/>"><spring:message
                                            code="navbar.button.logout"/></a></li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
</section>