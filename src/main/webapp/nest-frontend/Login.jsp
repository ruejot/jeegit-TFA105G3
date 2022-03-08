<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting-登入</title>
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

		<div class="page-content pt-50 pb-50">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 m-auto">
						<div class="row">
							<div class="col-md-3">
								<div class="dashboard-menu">
									<ul class="nav flex-column" role="tablist">
										<li class="nav-item"><a class="nav-link active" id="memberlogin-tab" data-bs-toggle="tab"
											href="#memberlogin" role="tab" aria-controls="memberlogin" aria-selected="false"><i
												class="fi-rs-settings-sliders mr-10"></i>個人會員登入</a></li>
										<li class="nav-item"><a class="nav-link" id="buglogin-tab" data-bs-toggle="tab"
											href="#buglogin" role="tab" aria-controls="buglogin" aria-selected="false"><i
												class="fi-rs-shopping-bag mr-10"></i>廠商登入</a></li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
								<div class="tab-content account dashboard-content pl-50">
									
									<div class="tab-pane fade active show" id="memberlogin" role="tabpanel"
										aria-labelledby="memberlogin-tab">
										<div class="card card-login mx-auto col-md-8">
											<!--mx-auto-->
											<div class="card-body">
												<h4 class="card-title mb-4 text-center rounded" style="background-color:#C9FED1">個人會員登入</h4>
												
												<!-- 用form綁定，這樣才可對應到MemberServlet的@WebServlet("/members/MembersLogin") -->
												<form method="POST" action="<%=request.getContextPath()%>/members/MembersLogin">
												<!-- 或可以這樣寫：<form action="../members/MembersLogin" method="POST"> -->
													<div class="mb-3">
														<input class="form-control" name="email" placeholder="個人會員 E-mail" type="email"/>
													</div>
													<!-- form-group// -->
													<div class="mb-3">
														<input class="form-control" name="password" placeholder="密碼" type="password"/>
													</div>
													<!-- form-group// -->
													<!-- 帳密錯誤時會出錯誤訊息在這 -->
													<span style ="color: red;">${errMsg}</span>
													<div class="mb-3">
														<a href="../nest-frontend/membersForgetPassword.jsp" class="float-end font-sm text-muted">忘記密碼?</a> 
														<!-- <label class="form-check">
															<input type="checkbox" class="form-check-input" /> <span class="form-check-label">記住我</span>
														</label> -->
													</div>
													<!-- form-group form-check .// -->
													<div class="mb-4">
														<!-- membersLoginservlet的login的if判斷式是找到action=login↓ -->
														<button type="submit" class="btn btn-primary w-100" name="action" value="login">登入</button>
														<!-- <input type="hidden" name="action" value="login"> -->
													</div>
													<!-- form-group// -->
												</form>

												<!-- 因為要有真實網域才可以串FB 登入api,所以先不串了，但問友人，google登入好像可以不用真實網域，再研究-->
												<!-- <p class="text-center small text-muted mb-15">或著從下列社群帳號登入</p> -->
												<!-- <div class="d-grid gap-3 mb-4">
													<a href="#" class="btn w-100 btn-light font-sm"> <svg aria-hidden="true" class="icon-svg"
															width="20" height="20" viewBox="0 0 20 20">
                                                                <path
																d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 002.38-5.88c0-.57-.05-.66-.15-1.18z"
																fill="#4285F4"></path>
                                                                <path
																d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 01-7.18-2.54H1.83v2.07A8 8 0 008.98 17z"
																fill="#34A853"></path>
                                                                <path
																d="M4.5 10.52a4.8 4.8 0 010-3.04V5.41H1.83a8 8 0 000 7.18l2.67-2.07z" fill="#FBBC05"></path>
                                                                <path
																d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 001.83 5.4L4.5 7.49a4.77 4.77 0 014.48-3.3z"
																fill="#EA4335"></path>
                                                            </svg> Google
													</a> <a href="#" class="btn w-100 btn-light font-sm"> <svg aria-hidden="true" class="icon-svg"
															width="20" height="20" viewBox="0 0 20 20">
                                                                <path
																d="M3 1a2 2 0 00-2 2v12c0 1.1.9 2 2 2h12a2 2 0 002-2V3a2 2 0 00-2-2H3zm6.55 16v-6.2H7.46V8.4h2.09V6.61c0-2.07 1.26-3.2 3.1-3.2.88 0 1.64.07 1.87.1v2.16h-1.29c-1 0-1.19.48-1.19 1.18V8.4h2.39l-.31 2.42h-2.08V17h-2.5z"
																fill="#4167B2"></path>
                                                            </svg> Facebook
													</a>
												</div> -->
												<p class="text-center mb-4">
													還沒有帳號嗎? <a href="../nest-frontend/MembersRegister.jsp">按此註冊</a>
												</p>
											</div>
										</div>
									</div>
									<div class="tab-pane fade" id="buglogin" role="tabpanel" aria-labelledby="buglogin-tab">
										<div class="card card-login mx-auto col-md-8">
											<!--mx-auto-->
											<div class="card-body">
												<h4 class="card-title mb-4 text-center rounded" style="background-color:#FFD05E">廠商登入</h4>
												
												<!-- 用form綁定，這樣才可對應到BusLoginServlet的@WebServlet("/bus/BusLogin") -->
												<form method="POST" action="<%=request.getContextPath()%>/bus/BusLogin">
												<!-- 或可以這樣寫：<form action="../bus/BusLogin" method="POST"> -->
													<div class="mb-3">
														<input class="form-control" name="email" placeholder="廠商 E-mail" type="email" />
													</div>
													<!-- form-group// -->
													<div class="mb-3">
														<input class="form-control" name="password" placeholder="密碼" type="password" />
													</div>
													<!-- form-group// -->
													<!-- 帳密錯誤時會出錯誤訊息在這 -->
													<span style ="color: red;">${errMsg1}</span>
													<div class="mb-3">
														<a href="../nest-backend/busForgetPassword.jsp" class="float-end font-sm text-muted">忘記密碼?</a> 
														<!-- <label class="form-check">
															<input type="checkbox" class="form-check-input" checked="" /> <span class="form-check-label">記住我</span>
														</label> -->
													</div>
													<!-- form-group form-check .// -->
													<div class="mb-4">
														<!-- busLoginservlet的login的if判斷式是找到這↓ -->
														<button type="submit" class="btn btn-primary w-100" name="busaction" value="login">登入</button>
													</div>
													<!-- form-group// -->
												</form>

												<p class="text-center mb-4">
													還沒有廠商帳號嗎? <a href="../nest-frontend/BusRegister.jsp">由此註冊成為合作店家</a>
												</p>
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

