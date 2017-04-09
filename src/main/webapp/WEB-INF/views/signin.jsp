<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <title tiles:fragment="title">Messages : Create</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
<div tiles:fragment="content">
    <form class="form-horizontal"  name="f" th:action="@{/login}" method="post">
        <fieldset>

            <legend>Please Login</legend>

            <div th:if="${param.error}" class="alert alert-error">
                Invalid username and password.
            </div>
            <div th:if="${param.logout}" class="alert alert-success">
                You have been logged out.
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="username" style="color: #1b6d85">Username</label>
                <div class="col-sm-10">
                <input  class="form-control"  placeholder="Username" type="text" id="username" name="username"/>
                </div>
            </div>
            <div class="form-group">
            <label class="col-sm-2 control-label" for="password" style="color: #1b6d85" >Password</label>
                <div class="col-sm-10">
            <input type="password" class="form-control" placeholder="Password"  id="password" name="password"/>
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                <button style="padding-bottom: 5px" type="submit" class="btn btn-default"  onclick="validate()">Log in</button>
            </div>
            </div>
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