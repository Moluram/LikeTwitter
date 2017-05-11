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
            code="label.updatePassword.title"/></title>
</head>
<body>
<form:form modelAttribute="passwords" method="POST" enctype="utf8">
    <tr>
        <spring:message code="label.user.password" var="password"/>
        <form:errors cssClass="alert alert-danger" path="password" element="div"/>
        <form:input path="password" value="" type="password" /></td>
    </tr>
    <tr><td>
        <label>
            <spring:message code="label.user.confirmPass"/>
        </label>
    </td>
        <td><form:input path="matchingPassword" value="" type="password" /></td>
        <form:errors element="div" />
    </tr>
    <button type="submit">
        <spring:message code="label.form.submit"/>
    </button>
</form:form>
</body>
</html>