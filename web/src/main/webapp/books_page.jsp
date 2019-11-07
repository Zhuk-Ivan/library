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
        <th style="display:none;">Id</th>
        <th style="width: 100px">Название</th>
        <th>Имя автора</th>
        <th>Фамилия автора</th>
        <th>Количество страниц</th>
        <th>isbn</th>
        <th>Жанр</th>
        <th>Статус</th>

    </tr>
    <c:forEach items="${books}" var="books">
        <tr>
            <form >
                <td style="display:none;"><input name="id" type="text"  value=${books.id}></td>
                <td><input name="title"  type="text"  value=${books.title}></td>
                <td style="display:none;"><input name="authorId"  type="text"  value=${books.author.id}>
                <td><input name="authorFN"  type="text"  value=${books.author.firstName}>
                <td><input name="authorLN"  type="text"  value=${books.author.lastName}>
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
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Количество страниц</th>
        <th>isbn</th>
        <th>Жанр</th>
        <th></th>
    </tr>
    <form > <tr>
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
                                </select></td>
        <td> <input formaction="${pageContext.request.contextPath}/addBook" formmethod="post" type="submit"  value="добавить"></td>
    </tr>
    </form>
</table>
</body>
</html>