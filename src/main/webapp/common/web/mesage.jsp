<%-- 
    Document   : mesage
    Created on : Oct 14, 2020, 2:39:30 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<c:if test="${!empty mesage}">
    <div class="alert <c:out value="${mesageColor}"/> alert-dismissible fade show " role="alert" style="position: fixed; top: 10vh;right: 0vh;">
    <strong>TestOnline!</strong> <c:out value="${mesage}"/>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
</c:if>