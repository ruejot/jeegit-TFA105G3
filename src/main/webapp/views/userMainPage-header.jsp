<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>FrontEnd-Header</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- Template 移除所有header, footer, aside的CSS,JS引用，都拉到外面 -->
<!-- slider-range.css correspond with slider-range.js -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" /> --%>
<!-- Manually open animate.min.css if you need. -->
<!-- <link rel="stylesheet" href="assets/css/plugins/animate.min.css" /> -->
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" /> --%>
</head>
<body>
<!-- 	<header class="header-area header-style-1 header-height-2">...</header> -->
<!-- 	<div class="mobile-header-active mobile-header-wrapper-style">...</div> -->
	<!--End header-->

<!-- 	<main class="main pages"> -->
		<div class="page-header breadcrumb-wrap">
			<div class="container">
				<div class="breadcrumb">
					<a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>首頁</a> <a href="page-account.html"><span></span>
						會員中心</a>
					<!--
                        <span></span> Pages <span></span> My Account
                        -->
				</div>
			</div>
		</div>
<!-- 	</main> -->
	
	<!-- Template 移除所有header, footer, aside的CSS,JS引用，都拉到外面 -->
<!--     Vendor JS -->
<%--     <script src="<%=request.getContextPath()%>/assets/js/vendors/modernizr-3.6.0.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/slick.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.syotimer.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/wow.js"></script> --%>
<!--     slider-range.js, jquery-ui.js , never appear at sametime -->
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/slider-range.js"></script> <!-- index-3.html, index-4, shop-*.html --> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script> <!-- blog-post-fullwidth.html, shop-*.html --> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/perfect-scrollbar.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/magnific-popup.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/select2.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/waypoints.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/counterup.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.countdown.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/images-loaded.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/isotope.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/scrollup.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.vticker-min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.theia.sticky.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.elevatezoom.js"></script> --%>
<!--     Invoice JS, shop-inovice-*.html -->
<!--     <script src="assets/js/invoice/jspdf.min.js"></script> -->
<!--     <script src="assets/js/invoice/invoice.js"></script> -->
<!--     Template  JS -->
<%--     <script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/js/shop.js?v=4.0"></script> <!-- ?v=4.0 --> --%>
</body>
</html>