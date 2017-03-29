<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="messages" />
<%@ page session="true" %>
<html>
<head>
    <title><spring:message
            code="label.badUser.title"></spring:message></title>
</head>
<body>
<h1>
    <div class="alert alert-error">
        ${param.message}
</h1>
<br>
<a href="<c:url value="/signup" />"><spring:message
        code="label.form.loginSignUp"></spring:message></a>

<c:if test="${param.expired}">
    <br>
    <h1>${label.form.resendRegistrationToken}</h1>
    <button onclick="location.href = '<c:url value="/signup/resendRegistrationToken"/>' "
            formmethod="get">
        <spring:message code="label.form.resendRegistrationToken"></spring:message>
    </button>
</c:if>
</body>
</html>