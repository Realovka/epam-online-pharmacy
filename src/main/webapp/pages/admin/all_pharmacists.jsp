<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="by.epam.onlinepharmacy.entity.Status" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title><fmt:message key="header.all_pharmacists"/></title>
</head>
<body>
<h1 style="text-align: center"><fmt:message key="header.list_all_pharmacists"/></h1>
<h2 style="text-align: left"><fmt:message key="header.pharmacist_waiting_verification"/></h2>
<label><fmt:message key="msg.verify.pharmacists"/></label>

<c:if test="${sessionScope.allPharmacists.size()>0}">
    <table border="3">
        <thead>
        <th><fmt:message key="column.table.id"/></th>
        <th><fmt:message key="column.table.first_name"/></th>
        <th><fmt:message key="column.table.last_name"/></th>
        <th><fmt:message key="column.table.status"/></th>
        </thead>

        <c:forEach items="${sessionScope.allPharmacists}" var="pharmacist">
            <c:choose>
                <c:when test="${pharmacist.status.equals(Status.IN_REGISTR)}">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=verification_pharmacist&id=${pharmacist.userId}">${pharmacist.userId}</a>
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
                </c:when>
            </c:choose>
        </c:forEach>
    </table>

    <h2 style="text-align: left"><fmt:message key="header.active_pharmacists"/></h2>
    <label><fmt:message key="msg.click_on_parameter"/></label>
    <table border="3">
        <thead>
        <th><fmt:message key="column.table.id"/></th>
        <th><fmt:message key="column.table.login"/></th>
        <th><fmt:message key="column.table.first_name"/></th>
        <th><fmt:message key="column.table.last_name"/></th>
        <th><fmt:message key="column.table.telephone"/></th>
        <th><fmt:message key="column.table.email"/></th>
        <th><fmt:message key="column.table.status"/></th>
        </thead>

        <c:forEach items="${sessionScope.allPharmacists}" var="pharmacist">
            <c:choose>
                <c:when test="${pharmacist.status.equals(Status.ACTIVE)}">
                    <tr>
                        <td>
                                ${pharmacist.userId}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacist_login_page&id=${pharmacist.userId}">${pharmacist.login}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacist_first_name_page&id=${pharmacist.userId}">${pharmacist.firstName}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacist_last_name_page&id=${pharmacist.userId}">${pharmacist.lastName}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacist_telephone_page&id=${pharmacist.userId}">${pharmacist.telephone}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacist_email_page&id=${pharmacist.userId}">${pharmacist.email}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=inactivation_pharmacist&id=${pharmacist.userId}">${pharmacist.status}</a>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</c:if>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
