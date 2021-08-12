<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Authorization</title>
</head>
<body>
<div style="text-align: center">
    <h1>Authorization</h1>
<form action="/auth" method="post">
    <p>${applicationScope.errorAuthentication}</p>
    Login<input type="text" name="loginAuthorization"/>
    Password<input type="password" name="passwordAuthorization"/>
    <input type="submit" name="Authorization"/>
    <a href="/registration.jsp">Registration</a>
    <a href="/logout">Logout</a>
</form>
</div>
</body>
</html>
