<%-- 
    Document   : waitting-room
    Created on : Oct 10, 2020, 12:06:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>

<div class="container" >
    <div class="row text-white mt-5 text-center card " style="background-color: rgba(0, 0, 0, 0.3);">
        <div class="col-12  text-uppercase mt-5"><h1 class="font-weight-bold display-2">wait</h1></div>
        <div class="col-6 offset-3 ">
            <h2 class="btn btn-group font-weight-bold" style="font-family: 'Times New Roman';"><span  class="btn btn-success" id="minute"></span><span class="btn btn-success" id="second"></span></h2>
        </div>
        <div class="col-12"><h5 class="text-success col-12 text-center mt-3" id="finish"></h5> </div>
        <div class="col-6 offset-3 " >
            <h2 style="font-family: 'Times New Roman';"><c:out value="Welcome ${student.fullName} to National Test Online. ${exam.content} is coming soon."/></h2>
        </div>
    </div>     
</div>

<script>
<%-- --%>
    var deadline = new Date("<c:out value="${exam.getTimeStartString() }"/>");

    var d =new Date();
    //var now = time.getTime();
    var j=1000;
        var getTimeNow = setInterval(function () {
        d = new Date("<c:out value="${getTime}"/>");
        j = j+ 1000;
        d = new Date(d.getTime() + j);
    }, 1000);
    var x = setInterval(function () {
        var examId = ${exam.examId };
         
        var t = deadline - d;
        var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60)) + Math.floor((t % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) * 60 + Math.floor(t / (1000 * 60 * 60 * 24)) * 24 * 60;
        var seconds = Math.floor((t % (1000 * 60)) / 1000);
        document.getElementById("minute").innerHTML = minutes;
        document.getElementById("second").innerHTML = seconds;
        if (t < 0) {
            clearInterval(x);
            document.getElementById("finish").innerHTML = "Get ready and good luck!";
            document.getElementById("minute").innerHTML = '0';
            document.getElementById("second").innerHTML = '0';
            var urlnext = window.location.origin + '/NationalTestOnline/exam-createexamtitle?examId=' + examId;
            setTimeout(function () {
                
                window.location.href = urlnext;
            }, 4000);

        }
    }, 1000);
    
        
</script> 
<!-- end exam -->