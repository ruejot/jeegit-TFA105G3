<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<script src="https://kit.fontawesome.com/7e95db7a28.js" crossorigin="anonymous"></script>
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
						<a href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp"><img
							src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
							alt="logo" /></a>
					</div>
					<div class="header-right">
						<div class="search-style-2">
							<form action="../product/SearchServlet" method="GET">
<!-- 								<select class="select-active"> -->
<!-- 									<option>所有類別</option> -->
<!-- 									<option>寵物飼料</option> -->
<!-- 									<option>寵物罐頭</option> -->
<!-- 									<option>寵物保健</option> -->
<!-- 									<option>寵物零食</option> -->
<!-- 									<option>寵物玩具</option> -->
<!-- 									<option>居家用品</option> -->
<!-- 									<option>外出用品</option> -->
<!-- 									<option>美容護理</option> -->
<!-- 									<option>人氣商品</option> -->
<!-- 									<option>其他</option> -->
<!-- 								</select>  -->
								<input type="text" placeholder="請輸入關鍵字...." name="usersearch" />
								<input type="hidden" name="action" value="search_from_header">
								<button type="submit" value="搜尋"></button>
							</form>
						</div>
						<!-- choose -->
						<!-- 	when，是BUS的情況。test BusUsing != null -->
						<!-- 	otherwise，其他情況 -->
						<!-- 		這裡共同區塊，for購物車按鈕的設定 -->
						<!-- 		if，是Members的情況。test MemberUsing != null -->
						<!-- 		if，是遊客的情況。test BusUsing == null && MemberUsing == null -->
						<!-- /choose -->
						<c:choose>
							<c:when test="${BusUsing != null}">
								<div class="header-action-right">
									<div class="header-action-2">
										<div class="header-action-icon-2">
											<a href="<%=request.getContextPath()%>/nest-backend/productManage.jsp">
											<span class="lable mr-10" style="font-size: 18px; color: #3bb77e;"><i class="fa-regular fa-shelves fa-lg mr-5"></i>商品管理</span></a>
										</div>
										<div class="header-action-icon-2">
											<a href="<%=request.getContextPath()%>/nest-backend/orderManage.jsp">
												<span class="lable mr-10" style="font-size: 18px; color: #3bb77e;"><i class="far fa-folder-open fa-lg mr-5"></i>訂單管理</span></a>
										</div>
										<div class="header-action-icon-2">
											<a>
											<span class="label mr-50" style="font-size: 18px; color: #3bb77e;"><i class="fa-regular fa-user-large fa-lg mr-5"></i>合作廠商</span></a>
											<div
												class="cart-dropdown-wrap cart-dropdown-hm2 account-dropdown">
												<ul>
													<li><a
														href="<%=request.getContextPath()%>/nest-backend/busAccountCenter.jsp">
															<i class="fi fi-rs-user mr-10"></i>廠商資料
													</a></li>
													<li><a
														href="<%=request.getContextPath()%>/nest-frontend/fendshop_mainpage.jsp?action=fromBackendSelf">
															<i class="fi fi-rs-settings-sliders mr-10"></i>我的商店
													</a></li>
													<li><a
														href="<%=request.getContextPath()%>/bus/BusLogout?action=logout">
<!-- 														<input type="hidden" name="action" value="logout"> -->
															<i class="fi fi-rs-sign-out mr-10"></i>廠商登出
													</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="header-action-right">
									<div class="header-action-2">
										<div class="header-action-icon-2">
											<a class="mini-cart-icon" href="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart"> 
<!-- 											<img -->
<!-- 												alt="Cart" -->
<%-- 												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-cart.svg" /> --%>
<!-- 												<span class="pro-count blue">N</span> -->
<%-- 											</a> <a href="<%=request.getContextPath()%>/nest-frontend/shopCart.jsp"> --%>
											<span class="lable mr-20" style="font-size: 18px; color: #3bb77e;"><i class="fa-regular fa-cart-shopping fa-lg mr-5"></i>購物車</span></a>
										</div>
										<c:if test="${MemberUsing != null}">
										<div class="header-action-icon-2">
											<a>
											<span class="label mr-50" style="font-size: 18px; color: #3bb77e;"><i class="fa-regular fa-user-large fa-lg mr-5"></i>歡迎回來</span></a>
											<div
													class="cart-dropdown-wrap cart-dropdown-hm2 account-dropdown">
													<ul>
														<li><a href="<%=request.getContextPath()%>/nest-frontend/accountCenter.jsp"><i
																class="fi fi-rs-user mr-10"></i>會員中心</a></li>
														<li><a href="<%=request.getContextPath()%>/nest-backend/blog_manage.jsp"><i
																class="fi fi-rs-settings-sliders mr-10"></i>部落格管理</a></li>
														<li><a href="<%=request.getContextPath()%>/members/MembersLogout?action=logout"><i
																class="fi fi-rs-sign-out mr-10"></i>會員登出</a>
<!-- 																<input type="hidden" name="action" value="logout"> </li> -->
													</ul>
												</div>
											</div>
										</c:if>
										<c:if test="${BusUsing == null && MemberUsing == null}">
										<!-- 都沒登入===遊客 -->
											<div class="header-action-icon-2">
												<a href="<%=request.getContextPath()%>/nest-frontend/Login.jsp"> 
<!-- 												<img class="svgInject" -->
<!-- 													alt="Account" -->
<%-- 													src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-user.svg" /> --%>
<%-- 												</a> <a href="<%=request.getContextPath()%>/nest-frontend/Login.jsp"> --%>
												<span class="label mr-50" style="font-size: 18px; color: #3bb77e;"><i class="fa-regular fa-user-large fa-lg mr-5"></i>登入</span></a>
											</div>
										</c:if>
									</div>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
		</div>
		<div class="header-bottom header-bottom-bg-color sticky-bar">
			<div class="container">
				<div class="header-wrap header-space-between position-relative">
					<div class="logo logo-width-1 d-block d-lg-none">
						<a href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp"><img
							src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
							alt="logo" /></a>
					</div>
					<div class="header-nav d-none d-lg-flex">
						<div class="main-categori-wrap d-none d-lg-block">
							<a class="categories-button-active" href="#"> <span
								class="fi-rs-apps"></span> <span class="et">瀏覽所有類別</span> <i
								class="fi-rs-angle-down"></i>
							</a>
							<div
								class="categories-dropdown-wrap categories-dropdown-active-large font-heading">
								<div class="d-flex categori-dropdown-inner">
									<ul>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物食品"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-1.svg"
												alt="" />寵物飼料
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物罐頭"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-2.svg"
												alt="" />寵物罐頭
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=居家清潔"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-3.svg"
												alt="" />寵物保健
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物食品"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-4.svg"
												alt="" />寵物零食
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物玩具"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-5.svg"
												alt="" />寵物玩具
										</a></li>
									</ul>
									<ul class="end">
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=生活用品"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-6.svg"
												alt="" />居家用品
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=外出用品"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-7.svg"
												alt="" />外出用品
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=美容護理"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-8.svg"
												alt="" />美容護理
										</a></li>
										<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=居家清潔"> <img
												src="<%=request.getContextPath()%>/assets/imgs/theme/icons/category-9.svg"
												alt="" />居家清潔
										</a></li>
										
									</ul>
								</div>
							</div>
						</div>
						<div
							class="main-menu main-menu-padding-1 main-menu-lh-2 d-none d-lg-block font-heading">
							<nav>
								<ul>
									<li><img
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-hot.svg"
										alt="hot deals" /><a style="color: #386641" class="active"
										href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp">商城首頁</a></li>
									<li><a>寵物糧食<i
											class="fi-rs-angle-down"></i></a>
										<ul class="sub-menu">
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=貓犬飼料">貓犬飼料</a></li>
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=貓抓板">貓抓板</a></li>
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=零食點心">零食點心</a></li>
										</ul></li>
									<li><a>生活用品 <i class="fi-rs-angle-down"></i></a>
										<ul class="sub-menu">
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=清潔">清潔用品</a></li>
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物玩具">寵物玩具</a></li>
											<li><a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&mainCategory=美容用具">美容裝飾</a></li>
										</ul></li>
									<li><a href="<%=request.getContextPath()%>/nest-frontend/blog-category-big2.jsp">部落格首頁</a></li>
									<li><a href="<%=request.getContextPath()%>/nest-frontend/contact_us.jsp"
										style="margin-right: 80px;">關於我們</a></li>
								</ul>
							</nav>
						</div>
					</div>
					<div class="header-action-icon-2 d-block d-lg-none">
						<div class="burger-icon burger-icon-white">
							<span class="burger-icon-top"></span> <span
								class="burger-icon-mid"></span> <span class="burger-icon-bottom"></span>
						</div>
					</div>
					<!-- 以下是小尺寸視窗時的顯示結果 -->
					<div class="header-action-right d-block d-lg-none">
						<div class="header-action-2">

							<div class="header-action-icon-2">
								<a class="mini-cart-icon label mr-10" href="<%=request.getContextPath()%>/nest-frontend/shopCart.jsp"> 
								<img
									alt="Account"
									src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-cart.svg" />
<!-- 									<span class="pro-count white">4</span> -->
								</a>
								</div>

								<div class="header-action-icon-2">
									<a href="<%=request.getContextPath()%>/nest-frontend/Login.jsp"> 
									<img class="svgInject"
										alt="user"
										src="<%=request.getContextPath()%>/assets/imgs/theme/icons/icon-user.svg" />
										<span class="label mr-10" style="font-size: 22px;"></span></a>

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
					<a href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp"><img
						src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
						alt="logo" /></a>
				</div>
				<div
					class="mobile-menu-close close-style-wrap close-style-position-inherit">
					<button class="close-style search-close">
						<i class="icon-top"></i> <i class="icon-bottom"></i>
					</button>
				</div>
			</div>
			<div class="mobile-header-content-area">
				<div class="mobile-search search-style-3 mobile-header-border">
					<form action="../product/SearchServlet" method="GET">
						<input type="text" placeholder="請輸入關鍵字...." name="usersearch" />
						<input type="hidden" name="action" value="search_from_header">
						<button type="submit" value="搜尋"></button>
					</form>
				</div>
<!-- 				<div class="mobile-menu-wrap mobile-header-border"> -->
<!-- 					mobile menu start -->
<!-- 					<nav> -->
<!-- 						<ul class="mobile-menu font-heading"> -->
<!-- 							<li class="menu-item-has-children"><a href="index.html">商城首頁</a></li> -->
<!-- 							<li class="menu-item-has-children"><a -->
<!-- 								href="shop-grid-right.html">寵物糧食</a> -->
<!-- 								<ul class="dropdown"> -->
<!-- 									<li><a href="shop-grid-right.html">品牌飼料</a></li> -->
<!-- 									<li><a href="shop-grid-left.html">貓狗罐罐</a></li> -->
<!-- 									<li><a href="shop-list-right.html">貓狗零食</a></li> -->
<!-- 									<li><a href="shop-list-left.html">寵物保健</a></li> -->
<!-- 								</ul></li> -->
<!-- 							<li class="menu-item-has-children"><a href="#">生活用品</a> -->
<!-- 								<ul class="dropdown"> -->
<!-- 									<li><a href="vendors-grid.html">居家用品</a></li> -->
<!-- 									<li><a href="vendors-list.html">外出用品</a></li> -->
<!-- 									<li><a href="vendor-details-1.html">寵物玩具</a></li> -->
<!-- 									<li><a href="vendor-details-2.html">美容護理</a></li> -->
<!-- 								</ul></li> -->
<!-- 							<li class="menu-item-has-children"><a href="#">寵物服務</a></li> -->
<!-- 							<li class="menu-item-has-children"><a -->
<!-- 								href="blog-category-fullwidth.html">部落格首頁</a></li> -->
<!-- 							<li class="menu-item-has-children"><a -->
<!-- 								href="page-contact.html">關於我們</a></li> -->
<!-- 						</ul> -->
<!-- 					</nav> -->
<!-- 					mobile menu end -->
<!-- 				</div> -->
				
				<div class="mobile-header-info-wrap">
					<div class="single-mobile-header-info">
						<a href="<%=request.getContextPath()%>/nest-frontend/contact_us.jsp"><i class="fi-rs-marker"></i>台北市中山區南京東路三段219號5樓
						</a>
					</div>
					<div class="single-mobile-header-info">
					<c:choose>
						<c:when test="${not(BusUsing == null) || not(MemberUsing == null)}">
						<!-- 有任一身分登入 -->
						<a href="<%=request.getContextPath()%>/members/MembersLogout?action=logout"><i class="fi-rs-user"></i>登入 / 登出 </a>
						</c:when>
						<c:otherwise>
						<a href="<%=request.getContextPath()%>/nest-frontend/Login.jsp"><i class="fi-rs-user"></i>登入 / 登出 </a>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="single-mobile-header-info">
						<a><i class="fi-rs-headphones"></i>02-2712-0589 </a>
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
				<div class="site-copyright">Copyright 2022 © Petting. All
					rights reserved.</div>
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