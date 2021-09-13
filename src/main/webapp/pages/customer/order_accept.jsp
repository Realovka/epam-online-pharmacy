<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title>Order accept</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>
<nav class="menu">
    <ul>
        <li>
            <a href="#"><fmt:message key="link.name.how_to_do_order"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.questions"/></a>
        </li>
    </ul>
</nav>
<label>Order is accepted</label>
<a href="${pageContext.request.contextPath}/controller?command=main_customer"><fmt:message key="link.customer_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
