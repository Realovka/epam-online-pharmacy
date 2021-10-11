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
    <title><fmt:message key="title.update_product_group"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_product_group"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_product_group" method="post">
    <c:if test="${requestScope.productGroupError != null}">
        <div class="error"><fmt:message key="error.product_group"/></div>
    </c:if>
    <input type="text" name="newGroup" placeholder="<fmt:message key="placeholder.new_group"/>"
           minlength="1" maxlength="70"/><br>
    <input type="submit" value="<fmt:message key="button.update_group"/>" class="button_product_update"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
        key="link.all_products"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

</body>
</html>
