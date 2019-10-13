<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Home</title>

</head>
<body>


     <div class="container">
                <h2>Welcome</h2>
                Добро пожаловать,
                <c:if test="${authUser.role =='user'}">
                    <c:out value="пользователь"/>
                </c:if>
                <c:if test="${authUser.role =='librarian'}">
                            <c:out value="администратор"/>
                </c:if>

                ${login}
     </div>

</body>
</html>