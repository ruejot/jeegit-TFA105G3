<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>seller板模-Petting</title>
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
<!-- /assets/css/main.css  /csDetail/assets/css/main.css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/FIXAside.jsp" />
	<main class="main-wrap">
		<jsp:include page="/views/FIXsellerHeader.jsp" />
		<section class="content-main">
			<!-- content-main start 在這<section>區塊內開始寫此後台頁內容 -->
			
			<!-- 開始寫此頁內容，看class name注意結構 -->
	        <!-- 開始寫此頁內容，看class name注意結構 -->
	        <!-- 開始寫此頁內容，看class name注意結構 -->
	        <!-- 開始寫此頁內容，看class name注意結構 -->
	        <!-- 結束此頁內容，看class name注意結構 -->	
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/FIXFooter.jsp" />
	</main>
	<!-- 此頁<main>結束 -->

	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js" type="text/javascript"></script>
</body>
</html>
