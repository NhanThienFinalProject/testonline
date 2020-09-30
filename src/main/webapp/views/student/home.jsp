<%-- 
    Document   : home
    Created on : Sep 30, 2020, 9:33:27 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Exam -->
  <section class="content-section bg-light" id="about">
    <div class="container text-left">
      <div class="row">
        <div class="col-lg-2 col-12 mx-auto pt-lg-2">
                  <div class="row"> 
                    <div class="btn-group col-6 offset-3"> 
                      <span  class="btn btn-success" id="minute"></span><span class="btn btn-success" id="second"></span>    
                    </div> 
                    <h5 class="text-danger col-12 text-center mt-3" id="finish"></h5> 
                  </div> 
                
        </div>
        <div class="col-lg-8 col-10 mx-auto border-left border">
          <div class="row p-3">
            <div class="col-12 text-left border-bottom mb-3" >
              <h2 id="question">Tính tổng 2 + 3 + 4 + 6 + 6 + 6= ?</h2>
              <p id="hinte">Hinte: <i>Sử dụng máy tính bỏ túi để bấm</i></p>
            </div>
            <div class="col-12" id="answers">
              <p class="btn btn-info" id="answer1">27</p>
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
    <script src="" type="text/javascript" charset="utf-8" async defer>
      function changecolor(name) {
        // body...
        document.getElementById(name).style.background = "green";
      }
    </script>
  </section>
  <script> 
  
var deadline = new Date("Sep 29, 2020 09:53:25").getTime(); 
  
var x = setInterval(function() { 
  
var now = new Date().getTime(); 
var t = deadline - now; 
//var days = Math.floor(t / (1000*60*60*24)); 
//var hours = Math.floor((t%(1000*60*60*24))/(1000 * 60 * 60)); 
// 
var minutes = Math.floor((t%(1000*60*60)) / (1000 * 60)) + Math.floor((t%(1000*60*60*24))/(1000 * 60 * 60))*60 + Math.floor(t / (1000*60*60*24)) *24*60; 
var seconds = Math.floor((t%(1000*60)) / 1000); 


document.getElementById("minute").innerHTML = minutes;  
document.getElementById("second").innerHTML =seconds;  
if (t < 0) { 
        clearInterval(x); 
        document.getElementById("finish").innerHTML = "Hết Giờ"; 
        document.getElementById("minute").innerHTML ='0' ;  
        document.getElementById("second").innerHTML = '0'; } 
}, 1000); 
</script> 
<!-- end exam -->
