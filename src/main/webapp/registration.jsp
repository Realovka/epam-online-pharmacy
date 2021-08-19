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
<a href="/index.jsp">Back to home page</a>
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
    <form action="/reg" method="post">
        <h1>Registration</h1><br>
        <p>${requestScope.errorRegistration}</p><br>
        <input type="text" name="loginRegistration" placeholder="login"/><br>
        <input type="password" name="passwordRegistration" placeholder="password"/><br>
        <input type="text" name="firstNameRegistration" placeholder="first name"/><br>
        <input type="text" name="lastNameRegistration" placeholder="last name"/><br>
        <input type="email" name="emailRegistration" placeholder="email"/><br>
        <input type="tel" name="telephoneRegistration" placeholder="telephone"/><br>
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
