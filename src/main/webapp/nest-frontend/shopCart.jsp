﻿<%@page import="java.util.stream.Collectors"%>
<%@page import="org.apache.catalina.filters.AddDefaultCharsetFilter"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.cart.model.*"%>
<%@page import="org.json.*"%>
<%@page import="com.product.model.*"%>
<%@page import="com.bus.model.*"%>
<%@page import="com.productImg.model.*" %>


<%
	
	List<String> cartlist = (List<String>) session.getAttribute("list");
	Map<String, List<ProductVO>> map = (Map<String, List<ProductVO>>) session.getAttribute("map");
	Map<Integer, Integer> qtyMap = (Map<Integer, Integer>) session.getAttribute("qtymap");
	pageContext.setAttribute("list", cartlist);
	pageContext.setAttribute("qtymap", map);
	pageContext.setAttribute("map", request.getAttribute("map"));
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
<!--             <div class="page-header breadcrumb-wrap"> -->
<!--             <div class="container"> -->
<!--                 <div class="breadcrumb"> -->
<!--                     <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a> -->
<!--                     <span></span> 商城 -->
<!--                     <span></span> 購物車 -->
<!--                 </div> -->
<!--             </div> -->
<!--         	</div> -->
        
        	<div class="container mb-80 mt-50">
            	<div class="row">
            	<div class="col-lg-1 mb-40" ></div>
                	<div class="col-lg-10 mb-40" >
                    	<h1 class="heading-2 mb-10">購物車</h1>
                    	<br>
                    	<%if (cartlist.isEmpty() && (cartlist.size() == 0)) { %>
                    		<div class="d-flex justify-content-between">
                    			<h6 class="text-body" style="font-size: 20px;">您的購物車裡目前沒有商品QQ</h6>
                    			<br>
                    			<br>
                    			<br>
                    			<br>
                    		</div>
                    		<div class="cart-action d-flex justify-content-between">
                                <form method= "GET" ACTION= "<%=request.getContextPath()%>/nest-frontend/HomePage.jsp">	
                                	<button type="submit">
                                		<i class="fi-rs-arrow-left mr-10"></i>繼續購物
                                	</button>
                                </form>
                            </div>
                        <%} %>
                    	<%if (cartlist!=null && (cartlist.size() > 0)) { %>
                    	<div class="d-flex justify-content-between">
                        	<h6 class="text-body" style="font-size: 20px;">目前您購物車的內容如下：</h6>
<!--                         	<h6 class="text-body"><a href="#" class="text-muted"><i class="fi-rs-trash mr-5"></i>清空</a></h6> -->
                   	    </div>
                	</div>
            	</div>
<%--                 <c:forEach var="busName" items="<% map.keySet(); %>">          --%>
				<% for (String str : map.keySet()) { %>
            	<div class="row">
            	<div class="col-lg-1"></div>
              	  <div class="col-lg-10">
                  	  <div class="table-responsive shopping-summery">
<%--                          <h6 class="text-body">商家名稱：<%=str %></h6> --%>
						<% Integer busid = Integer.valueOf(str); %>
						<% BusService busSvc = new BusService(); %>
			         	<% BusVO bus = busSvc.select(busid); %>
		         	    <% String busName = bus.getName(); %>
                         <h6 class="text-body" style="font-size: 16px;">商家名稱：<%=busName %></h6>
                   	     <table class="table table-wishlist">
                    	        <thead>
                              		<tr class="main-heading">
                              	        <th class="custome-checkbox start pl-30">
                                       	   
                                        	  
                                   	 	</th>
                                   	 	<th scope="col" colspan="2">商品名稱</th>
                                   	 	<th scope="col">單價</th>
                                   	 	<th scope="col">數量</th>
                                  	 	<th scope="col">小計</th>
                                   	 	<th scope="col" class="end" width="40px">移除</th>
                                   	 		
                                   	 	
                                	</tr>
                            	</thead>
                            	<% List<ProductVO> pList = map.get(str); %>
                            	<% ProductVO prdVO = null; %>
								<% for (int i = 0; i < pList.size(); i++) { %>
								<% prdVO = pList.get(i); %>
                            	<tbody>
                               		 <tr class="pt-30">
                                   		<td class="custome-checkbox pl-30" style="width:20px;">
                                        	
                                    	</td>
                                    	<% ProductImgService prdImgSvc = new ProductImgService(); %>
                                    	<td class="image product-thumbnail pt-20">
                                    		<a href="<%=request.getContextPath()%>/product/ProductJump?merid=<%=prdVO.getMerid() %>&action=product_jump">
                                    			<img src="<%=request.getContextPath()%>/ShowPic?imgid=<%=prdImgSvc.getOneProductImg(prdVO.getMerid()).getImgid() %>">
                                    		</a>
                                    	</td>
                                   		<td class="product-des product-name">
                                       		<h6 class="mb-5" style="font-size: 18px;">
                                       			<a class="product-name mb-10 text-heading" href="<%=request.getContextPath()%>/product/ProductJump?merid=<%=prdVO.getMerid() %>&action=product_jump">
                                       				<%=prdVO.getName() %>
                                       			</a>
                                       		</h6>
<!--                                         	<div class="product-rate-cover"> -->
<!--                                             	<div class="product-rate d-inline-block"> -->
<!--                                                 	<div class="product-rating" style="width:90%"></div> -->
<!--                                             	</div> -->
<!--                                             <span class="font-small ml-5 text-muted"> orderDetailVO.ranking average</span> -->
<!--                                         	</div> -->
                                    	</td>
                                   		<td class="price" data-title="Price">
                                        	<h4 class="text-body" style="font-size: 22px;">$ <%=prdVO.getPrice() %></h4>
                                    	</td>
                                    	<td class="text-center detail-info" data-title="Stock">
                                                	<h4 class="text-body" style="font-size: 22px;"><%=qtyMap.get(prdVO.getMerid()) %></h4>
                                    	</td>
                                    	<td class="price" data-title="Price">
                                        	<h4 class="text-brand" style="font-size: 22px;">$ <%=qtyMap.get(prdVO.getMerid()) * prdVO.getPrice() %></h4>
                                    	</td>
                                    	<td class="action text-center" data-title="Remove">
<!--                                     		<a href="#" class="text-body"><i class="fi-rs-trash"></i></a> -->
                                    		<form method= "POST" ACTION= "cartServlet.do">
                                    			<button type="submit" style="width: 50px; height: 50px; padding: 0px 0px;"><i class="fi-rs-trash"></i></button>
                                   	 			<input type="hidden" name="del" value="<%=prdVO.getMerid() %>">
                                   	 			<input type="hidden" name="location" value="<%=request.getContextPath()%>/nest-frontend/showCartServlet.do?action=showcart">
												<input type="hidden" name="action" value="delete">
                                   	 		</form>
                                    	</td>
                                	</tr>
                            	</tbody>
                            	<%} %>
                       		</table>
                       	
                    	</div>
                    	<div class="divider-2 mb-30"></div>
                            	<table class="table no-border">
                                	<tbody>
                                    	<tr>
                                        	<td class="cart_total_label">
                                            	<h6 class="text-brand" style="font-size: 22px;">總計</h6>
                                        	</td>
                                        	<td class="cart_total_amount">
                                            	<h4 class="text-brand text-end" style="font-size: 22px;">$ <%=pList.stream()
                                            	                                         						   .mapToInt(p -> qtyMap.get(p.getMerid()) * p.getPrice())
                                             	                                          						   .sum()%></h4> 
                                        	</td>
                                    	</tr>
                                	</tbody>
                            	</table>
                            	<div class="cart-action d-flex justify-content-between">
<%--                                 	<a href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp" class="btn" ><i class="fi-rs-arrow-left mr-10"></i>繼續購物</a> --%>
                                	<form method= "GET" ACTION= "<%=request.getContextPath()%>/nest-frontend/HomePage.jsp">	
                                		<button type="submit">
                                			<i class="fi-rs-arrow-left mr-10"></i>繼續購物
                                		</button>
                                	</form>
                                	<form method= "POST" ACTION= "cartServlet.do">
                                		<button type="submit">前往結帳<i class="fi-rs-sign-out ml-15"></i></button>
<!--                                 		<a class="btn  mr-10 mb-sm-15">前往結帳<i class="fi-rs-sign-out ml-15"></i></a> -->
                                   	 	<input type="hidden" name="busIdtoCheckout" value="<%=busid %>">
										<input type="hidden" name="action" value="checkout">
                                   	</form>
                            	</div>
                            	<br>
                            	<br>
                            	<br>
                    	</div>
                    </div>	
                    <%} %>
                    <%} %>
            </div>
        </main>
		<jsp:include page="footer.jsp"/>
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
