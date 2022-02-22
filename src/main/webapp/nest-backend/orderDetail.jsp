<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="java.util.*"%>

<%
	OrderDetailService ordDetailSvc = new OrderDetailService();
    List<OrderDetailVO> list = ordDetailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%
	OrderService ordSvc = new OrderService();
    List<OrderVO> ordList = ordSvc.getAll();
    pageContext.setAttribute("ordList",ordList);
%>

<!-- 這一支nest-backend/orderDetail.java是璟葶的 -->
<!-- 另nest-frontend 有一隻同名的orderDetail.java是毓珊的 -->
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>Order Details</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!-- Template CSS -->
        <link href="assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="screen-overlay"></div>
        <aside class="navbar-aside" id="offcanvas_aside">
            <div class="aside-top">
                <a href="index.html" class="brand-wrap"> 
                <img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg" class="logo" alt="logo" />
                </a>
                <div>
                    <button class="btn btn-icon btn-aside-minimize">
                        <i class="text-muted material-icons md-menu_open"></i>
                    </button>
                </div>
            </div>
            <nav>
                <ul class="menu-aside">
                    <li class="menu-item"><a class="menu-link" href="index.html">
                            <i class="icon material-icons md-home"></i> <span class="text">會員中心</span>
                    </a></li>
                    <li class="menu-item has-submenu"><a class="menu-link"
                        href="<%=request.getContextPath()%>/nest-backend/productManage.jsp"> <i
                            class="icon material-icons md-shopping_bag"></i> <span
                            class="text">商品管理</span>
                    </a></li>
                    <li class="menu-item has-submenu"><a class="menu-link"
                        href="<%=request.getContextPath()%>/nest-backend/orderManage.jsp"> <i
                            class="icon material-icons md-shopping_cart"></i> <span
                            class="text">訂單管理</span>
                    </a></li>
                    <li class="menu-item has-submenu"><a class="menu-link"
                        href="<%=request.getContextPath()%>/nest-backend/addProduct.jsp"> <i
                            class="icon material-icons md-add_box"></i> <span class="text">商品上架</span>
                    </a></li>
                    <li class="menu-item has-submenu"><a class="menu-link" href="#">
                            <i class="icon material-icons md-person"></i> <span class="text">我的帳戶</span>
                    </a></li>
                    <li class="menu-item"><a class="menu-link"
                        href="page-reviews.html"> <i
                            class="icon material-icons md-comment"></i> <span class="text">留言評價</span>
                    </a></li>
                    <li class="menu-item"><a class="menu-link" href="#"> <i
                            class="icon material-icons md-pie_chart"></i> <span class="text">數據中心</span>
                    </a></li>
                </ul>
                <hr />
                <ul class="menu-aside">
                    <li class="menu-item has-submenu"><a class="menu-link" href="#">
                            <i class="icon material-icons md-settings"></i> <span class="text">相關設定</span>
                    </a></li>
                </ul>
                <br /> <br />
            </nav>
        </aside>
        <main class="main-wrap">
            <section class="content-main">
                <div class="content-header">
                    <div>
                        <h2 class="content-title card-title">訂單明細</h2>
                    </div>
                </div>
                <div class="card">
                    <header class="card-header">
                        <div class="row align-items-center">
                            <div class="col-lg-6 col-md-6 mb-lg-0 mb-15">
                            <c:forEach var="orderDetailVO" items="${list}" >
                                <small class="text-muted">Order ID: ${orderDetailVO.orderId}</small>
                            </c:forEach>
                            </div>
                            <div class="col-lg-6 col-md-6 ms-auto text-md-end">
                                <select class="form-select d-inline-block mb-lg-0 mr-5 mw-200">
                                    <option>修改訂單狀態</option>
                                    <option>處理中</option>
                                    <option>配送中</option>
                                    <option>已完成</option>
                                    <option>已取消</option>
                                </select>
                                <a class="btn btn-primary" href="#">儲存</a> <!-- 要寫updateServlet -->
                            </div>
                        </div>
                    </header>
                    <!-- card-header end// -->
                    <div class="card-body">
                        <div class="row mb-50 mt-20 order-info-wrap">
                            <div class="col-md-4">
                                <article class="icontext align-items-start">
                                    <span class="icon icon-sm rounded-circle bg-primary-light">
                                        <i class="text-primary material-icons md-person"></i>
                                    </span>
                                    <div class="text">
                                        <h6 class="mb-1">顧客資料</h6>
                                        <p class="mb-1">
                                           姓名: 王曉明 <br />
                                           Email: alex@example.com <br />
                                           電話: 0988-123-123
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <!-- col// -->
                            <c:forEach var="orderVO" items="${ordList}">
                            <div class="col-md-4">
                                <article class="icontext align-items-start">
                                    <span class="icon icon-sm rounded-circle bg-primary-light">
                                        <i class="text-primary material-icons md-place"></i>
                                    </span>
                                    <div class="text">
                                        <h6 class="mb-1">收件人資訊</h6>
                                        <p class="mb-1">
                                            收件人: ${orderVO.receiver} <br />收件地址: ${orderVO.receiverAddr} <br />
                                            電話: ${orderVO.receiverPhone}
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <!-- col// -->
                            <div class="col-md-4">
                                <article class="icontext align-items-start">
                                    <span class="icon icon-sm rounded-circle bg-primary-light">
                                        <i class="text-primary material-icons md-local_shipping"></i>
                                    </span>
                                    <div class="text">
                                        <h6 class="mb-1">運送資訊</h6>
                                        <p class="mb-1">
                                            出貨方式: ${orderVO.shippingId}<br />
                                            追蹤碼: ${orderVO.tracking}
                                        </p>
                                    </div>
                                </article>
                            </div>
                            </c:forEach>
                            <!-- col// -->
                        </div>
                        <!-- row // -->
                        <div class="row">
                            <div class="col-lg-7">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th width="35%">產品名稱</th>
                                                <th width="15%">單價</th>
                                                <th width="15%">數量</th>
                                                <th width="15%" class="text-end">小計</th>
                                                <th width="20%"class="text-end">評價</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="orderDetailVO" items="${list}" >
                                            <tr>
                                                <td>
                                                    <a class="itemside" href="#"> 
                                                        <div>${orderDetailVO.merId}</div>
                                                    </a>
                                                </td>
                                                <td>${orderDetailVO.unitPrice}</td>
                                                <td>${orderDetailVO.qty}</td>
                                                <td class="text-end">${orderDetaulVO.unitPrice*orderDetailVO.qty}</td>
                                            </tr>
                                            </c:forEach>
                                            <c:forEach var="orderVO" items="${ordList}">
                                            <tr>
                                                <td colspan="4">
                                                    <article class="float-end">
                                                        <dl class="dlist">
                                                            <dt>總金額:</dt>
                                                            <dd><b class="h5">${orderVO.orderSum}</b></dd>
                                                        </dl>
                                                    </article>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- table-responsive// -->
                            </div>
                            <!-- col// -->
                            <div class="col-lg-1"></div>
                            <div class="col-lg-4">
                            <c:forEach var="orderVO" items="${ordList}">
                                <div class="box shadow-sm bg-light">
                                    <h6 class="mb-15">付款資訊</h6>
                                    <p>
                                        付款方式: ${orderVO.paymentId} <br />
                                        發票號碼: ${orderVO.invoiceId} <br />
                                    </p>
                                </div>
                            </c:forEach>
                            </div>
                            <!-- col// -->
                        </div>
                    </div>
                    <!-- card-body end// -->
                </div>
                <!-- card end// -->
            </section>
            <!-- content-main end// -->
        </main>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="<%=request.getContextPath()%>/assets/js/main.js?v=1.1" type="text/javascript"></script>
    </body>
</html>