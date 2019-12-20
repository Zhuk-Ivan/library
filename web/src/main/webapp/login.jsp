<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

       <html>
       <body>
       <a href="/library/"><spring:message code="page.main"/></a>

       <h1><spring:message code="page.login.head"/></h1><br>
       <form method="post" action="${pageContext.request.contextPath}/login">

           <input type="text" required placeholder="<spring:message code="placeholder.login"/>" name="login"><br>
           <input type="password" required placeholder="<spring:message code="placeholder.password"/>" name="password"><br><br>
           <input class="button" type="submit" value="<spring:message code="sign.in"/>">

       </form>
       ${errorBlock}
       ${errorLoginPassMessage}
       </body>
       </html>