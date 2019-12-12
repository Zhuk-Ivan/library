<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
     <a href="/library/logout">Выход</a>
     <a href="/library/main">Главная страница</a>
     <a href="/library/personal_details">Изменить персональные данные</a>
     <a href="/library/authors">Авторы</a>
      <c:if test="${user.role == 'LIBRARIAN'}">
         <a href="/library/users_orders">Все заявки</a>
         <a href="/library/users">Все пользователи</a>
      </c:if>
     <div class="container">
<h2>Пользователи библиотеки</h2>
     <c:if test="${users != null}">
        <table width="60%" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <th style="display:none;">id</th>
                <th>Логин</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>phone</th>
                <th>email</th>
                <th>Роль</th>
                <th>Удалить пользователя</th>
                <th>Заблокировать/разблокировать пользователя</th>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td style="display:none;">${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td><c:choose>
                        <c:when test="${user.role == 'USER'}">Активен</c:when>
                        <c:when test="${user.role == 'LIBRARIAN'}">Библиотекарь</c:when>
                        <c:otherwise>Заблокирован</c:otherwise>
                    </c:choose></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/deleteUser">
                    <button type="submit" name="login" value="${user.login}">Удалить</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/block">
                       <input name="login" type="hidden" value="${user.login}"></input>
                       <input name="role" type="hidden" value="${user.role}"></input>
                       <c:choose>
                            <c:when test="${user.role == 'USER'}">
                                <input type="submit" value="Заблокировать"></input></c:when>
                            <c:otherwise>
                                <input type="submit" value="Разблокировать"></input>
                            </c:otherwise>
                       </c:choose>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </c:if>
    </div>
</body>
</html>