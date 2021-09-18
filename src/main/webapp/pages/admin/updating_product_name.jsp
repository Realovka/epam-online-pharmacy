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
    <title><fmt:message key="title.update_product_name"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
</form>

<h1 style="text-align: center"><fmt:message key="title.update_product_name"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_product_name" method="post">
    <c:if test="${requestScope.productNameError != null}">
        <div class="error"><fmt:message key="error.product_name"/> </div>
    </c:if>
    <input type="text" name="newName" placeholder="<fmt:message key="placeholder.new_name"/>" size="35px"/><br>
    <input type="submit" value="<fmt:message key="button.update_name"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" style="color: #800000"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" style="color: #800000"><fmt:message key="link.logout"/></a>
</body>
</html>
