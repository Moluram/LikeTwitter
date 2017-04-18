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
</head>
<body>
<h1>
    <spring:message code="label.form.title.reset"></spring:message>
</h1>
<button type="submit" value="<c:url value="/${user.username}/reset-password"/>" formmethod="post">
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


<div >
    <c:if test="${isOwner}">
    <div >
        <form:form modelAttribute="tweet" method="POST" enctype="utf8">
            <tr><td>
                <label>
                    <spring:message code="label.form.text"></spring:message>
                </label>
            </td>
                <td><form:input path="text" value="" /></td>
                <form:errors path="text" element="div"/>
            </tr>

            <button type="submit">
                <spring:message code="label.form.submit"></spring:message>
            </button>
        </form:form>
    </div>
    </c:if>
    <c:forEach items="${tweets}" var="t">
        <div>
            <c:out value="${t.text}"/>
        </div>
    </c:forEach>
</div>
</body>
</html>
