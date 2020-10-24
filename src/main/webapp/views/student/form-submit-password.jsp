<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>

<div class="masthead">
    <div class="masthead-bg"></div>
    <div class="container-fluid h-100">
        <div class="row h-100">
            <div class="col-12 my-auto">
                <div class="masthead-content text-white py-5 py-md-0 ">
                    <h2 class="text-left text-sm">Exam Content: <c:out value="${exam.content}"/></h2>
                    <h4 class="text-left text-sm">Teacher: <c:out value="${exam.user.fullName}"/></h4>
                    <h4 class="text-left text-sm">Email teacher: <c:out value="${exam.user.email}"/></h4>
                    <h4 class="text-left text-sm">Time start: <c:out value="${exam.timeStartString}"/></h4>
                    <h4 class="text-left text-sm">Time end: <c:out value="${exam.timeEndString}"/></h4>
                    <h4 class="text-left text-sm">Point: <c:out value="${exam.pointLadder}"/></h4>

                    <p class="mb-5">Your exam is in <strong>Private</strong> status, so to jojn it please sign up for updates using the form below!</p>
                    <c:if test="${stringExamId != null}">
                        <form action="student-submit-password-waitting-room" method="POST">
                            <input type="hidden" name="examId" value="<c:out value="${stringExamId}"/>"/>
                            <div class="input-group input-group-newsletter">
                                <input type="password" class="form-control" placeholder="Enter password..." name="password" aria-label="Enter password..." aria-describedby="submit-button" required="true">
                                <div class="input-group-append">
                                    <button class="btn btn-secondary" type="submit" id="submit-button">Submit</button>
                                </div>

                            </div>
                        </form>
                    </c:if>
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


