<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/common.css">
    <title>Deleted pharmacists</title>
</head>
<body>
<h1 style="text-align: center">List inactive pharmacists</h1>
<c:choose>
<c:when test="${sessionScope.inactivePharmacists.size()>0}">
    <label>Click on id if you want activate inactive pharmacist</label>
    <table border="3">
    <thead>
    <th>Id</th>
    <th>First name</th>
    <th>Last name</th>
    <th>Status</th>
    </thead>

    <c:forEach items="${sessionScope.inactivePharmacists}" var="pharmacist">
                <tr>
                    <td>
                        <a href="/activationPharmacist?id=${pharmacist.userId}">${pharmacist.userId}</a>
                    </td>
                    <td>
                            ${pharmacist.firstName}
                    </td>
                    <td>
                            ${pharmacist.lastName}
                    </td>
                    <td>
                            ${pharmacist.status}
                    </td>
                </tr>
    </c:forEach>
    </table>
</c:when>
<c:otherwise>
    <label>No inactive pharmacists</label><br>
</c:otherwise>
</c:choose>
<a href="/mainAdmin">Main</a>
<a href="/logout">Logout</a>
</body>
</html>
