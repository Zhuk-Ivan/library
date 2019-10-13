<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>

</head>
<body>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<div class="form">

    <h1>Регистрация в системе</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/registration">

        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input type="text" required placeholder="fist name" name="firstName"><br>
        <input type="text" required placeholder="last name" name="lastName"><br>
        <input type="text" required placeholder="phone" name="phone"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <br>
        <input class="button" type="submit" value="Регистрироваться">
    </form>
</div>
</body>
</html>
