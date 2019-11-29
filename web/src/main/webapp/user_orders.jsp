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
                    <form method="post" action="${pageContext.request.contextPath}/userAddOrderServlet">
                              <input class="button" type="submit" value="Сформировать заказ">
                          </form>
</body>
</html>

