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
    <title><fmt:message key="title.products_by_inpn"/></title>
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
<h1><fmt:message key="title.search_products_by_inpn"/></h1>
<a href="${pageContext.request.contextPath}/controller?command=basket_page" class="common_link"><fmt:message
        key="link.basket"/></a><br>
<form action="${pageContext.request.contextPath}/controller?command=search_products_by_non_proprietary_name"
      method="post">
    <input type="text" name="nonProprietaryNameForSearchProducts"
           placeholder="<fmt:message key="placeholder.enter_non_proprietary_name_here"/>"><br>
    <input type="submit" value="<fmt:message key="button.search"/>" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.listProductsByNonProprietaryName.size()>0}">
        <label><fmt:message key="msg.if_you_want_to_add_position_to_order_click_on_name"/></label>
        <table border="3">
            <thead>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_dose"/></th>
            <th><fmt:message key="column.table_product_non_proprietary_name"/></th>
            <th><fmt:message key="column.table_plant"/></th>
            <th><fmt:message key="column.table_group"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_recipe"/></th>
            <th><fmt:message key="column.table_about"/></th>
            </thead>

            <c:forEach items="${sessionScope.listProductsByNonProprietaryName}" var="product">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=addition_product_to_order&product_id=${product.productId}&current_url=${pageContext.request.requestURL}">${product.name}</a>
                    </td>
                    <td>
                            ${product.dose}

                    </td>
                    <td>
                            ${product.nonProprietaryName}

                    </td>
                    <td>
                            ${product.plant}

                    </td>
                    <td>
                            ${product.group}

                    </td>
                    <td>
                            ${product.price}

                    </td>
                    <td>
                            ${product.recipe}

                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=about_product&product_id=${product.productId}">Read
                            more</a>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_customer" class="common_link"><fmt:message
        key="link.customer_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message key="link.logout"/></a>
</body>
</html>
