<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title>Updating pharmacist email</title>
</head>
<body>
<h1 style="text-align: center">Update pharmacist email</h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_email" method="post">
    <p>${requestScope.updatingPharmacistEmailError}</p>
    <input type="email" name="newEmail" placeholder="new email"/><br>
    <input type="submit" value="Update email"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin">Main</a>
<a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
</body>
</html>
