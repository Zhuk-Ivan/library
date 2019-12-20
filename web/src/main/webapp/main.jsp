<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
    </head>
    <body>
     <a href="/library/logout"><spring:message code="logout"/></a>
     <a href="/library/personal_details"><spring:message code="page.change.personal.data"/></a>
     <a href="/library/authors"><spring:message code="page.authors"/></a>
      <sec:authorize access="hasRole('ROLE_USER')">
        <a href="/library/my_orders"><spring:message code="page.my.orders"/></a>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_LIBRARIAN')">
         <a href="/library/users_orders"><spring:message code="page.users.orders"/></a>
         <a href="/library/users"><spring:message code="page.users"/></a>
      </sec:authorize>

     <h2><spring:message code="page.main.welcome"/> ${user.login}</h2>

     <form action="select" method="post">
         <p><select name="genre">
             <option disabled><spring:message code="select.book.genre"/></option>
             <option value="DETECTIVE"><spring:message code="book.genre.detective"/></option>
             <option selected value="DRAMA"><spring:message code="book.genre.drama"/></option>
             <option value="FANTASY"><spring:message code="book.genre.fantasy"/></option>
             <option value="DYSTOPIA"><spring:message code="book.genre.dystopia"/></option>
             <option value="PHILOSOPHY"><spring:message code="book.genre.philosophy"/></option>
             <option value="ALL"><spring:message code="book.genre.show.all"/></option>
            </select></p>
         <p><input type="submit" value="<spring:message code="button.choose"/>"></p>
     </form>

<div align="center">
<p style="color: red">${errorOrder}</p>
<p style="color: red">${errorMakeOrder}</p>
<p style="color: red">${errorBookInOrder}</p>
</div>


     <sec:authorize access="hasRole('ROLE_USER')">
        <table width="60%" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <th style="display:none;">id</th>
                <th style="width: 100px"><spring:message code="book.title"/></th>
                <th><spring:message code="book.author"/></th>
                <th><spring:message code="book.page.count"/></th>
                <th>isbn</th>
                <th><spring:message code="book.genre"/></th>
                <th><spring:message code="book.status"/></th>
                <th><spring:message code="book.in.stock"/></th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td style="display:none;">${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.authorFirstName} ${book.authorLastName}</td>
                    <td>${book.pageCount}</td>
                    <td>${book.isbn}</td>
                    <td><c:choose>
                        <c:when test="${book.genre == 'FANTASY'}"><spring:message code="book.genre.fantasy"/></c:when>
                        <c:when test="${book.genre == 'DETECTIVE'}"><spring:message code="book.genre.detective"/></c:when>
                        <c:when test="${book.genre == 'DYSTOPIA'}"><spring:message code="book.genre.dystopia"/></c:when>
                        <c:when test="${book.genre == 'DRAMA'}"><spring:message code="book.genre.drama"/><</c:when>
                        <c:when test="${book.genre == 'PHILOSOPHY'}"><spring:message code="book.genre.philosophy"/></c:when>
                        </c:choose>
                    </td>
                    <td><c:choose>
                        <c:when test="${book.status == 'FREE'}">Свободна</c:when>
                        <c:when test="${book.status == 'OCCUPIED'}">
                            <c:forEach items="${dates[book.id]}" var="date">${date}</c:forEach>
                        </c:when>
                        </c:choose>
                    </td>
                    <td>${book.inStock}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/form_order">
                            <input name="status" type="hidden" value="${book.status}"></input>
                            <input name="userId" type="hidden" value="${user.id}"></input>
                            <input name="inStock" type="hidden" value="${book.inStock}"></input>
                            <input name="id" type="hidden" value="${book.id}"></input>
                            <input type="submit" value="<spring:message code="order.form.order"/>"></input>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </sec:authorize>
        <br>
       <sec:authorize access="hasRole('ROLE_LIBRARIAN')">
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
       <br>

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
 </sec:authorize>

      <br>

      <form method="post" action="${pageContext.request.contextPath}/main">
          <input name="pageNumber" type="hidden" value="${pageNumber}">

          <c:if test="${pageNumber>1}">
              <input name="prev" type="submit" value="<spring:message code="button.next"/>">
          </c:if>

          <c:if test="${pageNumber<maxResult}">
              <input name="next" type="submit" value="<spring:message code="button.prev"/>">
          </c:if>
      </form>
    </body>
</html>