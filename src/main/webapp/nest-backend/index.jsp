<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="java.util.*"%>

<% 
	Integer busid = 1; 
	//Object account = session.getAttribute("BusUsing");
	//if(account == null) {
	//session.setAttribute("location", req.getRequestURI());
	//res.sendRedirect(req.getContextPath() + "/login.html");
%>

<%
	OrderService ordSvc = new OrderService();
    List<OrderVO> list = ordSvc.getOrdersByBusId(1);
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
 <meta http-equiv="x-ua-compatible" content="ie=edge" />
 <meta name="description" content="" />
 <meta name="viewport" content="width=device-width, initial-scale=1" />
 <!-- Template CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
</head>
<body>
<body>
        <div class="screen-overlay"></div>
        <%@ include file="/views/sellerAside.jsp" %>
        <main class="main-wrap">
           <jsp:include page="/views/sellerHeader_2.jsp"/>	
            <section class="content-main">
                <div class="content-header">
                    <div>
                        <h2 class="content-title card-title">賣家中心</h2>
                    </div>                    
                </div>
                <div class="card mb-4">
                    <header class="card-header">
                        <h4 class="card-title">最新訂單<h4>
                        <div class="row align-items-center"></div>
                    </header>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div class="table-responsive">
                                <table class="table align-middle table-nowrap mb-0">
                                    <thead class="table-light">
                                        <tr>
                                            <th class="align-middle" scope="col">Order ID</th>
                                            <th class="align-middle" scope="col">顧客名稱</th>
                                            <th class="align-middle" scope="col">訂單日期</th>
                                            <th class="align-middle" scope="col">訂單總額</th>
                                            <th class="align-middle" scope="col">訂單狀態</th>
                                            <th class="align-middle" scope="col">付款方式</th>
                                            <th class="align-middle" scope="col">訂單明細</th>
                                        </tr>
                                    </thead>
                                     <%@ include file="/pages/Prod_page1.file" %>
                                     <jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />
                                    <tbody id="myTable">
                                    <c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                                        <tr>
                                            <td class="text-center">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="transactionCheck02" />
                                                    <label class="form-check-label" for="transactionCheck02"></label>
                                                </div>
                                            </td>
                                            <td><a href="#" class="fw-bold">${orderVO.orderId}</a></td>
                                            <td>${memberSvc.select(orderVO.memberId).name}</td>
                                            <td>${orderVO.orderTime}</td>
                                            <td>$${orderVO.orderSum}</td>
                                             <td class="col2">
                                        		<span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 1}">1.處理中</c:if></span>
                                        		<span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 2}">2.配送中</c:if></span>
                                        		<span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 3}">3.已完成</c:if></span>
                                        		<span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 4}">4.已取消</c:if></span>
                                        	</td>
                                            <td><i class="material-icons md-payment font-xxl text-muted mr-5"></i> 信用卡</td>
                                            <td>
                                          <form method="post" ACTION="<%=request.getContextPath()%>/nest-backend/orderDetail.do">
                                           		<button class="btn btn-sm font-sm rounded btn-brand" type="submit">查看明細</button>
                                            	<input type="hidden" name="orderId"  value="${orderVO.orderId}">
			     								<input type="hidden" name="action" value="get_Ord_Detail">
                                          </form>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- table-responsive end// -->
                    </div>
                </div>
                <%@ include file="/pages/Prod_page2.file" %>
            </section>
 		<jsp:include page="/views/sellerFooter.jsp"/>
        </main>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="<%=request.getContextPath()%>/assets/js/main.js?v=1.1" type="text/javascript"></script>
        <script src="https://kit.fontawesome.com/60002e5c50.js"></script>
    </body>
</html>