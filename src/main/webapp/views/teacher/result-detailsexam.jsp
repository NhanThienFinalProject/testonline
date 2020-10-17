<%-- 
    Document   : result-exam
    Created on : Oct 12, 2020, 3:51:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
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
        <div class="container">
            <br>
            <h2><c:out value="${currentExam.content}"/></h2>
            <br>
            <div class="col-lg-12">
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-chart-bar mr-1"></i>
                        Chart Result
                    </div>
                    <div class="card-body"><canvas id="myBarChart" width="100%" height="20"></canvas></div>
                    <div class="card-footer small text-muted">Updated on <c:out value="${currentExam.timeEnd}"/></div>
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
    </c:otherwise>
</c:choose>
<c:set var="fasleScore" value="${0}"/>
<c:set var="D" value="${0}"/>
<c:set var="C" value="${0}"/>
<c:set var="B" value="${0}"/>
<c:set var="A" value="${0}"/>
<c:set var="Aplus" value="${0}"/>
<c:forEach var="result" items="${listResult}">
    <c:choose>
        <c:when test="${result.value >= currentExam.pointLadder*9/10}">
            <c:set var="Aplus" value="${Aplus + 1}"/>
        </c:when>
        <c:when test="${currentExam.pointLadder*8/10 <= result.value && result.value < currentExam.pointLadder*9/10}">
            <c:set var="A" value="${A + 1}"/>
        </c:when>
        <c:when test="${currentExam.pointLadder*7/10 <= result.value && result.value < currentExam.pointLadder*8/10}">
            <c:set var="B" value="${B + 1}"/>
        </c:when>
        <c:when test="${currentExam.pointLadder*6/10 <= result.value && result.value < currentExam.pointLadder*7/10}">
            <c:set var="C" value="${C + 1}"/>
        </c:when>
        <c:when test="${currentExam.pointLadder*5/10 <= result.value && result.value < currentExam.pointLadder*6/10}">
            <c:set var="D" value="${D + 1}"/>
        </c:when>
        <c:otherwise>
            <c:set var="falseScore" value="${falseScore + 1}"/>
        </c:otherwise>
    </c:choose>
</c:forEach>
<input type="hidden" value="${Aplus}" id="Aplus"/>
<input type="hidden" value="${A}" id="A"/>
<input type="hidden" value="${B}" id="B"/>
<input type="hidden" value="${C}" id="C"/>
<input type="hidden" value="${D}" id="D"/>
<input type="hidden" value="${falseScore}" id="falseScore"/>
    
    
