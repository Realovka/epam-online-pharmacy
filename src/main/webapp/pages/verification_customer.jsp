<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/main.css">
    <title><fmt:message key="title.verification.customer"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>

<p>
    <fmt:message key="msg.finish.registration"/>
</p>
<form action="${pageContext.request.contextPath}/controller?command=verification_customer" method="post">
    <p>
        <c:if test="${requestScope.codeVerificationError != null}">
    <div class="error"><fmt:message key="error.verification_customer"/></div>
    </c:if>
    </p>
    <input type="text" name="code" placeholder="<fmt:message key="placeholder.code"/>"/><br>
    <input type="submit" value="<fmt:message key="button.send"/>"/><br>
</form>
<a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
        key="link.name.back_to_login_page"/></a>
</body>
</html>
