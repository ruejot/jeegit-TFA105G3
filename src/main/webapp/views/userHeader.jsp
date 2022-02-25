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
	<header class="header-area header-style-1 header-height-2">
		<!--
            <div class="mobile-promotion">
                <span>Grand opening, <strong>up to 15%</strong> off all items. Only <strong>3 days</strong> left</span>
            </div>
            -->
		<div class="header-middle header-middle-ptb-1 d-none d-lg-block">
			<div class="container">
				<div class="header-wrap">
					<div class="logo logo-width-1">
						<a href="index.html"><img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
							alt="logo" /></a>
					</div>
					<div class="header-right">
						<div class="search-style-2">
							<form action="../product/SearchServlet" method="GET">
								<select class="select-active">
									<option>所有類別</option>
									<option>寵物飼料</option>
									<option>寵物罐頭</option>
									<option>寵物保健</option>
									<option>寵物零食</option>
									<option>寵物玩具</option>
									<option>居家用品</option>
									<option>外出用品</option>
									<option>美容護理</option>
									<option>人氣商品</option>
									<option>其他</option>
								</select> <input type="text" placeholder="請輸入關鍵字...." name="usersearch" /> <input type="hidden" name="action"
									value="search_from_header">
								<button type="submit" value="搜尋"></button>
							</form>
						</div>
						<div class="header-action-right">
							<div class="header-action-2">
								<div class="header-action-icon-2">
									<a class="mini-cart-icon" href="shop-cart.html"> <img alt="Cart"
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-cart.svg" /> <span
										class="pro-count blue">2</span>
									</a> <a href="shop-cart.html"><span class="lable">購物車</span></a>
									<div class="cart-dropdown-wrap cart-dropdown-hm2">
										<ul>
											<li>
												<div class="shopping-cart-img">
													<a href="shop-product-right.html"><img alt="Shop"
														src="<%=request.getContextPath()%>/assets/imgs/shop/thumbnail-3.jpg" /></a>
												</div>
												<div class="shopping-cart-title">
													<h4>
														<a href="shop-product-right.html">Daisy Casual Bag</a>
													</h4>
													<h4>
														<span>1 × </span>$800.00
													</h4>
												</div>
												<div class="shopping-cart-delete">
													<a href="#"><i class="fi-rs-cross-small"></i></a>
												</div>
											</li>
											<li>
												<div class="shopping-cart-img">
													<a href="shop-product-right.html"><img alt="Nest"
														src="<%=request.getContextPath()%>/assets/imgs/shop/thumbnail-2.jpg" /></a>
												</div>
												<div class="shopping-cart-title">
													<h4>
														<a href="shop-product-right.html">Corduroy Shirts</a>
													</h4>
													<h4>
														<span>1 × </span>$3200.00
													</h4>
												</div>
												<div class="shopping-cart-delete">
													<a href="#"><i class="fi-rs-cross-small"></i></a>
												</div>
											</li>
										</ul>
										<div class="shopping-cart-footer">
											<div class="shopping-cart-total">
												<h4>
													Total <span>$4000.00</span>
												</h4>
											</div>
											<div class="shopping-cart-button">
												<a href="shop-cart.html" class="outline">View cart</a> <a href="shop-checkout.html">Checkout</a>
											</div>
										</div>
									</div>
								</div>
								<div class="header-action-icon-2">
									<a href="page-account.html"> <img class="svgInject" alt="Account"
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-user.svg" />
									</a> <a href="page-account.html"><span class="lable ml-0">會員中心</span></a>
									<div class="cart-dropdown-wrap cart-dropdown-hm2 account-dropdown">
										<ul>
											<li><a href="page-account.html"><i class="fi fi-rs-user mr-10"></i>會員中心</a></li>
											<li><a href="page-account.html"><i class="fi fi-rs-location-alt mr-10"></i>訂單管理</a></li>
											<li><a href="page-account.html"><i class="fi fi-rs-settings-sliders mr-10"></i>帳戶設定</a></li>
											<li><a href="page-login.html"><i class="fi fi-rs-sign-out mr-10"></i>登出</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header-bottom header-bottom-bg-color sticky-bar">
			<div class="container">
				<div class="header-wrap header-space-between position-relative">
					<div class="logo logo-width-1 d-block d-lg-none">
						<a href="index.html"><img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
							alt="logo" /></a>
					</div>
					<div class="header-nav d-none d-lg-flex">
						<div class="main-categori-wrap d-none d-lg-block">
							<a class="categories-button-active" href="#"> <span class="fi-rs-apps"></span> <span class="et">瀏覽所有類別</span>
								<i class="fi-rs-angle-down"></i>
							</a>
							<div class="categories-dropdown-wrap categories-dropdown-active-large font-heading">
								<div class="d-flex categori-dropdown-inner">
									<ul>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-1.svg" alt="" />寵物飼料
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-2.svg" alt="" />寵物罐頭
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-3.svg" alt="" />寵物保健
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-4.svg" alt="" />寵物零食
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-5.svg" alt="" />寵物玩具
										</a></li>
									</ul>
									<ul class="end">
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-6.svg" alt="" />居家用品
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-7.svg" alt="" />外出用品
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-8.svg" alt="" />美容護理
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-9.svg" alt="" />人氣商品
										</a></li>
										<li><a href="shop-grid-right.html"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-10.svg" alt="" />其他
										</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="main-menu main-menu-padding-1 main-menu-lh-2 d-none d-lg-block font-heading">
							<nav>
								<ul>
									<li class="hot-deals"><img
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-hot.svg" alt="hot deals" /> <a
										href="shop-grid-right.html">熱門商品</a></li>
									<li><a style="color: #386641" class="active" href="index.html">商城首頁</a></li>
									<li><a href="page-about.html">關於我們</a></li>
									<li><a href="shop-grid-right.html">寵物糧食<i class="fi-rs-angle-down"></i></a>
										<ul class="sub-menu">
											<li><a href="shop-grid-right.html">品牌飼料</a></li>
											<li><a href="shop-grid-left.html">貓狗罐罐</a></li>
											<li><a href="shop-list-right.html">貓狗零食</a></li>
											<li><a href="shop-list-left.html">寵物保健</a></li>
										</ul></li>
									<li><a href="#">生活用品 <i class="fi-rs-angle-down"></i></a>
										<ul class="sub-menu">
											<li><a href="vendors-grid.html">居家用品</a></li>
											<li><a href="vendors-list.html">外出用品</a></li>
											<li><a href="vendor-details-1.html">寵物玩具</a></li>
											<li><a href="vendor-details-2.html">美容護理</a></li>
										</ul></li>
									<li><a href="#">寵物服務 </a></li>
									<li><a href="blog-category-grid.html">部落格首頁 </a></li>
									<li><a href="page-contact.html" style="margin-right: 80px;">聯絡我們</a></li>
								</ul>
							</nav>
						</div>
					</div>
					<div class="header-action-icon-2 d-block d-lg-none">
						<div class="burger-icon burger-icon-white">
							<span class="burger-icon-top"></span> <span class="burger-icon-mid"></span> <span
								class="burger-icon-bottom"></span>
						</div>
					</div>
					<div class="header-action-right d-block d-lg-none">
						<div class="header-action-2">

							<div class="header-action-icon-2">
								<a class="mini-cart-icon" href="shop-cart.html"> <img alt="Account"
									src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-cart.svg" /> <span
									class="pro-count white">2</span>
								</a>

								<div class="header-action-icon-2">
									<a href="page-account.html"> <img class="svgInject" alt="user"
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-user.svg" />
									</a> <a href="page-account.html"><span class="lable ml-0">會員中心</span></a>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="mobile-header-active mobile-header-wrapper-style">
		<div class="mobile-header-wrapper-inner">
			<div class="mobile-header-top">
				<div class="mobile-header-logo">
					<a href="index.html"><img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
						alt="logo" /></a>
				</div>
				<div class="mobile-menu-close close-style-wrap close-style-position-inherit">
					<button class="close-style search-close">
						<i class="icon-top"></i> <i class="icon-bottom"></i>
					</button>
				</div>
			</div>
			<div class="mobile-header-content-area">
				<div class="mobile-search search-style-3 mobile-header-border">
					<form action="#">
						<input type="text" placeholder="請輸入關鍵字..." />
						<button type="submit">
							<i class="fi-rs-search"></i>
						</button>
					</form>
				</div>
				<div class="mobile-menu-wrap mobile-header-border">
					<!-- mobile menu start -->
					<nav>
						<ul class="mobile-menu font-heading">
							<li class="menu-item-has-children"><a href="index.html">商城首頁</a></li>
							<li class="menu-item-has-children"><a href="shop-grid-right.html">寵物糧食</a>
								<ul class="dropdown">
									<li><a href="shop-grid-right.html">品牌飼料</a></li>
									<li><a href="shop-grid-left.html">貓狗罐罐</a></li>
									<li><a href="shop-list-right.html">貓狗零食</a></li>
									<li><a href="shop-list-left.html">寵物保健</a></li>
								</ul></li>
							<li class="menu-item-has-children"><a href="#">生活用品</a>
								<ul class="dropdown">
									<li><a href="vendors-grid.html">居家用品</a></li>
									<li><a href="vendors-list.html">外出用品</a></li>
									<li><a href="vendor-details-1.html">寵物玩具</a></li>
									<li><a href="vendor-details-2.html">美容護理</a></li>
								</ul></li>
							<li class="menu-item-has-children"><a href="#">寵物服務</a></li>
							<li class="menu-item-has-children"><a href="blog-category-fullwidth.html">部落格首頁</a></li>
							<li class="menu-item-has-children"><a href="page-contact.html">聯絡我們</a></li>
						</ul>
					</nav>
					<!-- mobile menu end -->
				</div>
				<div class="mobile-header-info-wrap">
					<div class="single-mobile-header-info">
						<a href="page-contact.html"><i class="fi-rs-marker"></i>台北市中山區南京東路三段219號5樓 </a>
					</div>
					<div class="single-mobile-header-info">
						<a href="page-login.html"><i class="fi-rs-user"></i>登入 / 登出 </a>
					</div>
					<div class="single-mobile-header-info">
						<a href="#"><i class="fi-rs-headphones"></i>02-2712-0589 </a>
					</div>
				</div>
				<!--
                    <div class="mobile-social-icon mb-50">
                        <h6 class="mb-15">Follow Us</h6>
                        <a href="#"><img src="assets/imgs/theme/icons/icon-facebook-white.svg" alt="" /></a>
                        <a href="#"><img src="assets/imgs/theme/icons/icon-twitter-white.svg" alt="" /></a>
                        <a href="#"><img src="assets/imgs/theme/icons/icon-instagram-white.svg" alt="" /></a>
                        <a href="#"><img src="assets/imgs/theme/icons/icon-pinterest-white.svg" alt="" /></a>
                        <a href="#"><img src="assets/imgs/theme/icons/icon-youtube-white.svg" alt="" /></a>
                    </div>
                    -->
				<div class="site-copyright">Copyright 2022 © Petting. All rights reserved.</div>
			</div>
		</div>
	</div>
	<!--End header-->

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