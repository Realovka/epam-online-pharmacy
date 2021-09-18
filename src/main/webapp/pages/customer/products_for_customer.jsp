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
<h1 style="text-align: center">List all products in pharmacies</h1>
<a href="${pageContext.request.contextPath}/controller?command=basket_page">BASKET</a><br>
<label>If you want to add position to order, click on id</label>
<c:choose>
    <c:when test="${sessionScope.allProducts.size()>0}">

        <table border="3">
            <thead>
            <th>Id</th>
            <th>Name</th>
            <th>Group</th>
            <th>Price</th>
            <th>Recipe</th>
            <th>About</th>
            </thead>

            <c:forEach items="${sessionScope.allProducts}" var="product">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=addition_product_to_order&productId=${product.productId}">${product.productId}</a>
                    </td>
                    <td>
                            ${product.name}

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
                        <a href="${pageContext.request.contextPath}/controller?command=about_product&productId=${product.productId}">Read more</a>

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
