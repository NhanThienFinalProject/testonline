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
                        <h1 >Spring Database Authentication</h1>

                        <div id="login-box">

                            <h2>Login with Username and Password</h2>

                            <c:if test="${not empty error}">
                                <div class="error">${error}</div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="msg">${msg}</div>
                            </c:if>
                            <div onload='document.loginForm.username.focus();'></div>
                            <form name='loginForm'
                                  action="<c:url value='/j_spring_security_check' />" method='POST'>
                                <table>
                                    <tr>
                                        <td>User:</td>
                                        <td><input type='text' name='username'></td>
                                    </tr>
                                    <tr>
                                        <td>Password:</td>
                                        <td><input type='password' name='password' /></td>
                                    </tr>
                                    <tr>
                                        <td colspan='2'><input name="submit" type="submit"
                                                               value="submit" /></td>
                                    </tr>
                                </table>

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />

                            </form>
                        </div>
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
