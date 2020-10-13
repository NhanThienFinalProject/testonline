<%-- 
    Document   : result-exam
    Created on : Oct 12, 2020, 3:51:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<div class="container">
    <div class="col-lg-12">
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-chart-bar mr-1"></i>
                Chart Result
            </div>
            <div class="card-body"><canvas id="myBarChart" width="100%" height="20"></canvas></div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table mr-1"></i>
                List of attendees
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>SĐT</th>
                                <th>Point</th>        
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>SĐT</th>
                                <th>Point</th> 
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="examtitle" items="${listExamtitleOfExamIDCurrentTeacher}">
                                <tr>
                                    <th><a href="result-student?examtitleId=${examtitle.examtitleId}&examId=${examtitle.exam.examId}&teacherId=${examtitle.exam.user.userId}" target="_blank"><c:out value="${examtitle.student.lastName}"/> <c:out value="${examtitle.student.firstName}"/></a></th>
                                    <th><c:out value="${examtitle.student.email}"/></th>
                                    <th><c:out value="${examtitle.student.phoneNumber}"/></th>
                                    <td><c:forEach var="result" items="${listResult}">
                                            <c:if test="${result.key eq examtitle.examtitleId}">
                                                ${result.value}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
