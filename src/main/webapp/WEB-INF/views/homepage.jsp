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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="../js/typeahead/0.11.1/typeahead.bundle.js"></script>

    <link rel="stylesheet" href="css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="bootstrap.min.js"></script>
</head>
<body>
<div>
    <form method="POST" enctype="multipart/form-data" action="/${user.username}/upload-photo">
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
<img src="/files/${user.userProfile.photoUrl}">
<img src="/files/${user.userProfile.miniPhoto}">
<form>
    <h2><spring:message code="label.form.search"></spring:message></h2>
    <input type="text" class="tt-query" id="username"/>
    <button type="submit" formmethod="get" value="<c:url value="/search"/>"></button>
</form>
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


<div>
    <c:if test="${isOwner}">
        <div>
            <form:form modelAttribute="tweet" method="POST" acceptCharset="UTF-8">
                <tr>
                    <td>
                        <label>
                            <spring:message code="label.form.text"></spring:message>
                        </label>
                    </td>
                    <td><form:input path="text" value=""/></td>
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
