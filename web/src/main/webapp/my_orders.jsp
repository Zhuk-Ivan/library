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
                                   <th>Убрать из корзины</th>
                               </tr>
                               <c:forEach items="${orderBooks}" var="book">
                                   <tr>
                                       <td style="display:none;">${book.id}</td>
                                       <td>${book.title}</td>
                                       <td>${book.authorFirstName} ${book.authorLastName}</td>
                                        <td><form method="post" action="${pageContext.request.contextPath}/deleteTempBook">
                                            <input name="id" type="hidden" value="${book.id}"></input>
                                            <input type="submit" value="Убрать из корзины"></input></form></td>
                                   </tr>
                               </c:forEach>
                   </table>
                    <form method="post" action="${pageContext.request.contextPath}/addOrder">
                              <input class="button" type="submit" value="Сформировать заказ">
                          </form>
                          <br>
                          ${emptyOrders}
                          <c:if test="${myOrders != null}">
                                     <table width="60%" cellspacing="0" cellpadding="4" border="1">
                                         <tr>
                                             <th style="display:none;">id</th>
                                             <th>Название книги</th>
                                             <th width="13%">Автор</th>
                                             <th>Дата выдачи книги</th>
                                             <th>Дата возвращения</th>
                                         </tr>
                                         <c:forEach items="${myOrders}" var="order">
                                             <tr>
                                                 <td style="display:none;">${order.id}</td>
                                                 <td>
                                                      <c:forEach items="${books[order.id]}" var="book">
                                                          <ol> ${book.title} </ol>
                                                      </c:forEach>
                                                 </td>
                                                 <td>
                                                      <c:forEach items="${books[order.id]}" var="book">
                                                          <ol>${book.authorFirstName} ${book.authorLastName}</ol>
                                                      </c:forEach></td>
                                                 <td>${order.takeDate}</td>
                                                 <td>${order.expireDate}</td>
                                             </tr>
                                         </c:forEach>
                                     </table>
                                 </c:if>
</body>
</html>

