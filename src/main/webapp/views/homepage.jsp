<%-- 
    Document   : homepage
    Created on : Sep 29, 2020, 4:28:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="user" items="${listUser}">
            <c:out value="${user.userId}"/>:<c:out value="${user.email}"/><br>
        </c:forEach>
    </body>
</html>
