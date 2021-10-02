<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/reg.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="title.how_to_do_order"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: ghostwhite; color: black; width: 30px" value="${sessionScope.secondLocale}">
</form>

<a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
        key="link.name.back_to_login_page"/></a>
<nav class="menu">
    <ul>
        <li>
            <a href="#"><fmt:message key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.questions"/></a>
        </li>
    </ul>
</nav>
<h1><fmt:message key="title.how_to_do_order"/></h1>
<p><fmt:message key="p.choose_link"/><fmt:message key="p.enter_name"/></p>
</body>
</html>
