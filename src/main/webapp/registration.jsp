<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Registration</h2>
</body>
<form action="/registration" method="post">
    <p>${sessionScope.errorRegistration}</p>
    Login<input type="text" name="loginRegistration"/>
    Password<input type="password" name="passwordRegistration"/>
    First name <input type="text" name="firstNameRegistration"/>
    Last name <input type="text" name="lastNameRegistration"/>
    email <input type="email" name="emailRegistration"/>
    Telephone <input type="tel" name="telephoneRegistration"/>
    Role <select name="role">
    <option>PHARMACIST</option>
    <option>CUSTOMER</option>
    <option>ADMIN</option>
</select>
    <input type="submit" name="Registration">
</form>
</html>
