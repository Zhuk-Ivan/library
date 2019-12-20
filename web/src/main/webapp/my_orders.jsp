<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>User orders page</title>

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
      <c:choose>
          <c:when test="${empty orderBooks}"><h2><spring:message code="error.user.temp.orders.empty"/></h2></c:when>
          <c:when test="${not empty orderBooks}">
                  <table width="60%" cellspacing="0" cellpadding="4" border="1">
                               <tr>
                                   <th style="display:none;">id</th>
                                   <th><spring:message code="book.title"/></th>
                                   <th><spring:message code="book.author"/></th>
                                   <th><spring:message code="button.remove.temp.book"/></th>
                               </tr>
                               <c:forEach items="${orderBooks}" var="book">
                                   <tr>
                                       <td style="display:none;">${book.id}</td>
                                       <td>${book.title}</td>
                                       <td>${book.authorFirstName} ${book.authorLastName}</td>
                                        <td><form method="post" action="${pageContext.request.contextPath}/deleteTempBook">
                                            <input name="id" type="hidden" value="${book.id}"></input>
                                            <input type="submit" value="<spring:message code="button.remove.temp.book"/>"></input></form></td>
                                   </tr>
                               </c:forEach>
                   </table>
                    <form method="post" action="${pageContext.request.contextPath}/addOrder">
                              <input class="button" type="submit" value="<spring:message code="button.create.order"/>">
                          </form>
                          </table>
                      </c:when>
                      </c:choose>
                          <br>
                          <c:choose>
                                   <c:when test="${empty myOrders}"><h2><spring:message code="error.user.orders.empty"/></h2></c:when>
                                   <c:when test="${not empty myOrders}">
                                     <table width="60%" cellspacing="0" cellpadding="4" border="1">
                                         <tr>
                                             <th style="display:none;">id</th>
                                             <th><spring:message code="book.title"/></th>
                                             <th><spring:message code="book.author"/></th>
                                             <th><spring:message code="order.take.date"/></th>
                                             <th><spring:message code="order.expire.date"/></th>
                                         </tr>
                                         <c:forEach items="${myOrders}" var="order">
                                             <tr>
                                                 <td style="display:none;">${order.id}</td>
                                                 <td>
                                                      <c:forEach items="${books[order.id]}" var="book">
                                                          <ol> ${book.title} </ol>
                                                      </c:forEach>
                                                 </td>
                                                 <td>
                                                      <c:forEach items="${books[order.id]}" var="book">
                                                          <ol>${book.authorFirstName} ${book.authorLastName}</ol>
                                                      </c:forEach></td>
                                                 <td>${order.takeDate}</td>
                                                 <td>${order.expireDate}</td>
                                             </tr>
                                         </c:forEach>
                                     </table>
                                     </c:when>
                                 </c:choose>
</body>
</html>

