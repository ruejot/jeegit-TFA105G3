<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">

<head>
<meta charset="utf-8" />
<title>Petting-註冊會員</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>

<body>
	<jsp:include page="/views/userHeader.jsp" />
	<!--End userHeader-->
	<main class="main pages">

		<div class="page-content pt-50 pb-50">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 m-auto">
						<div class="row">
							<div class="col-md-3">
								<div class="dashboard-menu">
									<ul class="nav flex-column" role="tablist">
										<li class="nav-item"><a class="nav-link  active"
											id="buglogin-tab" data-bs-toggle="tab" href="#buglogin"
											role="tab" aria-controls="buglogin" aria-selected="false"
											style="background-color: #FFD05E"><i
												class="fi-rs-shopping-bag mr-10"></i>廠商註冊</a></li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
								<div class="tab-content account dashboard-content pl-50">

									<div class="tab-pane fade active show" id="buglogin"
										role="tabpanel" aria-labelledby="buglogin-tab">
										<div class="card card-login mx-auto col-md-8">
											<!--mx-auto-->
											<div class="card-body">
												<h3 class="card-title mb-4 text-center rounded"
													style="background-color: #FFD05E">廠商註冊</h3>
												<p class="text-center mb-4">
													已有帳號嗎? <a
														href="<%=request.getContextPath()%>/nest-frontend/Login.jsp">由此登入</a>
												</p>
												<!-- 用form綁定，這樣才可對應到BusRegisterServlet的@WebServlet("/bus/BusRegister") -->
												<form method="POST"
													action="<%=request.getContextPath()%>/bus/BusRegister">
													<!-- 或可以這樣寫：<form action="../bus/BusRegister" method="POST"> -->
													<div class="mb-3">
														<input required="" class="form-control" name="busEmail"
															placeholder="廠商 E-mail" type="email" />
													</div>
													<!-- form-group// -->
													<div class="mb-3">
														<input required="" class="form-control" name="busPassword"
															placeholder="密碼" type="password" />
													</div>
													<div class="mb-3">
														<input required="" class="form-control"
															name="busPasswordRp" placeholder="確認密碼" type="password" />
													</div>
													<!-- 密碼跟確認密碼不一致時，會出警告訊息在這 -->
													<span style="color: red;">${warningBusMsg1}</span>
													<div class="mb-3">
														<input required="" class="form-control" name="busName"
															placeholder="商家名稱" type="text" />
													</div>
													<div class="mb-3">
														<input required="" class="form-control" name="busPhone"
															placeholder="電話" type="tel" />
													</div>
													<div class="mb-3">
														<input required="" class="form-control" name="busAddress"
															placeholder="地址" type="text" />
													</div>
													<!-- form-group// -->

													<!-- 帳號已被註冊時會出警告訊息在這 -->
													<span style="color: red;">${warningBusMsg2}</span>

													<!-- <div class="chek-form">
																	<input type="text" required="" name="email"
																		placeholder="Security code *" />
																</div>
																<span class="security-code">
																	<b class="text-new">8</b>
																	<b class="text-hot">6</b>
																	<b class="text-sale">7</b>
																	<b class="text-best">5</b>
																</span> -->

													<div class="login_footer form-group mb-50">
														<div class="chek-form">
															<div class="custome-checkbox">
																<input class="form-check-input" type="checkbox"
																	name="checkbox" id="privacycheckbox" value="1" checked>
																<label class="form-check-label" for="privacycheckbox"><span>我同意隱私權政策</span></label>
															</div>
														</div>
														<a href="page-privacy-policy.html"><i
															class="fi-rs-book-alt mr-5 text-muted"></i>由此了解更多隱私權政策</a>
														<!-- 若無勾選同意隱私權政策時會出警告訊息在這 -->
														<span style="color: red;">${warningPrivacyMsg}</span>
													</div>

													<!-- form-group form-check .// -->
													<div class="mb-4">
														<!-- busRegisterservlet的Register的if判斷式是找到這↓ -->
														<button type="submit" class="btn btn-primary w-100"
															name="busaction" value="register">送出並註冊</button>
													</div>
													<!-- form-group// -->

												</form>


											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>















		<!-- 結束此頁，注意相對位置，你寫的最後一行是相對於</main>的前一行 -->
	</main>
	<jsp:include page="/views/footer.jsp" />

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

</html>