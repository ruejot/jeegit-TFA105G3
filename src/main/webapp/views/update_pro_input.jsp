<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Revise Product</title>
<%-- <%@ include file="./sellerHeader.jsp" %>  --%>
<jsp:include page="/views/sellerHeader1.jsp"/>
<!-- Template CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
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
					href="page-products-list.html"> <i
						class="icon material-icons md-shopping_bag"></i> <span
						class="text">商品管理</span>
				</a></li>
				<li class="menu-item has-submenu"><a class="menu-link"
					href="page-orders-1.html"> <i
						class="icon material-icons md-shopping_cart"></i> <span
						class="text">訂單管理</span>
				</a></li>
				<li class="menu-item has-submenu"><a class="menu-link"
					href="page-form-product-1.html"> <i
						class="icon material-icons md-add_box"></i> <span class="text">商品上架</span>
				</a></li>
				<li class="menu-item has-submenu"><a class="menu-link"
					href="page-transactions-1.html"> <i
						class="icon material-icons md-monetization_on"></i> <span
						class="text">財務管理</span>
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
		<form method="post" action="product.do" name="form1">
			<div class="row">
				<div class="col-9">
					<div class="content-header">
						<h2 class="content-title">修改上架商品</h2>
						<div>
							<button class="btn btn-md rounded font-sm hover-up" style="background-color:#386641;" type="submit"><span>重新上架</span></button>
							<input type="hidden" name="action" value="update">	
							<input type="hidden" name="merid" value="<%=productVO.getMerid()%>">
							<input type="hidden" name="busid" class="form-control" value="<%=(productVO==null)? "" : productVO.getBusid()%>" />	
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card mb-4">
						<div class="card-header">
							<h4>基本資料填寫</h4>
						</div>
						<div style="color:red;padding-left:12px;">
						<c:if test="${not empty errorMsgs}">
						<c:forEach var="message" items="${errorMsgs}">
						<li style="list-style: none">${message}</li>
						</c:forEach>
						</c:if>
						</div>
						<div class="card-body">
								<div class="mb-4">
									<label for="product_name" class="form-label">商品名稱</label>
									<input type="text" name="name" placeholder="外出牽繩、貓抓板...." class="form-control"
										id="product_name" value="<%=productVO.getName()%>" />
								</div>
								<div class="mb-4">
									<label for="description" class="form-label">商品資訊</label>
									<textarea name="description" id="description" placeholder="在此描述商品..." class="form-control" rows="4"><%=productVO.getDescription() %></textarea>
									<input type="hidden" name="description" value="<%=(productVO==null)? "" : productVO.getDescription()%>" />
								</div>
								<div class="row">
									<div class="col-lg-4">
										<div class="mb-4">
											<label class="form-label">上架日期</label>
											<div class="row gx-2">
												<input name="shelfDate" placeholder="2022-02-14" id="today" type="text" class="form-control" value="<%=productVO.getShelfDate()%>"/>
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="mb-4">
											<label class="form-label">商品價格</label> 
											<input name="price" placeholder="NTD$" type="text" class="form-control" value="<%=productVO.getPrice()%>" />
										</div>
									</div>
									<div class="col-lg-4">
										<label class="form-label">商品數量</label> 
										<input name="stock" placeholder="1-999" type="text" class="form-control" value="<%=productVO.getStock()%>" />
									</div>
								</div>
								 <div class="mb-4">
                                        <label class="form-label">上架狀態</label><br>
                                        <input class="form-check-input" id="yet" type="radio" name="status" value="<%=(productVO==null)? "1" : productVO.getStatus() %>" />
                                        <label for="yet">尚未開賣</label>
                                        <input class="form-check-input ml-10" id="onsell" type="radio" name="status" value="<%=(productVO==null)? "2" : productVO.getStatus() %>" />
                                        <label for="onsell">熱賣中</label>
                                        <input class="form-check-input ml-10" id="off" type="radio" name="status"  value="<%=(productVO==null)? "3" : productVO.getStatus() %>" checked />
                                        <label for="off">暫停販售</label>
                                </div>
								<div class="mb-4">
									<label class="form-label">出貨方式</label><br> 
									<input id="home" name="shippingMethod" class="form-check-input" type="checkbox" value="1" /> 
									<label for="home">宅配到府</label>
									<input id="convenient" name="shippingMethod" class="form-check-input ml-10" type="checkbox" value="2" />
									<label for="convenient">超商取貨</label>
									<input id="personal" name="shippingMethod" class="form-check-input ml-10" type="checkbox" value="3" /> 
									<label for="personal">面交</label>
									<input id="store" name="shippingMethod" class="form-check-input ml-10" type="checkbox" value="4" />
									<label for="store">到店取貨</label>	
								</div>
						</div>
					</div>
					<!-- card end// -->
				</div>
				<div class="col-lg-3">
				<!--  
					<div class="card mb-4">
						<div class="card-header">
							<h4>新增照片</h4>
						</div>
						<div class="card-body">
							<div class="input-upload">
								<img src="../assets/imgs/theme/upload.svg" alt="" /> 
								<input type="hidden" name="action" value="insert">
								<input class="form-control" type="file" />
							</div>
						</div>
					</div>
					-->
					<!-- card end// -->
					<div class="card mb-4">
						<div class="card-header">
							<h4>商品分類</h4>
						</div>
						<div class="card-body">
							<div class="row gx-2">
								<div class="col-sm-6 mb-3">
									<label class="form-label">主類別</label> 
									<select id=mainCategory class="form-select" name=mainCategory>
										<option value="寵物糧食">寵物糧食</option>
										<option value="生活用品">生活用品</option>
									</select>
								</div>
								<div class="col-sm-6 mb-3">
									<label class="form-label">子類別</label> 
									<select id=subCategory class="form-select" name=subCategory>
									<optgroup label="寵物糧食">
										<option value="寵物飼料">寵物飼料</option>
										<option value="寵物罐頭">寵物罐頭</option>
										<option value="寵物零食">寵物零食</option>
										<option value="寵物保健">寵物保健</option>
									</optgroup>
									<optgroup label="生活用品">
										<option value="寵物飼料">居家用品</option>
										<option value="寵物罐頭">外出用品</option>
										<option value="寵物零食">寵物玩具</option>
										<option value="寵物保健">美容護理</option>
									</optgroup>
									</select>
								</div>
								<div class="mb-4">
									<label for="product_name" class="form-label">HashTags</label>
									<input type="text" class="form-control" />
								</div>
							</div>
							<!-- row.// -->
						</div>
					</div>
					<!-- card end// -->
				</div>
			</div>
			</form>
		</section>
		<!-- content-main end// -->
<%-- 		<%@ include file="./footer.jsp" %>   --%>
<jsp:include page="/views/footer.jsp"/>
		<!--
		<footer class="main-footer font-xs">
			<div class="row pb-30 pt-15">
				<div class="col-sm-6">
					<script>
						document.write(new Date().getFullYear());
					</script>
					&copy; Petting - All rights reserved .
				</div>
			</div>
		</footer>
		 -->
	</main>
	<script src="../assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="../assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/vendors/select2.min.js"></script>
	<script src="../assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="../assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="../assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>

	<!-- 以下為日期設定 -->
<%--
  java.sql.Date shelfDate = null;
  try {
	    shelfDate = productVO.getShelfDate();
   } catch (Exception e) {
	    shelfDate = new java.sql.Date(System.currentTimeMillis());
   }
--%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#today').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=productVO.getShelfDate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        var $optgroups = $('#subCatetory > optgroup');
        $('mainCategory').on("change", function(){
        	var selectedVal = this.value;
     	$('#subCategory').html($optgroups.filter('[label="'+selectedVal+'"]'));
        //$('#subCategory > optgroup[label="'selectedVal+'"]').show().siblings("optgroup").css("display","none");
        });
</script> 
</html>