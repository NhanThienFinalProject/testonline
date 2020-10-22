<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Error</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {
                background-color: #2F3242;
            }
            svg {
                position: absolute;
                top: 50%;
                left: 50%;
                margin-top: -250px;
                margin-left: -400px;
            }
            .message-box {
                height: 200px;
                width: 380px;
                position: absolute;
                top: 50%;
                left: 50%;
                margin-top: -100px;
                margin-left: 50px;
                color: tomato;
                font-family: Roboto;
            }
            .message-box h1 {
                font-size: 250px;
                line-height: 50px;
                margin-bottom: 30px;
            }
            .buttons-con .action-link-wrap {
                margin-top: 40px;
            }
            .buttons-con .action-link-wrap a {
                background: #68c950;
                padding: 8px 60px;
                border-radius: 4px;
                color: green;
                /*font-weight: bold;*/
                font-size: 14px;
                transition: all 0.3s linear;
                cursor: pointer;
                text-decoration: none;
                margin-right: 10px
            }
            .buttons-con .action-link-wrap a:hover {
                background: green;
                color: #68c950;
            }

            #Polygon-1 , #Polygon-2 , #Polygon-3 , #Polygon-4 , #Polygon-4, #Polygon-5 {
                animation: float 1s infinite ease-in-out alternate;
            }
            #Polygon-2 {
                animation-delay: .2s; 
            }
            #Polygon-3 {
                animation-delay: .4s; 
            }
            #Polygon-4 {
                animation-delay: .6s; 
            }
            #Polygon-5 {
                animation-delay: .8s; 
            }

            @keyframes float {
                100% {
                    transform: translateY(20px);
                }
            }
            @media (max-width: 450px) {
                svg {
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    margin-top: -250px;
                    margin-left: -190px;
                }
                .message-box {
                    top: 50%;
                    left: 50%;
                    margin-top: -100px;
                    margin-left: -190px;
                    text-align: center;
                }
            }
        </style>
    </head>

    <body>
        <svg width="380px" height="500px" viewBox="0 0 837 1045" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sketch="http://www.bohemiancoding.com/sketch/ns">
        <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" sketch:type="MSPage">
        <path d="M353,9 L626.664028,170 L626.664028,487 L353,642 L79.3359724,487 L79.3359724,170 L353,9 Z" id="Polygon-1" stroke="#007FB2" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M78.5,529 L147,569.186414 L147,648.311216 L78.5,687 L10,648.311216 L10,569.186414 L78.5,529 Z" id="Polygon-2" stroke="#EF4A5B" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M773,186 L827,217.538705 L827,279.636651 L773,310 L719,279.636651 L719,217.538705 L773,186 Z" id="Polygon-3" stroke="#795D9C" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M639,529 L773,607.846761 L773,763.091627 L639,839 L505,763.091627 L505,607.846761 L639,529 Z" id="Polygon-4" stroke="#F2773F" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M281,801 L383,861.025276 L383,979.21169 L281,1037 L179,979.21169 L179,861.025276 L281,801 Z" id="Polygon-5" stroke="#36B455" stroke-width="6" sketch:type="MSShapeGroup"></path>
        </g>
        </svg>
        <div class="message-box">
            <h1 id="error"></h1>
            <br>
            <div class="buttons-con">
                <div class="action-link-wrap">
                    <a onclick="history.back(-1)" class="link-button link-back-button">Go back</a>
                    <a href="/NationalTestOnline" class="link-button">Go home</a>
                </div>
            </div>
        </div>
        <script>
            var check = 0;
            var interval = setInterval(showErrorNumber, 1);
            function showErrorNumber() {
                if (check < 403) {
                    check++;
                    document.getElementById("error").innerHTML = check;
                }
                if(check === 100){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 7);
                }
                if(check === 203){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 14);
                }
                if(check === 303){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 26);
                }
                if(check === 355){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 80);
                }
                if(check === 395){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 150);
                }
                if(check === 397){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 370);
                }
                if(check === 399){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 600);
                }
                if(check === 401){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 900);
                }
                if(check === 402){
                    clearInterval(interval);
                    interval = setInterval(showErrorNumber, 1400);
                }
                if(check === 403){
                    clearInterval(interval);
                }
                console.log("hi");
            }
        </script>
    </body>
</html>