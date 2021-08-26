<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title>Admin main</title>
</head>
<body class="other">
    <h1>Hello ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacists">Pharmacists waiting verification and active pharmacists</a>
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies">All pharmacies</a>
    <a href="${pageContext.request.contextPath}/controller?command=all_products">All products</a>
    <a href="${pageContext.request.contextPath}/controller?command=inactive_pharmacists_page">Inactive pharmacists</a>
    <a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
</body>
</html>
