<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    <form action="${pageContext.request.contextPath}/controller?command=change_language&lang=${sessionScope.currentLocale}"
          method="post">
        <input type="hidden" name="current_url" value="${pageContext.request.requestURL}">
        <input type="submit" class="lang"
               value="${sessionScope.secondLocale}">
    </form>
</header>
