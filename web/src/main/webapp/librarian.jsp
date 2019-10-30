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
     <div class="container">
                <h2>Welcome librarian</h2>
     <c:if test="${users != null}">
           <table>
               <tr>
                   <th>Логин</th>
                   <th>Имя</th>
                   <th>Фамилия</th>
                   <th>phone</th>
                   <th>email</th>
                   <th>Удалить пользователя</th>
               </tr>
               <c:forEach items="${users}" var="user">
                   <tr>
                       <td>${user.login}</td>
                       <td>${user.firstName}</td>
                       <td>${user.lastName}</td>
                       <td>${user.phone}</td>
                       <td>${user.email}</td>
                       <td>
                        <form method="post" action="${pageContext.request.contextPath}/librarian">
                       <button type="submit" name="login" value="${user.login}">Удалить</button>
                        </form>
                       </td>
                   </tr>
                </form>
               </c:forEach>
           </table>
       </c:if>
     </div>
</body>
</html>