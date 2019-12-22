<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
<a href="/library/logout"><spring:message code="logout"/></a>
<a href="/library/main"><spring:message code="page.main"/></a>
     <a href="/library/personal_details"><spring:message code="page.change.personal.data"/></a>
     <a href="/library/authors"><spring:message code="page.authors"/></a>
      <c:if test="${user.role == 'USER'}">
        <a href="/library/my_orders"><spring:message code="page.my.orders"/></a>
      </c:if>
      <c:if test="${user.role == 'LIBRARIAN'}">
         <a href="/library/users_orders"><spring:message code="page.users.orders"/></a>
         <a href="/library/users"><spring:message code="page.users"/></a>
      </c:if>
      <br>
     <div class="container">
     <c:if test="${users != null}">
        <table width="60%" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <th style="display:none;">id</th>
                <th><spring:message code="placeholder.login"/></th>
                <th><spring:message code="placeholder.name"/></th>
                <th><spring:message code="placeholder.surname"/></th>
                <th><spring:message code="placeholder.phone"/></th>
                <th>email</th>
                <th>Роль</th>
                <th><spring:message code="button.delete.user"/></th>
                <th><spring:message code="button.block.user"/></th>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td style="display:none;">${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td><c:choose>
                        <c:when test="${user.role == 'USER'}">Активен</c:when>
                        <c:when test="${user.role == 'LIBRARIAN'}">Библиотекарь</c:when>
                        <c:otherwise>Заблокирован</c:otherwise>
                    </c:choose></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/deleteUser">
                    <button type="submit" name="login" value="${user.login}"><spring:message code="button.delete"/></button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/block">
                       <input name="login" type="hidden" value="${user.login}"></input>
                       <input name="role" type="hidden" value="${user.role}"></input>
                       <input type="submit" value="<spring:message code="button.block"/>"></input>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </c:if>
    </div>

   <br>
   <c:choose>
            <c:when test="${empty blockedUsers}"><h2><spring:message code="error.blocked.users.empty"/></h2></c:when>
            <c:when test="${not empty blockedUsers}">
                <table width="60%" cellspacing="0" cellpadding="4" border="1">
                            <tr>
                                <th style="display:none;">id</th>
                                <th><spring:message code="placeholder.login"/></th>
                                <th><spring:message code="placeholder.name"/></th>
                                <th><spring:message code="placeholder.surname"/></th>
                                <th><spring:message code="placeholder.phone"/></th>
                                <th>email</th>
                                <th>Роль</th>
                                <th>Books to return</th>
                                <th>Expire date</th>
                                <th><spring:message code="button.block.user"/></th>
                            </tr>
                            <c:forEach items="${blockedUsers}" var="blockedUser">
                            <tr>
                                <td style="display:none;">${blockedUser.id}</td>
                                <td>${blockedUser.login}</td>
                                <td>${blockedUser.firstName}</td>
                                <td>${blockedUser.lastName}</td>
                                <td>${blockedUser.phone}</td>
                                <td>${blockedUser.email}</td>
                                <td>${blockedUser.role}</td>
                                <td><c:forEach items="${blockedUser.booksToReturn}" var="books">
                                        <c:forEach items="${books.value}" var="book">
                                            <ol>${book.title}</ol>
                                    </c:forEach></c:forEach></td>
                                <td><c:forEach items="${blockedUser.booksToReturn}" var="books">
                                        <ol>${books.key}</ol>
                                    </c:forEach></td></td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/block">
                                       <input name="login" type="hidden" value="${blockedUser.login}"></input>
                                       <input name="role" type="hidden" value="${blockedUser.role}"></input>
                                       <input type="submit" value="<spring:message code="button.unblock"/>"></input>
                                       </form>
            </tr>
            </c:forEach>
        </table>
            </c:when>
   </c:choose>
</body>
</html>