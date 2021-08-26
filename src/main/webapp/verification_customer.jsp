<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css">
    <title>Complete registration</title>
</head>
<body>
<p>
    Check your mailbox and enter the code from the letter in the form below
</p>
<form action="${pageContext.request.contextPath}/controller?command=verification_customer" method="post">
    <p>
        ${sessionScope.codeVerificationError}
    </p>
    <input type="text" name="code" placeholder="code"/><br>
    <input type="submit" value="Send"/><br>
</form>
<a href="${pageContext.request.contextPath}/controller?command=login_page">Back to home page</a>
</body>
</html>
