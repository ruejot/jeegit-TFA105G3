<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>


<%--
List<ProductVO> list = (List<ProductVO>)session.getAttribute("list");
//ProductServlet.java(Controller), 存入session的list物件
--%>

<%--

BusVO busVO = (BusVO)session.getAttribute("BusUsing");

 --%>

<%
	ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getProductsByBusid(1); //proSvc.getProductsByBusid(busVO.getBusid());
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Manage Product</title>
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
					<h2 class="content-title card-title">商品管理</h2>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/nest-backend/addProduct.jsp" class="btn btn-primary btn-sm rounded">新增商品</a>
				</div>
			</div>
			<div class="card mb-4">
				<header class="card-header">
					<div class="row align-items-center">
						<div class="col col-check flex-grow-0">
							<div class="form-check ms-2">
								
							</div>
						</div>
						<div class="col-md-3 col-12 me-auto mb-md-0 mb-3">
						</div>
						<!-- 
						<div class="col-md-2 col-6">
							<select class="form-select">
								<option selected>上架狀態</option>
								<option>尚未開賣</option>
								<option>熱賣中</option>
								<option>暫停販售</option>
							</select>
						</div>
						-->
					</div>
				</header>
				<!-- card-header end// -->
				<div class="card-body">
				<article class="itemlist">
						<div class="row align-items-center">
							<div class="col col-check flex-grow-0">
								<div class="">
								
								</div>
							</div>
							<div class="col-lg-3 col-sm-4 col-8 flex-grow-1 col-name">
							<div class="itemside">
								<h6 class="mb-0">商品照片</h6>
							<div class="info">
								<h6 class="mb-0">商品名稱</h6>
							</div>
							</div>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-price">
								<h6 class="mb-0">商品價格</h6>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-price">
								<h6 class="mb-0">庫存數量</h6>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-status">
								<h6 class="mb-0">上架狀態</h6>
							</div>
							<div class="col-lg-2 col-sm-2 col-4 col-date">
								<h6 class="mb-0">上架日期</h6>
							</div>
							<div class="col-lg-2 col-sm-2 col-4 col-action text-end">		
							</div>
						</div>
						<!-- row .// -->
					</article>
				<%@ include file="pages/page1.file" %> 
				<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" > 
					<article class="itemlist">
						<div class="row align-items-center" id="tableRow">
							<div class="col col-check flex-grow-0">				
									
							</div>
							<div class="col-lg-3 col-sm-4 col-8 flex-grow-1 col-name">
								<a class="itemside" href="#">
								 <jsp:useBean id="productImgSvc" scope="page" class="com.productImg.model.ProductImgService" />
										<img src="<%=request.getContextPath()%>/ShowPic?imgid=${productImgSvc.getOneProductImg(productVO.merid).imgid}"
											class="img-sm img-thumbnail" alt="Item" />
									<div class="info">
										<h6 class="mb-0">${productVO.name}</h6>
									</div>
								</a>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-price">
								<span>${productVO.price}</span>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-price">
								<span>${productVO.stock}</span>
							</div>
							<div class="col-lg-1 col-sm-2 col-4 col-status">
								<c:if test="${productVO.status == 1}">尚未開賣</c:if>
								<c:if test="${productVO.status == 2}">熱賣中</c:if>	
								<c:if test="${productVO.status == 3}">暫停販售</c:if>
							</div>
							<div class="col-lg-2 col-sm-2 col-4 col-date">
								<span>${productVO.shelfDate}</span>
							</div>
							<div class="col-lg-2 col-sm-2 col-4 col-action text-end">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/nest-backend/product.do">
								<button class="btn btn-sm font-sm rounded btn-brand" type="submit"><i class="material-icons md-edit"></i>修改</button>			
			     				<input type="hidden" name="merid"  value="${productVO.merid}">
			     				<input type="hidden" name="action" value="getOne_For_Update"></FORM>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/nest-backend/product.do">
								<button class="btn btn-sm font-sm btn-light rounded" style="margin-top:5px" type="submit"><i class="material-icons md-delete_forever"></i>刪除</button>
			     				<input type="hidden" name="merid"  value="${productVO.merid}">
			     				<input type="hidden" name="action" value="delete"></FORM>			     			
							</div>
						</div>
						<!-- row .// -->
					</article>
				</c:forEach>
						<!-- row .// -->
					<!-- itemlist  .// -->
				</div>
				<!-- card-body end// -->
			</div>
			<!-- card end// -->
			<%@ include file="pages/page2.file" %>
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/sellerFooter.jsp" />  
	</main>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js" type="text/javascript"></script>
	<script src="https://kit.fontawesome.com/60002e5c50.js"></script>
	<script>$('#myTableRow').remove();</script>
</body>
</html>