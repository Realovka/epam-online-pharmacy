<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div style="text-align: center">
    <h1>Registration</h1>
<form action="/reg" method="post">
    <p>${requestScope.errorRegistration}</p>
    <input type="text" name="loginRegistration" placeholder="login"/>
    <input type="password" name="passwordRegistration" placeholder="password"/>
    <input type="text" name="firstNameRegistration" placeholder="first name"/>
    <input type="text" name="lastNameRegistration" placeholder="last name"/>
    <input type="email" name="emailRegistration" placeholder="email"/>
    <input type="tel" name="telephoneRegistration" placeholder="telephone"/>
    Role <select name="role">
    <option>PHARMACIST</option>
    <option>CUSTOMER</option>
    <option>ADMIN</option>
</select>
    <input type="submit" name="Registration">
</form>
</div>
</body>
</html>
