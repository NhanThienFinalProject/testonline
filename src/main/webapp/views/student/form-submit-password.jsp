<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>

<div class="masthead">
    <div class="masthead-bg"></div>
    <div class="container h-100">
        <div class="row h-100">
            <div class="col-12 my-auto">
                <div class="masthead-content text-white py-5 py-md-0">
                    <h1 class="mb-3">“Be Yourself, But Be Your Best Self”</h1>
                    <p class="mb-5">Your exam is in <strong>Private</strong> status, so to jojn it please sign up for updates using the form below!</p>
                    <form action="student-submit-password-waitting-room" method="POST">
                        <c:set value="${examId}" var="exam"></c:set>
                        <input hidden="true" name="examId" value="${examId}"/>
                        <input hidden="true" name="stringExamId" value="${stringExamId}"/>
                        <div class="input-group input-group-newsletter">
                            <c:if test="${message == null}">
                                <input type="password" class="form-control" placeholder="Enter password..." name="password" aria-label="Enter password..." aria-describedby="submit-button">
                                <div class="input-group-append">
                                    <button class="btn btn-secondary" type="submit" id="submit-button">Submit</button>
                                </div>
                            </c:if>
                            <c:if test="${message != null}">
                                <input style="border: 2px solid red" type="password" class="form-control" placeholder="Enter again..." name="password" aria-label="Enter password..." aria-describedby="submit-button">
                                <div class="input-group-append">
                                    <button class="btn btn-secondary" type="submit" id="submit-button">Submit</button>
                                </div>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="template/student/vendor/jquery/jquery.min.js"></script>
<script src="template/student/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="template/student/js/coming-soon.min.js"></script>


