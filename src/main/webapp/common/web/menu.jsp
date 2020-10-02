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
            <li class="sidebar-brand">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <i class="fa fa-user-circle mt-1" style="font-size:24px;color:#fff"></i> <span class="text-white mb-1"><b> ${pageContext.request.userPrincipal.name}</b></span><br>
                
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <h2 class="text-uppercase text-white"><b>ONLINE TEST</b></h2>
            </c:if>
            </li>
            <li class="sidebar-nav-item">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <a href="<c:url value="form-login"/>" class="btn btn-sm btn-success text-white">Login</a>
            </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">                
                <a href="javascript:formSubmit()" onclick="formSubmit()" class="btn btn-sm btn-warning text-white">Log-Out</a>
            </c:if>
            </li>
            <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#about">About</a>
            </li>
            <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#services">Services</a>
            </li>
            <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#portfolio">Portfolio</a>
            </li>
            <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#contact">Contact</a>
            </li>
        </ul>
    </nav>