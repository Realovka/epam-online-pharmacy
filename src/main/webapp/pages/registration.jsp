<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/reg.css">
    <link rel="stylesheet" href="/styles/common_menu.css">
    <title><fmt:message key="page.registration"/></title>
</head>
<body>

<jsp:include page="static_part/header.jsp"/>

<a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
        key="link.name.back_to_login_page"/></a>

<jsp:include page="static_part/menu_for_customer.jsp"/>

<div class="reg">
    <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <h1><fmt:message key="page.registration"/></h1><br>
        <c:if test="${requestScope.dataRegistrationError!=null}">
            <div class="error"><fmt:message key="error.registration_user_data"/></div>
        </c:if>
        <c:if test="${requestScope.registrationError!=null}">
            <div class="error"><fmt:message key="error.registration_user"/></div>
        </c:if>
        <label class="field"><fmt:message key="label.login"/></label><br>
        <input type="text" name="login" value="${requestScope.mapData.get("login")}"
               placeholder="<fmt:message key="placeholder.name.login"/>" size="35px"/><br>
        <label class="field"><fmt:message key="label.password"/></label><br>
        <input type="password" name="password" placeholder="<fmt:message key="placeholder.name.password"/>"
               size="35px"/><br>
        <label class="field"><fmt:message key="label.first_name"/></label><br>
        <input type="text" name="firstName" value="${requestScope.mapData.get("firstName")}"
               placeholder="<fmt:message key="placeholder.name.firstName"/>" size="35px"/><br>
        <label class="field"><fmt:message key="label.last_name"/></label><br>
        <input type="text" name="lastName" value="${requestScope.mapData.get("lastName")}"
               placeholder="<fmt:message key="placeholder.name.lastName"/>" size="35px"/><br>
        <label class="field"><fmt:message key="label.email"/></label><br>
        <input type="email" name="email" value="${requestScope.mapData.get("email")}"
               placeholder="<fmt:message key="placeholder.name.email"/>" size="35px"/><br>
        <label class="field"><fmt:message key="label.telephone"/></label><br>
        <input type="tel" name="telephone" value="${requestScope.mapData.get("telephone")}"
               placeholder="<fmt:message key="placeholder.name.telephone"/>" size="35px"/><br>
        <select name="role">
            <option><fmt:message key="role.customer"/></option>
            <option><fmt:message key="role.pharmacist"/></option>
        </select><br><br>
        <input type="submit" value="<fmt:message key="button.registration"/>">
    </form>
</div>

<ftg:footer/>
</body>

</html>
