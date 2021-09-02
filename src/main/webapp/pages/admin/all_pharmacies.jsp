<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent_En_en" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="header.all_pharmacies"/></title>
</head>
<body>
<h1 style="text-align: center"><fmt:message key="header.all_pharmacies"/></h1>
<h2 style="text-align: left"><fmt:message key="header.add_pharmacy"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_pharmacy" method="post">
    <div>${requestScope.numberError}</div><br>
    <div>${requestScope.stringParametersError}</div><br>
    <input type="text" name="number" placeholder="<fmt:message key="placeholder.number"/>"/><br>
    <input type="text" name="city" placeholder="<fmt:message key="placeholder.city"/>"/><br>
    <input type="text" name="street" placeholder="<fmt:message key="placeholder.street"/>"/><br>
    <input type="text" name="house" placeholder="<fmt:message key="placeholder.house"/>"/><br>
    <div>${requestScope.blockError}</div><br>
    <input type="text" name="block" placeholder="<fmt:message key="placeholder.block"/>"/><br>
    <input type="submit" value="<fmt:message key="button.input_pharmacy"/>">
</form>
<c:choose>
    <c:when test="${sessionScope.allPharmacies.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table.id"/></th>
            <th><fmt:message key="column.table.number"/></th>
            <th><fmt:message key="column.table.city"/></th>
            <th><fmt:message key="column.table.street"/></th>
            <th><fmt:message key="column.table.house"/></th>
            <th><fmt:message key="column.table.block"/></th>
            </thead>

            <c:forEach items="${sessionScope.allPharmacies}" var="pharmacy">

                <tr>
                    <td>
                            ${pharmacy.pharmacyId}
                    </td>
                    <td>
                            ${pharmacy.number}
                    </td>
                    <td>
                            ${pharmacy.city}
                    </td>
                    <td>
                            ${pharmacy.street}
                    </td>
                    <td>
                            ${pharmacy.house}
                    </td>
                    <td>
                            ${pharmacy.block}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
