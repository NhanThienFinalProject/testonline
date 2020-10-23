<%-- 
    Document   : web
    Created on : Sep 30, 2020, 2:48:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title><dec:title default="Online-Test" /></title>
        <%@ include file="/common/teacher/meta.jsp"%>
        <%@ include file="/common/teacher/style-link.jsp"%>
        
    </head>
    <body id="page-top" class="sb-nav-fixed">
        <%@ include file="/common/web/mesage.jsp"%>
        <%@ include file="/common/teacher/header.jsp"%>
        <div id="layoutSidenav">
            <%@ include file="/common/teacher/navbar.jsp"%>
            <div id="layoutSidenav_content">
                <main>
                   <dec:body /> 
                </main>
                <%@ include file="/common/teacher/footer.jsp"%>
            </div>
        </div>
        <%@ include file="/common/teacher/javascipt.jsp"%>
    </body>
</html>
