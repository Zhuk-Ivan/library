<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Authors</title>
    </head>
    <body>
        <a href="/library/logout">Выход</a>
        <a href="/library/main">Главная страница</a>
        <a href="/library/personal_details">Изменить персональные данные</a>
        <c:if test="${user.role == 'USER'}">
            <a href="/library/my_orders">Ваши заявки</a>
        </c:if>
        <c:if test="${user.role == 'LIBRARIAN'}">
            <a href="/library/users_orders">Все заявки</a>
            <a href="/library/myOrders">Все пользователи</a>
        </c:if>
<h3>Авторы в библиотеке</h3>
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
                    <c:if test="${user.role == 'LIBRARIAN'}">
                        <td><input formaction="${pageContext.request.contextPath}/deleteAuthor" formmethod="post" type="submit" value="удалить"></td>
                        <td><input formaction="${pageContext.request.contextPath}/updateAuthor" formmethod="post" type="submit" value="обновить"></td>
                    </c:if>
                </form>
            </tr>
            </c:forEach>
        </table>

        <br>

<c:if test="${user.role == 'LIBRARIAN'}">
<h3>Добавить нового автора</h3>
        <table border="1">
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th></th>
            </tr>
            <form >
                <tr>
                    <td><input name="firstName" required type="text"  placeholder="Имя"></td>
                    <td><input name="lastName" required type="text"  placeholder="Фамилия"></td>
                    <td><input formaction="${pageContext.request.contextPath}/addAuthor" formmethod="post" type="submit"  value="добавить"></td>
                </tr>
            </form>
        </table>
    </c:if>
    </body>
</html>