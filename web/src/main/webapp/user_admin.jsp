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

                <h2>Добро пожаловать, ${authUser.login}</h2>
                 <table>
                               <tr>
                               <th style="display:none;">id</th>
                                   <th>Имя</th>
                                   <th>Фамилия</th>
                                   <th>phone</th>
                                   <th>email</th>
                                   <th>login</th>
                               </tr>
                                   <tr><form>
                                       <td style="display:none;"><input name="id" type="text" readonly  value=${authUser.id}></td>
                                       <td><input name="firstName" type="text"  value=${user.firstName}></td>
                                       <td><input name="lastName" type="text"  value=${user.lastName}></td>
                                       <td><input name="phone" type="text"  value=${user.phone}></td>
                                       <td><input name="email" type="text"  value=${user.email}></td>
                                       <td><input name="login" type="text"  value=${authUser.login}></td>
                                       <td><input formaction="${pageContext.request.contextPath}/updateUser" formmethod="post" type="submit" value="обновить"></td>
                                       </form></tr>
                   </table>
                   </br>

                    <form action="selectGenre" method="post">
                                           <p><select name="genre">
                                            <option disabled>Выберите жанр</option>
                                            <option value="DETECTIVE">Детектив</option>
                                            <option selected value="DRAMA">Драма</option>
                                            <option value="FANTASY">Фантастика</option>
                                            <option value="DYSTOPIA">Антиутопия</option>
                                             <option value="PHILOSOPHY">Философия</option>
                                             <option value="ALL">Показать весь список</option>
                                           </select></p>
                                           <p><input type="submit" value="Выбрать"></p>
                                          </form>
                                        ${errorOrder}

                    <table width="60%" cellspacing="0" cellpadding="4" border="1">
                                            <tr>
                                                <th style="display:none;">id</th>
                                                <th style="width: 100px">Название</th>
                                                <th>Автор</th>
                                                <th>Количество страниц</th>
                                                <th>isbn</th>
                                                <th>Жанр</th>
                                                <th>Статус</th>
                                                <th>В наличии</th>
                                            </tr>
                                            <c:forEach items="${books}" var="book">
                                            <tr>
                                                <td style="display:none;">${book.id}</td>
                                                <td>${book.title}</td>
                                                <td>${book.author.firstName} ${book.author.lastName}</td>
                                                <td>${book.pageCount}</td>
                                                <td>${book.isbn}</td>
                                                <td><c:choose>
                                                    <c:when test="${book.genre == 'FANTASY'}">Фантастика</c:when>
                                                    <c:when test="${book.genre == 'DETECTIVE'}">Детектив</c:when>
                                                    <c:when test="${book.genre == 'DYSTOPIA'}">Антиутопия</c:when>
                                                    <c:when test="${book.genre == 'DRAMA'}">Драма</c:when>
                                                    <c:when test="${book.genre == 'PHILOSOPHY'}">Философия</c:when>
                                                </c:choose></td>
                                                <td><c:choose>
                                                    <c:when test="${book.status == 'FREE'}">Свободна</c:when>
                                                    <c:when test="${book.status == 'OCCUPIED'}">Занята</c:when>
                                                </c:choose></td>
                                                <td>${book.inStock}</td>
                                                <td><form method="post" action="${pageContext.request.contextPath}/userAddOrderServlet">
                                                <input name="status" type="hidden" value="${book.status}"></input>
                                                <input name="userId" type="hidden" value="${authUser.id}"></input>
                                                <input name="id" type="hidden" value="${book.id}"></input>
                                                <input type="submit" value="Добавить"></input>
                                                </form></td>
                                            </tr>
                                            </c:forEach>
                    </table




</body>
</html>