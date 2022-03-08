<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bus.model.*"%>

<%
BusVO busVO = (BusVO) session.getAttribute("BusUsing");
%>
<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>Petting 會員中心(廠商資料)</title>
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
	
	<!-- 付款方式的input標籤的css -->
	<style>
		input[type="checkbox"] {
  display: none;
}
input[type="checkbox"]+span {
  display: inline-block;
  padding-left: 26px;
  line-height: 20px;
  background: url(https://i.imgur.com/bZM5Itd.png) no-repeat left top;
            user-select: none;
}
input[type="checkbox"]:checked+span {
  background: url(https://i.imgur.com/JWm4WKA.png) no-repeat left top;
}
	</style>
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/sellerAside.jsp" />
	<main class="main-wrap">
		<jsp:include page="/views/sellerHeader_2.jsp" />
		<section class="content-main">


			<div class="card">
				<div class="card-header">
					<h2>廠商資料</h2>
				</div>
				<div class="card-body">
					<form method="post" name="enq"
						action="<%=request.getContextPath()%>/bus/BusDataUpdate">
						<div class="form-group col-md-6">
							<label>使用者ID：</label><span>${BusUsing.busid}</span> <input
								type="hidden" name="busBusid" value="${BusUsing.busid}"></input>
							<input type="hidden" name="busDate" value="${BusUsing.date}"></input>
							<%--<input type="hidden" name="busDate" value="<%=busVO.getDate()%>"></input> --%>
							<input type="hidden" name="busPaymentprovide" value="${BusUsing.paymentprovide}"></input>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label>店家名稱 *<span class="required"></span></label>
								<!-- <input required="" class="form-control" name="name" type="text" /> -->
								<input required="required" class="form-control" name="busName"
									type="text" value="${BusUsing.name}">
							</div>
							<div class="form-group col-md-6">
								<label>統一編號</label> <input class="form-control" name="busTaxid"
									type="text" value="${BusUsing.taxid}"></input>
							</div>
							<div class="form-group col-md-12">
								<label>Email *(本欄位不可更改)</label>
								<!-- input標籤設為disabled="disabled"為不可編輯的意思 -->
								<input required="required" class="form-control"
									readonly="readonly" name="busEmail" type="email"
									value="${BusUsing.email}" style="background-color: #e9ecef;"></input>
							</div>
							<div class="form-group col-md-6">
								<label>電話*</label> <input required="required"
									class="form-control" name="busPhone" type="text"
									value="${BusUsing.phone}"></input>
							</div>

							<!-- <div class="form-group col-md-6">
								<label>提供的付款方式(請選擇)</label> <select class="form-select"
									aria-label="Default select example">
									<option selected name="busPaymentprovide"
										value="${BusUsing.paymentprovide}">提供的付款方式(請選擇)</option>
									<option value="1">1.信用卡付款</option>
									<option value="2">2.貨到付款</option>
									<option value="3">3.ATM轉帳</option>
									<option value="4">4.超商付款</option>
								</select>
							</div> -->

							<!-- 既然購物車相對應也沒做出這個，那我就先....XDD -->
							<!-- <div class="form-group col-md-12"> -->
								<!-- <label>提供的付款方式(請勾選)：</label><label class="result"></label> -->
								<!-- <div class="result"></div> -->
							<!-- </div> -->
							<!-- <form>
								<label>
									<input type="checkbox" value="1" style="height: 14px;" />
									<span>1.信用卡付款</span>
								</label>
								<label>
									<input type="checkbox" value="2" style="height: 14px;" />
									<span>2.貨到付款</span>
								</label>
								<label>
									<input type="checkbox" value="3" style="height: 14px;"/>
									<span>3.ATM轉帳</span>
								</label>
								<label>
									<input type="checkbox" value="4" style="height: 14px;"/>
									<span>4.超商付款</span>
								</label>
								<hr />
							</form> -->
							<!-- <div class="form-group col-md-6"> -->
							<!-- 如何顯示勾選的js code在下方script -->
							<!-- <button onclick="checkResult()" class="btn btn-fill-out submit font-weight-bold btn-sm" style="border-color:green;border-width:1px;border-style:solid;">確認勾選</button>								 -->
							<!-- </div> -->
							

							<!-- <div class="row">
								<div class="form-check">//多選
									<input class="form-check-input" type="checkbox" value="1" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault">
										1.信用卡付款
									</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="2" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault">
										2.貨到付款
									</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="3" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault">
										3.ATM轉帳
									</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="4" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault">
										4.超商付款
									</label>
								</div>
							</div> -->
								

							<!-- <div class="form-group col-md-6">
								<label>提供的付款方式(請勾選)(單選)</label>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="1" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckChecked">1.信用卡付款</label>
								  </div>
								  <div class="form-check">
									<input class="form-check-input" type="checkbox" value="2" id="flexCheckChecked" checked>
									<label class="form-check-label" for="flexCheckChecked">2.貨到付款</label>
								  </div>
								  <div class="form-check">
									<input class="form-check-input" type="checkbox" value="3" id="flexCheckChecked" checked>
									<label class="form-check-label" for="flexCheckChecked">3.ATM轉帳</label>
								  </div>
								  <div class="form-check">
									<input class="form-check-input" type="checkbox" value="4" id="flexCheckChecked" checked>
									<label class="form-check-label" for="flexCheckChecked">4.超商付款</label>
								  </div>

							</div> -->

							<div class="form-group col-md-12">
								<label>地址*</label> <input required="required class="
									form-control" name="busAddress" type="text"
									value="${BusUsing.address}"></input>
							</div>
							<div class="form-group col-md-12">
								<label>簡介</label>
								<textarea class="form-control" name="busIntro"
									placeholder="想說什麼呢~~" style="height: 130px;" rows="3"
									value="${BusUsing.intro}"></textarea>
							</div>
							<div class="form-group col-md-12">
								<label>FB粉絲專頁網址</label> <input class="form-control" name="busFB"
									type="text" value="${BusUsing.fb}"}"></input>
							</div>
							<div class="form-group col-md-12">
								<label>IG粉絲團網址</label> <input class="form-control" name="busIG"
									type="text" value="${BusUsing.ig}"></input>
							</div>
							<div class="form-group col-md-12">
								<label>官方網站</label> <input class="form-control"
									name="busWebsite" type="text" value="${BusUsing.website}"></input>
							</div>
							<div class="form-group col-md-12">
								<label>現在的密碼*(如需修改任何資料，需輸入現在的密碼，以做確認)</label> <input
									class="form-control" name="old_BusPwd" type="password"></input>
							</div>
							<div class="col-md-12">
								<button type="submit"
									class="btn btn-fill-out submit font-weight-bold" name="action"
									value="busdataupdate" style="border-color:green;border-width:1px;border-style:solid;">儲存修改</button>
								<!-- 必填欄位尚未被填寫時↓ -->
								<span style="color: red;">${warningDataBusMsg}</span>
								<!-- 密碼寫錯時↓ -->
								<span style="color: red;">${errBusPWMsg}</span>
								<!-- 會員資料設定修改成功時↓ -->
								<span style="color: red;">${DataupdateSuccessBusMsg1}</span>
							</div>

							<!-- ===============================修改密碼=============================== -->
							<hr class="my-5" />

							<div class="row" style="max-width: 920px">

								<div class="col-md">
									<article class="box mb-3 bg-light">
										<a class="btn float-end btn-light btn-sm rounded font-md"
											href="<%=request.getContextPath()%>/nest-backend/busChangePassword.jsp"
											name="action" vlaue="changepw">變更密碼</a>
										<h5>變更密碼</h5>
										<small class="text-muted d-block" style="width: 70%">更改密碼</small>
									</article>
								</div>
								<!-- ===============================修改密碼=============================== -->

								<!-- ===============================刪除帳號=============================== -->

								<div class="col-md">
									<article class="box mb-3 bg-light">
										<a class="btn float-end btn-light rounded btn-sm font-md"
											href="<%=request.getContextPath()%>/nest-backend/busAccountDelete.jsp"
											name="action" vlaue="deleteaccount">刪除帳號</a>
										<h5>刪除帳號</h5>
										<small class="text-muted d-block" style="width: 70%">請注意!一旦您選擇刪除帳號後，將無法再回復帳號!</small>
									</article>
								</div>
								<!-- ===============================刪除帳號=============================== -->
							</div>
						</div>
					</form>

				</div>
			</div>

			<!-- ===============================帳戶資料=============================== -->
		</section>
		<!-- content-main end// -->
		<jsp:include page="/views/sellerFooter.jsp" />
	</main>
	<!-- 此頁<main>結束 -->
	<!-- <script>
		// 顯示付款方式勾選選擇的程式碼
		function checkResult() {
            var list = [];
            var chked = document.querySelectorAll("[type=checkbox]:checked");
            for (var i = 0; i < chked.length; i++) {
                list.push(chked[i].value);
            }
            // document.querySelector(".result").innerHTML = list.join(' ') || "none";
			document.querySelector(".result").innerHTML = list || "none";
        }

		
		// 控制"提供的付款方式(請選擇)"顯示的程式碼(單選)
		// $('.dropdown-menu a').click(function() {
		// 	$('#selected').text($(this).text());
		// });
	</script> -->
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
