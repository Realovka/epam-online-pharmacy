<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title>Updating pharmacist login</title>
</head>
<body>
<h1 style="text-align: center">Update pharmacist login</h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_login" method="post">
    <div>${requestScope.updatingPharmacistLoginError}</div>
    <input type="text" name="newLogin" placeholder="new login"/><br>
    <input type="submit" value="Update login"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
