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
    List<OrderVO> list = ordSvc.getOrdersByBusId(1);
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Manage Order</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- Template CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
</head>
<body>
	<div class="screen-overlay"></div>
	<%@ include file="/views/sellerAside.jsp" %>
	<main class="main-wrap">
	<jsp:include page="/views/sellerHeader_2.jsp"/>
		<section class="content-main">
			<div class="content-header">
				<div>
					<h2 class="content-title card-title">訂單管理</h2>
				</div>
				<div>
                    <input id="myInput" type="text" placeholder="查詢訂單編號" class="form-control bg-white" />                
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
							<select class="form-select" id="mySelector" onchange="myFunction()">
								<option id="myOption"selected>訂單狀態</option>
								<option value="1">處理中</option>
								<option value="2">配送中</option>
								<option value="3">已完成</option>
								<option value="4">已取消</option>
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
                                <tbody id="myTable">
                  				<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                                    <tr>
                                        <td class="col1">${orderVO.orderId}</td>
                                        <td><b>${memberSvc.select(orderVO.memberId).name}</b></td>
                                        <td>${memberSvc.select(orderVO.memberId).email}</td>
                                        <td>$${orderVO.orderSum}</td>              
                                        <td class="col2">
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 1}">1.處理中</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 2}">2.配送中</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 3}">3.已完成</c:if></span>
                                        <span class="badge rounded-pill alert-warning"><c:if test="${orderVO.orderStatus == 4}">4.已取消</c:if></span>
                                        </td>
                                        <td>${orderVO.orderTime}</td> 
                                        <td class="text-end">
                                         <form method="post" ACTION="<%=request.getContextPath()%>/nest-backend/orderDetail.do">
                                            <button class="btn btn-sm font-sm rounded btn-brand" type="submit">訂單明細</button>
                                            <input type="hidden" name="orderId"  value="${orderVO.orderId}">
			     							<input type="hidden" name="action" value="get_Ord_Detail">
                                          </form>
                                          <form method="post" ACTION="<%=request.getContextPath()%>/nest-backend/orderDetail.do">
                                            <button class="btn btn-sm font-sm btn-light rounded" type="submit">修改內容</button>
                                            <input type="hidden" name="orderId"  value="${orderVO.orderId}">
			     							<input type="hidden" name="action" value="get_Ord_Update">
                                          </form>
                                        </td>
                                       </tr>
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
		<jsp:include page="/views/sellerFooter.jsp"/>
	</main>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js" type="text/javascript"></script>
	<script src="https://kit.fontawesome.com/60002e5c50.js"></script>
	<script>
	
	$(function() {    
	    $('#myInput').change(function() { 
	        $("#myTable td.col1:contains('" + $(this).val() + "')").parent().show();
	        $("#myTable td.col1:not(:contains('" + $(this).val() + "'))").parent().hide();
	    });
	    
	});
	

	function myFunction() {
		  var input, filter, table, tr, td, i;
		  input = document.getElementById("mySelector");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[4];
		    if (td) {
		      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
	



	</script>
</body>
</html>