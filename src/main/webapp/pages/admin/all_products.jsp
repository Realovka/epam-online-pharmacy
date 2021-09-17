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
    <title><fmt:message key="title.all_products"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>
<h1 style="text-align: center"><fmt:message key="header.list_all_products"/></h1>
<h2 style="text-align: left"><fmt:message key="header.add_new_product_in_form"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_product" method="post">
    <c:if test="${requestScope.productNameError!=null}">
        <div><fmt:message key="error.product_name"/></div>
    </c:if>
    <c:if test="${requestScope.productGroupError!=null}">
        <div><fmt:message key="error.product_group"/></div>
    </c:if>
    <c:if test="${requestScope.productPriceError!=null}">
        <div><fmt:message key="error.product_price"/></div>
    </c:if>
    <c:if test="${requestScope.productInstructionError!=null}">
        <div><fmt:message key="error.product_instruction"/></div>
    </c:if>
    <label class="field"><fmt:message key="label.name"/></label><br>
    <input type="text" name="name" value="${requestScope.mapData.get("name")}"
           placeholder="<fmt:message key="placeholder.product_name"/>"/><br>
    <label class="field"><fmt:message key="label.group"/></><br>
    <input type="text" name="group" value="${requestScope.mapData.get("group")}"
           placeholder="<fmt:message key="placeholder.product_group"/>"/><br>
    <label class="field"><fmt:message key="label.price"/></label><br>
    <input type="text" name="price" value="${requestScope.mapData.get("price")}"
           placeholder="<fmt:message key="placeholder.product_price"/>"/><br>
    <label class="field"><fmt:message key="label.recipe"/></label><br>
    <select name="recipe">
        <option><fmt:message key="select.recipe_yes"/></option>
        <option><fmt:message key="select.recipe_no"/></option>
    </select><br><br>
    <label class="field"><fmt:message key="label.instruction"/></label><br>
    <textarea name="instruction"></textarea><br><br>
    <input type="submit" value="<fmt:message key="button.input_product"/>">
</form>
<c:choose>
    <c:when test="${sessionScope.allProducts.size()>0}">

        <table border="3">
            <thead>
            <th><fmt:message key="column.table.id"/></th>
            <th><fmt:message key="column.table_name"/></th>
            <th><fmt:message key="column.table_group"/></th>
            <th><fmt:message key="column.table_price"/></th>
            <th><fmt:message key="column.table_recipe"/></th>
            <th><fmt:message key="column.table_instruction"/></th>
            </thead>

                <c:forEach items="${sessionScope.allProducts}" var="product">

                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=addition_picture_page&productId=${product.productId}">${product.productId}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_product_name_page&productId=${product.productId}">${product.name}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_product_group_page&productId=${product.productId}">${product.group}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_product_price_page&productId=${product.productId}">${product.price}</a>

                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_product_recipe_page&productId=${product.productId}">${product.recipe}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=updating_product_instruction_page&productId=${product.productId}">${product.instruction}</a>
                        </td>
                    </tr>
                </c:forEach>

        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_admin" style="color: #000000"><fmt:message key="link.admin_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout" style="color: #000000"><fmt:message key="link.logout"/></a>
</body>
</html>