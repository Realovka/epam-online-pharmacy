<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/common_menu.css">
    <title>Alpha pharmacy</title>
</head>

<body>

<nav class="menu">
    <ul>
        <li>
            <a href="#">How to do order</a>
        </li>
        <li>
            <a href="#">About us</a>
        </li>
        <li>
            <a href="#">Questions</a>
        </li>
    </ul>
</nav>
<h2>Authorization</h2>
<div class="auth">
    <form action="/auth" method="post">
        <p>
            ${requestScope.errorAuthentication}
        </p>
        <input type="text" name="loginAuthorization" placeholder="login"/><br>
        <input type="password" name="passwordAuthorization" placeholder="password"/><br>
        <input type="submit" value="Sign in"/><br>
        <a href="/registration.jsp">Registration</a><br>
        <a href="/regcustomer.jsp">Verification customer</a><br>
        <a href="/logout">Logout</a>
    </form>
</div>
<div class="titles">
    <div class="title_first">
        Taking Care Of Your Health
    </div>
    <h1>
        Alpha Pharmacy
    </h1>
</div>
</body>
</html>
