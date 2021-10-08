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
    <title><fmt:message key="title.basket"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/menu_for_customer.jsp"/>

<a href="${pageContext.request.contextPath}/controller?command=choose_pharmacy"><fmt:message
        key="link.choose_pharmacy"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=order"><fmt:message key="link.go_to_order_page"/></a>

<h1><fmt:message key="title.basket"/></h1>

<c:if test="${requestScope.productQuantityError != null}">
    <div class="error"><fmt:message key="error.product_quantity"/></div>
</c:if>

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
                    <form action="${pageContext.request.contextPath}/controller?command=updating_product_quantity&product_id=${product.key.productId}"
                          method="post">
                        <input type="text" name="quantity"
                               placeholder="<fmt:message key="placeholder.update_quantity_here"/>"/>
                    </form>

                </td>
                </c:forEach>
        </table>
    </c:when>
</c:choose>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_customer.jsp"/>
<ftg:footer/>
</body>
</html>