<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session" />
<fmt:setBundle basename="${sessionScope.currentBundle}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title>About product</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
</form>
<div>${sessionScope.product.name}</div><br>
<div>${sessionScope.product.group}</div><br>
<div>${sessionScope.product.price}</div><br>
<div>${sessionScope.product.recipe}</div><br>
<img id="account_image" class="rounded-circle account-img" src="<c:url value="${pageContext.request.contextPath}${sessionScope.pathToFile}"/>" alt="no img"/><br>
<div>${sessionScope.product.instruction}</div>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message key="link.logout"/></a>
</body>
</html>
