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
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.menu"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.menu"/></h1>
<a href="${pageContext.request.contextPath}/controller?command=all_orders_in_needed_status&order_status_id=1" class="common_link"><fmt:message
        key="link.all_processing_orders"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=all_orders_in_needed_status&order_status_id=2" class="common_link"><fmt:message
        key="link.all_prepared_orders"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_pharmacist.jsp"/>

</body>
</html>
