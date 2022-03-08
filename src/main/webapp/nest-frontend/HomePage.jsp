<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<!DOCTYPE html>
<!-- [editor,date] wei,2022-02-22 -->
<%
ProductService productSvc = new ProductService();
List<ProductVO> productlist = productSvc.getAll();
session.setAttribute("productlist", productlist);


HomePageService homePageSVC = new HomePageService();
pageContext.setAttribute("homePageSVC", homePageSVC);
List<ProductVO> mainlist1 = homePageSVC.getSpecialClassByMainCategory("寵物食品");
request.setAttribute("mainlist1", mainlist1);
List<ProductVO> mainlist2 = homePageSVC.getSpecialClassByMainCategory("生活用品");
request.setAttribute("mainlist2", mainlist2);
List<ProductVO> mainlist3 = homePageSVC.getSpecialClassByMainCategory("寵物玩具");
request.setAttribute("mainlist3", mainlist3);
List<ProductVO> mainlist4 = homePageSVC.getSpecialClassByMainCategory("居家清潔");
request.setAttribute("mainlist4", mainlist4);
List<ProductVO> mainlist5 = homePageSVC.getSpecialClassByMainCategory("美容用具");
request.setAttribute("mainlist5", mainlist5);
%>
<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting Homepage</title>
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
<%-- 		<jsp:include page="/views/userMainPage-header.jsp" /> --%>
			<!-- 頭 上排十大類 -->
	<section class="popular-categories section-padding">
		<div class="container wow animate__animated animate__fadeIn">
			<div class="section-title">
				<div class="title">
					<h3>特色類別</h3>
					<ul class="list-inline nav nav-tabs links">
                        <li class="list-inline-item nav-item"> <a class="nav-link"
                            href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物玩具">寵物玩具</a>
                        </li>
                        </ul>
                        <ul class="list-inline nav nav-tabs links">
                        <li class="list-inline-item nav-item"> <a class="nav-link"
                            href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=居家清潔">居家清潔</a>
                        </li>
                        </ul>
                        <ul class="list-inline nav nav-tabs links">
                        <li class="list-inline-item nav-item"> <a class="nav-link"
                            href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=生活用品">生活用品</a>
                        </li>
                        </ul>
                        <ul class="list-inline nav nav-tabs links">
                        <li class="list-inline-item nav-item"> <a class="nav-link"
                            href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=寵物食品">寵物食品</a>
                        </li>
                        </ul>
				</div>
				<div
					class="slider-arrow slider-arrow-2 flex-right carausel-10-columns-arrow"
					id="carausel-10-columns-arrows"></div>
			</div>
	<!--特色子類別 -->
			<div class="carausel-10-columns-cover position-relative">
				<div class="carausel-10-columns" id="carausel-10-columns">
					<div class="card-2 bg-9 wow animate__animated animate__fadeInUp"
						data-wow-delay=".1s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=貓犬飼料">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/罐頭.png" alt="" /></a>
						</figure>
								<h6>貓犬飼料</h6>
						<span>${homePageSVC.getCountsBySubCategory('貓犬飼料')} items</span>
					</div>
					<div class="card-2 bg-10 wow animate__animated animate__fadeInUp"
						data-wow-delay=".2s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=魚蝦飼料">
							<img style="height:80px" 
								 src="<%=request.getContextPath()%>/assets/imgs/shop/魚飼料.png" alt="" /></a>
						</figure>
						<h6>魚蝦飼料</h6>
						<span>${homePageSVC.getCountsBySubCategory('魚蝦飼料')} items</span>
					</div>
					<div class="card-2 bg-11 wow animate__animated animate__fadeInUp"
						data-wow-delay=".3s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=貓抓板">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/貓抓板.png" alt="" /></a>
						</figure>
						<h6>貓抓板</h6>
						<span>${homePageSVC.getCountsBySubCategory('貓抓板')} items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay=".4s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=外出牽繩">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/牽繩.png" alt="" /></a>
						</figure>
						<h6>外出牽繩</h6>
						<span>${homePageSVC.getCountsBySubCategory('外出牽繩')} items</span>
					</div>
					<div class="card-2 bg-13 wow animate__animated animate__fadeInUp"
						data-wow-delay=".5s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=零食點心">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/狗零食.png" alt="" /></a>
						</figure>
						<h6>零食點心</h6>
						<span>${homePageSVC.getCountsBySubCategory('零食點心')} items</span>
					</div>
					<div class="card-2 bg-14 wow animate__animated animate__fadeInUp"
						data-wow-delay=".6s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=精靈球">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/精靈球.png" alt="" /></a>
						</figure>
						<h6>精靈球</h6>
						<span>${homePageSVC.getCountsBySubCategory('精靈球')} items</span>
					</div>
					<div class="card-2 bg-15 wow animate__animated animate__fadeInUp"
						data-wow-delay=".7s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=寵物服裝">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/catcloth.png" alt="" /></a>
						</figure>
						<h6>寵物服裝</h6>
						<span>${homePageSVC.getCountsBySubCategory('寵物服裝')} items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay=".8s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=過濾器材">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/過濾器.png" alt="" /></a>
						</figure>
						<h6>過濾器材</h6>
						<span>${homePageSVC.getCountsBySubCategory('過濾器材')}items</span>
					</div>
					<div class="card-2 bg-10 wow animate__animated animate__fadeInUp"
						data-wow-delay=".9s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=裝飾">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/梳子.png" alt="" /></a>
						</figure>
						<h6>美容、裝飾</h6>
						<span>${homePageSVC.getCountsBySubCategory('裝飾')} items</span>
					</div>
					<div class="card-2 bg-12 wow animate__animated animate__fadeInUp"
						data-wow-delay="1s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=寵物背包">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/外出背包.png" alt="" /></a>
						</figure>
						<h6>寵物背包</h6>
						<span>${homePageSVC.getCountsBySubCategory('寵物背包')} items</span>
					</div>
					<div class="card-2 bg-11 wow animate__animated animate__fadeInUp"
						data-wow-delay="0s">
						<figure class="img-hover-scale overflow-hidden">
							<a href="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=清潔">
							<img style="height:80px"
								 src="<%=request.getContextPath()%>/assets/imgs/shop/洗澡精.png" alt="" /></a>
						</figure>
						<h6>清潔用品</h6>
						<span>${homePageSVC.getCountsBySubCategory('清潔')} items</span>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- 尾 上排十大類 -->

<!--頭 滑動廣告頁面-->
		<div class="home-slide-cover mt-30">
					<div class="hero-slider-1 style-4 dot-style-1 dot-style-1-position-1">
						<div class="single-hero-slider single-animation-wrap" >
							<img  style="margin:auto;" src="<%=request.getContextPath()%>/assets/imgs/slider/buyingdog.jpg" width=1200px/> 
							<div class="slider-content">
								<h1 class="display-2 mb-40">
									趕緊加入<br /> 全台灣最ㄒㄧㄚ的寵物商店
								</h1>
								<p class="mb-65">訂閱每周優惠商品</p>
								<form class="form-subcriber d-flex" action="<%=request.getContextPath()%>/commonUtil/sendMailServlet">
									<input type="email" placeholder="電子信箱 emaill address" name="email"/>
									<input type="hidden" name="action" value="sendmail">
									<button class="btn" type="submit">領取</button>
								</form>
							</div>
						</div>
						<div class="single-hero-slider single-animation-wrap"
							style="background-image: url(assets/imgs/slider/)">
							<div class="slider-content">
								<h1 class="display-2 mb-40">
									品牌特惠!!<br /> &emsp;&emsp;3.18購物節
								</h1>
								<p class="mb-65">領取你的三折優惠券</p>
								<form class="form-subcriber d-flex" action="<%=request.getContextPath()%>/commonUtil/sendMailServlet">
									<input type="email" placeholder="電子信箱 emaill address" name="email"/>
									<input type="hidden" name="action" value="sendmail">
									<button class="btn" type="submit">領取</button>
								</form>
							</div>
						</div>
					</div>
					<div class="slider-arrow hero-slider-1-arrow"></div>
				</div>
<!--尾 滑動廣告頁面-->
<!--頭 橫三幅 廣告頁面-->
		<section class="banners mb-25">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="banner-img wow animate__animated animate__fadeInUp"
							data-wow-delay="0">
							<img src="<%=request.getContextPath()%>/assets/imgs/banner/sleepdog.jpg" alt="" height=340px width=512px />
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
							<img src="<%=request.getContextPath()%>/assets/imgs/banner/152.jpg" alt="" height=340px width=512px/>
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
							<img src="<%=request.getContextPath()%>/assets/imgs/banner/ego.png" alt="" height=340px width=512px/>
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
<!--尾 橫三幅 廣告頁面-->
		<!--End banners-->
		<section class="product-tabs section-padding position-relative">
                <div class="container">
                    <div class="section-title style-2 wow animate__animated animate__fadeIn">
                        <h3>熱門商品</h3>
                        <ul class="nav nav-tabs links" id="myTab" role="tablist">
                            
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-seven" data-bs-toggle="tab" data-bs-target="#tab-one" type="button" role="tab" aria-controls="tab-one" aria-selected="false">寵物食品</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-two" data-bs-toggle="tab" data-bs-target="#tab-two" type="button" role="tab" aria-controls="tab-two" aria-selected="false">生活用品</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-three" data-bs-toggle="tab" data-bs-target="#tab-three" type="button" role="tab" aria-controls="tab-three" aria-selected="false">寵物玩具</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-four" data-bs-toggle="tab" data-bs-target="#tab-four" type="button" role="tab" aria-controls="tab-four" aria-selected="false">居家清潔</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="nav-tab-five" data-bs-toggle="tab" data-bs-target="#tab-five" type="button" role="tab" aria-controls="tab-five" aria-selected="false">美容用具</button>
                            </li>
                        </ul>
                    </div>
                    <!--End nav-tabs-->
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="tab-one" role="tabpanel" aria-labelledby="tab-one">
                            <div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
						aria-labelledby="tab-one">
						<div class="row product-grid-4">
                            <c:forEach var="product" items="${mainlist1}">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
<!-- 											<div class="product-rate-cover"> -->
<!-- 												<div class="product-rate d-inline-block"> -->
<!-- 													<div class="product-rating" style="width: 20%"></div> -->
<!-- 												</div> -->
<!-- 												<span class="font-small ml-5 text-muted"> (4.0)</span> -->
<!-- 											</div> -->
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
                            				<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
												<button type="submit" id="btn_submit" style="color:#3BB77E "> 
													<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
													<input type="hidden" name="action" value="add">                                                    
	                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
													<input type="hidden" name="merId" value="${product.merid}">                                                    
													<input type="hidden" name="busId" value="${product.busid}">                                                    
													<input type="hidden" name="price" value="${product.price}">
													<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													
											</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							</div>
							</div>
                        </div>
                        <!--En tab one-->
                        <div class="tab-pane fade" id="tab-two" role="tabpanel" aria-labelledby="tab-two">
                            <div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
						aria-labelledby="tab-one">
						<div class="row product-grid-4">
                            <c:forEach var="product" items="${mainlist2}">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
<!-- 											<div class="product-rate-cover"> -->
<!-- 												<div class="product-rate d-inline-block"> -->
<!-- 													<div class="product-rating" style="width: 20%"></div> -->
<!-- 												</div> -->
<!-- 												<span class="font-small ml-5 text-muted"> (4.0)</span> -->
<!-- 											</div> -->
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
											<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
												<button type="submit" id="btn_submit" style="color:#3BB77E "> 
													<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
													<input type="hidden" name="action" value="add">                                                    
	                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
													<input type="hidden" name="merId" value="${product.merid}">                                                    
													<input type="hidden" name="busId" value="${product.busid}">                                                    
													<input type="hidden" name="price" value="${product.price}">
													<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													
											</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							</div>
							</div>
                        </div>
                        <!--En tab two-->
                        <div class="tab-pane fade" id="tab-three" role="tabpanel" aria-labelledby="tab-three">
                           <div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
									aria-labelledby="tab-one">
									<div class="row product-grid-4">
                            <c:forEach var="product" items="${mainlist3}">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
<!-- 											<div class="product-rate-cover"> -->
<!-- 												<div class="product-rate d-inline-block"> -->
<!-- 													<div class="product-rating" style="width: 20%"></div> -->
<!-- 												</div> -->
<!-- 												<span class="font-small ml-5 text-muted"> (4.0)</span> -->
<!-- 											</div> -->
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
													<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
														<button type="submit" id="btn_submit" style="color:#3BB77E "> 
															<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
															<input type="hidden" name="action" value="add">                                                    
			                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
															<input type="hidden" name="merId" value="${product.merid}">                                                    
															<input type="hidden" name="busId" value="${product.busid}">                                                    
															<input type="hidden" name="price" value="${product.price}">
															<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							</div>
							</div>
                        </div>
                        <!--En tab three-->
                        <div class="tab-pane fade" id="tab-four" role="tabpanel" aria-labelledby="tab-four">
                            <div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
						aria-labelledby="tab-one">
						<div class="row product-grid-4">
                            <c:forEach var="product" items="${mainlist4}">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
<!-- 											<div class="product-rate-cover"> -->
<!-- 												<div class="product-rate d-inline-block"> -->
<!-- 													<div class="product-rating" style="width: 20%"></div> -->
<!-- 												</div> -->
<!-- 												<span class="font-small ml-5 text-muted"> (4.0)</span> -->
<!-- 											</div> -->
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
													<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
														<button type="submit" id="btn_submit" style="color:#3BB77E "> 
															<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
															<input type="hidden" name="action" value="add">                                                    
			                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
															<input type="hidden" name="merId" value="${product.merid}">                                                    
															<input type="hidden" name="busId" value="${product.busid}">                                                    
															<input type="hidden" name="price" value="${product.price}">
															<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							</div>
							</div>
                        </div>
                        <!--En tab four-->
                        <div class="tab-pane fade" id="tab-five" role="tabpanel" aria-labelledby="tab-five">
                            <div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-one" role="tabpanel"
						aria-labelledby="tab-one">
						<div class="row product-grid-4">
                            <c:forEach var="product" items="${mainlist5}">
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
<!-- 											<div class="product-rate-cover"> -->
<!-- 												<div class="product-rate d-inline-block"> -->
<!-- 													<div class="product-rating" style="width: 20%"></div> -->
<!-- 												</div> -->
<!-- 												<span class="font-small ml-5 text-muted"> (4.0)</span> -->
<!-- 											</div> -->
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
													<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
														<button type="submit" id="btn_submit" style="color:#3BB77E "> 
															<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
															<input type="hidden" name="action" value="add">                                                    
			                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
															<input type="hidden" name="merId" value="${product.merid}">                                                    
															<input type="hidden" name="busId" value="${product.busid}">                                                    
															<input type="hidden" name="price" value="${product.price}">
															<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							</div>
							</div>
                        </div>
                        <!--En tab five-->
                    </div>
                    <!--End tab-content-->
                </div>
            </section>
		<!--頭 輪播CSS-->
		<section class="section-padding pb-5">
			<div class="container">
				<div class="section-title wow animate__animated animate__fadeIn">
                        <h3 class="">所有商品</h3>
                        </div>
				<div class="row">
				  <div class="col-lg-3 d-none d-lg-flex wow animate__animated animate__fadeIn">
                            <div class="banner-img style-2">
                                <div class="banner-text">
                                    <h2 class="mb-100">我們最棒的商品都在這~</h2>
                                </div>
                            </div>
                        </div>
					<div
						class="col-lg-9 col-md-12 wow animate__animated animate__fadeIn"
						data-wow-delay=".3s">
						<div class="tab-content" id="myTabContent-1">
							<div class="tab-pane fade show active" id="tab-one-1"
								role="tabpanel" aria-labelledby="tab-one-1">
								<div
									class="carausel-4-columns-cover arrow-center position-relative">
									<div
										class="slider-arrow slider-arrow-2 carausel-4-columns-arrow"
										id="carausel-4-columns-arrows"></div>
									<div class="carausel-4-columns carausel-arrow-center"
										id="carausel-4-columns">
										<!--尾 輪播CSS-->
										<!--所有商品輪播-->
							<c:forEach var="product" items="${productlist}" >
								<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
									<div
										class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn"
										data-wow-delay=".1s">
										<div class="product-img-action-wrap">
											<div class="product-img product-img-zoom">
													<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
														<img class="default-img" style="height:266px" 
															src="<%=request.getContextPath()%>/ProdFirstPic?aa=${product.merid}"/>
													</a>
											</div>
										</div>
										<div class="product-content-wrap">
											<div class="product-category">
												<a href="<%=request.getContextPath()%>/product/SearchServlet?action=HomeTag&mainCategory=${product.subCategory}">
													${product.subCategory}
												</a>
											</div>
											<h2>
												<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${product.merid}&action=product_jump">
													${product.name}
												</a>
											</h2>
											<div class="product-card-bottom">
												<div class="product-price">
													<span>$ ${product.price}</span>
												</div>
												<div class="add-cart">
													<form action="<%=request.getContextPath()%>/nest-frontend/cartServlet.do" method="POST">
														<button type="submit" id="btn_submit" style="color:#3BB77E "> 
															<i class="fi-rs-shopping-cart mr-5"></i>Add</button>
															<input type="hidden" name="action" value="add">                                                    
			                                                <input type="hidden" name="qty"  id="i_qty" value="1">   
															<input type="hidden" name="merId" value="${product.merid}">                                                    
															<input type="hidden" name="busId" value="${product.busid}">                                                    
															<input type="hidden" name="price" value="${product.price}">
															<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
									</div>
								</div>
							</div>
									</div>
								</div>
							</div>
						</div>
		</section>

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
</body>
</html>

