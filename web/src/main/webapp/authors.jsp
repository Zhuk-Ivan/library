<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Authors</title>
    </head>
    <body>
        <a href="/library/logout"><spring:message code="logout"/></a>
        <a href="/library/main"><spring:message code="page.main"/></a>
             <a href="/library/personal_details"><spring:message code="page.change.personal.data"/></a>
             <a href="/library/authors"><spring:message code="page.authors"/></a>
              <sec:authorize access="hasRole('ROLE_USER')">
                <a href="/library/my_orders"><spring:message code="page.my.orders"/></a>
              </sec:authorize>
              <sec:authorize access="hasRole('ROLE_LIBRARIAN')">
                 <a href="/library/users_orders"><spring:message code="page.users.orders"/></a>
                 <a href="/library/users"><spring:message code="page.users"/></a>
              </sec:authorize>

        <sec:authorize access="hasRole('ROLE_USER')">
                <table>
                    <tr>
                        <th>№</th>
                        <th><spring:message code="book.author"/></th>
                    </tr>
                    <c:forEach items="${authors}" var="authors">
                    <tr>
                            <td>${authors.id}</td>
                            <td><form method="post" action="${pageContext.request.contextPath}/findBooksByAuthorId">
                                <input name="id" type="hidden" value="${authors.id}" id="test"></input>
                                <input type="submit" class="link-lookalike" value="${authors.firstName} ${authors.lastName}"/>
                                </form></td>
                    </tr>
                    </c:forEach>
                </table>

                <br>
                <c:if test="${authorBooks != null}">
                     <table width="60%" cellspacing="0" cellpadding="4" border="1">
                                <tr>
                                    <th style="width: 100px"><spring:message code="book.title"/></th>
                                    <th><spring:message code="book.page.count"/></th>
                                    <th>isbn</th>
                                    <th><spring:message code="book.genre"/></th>
                                </tr>
                                <c:forEach items="${authorBooks}" var="book">
                                    <tr>
                                        <td>${book.title}</td>
                                        <td>${book.pageCount}</td>
                                        <td>${book.isbn}</td>
                                        <td><c:choose>
                                             <c:when test="${book.genre == 'FANTASY'}"><spring:message code="book.genre.fantasy"/></c:when>
                                             <c:when test="${book.genre == 'DETECTIVE'}"><spring:message code="book.genre.detective"/></c:when>
                                             <c:when test="${book.genre == 'DYSTOPIA'}"><spring:message code="book.genre.dystopia"/></c:when>
                                             <c:when test="${book.genre == 'DRAMA'}"><spring:message code="book.genre.drama"/></c:when>
                                             <c:when test="${book.genre == 'PHILOSOPHY'}"><spring:message code="book.genre.philosophy"/></c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            </c:if>
                </sec:authorize>

<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
        <table>
            <tr>
                <th>№</th>
                <th><spring:message code="placeholder.name"/></th>
                <th><spring:message code="placeholder.surname"/></th>
            </tr>
            <c:forEach items="${authors}" var="authors">
            <tr>
                <form >
                    <td><input name="id" type="text"  value=${authors.id}></td>
                    <td><input name="<spring:message code="placeholder.name"/>"  type="text"  value=${authors.firstName}></td>
                    <td><input name="<spring:message code="placeholder.surname"/>"  type="text"  value=${authors.lastName}></td>
                    <c:if test="${user.role == 'LIBRARIAN'}">
                        <td><input formaction="${pageContext.request.contextPath}/deleteAuthor" formmethod="post" type="submit" value="<spring:message code="button.delete"/>"></td>
                        <td><input formaction="${pageContext.request.contextPath}/updateAuthor" formmethod="post" type="submit" value="<spring:message code="button.update"/>"></td>
                    </c:if>
                </form>
            </tr>
            </c:forEach>
        </table>

        <br>

        <table border="1">
            <tr>
                <th><spring:message code="placeholder.name"/></th>
                <th><spring:message code="placeholder.surname"/></th>
                <th></th>
            </tr>
            <form >
                <tr>
                    <td><input name="<spring:message code="placeholder.name"/>" required type="text"  placeholder="Имя"></td>
                    <td><input name="<spring:message code="placeholder.surname"/>" required type="text"  placeholder="Фамилия"></td>
                    <td><input formaction="${pageContext.request.contextPath}/addAuthor" formmethod="post" type="submit"  value="<spring:message code="button.add"/>"></td>
                </tr>
            </form>
        </table>
    </sec:authorize>
    </body>

     <style type="text/css">
                .link-lookalike {
                    background: none;
                    border: none;
                    color: black;
                    text-decoration: underline;
                    cursor: pointer;
                }
            </style>
</html>