<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="java.util.*"%>

<%	
	List<OrderDetailVO> list = (List<OrderDetailVO>)request.getAttribute("orderDetailList");
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
<head>
	<meta charset="utf-8">
	<title>Petting 會員中心</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="/assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link rel="stylesheet" href="assets/css/plugins/animate.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
</head>
<body>
	<jsp:include page="/views/userHeader.jsp"/>
        <main class="main pages">
        	<jsp:include page="/views/userMainPage-header.jsp" />
            <div class="page-content pt-150 pb-150">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 m-auto">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="dashboard-menu">
                                        <ul class="nav flex-column" role="tablist">
                                            <!--
                                            <li class="nav-item">
                                                <a class="nav-link active" id="dashboard-tab" data-bs-toggle="tab" href="#dashboard" role="tab" aria-controls="dashboard" aria-selected="false"><i class="fi-rs-settings-sliders mr-10"></i>會員中心</a>
                                            </li>
                                            -->
                                            <li class="nav-item">
                                                <a class="nav-link active" id="orders-tab" data-bs-toggle="tab" href="accountCenter.jsp" role="tab" aria-controls="orders" aria-selected="false"><i class="fi-rs-shopping-bag mr-10"></i>訂單管理</a>
                                            </li>
                                            <!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
                                            <li class="nav-item">
                                                <a class="nav-link" id="address-tab" data-bs-toggle="tab" href="<%=request.getContextPath()%>/views/articleManagement.jsp" role="tab" aria-controls="address" aria-selected="true"><i class="fi-rs-marker mr-10"></i>文章管理</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="nav-link" id="account-detail-tab" data-bs-toggle="tab" href="<%=request.getContextPath()%>/views/accountSetting.jsp" role="tab" aria-controls="account-detail" aria-selected="true"><i class="fi-rs-user mr-10"></i>帳戶設定</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="page-login.html"><i class="fi-rs-sign-out mr-10"></i>登出</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="tab-content account dashboard-content pl-50">
                                        <!--
                                        <div class="tab-pane fade active show" id="dashboard" role="tabpanel" aria-labelledby="dashboard-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="mb-0">Hello 會員名字!</h3>
                                                </div>
                                                <div class="card-body">
                                                    <p>
                                                        在會員中心您可以 &amp; 進行 <a href="#">訂單管理</a>,<br />
                                                        <a href="#">文章管理</a> 及 <a href="#">帳戶設定</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        -->
                                        <div class="tab-pane fade active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="mb-0">訂單詳細</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>訂單編號</th>
                                                                    <th>商品編號</th>
                                                                    <th>數量</th>
                                                                    <th>單價</th>
                                                                    
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                           		<%@ include file="page1.file" %>
																<c:forEach var="orderDetailVO" items="${list}"
																	begin="<%=pageIndex%>"
																	end="<%=pageIndex+rowsPerPage-1%>">
															
																<tr>
																	<td>${orderDetailVO.orderId}</td>
																	<td>${orderDetailVO.merId}</td>
																	<td>${orderDetailVO.qty}</td>
																	<td>${orderDetailVO.unitPrice}</td>
																	<c:if test="${orderSvc.getOneByOrderId(orderDetailVO.orderId).orderStatus == 3}">
																		<form method= "POST" ACTION= "orderDetail.do">
																			<button class="btn-small d-block" type="submit">評價留言</button>
																			<input type="hidden" name="orderId" value="${orderDetailVO.merId}">
																			<input type="hidden" name="action" value="ranking_Ord_Detail">
																		</form>
																	</c:if>
																</tr>
															
															</c:forEach>
															<%@ include file="page2.file" %>
                                                            </tbody>
                                                        </table>
                                                        <a href="accountCenter.jsp">回上頁</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--
                                        <div class="tab-pane fade" id="track-orders" role="tabpanel" aria-labelledby="track-orders-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="mb-0">Orders tracking</h3>
                                                </div>
                                                <div class="card-body contact-from-area">
                                                    <p>To track your order please enter your OrderID in the box below and press "Track" button. This was given to you on your receipt and in the confirmation email you should have received.</p>
                                                    <div class="row">
                                                        <div class="col-lg-8">
                                                            <form class="contact-form-style mt-30 mb-50" action="#" method="post">
                                                                <div class="input-style mb-20">
                                                                    <label>Order ID</label>
                                                                    <input name="order-id" placeholder="Found in your order confirmation email" type="text" />
                                                                </div>
                                                                <div class="input-style mb-20">
                                                                    <label>Billing email</label>
                                                                    <input name="billing-email" placeholder="Email you used during checkout" type="email" />
                                                                </div>
                                                                <button class="submit submit-auto-width" type="submit">Track</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        -->
                                        <div class="tab-pane fade" id="address" role="tabpanel" aria-labelledby="address-tab">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="card mb-3 mb-lg-0">
                                                        <div class="card-header">
                                                            <h3 class="mb-0">文章管理</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <address>
                                                                3522 Interstate<br />
                                                                75 Business Spur,<br />
                                                                Sault Ste. <br />Marie, MI 49783
                                                            </address>
                                                            <p>New York</p>
                                                            <a href="#" class="btn-small">Edit</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <h5 class="mb-0">Shipping Address</h5>
                                                        </div>
                                                        <div class="card-body">
                                                            <address>
                                                                4299 Express Lane<br />
                                                                Sarasota, <br />FL 34249 USA <br />Phone: 1.941.227.4444
                                                            </address>
                                                            <p>Sarasota</p>
                                                            <a href="#" class="btn-small">Edit</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="account-detail" role="tabpanel" aria-labelledby="account-detail-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>Account Details</h5>
                                                </div>
                                                <div class="card-body">
                                                    <p>Already have an account? <a href="page-login.html">Log in instead!</a></p>
                                                    <form method="post" name="enq">
                                                        <div class="row">
                                                            <div class="form-group col-md-6">
                                                                <label>First Name <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="name" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>Last Name <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="phone" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>Display Name <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="dname" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>Email Address <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="email" type="email" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>Current Password <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="password" type="password" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>New Password <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="npassword" type="password" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>Confirm Password <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="cpassword" type="password" />
                                                            </div>
                                                            <div class="col-md-12">
                                                                <button type="submit" class="btn btn-fill-out submit font-weight-bold" name="submit" value="Submit">Save Change</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
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
        <script src="/assets/js/vendors/modernizr-3.6.0.min.js"></script>
        <script src="/assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
        <script src="/assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="/assets/js/plugins/slick.js"></script>
        <script src="/assets/js/plugins/jquery.syotimer.min.js"></script>
        <script src="/assets/js/plugins/wow.js"></script>
        <script src="/assets/js/plugins/perfect-scrollbar.js"></script>
        <script src="/assets/js/plugins/magnific-popup.js"></script>
        <script src="/assets/js/plugins/select2.min.js"></script>
        <script src="/assets/js/plugins/waypoints.js"></script>
        <script src="/assets/js/plugins/counterup.js"></script>
        <script src="/assets/js/plugins/jquery.countdown.min.js"></script>
        <script src="/assets/js/plugins/images-loaded.js"></script>
        <script src="/assets/js/plugins/isotope.js"></script>
        <script src="/assets/js/plugins/scrollup.js"></script>
        <script src="/assets/js/plugins/jquery.vticker-min.js"></script>
        <script src="/assets/js/plugins/jquery.theia.sticky.js"></script>
        <script src="/assets/js/plugins/jquery.elevatezoom.js"></script>
        <!-- Template  JS -->
        <script src="/assets/js/main_frontend.js"></script>
        <script src="/assets/js/shop.js"></script>

</body>
</html>