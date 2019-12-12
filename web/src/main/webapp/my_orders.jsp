<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User orders page</title>

</head>
<body>
     <a href="/library/logout">Выход</a>
     <a href="/library/main">Главная страница</a>
     <a href="/library/personal_details">Изменить персональные данные</a>
     <a href="/library/authors">Авторы</a>
      <c:if test="${user.role == 'USER'}">
        <a href="/library/my_orders">Ваши заявки</a>
      </c:if>
      <c:if test="${user.role == 'LIBRARIAN'}">
         <a href="/library/users_orders">Все заявки</a>
         <a href="/library/users">Все пользователи</a>
      </c:if>
     <br>
                  <table width="60%" cellspacing="0" cellpadding="4" border="1">
                               <tr>
                                   <th style="display:none;">id</th>
                                   <th>Название книги</th>
                                   <th>Автор</th>
                                   <th>Сформировать заказ</th>
                               </tr>
                               <c:forEach items="${orderBooks}" var="book">
                                   <tr>
                                       <td>${book.title}</td>
                                       <td>${book.authorFirstName} ${book.authorLastName}</td>
                                   </tr>
                               </c:forEach>
                   </table>
                    <form method="post" action="${pageContext.request.contextPath}/addOrder">
                              <input class="button" type="submit" value="Сформировать заказ">
                          </form>
</body>
</html>

