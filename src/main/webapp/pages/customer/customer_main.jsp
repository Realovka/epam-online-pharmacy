<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftg" uri="/WEB-INF/tld/footer.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/common.css">
    <title><fmt:message key="title.customer_main"/></title>
</head>

<form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
      method="post">
    <input type="hidden" name="current_url" value="${pageContext.request.requestURL}"/>
    <input type="submit" value="${sessionScope.secondLocale}" class="lang"/>
</form>

<nav class="menu">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=how_to_do_order_page"><fmt:message
                    key="link.name.how_to_do_order"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=about_us_page"><fmt:message
                    key="link.name.about_us"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=questions_page"><fmt:message
                    key="link.name.faq"/></a>
        </li>
    </ul>
</nav>

<h1><fmt:message key="msg.hello"/> ${sessionScope.authUser.firstName} ${sessionScope.authUser.lastName}</h1>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=search_products_by_name_page"
           class="common_link"><fmt:message
                key="link.search_by_product_name"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=search_products_by_non_proprietary_name_page"
           class="common_link"><fmt:message
                key="link.search_by_product_non_proprietary_name"/></a><br>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=logout" class="common_link"><fmt:message
                key="link.logout"/></a>
    </li>
</ul>
<ftg:footer/>
</body>
</html>
