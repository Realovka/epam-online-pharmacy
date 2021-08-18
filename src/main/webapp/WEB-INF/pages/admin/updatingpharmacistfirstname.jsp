<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/common.css">
    <title>Updating pharmacist first name</title>
</head>
<body>
<h1 style="text-align: center">Update first name</h1>
<form action="/updatePharmacistFirstName" method="post">
    <p>${requestScope.errorUpdatingFirstName}</p>
    <input type="text" name="newFirstName" placeholder="new first name"/>
    <input type="submit" name="Update first name"/>
</form>
<a href="/mainAdmin">Main</a>
<a href="/logout">Logout</a>
</body>
</html>
