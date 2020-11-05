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

        <form:form  action="teacher-save-exam" method="POST" onsubmit="return show()" modelAttribute="examModel" cssClass="form-row border font-weight-bold">
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
                    <label class="control-label col-sm-6">Max students</label>
                    <div class="col-sm-10">
                        <form:input path="maxStudent" type="number" cssClass="form-control " placeholder="Enter Max Student"/>

                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label class="control-label col-sm-4">Point</label>
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
                        <input id="timeStart" type="datetime-local"  class="form-control " placeholder="Time Start Exam"/>
                        <form:input path="timeStart" id="timeStartClone" type="hidden"/>
                    </div>
                </div>
            </div>
            <div class= "col-md-4 ">
                <div class="form-group">
                    <label class="control-label col-sm-4">Time End</label>
                    <div class="col-sm-10">
                        <input id="timeEnd" type="datetime-local"  class="form-control " placeholder="Time End Exam"/>
                        <form:input path="timeEnd" id="timeEndClone" type="hidden"/>
                    </div>
                </div>
            </div>
            <div class="col-md-8 offset-md-2">
                <div class="form-group">
                    <label class="control-label col-sm-4">Password</label>
                    <div class="col-sm-11">
                        <form:input path="password" type="password" cssClass="form-control "  placeholder="Enter password exam"/>
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
                    <table class="table table-bordered" id="dataTablex"   width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Select</th>
                                <th>Content</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Number Student</th>
                                <th>Point</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="exam" items="${listExam}">
                                <tr>
                                    <td><a href="<c:url value="teacher-detail-exam?examid=${exam.examId}"/>" target="_Blank"><i class="far fa-sticky-note"></i></a></td>
                                    <td><a href="<c:url value="teacher-detail-exam?examid=${exam.examId}"/>" target="_Blank"><c:out value="${exam.content}"/></a></td>
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
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js" type="text/javascript" charset="utf-8"  >
    
</script>
<script src="https://code.jquery.com/jquery-3.5.1.js" type="text/javascript" charset="utf-8"  ></script>

<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"  ></script>
<script   type="text/javascript" charset="utf-8"  >
   
    $(document).ready(function () {
        $('#dataTablex').DataTable({
      "order": [[ 0, "desc" ]]
      });
        $('.dataTables_length').addClass('bs-select');
      });
</script>
<script>
    function show() {
        var timeStartString = document.getElementById("timeStart").value;
        var timeEndString = document.getElementById("timeEnd").value;
        var timeStart = new  Date(timeStartString);
        var timeEnd = new  Date(timeEndString);
        document.getElementById("timeStartClone").value = parseString(timeStart);
        document.getElementById("timeEndClone").value = parseString(timeEnd);
        return true;
    }
    function parseString(datetime) {
        var year = datetime.getFullYear();
        var month = (datetime.getMonth()+1 < 10) ? '0' + (datetime.getMonth()+1) : (datetime.getMonth()+1);
        var day = (datetime.getDate() < 10) ? '0' + datetime.getDate() : datetime.getDate();
        var hour = (datetime.getHours() < 10) ? '0' + datetime.getHours() : datetime.getHours();
        var minute = (datetime.getMinutes() < 10) ? '0' + datetime.getMinutes() : datetime.getMinutes();
        var second = (datetime.getSeconds() < 10) ? '0' + datetime.getSeconds() : datetime.getSeconds();
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    }
</script>
