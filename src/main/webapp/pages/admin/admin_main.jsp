<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.admin_main"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=all_pharmacists" class="common_link"><fmt:message
                key="link.all_pharmacists"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies" class="common_link"><fmt:message
                key="header.all_pharmacies"/></a><br>
    </li>
    <li>
    <a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
            key="link.all_products"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=inactive_pharmacists_page"
           class="common_link"><fmt:message key="link.all_inactive_pharmacists"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
                key="link.logout"/></a>
    </li>
</ul>
</body>
</html>
