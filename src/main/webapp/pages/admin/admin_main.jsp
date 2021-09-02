<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent_En_en" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.admin_main"/></title>
</head>
<body class="other">
    <h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacists"><fmt:message key="link.all_pharmacists"/></a><br>
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies"><fmt:message key="header.all_pharmacies"/></a><br>
    <a href="${pageContext.request.contextPath}/controller?command=all_products"></a><fmt:message key="link.all_products"/><br>
    <a href="${pageContext.request.contextPath}/controller?command=inactive_pharmacists_page"><fmt:message key="link.all_inactive_pharmacists"/></a><br>
    <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
