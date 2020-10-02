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
            <div class="col-lg-4 offset-lg-4">
                <div class="card shadow-lg border-0  ">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                    <div class="card-body">
                      <!--  <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong>Holy guacamole!</strong> You should check in on some of those fields below.
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div> -->
                        <form action="<c:url value='j_spring_security_check' />" method='POST'>
                            <div class="form-group">
                                <label class="small mb-1" for="inputUserName">UserName</label>
                                <input class="form-control py-4" id="inputUserName" name="j_username" type="text" placeholder="Enter email address" />
                            </div>
                            <div class="form-group">
                                <label class="small mb-1" for="inputPassword">Password</label>
                                <input class="form-control py-4" id="inputPassword" type="password" name="j_password" placeholder="Enter password" />
                            </div>
                            <div class="form-group">
                                <div class="custom-control custom-checkbox">
                                    <input class="custom-control-input" id="rememberPasswordCheck" type="checkbox" />
                                    <label class="custom-control-label" for="rememberPasswordCheck">Remember password</label>
                                </div>
                            </div>
                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                <a class="small" href="password.html">Forgot Password?</a>
                                <!-- <a class="btn btn-primary" href="index.html">Login</a> -->
                                <input type="submit" class="btn btn-primary" value="Login"/>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <div class="small"><a href="register.html">Need an account? Sign up!</a></div>
                    </div>
                </div>
            </div>
            <!-- end login -->
        </div>
    </div>
</section>