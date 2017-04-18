<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Moluram
  Date: 4/17/2017
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="bootstrap.min.js"></script>
</head>
<body>

<div class="bs-example">
    <form >
        <h2> <spring:message code="label.form.search"></spring:message></h2>
        <input type="text" class="tt-query" id="username"/>
        <button formmethod="get" value="<c:url value="/search"/>"></button>
    </form>
</div>
<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Username</th>
            <th>Submit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usernames}" var="t">
            <tr>
                <td>t</td>
                <td><button formmethod="get" value="<c:url value="/${t}"/>"></button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
