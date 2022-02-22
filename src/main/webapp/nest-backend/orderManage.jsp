<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>


<%--
List<OrderVO> list = (List<OrderVO>)session.getAttribute("list");
//OrderServlet.java(Controller), 存入session的list物件
--%>

<%
	OrderService ordSvc = new OrderService();
    List<OrderVO> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Manage Order</title>
<jsp:include page="/views/sellerHeader1.jsp"/>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- Template CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
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
					<h2 class="content-title card-title">訂單管理</h2>
				</div>
				<div>
                    <input type="text" placeholder="查詢訂單編號" class="form-control bg-white" />
                </div>
			</div>
			<div class="card mb-4">
				<header class="card-header">
					<div class="row align-items-center">
						<div class="col col-check flex-grow-0">
							<div class="form-check ms-2">
								
							</div>
						</div>
						<div class="col-md-2 col-6">
							<input type="date" class="form-control" />
						</div>
						<div class="col-md-2 col-6">
							<select class="form-select">
								<option selected>訂單狀態</option>
								<option>處理中</option>
								<option>配送中</option>
								<option>已完成</option>
								<option>已取消</option>
							</select>
						</div>
					</div>
				</header>
				</div>
				<!-- card-header end// -->
				 <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>訂單編號</th>
                                        <th scope="col">姓名</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">訂單總額</th>
                                        <th scope="col">訂單狀態</th>
                                        <th scope="col">訂購時間</th>
                                        <th scope="col" class="text-end">訂單管理</th>
                                    </tr>
                                </thead>
                                <%@ include file="/pages/Prod_page1.file" %>
                                <jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />
                                <tbody>
                  				<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                                    <tr>
                                        <td>${orderVO.orderId}</td>
                                        <td><b>${orderVO.memberId}</b></td>
                                        <td>marvin@example.com</td>
                                        <td>$${orderVO.orderSum}</td>              
                                        <td>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 1}">處理中</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 2}">配送中</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 3}">已完成</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 4}">已取消</c:if></span>
                                        </td>
                                        <td>${orderVO.orderTime}</td> 
                                        <td class="text-end">
                                         <form method="post" ACTION="<%=request.getContextPath()%>/nest-backend/orderDetail.do">
                                            <button class="btn btn-sm font-sm rounded btn-brand" type="submit">訂單明細</button>
                                            <input type="hidden" name="orderId"  value="${orderDetailVO.orderId}">
			     							<input type="hidden" name="action" value="get_Ord_Detail">
                                          </form>
                                          <form method="post" ACTION="<%=request.getContextPath()%>/nest-backend/order.do">
                                            <button class="btn btn-sm font-sm btn-light rounded" type="submit">修改內容</button>
                                            <input type="hidden" name="orderId"  value="${orderVO.orderId}">
			     							<input type="hidden" name="action" value="get_Ord_Update">
                                          </form>
                                        </td>
                                   </c:forEach>
                        		 </tbody>
                            </table>
                        </div>
                        <!-- table-responsive //end -->
                       </div>
                    <!-- card-body end// -->
			<%@ include file="/pages/Prod_page2.file" %>
		</section>
		<!-- content-main end// -->
	</main>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js" type="text/javascript"></script>
</body>
</html>