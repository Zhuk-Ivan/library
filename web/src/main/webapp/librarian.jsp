<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Librarian</title>

</head>
<body>
<a href="/library/">Главная</a>
 <a href="/library/logout">Выход</a>
 <a href="/library/libr_page">Администрирование авторов</a>
  <a href="/library/books_page">Администрирование книг</a>
     <a href="/library/users_orders">Администрирование заказов</a>
     <div class="container">
                <h2>Welcome librarian</h2>
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
                       <td style="display:none;">${user.authUser.id}</td>
                       <td>${user.authUser.login}</td>
                       <td>${user.firstName}</td>
                       <td>${user.lastName}</td>
                       <td>${user.phone}</td>
                       <td>${user.email}</td>
                       <td><c:choose>
                            <c:when test="${user.authUser.role == 'USER'}">Активен</c:when>
                            <c:when test="${user.authUser.role == 'LIBRARIAN'}">Библиотекарь</c:when>
                            <c:otherwise>Заблокирован</c:otherwise>
                       </c:choose></td>
                       <td><form method="post" action="${pageContext.request.contextPath}/librarianDelete">
                            <button type="submit" name="id" value="${user.authUser.id}">Удалить</button>
                            </form>
                       </td>
                       <td><form method="post" action="${pageContext.request.contextPath}/librarianBlock">
                       <input name="id" type="hidden" value="${user.authUser.id}"></input>
                       <input name="role" type="hidden" value="${user.authUser.role}"></input>
                       <c:choose>
                           <c:when test="${user.authUser.role == 'USER'}"><input type="submit" value="Заблокировать"></input></c:when>
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