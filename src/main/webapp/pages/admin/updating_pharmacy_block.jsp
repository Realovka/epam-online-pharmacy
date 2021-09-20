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
    <title><fmt:message key="title.update_pharmacy_block"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
</form>

<h1 style="text-align: center"><fmt:message key="title.update_pharmacy_block"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacy_block" method="post">
    <c:if test="${requestScope.updatingPharmacyBlockError != null}">
        <div class="error"><fmt:message key="error.pharmacy_block_error"/> </div>
    </c:if>
    <input type="text" name="newBlock" placeholder="<fmt:message key="placeholder.new_block"/>"/><br>
    <input type="submit" value="<fmt:message key="button.update_block"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message key="link.logout"/></a>
</body>
</html>
