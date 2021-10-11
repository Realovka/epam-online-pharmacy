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
    <title><fmt:message key="title.update_pharmacist_last_name"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_pharmacist_last_name"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_last_name" method="post">
    <c:if test="${requestScope.updatingPharmacistLastNameError != null}">
        <div class="error"><fmt:message key="error.update_user_last_name"/></div>
    </c:if>
    <input type="text" name="newLastName" placeholder="<fmt:message key="placeholder.new_last_name"/>" minlength="1"
           maxlength="45" class="change_data"/><br>
    <input type="submit" value="<fmt:message key="button.update_last_name"/>" class="button"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_pharmacists" class="common_link"><fmt:message
        key="link.all_pharmacists"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

</body>
</html>