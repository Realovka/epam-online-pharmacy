<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style.css">
    <title>Home page</title>
</head>
<body>
<div style="text-align: center">
    <h1>Authorization</h1>
    <form action="/auth" method="post">
        <p>${requestScope.errorAuthentication}</p>
        <input type="text" name="loginAuthorization" placeholder="login"/>
        <input type="password" name="passwordAuthorization" placeholder="password"/>
        <input type="submit" name="Authorization"/>
        <a href="/registration.jsp">Registration</a>
        <a href="/logout">Logout</a>
    </form>
</div>
</body>
</html>
