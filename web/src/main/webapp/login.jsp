<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <html>
       <body>
       <a href="/library/">Главная</a>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
       <h1>Вход в систему</h1><br>
       <form method="post" action="${pageContext.request.contextPath}/login">

           <input type="text" required placeholder="ваш логин" name="login"><br>
           <input type="password" required placeholder="ваш пароль" name="password"><br><br>
           <input class="button" type="submit" value="Войти">

       </form>
       ${errorLoginPassMessage}
       </body>
       </html>