<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <title tiles:fragment="title">Messages : Create</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">


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

</head>
<body>
<div class="container">
    <form class="form-signin"  name="f" th:action="@{/login}" method="post">
        <fieldset>
            <h2 class="form-signin-heading">Please sign in</h2>
            <%--<legend>Please Login</legend>--%>

            <div th:if="${param.error}" class="alert alert-error">
                Invalid username and password.
            </div>
            <div th:if="${param.logout}" class="alert alert-success">
                You have been logged out.
            </div>
            <div class="form-group">

                <input  type="username" class="form-control" placeholder="Username" required autofocus type="text" id="username" name="username"/>

            <input type="password" class="form-control" placeholder="Password"  id="password" name="password"/>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-lg btn-primary btn-block" type="submit"  onclick="validate()">Log in</button>

        </fieldset>
    </form>
</div>
</body>
</html>

<script type="text/javascript">
  function validate() {
    if (document.f.username.value == "" && document.f.password.value == "") {
      alert("Username and password are required");
      document.f.username.focus();
      return false;
    }
    if (document.f.username.value == "") {
      alert("Username is required");
      document.f.username.focus();
      return false;
    }
    if (document.f.password.value == "") {
      alert("Password is required");
      document.f.password.focus();
      return false;
    }
  }
</script>


