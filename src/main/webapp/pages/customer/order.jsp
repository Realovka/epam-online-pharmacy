<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.order"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<nav class="menu">
    <ul>
        <li>
            <a href="#"><fmt:message key="link.name.how_to_do_order"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="link.name.questions"/></a>
        </li>
    </ul>
</nav>
<h1><fmt:message key="title.order"/></h1>
<label><fmt:message key="msg.if_you_want_update_products_in_basket"/></label>
<a href="${pageContext.request.contextPath}/controller?command=basket_page"><fmt:message key="link.click_here"/></a><br>
<label><fmt:message key="msg.if_you_want_choose_other_pharmacy"/></label>
<a href="${pageContext.request.contextPath}/controller?command=choose_pharmacy"><fmt:message key="link.click_here"/></a><br><br>
<label><fmt:message key="msg.the_pharmacy_where_your_order_will_be_delivered"/></label>
<table border="3">
    <thead>
    <th><fmt:message key="column.table.number"/></th>
    <th><fmt:message key="column.table.city"/></th>
    <th><fmt:message key="column.table.street"/></th>
    <th><fmt:message key="column.table.house"/></th>
    <th><fmt:message key="column.table.block"/></th>
    </thead>

    <tr>
        <td>
            ${sessionScope.pharmacyOrder.number}
        </td>
        <td>
            ${sessionScope.pharmacyOrder.city}
        </td>
        <td>
            ${sessionScope.pharmacyOrder.street}
        </td>
        <td>
            ${sessionScope.pharmacyOrder.house}
        </td>
        <td>
            ${sessionScope.pharmacyOrder.block}
        </td>
    </tr>

</table>
<br>
<label><fmt:message key="msg.items_are_in_your_order"/></label><br>
<c:choose>
    <c:when test="${sessionScope.listProductsInBasket.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_dose"/></th>
            <th><fmt:message key="column.table_plant"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_quantity"/></th>
            </thead>

            <c:forEach items="${sessionScope.listProductsInBasket}" var="product">

            <tr>
                <td>
                        ${product.key.name}
                </td>
                <td>
                        ${product.key.dose}
                </td>
                <td>
                        ${product.key.plant}
                </td>
                <td>
                        ${product.key.price}
                </td>
                <td>
                        ${product.value}
                </td>
                </c:forEach>
        </table>
    </c:when>
</c:choose><br>
<label><fmt:message key="msg.you_will_not_able_to_edit_the_order_after_submitting"/></label><br>
<form action="${pageContext.request.contextPath}/controller?command=send_order" method="post">
    <input type="submit" value="<fmt:message key="button.send_order"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_customer" class="common_link"><fmt:message
        key="link.customer_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
<ftg:footer/>
</body>
</html>
