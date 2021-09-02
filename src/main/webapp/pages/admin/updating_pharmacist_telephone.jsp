<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent_En_en" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.update_pharmacist_telephone"/></title>
</head>
<body>
<h1 style="text-align: center"><fmt:message key="title.update_pharmacist_telephone"/></h1>
<form action="${pageContext.request.contextPath}/controller?command=updating_pharmacist_telephone" method="post">
    <p>${requestScope.updatingPharmacistTelephoneError}</p>
    <input type="tel" name="newTelephone" placeholder="<fmt:message key="placeholder.new_telephone"/>"/><br>
    <input type="submit" value="<fmt:message key="button.update_telephone"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>