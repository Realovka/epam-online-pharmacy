<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.epam.onlinepharmacy.entity.Status" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/common.css">
    <title>All pharmacists</title>
</head>
<body>
<h1 style="text-align: center">List all pharmacists</h1>
<h2 style="text-align: left">Pharmacists waiting verification</h2>
<label>Click on id if you want to verify pharmacist</label>

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
                <c:when test="${pharmacist.status.equals(Status.IN_REGISTR)}">
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
    <label>Click on parameter if you want to change it</label>
    <table border="3">
        <thead>
        <th>Id</th>
        <th>Login</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Telephone</th>
        <th>email</th>
        <th>Status</th>
        </thead>

        <c:forEach items="${sessionScope.allPharmacists}" var="pharmacist">
            <c:choose>
                <c:when test="${pharmacist.status.equals(Status.ACTIVE)}">
                    <tr>
                        <td>
                                ${pharmacist.userId}
                        </td>
                        <td>
                            <a href="/updateLogin?id=${pharmacist.userId}">${pharmacist.login}</a>
                        </td>
                        <td>
                            <a href="/updateFirstName?id=${pharmacist.userId}">${pharmacist.firstName}</a>

                        </td>
                        <td>
                            <a href="/updateLastName?id=${pharmacist.userId}">${pharmacist.lastName}</a>
                        </td>
                        <td>
                            <a href="/updateTelephone?id=${pharmacist.userId}">${pharmacist.telephone}</a>
                        </td>
                        <td>
                            <a href="/updateEmail?id=${pharmacist.userId}">${pharmacist.email}</a>
                        </td>
                        <td>
                            <a href="/inactivationPharmacist?id=${pharmacist.userId}">${pharmacist.status}</a>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</c:if>
<a href="/mainAdmin">Main</a>
<a href="/logout">Logout</a>
</body>
</html>
