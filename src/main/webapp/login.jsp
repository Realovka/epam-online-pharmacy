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
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
        <p>
            ${sessionScope.loginError}
        </p>
        <input type="text" name="login" placeholder="login"/><br>
        <input type="password" name="password" placeholder="password"/><br>
        <input type="submit" value="Sign in"/><br>
    </form>
    <a href="${pageContext.request.contextPath}/controller?command=registration_page">Registration</a><br>
    <a href="${pageContext.request.contextPath}/controller?command=verification_customer_page">Verification customer</a><br>
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

