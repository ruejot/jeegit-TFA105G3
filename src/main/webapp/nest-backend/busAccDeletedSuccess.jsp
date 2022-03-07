<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting 刪除帳號作業完成 </title>
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
			<div class="card">
											<div class="card-header">
												<h5>您的帳號已刪除，並且無法恢復!!</h5>
											</div>
											<div class="card-body">
												
													
													<div class="row">

														<div class="form-group col-md-12">
															<h6>很高興與您這段時間的緣分，希望未來還能有緣相聚~</h6>
														</div>

														<div class="form-group col-md-12">
															<label>將在<img src="http://unicatolicaquixada.edu.br/wp-content/uploads/2019/08/contagem-regressiva-gif-6.gif" class="n3VNCb" style="width: 17px; height: 27px; margin: 0px;">秒後自動跳轉至首頁!</label>
															
															
														</div>

														<meta http-equiv="refresh" content="5;url=/jeeweb-TFA105G3/nest-frontend/HomePage.jsp">
														
												


											</div>

										</div>
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
