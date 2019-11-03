<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User orders page</title>

</head>
<body>

     <a href="/library/">Главная</a>
     <a href="/library/logout">Выход</a>
     <a href="/library/user_admin">Кабинет пользователя</a>
     <br/>
                  <table width="60%" cellspacing="0" cellpadding="4" border="1">
                               <tr>
                                   <th>id</th>
                                   <th>Индекс книги</th>
                                   <th>Индекс пользователя</th>
                                   <th>Дата взятия</th>
                                   <th>Дата возвращения</th>
                               </tr>
                               <c:forEach items="${orders}" var="order">
                                   <tr>
                                       <td>${order.id}</td>
                                       <td>${order.bookId}</td>
                                       <td>${order.userId}</td>
                                       <td>${order.takeDate}</td>
                                       <td>${order.expireDate}</td>
                                   </tr>
                               </c:forEach>
                   </table>
</body>
</html>