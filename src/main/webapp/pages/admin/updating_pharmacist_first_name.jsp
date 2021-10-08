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
    <title><fmt:message key="title.update_pharmacist_first_name"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.update_pharmacist_first_name"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_first_name" method="post">
    <c:if test="${requestScope.updatingPharmacistFirstNameError != null}">
        <div class="error"><fmt:message key="error.update_user_first_name"/></div>
    </c:if>
    <input type="text" name="newFirstName" placeholder="<fmt:message key="placeholder.new_first_name"/>"
           class="change_data"/><br>
    <input type="submit" value="<fmt:message key="button.update_first_name"/>" class="button"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=all_pharmacists" class="common_link"><fmt:message
        key="link.all_pharmacists"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

<ftg:footer/>
</body>
</html>
