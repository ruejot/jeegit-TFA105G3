<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.productImg.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product.controller.*"%>
<!DOCTYPE html>
<%-- 取出 Concroller ProductJumpServlet.java存入request的一件商品--%>
<% List<ProductVO> aProd = (List<ProductVO>) request.getAttribute("aProd");%>
<% List<ProductVO> relatedProd = (List<ProductVO>) request.getAttribute("relatedProd"); %>

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
                                                    <figure class="border-radius-10" style="align-content: stretch">
                                                        <img src="<%=request.getContextPath()%>/getproductPic?aa=${prodImgVO.imgid}" alt="product image" />
                                                    </figure>
                                                    </c:forEach>
                                                </div>
                                    <!-- THUMBNAILS 商品圖片小圖-->
                                                <div class="slider-nav-thumbnails">
                                                <c:forEach var="prodImgVO" items="${aProd}" >
                                                    <div><img src="<%=request.getContextPath()%>/getproductPic?aa=${prodImgVO.imgid}" alt="product image" /></div>
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
 											<form action="../CartServlet" method="GET">
                                                <div class="detail-extralink mb-50">
                                                    <div class="detail-qty border radius">
                                                        <a class="qty-down"><i class="fi-rs-angle-small-down"></i></a>
															<span class="qty-val" id="s_qty" >1</span>
                                                        <a class="qty-up"><i class="fi-rs-angle-small-up"></i></a>
                                                    </div>
	                                                    <div class="product-extra-link2">
	                                                        <button type="submit" class="button button-add-to-cart" id="btn_submit" ><i class="fi-rs-shopping-cart"></i>加入購物車</button>
															<input type="hidden" name="action" value="add">                                                    
	                                                        <input type="hidden" name="qty"  id="i_qty" value="1">   
															<input type="hidden" name="merId" value="${aProd.get(0).merid}">                                                    
															<input type="hidden" name="busId" value="${aProd.get(0).busid}">                                                    
															<input type="hidden" name="price" value="${aProd.get(0).price}">                                                    
															<input type="hidden" name="name" value="${aProd.get(0).name}">                                                    
	                                                    </div>
                                                	</div>
                                          	</form>
                                                        <a class="btn btn-xs" href="<%=request.getContextPath()%>/nest-frontend/ShopMainpage.do?busid=${aProd.get(0).busid}&action=show_Shop_Mainpage" >
    													查看商店<i class="fi-rs-arrow-small-right"></i>
														</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-60">
                                        <div class="col-12">
                                            <h2 class="section-title style-1 mb-30">相關商品</h2>
                                        </div>
                                        <div class="col-12">
                                            <div class="row related-products">
	                                            <c:forEach var="relatedProd" items="${relatedProd}" end="9" >
	                                                <div class="col-lg-3 col-md-4 col-12 col-sm-6">
	                                                    <div class="product-cart-wrap hover-up">
	                                                        <a href="<%=request.getContextPath()%>/product/ProductJump?merid=${relatedProd.merid}&action=product_jump" tabindex="0">
	                                                        <div class="product-content-wrap">
	                                                            <div class="product-price">
	                                                            <h2>${relatedProd.name}</h2>
	                                                                <span>$ ${relatedProd.price} </span>
	                                                            </div>
	                                                                <img style="height:150px ;width:150px " src="<%=request.getContextPath()%>/ProdFirstPic?aa=${relatedProd.merid}">
	                                                        </div>
	                                                        </a>
	                                                    </div>
	                                                </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 primary-sidebar sticky-sidebar mt-30">

                                <!-- Fillter By Price -->
<!--                                 <div class="sidebar-widget price_range range mb-30"> -->
<!--                                     <h5 class="section-title style-1 mb-30">價格區間</h5> -->
<!--                                     <div class="price-filter"> -->
<!--                                         <div class="price-filter-inner"> -->
<!-- <!--                                             <div id="slider-range" class="mb-20"></div> SLIDER BAR -->
<!--                                             <div class="d-flex justify-content-between"> -->
<!--                                             <input type="range" min="0" max="10000" value= "1" id="p_price"> -->
<!--                                                 <div class="text-brand">$<strong id="p_price_value" ></strong> -->
<!--                                                 </div> -->
<!-- 											</div> -->
<!--                                                 <button type="submit" class="button button-add-to-cart">查詢</button> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
                            
                            </div>
                        </div>
                    </div>
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
	
	<script >
	
	
	var s_qty = document.querySelector("#s_qty");
	var i_qty = document.querySelector("#i_qty");

		$("#btn_submit").click(function(){
			var qty = s_qty.textContent;
			$("#i_qty").val(qty);
		});

	
	
	
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
</body>
</html>

