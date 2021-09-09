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
    <title>All products</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}&current_url=${pageContext.request.requestURL}">${sessionScope.secondLocale}</a><br>
<h1 style="text-align: center">List all products</h1>
<h2 style="text-align: left">Add new product in form</h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_product" method="post">
    <input type="text" name="name" placeholder="name"/><br>
    <input type="text" name="price" placeholder="price"/><br>
    <input type="text" name="group" placeholder="group"/><br>
    <select name="recipe">
        <option>Yes</option>
        <option>No</option>
    </select><br>
    <input type="text" name="instruction" placeholder="instruction"/><br>
    <input type="submit" value="Input product">
</form>
<c:choose>
    <c:when test="${sessionScope.allProducts.size()>0}">

        <table border="3">
            <thead>
            <th>Id</th>
            <th>Name</th>
            <th>Group</th>
            <th>Price</th>
            <th>Recipe</th>
            <th>Instruction</th>
            </thead>

            <c:forEach items="${sessionScope.allProducts}" var="product">

                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=addition_picture_page&productId=${product.productId}">${product.productId}</a>
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
                            ${product.instruction}

                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/controller?command=main_admin"><fmt:message key="link.main"/></a>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="link.logout"/></a>
</body>
</html>