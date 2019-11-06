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
                   <th>Роль</th>
                   <th>Название книги</th>
                   <th width="13%">Автор</th>
                   <th>Дата взятия</th>
                   <th>Дата возвращения</th>
                   <th>Заблокировать/разблокировать пользователя</th>
               </tr>
               <c:forEach items="${orders}" var="order">
                   <tr>
                       <td style="display:none;">${order.userId}</td>
                       <td>${order.userLogin}</td>
                       <td><c:choose>
                           <c:when test="${order.userRole.equals('user')}">Активен</c:when>
                           <c:otherwise>Заблокирован</c:otherwise>
                       </c:choose></td>
                       <td>${order.bookTitle}</td>
                       <td>${order.bookAuthorFN} ${order.bookAuthorLN}</td>
                       <td>${order.takeDate}</td>
                       <td>${order.expireDate}</td>
                       <td><form method="post" action="${pageContext.request.contextPath}/librarianBlock">
                       <input name="id" type="hidden" value="${order.userId}"></input>
                       <input name="role" type="hidden" value="${order.userRole}"></input>
                       <c:choose>
                           <c:when test="${order.userRole.equals('user')}"><input type="submit" value="Заблокировать"></input></c:when>
                            <c:otherwise><input type="submit" value="Разблокировать"></input></c:otherwise>
                       </c:choose>
                       </form></td>
                   </tr>
               </c:forEach>
           </table>
       </c:if>
     </div>
</body>
</html>