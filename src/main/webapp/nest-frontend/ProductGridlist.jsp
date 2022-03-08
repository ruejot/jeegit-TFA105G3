<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%  
String usersearch = (String) request.getAttribute("usersearch");
String mainCategory = (String) request.getAttribute("mainCategory");
String subCategory = (String) request.getAttribute("subCategory");
Integer counts = (Integer) request.getAttribute("counts");
List<ProductVO> searchlist = (List<ProductVO>) session.getAttribute("searchlist");
     request.setAttribute("searchlist", searchlist);
%>
<%
ProductService productSvc = new ProductService();
ProductVO productbean = productSvc.getOneProduct(5);
pageContext.setAttribute("productbean", productbean);

List<ProductVO> list = productSvc.getAll();
pageContext.setAttribute("list", list);


%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>FrontEnd板模-Petting</title>
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
		 <div class="container">
                    <div class="archive-header">
                        <div class="row align-items-center">
                            <div class="col-xl-3">
                                <h1 class="mb-15">您想找的商品:${usersearch} ${mainCategory} ${subCategory}</h1>
                            </div>
                            <div class="col-xl-9 text-end d-none d-xl-block">
                                <ul class="tags-list">
                                <c:forEach var="hashtag" items="${searchlist}" end="8" step="2" >
                                    <li class="hover-up">
                                        <a href="<%=request.getContextPath()%>/product/ProductJump?merid=${hashtag.merid}&action=product_jump"><i class="fi-rs-cross mr-10"></i>${hashtag.name}</a>
                                    </li>
                                 </c:forEach> 
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            <div class="container mb-30">
                <div class="row flex-row-reverse">
                    <div class="col-lg-4-5">
                        <div class="shop-product-fillter">
                            <div class="totall-product">
                                <p>We found <strong class="text-brand"> ${counts} </strong> items for you!</p>
                            </div>
                        </div>
                        <!--start product card-->
                        <div class="row product-grid">
						<!-- 頭 商品列表 -->                   
							<%@ include file="/pages/Prod_10_page1.file" %>         
                            <c:forEach var="product" items="${searchlist}" 
                            		   begin="<%=pageIndex%>" 
                            		   end="<%=pageIndex+rowsPerPage-1%>">
								
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
															<input type="hidden" name="location" value="<%=request.getContextPath()%>/product/SearchServlet?action=sub&subCategory=${product.subCategory}">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 尾 商品列表 -->
                            <div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
                        </div>
                        <!--product grid 分頁標籤-->
                        <div class="pagination-area mt-20 mb-20">
                            <nav aria-label="Page navigation example" style="text-align:center;">
                                <ul class="pagination justify-content-start" >
<!--                                     第一次載入頁面 -->
                                    <c:if test="${param.action == ('search_from_header' || 'HomeTag' || 'sub')}" var="condition0" scope="session">
											<li class="page-item active"><a class="page-link">1</a></li>
                                    		<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=${param.whichPage+2}">2</a></li>
                                    		<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=${param.whichPage+3}">3</a></li>
                                    </c:if>
<!--                                   第一頁 -->
                                    <c:if test="${param.whichPage == '1' }" var="condition1" scope="session" > 
                                        <li class="page-item active"><a class="page-link">${param.whichPage}</a></li>
                                    	<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=2">2</a></li>
                                    </c:if>
<!--                                   第二頁 -->
                                    <c:if test="${param.whichPage == '2' }" var="condition2" scope="session" > 
             							<li class="page-item">
             								<a class="page-link"href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">
             									<i class="fi-rs-arrow-small-left"></i>
             								</a>
             							</li>
                                    	<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=1">1</a></li>
             							<li class="page-item active"><a class="page-link">${param.whichPage}</a></li>
             						</c:if>
<!--                                   第三頁以上 -->
                                    <c:if test="${param.whichPage >= '3' }" var="condition2" scope="session" > 
             							<li class="page-item">
             								<a class="page-link"href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">
             									<i class="fi-rs-arrow-small-left"></i>
             								</a>
             							</li>
                                    	<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=${param.whichPage-1}">${param.whichPage-1}</a></li>
             							<li class="page-item active"><a class="page-link">${param.whichPage}</a></li>
                                    	<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=${param.whichPage+1}">${param.whichPage+1}</a></li>
                                    </c:if>
<!--                                     向右標籤 -->
                                    <li class="page-item">
	                                    <a class="page-link"href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">
	                                    	<i class="fi-rs-arrow-small-right"></i> 
	                                    </a>
                                    </li>
                                </ul>
                            </nav>
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
</body>
</html>

