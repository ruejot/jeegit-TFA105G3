<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- [editor,date] wei,2022-02-22 -->

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting 會員設定</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main_backend.css"
	type="text/css" />
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/sellerAside.jsp" />
	<main class="main-wrap">
		<jsp:include page="/views/sellerHeader_2.jsp" />
		<section class="content-main">
			<div class="card">
				<div class="card-header">
					<h5>修改密碼</h5>
				</div>
				<div class="card-body">
					<form method="post" name="enq"
						action="<%=request.getContextPath()%>/buss/BusPasswordChange">
						<div class="form-group col-md-6">
							<label>使用者ID：</label><span>${BusUsing.busid}</span><span>；</span>
							<label name="busEmail" type="email">Email(帳號)：<span>${BusUsing.email}</label>
							
							<input type="hidden" name="busBusid" value="${BusUsing.busid}"></input>
							<input type="hidden" name="busEmail" value="${BusUsing.email}"></input>
							
							<input type="hidden" name="busPhone" value="${BusUsing.phone}"></input>
							<input type="hidden" name="busAddress" value="${BusUsing.address}"></input>
							<input type="hidden" name="busTaxid" value="${BusUsing.taxid}"></input>
							<input type="hidden" name="busDate" value="${BusUsing.date}"></input>
							<input type="hidden" name="busEmail" value="${BusUsing.email}"></input>
							<input type="hidden" name="busIntro" value="${BusUsing.intro}"></input>
							<input type="hidden" name="busFB" value="${BusUsing.busFB}"></input>
							<input type="hidden" name="busIG" value="${BusUsing.busIG}"></input>
							<input type="hidden" name="busWebsite" value="${BusUsing.busWebsite}"></input>
							<input type="hidden" name="busPaymentprovide" value="${BusUsing.busPaymentprovide}"></input>
						</div>

						<div class="row">

							<div class="form-group col-md-12">
								<h6 style="color: red;">注意!${BusUsing.name}您現在正在進行密碼變更作業!!</h6>
								<input type="hidden" name="busName"
									value="${BusUsing.name}"></input>
							</div>

							<div class="form-group col-md-12">
								<label>現在的密碼*(如需修改資料，需輸入現在的密碼，以做確認)</label> <input required=""
									class="form-control" name="busPassword" type="password"
									placeholder="請輸入目前的密碼" />
								<!-- 現在密碼填錯時↓ -->
								<span style="color: red;">${busPWErrMsg}</span>
							</div>

							<div class="form-group col-md-12">
								<label>新的密碼*</label> <input required="" class="form-control"
									name="newBusPassword" type="password" placeholder="請輸入新密碼" />
							</div>
							<div class="form-group col-md-12">
								<label>確認密碼*</label> <input required="" class="form-control"
									name="newBusPasswordRp" type="password"
									placeholder="請再輸入一次新密碼" />
								<!-- 新密碼兩欄填寫不一致時↓ -->
								<span style="color: red;">${warningBusPWDismatchMsg}</span>
							</div>
							<div class="col-md-12">
								<button type="submit"
									class="btn btn-fill-out submit font-weight-bold" name="action"
									value="changepw">確認修改密碼</button>
								<!-- 必填欄位尚未被填寫時↓ -->
								<span style="color: red;">${warningBusPWMsg}</span>
								<!-- 會員資料設定修改成功時↓ -->
								<span style="color: red;">${BusPWupdateMsg}</span>
							</div>



							
						</div>
					</form>


				</div>

			</div>
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/sellerFooter.jsp" />
	</main>
	<!-- 此頁<main>結束 -->

	<!-- Main Script -->
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
	<!-- 60002e5c50.js是fontawesome給璟葶這個會員註冊的key碼，如果之後fontawesome有新版，可能會失效，到時請自己去fontawesome註冊拿新的key -->
	<script src="https://kit.fontawesome.com/60002e5c50.js"></script>
	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js"
		type="text/javascript"></script>
</body>
</html>
