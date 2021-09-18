<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session" />
<fmt:setBundle basename="${sessionScope.currentBundle}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title>Order</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
    <input type="submit" style="background-color: dimgrey; color: white; width: 30px" value="${sessionScope.secondLocale}">
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
<label>If you want update products in basket</label>
<a href="${pageContext.request.contextPath}/controller?command=basket_page">click here</a><br>
<label>If you want choose other pharmacy</label>
<a href="${pageContext.request.contextPath}/controller?command=choose_pharmacy">click here</a><br>
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
                    ${sessionScope.pharmacy.number}
            </td>
            <td>
                    ${sessionScope.pharmacy.city}
            </td>
            <td>
                    ${sessionScope.pharmacy.street}
            </td>
            <td>
                    ${sessionScope.pharmacy.house}
            </td>
            <td>
                    ${sessionScope.pharmacy.block}
            </td>
        </tr>

</table>
<c:choose>
    <c:when test="${sessionScope.products.size()>0}">

        <table border="3">
            <thead>
            <th>Name</th>
            <th>Quantity</th>
            </thead>

            <c:forEach items="${sessionScope.products}" var="product">

            <tr>
                <td>
                        ${product.key.name}
                </td>
                <td>
                        ${product.value}
                </td>
                </c:forEach>
        </table>
    </c:when>
</c:choose>
<form action="${pageContext.request.contextPath}/controller?command=send_order" method="post">
    <input type="submit" value="<fmt:message key="button.send_order"/>"/>
</form>
<a href="${pageContext.request.contextPath}/controller?command=main_customer"><fmt:message key="link.customer_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
