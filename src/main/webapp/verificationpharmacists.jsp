<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Verification new pharmacists</title>
</head>
<body>
<h1 style="text-align: center">List all pharmacists</h1>
<h2 style="text-align: left">Pharmacists waiting verification</h2>
<label>Click on id if you want activate pharmacist</label>

<c:if test="${sessionScope.allPharmacists.size()>0}">
    <table border="3">
        <thead>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Status</th>
        </thead>

        <c:forEach items="${sessionScope.allPharmacists}" var="pharmacist">
            <c:choose>
                <c:when test="${pharmacist.status.toString().equals('IN_REGISTR')}">
                    <tr>
                        <td>
                            <a href="/verificationPharmacist?id=${pharmacist.userId}">${pharmacist.userId}</a>
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
                </c:when>
            </c:choose>
        </c:forEach>
    </table>

    <h2 style="text-align: left">Active pharmacists</h2>
    <table border="3">
        <thead>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Status</th>
        </thead>

        <c:forEach items="${sessionScope.allPharmacists}" var="pharmacist">
            <c:choose>
                <c:when test="${pharmacist.status.toString().equals('ACTIVE')}">
                    <tr>
                        <td>
                               ${pharmacist.userId}
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
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</c:if>
<a href="/logout">Logout</a>
</div>
</body>
</html>
