<%-- 
    Document   : exam-form
    Created on : Oct 3, 2020, 2:43:50 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<div class="container-fluid">
    <h1 class="mt-3">Create Test</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item"><a href="index.html">Working place</a></li>
        <li class="breadcrumb-item active">Create Exam</li>
    </ol>
    <div class="contact-form">
      
            <form:form  action="saveExam" method="POST" modelAttribute="examModel" cssClass="form-row border font-weight-bold">
            <div class= "col-md-8 offset-md-2">
                <div class="form-group">
                    <label class="control-label col-sm-2">Test's Content</label>
                    <div class="col-sm-11">
                        <form:textarea path="content" cssClass="form-control" rows="2" cols="12"/>
                    </div>
                </div>
            </div>
            <div class= "col-md-4 offset-md-2">
                <div class="form-group">
                    <div class="col-sm-10">
                        <form:input path="maxStudent" type="number" cssClass="form-control " placeholder="Enter Max Student"/>
                        
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <div class="col-sm-10">
                        <form:input path="pointLadder" type="number" cssClass="form-control " placeholder="Point 10 or 100 or ..."/>
                    </div>
                </div>
            </div>
            <div class= "col-md-4 offset-md-2">
                <div class="form-group">
                    <label class="control-label col-sm-4" >Time Start</label>
                    <div class="col-sm-10">
                        <!-- type="datetime-local" -->
                        <form:input path="timeStart" type="datetime-local"  cssClass="form-control " placeholder="Time Start Exam"/>
                    </div>
                </div>
            </div>
            <div class= "col-md-4 ">
                <div class="form-group">
                    <label class="control-label col-sm-4">Time End</label>
                    <div class="col-sm-10">
                       <form:input path="timeEnd" type="datetime-local" data-date-format="yyyy-MM-dd HH:mm:ss"  cssClass="form-control " placeholder="Time End Exam"/>
                    </div>
                </div>
            </div>
            <div class="col-md-8 offset-md-2">
                <div class="form-group">
                    <div class="col-sm-11">
                       <form:input path="password" type="text" cssClass="form-control "  placeholder="Enter password exam"/>
                    </div>
                </div>
            </div>
            <div class="col-md-8 offset-md-2">
                <div class="form-group">
                    <div class="offset-md-4 col-md-4">
                        <form:button type="submit" class="btn btn-success" >Create Exam Now</form:button>
                    </div>
                </div>
            </div>
            </form:form>
  
    </div>
    <div class="col-md-10 offset-md-1">
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table mr-1"></i>
                List of questions
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Content</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Number Student</th>
                                <th>Point</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                               <th>ID</th>
                                <th>Content</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Number Student</th>
                                <th>Point</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="exam" items="${listExam}">
                            <tr>
                                <td><c:out value="${exam.examId}"/></td>
                                <td><c:out value="${exam.content}"/></td>
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

