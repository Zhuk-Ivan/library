<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ChangePersonalData</title>
</head>
    <body>
    <a href="/library/logout">Выход</a>
         <a href="/library/main">Главная страница</a>
         <a href="/library/authors">Авторы</a>
          <c:if test="${user.role == 'USER'}">
            <a href="/library/my_orders">Ваши заявки</a>
          </c:if>
          <c:if test="${user.role == 'LIBRARIAN'}">
             <a href="/library/users_orders">Все заявки</a>
             <a href="/library/users">Все пользователи</a>
          </c:if>
        <table>
            <tr>
                <th style="display:none;">id</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>phone</th>
            </tr>
            <tr>
                <form>
                    <td style="display:none;"><input name="id" type="text" readonly  value=${user.id}></td>
                    <td><input name="firstName" type="text"  value=${user.firstName}></td>
                    <td><input name="lastName" type="text"  value=${user.lastName}></td>
                    <td><input name="phone" type="text"  value=${user.phone}></td>
                    <td><input formaction="${pageContext.request.contextPath}/updateUser" formmethod="post" type="submit" value="обновить"></td>
                </form>
            </tr>
        </table>
    </body>
</html>