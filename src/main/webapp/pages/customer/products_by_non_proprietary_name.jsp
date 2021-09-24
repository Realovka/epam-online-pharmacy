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
    <title>Products</title>
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
<h1 style="text-align: center">Search products by non proprietary name</h1>
<a href="${pageContext.request.contextPath}/controller?command=basket_page">BASKET</a><br>
<form action="${pageContext.request.contextPath}/controller?command=search_products_by_non_proprietary_name" method="post">
    <input type="text" name="nonProprietaryNameForSearchProducts" placeholder="enter non proprietary name here"><br>
    <input type="submit" value="Search" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.listProductsByNonProprietaryName.size()>0}">
        <label>If you want to add position to order, click on name</label>
        <table border="3">
            <thead>
            <th>Name</th>
            <th>Dose</th>
            <th>INPN</th>
            <th>Plant</th>
            <th>Group</th>
            <th>Price</th>
            <th>Recipe</th>
            <th>About</th>
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
                        <a href="${pageContext.request.contextPath}/controller?command=about_product&product_id=${product.productId}">Read more</a>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_customer"><fmt:message key="link.customer_main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>
