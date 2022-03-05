<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
<head>
<meta charset="utf-8">
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
	href="/assets/imgs/theme/favicon.svg" />
<!-- Template CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>
<body>
	<jsp:include page="/views/userHeader.jsp" />
	<main class="main pages">
		<div class="page-header breadcrumb-wrap">
			<div class="container">
				<div class="breadcrumb">
					<a href="<%=request.getContextPath()%>/views/index.jsp"
						rel="nofollow"><i class="fi-rs-home mr-5"></i>首頁</a> <a
						href="<%=request.getContextPath()%>/nest-frontend/orderOverview.jsp"><span></span>
						會員中心</a>
					<!--
                        <span></span> Pages <span></span> My Account
                        -->
				</div>
			</div>
		</div>
		<div class="page-content pt-150 pb-150">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 m-auto">
						<div class="row">
							<div class="col-md-3">
								<div class="dashboard-menu">
									<ul class="nav flex-column" role="tablist">
										<!--
                                            <li class="nav-item">
                                                <a class="nav-link active" id="dashboard-tab" data-bs-toggle="tab" href="#dashboard" role="tab" aria-controls="dashboard" aria-selected="false"><i class="fi-rs-settings-sliders mr-10"></i>會員中心</a>
                                            </li>
                                            -->
										<li class="nav-item"><a class="nav-link" id="orders-tab"
											data-bs-toggle="tab"
											href="<%=request.getContextPath()%>/order/orderOverview.jsp"
											role="tab" aria-controls="orders" aria-selected="false"><i
												class="fi-rs-shopping-bag mr-10"></i>訂單管理</a></li>
										<!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
										<li class="nav-item"><a class="nav-link" id="address-tab"
											data-bs-toggle="tab"
											href="<%=request.getContextPath()%>/views/articleManagement.jsp"
											role="tab" aria-controls="address" aria-selected="true"><i
												class="fi-rs-marker mr-10"></i>文章管理</a></li>

										<li class="nav-item"><a class="nav-link active"
											id="account-detail-tab" data-bs-toggle="tab"
											href="<%=request.getContextPath()%>/views/accountSetting.jsp"
											role="tab" aria-controls="account-detail"
											aria-selected="true"><i class="fi-rs-user mr-10"></i>帳戶設定</a>
										</li>
										<li class="nav-item"><a class="nav-link"
											href="page-login.html"><i class="fi-rs-sign-out mr-10"></i>登出</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
								<div class="tab-content account dashboard-content pl-50">


									<div class="tab-pane fade active show" id="account-detail"
										role="tabpanel" aria-labelledby="account-detail-tab">


										<div class="card">
											<div class="card-header">
												<h5>變更密碼</h5>
											</div>
											<div class="card-body">
												<form method="post" name="enq"
													action="<%=request.getContextPath()%>/nest-fronted/MembersDataUpdate">
													<div class="form-group col-md-6">
														<label>使用者ID：<span name="membersID">${MemberUsing.membersID}</span></label>
													</div>
													<div class="row">

														<div class="form-group col-md-6">
															<span name="membersName">${MemberUsing.membersname}您好，您現正進行密碼變更作業</span>>
														</div>
													</div>
													<div class="form-group col-md-12">
														<label>現在的密碼</label> <input required=""
															class="form-control" name="membersPassword"
															type="password" />
													</div>

													<br> <br>
													<div class="form-group col-md-12">
														<label>新的密碼</label> <input class="form-control"
															name="newnMembersPassword" type="password" />
													</div>
													<div class="form-group col-md-12">
														<label>確認密碼</label> <input class="form-control"
															name="newMembersPasswordRp" type="password" />
													</div>
													<div class="col-md-12">
														<button type="submit"
															class="btn btn-fill-out submit font-weight-bold"
															name="action" value="changepw">確認修改密碼</button>
														<span style="color: red;">${warningMembersPWMsg}</span>
														<span style="color: red;">${warningMembersPWMsg1}</span>
														<span style="color: red;">${MembersPWupdateMsg}</span>
													</div>
												</form>
											</div>

											<!-- ===============================修改密碼=============================== -->

										</div>





									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/views/footer.jsp" />
	<!--
        Preloader Start
        <div id="preloader-active">
            <div class="preloader d-flex align-items-center justify-content-center">
                <div class="preloader-inner position-relative">
                    <div class="text-center">
                        <img src="assets/imgs/theme/loading.gif" alt="" />
                    </div>
                </div>
            </div>
        </div>
        -->

	<!-- Template  JS -->
	<script src="/assets/js/main_frontend.js"></script>
	<script src="/assets/js/shop.js"></script>

</body>
</html>