<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>
<%@page buffer="8192kb" autoFlush="true" %>

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
											href="<%=request.getContextPath()%>/nest-frontend/accountSetting.jsp"
											role="tab" aria-controls="account-detail"
											aria-selected="true"><i class="fi-rs-user mr-10"></i>帳戶設定</a>
										</li>
										<li class="nav-item"><a class="nav-link"
											href="../nest-frontend/HomePage.jsp"><i
												class="fi-rs-sign-out mr-10"></i>登出</a></li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
								<div class="tab-content account dashboard-content pl-50">

									<!-- ===============================帳戶設定=============================== -->
									<div class="tab-pane fade active show" id="account-detail"
										role="tabpanel" aria-labelledby="account-detail-tab">

										<div class="card">
											<div class="card-header">
												<h5>帳戶設定</h5>
											</div>
											<div class="card-body">
												<form method="post" name="enq"
													action="<%=request.getContextPath()%>/members/MembersDataUpdate">
													<div class="form-group col-md-6">
														<label>使用者ID：</label><span>${MemberUsing.memberid}</span>
													</div>
													<div class="row">

														<div class="form-group col-md-6">
															<label>姓名<span class="required">*</span></label>
															<!-- <input required="" class="form-control" name="name" type="text" /> -->
															<input class="form-control" name="membersName"
																type="text" value="${MemberUsing.name}">
														</div>
														<div class="form-group col-md-6">
															<label>暱稱</label> <input class="form-control"
																name="membersNickname" type="text" value="${MemberUsing.nickname}"></input>
														</div>
														<div class="form-group col-md-12">
															<label>Email(帳號)*(本欄位不可更改)</label>
															<!-- input標籤設為disabled="disabled"為不可編輯的意思 -->
															<input required="" class="form-control"
																name="membersEmail" type="email" disabled="disabled" value="${MemberUsing.email}"></input>
														</div>
														<div class="form-group col-md-6">
															<label>手機<span class="required">*</span></label> <input
																required="" class="form-control" name="membersMobile" value="${MemberUsing.mobile}"></input>
															<!-- 當手機號碼格式不合格時↓ -->
															<span style="color: red;">${warningDataMembersMsg1}</span>
														</div>
														<div class="form-group col-md-6">
															<label>電話</label> <input class="form-control"
																name="membersPhone" type="text" value="${MemberUsing.phone}"></input>
														</div>
														<div class="form-group col-md-12">
															<label>地址</label> <input class="form-control"
																name="membersAddress" type="text"></input>
														</div>
														<div class="form-group col-md-6">
															<label>簡介</label> <input class="form-control"
																name="membersIntro" type="textarea" />
														</div>
														<!-- <div class="form-group col-md-12">
																<label>現在的密碼*(如需修改資料，需輸入現在的密碼，以做確認)</label> <input
																	required="" class="form-control" name="membersPassword"
																	type="password" />
															</div> -->

														<!-- <hr>
															<h5>修改密碼</h5>
															<br> <br>
															<div class="form-group col-md-12">
																<label>新的密碼</label> <input class="form-control"
																	name="newnMembersPassword" type="password" />
															</div>
															<div class="form-group col-md-12">
																<label>確認密碼</label> <input class="form-control"
																	name="newMembersPasswordRp" type="password" />
															</div> -->
														<div class="col-md-12">
															<button type="submit"
																class="btn btn-fill-out submit font-weight-bold"
																name="action" value="membersdataupdate">儲存修改</button>
															<!-- 必填欄位尚未被填寫時↓ -->
															<span style="color: red;">${warningDataMembersMsg}</span>
															<!-- 會員資料設定修改成功時↓ -->
															<span style="color: red;">${DataupdateSuccessMembersMsg1}</span>
														</div>

														<!-- ===============================修改密碼=============================== -->
														<hr class="my-5" />

														<div class="row" style="max-width: 920px">

															<div class="col-md">
																<article class="box mb-3 bg-light">
																	<a
																		class="btn float-end btn-light btn-sm rounded font-md"
																		href="<%=request.getContextPath()%>/nest-frontend/membersChangePassword.jsp"
																		name="action" vlaue="changepw">變更密碼</a>
																	<h5>變更密碼</h5>
																	<small class="text-muted d-block" style="width: 70%">更改密碼</small>
																</article>
															</div>
															<!-- col.//
														   
															<!-- ===============================修改密碼=============================== -->

															<!-- ===============================刪除帳號=============================== -->

															<!-- <div class="col-md">
																<article class="box mb-3 bg-light">
																	<a class="btn float-end btn-light rounded btn-sm font-md" href="#" name="action" vlaue="deleteaccount">刪除帳號</a>
																	<h5>刪除帳號</h5>
																	<small class="text-muted d-block" style="width: 70%">請注意!一旦您選擇刪除帳號後，將無法再回復帳號!</small>
																</article>
															</div> -->
															<!-- ===============================刪除帳號=============================== -->
														</div>
												</form>


											</div>

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