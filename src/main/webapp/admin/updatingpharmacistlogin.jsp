<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Updating pharmacist login</title>
</head>
<body>
<h1 style="text-align: center">Update login</h1>
<form action="/updatePharmacistLogin" method="post">
    <p>${requestScope.errorUpdatingLogin}</p>
    <input type="text" name="newLogin" placeholder="new login"/>
    <input type="submit" name="Update login"/>
</form>
<a href="/admin/adminmain.jsp">Main</a>
<a href="/logout">Logout</a>
</body>
</html>
