<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.productImg.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product.controller.*"%>
<!DOCTYPE html>
<%-- 取出 Concroller ProductJumpServlet.java存入request的一件商品--%>
<% List<ProductVO> aProd = (List<ProductVO>) request.getAttribute("aProd");%>


<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting-商品資訊</title>
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
		<jsp:include page="/views/userMainPage-header.jsp" />
		 <div class="container">
            <!-- Main HERE -->
            <div class="container mb-30">
                <div class="row">
                    <div class="col-xl-11 col-lg-12 m-auto">
                        <div class="row flex-row-reverse">
                            <div class="col-xl-9">
                                <div class="product-detail accordion-detail">
                                    <div class="row mb-50 mt-30">
                                        <div class="col-md-6 col-sm-12 col-xs-12 mb-md-0 mb-sm-5">
                                            <div class="detail-gallery">
                                                <span class="zoom-icon"><i class="fi-rs-search"></i></span>
                                    <!-- MAIN SLIDES 商品圖片大圖 -->
                                                <div class="product-image-slider">
                                                    <c:forEach var="prodImgVO" items="${aProd}" >
                                                    <figure class="border-radius-10">
                                                        <img src="http://localhost:7080/jeeweb-TFA105G3/getproductPic?aa=${prodImgVO.imgid}" alt="product image" />
                                                    </figure>
                                                    </c:forEach>
                                                </div>
                                    <!-- THUMBNAILS 商品圖片小圖-->
                                                <div class="slider-nav-thumbnails">
                                                <c:forEach var="prodImgVO" items="${aProd}" >
                                                    <div><img src="http://localhost:7080/jeeweb-TFA105G3/getproductPic?aa=${prodImgVO.imgid}" alt="product image" /></div>
                                                </c:forEach>
                                                </div>
                                            </div>
                                            <!-- 商品資訊 -->
                                        </div>
                                        <div class="col-md-6 col-sm-12 col-xs-12">
                                            <div class="detail-info pr-30 pl-30">
                                                <span class="stock-status out-stock"> Sale Off </span>
                                                <h2 class="title-detail">${aProd.get(0).name}</h2>
                                                <div class="product-detail-rating">
                                                    <div class="product-rate-cover text-end">
                                                        <div class="product-rate d-inline-block">
                                                            <div class="product-rating" style="width: 90%"></div>
                                                        </div>
                                                        <span class="font-small ml-5 text-muted"> (32 reviews)</span>
                                                    </div>
                                                </div>
                                                <div class="clearfix product-price-cover">
                                                    <div class="product-price primary-color float-left">
                                                        <span class="current-price text-brand">$ ${aProd.get(0).price}</span>
                                                    </div>
                                                </div>
                                                <div class="short-desc mb-30">
                                                    <p class="font-lg">${aProd.get(0).description}</p>
                                                </div>
                                                <div class="detail-extralink mb-50">
                                                    <div class="detail-qty border radius">
                                                        <a href="#" class="qty-down"><i class="fi-rs-angle-small-down"></i></a>
                                                        <span class="qty-val">1</span>
                                                        <a href="#" class="qty-up"><i class="fi-rs-angle-small-up"></i></a>
                                                    </div>
                                                    <div class="product-extra-link2">
                                                        <button type="submit" class="button button-add-to-cart"><i class="fi-rs-shopping-cart"></i>加入購物車</button>
                                                    </div><br>
                                                        <a class="btn btn-xs" href="<%=request.getContextPath()%>/nest-frontend/ShopMainpage.do?busid=${aProd.get(0).busid}&action=show_Shop_Mainpage" style="background-color:#386641;">
    													查看商店<i class="fi-rs-arrow-small-right"></i>
														</a>
                                                </div>

                                            </div>
                                            <!-- Detail Info -->
                                        </div>
                                    </div>
                                                     <div class="product-info">
                                        <div class="tab-style3">
                                           <div class="tab-content shop_info_tab entry-main-content">
                                                <div class="tab-pane fade " id="Description">
                                                    <div class="">
                                                        <p>123123</p>
                                                    </div>
                                                </div>
                                               
                                                <div class="tab-pane fade show active" id="Reviews">
                                                    <!--Comments-->
                                                    <div class="comments-area">
                                                        <div class="row">
                                                            <div class="col-lg-8">
                                                                <h4 class="mb-30">Customer questions and answers</h4>
                                                                <div class="comment-list">
                                                                    <div class="single-comment justify-content-between d-flex mb-30">
                                                                        <div class="user justify-content-between d-flex">
                                                                            <div class="thumb text-center">
                                                                                <img src="assets/imgs/blog/author-2.png" alt="" />
                                                                                <a href="#" class="font-heading text-brand">Sienna</a>
                                                                            </div>
                                                                            <div class="desc">
                                                                                <div class="d-flex justify-content-between mb-10">
                                                                                    <div class="d-flex align-items-center">
                                                                                        <span class="font-xs text-muted">December 4, 2021 at 3:12 pm </span>
                                                                                    </div>
                                                                                    <div class="product-rate d-inline-block">
                                                                                        <div class="product-rating" style="width: 100%"></div>
                                                                                    </div>
                                                                                </div>
                                                                                <p class="mb-10">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Delectus, suscipit exercitationem accusantium obcaecati quos voluptate nesciunt facilis itaque modi commodi dignissimos sequi repudiandae minus ab deleniti totam officia id incidunt? <a href="#" class="reply">Reply</a></p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4">
                                                                <h4 class="mb-30">Customer reviews</h4>
                                                                <div class="d-flex mb-30">
                                                                    <div class="product-rate d-inline-block mr-15">
                                                                        <div class="product-rating" style="width: 90%"></div>
                                                                    </div>
                                                                    <h6>4.8 out of 5</h6>
                                                                </div>
                                                                <div class="progress">
                                                                    <span>5 star</span>
                                                                    <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">50%</div>
                                                                </div>
                                                                <div class="progress">
                                                                    <span>4 star</span>
                                                                    <div class="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">25%</div>
                                                                </div>
                                                                <div class="progress">
                                                                    <span>3 star</span>
                                                                    <div class="progress-bar" role="progressbar" style="width: 45%" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100">45%</div>
                                                                </div>
                                                                <div class="progress">
                                                                    <span>2 star</span>
                                                                    <div class="progress-bar" role="progressbar" style="width: 65%" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">65%</div>
                                                                </div>
                                                                <div class="progress mb-30">
                                                                    <span>1 star</span>
                                                                    <div class="progress-bar" role="progressbar" style="width: 85%" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100">85%</div>
                                                                </div>
                                                                <a href="#" class="font-xs text-muted">How are ratings calculated?</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--comment form-->
                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-60">
                                        <div class="col-12">
                                            <h2 class="section-title style-1 mb-30">相關商品</h2>
                                        </div>
                                        <div class="col-12">
                                            <div class="row related-products">
                                                <div class="col-lg-3 col-md-4 col-12 col-sm-6">
                                                    <div class="product-cart-wrap hover-up">
                                                        <div class="product-img-action-wrap">
                                                            <div class="product-img product-img-zoom">
                                                                <a href="shop-product-right.html" tabindex="0">
                                                                    <img class="default-img" src="assets/imgs/shop/product-2-1.jpg" alt="" />
                                                                    <img class="hover-img" src="assets/imgs/shop/product-2-2.jpg" alt="" />
                                                                </a>
                                                            </div>
                                                            <div class="product-action-1">
                                                                <a aria-label="Quick view" class="action-btn small hover-up" data-bs-toggle="modal" data-bs-target="#quickViewModal"><i class="fi-rs-search"></i></a>
                                                                <a aria-label="Add To Wishlist" class="action-btn small hover-up" href="shop-wishlist.html" tabindex="0"><i class="fi-rs-heart"></i></a>
                                                                <a aria-label="Compare" class="action-btn small hover-up" href="shop-compare.html" tabindex="0"><i class="fi-rs-shuffle"></i></a>
                                                            </div>
                                                            <div class="product-badges product-badges-position product-badges-mrg">
                                                                <span class="hot">Hot</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap">
                                                            <h2><a href="shop-product-right.html" tabindex="0">Ulstra Bass Headphone</a></h2>
                                                            <div class="rating-result" title="90%">
                                                                <span> </span>
                                                            </div>
                                                            <div class="product-price">
                                                                <span>$238.85 </span>
                                                                <span class="old-price">$245.8</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-4 col-12 col-sm-6">
                                                    <div class="product-cart-wrap hover-up">
                                                        <div class="product-img-action-wrap">
                                                            <div class="product-img product-img-zoom">
                                                                <a href="shop-product-right.html" tabindex="0">
                                                                    <img class="default-img" src="assets/imgs/shop/product-3-1.jpg" alt="" />
                                                                    <img class="hover-img" src="assets/imgs/shop/product-4-2.jpg" alt="" />
                                                                </a>
                                                            </div>
                                                            <div class="product-action-1">
                                                                <a aria-label="Quick view" class="action-btn small hover-up" data-bs-toggle="modal" data-bs-target="#quickViewModal"><i class="fi-rs-search"></i></a>
                                                                <a aria-label="Add To Wishlist" class="action-btn small hover-up" href="shop-wishlist.html" tabindex="0"><i class="fi-rs-heart"></i></a>
                                                                <a aria-label="Compare" class="action-btn small hover-up" href="shop-compare.html" tabindex="0"><i class="fi-rs-shuffle"></i></a>
                                                            </div>
                                                            <div class="product-badges product-badges-position product-badges-mrg">
                                                                <span class="sale">-12%</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap">
                                                            <h2><a href="shop-product-right.html" tabindex="0">Smart Bluetooth Speaker</a></h2>
                                                            <div class="rating-result" title="90%">
                                                                <span> </span>
                                                            </div>
                                                            <div class="product-price">
                                                                <span>$138.85 </span>
                                                                <span class="old-price">$145.8</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-4 col-12 col-sm-6">
                                                    <div class="product-cart-wrap hover-up">
                                                        <div class="product-img-action-wrap">
                                                            <div class="product-img product-img-zoom">
                                                                <a href="shop-product-right.html" tabindex="0">
                                                                    <img class="default-img" src="assets/imgs/shop/product-4-1.jpg" alt="" />
                                                                    <img class="hover-img" src="assets/imgs/shop/product-4-2.jpg" alt="" />
                                                                </a>
                                                            </div>
                                                            <div class="product-action-1">
                                                                <a aria-label="Quick view" class="action-btn small hover-up" data-bs-toggle="modal" data-bs-target="#quickViewModal"><i class="fi-rs-search"></i></a>
                                                                <a aria-label="Add To Wishlist" class="action-btn small hover-up" href="shop-wishlist.html" tabindex="0"><i class="fi-rs-heart"></i></a>
                                                                <a aria-label="Compare" class="action-btn small hover-up" href="shop-compare.html" tabindex="0"><i class="fi-rs-shuffle"></i></a>
                                                            </div>
                                                            <div class="product-badges product-badges-position product-badges-mrg">
                                                                <span class="new">New</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap">
                                                            <h2><a href="shop-product-right.html" tabindex="0">HomeSpeak 12UEA Goole</a></h2>
                                                            <div class="rating-result" title="90%">
                                                                <span> </span>
                                                            </div>
                                                            <div class="product-price">
                                                                <span>$738.85 </span>
                                                                <span class="old-price">$1245.8</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-4 col-12 col-sm-6 d-lg-block d-none">
                                                    <div class="product-cart-wrap hover-up mb-0">
                                                        <div class="product-img-action-wrap">
                                                            <div class="product-img product-img-zoom">
                                                                <a href="shop-product-right.html" tabindex="0">
                                                                    <img class="default-img" src="assets/imgs/shop/product-5-1.jpg" alt="" />
                                                                    <img class="hover-img" src="assets/imgs/shop/product-3-2.jpg" alt="" />
                                                                </a>
                                                            </div>
                                                            <div class="product-action-1">
                                                                <a aria-label="Quick view" class="action-btn small hover-up" data-bs-toggle="modal" data-bs-target="#quickViewModal"><i class="fi-rs-search"></i></a>
                                                                <a aria-label="Add To Wishlist" class="action-btn small hover-up" href="shop-wishlist.html" tabindex="0"><i class="fi-rs-heart"></i></a>
                                                                <a aria-label="Compare" class="action-btn small hover-up" href="shop-compare.html" tabindex="0"><i class="fi-rs-shuffle"></i></a>
                                                            </div>
                                                            <div class="product-badges product-badges-position product-badges-mrg">
                                                                <span class="hot">Hot</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap">
                                                            <h2><a href="shop-product-right.html" tabindex="0">Dadua Camera 4K 2021EF</a></h2>
                                                            <div class="rating-result" title="90%">
                                                                <span> </span>
                                                            </div>
                                                            <div class="product-price">
                                                                <span>$89.8 </span>
                                                                <span class="old-price">$98.8</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 primary-sidebar sticky-sidebar mt-30">

                                <!-- Fillter By Price -->
                                <div class="sidebar-widget price_range range mb-30">
                                    <h5 class="section-title style-1 mb-30">價格區間</h5>
                                    <div class="price-filter">
                                        <div class="price-filter-inner">
<!--                                             <div id="slider-range" class="mb-20"></div> SLIDER BAR -->
                                            <div class="d-flex justify-content-between">
                                            <input type="range" min="0" max="10000" value= "1" id="p_price">
                                                <div class="text-brand">$<strong id="p_price_value" class="detail-qty border radius"></strong>
                                                </div>
											</div>
                                                <button type="submit" class="button button-add-to-cart">查詢</button>
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
	<script >
	var p_price = document.querySelector("#p_price");
    var p_price_value = document.querySelector("#p_price_value");
	
	function p_value(){
		p_price_value.innerHTML = p_price.value;
		p_price.addEventListener("mousemove",function(){
			p_price_value.innerHTML = p_price.value;
		});
	}
	p_value();
	</script>
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

