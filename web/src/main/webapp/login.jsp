<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <html>
       <body>
       <h1>Вход в систему</h1><br>
       <form method="post" action="${pageContext.request.contextPath}/login">

           <input type="text" required placeholder="ваш логин" name="login"><br>
           <input type="password" required placeholder="ваш пароль" name="password"><br><br>
           <input class="button" type="submit" value="Войти">

       </form>
       </body>
       </html>