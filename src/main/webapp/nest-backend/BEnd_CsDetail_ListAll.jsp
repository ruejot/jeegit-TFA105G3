<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.csdetail.model.*"%>
<%@ page import="java.util.List"%>

<%
CsDetailService csDetailSvc = new CsDetailService();
List<CsDetailVO> list = csDetailSvc.selectAll();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MembersService" />

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>客服列表-Petting</title>
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
<!-- /assets/css/main.css  /csDetail/assets/css/main.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_backend.css" />
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/FIXAside.jsp" />
	<main class="main-wrap">
		<jsp:include page="/views/FIXsellerHeader.jsp" />
			<!-- 在<section>content-main start開始寫此後台頁內容 -->
		<section class="content-main">
			<div class="content-header">
				<div>
					<h2 class="content-title card-title">客服列表</h2>
					<p>回應顧客服務留言，全部列表.</p>
				</div>
				<!-- <div>
                        <input type="text" placeholder="Search order ID" class="form-control bg-white" />
                    </div> -->
			</div>
			<div class="card mb-4">
				<header class="card-header">
					<div class="row gx-3">
						<!--                             <div class="col-lg-4 col-md-6 me-auto"> -->
						<!--                                 <input type="text" placeholder="搜尋會員ID..." class="form-control" /> -->
						<!--                             </div> -->
						<div class="col-lg-2 col-6 col-md-3">
							<select class="form-select">
								<option>全部客服表單</option>
								<option>1_待處理</option>
								<option>2_處理中</option>
								<option>3_已完成</option>
							</select>
						</div>
						<div class="col-lg-2 col-6 col-md-3">
							<select class="form-select">
								<option>顯示 20筆</option>
								<option>顯示 30筆</option>
								<option>顯示 40筆</option>
							</select>
						</div>
					</div>
				</header>
				<!-- card-header end// -->
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>客服單編號</th>
									<th scope="col">會員</th>
									<th scope="col">回應內容</th>
									<th scope="col">立案時間</th>
									<th scope="col">回覆狀態</th>
									<th scope="col">回覆時間</th>
									<th scope="col" class="text-end">對客服單進行回應</th>
								</tr>
							</thead>
							<tbody>
								<%@ include file="/pages/CsDetail_page1.file"%>
								<c:forEach var="csDetailVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td>${csDetailVO.caseid}</td>
										<%-- 										<td>${csDetailVO.memberid}</td> --%>
										<td>${membersSvc.select(csDetailVO.memberid).name}</td>
										<%--  										<td><c:forEach var="membersBean" items="${membersSvc.all}"> --%>
										<%--  												<c:if test="${csDetailVO.memberid==membersBean.memberid}"> --%>
										<%--  													${membersBean.name} --%>
										<%--  												</c:if> --%>
										<%--  											</c:forEach> --%>
										<!--  										</td> -->
										<td>${csDetailVO.feedback}</td>
										<td>${csDetailVO.casetime}</td>
										<td><c:if test="${csDetailVO.replystatus == 1}">
												<span class="badge rounded-pill alert-danger">待處理</span>
											</c:if> <c:if test="${csDetailVO.replystatus == 2}">
												<span class="badge rounded-pill alert-warning">處理中</span>
											</c:if> <c:if test="${csDetailVO.replystatus == 3}">
												<span class="badge rounded-pill alert-success">已完成</span>
											</c:if></td>
										<td>${csDetailVO.replytime}</td>
										<td class="text-end">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/CsDetail.do" class="badge rounded font-sm">
												<input type="submit" value="回覆" class="btn btn-md rounded font-sm"> 
												<input type="hidden" name="caseid" value="${csDetailVO.caseid}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM> <!-- <a href="#" class="btn btn-md rounded font-sm">內容</a> -->
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<!-- table-responsive //end -->
				</div>
				<!-- card-body end// -->
			</div>
			<!-- card end// -->
			<div class="pagination-area mt-15 mb-50">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-start">
						<%@ include file="/pages/CsDetail_page2.file"%>
						<!-- <li class="page-item active"><a class="page-link" href="#">01</a></li> -->
						<!-- <li class="page-item"><a class="page-link" href="#">02</a></li>
                            <li class="page-item"><a class="page-link" href="#">03</a></li>
                            <li class="page-item"><a class="page-link dot" href="#">...</a></li>
                            <li class="page-item"><a class="page-link" href="#">16</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#"><i class="material-icons md-chevron_right"></i></a>
                            </li> -->
					</ul>
				</nav>
			</div>
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/footer.jsp" />
	</main>
	<!-- 此頁<main>結束 -->

	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script
		src="<%=request.getContextPath()%>/assets/js/main_backend.js?v=1.1"
		type="text/javascript"></script>
</body>
</html>
