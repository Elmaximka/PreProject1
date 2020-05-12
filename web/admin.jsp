<%@ page import="main.java.service.UserService" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<br>
<%! UserService userService = UserService.instance();%>
<%! Gson gson = new Gson();%>
<% response.getWriter().println(gson.toJson(userService.getAllUsers()));%>
<p>

</p>
Add new User
<form action="${pageContext.request.contextPath}/admin/post" method="post">
    Name: <label>
    <input type="text" name="name"/>
</label>
    Password: <label>
    <input type="password" name="password"/>
</label>
    Gender:
    <label>
        <input type="radio" name="gender" value="female" checked/>
    </label>Female
    <label>
        <input type="radio" name="gender" value="male"/>
    </label>Male
        Role:
    <label>
    <input type="radio" name="role" value="user" checked/>
    </label>User
    <label>
        <input type="radio" name="role" value="admin">
    </label>Admin
    <input type="submit" value="Add"/>
</form>
Change User
<form action="${pageContext.request.contextPath}/admin/change" method="post">
    Name: <label>
    <input type="text" name="name"/>
</label>
    New Name: <label>
    <input type="text" name="newName"/>
</label>
    New Password: <label>
    <input type="password" name="password"/>
</label>
    New Gender:
    <label>
        <input type="radio" name="gender" value="female" checked/>
    </label>Female
    <label>
        <input type="radio" name="gender" value="male"/>
    </label>Male
    New Role:
    <label>
        <input type="radio" name="role" value="user" checked/>
    </label>User
    <label>
        <input type="radio" name="role" value="admin">
    </label>Admin
    <input type="submit" value="Change">
</form>
Delete User
<form action="${pageContext.request.contextPath}/admin/delete" method="post">
    Name: <label>
    <input type="text" name="name"/>
</label>
    <input type="submit" value="Delete">
</form>

</body>
</html>
