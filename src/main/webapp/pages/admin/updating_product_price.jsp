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
    <title><fmt:message key="title.update_product_price"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_product_price"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_product_price" method="post">
    <c:if test="${requestScope.productPriceError != null}">
        <div class="error"><fmt:message key="error.product_price"/></div>
    </c:if>
    <input type="text" name="newPrice" placeholder="<fmt:message key="placeholder.new_price"/>"/><br>
    <input type="submit" value="<fmt:message key="button.update_price"/>" class="button_product_update"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
        key="link.all_products"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

</body>
</html>
