<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Взятые книги</title>

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
                <h2>Welcome librarian</h2>
     <c:choose>
         <c:when test="${empty orders}"><h2><spring:message code="error.orders.empty"/></h2></c:when>
         <c:when test="${not empty orders}">
           <table width="60%" cellspacing="0" cellpadding="4" border="1">
               <tr>
                   <th style="display:none;">id</th>
                   <th><spring:message code="placeholder.login"/></th>
                   <th><spring:message code="book.title"/></th>
                   <th><spring:message code="book.author"/></th>
                   <th><spring:message code="order.create.date"/></th>
                   <th><spring:message code="order.take.date"/></th>
                   <th><spring:message code="order.expire.date"/></th>
                   <th><spring:message code="button.issue.book"/></th>
               </tr>
               <c:forEach items="${orders}" var="order">
                   <tr>
                       <td style="display:none;">${order.id}</td>
                       <td>${order.login}</td>
                       <td>
                            <c:forEach items="${books[order.id]}" var="book">
                                <ol> ${book.title} </ol>
                            </c:forEach>
                       </td>
                       <td>
                            <c:forEach items="${books[order.id]}" var="book">
                                <ol>${book.authorFirstName} ${book.authorLastName}</ol>
                            </c:forEach></td>
                       <td>${order.createOrder}</td>
                       <td>${order.takeDate}</td>
                       <td>${order.expireDate}</td>
                       <td><form method="post" action="${pageContext.request.contextPath}/approve">
                       <input name="id" type="hidden" value="${order.id}"></input>
                       <input type="submit" value="<spring:message code="button.issue.book"/>"></input>
                       </form></td>
                       <td><form method="post" action="${pageContext.request.contextPath}/deleteOrder">
                           <input name="id" type="hidden" value="${order.id}"></input>
                           <input type="submit" value="<spring:message code="button.close.order"/>"></input></form></td>
                   </tr>
               </c:forEach>
           </table>
       </c:when>
      </c:choose>
     </div>
</body>
</html>