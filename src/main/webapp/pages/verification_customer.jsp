<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="title.verification.customer"/></title>
</head>
<body>

<jsp:include page="static_part/header.jsp"/>

<jsp:include page="static_part/menu_for_customer.jsp"/>

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
<ftg:footer/>
</body>
</html>
