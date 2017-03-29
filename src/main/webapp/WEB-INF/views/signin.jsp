<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: berthold
  Date: 29.03.2017
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<html>
<head></head>
<body>
<h1>Sign in</h1>
<form name='f' action="/signin" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" onclick="validate()" /></td>
        </tr>
    </table>
</form>
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

<c:if test="${param.regSucc == true}">
    <div id="status">
        <spring:message code="message.regSucc">
        </spring:message>
    </div>
</c:if>
<c:if test="${param.regError == true}">
    <div id="error">
        <spring:message code="message.regError">
        </spring:message>
    </div>
</c:if>