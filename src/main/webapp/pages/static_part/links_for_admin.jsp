<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<div>
    <a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
            key="link.admin_main"/></a><br>
    <a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
            key="link.logout"/></a>
</div>
