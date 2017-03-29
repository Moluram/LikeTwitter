<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<button type="submit" value="<c:url value="/${username}/reset-password"/>" formmethod="post">
    <spring:message code="label.form.reset"></spring:message>
</button>
</body>
</html>