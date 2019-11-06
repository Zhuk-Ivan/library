<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books edit</title>
</head>
<body>
<a href="/library/">Главная</a>
<a href="/library/logout">Выход</a>
<a href="/library/libr_page">Администрирование авторов</a>
<a href="/library/users_orders">Администрирование заказов</a>
<h3>Просмотр книг</h3>
<table>
    <tr>
        <th>Id</th>
        <th style="width: 100px">Название</th>
        <th>Id автора</th>
        <th>Количество страниц</th>
        <th>isbn</th>
        <th>Жанр</th>
        <th>Статус</th>

    </tr>
    <c:forEach items="${books}" var="books">
        <tr>
            <form >
                <td><input name="id" type="text"  value=${books.id}></td>
                <td><input name="title"  type="text"  value=${books.title}></td>
                <td><input name="author_id"  type="text"  value=${books.authorId}></td>
                <td><input name="page_count"  type="text"  value=${books.pageCount}></td>
                <td><input name="isbn"  type="text"  value=${books.isbn}></td>
                <td><input name="genre"  type="text"  value=${books.genre}></td>
                <td><input name="status"  type="text"  value=${books.status}></td>
                <td><input formaction="${pageContext.request.contextPath}/deleteBook" formmethod="post" type="submit" value="удалить"></td>
                <td><input formaction="${pageContext.request.contextPath}/updateBook" formmethod="post" type="submit" value="обновить"></td>
            </form>
           </tr>
    </c:forEach>
</table>
<h3>Добавить новую книгу</h3>
<table border="1">
    <tr>
        <th>Название</th>
        <th>Id автора</th>
        <th>Количество страниц</th>
        <th>isbn</th>
        <th>Жанр</th>
        <th></th>
    </tr>
    <form > <tr>
        <td><input name="title" required type="text"  placeholder="Название"></td>
        <td><input name="author_id" required type="text"  placeholder="Id автора"></td>
        <td><input name="page_count" required type="text"  placeholder="Количество страниц"></td>
        <td><input name="isbn" required type="text"  placeholder="isbn"></td>
        <td><input name="genre" required type="text"  placeholder="Жанр"></td>
        <td> <input formaction="${pageContext.request.contextPath}/addBook" formmethod="post" type="submit"  value="добавить"></td>
    </tr>
    </form>
</table>
</body>
</html>