<%--
  Created by IntelliJ IDEA.
  User: Lenka
  Date: 23.03.2017
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">

    <form:label path="username">Enter your user-name</form:label>
    <form:input id="username" name="username" path="username" /><br>
    <form:label path="username">Please enter your password</form:label>
    <form:password id="password" name="password" path="password" /><br>
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>