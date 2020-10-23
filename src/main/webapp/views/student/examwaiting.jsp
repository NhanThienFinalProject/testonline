<%-- 
    Document   : menu
    Created on : Sep 30, 2020, 9:27:11 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<div class="row mt-5">
    <div class="col-6 offset-3">
        <h3 class="text-center text-uppercase">Danh Sách các bài thi sắp diễn ra</h3>
    </div>
</div>
<div class="row mt-1">
    <div class="col-4 offset-1">
        <div class="list-group" id="list-tab" role="tablist">
            <c:forEach var="exam" items="${listExam}">
                <a class="list-group-item list-group-item-action" id="list-<c:out value="${exam.examId}"/>-list" data-toggle="list" href="#list-<c:out value="${exam.examId}"/>" role="tab" aria-controls="1"><c:out value="${exam.content}"/></a>
            </c:forEach>
        </div>
    </div>
    <div class="col-7">
        <div class="tab-content" id="nav-tabContent">
            <c:forEach var="exam" items="${listExam}">
                <div class="tab-pane fade border" id="list-<c:out value="${exam.examId}"/>" role="tabpanel" aria-labelledby="list-<c:out value="${exam.examId}"/>-list">
                    <ul class="list-group">
                        <li class="list-group-item active">Exam Content: <c:out value="${exam.content}"/></li>
                        <li class="list-group-item">Teacher: <c:out value="${exam.user.fullName}"/></li>
                        <li class="list-group-item">Email teacher: <c:out value="${exam.user.email}"/></li>
                        <li class="list-group-item">Time start: <c:out value="${exam.timeStartString}"/></li>
                        <li class="list-group-item">Time end: <c:out value="${exam.timeEndString}"/></li>
                        <li class="list-group-item">Point: <c:out value="${exam.pointLadder}"/></li>
                        <li class="list-group-item"><a href="">Do test now</a> <c:out value="${exam.pointLadder}"/></li>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
