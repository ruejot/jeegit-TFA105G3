<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>

<%	
	Integer memberId = 22;
    OrderService ordSvc = new OrderService();
    List<OrderVO> list = ordSvc.getOrdersByMemberId(memberId);
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
    <head>
        <meta charset="utf-8" />
        <title>Petting 會員中心</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
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
                                                <a class="nav-link active" id="orders-tab" data-bs-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false"><i class="fi-rs-shopping-bag mr-10"></i>訂單管理</a>
                                            </li>
                                            <!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
                                            <li class="nav-item">
                                                <a class="nav-link" id="address-tab" data-bs-toggle="tab" href="#address" role="tab" aria-controls="address" aria-selected="true"><i class="fi-rs-marker mr-10"></i>文章管理</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="nav-link" id="account-detail-tab" data-bs-toggle="tab" href="#account-detail" role="tab" aria-controls="account-detail" aria-selected="true"><i class="fi-rs-user mr-10"></i>帳戶設定</a>
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
                                                    <h3 class="mb-0">訂單管理</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>訂單編號</th>
                                                                    <th>訂購時間</th>
                                                                    <th>訂單狀態</th>
                                                                    <th>總金額</th>
                                                                    
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                            <%@ include file="page1.file" %>
															<c:forEach var="orderVO" items="${list}"
																begin="<%=pageIndex%>"
																end="<%=pageIndex+rowsPerPage-1%>">

																<tr>
																	<td>${orderVO.orderId}</td>
																	<td>${orderVO.orderTime}</td>
																	<td><c:if test="${orderVO.orderStatus == 1}">處理中</c:if>
																		<c:if test="${orderVO.orderStatus == 2}">配送中</c:if>
																		<c:if test="${orderVO.orderStatus == 3}">已完成</c:if>
																		<c:if test="${orderVO.orderStatus == 4}">已取消</c:if></td>
																	<td>$${orderVO.orderSum}</td>
																	<td>
																		<form method= "POST" ACTION= "orderDetail.do">
																			<button class="btn-small d-block" type="submit">詳細</button>
																			<input type="hidden" name="orderId" value="${orderVO.orderId}">
																			<input type="hidden" name="action" value="get_Ord_Detail">
																		</form>
																	</td>
																	<c:if test="${orderVO.orderStatus == 1}">
																		<td>
																			<form method= "POST" ACTION= "order.do">
																				<button class="" type="submit">取消</button>
																				<input type="hidden" name="orderId" value="${orderVO.orderId}">
																				<input type="hidden" name="action" value="cancel_Ord">
																			</form>
																		</td>
																	</c:if>
																	<c:if test="${orderVO.orderStatus != 1}">
																		<td>
																				<button class="" type="">無法取消</button>
																		</td>
																	</c:if>
																</tr>
															</c:forEach>
															<%@ include file="page2.file" %>
                                                            </tbody>
                                                        </table>
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

                                        <!-- ===============================帳戶資料=============================== -->
                                        <div class="tab-pane fade" id="account-detail" role="tabpanel" aria-labelledby="account-detail-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>帳戶資料</h5>
                                                </div>
                                                <div class="card-body">
                                                    <p>已有帳號嗎? <a href="<%=request.getContextPath()%>/nest-frontend/Login.jsp">由此登入</a></p>
                                                    <form method="post" name="enq" action="<%=request.getContextPath()%>/members/MembersDataUpdate">
                                                        <div class="row">
                                                            <div class="form-group col-md-6">
                                                                <label>姓名<span class="required">*</span></label>
                                                                <input required="" class="form-control" name="name" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>暱稱</label>
                                                                <input class="form-control" name="membersNickname" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>手機<span class="required">*</span></label>
                                                                <input required="" class="form-control" name="membersMobile" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>電話</label>
                                                                <input class="form-control" name="membersPhone" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>地址</label>
                                                                <input class="form-control" name="membersAddress" type="text" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>Email(帳號) <span class="required">*</span></label>
                                                                <input required="" class="form-control" name="email" type="email" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>現在的密碼</label>
                                                                <input required="" class="form-control" name="password" type="password" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>新的密碼</label>
                                                                <input required="" class="form-control" name="npassword" type="password" />
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>確認密碼</label>
                                                                <input required="" class="form-control" name="cpassword" type="password" />
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>簡介</label>
                                                                <input class="form-control" name="membersIntro" type="text" />
                                                            </div>
                                                            <div class="col-md-12">
                                                                <button type="submit" class="btn btn-fill-out submit font-weight-bold" name="action" value="membersdataupdate">儲存修改</button>
                                                            </div>

                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- ===============================帳戶資料=============================== -->

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
        <script src="assets/js/vendors/modernizr-3.6.0.min.js"></script>
        <script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
        <script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="assets/js/plugins/slick.js"></script>
        <script src="assets/js/plugins/jquery.syotimer.min.js"></script>
        <script src="assets/js/plugins/wow.js"></script>
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
        <script src="assets/js/main.js"></script>
        <script src="assets/js/shop.js"></script>
    </body>
</html>
