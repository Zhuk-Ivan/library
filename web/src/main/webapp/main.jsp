<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>User admin page</title>
    </head>
    <body>
     <a href="/library/logout">Выход</a>
     <a href="/library/personal_details">Изменить персональные данные</a>
     <a href="/library/authors">Авторы</a>
      <c:if test="${user.role == 'USER'}">
        <a href="/library/my_orders">Ваши заявки</a>
      </c:if>
      <c:if test="${user.role == 'LIBRARIAN'}">
         <a href="/library/users_orders">Все заявки</a>
         <a href="/library/users">Все пользователи</a>
      </c:if>

     <h2>Добро пожаловать, ${user.login}</h2>

     <form action="select" method="post">
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
${errorMakeOrder}

        <c:if test="${user.role == 'USER'}">
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
                    <td>${book.authorFirstName} ${book.authorLastName}</td>
                    <td>${book.pageCount}</td>
                    <td>${book.isbn}</td>
                    <td><c:choose>
                        <c:when test="${book.genre == 'FANTASY'}">Фантастика</c:when>
                        <c:when test="${book.genre == 'DETECTIVE'}">Детектив</c:when>
                        <c:when test="${book.genre == 'DYSTOPIA'}">Антиутопия</c:when>
                        <c:when test="${book.genre == 'DRAMA'}">Драма</c:when>
                        <c:when test="${book.genre == 'PHILOSOPHY'}">Философия</c:when>
                        </c:choose>
                    </td>
                    <td><c:choose>
                        <c:when test="${book.status == 'FREE'}">Свободна</c:when>
                        <c:when test="${book.status == 'OCCUPIED'}">Ближайшее возвращение книги будет <br> ${book.nearestDateToReturn}</c:when>
                        </c:choose>
                    </td>
                    <td>${book.inStock}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/form_order">
                            <input name="status" type="hidden" value="${book.status}"></input>
                            <input name="userId" type="hidden" value="${user.id}"></input>
                            <input name="inStock" type="hidden" value="${book.inStock}"></input>
                            <input name="id" type="hidden" value="${book.id}"></input>
                            <input type="submit" value="Добавить в корзину"></input>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>

        <br>
       <c:if test="${user.role == 'LIBRARIAN'}">
       <table>
         <tr>
               <th style="display:none;">Id</th>
               <th style="width: 100px">Название</th>
               <th>Имя автора</th>
               <th>Фамилия автора</th>
               <th>Количество страниц</th>
               <th>isbn</th>
               <th>Жанр</th>
               <th>Статус</th>
               <th>В наличии</th>
         </tr>
            <c:forEach items="${books}" var="books">
                <tr>
                    <form >
                        <td style="display:none;"><input name="id" type="text"  value=${books.id}></td>
                        <td><input name="title"  type="text"  value=${books.title}></td>
                        <td><input name="authorFirstName"  type="text"  value=${books.authorFirstName}>
                        <td><input name="authorLastName"  type="text"  value=${books.authorLastName}>
                        <td><input name="page_count"  type="text"  value=${books.pageCount}></td>
                        <td><input name="isbn"  type="text"  value=${books.isbn}></td>
                        <td><select name ="genre">
                            <option selected value="FANTASY">Фантастика</option>
                            <option selected value="DETECTIVE">Детектив</option>
                            <option selected value="DYSTOPIA">Антиутопия</option>
                            <option selected value="DRAMA">Драма</option>
                            <option selected value="PHILOSOPHY">Философия</option>
                            </select></td>
                        <td><select name ="status">
                            <option selected value="FREE">Свободна</option>
                            <option selected value="OCCUPIED">Забронирована</option>
                        </select></td></td>
                        <td><input name="inStock"  type="text"  value=${books.inStock}></td>
                        <td><input formaction="${pageContext.request.contextPath}/deleteBook" formmethod="post" type="submit" value="удалить"></td>
                        <td><input formaction="${pageContext.request.contextPath}/updateBook" formmethod="post" type="submit" value="обновить"></td>
                    </form>
                   </tr>
            </c:forEach>
        </table>
       </c:if>

       <br>

      <c:if test="${user.role == 'LIBRARIAN'}">
        </table
        <h3>Добавить новую книгу</h3>
        <table border="1">
            <tr>
                <th>Название</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Количество страниц</th>
                <th>isbn</th>
                <th>Жанр</th>
                <th>В наличии</th>
            </tr>
            <form >
                <tr>
                    <td><input name="title" required type="text"  placeholder="Название"></td>
                    <td><input name="authorFN" required type="text"  placeholder="Имя автора"></td>
                    <td><input name="authorLN" required type="text"  placeholder="Фамилия автора"></td>
                    <td><input name="page_count" required type="text"  placeholder="Количество страниц"></td>
                    <td><input name="isbn" required type="text"  placeholder="isbn"></td>
                    <td><select name ="genre">
                            <option selected value="FANTASY">Фантастика</option>
                            <option selected value="DETECTIVE">Детектив</option>
                            <option selected value="DYSTOPIA">Антиутопия</option>
                            <option selected value="DRAMA">Драма</option>
                            <option selected value="PHILOSOPHY">Философия</option>
                        </select>
                    </td>
                    <td><input name="inStock" required type="text"  placeholder="Количество в библиотеке"></td>
                    <td><input formaction="${pageContext.request.contextPath}/addBook" formmethod="post" type="submit"  value="Добавить"></td>
                </tr>
            </form>
        </table>
      </c:if>

      <br>

      <form method="post" action="${pageContext.request.contextPath}/main">
          <input name="pageNumber" type="hidden" value="${pageNumber}">

          <c:if test="${pageNumber>1}">
              <input name="prev" type="submit" value="Назад">
          </c:if>

          <c:if test="${pageNumber<maxResult}">
              <input name="next" type="submit" value="Далее">
          </c:if>
      </form>
    </body>
</html>