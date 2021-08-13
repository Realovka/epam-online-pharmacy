<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main for admin</title>
</head>
<body>
<div style="text-align: center">
    <h1>Hello ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
    <a href="/allPharmacists">Pharmacists waiting verification and active pharmacists</a>
    <a href="/additionproduct.jsp">Addition new product</a>
    <a href="/inactivePharmacists">Inactive pharmacists</a>
    <a href="/logout">Logout</a>
</div>
</body>
</html>
