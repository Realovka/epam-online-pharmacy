<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/reg.css">
    <link rel="stylesheet" href="styles/common_menu.css">
    <title>Registration</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=login_page">Back to home page</a>
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
<div class="registr">
    <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <h1>Registration</h1><br>
        <p>${sessionScope.registrationError}</p><br>
        <input type="text" name="login" placeholder="login"/><br>
        <input type="password" name="password" placeholder="password"/><br>
        <input type="text" name="firstName" placeholder="first name"/><br>
        <input type="text" name="lastName" placeholder="last name"/><br>
        <input type="email" name="email" placeholder="email"/><br>
        <input type="tel" name="telephone" placeholder="telephone"/><br>
        <select name="role">
            <option>PHARMACIST</option>
            <option>CUSTOMER</option>
            <option>ADMIN</option>
        </select><br><br>
        <input type="submit" value="Registration">
    </form>
</div>
</div>
</body>
</html>
