<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/reg.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="page.registration"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>
<a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
        key="link.name.back_to_login_page"/></a>
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
<div class="reg">
    <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <h1><fmt:message key="page.registration"/></h1><br>
        <c:if test="${requestScope.dataRegistrationError!=null}">
            <div><fmt:message key="error.registration_user_data"/></div>
        </c:if>
        <c:if test="${requestScope.registrationError!=null}">
            <div><fmt:message key="error.registration_user"/></div>
        </c:if>
        <input type="text" name="login" value="${requestScope.mapData.get("login")}"
               placeholder="<fmt:message key="placeholder.name.login"/>" size="35px"/><br>
        <input type="password" name="password" placeholder="<fmt:message key="placeholder.name.password"/>"
               size="35px"/><br>
        <input type="text" name="firstName" value="${requestScope.mapData.get("firstName")}"
               placeholder="<fmt:message key="placeholder.name.firstName"/>" size="35px"/><br>
        <input type="text" name="lastName" value="${requestScope.mapData.get("lastName")}"
               placeholder="<fmt:message key="placeholder.name.lastName"/>" size="35px"/><br>
        <input type="email" name="email" value="${requestScope.mapData.get("email")}"
               placeholder="<fmt:message key="placeholder.name.email"/>" size="35px"/><br>
        <input type="tel" name="telephone" value="${requestScope.mapData.get("telephone")}"
               placeholder="<fmt:message key="placeholder.name.telephone"/>" size="35px"/><br>
        <select name="role">
            <option><fmt:message key="role.customer"/></option>
            <option><fmt:message key="role.pharmacist"/></option>
        </select><br><br>
        <input type="submit" value="<fmt:message key="button.registration"/>">
    </form>
</div>
</body>
</html>
