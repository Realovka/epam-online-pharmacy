<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/common.css">
    <title>All products</title>
</head>
<body>
<h1 style="text-align: center">List all products</h1>
<h2 style="text-align: left">Add new product in form</h2>
<form action="/addProduct" method="post">
    <input type="text" name="name" placeholder="name"/>
    <input type="text" name="price" placeholder="price"/>
    <input type="text" name="group" placeholder="group"/>
    <input type="text" name="recipe" placeholder="recipe"/>
    <input type="file" name="picture" placeholder="picture"/>
    <input type="text" name="instruction" placeholder="instruction"/>
    <input type="submit" value="Input product">
</form>

<a href="/logout">Logout</a>
</body>
</html>