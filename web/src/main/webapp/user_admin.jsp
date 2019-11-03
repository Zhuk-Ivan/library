<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User admin page</title>

</head>
<body>

     <a href="/library/">Главная</a>
     <a href="/library/logout">Выход</a>
     <a href="/library/user_orders">Ваши заявки</a>

                <h2>Добро пожаловать, ${user.firstName}</h2>
                 <table>
                               <tr>
                               <th>id</th>
                                   <th>Имя</th>
                                   <th>Фамилия</th>
                                   <th>phone</th>
                                   <th>email</th>
                                   <th>login</th>
                                   <th>password</th>
                               </tr>
                                   <tr><form>
                                       <td><input name="id" type="text"  value=${user.id}></td>
                                       <td><input name="firstName" type="text"  value=${user.firstName}></td>
                                       <td><input name="lastName" type="text"  value=${user.lastName}></td>
                                       <td><input name="phone" type="text"  value=${user.phone}></td>
                                       <td><input name="email" type="text"  value=${user.email}></td>
                                       <td><input name="login" type="text"  value=${user.login}></td>
                                       <td><input name="password" type="text"  value=${user.password}></td>
                                       <td><input formaction="${pageContext.request.contextPath}/updateUser" formmethod="post" type="submit" value="обновить"></td>
                                       </form></tr>
                   </table>
                   </br>

                    <table width="60%" cellspacing="0" cellpadding="4" border="1">
                                            <tr>
                                                <th>id</th>
                                                <th style="width: 100px">Название</th>
                                                <th>Количество страниц</th>
                                                <th>isbn</th>
                                                <th>Жанр</th>
                                                <th>Автор</th>
                                            </tr>
                                            <c:forEach items="${books}" var="book">
                                            <tr>
                                                <td>${book.id}</td>
                                                <td>${book.title}</td>
                                                <td>${book.pageCount}</td>
                                                <td>${book.isbn}</td>
                                                <td>${book.genre}</td>
                                                <td>${book.authorFirstName} ${book.authorLastName}</td>
                                                <td><form method="post" action="${pageContext.request.contextPath}/addOrder">
                                                <button type="submit" name="id" value="${book.id}">Добавить</button></form></td>
                                            </tr>
                                            </c:forEach>
                    </table>

</body>
</html>