<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Petting-footer</title>
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
	<footer class="main">
		<section class="section-padding footer-mid">
			<div class="container pt-15 pb-20">
				<div class="row">
					<div class="col">
						<div class="widget-about font-md mb-md-3 mb-lg-3 mb-xl-0 wow animate__animated animate__fadeInUp"
							data-wow-delay="0">
							<div class="logo ">
								<a href="index.html" class=""><img
									src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg" width="100px" alt="logo" /></a>

							</div>
							<ul class="contact-infor">
								<li><img src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-location.svg" alt="" /><strong>地址:
								</strong> <br>
								<span>台北市中山區南京東路三段219號5樓</span></li>
								<li><img src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-contact.svg" alt="" /><strong>總機:</strong>
									<br>
								<span>02-2712-0589</span></li>
								<li><img src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-email-2.svg" alt="" /><strong>Email:</strong>
									<br>
								<span>contact@petting.com</span></li>
							</ul>
						</div>
					</div>
					<div class="footer-link-widget col wow animate__animated animate__fadeInUp" data-wow-delay=".1s">
						<h4 class="widget-title">關於我們</h4>
						<ul class="footer-list mb-sm-5 mb-md-0">
							<li><a style="color: #386641;" href="#">關於Petting</a></li>
							<li><a style="color: #386641;" href="#">隱私權政策</a></li>
						</ul>
					</div>
					<!----
                    <div class="footer-link-widget col wow animate__animated animate__fadeInUp" data-wow-delay=".2s">
                        <h4 class="widget-title">追蹤我們</h4>
                        <ul class="footer-list mb-sm-5 mb-md-0">
                            <li><a style="color:#386641;" href="#">LINE</a></li>
                            <li><a style="color:#386641;" href="#">Facebook</a></li>
                            <li><a style="color:#386641;" href="#">Instagram</a></li>
                        </ul>
                    </div>
                    --->
					<div class="footer-link-widget col wow animate__animated animate__fadeInUp" data-wow-delay=".3s">
						<h4 class="widget-title">購物說明</h4>
						<ul class="footer-list mb-sm-5 mb-md-0">
							<li><a style="color: #386641;" href="#">付款相關</a></li>
							<li><a style="color: #386641;" href="#">運送相關</a></li>
							<li><a style="color: #386641;" href="#">退換貨說明</a></li>
						</ul>
					</div>
					<div class="footer-link-widget col wow animate__animated animate__fadeInUp" data-wow-delay=".4s">
						<h4 class="widget-title">24小時服務專線</h4>
						<div class="">
							<div class="hotline d-lg-inline-flex mr-30">
								<img style="width: 20px" src="<%=request.getContextPath()%>/assets/imgs/theme/icons/phone-call.svg"
									alt="hotline" />
								<p style="color: #386641;">
									02-2712-0589<br>
									<span>營業時間 10:00-18:00</span>
								</p>
								<br> <br>
							</div>
							<div class="hotline d-lg-inline-flex">
								<img style="width: 20px" src="<%=request.getContextPath()%>/assets/imgs/theme/icons/phone-call.svg"
									alt="hotline" />
								<p style="color: #386641;">
									02-6688-6688<br>
									<span>24小時服務專線</span>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="container pb-30 wow animate__animated animate__fadeInUp" data-wow-delay="0">
			<div class="row align-items-center">
				<div class="col-12 mb-30">
					<div class="footer-bottom"></div>
				</div>
				<div class="col-xl-4 col-lg-6 col-md-6">
					<p class="font-sm mb-0">
						<script>
                            document.write(new Date().getFullYear());
                        </script>
                        - <strong class="text-brand">Petting</strong> ,  All for our pets.
					</p>
				</div>
			</div>
		</div>
	</footer>
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