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
    <title><fmt:message key="title.update_pharmacist_telephone"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_pharmacist_telephone"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_telephone" method="post">
    <c:if test="${requestScope.updatingPharmacistTelephoneError != null}">
        <div class="error"><fmt:message key="error.update_user_telephone"/></div>
    </c:if>
    <input type="tel" name="newTelephone" placeholder="<fmt:message key="placeholder.new_telephone"/>"
           pattern="^\+[0-9]{12}$" class="change_data"/><br>
    <input type="submit" value="<fmt:message key="button.update_telephone"/>" class="button"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_pharmacists" class="common_link"><fmt:message
        key="link.all_pharmacists"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

</body>
</html>