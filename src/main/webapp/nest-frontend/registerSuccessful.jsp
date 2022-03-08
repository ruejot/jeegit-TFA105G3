<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!-- [editor,date] wei,2022-02-22 -->

		<html lang="zh-Hant-TW">

		<head>
			<meta charset="utf-8" />
			<title>Petting註冊成功!</title>
			<meta http-equiv="x-ua-compatible" content="ie=edge" />
			<meta name="description" content="" />
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<meta property="og:title" content="" />
			<meta property="og:type" content="" />
			<meta property="og:url" content="" />
			<meta property="og:image" content="" />
			<!-- Favicon -->
			<link rel="shortcut icon" type="image/x-icon"
				href="<%=request.getContextPath()%>/assets/imgs/theme/Petting_logo.png" />
			<!-- Template CSS -->
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" />
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
		</head>

		<body>
			<jsp:include page="/views/userHeader.jsp" />
			<!--End userHeader-->
			<main class="main pages">
				<jsp:include page="/views/userMainPage-header.jsp" />
				<!-- page="/views/userMainPage-header.jsp" /> 說明如下 -->
				<!-- userMainPage-header.jsp，是 首頁>其他頁 顯示列 ，這一個 block 至少有2種樣式，有的頁面甚至沒有page-header區塊，自己觀察。 -->
				<!-- 需要因應各業顯示內容變動，會再確認userMainPage-header.jsp這樣分割是否適合。 -->
				<!--  -->
				<!-- 開始寫此jsp頁內容，重點，從<div class="page-header..."> 的"同層下一區塊"開始寫內容 -->
				<!-- "同層下一區塊"，板模各頁不盡相同 -->
				<!-- 有些板模頁是<div class="page-content ..." 開頭 -->
				<!-- 有些板模頁是<div class="container"> 開頭。自己觀察 -->

				<span style="font-size:24px;text-align: center;">
					<h3>恭喜註冊成功!</h3>
					<br>
						<h4>歡迎加入PETTING! </h4>
<%-- 					<h4>${ membersVO.getName(name)}，歡迎加入PETTING! </h4> --%>
<%-- 					<h4><%=session.getAttribute("name")%>，歡迎加入PETTING!</h4>						 --%>
						<!-- <meta http-equiv="refresh" content="等待秒數;url=頁面路徑"/>   -->
						<br>
						<p>將在<img src="http://unicatolicaquixada.edu.br/wp-content/uploads/2019/08/contagem-regressiva-gif-6.gif" class="n3VNCb" style="width: 17px; height: 27px; margin: 0px;">秒後自動跳轉至登入頁!再煩請重新登入!!</p>
						<meta http-equiv="refresh" content="5;url=/jeeweb-TFA105G3/nest-frontend/Login.jsp">
				</span>
				<span style="font-size:24px;">














					<!-- 結束此頁，注意相對位置，你寫的最後一行是相對於</main>的前一行 -->
			</main>
			<jsp:include page="/views/footer.jsp" />

			<!-- Preloader Start -->
			<!-- Vendor JS-->
			<script src="<%=request.getContextPath()%>/assets/js/vendors/modernizr-3.6.0.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/slick.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.syotimer.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/wow.js"></script>
			<!--slider-range.js, jquery-ui.js , never appear at sametime-->
			<script src="<%=request.getContextPath()%>/assets/js/plugins/slider-range.js"></script>
			<!-- index-3.html, index-4, shop-*.html，板模的這幾頁有用到jquery-ui.js -->
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script>
			<!-- blog-post-fullwidth.html, shop-*.html，板模的這幾頁有用到perfect-scrollbar.js -->
			<script src="<%=request.getContextPath()%>/assets/js/plugins/perfect-scrollbar.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/magnific-popup.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/select2.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/waypoints.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/counterup.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.countdown.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/images-loaded.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/isotope.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/scrollup.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.vticker-min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.theia.sticky.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.elevatezoom.js"></script>

			<!-- Invoice page's JS -->
			<!-- <script src="assets/js/invoice/jspdf.min.js"></script> -->
			<!-- <script src="assets/js/invoice/invoice.js"></script> -->

			<!-- Template  JS -->
			<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
		</body>

		</html>