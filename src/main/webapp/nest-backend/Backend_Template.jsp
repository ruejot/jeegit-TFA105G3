<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

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
			<!-- 開始寫此頁，注意相對位置，從<section class="content-main"> 標籤的區塊"內"(<section>的子孫)，開始貼上你的程式碼 -->
			<!-- 開始寫此頁內容，看class name注意結構 -->
			<!-- 開始寫此頁內容，看class name注意結構 -->
			<!-- 開始寫此頁內容，看class name注意結構 -->
	        <!-- 結束此頁，注意相對位置，你寫的最後一行是相對於</section>的前一行 -->	
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
