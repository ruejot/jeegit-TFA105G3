<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>FrontEnd板模-Petting</title>
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
		<div class="container mb-80 mt-50">
			<div class="row">
				<div class="col-lg-1"></div>
				<div class="col-lg-8 mb-40">
					<h1 class="heading-2 mb-10">結帳</h1>
					<div class="d-flex justify-content-between">
						<h6 class="text-body">
							目前有 <span class="text-brand">3</span> 項商品在您的購車
						</h6>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-1"></div>
				<div class="col-lg-7">
					<div class="row">
						<h4 class="mb-30">收件人資訊</h4>
						<form method="post">
							<div class="row">
								<div class="form-group col-lg-6">
									<input type="text" required="" name="receiver" value="預設MemberUsing.name的EL" placeholder="收件人">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-6">
									<input required="" type="text" name="email" readonly value="readonly強制MemberUsing.email"
										placeholder="強制MemberUsing.email的EL">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-6">
									<input type="text" name="zipcode" placeholder="郵遞區號 (例: 105)">
								</div>
							</div>
							<div class="row">
								<div id="twzipcode_ADV"></div>
								<!-- <div class="form-group col-lg-6"> -->
								<!-- <input type="text" name="zipcode" placeholder="郵遞區號 (例: 105)"> -->
								<!-- </div> -->
							</div>
							<div class="row">
								<div class="form-group col-lg-10">
									<input type="text" name="billing_address" value="" required=""
										placeholder="縣市地址 (例: 台北市中山區南京東路三段219號) *">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-6">
									<input type="text" required="" name="phone" value="可以預設MemberUsing.mobile" placeholder="手機號碼 *">
								</div>
							</div>
							<!-- <div class="form-group mb-30"> -->
							<!-- <textarea rows="5" placeholder="Additional information"></textarea> -->
							<!-- </div> -->
						</form>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="payment ml-30 mb-50">
						<h4 class="mb-20">出貨方式</h4>
						<div class="payment_option">
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_1" id="exampleRadios3"> <label
									class="form-check-label" for="exampleRadios3" data-bs-toggle="collapse" data-target="#bankTranfer"
									aria-controls="bankTranfer">信用卡付款</label>
							</div>
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_1" id="exampleRadios4" checked="">
								<label class="form-check-label" for="exampleRadios4" data-bs-toggle="collapse"
									data-target="#checkPayment" aria-controls="checkPayment">貨到付款</label>
							</div>
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_1" id="exampleRadios5"> <label
									class="form-check-label" for="exampleRadios5" data-bs-toggle="collapse" data-target="#paypal"
									aria-controls="paypal">ATM轉帳</label>
							</div>
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_1" id="exampleRadios6"> <label
									class="form-check-label" for="exampleRadios6" data-bs-toggle="collapse" data-target="#bankTranfer"
									aria-controls="bankTranfer">超商付款</label>
							</div>
						</div>
						<div class="payment-logo d-flex">
							<!-- <img class="mr-15" src="assets/imgs/theme/icons/payment-paypal.svg" alt=""> -->
							<img class="mr-15" src="assets/imgs/theme/icons/payment-visa.svg" alt=""> <img class="mr-15"
								src="assets/imgs/theme/icons/payment-master.svg" alt="">
							<!-- <img src="assets/imgs/theme/icons/payment-zapper.svg" alt=""> -->
						</div>
					</div>
					<div class="payment ml-30">
						<h4 class="mb-20">付款方式</h4>
						<div class="payment_option">
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_2" id="exampleRadios7"> <label
									class="form-check-label" for="exampleRadios7" data-bs-toggle="collapse" data-target="#bankTranfer"
									aria-controls="bankTranfer">宅配到府</label>
							</div>
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_2" id="exampleRadios8"> <label
									class="form-check-label" for="exampleRadios8" data-bs-toggle="collapse" data-target="#checkPayment"
									aria-controls="checkPayment">超商取貨</label>
							</div>
							<div class="custome-radio">
								<input class="form-check-input" required="" type="radio" name="group_2" id="exampleRadios9" checked="">
								<label class="form-check-label" for="exampleRadios9" data-bs-toggle="collapse" data-target="#paypal"
									aria-controls="paypal">面交到店取貨</label>
							</div>
						</div>
						<a href="#" class="btn btn-fill-out btn-block mt-30">確認訂購<i class="fi-rs-sign-out ml-15"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div></div>
		<!-- 注意，有全形空白拉排版 -->
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
	<!-- TWtwzipcode下拉選單 JS -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	<script>
		$(function() {
			$('#twzipcode_ADV').twzipcode(
					{
// 						detect: true,
// 						zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
						countySel : '臺北市', //縣市預設值
						districtSel : '中山區', //鄉鎮市區預設值
						readonly : false,
						css : [ 'form-group col-lg-3', 'form-group col-lg-3',
								'form-group col-lg-2' ]
					});
		});
	</script>
</body>
</html>

