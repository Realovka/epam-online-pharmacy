<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title>Updating pharmacist first name</title>
</head>
<body>
<h1 style="text-align: center">Update pharmacist first name</h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_first_name" method="post">
    <p>${requestScope.updatingPharmacistFirstNameError}</p>
    <input type="text" name="newFirstName" placeholder="new first name"/><br>
    <input type="submit" value="Update first name"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin">Main</a>
<a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
</body>
</html>
