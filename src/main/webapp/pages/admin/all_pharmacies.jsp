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
    <title><fmt:message key="header.all_pharmacies"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"  method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<h1><fmt:message key="header.all_pharmacies"/></h1>
<h2><fmt:message key="header.add_pharmacy"/></h2>
<form action="${pageContext.request.contextPath}/controller?command=addition_pharmacy" method="post">

    <c:if test="${requestScope.numberError!=null}">
        <div class="error"><fmt:message key="error.pharmacy_number"/></div>
    </c:if>
    <c:if test="${requestScope.stringParametersError!=null}">
        <div class="error"><fmt:message key="error.pharmacy_string_parameter_error"/></div>
    </c:if>
    <c:if test="${requestScope.blockError!=null}">
        <div class="error"><fmt:message key="error.pharmacy_block_error"/></div>
    </c:if>

    <label class="field"><fmt:message key="label.number"/></label><br>
    <input type="text" name="number" value="${requestScope.mapData.get("number")}"
           placeholder="<fmt:message key="placeholder.number"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.city"/></label><br>
    <input type="text" name="city" value="${requestScope.mapData.get("city")}"
           placeholder="<fmt:message key="placeholder.city"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.street"/></label><br>
    <input type="text" name="street" value="${requestScope.mapData.get("street")}"
           placeholder="<fmt:message key="placeholder.street"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.house"/></label><br>
    <input type="text" name="house" value="${requestScope.mapData.get("house")}"
           placeholder="<fmt:message key="placeholder.house"/>" size="35px"/><br>
    <label class="field"><fmt:message key="label.block"/></label><br>
    <input type="text" name="block" value="${requestScope.mapData.get("block")}"
           placeholder="<fmt:message key="placeholder.block"/>" size="35px"/><br>
    <input type="submit" value="<fmt:message key="button.input_pharmacy"/>" class="button">
</form>

<c:choose>
    <c:when test="${sessionScope.currentPharmacies.size()>0}">
        <label><fmt:message key="msg.click_on_parameter"/></label>
        <table border="3">
            <thead>
            <th width="30"><fmt:message key="column.table.id"/></th>
            <th width="30"><fmt:message key="column.table.number"/></th>
            <th width="200"><fmt:message key="column.table.city"/></th>
            <th width="200"><fmt:message key="column.table.street"/></th>
            <th width="50"><fmt:message key="column.table.house"/></th>
            <th width="30"><fmt:message key="column.table.block"/></th>
            </thead>

            <c:forEach items="${sessionScope.currentPharmacies}" var="pharmacy">

                <tr>
                    <td width="30">
                            ${pharmacy.pharmacyId}
                    </td>
                    <td width="30">
                        <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacy_number_page&pharmacy_id=${pharmacy.pharmacyId}&currentPage=${requestScope.currentPage}">${pharmacy.number}</a>
                    </td>
                    <td width="200">
                        <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacy_city_page&pharmacy_id=${pharmacy.pharmacyId}">${pharmacy.city}</a>
                    </td>
                    <td width="200">
                        <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacy_street_page&pharmacy_id=${pharmacy.pharmacyId}">${pharmacy.street}</a>
                    </td>
                    <td width="50">
                        <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacy_house_page&pharmacy_id=${pharmacy.pharmacyId}">${pharmacy.house}</a>
                    </td>
                    <td width="30">
                        <a href="${pageContext.request.contextPath}/controller?command=updating_pharmacy_block_page&pharmacy_id=${pharmacy.pharmacyId}">${pharmacy.block}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<c:if test="${sessionScope.previousPharmacies.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies&count_back=true&current_page=${sessionScope.currentPage}" style="color: #800000"><fmt:message key="link.previous_pharmacies"/> </a>
</c:if>
<c:if test="${sessionScope.nextPharmacies.size() > 0}">
    <a href="${pageContext.request.contextPath}/controller?command=all_pharmacies&count_forward=true&current_page=${sessionScope.currentPage}" style="color: #800000"><fmt:message key="link.next_pharmacies"/></a>
</c:if><br>

<a href="${pageContext.request.contextPath}/controller?command=main_admin" class="common_link"><fmt:message
        key="link.admin_main"/></a><br>
<a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
        key="link.logout"/></a>
</body>
</html>
