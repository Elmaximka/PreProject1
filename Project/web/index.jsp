<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login Page</title>
</head>
<body>
Login
<form action="${pageContext.request.contextPath}/user" method="post">
  Name: <label>
  <input type="text" name="name"/>
</label>
  Password: <label>
  <input type="password" name="password"/>
</label>
  <input type="submit" name="Login">
</form>
</body>
</html>