<%-- 
    Document   : home
    Created on : Sep 30, 2020, 9:33:27 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>

<!-- Exam -->
<c:choose>
    <c:when test="${message != null}">
        <div class="container">
            <div id="notfound">
                <div class="notfound">
                    <div class="notfound-404">
                        <h2><c:out value="${message}"/></h2>
                    </div>
                    <a href="http://localhost:8080/NationalTestOnline">Go TO Homepage</a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>

        <section class="content-section bg-light" id="about">
            <form action="teacher-sendmail" method="POST">
                <input type="hidden" name="examId" value="${requiredExam.examId}"/>
                <input type="hidden" name="link" value="${linkExam}"/>
                <div class="container">
                    <div class="page-header">
                        <h2> <c:out value="${requiredExam.content}"/></h2> 
                    </div>
                    <div class="form-row">
                        <div class="col-md-9">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table mr-1"></i>
                                    Your students
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <td><div class="form-check">
                                                            <input type="checkbox" onclick="selectAllOrNot()" class="form-check-input" id="selectAll">
                                                        </div>
                                                    </td>
                                                    <th>Name</th>
                                                    <th>Email</th>
                                                    <th>Phone number</th>       
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="student" items="${listStudentOfCurrentStudent}">
                                                    <tr>
                                                        <td><div class="form-check">
                                                                <input type="checkbox" name="exampleCheck1" class="form-check-input" id="exampleCheck1" value="${student.email}">
                                                            </div></td>
                                                        <td><c:out value="${student.firstName}"/> <c:out value="${student.lastName}"/></td>
                                                        <td><c:out value="${student.email}"/></td>
                                                        <td><c:out value="${student.phoneNumber}"/></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-secondary btn-lg btn-block"><i class="fas fa-paper-plane"></i></button>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <input type="text" id="email" name="email" class="form-control input-sm" maxlength="100" placeholder="... @gmail.com" />
                                <div class="input-group-append">
                                    <button class="btn btn-dark" onclick="return addEmail()" type="button" "><i class="fas fa-plus"></i></button>
                                </div>
                            </div>
                            <br>
                            <div id="message">
                            </div>
                            <table class="table" id="table">
                            </table>
                            <div id="hidden">
                            </div>
                        </div>
                    </div>
                </div>
            </form>     
            <script>
                function addEmail() {
                    var emailInput = document.getElementById("email").value;
                    if (checkValidEmail(emailInput)) {
                        var listEmail = document.getElementById("table").innerHTML;
                        document.getElementById("table").innerHTML = listEmail + "<tr class='alert'>" +
                                "<td>" + '<div class="form-check">' +
                                '<input type="checkbox" checked="return true" name="exampleCheck1" class="form-check-input" id="exampleCheck1" value="' + emailInput + '">' +
                                emailInput +'</div>'+
                                "</td>" +
                                "</tr>";
                        document.getElementById("email").value = "";
                        document.getElementById("email").style.borderColor = "gray";
                        document.getElementById("message").innerHTML = "";
                    } else {
                        document.getElementById("email").style.borderColor = "red";
                        document.getElementById("message").innerHTML = "<div class='alert alert-danger fade show alert-dismissible' role='alert'>" +
                                "<strong><i class='fa fa-warning' aria-hidden='true'></i></strong> You need to fill out <b>correct</b> email's format!" +
                                "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button>" +
                                "</div>";
                    }
                    return false;
                }
                function checkValidEmail(email) {
                    var position = email.search("@");
                    var check = email.slice(position);
                    if (check === "@gmail.com") {
                        return true;
                    }
                    return false;
                }
                function selectAllOrNot() {
                    var check = document.getElementById("selectAll");
                    if (check.checked === true) {
                        var singleCheck = document.getElementsByName("exampleCheck1");
                        for (var i = 0; i < singleCheck.length; i++) {
                            singleCheck[i].checked = true;
                        }
                    } else {
                        var singleCheck = document.getElementsByName("exampleCheck1");
                        for (var i = 0; i < singleCheck.length; i++) {
                            singleCheck[i].checked = false;
                        }
                    }
                }
            </script>
        </section>
    </c:otherwise>
</c:choose>