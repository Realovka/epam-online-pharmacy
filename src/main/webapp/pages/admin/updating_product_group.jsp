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
    <title><fmt:message key="title.update_product_group"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
</form>

<h1 style="text-align: center"><fmt:message key="title.update_product_group"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_product_group" method="post">
    <c:if test="${requestScope.productGroupError != null}">
        <div class="error"><fmt:message key="error.product_group"/> </div>
    </c:if>
    <input type="text" name="newGroup" placeholder="<fmt:message key="placeholder.new_group"/>" size="35px"/><br>
    <input type="submit" value="<fmt:message key="button.update_group"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message key="link.logout"/></a>
</body>
</html>
