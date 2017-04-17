<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title><spring:message code="label.form.title"></spring:message></title>
</head>
<body>




<h1>
    <spring:message code="label.form.title"></spring:message>
</h1>
<form:form  class="form-inline" modelAttribute="user" method="POST" enctype="utf8">

    <br>
    <tr>
        <div class="form-group"><td>

        <label>
            <spring:message code="label.user.username"></spring:message>
        </label>
    </td>
            <div class="form-group">

            <td><form:input class="sr-only" path="username" value="" /></td>
                <form:errors path="username" element="div"/></div>
    </tr>
<div class="form-group">
    <tr><td>
        <label>
            <spring:message code="label.user.email"></spring:message>
        </label>
    </td>
        <div class="form-group">
        <td><form:input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email" path="email" value="" /></td>
        <form:errors path="email" element="div" />
        </div>
    </tr>
    <tr><td>
        <label>
            <spring:message code="label.user.password"></spring:message>
        </label>
    </td>
        <td>
            <form:input path="password" value="" type="password" /></td>
        <form:errors path="password" element="div" />
    </tr>
    <tr><td>
        <label>
            <spring:message code="label.user.confirmPass"></spring:message>
        </label>
    </td>
        <td><form:input path="matchingPassword" value="" type="password" /></td>
        <form:errors element="div" />
    </tr>
    <button type="submit">
        <spring:message code="label.form.submit"></spring:message>
    </button>
</form:form>
<br>
<a href="<c:url value="/signin" />">
    <spring:message code="label.form.loginLink"></spring:message>
</a>


    <%--<form class="form-inline" role="form">--%>
        <%--<div class="form-group">--%>
            <%--<label class="sr-only" for="exampleInputEmail2">Email</label>--%>
            <%--<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label class="sr-only" for="exampleInputPassword2">Пароль</label>--%>
            <%--<input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">--%>
        <%--</div>--%>
        <%--<div class="checkbox">--%>
            <%--<label>--%>
                <%--<input type="checkbox"> Запомнить меня--%>
            <%--</label>--%>
        <%--</div>--%>
        <%--<button type="submit" class="btn btn-default">Войти</button>--%>
    <%--</form>--%>


</body>
</html>