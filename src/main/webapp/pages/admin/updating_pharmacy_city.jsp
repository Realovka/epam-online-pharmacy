<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session" />
<fmt:setBundle basename="${sessionScope.currentBundle}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.update_pharmacy_city"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>
<h1 style="text-align: center"><fmt:message key="title.update_pharmacy_city"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacy_city" method="post">
    <c:if test="${requestScope.updatingPharmacyCityError != null}">
        <div class="error"><fmt:message key="error.pharmacy_city"/> </div>
    </c:if>
    <input type="text" name="newCity" placeholder="<fmt:message key="placeholder.new_city"/>"/><br>
    <input type="submit" value="<fmt:message key="button.update_city"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>