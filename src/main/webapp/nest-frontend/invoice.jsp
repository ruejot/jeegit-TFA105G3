<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.order.model.*"%>
<%@page import="java.text.*" %>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting 訂單成立</title>
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
	<div class="invoice invoice-content invoice-6">
            
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="invoice-inner">
                            <div class="invoice-info" id="invoice_wrapper">
                                <div class="invoice-header">
                                    <div class="invoice-icon">
                                        <img src="assets/imgs/theme/icons/icon-invoice.svg" class="img-fluid" alt="">
                                    </div>
                                    <div class="row align-items-center">
                                        <div class="col-md-6">
                                            <div class="logo">
                                                <a href="HomePage.jsp" style="width: 200px"><img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg" alt="logo" /></a>
                                            </div>
                                        </div>
                                        <div class="col-md-6 text-end">
                                            <h2 class="mb-0">訂單明細</h2>
                                        </div>
                                    </div>
                                    <div class="row align-items-center">
                                        <div class="col-md-6">
                                            <div class="text">
                                            	<br>
                                                台北市中山區南京東路三段219號5樓<br />
                                                總機： 02-2712-0589<br />
                                            </div>
                                        </div>
<!--                                         <div class="col-md-6 text-end"> -->
<!--                                             <strong class="text-brand">Webz Poland</strong> <br /> -->
<!--                                             Madalinskiego 871-101 Szczecin, Poland<br> -->
<!--                                             <abbr title="Email">Email: </abbr>nfo@webz.com.pl -->
<!--                                         </div> -->
                                    </div>
                                    <div class="row mt-20">
                                       <div class="col-12"> <div class="hr mb-10"></div></div>
                                        <div class="col-lg-2">
                                           <strong class="text-brand"> 訂單編號：</strong> ${new_orderId }
                                        </div>
                                         <div class="col-lg-4">
                                           <% DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd"); %>
                                           <strong class="text-brand"> 成立日期：</strong>
                                           <%= dateformat.format(((OrderVO)request.getAttribute("orderVO")).getOrderTime()) %>
                                        </div>
                                        <div class="col-lg-3">
                                           <strong class="text-brand"> 付款方式：</strong>
                                             <c:if test="${orderVO.paymentId == 1}">信用卡付款</c:if>
											 <c:if test="${orderVO.paymentId == 2}">貨到付款</c:if>
											 <c:if test="${orderVO.paymentId == 3}">ATM轉帳</c:if>
											 <c:if test="${orderVO.paymentId == 4}">超商付款</c:if>
                                        </div>
                                        <div class="col-lg-3">
                                           <strong class="text-brand"> 出貨方式：</strong>
                                             <c:if test="${orderVO.shippingId == 1}">宅配到府</c:if>
											 <c:if test="${orderVO.shippingId == 2}">超商取貨</c:if>
											 <c:if test="${orderVO.shippingId == 3}">面交</c:if>
											 <c:if test="${orderVO.shippingId == 4}">到店取貨</c:if>
                                        </div>
                                        <div class="col-12"><div class="hr mt-10"></div></div>
                                    </div>
                                </div>

                                <div class="invoice-center">
                                    <div class="table-responsive">
                                        <table class="table table-striped invoice-table">
                                            <thead class="bg-active">
                                                <tr>
                                                    <th>商品名稱</th>
                                                    <th class="text-center">單價</th>
                                                    <th class="text-center">數量</th>
                                                    <th class="text-right">小計</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <c:forEach var="orderDetailVO" items="${orderList}">
                                                <tr>
                                                    <td>
                                                        <div class="item-desc-1">
                                                            <span>
                                                            	<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${orderDetailVO.merId }&action=product_jump">
                                                            		${productSvc.getOneProduct(orderDetailVO.merId).name}
                                                            	</a>	
                                                            </span>
                                                        </div>
                                                    </td>
                                                    <td class="text-center">$${orderDetailVO.unitPrice}</td>
                                                    <td class="text-center">${orderDetailVO.qty}</td>
                                                    <td class="text-right">$${orderDetailVO.unitPrice * orderDetailVO.qty}</td>
                                                </tr>
                                              </c:forEach>
                                                <tr>
                                                    <td colspan="3" class="text-end f-w-600">總計</td>
                                                    <td class="text-right f-w-600">$${orderVO.orderSum }</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="invoice-btn-section clearfix d-print-none">
                                <a href="javascript:window.print()" class="btn btn-lg btn-custom btn-print hover-up"> <img src="assets/imgs/theme/icons/icon-print.svg" alt="" /> 列印 </a>
                                <a id="invoice_download_btn" class="btn btn-lg btn-custom btn-download hover-up"> <img src="assets/imgs/theme/icons/icon-download.svg" alt="" /> 下載 </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</main>
	<jsp:include page="footer.jsp" />

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

	<!--Invoice page's JS-->
	<script src="<%=request.getContextPath()%>/assets/js/invoice/jspdf.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/invoice/invoice.js"></script>

	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
	
	<!-- TWtwzipcode下拉選單 JS -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	
</body>
</html>

