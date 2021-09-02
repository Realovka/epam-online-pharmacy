<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent_En_en" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.inactive_pharmacists"/></title>
</head>
<body>
<h1 style="text-align: center"><fmt:message key="header.list_inactive_pharmacists"/></h1>
<c:choose>
<c:when test="${sessionScope.inactivePharmacists.size()>0}">
    <label><fmt:message key="msg.activation_pharmacist"/></label>
    <table border="3">
    <thead>
    <th><fmt:message key="column.table.id"/></th>
    <th><fmt:message key="column.table.first_name"/></th>
    <th><fmt:message key="column.table.last_name"/></th>
    <th><fmt:message key="column.table.status"/></th>
    </thead>

    <c:forEach items="${sessionScope.inactivePharmacists}" var="pharmacist">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=activation_pharmacist&id=${pharmacist.userId}">${pharmacist.userId}</a>
                    </td>
                    <td>
                            ${pharmacist.firstName}
                    </td>
                    <td>
                            ${pharmacist.lastName}
                    </td>
                    <td>
                            ${pharmacist.status}
                    </td>
                </tr>
    </c:forEach>
    </table>
</c:when>
<c:otherwise>
    <label><fmt:message key="msg.no_inactive_pharmacists"/></label><br>
</c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
