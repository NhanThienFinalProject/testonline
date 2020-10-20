<%-- 
    Document   : detailexam
    Created on : Oct 6, 2020, 11:44:23 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-10 offset-1">
            <ul class="list-group">
                <li class="list-group-item active mt-3">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9">
                                <c:if test="${exam != null}">Exam: <c:out value="${exam.content}"/>
                                </div>
                                <div class="col-md-3">
                                    <a href="teacher-preparing-sendmail?examId=${exam.examId}&link-exam=${linkExam}"><button class="btn btn-info" type="button" >Send password <i class="fas fa-paper-plane"></i></button></a>
                                </div>
                                </c:if><c:if test="${exam == null}">Exam is invalid</c:if>
                            </div>
                        </div>
                    </li>
                <c:if test="${exam != null}">
                    <li class="list-group-item">Status:
                        <c:choose>
                            <c:when test="${exam.status == 0}">
                                Not Active
                            </c:when>
                            <c:when test="${exam.status == 1}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Unknow
                            </c:otherwise>
                        </c:choose>

                    </li>
                    <li class="list-group-item">Password: <c:out value="**********"/></li>
                    <li class="list-group-item">Time-Start: <c:out value="${exam.timeStart}"/></li>
                    <li class="list-group-item">Time-End: <c:out value="${exam.timeEnd}"/></li>
                    <li class="list-group-item">Point: <c:out value="${exam.pointLadder}"/></li>
                    <li class="list-group-item">Max-Student: <c:out value="${exam.maxStudent}"/></li>
                    <li class="list-group-item">Create-Date: <c:out value="${exam.createDate}"/></li>
                    <div>
                        <li class="list-group-item">Link:
                            <button title="Refresh" onclick="return refreshLinkExam()" style="border: none;background-color: white"><i style="color: gray" class="fa fa-recycle" aria-hidden="true" ></i></button>
                            <input id="link" style="width: 100%; border-color: lightgray; border-radius: 4px" value="${linkExam}"></input>
                            <input id="preventiveLink" hidden="true" value="${linkExam}"></input>
                        </li>
                    </div>
                </c:if>
            </ul>
        </div>
    </div>
    <c:if test="${exam != null}">
        <div class="row">
            <div class="col-10 offset-1">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-sm table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Content</th>
                                    <th>Hinte</th>
                                    <th>Details</th>        
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty listQuestionRD}">
                                    <tr>
                                        <td colspan="4">
                                            <p class="text-danger text-center"><i>Bạn chưa có question nào trong đề thi vui lòng chọn câu hỏi và <b>Add</b> vào Exam của mình</i></p>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach var="item" items="${listQuestionRD}">
                                    <tr>
                                        <td>${item.questionQR.category.categoryName}</td>
                                        <td>${item.questionQR.content}</td>
                                        <td>${item.questionQR.hinte}</td>
                                        <td class="btn-group"><a class="btn btn-sm btn-info" href="teacher-question-details?questionId=${item.questionQR.questionId}">Details</a><a class="btn btn-sm btn-warning" href="teacher-question-random-delete?questionRDId=${item.questionRandom}&examIdPR=${exam.examId}">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- test -->
        <div class="row ">
            <div class="col-10 offset-1">
                <div class="card bg-info text-white">
                    <div class="card-body">Add Question to Exam</div>
                </div>
            </div>
            <div class="col-10 offset-1">
                <div id="accordion">
                    <!-- for -->
                    <c:forEach var="category" items="${listCategory}">
                        <div class="card">
                            <div class="card-header">
                                <a class="card-link" data-toggle="collapse" href="#collapse<c:out value="${category.categoryId}"/>">
                                    <c:out value="${category.categoryName}"/> <i class="fa fa-angle-double-down"></i>
                                </a>
                            </div>
                            <div id="collapse<c:out value="${category.categoryId}"/>" class="collapse show" data-parent="#accordion">
                                <div class="card-body">
                                    <table class="table table-sm table-bordered">
                                        <thead>
                                            <tr>
                                                <th ><h6>✔</h6></th>
                                                <th>Content</th>
                                                <th>True Answer</th>
                                            </tr>
                                        </thead>
                                        <form action="saveQuestionRandom" method="POST" name="">
                                            <tfoot>
                                                <tr>
                                                    <th colspan="3"><h6><button class="btn btn-sm btn-success">Add to Exam</button></h6></th>                                       
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                            <input type="hidden" name="examIdPR" value="<c:out value="${exam.examId}"/>"/>
                                            <c:forEach var="question" items="${category.listQuestion}">
                                                <tr>
                                                    <td><input type="checkbox" value="<c:out value="${question.questionId}"/>" name="questionIdPR"/></td>
                                                    <td><c:out value="${question.content}"/></td>
                                                    <td><c:out value="${question.questionId}"/></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </form>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- endfor -->


                </div>
            </div>
        </div>     
    </c:if>
</div>
<script>
    function refreshLinkExam() {
        var preventiveLink = document.getElementById("preventiveLink").value;
        document.getElementById("link").value = preventiveLink;
    }
</script>
