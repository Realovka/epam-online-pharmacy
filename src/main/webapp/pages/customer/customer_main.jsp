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
    <title><fmt:message key="title.customer_main"/></title>
</head>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/menu_for_customer.jsp"/>

<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>

<c:if test="${requestScope.needChooseProductsError != null}">
    <div class="error"><fmt:message key="error.need_choose_products"/></div>
</c:if>

<ul>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=search_products_by_name_page"
           class="common_link"><fmt:message
                key="link.search_by_product_name"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=search_products_by_non_proprietary_name_page"
           class="common_link"><fmt:message
                key="link.search_by_product_non_proprietary_name"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
                key="link.logout"/></a>
    </li>
</ul>
</body>
</html>
