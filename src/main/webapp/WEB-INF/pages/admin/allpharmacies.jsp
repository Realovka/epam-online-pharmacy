<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/common.css">
    <title>All pharmacies</title>
</head>
<body>
<h1 style="text-align: center">List all pharmacies</h1>
<h2 style="text-align: left">Add new pharmacy in form</h2>
<form action="/addPharmacy" method="post">
    <input type="text" name="number" placeholder="number"/>
    <input type="text" name="city" placeholder="city"/>
    <input type="text" name="street" placeholder="street"/>
    <input type="text" name="house" placeholder="house"/>
    <input type="text" name="block" placeholder="block"/>
    <input type="submit" name="Input pharmacy">
</form>
<c:choose>
    <c:when test="${sessionScope.allPharmacies.size()>0}">

        <table border="3">
            <thead>
            <th>Id</th>
            <th>Number</th>
            <th>City</th>
            <th>Street</th>
            <th>House</th>
            <th>Block</th>
            </thead>

            <c:forEach items="${sessionScope.allPharmacies}" var="pharmacy">

                <tr>
                    <td>
                            ${pharmacy.pharmacyId}
                    </td>
                    <td>
                            ${pharmacy.number}
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
<a href="/mainAdmin">Main</a>
<a href="/logout">Logout</a>
</body>
</html>
