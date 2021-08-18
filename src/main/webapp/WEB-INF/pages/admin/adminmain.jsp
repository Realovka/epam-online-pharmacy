<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/common.css">
    <title>Admin main</title>
</head>
<body class="other">
<div style="text-align: center">
    <h1>Hello ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
    <a href="/allPharmacists">Pharmacists waiting verification and active pharmacists</a>
    <a href="/allPharmacies">All pharmacies</a>
    <a href="/inactivePharmacists">Inactive pharmacists</a>
    <a href="/logout">Logout</a>
</div>
</body>
</html>
