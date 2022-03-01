<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.cart.model.Products"%>

<%
	Vector<Products> cartlist = (Vector<Products>) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
    <head>
    	<meta charset="utf-8">
    	<title>Petting 購物車</title>
    	<meta http-equiv="x-ua-compatible" content="ie=edge">
    	<meta name="description" content="">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta property="og:title" content="">
    	<meta property="og:type" content="">
    	<meta property="og:url" content="">
    	<meta property="og:image" content="">
    	<!-- Favicon -->
    	<link rel="shortcut icon" type="image/x-icon" href="assets/imgs/theme/favicon.svg">
    	<!-- Template CSS -->
    	<link rel="stylesheet" href="assets/css/main.css?v=4.0">
	</head>

    <body>
		<jsp:include page="/views/userHeader.jsp"/>
        <main class="main pages">
            <div class="page-header breadcrumb-wrap">
            <div class="container">
                <div class="breadcrumb">
                    <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
                    <span></span> 商城
                    <span></span> 購物車
                </div>
            </div>
        	</div>
        
        	<div class="container mb-80 mt-50">
            	<div class="row">
                	<div class="col-lg-8 mb-40" >
                    	<h1 class="heading-2 mb-10">購物車</h1>
                    	<%if (cartlist!=null && (cartlist.size() > 0)) { %>
                    	<div class="d-flex justify-content-between">
                        	<h6 class="text-body">您的購物車裡共有 <span class="text-brand">count</span> 項商品</h6>
                        	<h6 class="text-body"><a href="#" class="text-muted"><i class="fi-rs-trash mr-5"></i>清空</a></h6>
                   	    </div>
                	</div>
            	</div>
            	<div class="row">
              	  <div class="col-lg-8">
                  	  <div class="table-responsive shopping-summery">
                   	     <table class="table table-wishlist">
                    	        <thead>
                              		<tr class="main-heading">
                              	        <th class="custome-checkbox start pl-30">
                                       	   <input class="form-check-input" type="checkbox" name="checkbox" id="exampleCheckbox11" value="">
                                        	  <label class="form-check-label" for="exampleCheckbox11"></label>
                                   	 	</th>
                                   	 	<th scope="col" colspan="2">商品名稱</th>
                                   	 	<th scope="col">單價</th>
                                   	 	<th scope="col">數量</th>
                                  	 	<th scope="col">小計</th>
                                   	 	<th scope="col" class="end" width="40px">移除</th>
                                	</tr>
                            	</thead>
                            	<tbody>
                            		<%
                            		for (int index = 0; index < cartlist.size(); index++){
                            			Products order = cartlist.get(index);
                            		%>
                               		 <tr class="pt-30">
                                   		<td class="custome-checkbox pl-30">
                                        	<input class="form-check-input" type="checkbox" name="checkbox" id="exampleCheckbox1" value="">
                                        	<label class="form-check-label" for="exampleCheckbox1"></label>
                                    	</td>
                                    	<td class="image product-thumbnail pt-40"><img src="assets/imgs/shop/product-1-1.jpg" alt="#"></td>
                                   		<td class="product-des product-name">
                                       		<h6 class="mb-5"><a class="product-name mb-10 text-heading" href="shop-product-right.html"><%=order.getName() %></a></h6>
                                        	<div class="product-rate-cover">
                                            	<div class="product-rate d-inline-block">
                                                	<div class="product-rating" style="width:90%">
                                                </div>
                                            </div>
                                            <span class="font-small ml-5 text-muted"> orderDetailVO.ranking average</span>
                                        	</div>
                                    	</td>
                                   		<td class="price" data-title="Price">
                                        	<h4 class="text-body"><%=order.getPrice() %> </h4>
                                    	</td>
                                    	<td class="text-center detail-info" data-title="Stock">
                                        	<div class="detail-extralink mr-15">
                                            	<div class="detail-qty border radius">
                                                	<a href="#" class="qty-down"><i class="fi-rs-angle-small-down"></i></a>
                                                	<span class="qty-val"><%=order.getQty() %></span>
                                                	<a href="#" class="qty-up"><i class="fi-rs-angle-small-up"></i></a>
                                            	</div>
                                        	</div>
                                    	</td>
                                    	<td class="price" data-title="Price">
                                        	<h4 class="text-brand"><%=order.getPrice() * order.getQty() %> </h4>
                                    	</td>
                                    	<td class="action text-center" data-title="Remove"><a href="#" class="text-body"><i class="fi-rs-trash"></i></a></td>
                                	</tr>
                                	<%}%>
                            	</tbody>
                       		</table>
                    	</div>
                    	<div class="divider-2 mb-30"></div>
                            	<table class="table no-border">
                                	<tbody>
                                    	<tr>
                                        	<td class="cart_total_label">
                                            	<h6 class="text-muted">總計</h6>
                                        	</td>
                                        	<td class="cart_total_amount">
                                            	<h4 class="text-brand text-end">${amount}</h4>
                                        	</td>
                                    	</tr>
                                	</tbody>
                            	</table>
                            	<div class="cart-action d-flex justify-content-between">
                                	<a class="btn "><i class="fi-rs-arrow-left mr-10"></i>繼續購物</a>
                                	<a class="btn  mr-10 mb-sm-15">前往結帳<i class="fi-rs-sign-out ml-15"></i></a>
                            	</div> 
                    	</div>
                    	<%} %>
                	</div>
            	</div>
        	</div>
        </main>
		<jsp:include page="/views/footer.jsp"/>
        <!--
        Preloader Start
        <div id="preloader-active">
            <div class="preloader d-flex align-items-center justify-content-center">
                <div class="preloader-inner position-relative">
                    <div class="text-center">
                        <img src="assets/imgs/theme/loading.gif" alt="" />
                    </div>
                </div>
            </div>
        </div>
        -->
         <!-- Vendor JS-->
    		<script src="assets/js/vendor/modernizr-3.6.0.min.js"></script>
    		<script src="assets/js/vendor/jquery-3.6.0.min.js"></script>
    		<script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
    		<script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
    		<script src="assets/js/plugins/slick.js"></script>
    		<script src="assets/js/plugins/jquery.syotimer.min.js"></script>
    		<script src="assets/js/plugins/wow.js"></script>
    		<script src="assets/js/plugins/jquery-ui.js"></script>
    		<script src="assets/js/plugins/perfect-scrollbar.js"></script>
    		<script src="assets/js/plugins/magnific-popup.js"></script>
    		<script src="assets/js/plugins/select2.min.js"></script>
    		<script src="assets/js/plugins/waypoints.js"></script>
    		<script src="assets/js/plugins/counterup.js"></script>
    		<script src="assets/js/plugins/jquery.countdown.min.js"></script>
    		<script src="assets/js/plugins/images-loaded.js"></script>
    		<script src="assets/js/plugins/isotope.js"></script>
    		<script src="assets/js/plugins/scrollup.js"></script>
    		<script src="assets/js/plugins/jquery.vticker-min.js"></script>
    		<script src="assets/js/plugins/jquery.theia.sticky.js"></script>
    		<script src="assets/js/plugins/jquery.elevatezoom.js"></script>
    	<!-- Template  JS -->
    		<script src="./assets/js/main.js?v=4.0"></script>
    		<script src="./assets/js/shop.js?v=4.0"></script>
    </body>
</html>
