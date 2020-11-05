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
                        <h5 class="text-danger col-12 text-center mt-3">Time left</h5>
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
                                <li class="list-group-item active"><b><c:out value="${fn:length(listExamTitleId)}"/> question</b></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 col-10 mx-auto border-left border" id="loadQuestion">
                    
                    <div class="row border-top pt-2 pb-2">
                        <div class="col-2 offset-1 btn">previous</div>
                        <div class="col-2 offset-6 btn">next</div>
                    </div>
                </div>
                <div class="col-lg-2 col-2 mx-auto">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Question</th>
                                <th>Your answer</th>
                            </tr>
                        </thead>
                        <tbody id="resultChooseAnswer">
                             
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <script type="text/javascript" charset="utf-8" async defer>
            var listQuestion = [<c:forEach var="item" items="${listExamTitleId}"> <c:out value="${item},"/></c:forEach>];
            var nextTo = 0;
            var examId = <c:out value="${examDetail.examId}"/>;
            var examtitleId = <c:out value="${examTitle.examtitleId}"/>;
            console.log('test length: ' + listQuestion.length);
            getQuestion(listQuestion[nextTo],nextTo+1);
            getAnswerStudent(examtitleId);
            //e.preventDefault();
             
            function  chooseAnswer(questionOfExamtitleID, resultAnswerId) {
                var data = {};
                var answer = questionOfExamtitleID + '#' + resultAnswerId;
                saveResult(answer);
                
                if(nextTo != listQuestion.length -1){
                     nextTo = nextTo+1;
                    getQuestion(listQuestion[nextTo],nextTo +1);
                   
                }else{
                     $("#loadQuestion").html('<div class="row mt-5 mb-5"> <div class="col-10 offset-1 text-center"><h2>You already finished your exam. Please wait untill time is up to check result!</h2></div> <div class="col-4 offset-4 text-center mt-5"><h3 class="btn btn-primary btn-sm float-left" onclick="getQuestion('+listQuestion[nextTo]+','+(nextTo+1)+')">Back</h3><h3 class="btn btn-outline-secondary btn-sm float-right disabled">Check your result</h3></div> </div>');
                }
                setTimeout(function(){
                   getAnswerStudent(examtitleId);
                }, 500);
            }
         //function nextQuestion() {
              //  i++;
              //  getQuestion(listQuestion[i]);
              //  if (!listQuestion.length == i)
                    
              //  }
//save result
            function saveResult(datax) {
                $.ajax({
                        // URL gửi data
                        url:window.location.origin+'/NationalTestOnline/api-dotest',
                         // type phương thức gửi get POST DELETE PUT
                        type: 'POST',
			// Dữ liệu chuyển kiểu JSON
                        contentType: 'application/json',
			// đang là scriptObject phải có bộ chuyển sang json như này
			data: JSON.stringify(datax),
			//server trả về 1 json cho clien
			dataType: 'json',
			// thành công sẽ chạy cái này: check result
                        success: function (result) {
                        // hiển thị câu hỏi tiếp theo và thông baos
                        console.log('save: ' + result);
                    },
                    // thất bại sẽ chạy cái này:check error
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
// load data
            function getQuestion(id,nextTo) {
                $.ajax({
                    // URL gửi data
                    url: window.location.origin+'/NationalTestOnline/api-dotest/' + id,
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
                        var next = '';
                        var previous = '';
                         
                            if(nextTo <= listQuestion.length){
                            next = '<div class="col-2 offset-6 btn" onclick="getQuestion('+listQuestion[nextTo]+','+ (nextTo + 1) +')">next</div>';
                            
                            }else{
                                next = '<div class="col-2 offset-6 btn"><p>next</p></div>';
                            }
                            if(nextTo >= 1){
                            previous = '<div class="col-2 offset-1 btn" onclick="getQuestion('+listQuestion[nextTo-2]+','+(nextTo -1)+')">previous</div>';
                            }else{
                              previous = '<div class="col-2 offset-1 btn"><p>previous</p></div>';  
                            }
                        
                        // set answer
                        for (i = 0; i < result.question.listAnswer.length; i++) {
                            var colorBtn = "btn-info";
                            if (result.question.listAnswer[i].answerId === result.resultAnswerId) {
                                    colorBtn = "btn-success";
                             }
                            listTextAnswer = listTextAnswer + '<div class="col-12"> <p class="btn '+colorBtn+'" onclick="chooseAnswer(' + result.questionOfExamtitleID + ',' + result.question.listAnswer[i].answerId + ')">' + result.question.listAnswer[i].answer + '</p> </div>';
                        }
                        //view question
                        var hinte = "";
                        if(result.question.hinte != null){
                            hinte = 'Hinte: <i>' + result.question.hinte +'</i>';
                        }
                        $("#loadQuestion").html('<div class="row p-3"> <div class="col-12 text-left border-bottom mb-3" > <h2 id="question"> Question '+nextTo +': ' + result.question.content + '</h2> <p id="hinte">'+hinte+'</p> </div> ' + listTextAnswer + ' </div> <div class="row border-top pt-2 pb-2"> '+previous+next+' </div>');


                    },
                    // thất bại sẽ chạy cái này:check error
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
            // get answer student
            function getAnswerStudent(examtitleId) {
                $.ajax({
                    // URL gửi data
                    url: window.location.origin+'/NationalTestOnline/api-answer/' + examtitleId,
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
                        var listChooseAnswer = "";
                        // set answer
                        for (i = 0; i < result.listQuestionOfExamtitle.length; i++) {
                         var   stringAnswer = "";
                            for (var j = 0; j < result.listQuestionOfExamtitle[i].question.listAnswer.length; j++) {
                                if(result.listQuestionOfExamtitle[i].resultAnswerId == result.listQuestionOfExamtitle[i].question.listAnswer[j].answerId ){
                                    listChooseAnswer = listChooseAnswer + '<tr><td><p class="btn btn-sm btn-info" onclick="getQuestion('+result.listQuestionOfExamtitle[i].questionOfExamtitleID+','+(i+1)+')">'+'Question '+(i+1)+'</p></td><td>'+result.listQuestionOfExamtitle[i].question.listAnswer[j].answer+'</td></tr>';
                                }else if ((result.listQuestionOfExamtitle[i].resultAnswerId == -1 && j == result.listQuestionOfExamtitle[i].question.listAnswer.length - 1)|| (result.listQuestionOfExamtitle[i].resultAnswerId == 0 && j == result.listQuestionOfExamtitle[i].question.listAnswer.length - 1)) {
                                    listChooseAnswer = listChooseAnswer + '<tr><td><p class="btn btn-sm btn-info" onclick="getQuestion('+result.listQuestionOfExamtitle[i].questionOfExamtitleID+','+(i+1)+')">'+'Question '+(i+1)+'</p></td><td></td></tr>';
                                }
                            }
                        }
                        //view question
                        $("#resultChooseAnswer").html(listChooseAnswer);
                        console.log('refecft');

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

            var deadline = new Date("<c:out value="${examDetail.timeEndString }"/>");
            console.log(deadline);
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
                    document.getElementById("finish").innerHTML = "Time's up";
                    document.getElementById("minute").innerHTML = '0';
                    document.getElementById("second").innerHTML = '0';
                    window.location.href = window.location.origin+'/NationalTestOnline/student-list-result';
                }
            }, 1000);
        </script> 
        <!-- end exam -->
</c:if>