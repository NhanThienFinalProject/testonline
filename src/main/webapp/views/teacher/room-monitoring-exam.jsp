<%-- 
    Document   : room-monitoring-exam
    Created on : Oct 18, 2020, 8:07:02 PM
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
        <div class="data-table-area mg-b-15" >
            <div class="container-fluid" >
                <div class="row" >
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="sparkline13-list">
                            <div class="sparkline13-hd">
                                <c:choose>
                                    <c:when test="${requiredExam.timeStart.getMinute() == 0}">
                                        <c:set var="minuteStart" value="00"/>
                                    </c:when>
                                    <c:when test="${requiredExam.timeStart.getMinute() < 10}">
                                        <c:set var="minuteStart" value="0${requiredExam.timeStart.getMinute()}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="minuteStart" value="${requiredExam.timeStart.getMinute()}"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${requiredExam.timeEnd.getMinute() == 0}">
                                        <c:set var="minuteEnd" value="00"/>
                                    </c:when>
                                    <c:when test="${requiredExam.timeEnd.getMinute() < 10}">
                                        <c:set var="minuteEnd" value="0${requiredExam.timeEnd.getMinute()}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="minuteEnd" value="${requiredExam.timeEnd.getMinute()}"/>
                                    </c:otherwise>
                                </c:choose>
                                <div class="main-sparkline13-hd">
                                    <h1><span><i class="fas fa-circle" style="color:#3ADF00;width: 15px;"></i></span> <c:out value="${requiredExam.content}" /></h1>
                                    <h6><c:out value="${requiredExam.timeStart.getHour()}" />:<c:out value="${minuteStart}" /> - <c:out value="${requiredExam.timeEnd.getHour()}" />:<c:out value="${minuteEnd}" />  <c:out value="${requiredExam.timeStart.getDayOfWeek()}" /> , <c:out value="${requiredExam.timeStart.getDayOfMonth()}" />  <c:out value="${requiredExam.timeStart.getMonth()}" />  <c:out value="${requiredExam.timeStart.getYear()}" />.</h6>
                                    <h3 id="MyClockDisplay" class="clock" onload="showTime()"></h3>
                                </div>
                            </div>
                            <div class="sparkline13-graph">
                                <div class="datatable-dashv1-list custom-datatable-overright">
                                    <table id="table" data-toggle="table" data-pagination="false" data-search="true" data-show-columns="false" data-show-pagination-switch="false" data-show-refresh="false" data-key-events="false" data-show-toggle="false" data-resizable="false" data-cookie="true"
                                           data-cookie-id-table="saveId" data-show-export="false" data-click-to-select="false" data-toolbar="#toolbar">
                                        <thead>
                                            <tr>
                                                <th data-field="id">ID</th>
                                                <th data-field="name" data-editable="true">Name</th>
                                                <th data-field="email" data-editable="true">Email</th>
                                                <th data-field="phone" data-editable="true">Phone</th>
                                                <th data-field="complete">Completed</th>
                                                <th data-field="task" data-editable="true">Task</th>
                                                <th data-field="action">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="loadStudent">
                                        </tbody>
                                    </table>
                                    <hr>
                                    <div>
                                        <button onclick="setIntervalFunction()" type="button" class="btn btn-dark btn-block"><b>Load room</b></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" value="${requiredExam.examId}" id="requiredExam"/>
        <input type="hidden" value="${requiredExam.timeEnd.getHour()}" id="hour"/>
        <input type="hidden" value="${requiredExam.timeEnd.getMinute()}" id="minute"/>
        <script type="text/javascript" charset="utf-8" async defer>
            var intervalTime;
            var examId = document.getElementById("requiredExam").value;
            function setIntervalFunction() {
                getListStudentDoingExam();
                intervalTime = setInterval(getListStudentDoingExam, 30000);
            }
            // load data
            function getListStudentDoingExam() {
                $.ajax({
                    // URL gửi data
                    url: window.location.origin+'/NationalTestOnline/api-monitor-exam/' + examId,
                    // type phương thức gửi get POST DELETE PUT
                    type: 'GET',
                    // Dữ liệu chuyển kiểu JSON
                    //contentType: 'application/json',
                    // đang là scriptObject phải có bộ chuyển sang json như này
                    //data: JSON.stringify(data),
                    //server trả về 1 json cho clien
                    dataType: 'json',
                    // thành công sẽ chạy cái này: check result
                    success: function (result) {
                        document.getElementById("loadStudent").innerHTML = '';
                        var listStudent = document.getElementById("loadStudent").innerHTML;
                        // set view
                        for (i = 0; i < result.length; i++) {
                            var numberOfCompleteQA = 0;
                            for (j = 0; j < result[i].listQuestionOfExamtitle.length; j++) {
                                if ( result[i].listQuestionOfExamtitle[j].resultAnswerId !== 0) {
                                    numberOfCompleteQA++;
                                    console.log("abc"+result[i].listQuestionOfExamtitle[j].resultAnswerId);
                                }
                            }
                            listStudent = listStudent + '<tr>' +
                                    '<td>' + result[i].student.userId + '</td>' +
                                    '<td>' + result[i].student.firstName + ' ' + result[i].student.lastName + '</td>' +
                                    '<td>' + result[i].student.email + '</td>' +
                                    '<td>' + result[i].student.phoneNumber + '</td>' +
                                    '<td class="datatable-ct"><span class="pie">' + numberOfCompleteQA + '/' + result[i].listQuestionOfExamtitle.length + '</span></td>' +
                                    '<td>' + numberOfCompleteQA / result[i].listQuestionOfExamtitle.length * 100 + '%</td>' +
                                    '<td class="datatable-ct"><i class="fa fa-check"></i></td>' +
                                    '</tr>';
                        }
                        //view question
                        $("#loadStudent").html(listStudent);


                    },
                    // thất bại sẽ chạy cái này:check error
                    error: function (error) {
                        console.log("error");
                    }
                });
            }
            var hourEnd = document.getElementById("hour").value;
            var minuteEnd = document.getElementById("minute").value;
            function showTime() {
                var date = new Date();
                var h = date.getHours(); // 0 - 23
                var m = date.getMinutes(); // 0 - 59
                var s = date.getSeconds(); // 0 - 59
                var session = "AM";

                if (h === 0) {
                    h = 12;
                }

                if (h > 12) {
                    session = "PM";
                }

                h = (h < 10) ? "0" + h : h;
                m = (m < 10) ? "0" + m : m;
                s = (s < 10) ? "0" + s : s;


                var time = h + ":" + m + ":" + s + " " + session;
                document.getElementById("MyClockDisplay").innerText = time;
                document.getElementById("MyClockDisplay").textContent = time;

                if (h > hourEnd || m >= minuteEnd && h >= hourEnd) {
                    window.location.href = "http://localhost:8080/NationalTestOnline/teacher-result-exam?examId=" + examId;
                }

                setTimeout(showTime, 1000);
            }

            showTime();
        </script>
    </c:otherwise>
</c:choose>