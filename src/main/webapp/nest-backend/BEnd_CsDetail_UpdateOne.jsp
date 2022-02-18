<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.csdetail.model.*"%>
<%@ page import="java.util.List"%>

<%
CsDetailVO csDetailVO = (CsDetailVO) request.getAttribute("csDetailVO"); //CsDetailServlet.java (Concroller) 存入req的csDetailBean物件 (包括幫忙取出的csDetailBean, 也包括輸入資料錯誤時的csDetailBean物件)
%>

<jsp:useBean id="membersSvc" scope="page"
	class="com.members.model.MembersService" />

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
			href="/assets/imgs/theme/Petting_logo.png" />
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
		<section class="content-main">
			<!-- 在<section>content-main start開始寫此後台頁內容 -->
			<div class="row">
				<div class="col-9">
					<div class="content-header">
						<h2 class="content-title">
							會員編號
							<%=csDetailVO.getMemberid()%>
							的客服單
						</h2>
					</div>
				</div>
				<div class="col-9">
					<div class="card">
						<div class="card-body">
							<div class="row gx-5">
								<aside class="col-lg-3 border-end">
									<nav class="nav nav-pills flex-column mb-4">
										<a class="nav-link active" aria-current="page" href="#">回應單筆</a>
										<!-- <a class="nav-link" href="#">回應單筆</a> -->
									</nav>
								</aside>
								<div class="col-lg-9">
									<section class="content-body p-xl-4">
										<form>
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">客服單編號</label>
												<div class="col-lg-9">
													<label class="col-form-label"><%=csDetailVO.getCaseid()%></label>
													<!-- <input type="text" class="form-control" placeholder="Type here" /> -->
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">立案時間</label>
												<div class="col-lg-9">
													<label class="col-form-label"><%=csDetailVO.getCasetime()%></label>
													<!-- <input type="text" class="form-control" placeholder="Type here" /> -->
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">顧客意見內容</label>
												<div class="col-lg-9">
													<label class="col-form-label"><%=csDetailVO.getFeedback()%></label>
													<!-- <input type="text" class="form-control" placeholder="Type here" /> -->
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">回覆狀態</label>
												<div class="col-lg-9">
													<label class="mb-2 form-check form-check-inline"
														style="width: 45%;"> <input
														class="form-check-input" name="mycategory" type="radio">
														<span class="col-form-label"> 1_待處理 </span>
													</label> <label class="mb-2 form-check form-check-inline"
														style="width: 45%;"> <input
														class="form-check-input" checked="" name="mycategory"
														type="radio"> <span class="col-form-label">
															2_處理中 </span>
													</label> <label class="mb-2 form-check form-check-inline"
														style="width: 45%;"> <input
														class="form-check-input" name="mycategory" type="radio">
														<span class="col-form-label"> 3_已完成 </span>
													</label>
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">回覆內容</label>
												<div class="col-lg-9">
													<textarea class="form-control" placeholder="輸入回應..."
														rows="3">幫您確認，晚點回覆您，請稍等!</textarea>
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">回覆時間</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="YYY-MM-DD" value="2022-02-17" />
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<!-- row.// -->
											<br />
											<button class="btn btn-primary" type="submit">完成回應更新</button>
										</form>
									</section>
									<!-- content-body .// -->
								</div>
								<!-- col.// -->
							</div>
							<!-- row.// -->
						</div>
						<!-- card body end// -->
					</div>
				</div>
			</div>
		</section>
		<!-- section content-main end// -->
		<jsp:include page="/views/footer.jsp" />
	</main>
	<script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="assets/js/vendors/select2.min.js"></script>
	<script src="assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>
