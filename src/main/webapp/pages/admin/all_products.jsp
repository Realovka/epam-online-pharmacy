<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="properties.pagecontent_En_en" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title>All products</title>
</head>
<body>
<h1 style="text-align: center">List all products</h1>
<h2 style="text-align: left">Add new product in form</h2>
<form action="/addProduct" method="post">
    <input type="text" name="name" placeholder="name"/>
    <input type="text" name="price" placeholder="price"/>
    <input type="text" name="group" placeholder="group"/>
    <input type="text" name="recipe" placeholder="recipe"/>
    <input type="file" name="picture" placeholder="picture"/>
    <input type="text" name="instruction" placeholder="instruction"/>
    <input type="submit" value="Input product">
</form>
<c:choose>
    <c:when test="${sessionScope.allProducts.size()>0}">

        <table border="3">
            <thead>
            <th>Id</th>
            <th>Name</th>
            <th>Group</th>
            <th>Recipe</th>
            <th>Picture</th>
            <th>Instruction</th>
            </thead>

            <c:forEach items="${sessionScope.allProducts}" var="product">

                <tr>
                    <td>
                            ${product.}
                    </td>
                    <td>
                            ${}
                    </td>
                    <td>
                            ${pharmacy.city}
                    </td>
                    <td>
                            ${pharmacy.street}
                    </td>
                    <td>
                            ${pharmacy.house}
                    </td>
                    <td>
                            ${pharmacy.block}
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