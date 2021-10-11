<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.all_processing_orders"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.all_processing_orders"/></h1>

<c:choose>
    <c:when test="${sessionScope.listOrders.size()>0}">

        <p><fmt:message key="msg.click_on_order_id"/></p>

        <table border="3">
            <thead>
            <th><fmt:message key="column.table.order_id"/></th>
            <th><fmt:message key="column.table.start"/></th>
            <th><fmt:message key="column.table.end"/></th>
            <th><fmt:message key="column.table.pharmacy_number"/></th>
            <th><fmt:message key="column.table.status"/></th>
            </thead>

            <c:forEach items="${sessionScope.listOrders}" var="order">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=basket_for_order&order_id=${order.orderId}">${order.orderId}</a>
                    </td>
                    <td>
                            ${order.dataStarting}
                    </td>
                    <td>
                            ${order.dataEnding}
                    </td>
                    <td>
                            ${order.pharmacy.number}
                    </td>
                    <td>
                            ${order.statusOrder}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=all_orders_in_pharmacy_page&pharmacy_id=${sessionScope.pharmacyId}"
   class="common_link"><fmt:message key="link.to_menu"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_pharmacist.jsp"/>

</body>
</html>
