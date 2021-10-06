<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>
<%@ page import="by.epam.onlinepharmacy.entity.StatusOrder" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.processing_order"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="title.processing_order"/></h1>

<c:choose>
    <c:when test="${sessionScope.basket.size()>0}">
        <label><fmt:message key="label.customer_name"/></label>
        <div>${sessionScope.basket.get(0).user.firstName} ${sessionScope.basket.get(0).user.lastName}</div>
        <br>
        <label><fmt:message key="label.customer_telephone"/></label>
        <div> ${sessionScope.basket.get(0).user.telephone}</div>
        <br>
        <label><fmt:message key="label.order_id"/></label>
        <div>${sessionScope.order.orderId}</div>
        <br>
        <label><fmt:message key="label.data_starting_order"/></label>
        <div> ${sessionScope.order.dataStarting}</div>
        <br>
        <label><fmt:message key="label.data_ending_order"/></label>
        <div>${sessionScope.order.dataEnding}</div>
        <br>
        <label><fmt:message key="label.status_order"/></label>
        <div>${sessionScope.order.statusOrder}</div>
        <br>

        <table border="3">
            <thead>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_dose"/></th>
            <th><fmt:message key="column.table_plant"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_quantity"/></th>
            </thead>

            <c:forEach items="${sessionScope.basket}" var="basket">

                <tr>
                    <td>
                            ${basket.product.name}
                    </td>
                    <td>
                            ${basket.product.dose}
                    </td>
                    <td>
                            ${basket.product.plant}
                    </td>
                    <td>
                            ${basket.product.price}
                    </td>
                    <td>
                            ${basket.quantity}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose><br>
<c:if test="${sessionScope.order.statusOrder.equals(StatusOrder.PROCESSING)}">
    <form action="${pageContext.request.contextPath}/controller?command=updating_order_status_to_prepared"
          method="post">
        <input type="hidden" name="order_id" value="${sessionScope.order.orderId}"/>
        <input type="hidden" name="order_status_id" value="2"/>
        <input type="submit" value="<fmt:message key="button.prepare"/>" class="button"/>
    </form>
</c:if>
<br>

<c:if test="${sessionScope.order.statusOrder.equals(StatusOrder.PROCESSING)}">
    <form action="${pageContext.request.contextPath}/controller?command=updating_order_status_to_deleted"
          method="post">
        <input type="hidden" name="order_id" value="${sessionScope.order.orderId}"/>
        <input type="hidden" name="order_status_id" value="4"/>
        <input type="submit" value="<fmt:message key="button.delete"/>" class="button"/>
    </form>
</c:if>
<br>


<a href="${pageContext.request.contextPath}/controller?command=all_processing_orders&pharmacy_id=${sessionScope.order.pharmacy.pharmacyId}"
   class="common_link">
    <fmt:message key="link.to_list_processing_orders"/> </a><br>
<a href="${pageContext.request.contextPath}/controller?command=all_orders_in_pharmacy_page&pharmacy_id=${sessionScope.pharmacyId}"
   class="common_link"> <fmt:message key="link.to_menu"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=main_pharmacist" class="common_link"><fmt:message
        key="link.pharmacist_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
</body>
</html>
