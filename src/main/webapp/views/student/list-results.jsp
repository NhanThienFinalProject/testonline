<%-- 
    Document   : list-results
    Created on : Oct 7, 2020, 9:03:26 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>

<div class="card-header">
    <i class="fas fa-table mr-1"></i>
    List of Exams
</div>
<div class="card-body">
    <div class="table-responsive">
        <table class="table table-bordered" id="dataTableu" width="100%" cellspacing="0">
            <thead>
                <tr>
                    <th>Exam Name</th>
                    <th>Time Start</th>
                    <th>Time End</th>    
                    <th>Point</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listExamtitleOfCurrentStudent}" varStatus="seq1">
                    <tr>
                        <td><a target="_blank" href="result-student?examtitleId=${item.examtitleId}&examId=${item.exam.examId}&teacherId=${item.exam.user.userId}">${item.exam.content}</a></td>
                        <td>${item.exam.timeStart}</td>
                        <td>${item.exam.timeEnd}</td>
                        <td><c:forEach var="item2" items="${listResult}">
                                <c:if test="${item2.key eq item.examtitleId}">
                                    ${item2.value}
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js" type="text/javascript" charset="utf-8"  >
    
</script>
<script src="https://code.jquery.com/jquery-3.5.1.js" type="text/javascript" charset="utf-8"  ></script>

<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"  ></script>
<script   type="text/javascript" charset="utf-8"  >
   
    $(document).ready(function () {
        $('#dataTableu').DataTable({
      "order": [[ 1, "desc" ]]
      });
        $('.dataTables_length').addClass('bs-select');
      });
</script>