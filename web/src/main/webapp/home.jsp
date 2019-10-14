<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>

</head>
<body>

     <a href="/library/">Главная</a>
     <div class="container">
                <h2>Welcome</h2>
                Добро пожаловать,
                <c:if test="${authUser.role =='user'}">
                    <c:out value="пользователь"/>
                </c:if>
                <c:if test="${authUser.role =='librarian'}">
                            <c:out value="администратор"/>
                            <a href="/library/librarian">Информация о пользователях</a>
                </c:if>

                 <table>
                               <tr>
                                   <th>Имя</th>
                                   <th>Фамилия</th>
                                   <th>phone</th>
                                   <th>email</th>
                               </tr>

                                   <tr>
                                       <td>${user.firstName}</td>
                                       <td>${user.lastName}</td>
                                       <td>${user.phone}</td>
                                       <td>${user.email}</td>
                                    </tr>
                   </table>
                   </br>
                <a href="/library/logout">Выход</a>
     </div>
</body>
</html>