<%-- 
    Document   : register
    Created on : Sep 30, 2020, 3:20:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content-section bg-light" id="about">
    <div class="container text-left">
        <div class="row">
            <!-- login -->
            <div class="col-lg-6 offset-lg-3">
                <div class="card shadow-lg border-0 rounded-lg mt-0">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                    <div class="card-body">
                        <form action="#123" class="needs-validation" novalidate>
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputFirstName">First Name</label>
                                        <input class="form-control py-3" id="inputFirstName" type="text" placeholder="Enter first name" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputLastName">Last Name</label>
                                        <input class="form-control py-3" id="inputLastName" type="text" placeholder="Enter last name" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputCity">City</label>
                                        <input class="form-control py-3" id="inputCity" type="text" placeholder="Enter city" />
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputRole">Bạn đang là</label>
                                        <select class="form-control" id="inputRole" >
                                            <option value="" disabled selected hidden >Select</option>
                                            option
                                            <option value="student">Student</option>
                                            <option value="teacher">Teacher</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputPhoneNumber">Phone Number</label>
                                        <input class="form-control py-3" id="inputPhoneNumber" type="text" placeholder="Enter phone number" />
                                    </div>
                                </div>
                            </div>
                            <!-- <div class="form-group">
                                <label class="small mb-1" for="inputEmailAddress">Email</label>
                                <input class="form-control py-3" id="inputEmailAddress" type="email" aria-describedby="emailHelp" placeholder="Enter email address" />
                            </div> -->
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputEmail">Email</label>
                                        <input class="form-control py-3" id="inputEmail" type="text" placeholder="Enter email" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputUserName">Username</label>
                                        <input class="form-control py-3" id="inputUserName" type="text" placeholder="Enter username" />
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
                                        <input class="form-control py-3" id="inputConfirmPassword" type="password" placeholder="Confirm password" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mt-4 mb-0">
                                <input class="btn btn-primary btn-block" type="submit" value="Create account">
                                </input>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <div class="small"><a href="login.html">Have an account? Go to login</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>