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
    <title><fmt:message key="title.search_pharmacies"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
<h2><fmt:message key="title.search_pharmacies"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=search_pharmacies_by_city" method="post">
    <input type="text" name="cityForSearchPharmacies"
           placeholder="<fmt:message key="placeholder.enter_city_for_search_pharmacies_here"/>"><br>
    <input type="submit" value="<fmt:message key="button.search"/>" class="button">
</form>



<c:choose>
    <c:when test="${sessionScope.listPharmaciesByCity.size()>0}">
        <p><fmt:message key="msg.click_on_the_pharmacy_number"/></p>
        <table border="3">
            <thead>
            <th><fmt:message key="column.table.number"/></th>
            <th><fmt:message key="column.table.city"/></th>
            <th><fmt:message key="column.table.street"/></th>
            <th><fmt:message key="column.table.house"/></th>
            <th><fmt:message key="column.table.block"/></th>
            </thead>

            <c:forEach items="${sessionScope.listPharmaciesByCity}" var="pharmacy">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=all_orders_in_pharmacy_page&pharmacy_id=${pharmacy.pharmacyId}">${pharmacy.number}</a>
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
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
