<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>



    <link rel="stylesheet" href="css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="bootstrap.min.js"></script>

    <style>

        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>


    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title><spring:message code="label.form.title"></spring:message></title>
</head>
<body>
<div class="container">

        <fieldset>


<h1>
    <spring:message code="label.form.title"></spring:message>
</h1>
<form:form class="form-signin"  name="f"   modelAttribute="user" method="POST" enctype="utf8">
            <fieldset>
<br>

    <div class="form-group">

        <label>
            <spring:message code="label.user.username"></spring:message>
        </label>


        <div class="form-group">
            <form:input type="username"  class="form-control" placeholder="Username"  path="username" value="" />
            <form:errors path="username" element="div"/>




        <label>
            <spring:message code="label.user.email"></spring:message>
        </label>


        <form:input type="email" class="form-control" placeholder="Email address" id="exampleInputEmail1"  path="email" value="" />
            <form:errors path="email" element="div" />



        <label>
            <spring:message code="label.user.password"></spring:message>
        </label>


            <form:input class="form-control" placeholder="Password"  id="password" name="password" path="password" value="" type="password" />
        <form:errors path="password" element="div" />


        <label>
            <spring:message code="label.user.confirmPass"></spring:message>
        </label>


        <form:input class="form-control" placeholder="Repead password"  id="password" name="password" path="matchingPassword" value="" type="password" />
        <form:errors element="div" />
        </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">
        <spring:message code="label.form.submit"></spring:message>
    </button>
    </form:form>
    <br>
    <a href="<c:url value="/signin" />">
        <spring:message code="label.form.loginLink"></spring:message>
    </a>
    </div></fieldset>

</body>
</html>