<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
</form>

<img id="account_image" class="rounded-circle account-img" src="<c:url value="${pageContext.request.contextPath}${sessionScope.pathToFile}"/>" alt="no img"/>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" style="color: #800000"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" style="color: #800000"><fmt:message key="link.logout"/></a>
</body>
</html>
