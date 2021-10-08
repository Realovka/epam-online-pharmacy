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
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.about_product"/></title>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/header.jsp"/>

<h1><fmt:message key="title.about_product"/></h1>
<img id="account_image" class="pic"
     src="/addImage?product_id=${sessionScope.productId}" alt="no img"/><br>
<label class="product"><fmt:message key="label.product_name"/></label>
<div>${sessionScope.product.name}</div>
<br>
<label class="product"><fmt:message key="label.product_non_proprietary_name"/></label>
<div>${sessionScope.product.nonProprietaryName}</div>
<br>
<label class="product"><fmt:message key="label.product_dose"/></label>
<div>${sessionScope.product.dose}</div>
<br>
<label class="product"><fmt:message key="label.product_plant"/></label>
<div>${sessionScope.product.plant}</div>
<br>
<label class="product"><fmt:message key="label.product_group"/></label>
<div>${sessionScope.product.group}</div>
<br>
<label class="product"><fmt:message key="label.product_price"/></label>
<div>${sessionScope.product.price}</div>
<br>
<label class="product"><fmt:message key="label.product_recipe"/></label>
<div>${sessionScope.product.recipe}</div>
<br>
<label class="product"><fmt:message key="label.product_instruction"/></label>
<div>${sessionScope.product.instruction}</div>
<a href="${pageContext.request.contextPath}/controller?command=all_products" class="common_link"><fmt:message
        key="link.all_products"/></a><br>

<jsp:include page="${pageContext.request.contextPath}/pages/static_part/links_for_admin.jsp"/>

<ftg:footer/>
</body>
</html>
