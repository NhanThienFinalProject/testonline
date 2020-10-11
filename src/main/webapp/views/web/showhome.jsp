<%-- 
    Document   : login
    Created on : Sep 30, 2020, 3:20:33 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<%@page session="true"%>
<section class="content-section bg-light" id="about">
    <div class="container text-left">
        <div class="row">
            <!-- login -->
            <c:forEach var="user" items="${listUser}" >
                fullname:<c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>[
                createDate: <c:out value="${user.createDate}"/>]<br>
            </c:forEach>
            <!-- end login -->
        </div>
    </div>
</section>