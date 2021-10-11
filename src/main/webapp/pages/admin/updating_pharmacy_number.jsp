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
    <title><fmt:message key="title.update_pharmacy_number"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_pharmacy_number"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacy_number" method="post">
    <c:if test="${requestScope.updatingPharmacyNumberError != null}">
        <div class="error"><fmt:message key="error.pharmacy_number"/></div>
    </c:if>
    <input type="text" name="newNumber" placeholder="<fmt:message key="placeholder.new_number"/>"
           class="change_data"/><br>
    <input type="submit" value="<fmt:message key="button.update_number"/>" class="button"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_pharmacies" class="common_link"><fmt:message
        key="header.all_pharmacies"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

</body>
</html>
