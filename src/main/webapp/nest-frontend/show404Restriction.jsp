<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->
<% int countDownSec = 5; %> <!-- 因為gif長度關係，最多10秒-->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>FrontEnd板模-Petting</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="refresh" content="<%=countDownSec%>; url=HomePage.jsp" />
<meta property="og:title" content="" />
<meta property="og:type" content="" />
<meta property="og:url" content="" />
<meta property="og:image" content="" />
<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/imgs/theme/Petting_logo.png" />
<!-- Template CSS -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" /> --%>
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" /> --%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>

<body>
	<%-- 	<jsp:include page="/views/userHeader.jsp" /> --%>
	<!--End userHeader-->
	<main class="main page-404">
            <div class="page-content pt-100 pb-100">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-8 col-lg-10 col-md-12 m-auto text-center">
                            <p class="mb-20"><img src="../assets/imgs/page/shibaWait.gif" alt="" class="hover-up" /></p>
                            <h1 class="display-2 mb-30">您無權限喔 !<br/>將於 <span class="countdownNum" id="seconds"><%=countDownSec%></span> 秒後導向首頁 !</h1>
                            <p class="font-lg text-grey-700 mb-30">
                                不能進來喔!<br />
                                您沒有存取的權限，無法瀏覽部分網頁。
                            </p>
                            <a class="btn btn-default submit-auto-width font-xs hover-up mt-30" href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp">
                            <i class="fi-rs-home mr-5"></i>立即回到首頁</a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
	<jsp:include page="/views/footer.jsp" />
                            <a href='https://www.freepik.com/vectors/dog'>Dog vector created by pch.vector - www.freepik.com</a>

	<!-- Preloader Start -->
	<!-- Vendor JS-->
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/modernizr-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/slick.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.syotimer.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/wow.js"></script>
	<!--slider-range.js, jquery-ui.js , never appear at sametime-->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/slider-range.js"></script>
	<!-- index-3.html, index-4, shop-*.html，板模的這幾頁有用到jquery-ui.js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script>
	<!-- blog-post-fullwidth.html, shop-*.html，板模的這幾頁有用到perfect-scrollbar.js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/perfect-scrollbar.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/magnific-popup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/waypoints.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/counterup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.countdown.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/images-loaded.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/isotope.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/scrollup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.vticker-min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.theia.sticky.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.elevatezoom.js"></script>

	<!-- Invoice page's JS -->
	<!-- <script src="assets/js/invoice/jspdf.min.js"></script> -->
	<!-- <script src="assets/js/invoice/invoice.js"></script> -->

	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
</body>
<!-- <script>
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByTagName('img');
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}
  slides[slideIndex-1].style.display = "block";  

  setTimeout(showSlides, 1100); // Change image every 1 seconds	  
}
</script> -->
<style type="text/css">
#clock {
/* 	width: 200px; */
/* 	height: 200px; */
/* 	border-radius: 50%; */
	background-color: lightblue;
	margin: auto;
}

span#countdownNum {
	display: block;
	width: 100%;
	margin: auto;
	padding-top: 30px;
	text-align: center;
	font-size: 120px;
}
</style>
<script>
timeLeft = <%=countDownSec%>;

function countdown() {
	timeLeft--;
	document.getElementById("seconds").innerHTML = String( timeLeft );
	if (timeLeft > 0) {
		setTimeout(countdown, 1000);
	}
};

setTimeout(countdown, 1000);
</script>
</html>

