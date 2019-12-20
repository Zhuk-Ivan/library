<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>

</head>
<body>
<div class="form">
    <a href="/library/"><spring:message code="page.main"/></a>
    <h1><spring:message code="page.registration.head"/></h1><br>
    <form method="post" action="${pageContext.request.contextPath}/registration">

        <input type="text" required placeholder="<spring:message code="placeholder.login"/>" name="login"><br>
        <input type="password" required placeholder="<spring:message code="placeholder.password"/>" name="password"><br>
        <input type="text" required placeholder="<spring:message code="placeholder.name"/>" name="firstName"><br>
        <input type="text" required placeholder="<spring:message code="placeholder.surname"/>" name="lastName"><br>
        <input type="text" required placeholder="<spring:message code="placeholder.phone"/>" name="phone"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <br>
        <input class="button" type="submit" value="<spring:message code="page.registration.reg"/>">
    </form>

    <p style="color: red">${error}</p>
</div>
</body>
</html>
