<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors edit</title>
</head>
<body>
<h3>Просмотр авторов</h3>
<table>
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Фамилия</th>
    </tr>
    <c:forEach items="${authors}" var="authors">
        <tr>
            <form >
                <td><input name="id" type="text"  value=${authors.id}></td>
                <td><input name="firstName"  type="text"  value=${authors.firstName}></td>
                <td><input name="lastName"  type="text"  value=${authors.lastName}></td>
                <td><input formaction="${pageContext.request.contextPath}/deleteAuthor" formmethod="post" type="submit" value="удалить"></td>
                <td><input formaction="${pageContext.request.contextPath}/updateAuthor" formmethod="post" type="submit" value="обновить"></td>
            </form>
           </tr>
    </c:forEach>
</table>
<h3>Добавить нового автора</h3>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th></th>

    </tr>
    <form > <tr>
        <td><input name="firstName" required type="text"  placeholder="Имя"></td>
        <td><input name="lastName" required type="text"  placeholder="Фамилия"></td>
        <td> <input formaction="${pageContext.request.contextPath}/addAuthor" formmethod="post" type="submit"  value="добавить"></td>
    </tr>
    </form>
</table>
</body>
</html>