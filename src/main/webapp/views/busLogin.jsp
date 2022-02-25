<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting 商業會員登入</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta property="og:title" content="" />
<meta property="og:type" content="" />
<meta property="og:url" content="" />
<meta property="og:image" content="" />
<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets/imgs/theme/Petting_logo.png" />
<!-- Template CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_backend.css" type="text/css" />
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/sellerAside.jsp" />
	<main class="main-wrap">
		<jsp:include page="/views/sellerHeader_2.jsp"/>
		<section class="content-main">

	<form action="../bus/BusLogin" method="POST">
	
		<section class="content-main mt-80 mb-80">
			<div class="card mx-auto card-login">
				<div class="card-body">
					<h4 class="card-title mb-4">商業會員登入</h4>
						<p class="text-center mb-15">是個人會員?
							<a href="memberLogin.jsp">個人會員登入由此去</a>
						</p>
<!-- 					<form> -->
						<div class="mb-3">
							<input class="form-control" placeholder="請輸入email"
								type="email" name="email" />
						</div>
						<!-- form-group// -->
						<div class="mb-3">
							<input class="form-control" placeholder="請輸入密碼"
								type="password" name="password" />
						</div>
						<!-- form-group// -->
<%-- 帳密錯誤時會出錯誤訊息在這--%>
						<span style ="color: red;">${errMsg}</span>
						<div class="mb-3">
							<a href="#" class="float-end font-sm text-muted">忘記密碼?</a> <label class="form-check"> <input
								type="checkbox" class="form-check-input"  /> <span
								class="form-check-label">記住我</span>
							</label>
						</div>
						<!-- form-group form-check .// -->
						<div class="mb-4">
							<button type="submit" class="btn btn-primary w-100">登入</button>
							<input type="hidden" name="action" value="login">
						</div>
						<!-- form-group// -->
<!-- 					</form> -->
					<p class="text-center small text-muted mb-15">或由以下社交帳號登入</p>
					<div class="d-grid gap-3 mb-4">
						<a href="#" class="btn w-100 btn-light font-sm"> <svg
								aria-hidden="true" class="icon-svg" width="20" height="20"
								viewBox="0 0 20 20">
                                    <path
									d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 002.38-5.88c0-.57-.05-.66-.15-1.18z"
									fill="#4285F4"></path>
                                    <path
									d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 01-7.18-2.54H1.83v2.07A8 8 0 008.98 17z"
									fill="#34A853"></path>
                                    <path
									d="M4.5 10.52a4.8 4.8 0 010-3.04V5.41H1.83a8 8 0 000 7.18l2.67-2.07z"
									fill="#FBBC05"></path>
                                    <path
									d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 001.83 5.4L4.5 7.49a4.77 4.77 0 014.48-3.3z"
									fill="#EA4335"></path>
                                </svg> 使用Google帳號登入
						</a> <a href="#" class="btn w-100 btn-light font-sm"> <svg
								aria-hidden="true" class="icon-svg" width="20" height="20"
								viewBox="0 0 20 20">
                                    <path
									d="M3 1a2 2 0 00-2 2v12c0 1.1.9 2 2 2h12a2 2 0 002-2V3a2 2 0 00-2-2H3zm6.55 16v-6.2H7.46V8.4h2.09V6.61c0-2.07 1.26-3.2 3.1-3.2.88 0 1.64.07 1.87.1v2.16h-1.29c-1 0-1.19.48-1.19 1.18V8.4h2.39l-.31 2.42h-2.08V17h-2.5z"
									fill="#4167B2"></path>
                                </svg> 使用Facebook帳號登入
						</a>
					</div>
					<p class="text-center mb-4">
						還沒有帳號? <a href="#">點我註冊</a>
					</p>
				</div>
			</div>
		</section>
		
	</form>
	
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/sellerFooter.jsp" />
	</main>
	<!-- 此頁<main>結束 -->
	
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
    <!-- 60002e5c50.js是fontawesome給璟葶這個會員註冊的key碼，如果之後fontawesome有新版，可能會失效，到時請自己去fontawesome註冊拿新的key -->
    <script src="https://kit.fontawesome.com/60002e5c50.js"></script>
	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js" type="text/javascript"></script>
</body>
</html>
