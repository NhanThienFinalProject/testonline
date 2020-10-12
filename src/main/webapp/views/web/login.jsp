<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<section class="content-section bg-light" id="about">
    <div class="container text-left">
        <div class="row">
            <!-- login -->
            <div class="col-lg-4 offset-lg-4">
                <div class="card shadow-lg border-0  ">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                    <div class="card-body">                     
                        <div id="login-box">
                            <c:if test="${not empty error}">
                                <div class="error">${error}</div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="msg">${msg}</div>
                            </c:if>
                            <div onload='document.loginForm.username.focus();'></div>
                            <form name='loginForm'
                                  action="<c:url value='/j_spring_security_check' />" method='POST'>
                                <div class="form-group">
                                    <label class="small mb-1" for="inputUserName">UserName</label>
                                    <input class="form-control py-4" type='text' name='username'>
                                </div>
                                <div class="form-group">
                                    <label class="small mb-1" for="inputPassword">Password</label>
                                    <input class="form-control py-4" type='password' name='password' />
                                </div>
                                <div class="form-group">
                                    <div class="custom-control custom-checkbox">
                                        <input class="custom-control-input" id="rememberPasswordCheck" type="checkbox" />
                                        <label class="custom-control-label" for="rememberPasswordCheck">Remember password</label>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                    <a class="small" href="#">Forgot Password?</a>
                                    <!-- <a class="btn btn-primary" href="index.html">Login</a> -->
                                    <input type="submit" class="btn btn-primary" value="Login"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card-footer text-center">
                        <div class="small"><a href="form-register">Need an account? Sign up!</a></div>
                    </div>
                </div>
            </div>
                               
            <!-- end login -->
        </div>
    </div>
</section>               
