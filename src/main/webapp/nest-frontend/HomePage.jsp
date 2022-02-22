<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@page import="com.merimg.service.MerImgService"%>
<%@page import="com.merimg.model.MerImgVO"%>
<!DOCTYPE html>
<%
ProductService productSvc = new ProductService();
ProductVO productbean = productSvc.getOneProduct(5);
pageContext.setAttribute("productbean", productbean);

List<ProductVO> productlist = productSvc.getAll();
pageContext.setAttribute("productlist", productlist);
%>

<html class="no-js" lang="en">
<head>
<meta charset="UTF-8" />
<title>Nest - Homepage</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta property="og:title" content="" />
<meta property="og:type" content="" />
<meta property="og:url" content="" />
<meta property="og:image" content="" />
<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="assets/imgs/theme/favicon.svg" />

<!-- Template CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main.css" />
</head>

<jsp:include page="/views/sellerHeader.jsp" />
<body>
<!-- 頭 上排十大類 -->
	<section class="popular-categories section-padding">
		<div class="container wow animate__animated animate__fadeIn">
			<div class="section-title">
				<div class="title">
					<h3>特色類別</h3>
					<ul class="list-inline nav nav-tabs links">
						<li class="list-inline-item nav-item"><a class="nav-link"
							href="shop-grid-left.html">寵物食品</a></li>
						<li class="list-inline-item nav-item"><a class="nav-link"
							href="shop-grid-left.html">寵物玩具</a></li>
						<li class="list-inline-item nav-item"><a
							class="nav-link active" href="shop-grid-left.html">居家清潔</a></li>
						<li class="list-inline-item nav-item"><a class="nav-link"
							href="shop-grid-left.html">美容用具</a></li>
					</ul>
				</div>
				<div
					class="slider-arrow slider-arrow-2 flex-right carausel-10-columns-arrow"
					id="carausel-10-columns-arrows"></div>
			</div>
			<div class="carausel-10-columns-cover position-relative">
				<div class="carausel-10-columns" id="carausel-10-columns">
					<div class="card-2 bg-9 wow animate__animated animate__fadeInUp"
						data-wow-delay=".1s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/罐頭.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">貓犬罐頭</a>
						</h6>
						<span>36 items</span>
					</div>
					<div class="card-2 bg-10 wow animate__animated animate__fadeInUp"
						data-wow-delay=".2s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/魚飼料.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">魚蝦飼料</a>
						</h6>
						<span>8 items</span>
					</div>
					<div class="card-2 bg-11 wow animate__animated animate__fadeInUp"
						data-wow-delay=".3s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/貓抓板.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">貓抓板</a>
						</h6>
						<span>14 items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay=".4s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/牽繩.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">外出牽繩</a>
						</h6>
						<span>24 items</span>
					</div>
					<div class="card-2 bg-13 wow animate__animated animate__fadeInUp"
						data-wow-delay=".5s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/狗零食.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">零食點心</a>
						</h6>
						<span>56 items</span>
					</div>
					<div class="card-2 bg-14 wow animate__animated animate__fadeInUp"
						data-wow-delay=".6s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="ShopProductPagev1.jsp"><img
								src="assets/imgs/shop/精靈球.png" alt="" /></a>
						</figure>
						<h6>
							<a href="ShopProductPagev1.jsp">精靈球</a>
						</h6>
						<span>18 items</span>
					</div>
					<div class="card-2 bg-15 wow animate__animated animate__fadeInUp"
						data-wow-delay=".7s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/catcloth.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">服裝</a>
						</h6>
						<span>36 items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay=".8s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/過濾器.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">過濾器材</a>
						</h6>
						<span>13 items</span>
					</div>
					<div class="card-2 bg-10 wow animate__animated animate__fadeInUp"
						data-wow-delay=".9s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/梳子.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">美容商品</a>
						</h6>
						<span>34 items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay="1s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/外出背包.png" alt="" /></a>
						</figure>
						<h6>
							<a href="shop-grid-left.html">寵物背包</a>
						</h6>
						<span>19 items</span>
					</div>
					<div class="card-2 bg-11 wow animate__animated animate__fadeInUp"
						data-wow-delay="0s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="shop-grid-left.html"><img
								src="assets/imgs/shop/洗澡精.png" alt="" /></a>
						</figure>
						assets/imgs/shop/catcloth.png
						<h6>
							<a href="shop-grid-left.html">清潔用品</a>
						</h6>
						<span>8 items</span>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- 尾 上排十大類 -->
	<main class="main">
	
		<section class="home-slider position-relative mb-30">
<!--頭 滑動廣告頁面-->
			<div class="container">
				<div class="home-slide-cover mt-30">
					<div class="hero-slider-1 style-4 dot-style-1 dot-style-1-position-1">
						<div class="single-hero-slider single-animation-wrap"
							style="background-image: url(assets/imgs/slider/slider-1.png)">
							<div class="slider-content">
								<h1 class="display-2 mb-40">
									趕緊加入<br /> 全台灣最ㄒㄧㄚ的寵物商店
								</h1>
								<p class="mb-65">訂閱每周優惠商品</p>
								<form class="form-subcriber d-flex">
									<input type="email" placeholder="電子信箱 emaill address" />
									<button class="btn" type="submit">訂閱</button>
								</form>
							</div>
						</div>
						<div class="single-hero-slider single-animation-wrap"
							style="background-image: url(assets/imgs/slider/slider-2.png)">
							<div class="slider-content">
								<h1 class="display-2 mb-40">
									品牌特惠!!<br /> &emsp;&emsp;3.18購物節
								</h1>
								<p class="mb-65">領取你的三折優惠券</p>
								<form class="form-subcriber d-flex">
									<input type="email" placeholder="電子信箱 emaill address" />
									<button class="btn" type="submit">領取</button>
								</form>
							</div>
						</div>
					</div>
					<div class="slider-arrow hero-slider-1-arrow"></div>
				</div>
			</div>
		</section>
	<!--尾 滑動廣告頁面-->
		<!--廣告頁面-->
		<section class="banners mb-25">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="banner-img wow animate__animated animate__fadeInUp"
							data-wow-delay="0">
							<img src="assets/imgs/banner/banner-1.png" alt="" />
							<div class="banner-text">
								<h4>
									&#129409;阿偉的商店&#129409; <br /> <br /> 寵物百貨
								</h4>
								<a href="shop-grid-left.html" class="btn btn-xs">逛起來 <i
									class="fi-rs-arrow-small-right"></i></a>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="banner-img wow animate__animated animate__fadeInUp"
							data-wow-delay=".2s">
							<img src="assets/imgs/banner/banner-2.png" alt="" />
							<div class="banner-text">
								<h4>
									Poké Ball ϞϞ(๑⚈ ․̫ ⚈๑)∩<br /> 寶可夢是種非常可愛的生物
								</h4>
								<a href="shop-grid-left.html" class="btn btn-xs">逛起來 <i
									class="fi-rs-arrow-small-right"></i></a>
							</div>
						</div>
					</div>
					<div class="col-lg-4 d-md-none d-lg-flex">
						<div
							class="banner-img mb-sm-0 wow animate__animated animate__fadeInUp"
							data-wow-delay=".4s">
							<img src="assets/imgs/banner/banner-3.png" alt="" />
							<div class="banner-text">
								<h4>
									寵物也要吃好好 <br />有機寵物鮮食
								</h4>
								<a href="shop-grid-left.html" class="btn btn-xs">逛起來 <i
									class="fi-rs-arrow-small-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--End banners-->
		<section class="product-tabs section-padding position-relative">
			<div class="container">
				<div class="section-title style-2 wow animate__animated animate__fadeIn">
					<h3>熱門商品</h3>
					<ul class="nav nav-tabs links" id="myTab" role="tablist">

						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-seven" data-bs-toggle="tab"
								data-bs-target="#tab-seven" type="button" role="tab"
								aria-controls="tab-seven" aria-selected="false">品牌飼料</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-two" data-bs-toggle="tab"
								data-bs-target="#tab-two" type="button" role="tab"
								aria-controls="tab-two" aria-selected="false">貓狗罐頭</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-three" data-bs-toggle="tab"
								data-bs-target="#tab-three" type="button" role="tab"
								aria-controls="tab-three" aria-selected="false">抓板玩具</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-four" data-bs-toggle="tab"
								data-bs-target="#tab-four" type="button" role="tab"
								aria-controls="tab-four" aria-selected="false">環境清潔</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-five" data-bs-toggle="tab"
								data-bs-target="#tab-five" type="button" role="tab"
								aria-controls="tab-five" aria-selected="false">居家用品</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="nav-tab-six" data-bs-toggle="tab"
								data-bs-target="#tab-six" type="button" role="tab"
								aria-controls="tab-six" aria-selected="false">外出用品</button>
						</li>
					</ul>
				</div>
				</div>
				<!--End nav-tabs熱門商品-->
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
						aria-labelledby="tab-one">
						<div class="row product-grid-4">
							<!--頭 熱門十商品-->
							<c:forEach var="product" items="${productlist}" end="9">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
												<a href="ShopProductPage.jsp"> <img class="default-img"
													src="http://localhost:7080/jeeweb-TFA105G3/ProdFirstPic?aa=${product.merid}"
													alt="" />
												</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="shop-grid-left.html">${product.subCategory}</a>
											</div>
											<h2>
												<a href="shop-product-right.html">${product.name}</a>
											</h2>
											<div class="product-rate-cover">
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 20%"></div>
												</div>
												<span class="font-small ml-5 text-muted"> (4.0)</span>
											</div>
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$${product.price}</span>
												</div>
												<div class="add-cart">
													<a class="add" href="shop-cart.html"><i
														class="fi-rs-shopping-cart mr-5"></i>Add </a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<!--頭 熱門十商品-->
						</div>
					</div>
				</div>
		</section>
		<!--Products Tabs今日熱銷-->
		<section class="section-padding pb-5">
			<div class="container">
				<div class="section-title wow animate__animated animate__fadeIn">
					<h3 class="">今日熱銷</h3>
					<!-- <ul class="nav nav-tabs links" id="myTab-2" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="nav-tab-one-1" data-bs-toggle="tab" data-bs-target="#tab-one-1" type="button" role="tab" aria-controls="tab-one" aria-selected="true">Featured</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-two-1" data-bs-toggle="tab" data-bs-target="#tab-two-1" type="button" role="tab" aria-controls="tab-two" aria-selected="false">Popular</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-three-1" data-bs-toggle="tab" data-bs-target="#tab-three-1" type="button" role="tab" aria-controls="tab-three" aria-selected="false">New added</button>
                            </li>
                        </ul> -->
				</div>
				<div class="row">
					<!-- <div class="col-lg-3 d-none d-lg-flex wow animate__animated animate__fadeIn">
                             <div class="banner-img style-2">
                                <div class="banner-text">
                                    <h2 class="mb-100">Bring nature into your home</h2>
                                    <a href="shop-grid-left.html" class="btn btn-xs">逛起來 <i class="fi-rs-arrow-small-right"></i></a>
                                </div>
                            </div>
                        </div> -->
					<div
						class="col-lg-9 col-md-12 wow animate__animated animate__fadeIn"
						data-wow-delay=".4s">
						<div class="tab-content" id="myTabContent-1">
							<div class="tab-pane fade show active" id="tab-one-1"
								role="tabpanel" aria-labelledby="tab-one-1">
								<div
									class="carausel-5-columns-cover arrow-center position-relative">
									<div
										class="slider-arrow slider-arrow-2 carausel-4-columns-arrow"
										id="carausel-4-columns-arrows"></div>
									<div class="carausel-4-columns carausel-arrow-center"
										id="carausel-4-columns">
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-1-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-1-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">輕鬆飛舞鳥飼料5Kg裝</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$799 </span> <span class="old-price">$999</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-5-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-5-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="new">Save 35%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">全天然果乾</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 20/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-2-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-2-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="sale">Sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">美格天然罐頭20罐入</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-3-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-3-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="best">Best sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">史考克美容刷 </a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$99 </span> <span class="old-price">$150</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-4-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-4-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">菁英寵物聰明豆</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$100 </span> <span class="old-price">$120</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>

									</div>
								</div>
							</div>
							<!--End tab-pane-->
							<div class="tab-pane fade" id="tab-two-1" role="tabpanel"
								aria-labelledby="tab-two-1">
								<div
									class="carausel-4-columns-cover arrow-center position-relative">
									<div
										class="slider-arrow slider-arrow-2 carausel-4-columns-arrow"
										id="carausel-4-columns-2-arrows"></div>
									<div class="carausel-4-columns carausel-arrow-center"
										id="carausel-4-columns-2">
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-10-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-10-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Canada Dry Ginger Ale
														– 2 L Bottle</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-15-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-15-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="new">Save 35%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Encore Seafoods
														Stuffed Alaskan</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-12-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-12-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="sale">Sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Gorton’s Beer
														Battered Fish </a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-13-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-13-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="best">Best sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Haagen-Dazs Caramel
														Cone Ice</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-14-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-14-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Italian-Style Chicken
														Meatball</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="tab-three-1" role="tabpanel"
								aria-labelledby="tab-three-1">
								<div
									class="carausel-4-columns-cover arrow-center position-relative">
									<div
										class="slider-arrow slider-arrow-2 carausel-4-columns-arrow"
										id="carausel-4-columns-3-arrows"></div>
									<div class="carausel-4-columns carausel-arrow-center"
										id="carausel-4-columns-3">
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-7-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-7-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Perdue Simply Smart
														Organics Gluten Free</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-8-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-8-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="new">Save 35%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Seeds of Change
														Organic Quinoa</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img" src="assets/imgs/shop/product-9-1.jpg"
														alt="" /> <img class="hover-img"
														src="assets/imgs/shop/product-9-2.jpg" alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="sale">Sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Signature Wood-Fired
														Mushroom</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-13-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-13-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="best">Best sale</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Simply Lemonade with
														Raspberry Juice</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
										<div class="product-cart-wrap">
											<div class="product-img-action-wrap">
												<div class="product-img product-img-zoom">
													<a href="shop-product-right.html"> <img
														class="default-img"
														src="assets/imgs/shop/product-14-1.jpg" alt="" /> <img
														class="hover-img" src="assets/imgs/shop/product-14-2.jpg"
														alt="" />
													</a>
												</div>
												<div class="product-action-1">
													<a aria-label="Quick view"
														class="action-btn small hover-up" data-bs-toggle="modal"
														data-bs-target="#quickViewModal"> <i class="fi-rs-eye"></i></a>
													<a aria-label="Add To Wishlist"
														class="action-btn small hover-up"
														href="shop-wishlist.html"><i class="fi-rs-heart"></i></a>
													<a aria-label="Compare" class="action-btn small hover-up"
														href="shop-compare.html"><i class="fi-rs-shuffle"></i></a>
												</div>
												<div
													class="product-badges product-badges-position product-badges-mrg">
													<span class="hot">Save 15%</span>
												</div>
											</div>
											<div class="product-content-wrap">
												<div class="product-category">
													<a href="shop-grid-left.html">Hodo Foods</a>
												</div>
												<h2>
													<a href="shop-product-right.html">Organic Quinoa,
														Brown, & Red Rice</a>
												</h2>
												<div class="product-rate d-inline-block">
													<div class="product-rating" style="width: 80%"></div>
												</div>
												<div class="product-price mt-10">
													<span>$238.85 </span> <span class="old-price">$245.8</span>
												</div>
												<div class="sold mt-15 mb-15">
													<div class="progress mb-5">
														<div class="progress-bar" role="progressbar"
															style="width: 50%" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
													<span class="font-xs text-heading"> Sold: 90/120</span>
												</div>
												<a href="shop-cart.html" class="btn w-100 hover-up"><i
													class="fi-rs-shopping-cart mr-5"></i>Add To Cart</a>
											</div>
										</div>
										<!--End product Wrap-->
									</div>
								</div>
							</div>
						</div>
						<!--End tab-content-->
					</div>
					<!--End Col-lg-9-->
				</div>
			</div>
		</section>

		<!--End Deals-->
		<section class="section-padding mb-30">
			<div class="container">
				<div class="row">
					<div
						class="col-xl-3 col-lg-4 col-md-6 mb-sm-5 mb-md-0 wow animate__animated animate__fadeInUp"
						data-wow-delay="0">
						<h4 class="section-title style-1 mb-30 animated animated">單月熱銷</h4>
						<div class="product-list-small animated animated">
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-1.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Nestle Original
											Coffee-Mate Coffee Creamer</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-2.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Nestle Original
											Coffee-Mate Coffee Creamer</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-3.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Nestle Original
											Coffee-Mate Coffee Creamer</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
						</div>
					</div>
					<div
						class="col-xl-3 col-lg-4 col-md-6 mb-md-0 wow animate__animated animate__fadeInUp"
						data-wow-delay=".1s">
						<h4 class="section-title style-1 mb-30 animated animated">即將完售</h4>
						<div class="product-list-small animated animated">
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-4.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Organic Cage-Free Grade
											A Large Brown Eggs</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-5.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Seeds of Change Organic
											Quinoa, Brown, & Red Rice</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-6.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Naturally Flavored
											Cinnamon Vanilla Light Roast Coffee</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
						</div>
					</div>
					<div
						class="col-xl-3 col-lg-4 col-md-6 mb-sm-5 mb-md-0 d-none d-lg-block wow animate__animated animate__fadeInUp"
						data-wow-delay=".2s">
						<h4 class="section-title style-1 mb-30 animated animated">新鮮商品</h4>
						<div class="product-list-small animated animated">
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-7.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Pepperidge Farm
											Farmhouse Hearty White Bread</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-8.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Organic Frozen Triple
											Berry Blend</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-9.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Oroweat Country
											Buttermilk Bread</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
						</div>
					</div>
					<div
						class="col-xl-3 col-lg-4 col-md-6 mb-sm-5 mb-md-0 d-none d-xl-block wow animate__animated animate__fadeInUp"
						data-wow-delay=".3s">
						<h4 class="section-title style-1 mb-30 animated animated">好評推薦</h4>
						<div class="product-list-small animated animated">
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-10.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Foster Farms Takeout
											Crispy Classic Buffalo Wings</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-11.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">Angie’s Boomchickapop
											Sweet & Salty Kettle Corn</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
							<article class="row align-items-center hover-up">
								<figure class="col-md-4 mb-0">
									<a href="shop-product-right.html"><img
										src="assets/imgs/shop/thumbnail-12.jpg" alt="" /></a>
								</figure>
								<div class="col-md-8 mb-0">
									<h6>
										<a href="shop-product-right.html">All Natural
											Italian-Style Chicken Meatballs</a>
									</h6>
									<div class="product-rate-cover">
										<div class="product-rate d-inline-block">
											<div class="product-rating" style="width: 90%"></div>
										</div>
										<span class="font-small ml-5 text-muted"> (4.0)</span>
									</div>
									<div class="product-price">
										<span>$32.85</span> <span class="old-price">$33.8</span>
									</div>
								</div>
							</article>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--End 4 columns-->

		<section class="featured section-padding">
			<div class="container">
				<div class="row">
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6 mb-md-4 mb-xl-0">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay="0">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-1.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">價格最優惠</h3>
								<p>平均省5元或更多</p>
							</div>
						</div>
					</div>
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay=".1s">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-2.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">交易最自由</h3>
								<p>24Hr/7Days 驚豔的服務</p>
							</div>
						</div>
					</div>
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay=".2s">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-3.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">輕鬆管理訂單</h3>
								<p>就是這麼簡單</p>
							</div>
						</div>
					</div>
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay=".3s">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-4.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">聰明的選擇</h3>
								<p>精選優惠一覽無遺</p>
							</div>
						</div>
					</div>
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay=".4s">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-5.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">Easy returns</h3>
								<p>Within 30 days</p>
							</div>
						</div>
					</div>
					<div class="col-lg-1-5 col-md-4 col-12 col-sm-6 d-xl-none">
						<div
							class="banner-left-icon d-flex align-items-center wow animate__animated animate__fadeInUp"
							data-wow-delay=".5s">
							<div class="banner-icon">
								<img src="assets/imgs/theme/icons/icon-6.svg" alt="" />
							</div>
							<div class="banner-text">
								<h3 class="icon-box-title">安全的配送</h3>
								<p>三日內退貨免運</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<%-- 	 <jsp:include page="/views/footer.jsp"/> --%>
	<!-- Vendor JS-->
	<script src="assets/js/vendors/modernizr-3.6.0.min.js"></script>
	<script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
	<script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="assets/js/plugins/slick.js"></script>
	<script src="assets/js/plugins/jquery.syotimer.min.js"></script>
	<script src="assets/js/plugins/waypoints.js"></script>
	<script src="assets/js/plugins/wow.js"></script>
	<script src="assets/js/plugins/perfect-scrollbar.js"></script>
	<script src="assets/js/plugins/magnific-popup.js"></script>
	<script src="assets/js/plugins/select2.min.js"></script>
	<script src="assets/js/plugins/counterup.js"></script>
	<script src="assets/js/plugins/jquery.countdown.min.js"></script>
	<script src="assets/js/plugins/images-loaded.js"></script>
	<script src="assets/js/plugins/isotope.js"></script>
	<script src="assets/js/plugins/scrollup.js"></script>
	<script src="assets/js/plugins/jquery.vticker-min.js"></script>
	<script src="assets/js/plugins/jquery.theia.sticky.js"></script>
	<script src="assets/js/plugins/jquery.elevatezoom.js"></script>
	<!-- Template  JS -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/shop.js?v=4.0"></script>
</body>
</html>
