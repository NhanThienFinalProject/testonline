<%-- 
    Document   : menu
    Created on : Sep 30, 2020, 9:27:11 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<!-- Navigation -->
<a class="menu-toggle rounded" href="#">
    <i class="fas fa-bars"></i>
</a>
<nav id="sidebar-wrapper">

    <form action="<c:url value='/j_spring_security_logout' />" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
    <!-- security logout  -->


    <!-- For login user -->


    <!-- security logout -->
    <ul class="sidebar-nav">
        <c:choose>
            <c:when test="${pageContext.request.isUserInRole('student')}">
                <li class="sidebar-nav-item p-3">
                        <i class="fa fa-user-circle mt-1" style="font-size:16px;color:#fff"></i> <span class="text-white mb-1">Hi student! <b>${pageContext.request.userPrincipal.name}</b></span><br> 
                </li>
                <li class="sidebar-nav-item">
                    <a class="js-scroll-trigger text-center text-uppercase" href="showhome">Home</a>
                </li>
                <li class="sidebar-nav-item">
                    <a class="js-scroll-trigger" href="waiting-exam">Exam is waiting</a>
                </li>
                <li class="sidebar-nav-item">
                    <a class="js-scroll-trigger" href="student-list-result">Check result exam</a>
                </li>
                <li class="sidebar-nav-item">
                    <a class="js-scroll-trigger" href="infomation-user">Personal Information</a>
                </li>
                <li class="sidebar-nav-item pl-2 pr-2">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">                
                        <a href="javascript:formSubmit()" onclick="formSubmit()" class="btn btn-sm btn-warning text-white text-uppercase text-md">Log-Out</a>
                    </c:if>
                </li>
            </c:when>
            <c:when test="${pageContext.request.isUserInRole('teacher')}">
                <li class="sidebar-nav-item p-3">
                        <i class="fa fa-user-circle mt-1" style="font-size:16px;color:#fff"></i> <span class="text-white mb-1">Hi teacher! <b>${pageContext.request.userPrincipal.name}</b></span><br> 
                </li>
                <li class="sidebar-nav-item">
                    <a class="js-scroll-trigger" href="teacher-home">Exam Manager</a>
                </li>
                <li class="sidebar-nav-item pl-2 pr-2">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">                
                        <a href="javascript:formSubmit()" onclick="formSubmit()" class="btn btn-sm btn-warning text-white text-uppercase text-md">Log-Out</a>
                    </c:if>
                </li>
            </c:when>
            <c:otherwise>
                <li class="sidebar-nav-item p-3 mt-2">
                    <h2 class="text-uppercase text-white mt-5 text-center"><b>ONLINE TEST</b></h2>
                </li>
                <li class="sidebar-nav-item pb-5 pl-2 pr-2">
                    <a href="<c:url value="form-login"/>" class="btn btn-sm btn-warning text-white text-uppercase text-md">Login</a>
                </li>
                
            </c:otherwise>
        </c:choose>
     
    </ul>
</nav>

