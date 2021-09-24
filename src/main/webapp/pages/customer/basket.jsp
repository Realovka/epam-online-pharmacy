<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.customer_main"/></title>
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
<a href="${pageContext.request.contextPath}/controller?command=choose_pharmacy">Choose pharmacy</a><br>
<a href="${pageContext.request.contextPath}/controller?command=order">Go to order page</a>
<c:choose>
    <c:when test="${sessionScope.listProductsInBasket.size()>0}">

        <table border="3">
            <thead>
            <th>Name</th>
            <th>Dose</th>
            <th>Plant</th>
            <th>Price</th>
            <th>Quantity</th>
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
                            <form action="${pageContext.request.contextPath}/controller?command=updating_product_quantity&product_id=${product.key.productId}" method="post">
                                <input type="text" name="quantity" placeholder="Update quantity here"/>
                            </form>

                </td>
                </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_customer"><fmt:message key="link.customer_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>