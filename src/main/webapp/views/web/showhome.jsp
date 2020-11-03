<%-- 
    Document   : login
    Created on : Sep 30, 2020, 3:20:33 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<%@page session="true"%>
 
  <!-- About -->
  <section class="content-section bg-light" id="about">
    <div class="container text-center">
      <div class="row">
        <div class="col-lg-10 mx-auto">
          <h2>create a quiz and run a test created by you!</h2>
          <p class="lead mb-5">With just a few simple steps you can organize an exam easily
              <br>
            <a href="#" class="btn btn-info btn-xl js-scroll-trigger mt-5">Start with student</a></p>
          <a class="btn btn-dark btn-xl js-scroll-trigger" href="#services">Start with teacher</a>
        </div>
      </div>
    </div>
  </section>

  <!-- Services -->
  <section class="content-section bg-primary text-white text-center" id="services">
    <div class="container">
      <div class="content-section-heading">
        <h3 class="text-secondary mb-0">EXAMINATION</h3>
        <h2 class="mb-5">EXAMINATION CÓ GÌ?</h2>
      </div>
      <div class="row">
        <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
          <span class="service-icon rounded-circle mx-auto mb-3">
            <i class="icon-screen-smartphone"></i>
          </span>
          <h4>
            <strong>Responsive</strong>
          </h4>
          <p class="text-faded mb-0">Looks great on any screen size!</p>
        </div>
        <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
          <span class="service-icon rounded-circle mx-auto mb-3">
            <i class="icon-pencil"></i>
          </span>
          <h4>
            <strong>Redesigned</strong>
          </h4>
          <p class="text-faded mb-0">Freshly redesigned for Bootstrap 4.</p>
        </div>
        <div class="col-lg-3 col-md-6 mb-5 mb-md-0">
          <span class="service-icon rounded-circle mx-auto mb-3">
            <i class="icon-like"></i>
          </span>
          <h4>
            <strong>Favorited</strong>
          </h4>
          <p class="text-faded mb-0">Millions of users
            <i class="fas fa-heart"></i>
            Start Bootstrap!</p>
        </div>
        <div class="col-lg-3 col-md-6">
          <span class="service-icon rounded-circle mx-auto mb-3">
            <i class="icon-mustache"></i>
          </span>
          <h4>
            <strong>Question</strong>
          </h4>
          <p class="text-faded mb-0">I mustache you a question...</p>
        </div>
      </div>
    </div>
  </section>

  <!-- Callout -->
  <section class="callout">
    <div class="container text-center">
      <h2 class="mx-auto mb-5">Welcome to
        <em>your</em>
        EXAMINATION!</h2>
      <a class="btn btn-primary btn-xl" href="https://startbootstrap.com/themes/stylish-portfolio/">Download Now!</a>
    </div>
  </section>

  <!-- Portfolio -->
  <section class="content-section" id="portfolio">
    <div class="container">
      <div class="content-section-heading text-center">
        <h3 class="text-secondary mb-0">EXAMINATION</h3>
        <h2 class="mb-5">Target Projects</h2>
      </div>
      <div class="row no-gutters">
        <div class="col-lg-6">
          <a class="portfolio-item" href="#!">
            <div class="caption">
              <div class="caption-content">
                <div class="h2">Tiện dụng</div>
                <p class="mb-0">Cùng với những khó khăn trong thời gian dịch covid-19 thì thi online là một giải pháp tuyệt vời!</p>
              </div>
            </div>
            <img class="img-fluid" src="https://thumbnails.texastribune.org/2YDa4dGdvgfSpMFlwmt0VHVzqXg=/1200x804/smart/filters:quality(95)/static.texastribune.org/media/images/multiple-choice.jpg" alt="">
          </a>
        </div>
        <div class="col-lg-6">
          <a class="portfolio-item" href="#!">
            <div class="caption">
              <div class="caption-content">
                <div class="h2">Nhanh chóng</div>
                <p class="mb-0">Cung cấp các giải pháp thi online tuyệt vời nhanh chóng tiện lợi và học sinh dễ dàng làm quen tiếp cận</p>
              </div>
            </div>
            <img class="img-fluid" src="https://previews.123rf.com/images/smolaw11/smolaw111703/smolaw11170300006/73350570-student-testing-in-exercise-exams-answer-on-a-tablet-with-multiple-choice-questions-of-e-learning-by.jpg" alt="">
          </a>
        </div>
        <div class="col-lg-6">
          <a class="portfolio-item" href="#!">
            <div class="caption">
              <div class="caption-content">
                <div class="h2">Quản lý</div>
                <p class="mb-0">Dễ dàng tổ chức cũng như quản lý bài thi</p>
              </div>
            </div>
            <img class="img-fluid" src="https://www.middleweb.com/wp-content/uploads/2019/02/russell-overhead-testing.jpg" alt="">
          </a>
        </div>
        <div class="col-lg-6">
          <a class="portfolio-item" href="#!">
            <div class="caption">
              <div class="caption-content">
                <div class="h2">Ổn định</div>
                <p class="mb-0">Hệ thống có mức ổn định cao.</p>
              </div>
            </div>
            <img class="img-fluid" src="https://www.washingtonpost.com/rf/image_1484w/2010-2019/WashingtonPost/2015/09/02/Education/Images/Common_Core_Tests-03608-3595-567.jpg?t=20170517" alt="">
          </a>
        </div>
      </div>
    </div>
  </section>

  <!-- Call to Action -->
  <section class="content-section bg-primary text-white">
    <div class="container text-center">
      <h2 class="mb-4">Find out more about our website...</h2>
      <a href="#!" class="btn btn-xl btn-light mr-4">Click Me!</a>
      <a href="#!" class="btn btn-xl btn-dark">Look at Me!</a>
    </div>
  </section>

  <!-- Map -->
  <div id="contact" class="map">
    <iframe src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=28.659344,-81.187888&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A&amp;output=embed"></iframe>
    <br />
    <small>
      <a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=28.659344,-81.187888&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A"></a>
    </small>
  </div>