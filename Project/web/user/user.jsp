<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 11.05.2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<% User user = (User)session.getAttribute("role");%>
<%! Gson gson = new Gson();%>
<% response.getWriter().println(gson.toJson(user));%>
</body>
</html>