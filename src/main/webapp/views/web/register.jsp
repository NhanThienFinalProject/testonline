<%-- 
    Document   : register
    Created on : Sep 30, 2020, 3:20:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<section class="content-section bg-light" id="about">
    <div class="container text-left">
        <div class="row">
            <!-- login -->
            <div class="col-lg-6 offset-lg-3">
                <div class="card shadow-lg border-0 rounded-lg mt-0">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                    <div class="card-body">
                        <form:form action="form-save"  method="POST" modelAttribute="newUser" onsubmit="return validateForm()">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputFirstName">First Name</label>
                                        <form:input path="firstName" class="form-control py-3" id="inputFirstName" type="text" placeholder="Enter first name" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputLastName">Last Name</label>
                                        <form:input path="lastName" class="form-control py-3" id="inputLastName" type="text" placeholder="Enter last name" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputCity">City</label>
                                        <form:input path="city" class="form-control py-3" id="inputCity" type="text" placeholder="Enter city" />
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputRole">Career</label>
                                        <form:select path="role.roleId" class="form-control" id="inputRole" >
                                            <form:option value="" disabled="TRUE" selected="TRUE" hidden="TRUE">Select</form:option>
                                            <form:option value="student">Student</form:option>
                                            <form:option value="teacher">Teacher</form:option>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputPhoneNumber">Phone Number</label>
                                        <form:input path="phoneNumber" class="form-control py-3" id="inputPhoneNumber" type="text" placeholder="Enter phone number" />
                                    </div>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputEmail">Email</label>
                                        <form:input path="email" class="form-control py-3" id="inputEmail" type="text" placeholder="Enter email" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputUserName">Username</label>
                                        <form:input path="userName" class="form-control py-3" id="inputUserName" type="text" placeholder="Enter username" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputPassword">Password</label>
                                        <input class="form-control py-3" id="inputPassword" type="password" placeholder="Enter password" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputConfirmPassword">Confirm Password</label>
                                        <form:input path="password" class="form-control py-3" id="inputConfirmPassword" type="password" placeholder="Confirm password" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mt-4 mb-0">
                                <form:button class="btn btn-primary btn-block" >Create Account</form:button>
                                </div>
                        </form:form>
                    </div>
                    <div class="card-footer text-center">
                        <div class="small"><a href="form-login">Have an account? Go to login</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function validateForm() {
        var inputValue = [];
        var password = document.getElementById("inputPassword").value;
        var confirmPassword = document.getElementById("inputConfirmPassword").value;
        inputValue.push(document.getElementById("inputFirstName").value);
        inputValue.push(document.getElementById("inputLastName").value);
        inputValue.push(document.getElementById("inputCity").value);
        inputValue.push(document.getElementById("inputRole").value);
        inputValue.push(document.getElementById("inputPhoneNumber").value);
        inputValue.push(document.getElementById("inputEmail").value);
        inputValue.push(document.getElementById("inputUserName").value);
        inputValue.push(document.getElementById("inputPassword").value);
        inputValue.push(document.getElementById("inputConfirmPassword").value);
        inputValue.forEach(filter);
        var check;
        function filter(value) {
            if (value === "" || value === null) {
                check = 1;
            }
            return check;
        }
        ;
        if (check === 1) {
            alert("You missed something?");
            return false;
        }
        if (password !== confirmPassword) {
            alert("Password not the same!");
            return false;
        }
    }
</script>