<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>ChangePersonalData</title>
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
        <table>
            <tr>
                <th style="display:none;">id</th>
                <th><spring:message code="placeholder.name"/></th>
                <th><spring:message code="placeholder.surname"/></th>
                <th><spring:message code="placeholder.phone"/></th>
            </tr>
            <tr>
                <form>
                    <td style="display:none;"><input name="id" type="text" readonly  value=${user.id}></td>
                    <td><input name="firstName" type="text"  value=${user.firstName}></td>
                    <td><input name="lastName" type="text"  value=${user.lastName}></td>
                    <td><input name="phone" type="text"  value=${user.phone}></td>
                    <td><input formaction="${pageContext.request.contextPath}/updateUser" formmethod="post" type="submit" value="<spring:message code="button.update"/>"></td>
                </form>
            </tr>
        </table>
    </body>
</html>