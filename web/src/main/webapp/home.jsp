<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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