<%-- 
    Document   : list-finished-exam
    Created on : Oct 14, 2020, 5:59:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<div class="container">
    <br>
    <h2>Completed exams</h2>
    <br>
    <div class="col-md-12">
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table mr-1"></i>
                List finished exam
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Content</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Number of attendees</th>        
                                <th>Point</th>        
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="exam" items="${listFinishedExamOfCurrentTeacher}">
                                <tr>
                                    <td><a target="_blank" href="teacher-result-exam?examId=${exam.examId}"><c:out value="${exam.content}"/></a></td>
                                    <td><c:out value="${exam.timeStart}"/></td>
                                    <td><c:out value="${exam.timeEnd}"/></td>
                                    <td><c:out value="${exam.maxStudent}"/></td>
                                    <td><c:out value="${exam.pointLadder}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>