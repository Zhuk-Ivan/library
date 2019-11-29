<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Взятые книги</title>

</head>
<body>
<a href="/library/">Главная</a>
 <a href="/library/logout">Выход</a>
 <a href="/library/libr_page">Администрирование авторов</a>
  <a href="/library/books_page">Администрирование книг</a>
     <div class="container">
                <h2>Welcome librarian</h2>
     <c:if test="${orders != null}">
           <table width="60%" cellspacing="0" cellpadding="4" border="1">
               <tr>
                   <th style="display:none;">id</th>
                   <th>Логин</th>
                   <th>Название книги</th>
                   <th width="13%">Автор</th>
                   <th>Дата создания запроса</th>
                   <th>Дата выдачи книги</th>
                   <th>Дата возвращения</th>
                   <th>Выдать книгу</th>
               </tr>
               <c:forEach items="${orders}" var="order">
                   <tr>
                       <td style="display:none;">${order.id}</td>
                       <td>${order.login}</td>
                       <td>
                            <c:forEach items="${books[order.id]}" var="book">
                                <ol> ${book.title} </ol>
                            </c:forEach>
                       </td>
                       <td>
                            <c:forEach items="${books[order.id]}" var="book">
                                <ol>${book.authorFirstName} ${book.authorLastName}</ol>
                            </c:forEach></td>
                       <td>${order.createOrder}</td>
                       <td>${order.takeDate}</td>
                       <td>${order.expireDate}</td>
                       <td><form method="post" action="${pageContext.request.contextPath}/librarian_approve">
                       <input name="id" type="hidden" value="${order.id}"></input>
                       <input type="submit" value="Выдать"></input>
                       </form></td>
                   </tr>
               </c:forEach>
           </table>
       </c:if>
     </div>
</body>
</html>