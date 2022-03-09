<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bus.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productImg.model.*"%>
<%@ page import="java.util.*"%>
<!-- [writer] wei -->

<%
BusService busSvc = new BusService();
BusVO busVO = null;
Integer busidFromBackend = null;

ProductService productSvc = new ProductService();
List<ProductVO> productlist_shopMp = null;

String action = request.getParameter("action");

// 正常來說，一定是從某頁面連到shop_mainpage，必定會攜帶轉跳資訊(預計顯示商家的busid)

if (request.getAttribute("busVO_From_ShopProductPage") != null) {
// 如果是從ShopProductPage.jsp連過來的，顯示該商家主頁。
	busVO = (BusVO) request.getAttribute("busVO_From_ShopProductPage");
	productlist_shopMp = productSvc.getProductsByBusid(busVO.getBusid());
} else if ("fromBackendSelf".equals(action)) {
// 如果是從後端連過來看自己商家首頁的
	busVO = (BusVO) session.getAttribute("BusUsing");
	productlist_shopMp = productSvc.getProductsByBusid(busVO.getBusid());
} else {
// 如果不是，就顯示預設的商家id_4。(測試用，e.g.直接點開fendshop_maimpage.jsp)
	busVO = busSvc.select(2004); // 預設顯示bus_id=4的商店
	productlist_shopMp = productSvc.getProductsByBusid(4);
}

session.setAttribute("busVO_shopMpage", busVO);
request.setAttribute("productlist_shopMp", productlist_shopMp);
session.setAttribute("sourceMainPage", request.getRequestURI());
%>

<%
// // ___不透過另一個servlet_(ShowShopMainpage.java)中介轉跳setAttribute，再從fendshop_mainpae.jsp取得busVO
// // 而是直接從ShopProductPage.jsp連到fendshop_mainpae.jsp，透過getParameter取得busid.
// // 但request.getParameter("busid")找不到會發生500。

// BusService busSvc = new BusService();
// BusVO busVO = null;
// ProductService productSvc = new ProductService();
// List<ProductVO> productlist_shopMp = null;

// Integer busid = Integer.parseInt(request.getParameter("busid"));

// if(request.getParameter("busid") != null){
// 	busVO = busSvc.select(busid);
// 	productlist_shopMp = productSvc.getProductsByBusid(busid); // busVO.getBusid()
// } else {
// // 不會進到else，因為request.getParameter("busid")找不到會發生500。
// // 如果不是，就顯示預設的商家。(測試用，e.g.直接點開fendshop_maimpage.jsp)
// 	busVO = busSvc.select(4); // 預設顯示bus_id=4的商店
// 	productlist_shopMp = productSvc.getProductsByBusid(4);
// }

// pageContext.setAttribute("busVO", busVO);
// pageContext.setAttribute("productlist_shopMp", productlist_shopMp);
%>

<% 
// 顯示全部，測試用
// ProductService productSvc = new ProductService();
// List<ProductVO> productlist_shopMp = productSvc.getAll();
// session.setAttribute("productlist_shopMp", productlist_shopMp);
%>

<% 
// (測試用)如果沒有轉跳來源，默認顯示busid = 4的商家;
// BusVO busVO = busSvc.select(4);

// 如果有轉跳，就顯示那一個busid的內容。
// (session內有"BusUsing"，即 session.getAttribute("MemberUsing") != null
// if(session.getAttribute("BusUsing")!=null){
// 	busVO = ((BusVO) session.getAttribute("BusUsing"));
// }
// 上面邏輯怪怪的，如果是遊客, 個人登入要怎麼看到某一特定的商家首頁?
%>

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>商家首頁-Petting</title>
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
<!-- Manually open slider-range.css if need. it correspond with slider-range.js -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" /> --%>
<!-- Manually open animate.min.css if you need. -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>

<body>
	<jsp:include page="/views/userHeader.jsp" />
	<!--End userHeader-->
	<main class="main pages">
		<!-- <main class="page-header breadcrumb-wrap"> 是 首頁>其他頁 顯示列 this block 至少有2種樣式，有的頁面甚至沒有page-header區塊，會再確認userMainPage-header.jsp這樣分割是否適合。 -->
		<!-- 開始寫此jsp頁內容，重點，從<div class="page-header..."> 的{同層下一區塊}開始寫內容 -->
		<div class="container mb-30">
			<div class="archive-header-2 text-center pt-70 pb-30">
				<h1 class="display-2 mb-50">${busVO_shopMpage.name}</h1>
				<!-- remove search, <div class="row"> -->
			</div>
			<div class="row flex-row-reverse">
				<div class="col-lg-4-5">
					<div class="shop-product-fillter">
						<div class="totall-product">
							<p>
								我們這裡有 <strong class="text-brand"><%=productlist_shopMp.size()%></strong> 項商品等著您來挑選！
							</p>
						</div>
					</div>
                        <div class="product-list mb-50">
                        	<!--開始single product開頭-->
                        	<c:forEach var="product" items="${productlist_shopMp}" end="<%=productlist_shopMp.size()%>" step="1" >
                            <div class="product-cart-wrap">
                                <div class="product-img-action-wrap">
                                    <div class="product-img product-img-zoom">
                                        <div class="product-img-inner">
                                            <a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
<!--                                             style="height:305px; max-width:100%; width:auto;" -->
                                                <img class="default-img" src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}" alt="" />
<%--                                                 src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}" --%>
<!-- 												src="assets/imgs/shop/product-2-1.jpg" -->
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-content-wrap">
                                    <div class="product-category mb-15">
                                        <p>分類「${product.subCategory}」</p>
                                    </div>
                                    <h2>
                                    <a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
                                    ${product.name}
                                    </a>
                                    </h2>
                                    <p class="mt-15 mb-35">${product.description}</p>

                                    <div class="product-price">
                                        <span>$${product.price}</span>
                                    </div>

                                    <div class="mt-30">
<%--                                         <a aria-label="Buy now" class="btn" href="<%=request.getContextPath()%>/nest-frontend/shopCart.jsp"><i class="fi-rs-shopping-cart mr-5"></i>加到購物車, 要改寫,目前是開頁面</a> --%>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            <!--single product-->
                        </div>
                        <!--product grid-->
					<div class="pagination-area mt-20 mb-20">
					</div>
				</div>
				<div class="col-lg-1-5 primary-sidebar sticky-sidebar">
					<div class="sidebar-widget widget-store-info mb-30 bg-3 border-0">
						<div class="vendor-logo mb-30">
							<img src="assets/imgs/vendor/vendor-16.png" alt="" />
						</div>
						<div class="vendor-info">
							<div class="product-category mb-5">
								<span class="text-muted">關於我們</span>
							</div>
							<h4 class="mb-10">
								<a class="text-heading text-align-center">${busVO_shopMpage.name}</a>
							</h4>

							<div class="vendor-des mb-30">
								<p class="font-sm text-heading">${empty busVO_shopMpage.intro?"您好，歡迎來到我的商店，這裡各式各樣的商品都是由我們親手精心挑選，來逛逛吧!":busVO_shopMpage.intro}</p>
							</div>
							<div class="follow-social mb-20">
								<h6 class="mb-15">聯絡我們</h6>
								<ul class="social-network">
									<li class="hover-up ml-40"><a href="https://www.facebook.com/ntoubakingclub/"> <img src="../assets/imgs/theme/icons/social-fb.svg" alt="" />
									</a></li>
									<li class="hover-up ml-20"><a href="https://www.instagram.com/cataholic.lover/"> <img src="../assets/imgs/theme/icons/social-insta.svg" alt="" />
									</a></li>
									<li class="hover-up ml-20"><a href="../nest-frontend/fendcs_mail.jsp"> <img src="../assets/imgs/theme/icons/email_nb.png" alt="" />
									</a></li>
								</ul>
							</div>

							<div class="vendor-info">
								<ul class="font-sm mb-20">
									<li><img class="mr-5" src="assets/imgs/theme/icons/icon-location.svg" alt="" /><strong>地址：
									</strong> <br><span>${busVO_shopMpage.address}</span></li>
									<li>　</li>
									<li><img class="mr-5" src="assets/imgs/theme/icons/icon-contact.svg" alt="" /><strong>電話：
									</strong> <span>${busVO_shopMpage.phone}</span></li>
								</ul>
								<form method="get" action="<%=request.getContextPath()%>/nest-backend/CsDetail.do">
									<button type="submit" class="btn btn-xs">立即表達您的需求給店家 <i
									class="fi-rs-arrow-small-right"></i></button>
									<input type="hidden" name="busid" value="${busVO_shopMpage.busid}">
									<input type="hidden" name="action" value="from_shopmain_to_CsReply_with_Busid">
<%-- 									<% request.getSession().setAttribute("sourcePage", request.getRequestURI()); %> --%>
<%-- 									<input type="hidden" name="sourcePage" value="<%=request.getServletPath()%>"> --%>
<%-- 									<% System.out.println(request.getRequestURI()); %> --%>
<%-- 									<% System.out.println(request.getServletPath()); %> --%>
								</form>
<%-- 									<a class="btn btn-xs" href="<%=request.getContextPath()%>/nest-frontend/fendshop_mainpage.jsp"> --%>
<!-- 									關於我<i class="fi-rs-arrow-small-right"></i> -->
<!-- 									</a> -->
							</div>
						</div>
					</div>
					<!-- Fillter By Price, block to n/a -->

					<!-- banner-img, block to n/a -->

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
	<!-- index-3.html, index-4, shop-*.html -->
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script>
	<!-- blog-post-fullwidth.html, shop-*.html -->
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
	<!-- Invoice JS, shop-inovice-*.html -->
	<!-- <script src="assets/js/invoice/jspdf.min.js"></script> -->
	<!-- <script src="assets/js/invoice/invoice.js"></script> -->
	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/shop.js?v=4.0"></script>
	<!-- ?v=4.0 -->
</body>
</html>

