<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <title tiles:fragment="title">Messages : Create</title>
</head>
<body>
<div tiles:fragment="content">
    <form name="f" th:action="@{/login}" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <div th:if="${param.error}" class="alert alert-error">
                Invalid username and password.
            </div>
            <div th:if="${param.logout}" class="alert alert-success">
                You have been logged out.
            </div>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn" onclick="validate()">Log in</button>
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