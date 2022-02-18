<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
<head>
	<meta charset="utf-8">
	<title>Petting 會員中心</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!-- Template CSS -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>
<body>
	<jsp:include page="/views/userHeader.jsp"/>
        <main class="main pages">
            <div class="page-header breadcrumb-wrap">
                <div class="container">
                    <div class="breadcrumb">
                        <a href="<%=request.getContextPath()%>/views/index.jsp" rel="nofollow"><i class="fi-rs-home mr-5"></i>首頁</a>
                        <a href="<%=request.getContextPath()%>/order/orderOverview.jsp" ><span></span> 會員中心</a>
                        <!--
                        <span></span> Pages <span></span> My Account
                        -->
                    </div>
                </div>
            </div>
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
                                                <a class="nav-link" id="orders-tab" data-bs-toggle="tab" href="<%=request.getContextPath()%>/order/orderOverview.jsp" role="tab" aria-controls="orders" aria-selected="false"><i class="fi-rs-shopping-bag mr-10"></i>訂單管理</a>
                                            </li>
                                            <!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
                                            <li class="nav-item">
                                                <a class="nav-link active" id="address-tab" data-bs-toggle="tab" href="<%=request.getContextPath()%>/views/articleManagement.jsp" role="tab" aria-controls="address" aria-selected="true"><i class="fi-rs-marker mr-10"></i>文章管理</a>
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
                                        <div class="tab-pane fade active show" id="address" role="tabpanel" aria-labelledby="address-tab">
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
        
        <!-- Template  JS -->
        <script src="/assets/js/main_frontend.js"></script>
        <script src="/assets/js/shop.js"></script>

</body>
</html>