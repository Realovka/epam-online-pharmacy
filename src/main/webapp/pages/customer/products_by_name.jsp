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
    <title><fmt:message key="title.products_by_name"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/menu_for_customer.jsp"/>

<h1><fmt:message key="title.search_products_by_name"/></h1>

<a href="${pageContext.request.contextPath}/controller?command=basket_page" class="common_link"><fmt:message
        key="link.basket"/></a><br>
<form action="${pageContext.request.contextPath}/controller?command=search_products_by_name" method="post">
    <input type="text" name="nameForSearchProducts"
           placeholder="<fmt:message key="placeholder.enter_product_name_here"/>"><br>
    <input type="submit" value="<fmt:message key="button.search"/>" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.listProductsByName.size()>0}">
        <p><fmt:message key="msg.if_you_want_to_add_position_to_order_click_on_name"/></p>
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

            <c:forEach items="${sessionScope.listProductsByName}" var="product">

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
                        <a href="${pageContext.request.contextPath}/controller?command=about_product&product_id=${product.productId}"><fmt:message
                                key="link.read_more"/></a>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>

<c:if test="${requestScope.noSuchProductsInSearch != null}">
    <fmt:message key="msg.no_results_were_found_for_your_search"/>
</c:if><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_customer.jsp"/>

</body>
</html>
