<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Authorization</h2>
</body>
<form action="/authorization" method="post">
    <p>${sessionScope.errorAuthorization}</p>
    Login<input type="text" name="loginAuthorization"/>
    Password<input type="text" name="passwordAuthorization"/>
    <input type="submit" name="Authorization"/>
    <a href="/registration.jsp">Registration</a>
    <a href="/logout">Logout</a>
</form>
</html>
