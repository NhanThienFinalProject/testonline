<%-- 
    Document   : home
    Created on : Oct 3, 2020, 1:59:03 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<!-- About -->
<div class="container">
    <div class="row">
        
        <table class="table col-6 offset-3 mt-5">
            <thead>
                <tr>
                    <th scope="col"><c:out value="${user.role.nameRole}"/>:</th>
                    <th scope="col"><c:out value="${user.fullName}"/></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">User name:</th>
                    <td><c:out value="${user.userName}"/></td>                    
                </tr>
                <tr>
                    <th scope="row">Email:</th>
                    <td><c:out value="${user.email}"/></td>                    
                </tr>
                <tr>
                    <th scope="row">Phone:</th>
                    <td><c:out value="${user.phoneNumber}"/></td>                    
                </tr>
                <tr>
                    <th scope="row">Adress:</th>
                    <td><c:out value="${user.city}"/></td>                    
                </tr>
                <tr>
                    <th scope="row">CreateDate:</th>
                    <td><c:out value="${user.createDate}"/></td>                    
                </tr>
            </tbody>
        </table>
    </div>
</div>