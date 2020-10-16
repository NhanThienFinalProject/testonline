<%-- 
    Document   : exam
    Created on : Oct 14, 2020, 1:45:17 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<c:if test="${examStatus == 'active'}">
    <!-- Exam -->
    <section class="content-section bg-light" id="about">
        <div class="container-fluid text-left">
            <div class="row">
                <div class="col-lg-3 col-12 mx-auto pt-lg-2">
                    <div class="row"> 
                        <h5 class="text-danger col-12 text-center mt-3">Thời gian còn lại</h5>
                        <div class="btn-group col-6 offset-3"> 
                            <span  class="btn btn-success" id="minute"></span><span class="btn btn-success" id="second"></span>    
                        </div> 
                        <h5 class="text-danger col-12 text-center mt-3" id="finish"></h5> 
                    </div> 
                    <div class="row">
                        <div class="col-lg-12 col-12 mx-auto">
                            <ul class="list-group">
                                <li class="list-group-item active text-center"><c:out value="${examDetail.content}"/></li>
                                <li class="list-group-item">teacher: <c:out value="${examDetail.user.fullName}"/></li>
                                <li class="list-group-item">Time Start: <c:out value="${examDetail.timeStart }"/></li>
                                <li class="list-group-item">Time End: <c:out value="${examDetail.timeEnd }"/></li>
                                <li class="list-group-item">Point: <c:out value="${examDetail.pointLadder }"/></li>
                                <li class="list-group-item">Student: <c:out value="${userDetail.fullName }"/></li>
                                <li class="list-group-item">Username: <c:out value="${userDetail.userName }"/></li>
                                <li class="list-group-item">Email: <c:out value="${userDetail.email }"/></li>
                                <li class="list-group-item">[<c:out value="${fn:length(ListquestionId)}"/>] question</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 col-10 mx-auto border-left border" id="loadQuestion">
                    <div class="row p-3">
                        <div class="col-12 text-left border-bottom mb-3" >
                            <h2 id="question">Tính tổng 2 + 3 + 4 + 6 + 6 + 6= ?</h2>
                            <p id="hinte">Hinte: <i>Sử dụng máy tính bỏ túi để bấm</i></p>
                        </div>
                        <div class="col-12" id="answers">
                            <p class="btn btn-info" id="answer1" onclick="chooseAnswer(12, 21)">27</p>
                        </div>
                        <div class="col-12" id="answers">
                            <p class="btn btn-info" id="answer2">Hai mươi bảy</p>
                        </div>
                        <div class="col-12" id="answers">
                            <p class="btn btn-info" id="answer3">Hai 7</p>
                        </div>
                        <div class="col-12" id="answers">
                            <p class="btn btn-info" id="answer4">Hỏi chấm</p>
                        </div>
                    </div>
                    <div class="row border-top pt-2 pb-2">
                        <div class="col-2 offset-1 btn">previous</div>
                        <div class="col-2 offset-6 btn">next</div>
                    </div>
                </div>
                <div class="col-lg-2 col-2 mx-auto">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Câu</th>
                                <th>Đáp án</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>A</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>C</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>B</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <script type="text/javascript" charset="utf-8" async defer>
            var listQuestion = [<c:forEach var="item" items="${ListquestionId}"> <c:out value="${item},"/></c:forEach>];
            var i = 0;
            function nextQuestion() {
                getQuestion(listQuestion[i]);
                if (!listQuestion.length == i) 
                    i++;
            }

            function  chooseAnswer(questionOfExamTitleId, answerId) {
                var data = {};
                data["questionOfExamTitleId"] = questionOfExamTitleId;
                data["answerId"] = answerId;
                saveResult(data);
            }
            function nextQuestion() {

            }
//save result
            function saveResult(data) {
                $.ajax({
                    // URL gửi data
                    url: 'http://localhost:8080/NationalTestOnline/api-dotest',
                    // type phương thức gửi get POST DELETE PUT
                    type: 'POST',
                    // Dữ liệu chuyển kiểu JSON
                    contentType: 'application/json',
                    // đang là scriptObject phải có bộ chuyển sang json như này
                    data: JSON.stringify(data),
                    //server trả về 1 json cho clien
                    dataType: 'json',
                    // thành công sẽ chạy cái này: check result
                    success: function (result) {
                        // hiển thị câu hỏi tiếp theo và thông baos

                    },
                    // thất bại sẽ chạy cái này:check error
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
// load data
            function getQuestion(id) {
                $.ajax({
                    // URL gửi data
                    url: 'http://localhost:8080/NationalTestOnline/api-dotest/10',
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
                        var listTextAnswer = "";
                        // set answer
                        console.log("success" + result);
                        /*
                         for (i = 0; i <= 3;i++){
                         listTextAnswer = "<div class=\"col-12\"> <p class=\"btn btn-info\" onclick=\"chooseAnswer(" + result.questionOfExamtitleID + "," + result.question.listAnswer[i].answerId + ")\">" + result.question.listAnswer[i].answer + "</p> </div>";
                         }
                         //view question
                         $("#loadQuestion").html('<div class="row p-3"> <div class="col-12 text-left border-bottom mb-3" > <h2 id="question">' + result.question.content + '</h2> <p id="hinte">Hinte: <i>' + result.question.hinte + '</i></p> </div> ' + listTextAnswer + ' </div> <div class="row border-top pt-2 pb-2"> <div class="col-2 offset-1 btn">previous</div> <div class="col-2 offset-6 btn">next</div> </div>');
                         *              
                         */
                    },
                    // thất bại sẽ chạy cái này:check error
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
            </script>
            <script type="text/javascript" charset="utf-8" async defer>
                function changecolor(name) {
                    // body...
                    document.getElementById(name).style.background = "green";
                }
            </script>
        </section>
        <script>

            var deadline = new Date("Oct 15, 2020 12:53:25").getTime();
            var x = setInterval(function () {

                var now = new Date().getTime();
                var t = deadline - now;
                //var days = Math.floor(t / (1000*60*60*24)); 
                //var hours = Math.floor((t%(1000*60*60*24))/(1000 * 60 * 60)); 
                // 
                var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60)) + Math.floor((t % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) * 60 + Math.floor(t / (1000 * 60 * 60 * 24)) * 24 * 60;
                var seconds = Math.floor((t % (1000 * 60)) / 1000);
                document.getElementById("minute").innerHTML = minutes;
                document.getElementById("second").innerHTML = seconds;
                if (t < 0) {
                    clearInterval(x);
                    document.getElementById("finish").innerHTML = "Hết Giờ";
                    document.getElementById("minute").innerHTML = '0';
                    document.getElementById("second").innerHTML = '0';
                }
            }, 1000);
        </script> 
        <!-- end exam -->
</c:if>