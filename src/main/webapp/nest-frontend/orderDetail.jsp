﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>

<%
	MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");
  List<OrderDetailVO> list = (List<OrderDetailVO>)request.getAttribute("orderDetailList");
  pageContext.setAttribute("list",list);
%>

<jsp:useBean id="orderSvc" scope="page" class="com.order.model.OrderService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
    <head>
        <meta charset="utf-8" />
        <title>Petting 會員中心</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <!-- Template CSS -->
        <link rel="stylesheet" href="assets/css/plugins/animate.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
    </head>

    <body>
		<jsp:include page="/views/userHeader.jsp"/>
        <main class="main pages">
            <jsp:include page="/views/userMainPage-header.jsp" />
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
                                            <li class="nav-item">
                                                <a class="nav-link active" id="orders-tab" data-bs-toggle="tab" href="accountCenter.jsp" role="tab" aria-controls="orders" aria-selected="true"><i class="fi-rs-shopping-bag mr-10"></i>訂單管理</a>
                                            </li>
                                            <!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
<!--                                             <li class="nav-item"> -->
<!--                                                 <a class="nav-link" id="address-tab" data-bs-toggle="tab" href="#address" role="tab" aria-controls="address" aria-selected="true"><i class="fi-rs-marker mr-10"></i>文章管理</a> -->
<!--                                             </li> -->

                                            <li class="nav-item">
                                                <a class="nav-link" id="account-detail-tab" data-bs-toggle="tab" href="#account-detail" role="tab" aria-controls="account-detail" aria-selected="true"><i class="fi-rs-user mr-10"></i>帳戶設定</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<%=request.getContextPath()%>/members/MembersLogout?action=logout"><i class="fi-rs-sign-out mr-10"></i>登出</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="tab-content account dashboard-content pl-50">
                                        <!--
                                        <div class="tab-pane fade active show" id="dashboard" role="tabpanel" aria-labelledby="dashboard-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="mb-0">Hello 會員名字!</h3>
                                                </div>
                                                <div class="card-body">
                                                    <p>
                                                        在會員中心您可以 &amp; 進行 <a href="#">訂單管理</a>,<br />
                                                        <a href="#">文章管理</a> 及 <a href="#">帳戶設定</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        -->
                                        <div class="tab-pane fade active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="mb-0">訂單詳細</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
<!--                                                                     <th>訂單編號</th> -->
<!--                                                                     <th>商品編號</th> -->
                                                                    <th>商品名稱</th>
                                                                    <th>數量</th>
                                                                    <th>單價</th>
                                                                    
                                                                </tr>
                                                            </thead>
															
                                                            <tbody>
                                                           		<%@ include file="page1.file" %>
																<c:forEach var="orderDetailVO" items="${list}"
																	begin="<%=pageIndex%>"
																	end="<%=pageIndex+rowsPerPage-1%>">
															
																<tr>
<%-- 																	<td>${orderDetailVO.orderId}</td> --%>
<%-- 																	<td>${orderDetailVO.merId}</td> --%>
																	<td><a href="<%=request.getContextPath()%>/product/ProductJump?merid=${orderDetailVO.merId }&action=product_jump">
																	${productSvc.getOneProduct(orderDetailVO.merId).name}</a></td>
																	<td>${orderDetailVO.qty}</td>
																	<td>$${orderDetailVO.unitPrice}</td>
																	<c:if test="${orderSvc.getOneByOrderId(orderDetailVO.orderId).orderStatus == 3}">
																		<td>
																		<form method= "POST" ACTION= "orderDetail.do">
																			<button class="btn-small d-block" type="submit">評價留言</button>
																			<input type="hidden" name="merId" value="${orderDetailVO.merId}">
																			<input type="hidden" name="action" value="ranking_Ord_Detail">
																		</form>
																		</td>
																	</c:if>
																</tr>
															
															</c:forEach>
															<%@ include file="page2.file" %>
                                                            </tbody>
                                                        </table>
                                                        <form method= "POST" ACTION= "orderDetail.do">
															<button class="btn-small d-block" type="submit" style="width: 110px; height: 50px;
																													padding: 5px 20px;	font-size: 14px;">聯繫客服</button>
															<input type="hidden" name="orderId" value="${list.get(0).orderId}">
															<%-- <input type="hidden" name="orderId" value="${orderDetailVO.orderId}"> --%>
															<input type="hidden" name="action" value="contact_Ord_Detail">
														</form>
                                                       	<a href="accountCenter.jsp">回上頁</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <!-- ===============================帳戶資料=============================== -->
                                        <div class="tab-pane fade" id="account-detail" role="tabpanel" aria-labelledby="account-detail-tab">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>帳戶設定</h5>
                                                </div>
                                                <div class="card-body">
                                                    <form method="post" name="enq"
                                                        action="<%=request.getContextPath()%>/members/MembersDataUpdate">
                                                        <div class="form-group col-md-6">
                                                            <label>使用者ID：</label><span>${MemberUsing.memberid}</span>
                                                            <input type="hidden" name="membersMemberid" value="${MemberUsing.memberid}">
                                                            <input type="hidden" name="membersDate" value="<%=membersVO.getDate()%>">
                                                        </div>
                                                        <div class="row">
    
                                                            <div class="form-group col-md-6">
                                                                <label>姓名 *<span class="required"></span></label>
                                                                <!-- <input required="" class="form-control" name="name" type="text" /> -->
                                                                <input class="form-control" name="membersName" required="required"
                                                                    type="text" value="${MemberUsing.name}">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>暱稱</label> <input class="form-control"
                                                                    name="membersNickname" type="text" value="${MemberUsing.nickname}"></input>
                                                            </div>
                                                            <!-- <div class="form-group col-md-6">
                                                                <label>新密碼 *</label> <input class="form-control"
                                                                    required name="NEWmembersPassword" type="password" placeholder="若新密碼不須修改，請輸入舊密碼"></input>
                                                            </div> -->
                                                            <div class="form-group col-md-12">
                                                                <label>Email *(本欄位不可更改)</label>
                                                                <!-- input標籤設為disabled="disabled"為不可編輯的意思 -->
                                                                <input required="required" class="form-control" readonly="readonly"
                                                                name="membersEmail" type="email" value="${MemberUsing.email}" style="background-color: #e9ecef;"></input>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>手機 *<span class="required"></span></label> <input
                                                                required="required" class="form-control" name="membersMobile" value="${MemberUsing.mobile}"></input>
                                                                <!-- 當手機號碼格式不合格時↓ -->
                                                                <span style="color: red;">${warningDataMembersMsg1}</span>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>電話</label> <input class="form-control"
                                                                name="membersPhone" type="text" value="${MemberUsing.phone}"></input>
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>地址</label> <input class="form-control"
                                                                name="membersAddress" type="text" value="${MemberUsing.address}"></input>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label>簡介</label> <textarea class="form-control"
                                                                name="membersIntro" placeholder="想說什麼呢~~" style="height: 130px;" rows="3">${MemberUsing.intro}</textarea>
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label>現在的密碼*(如需修改任何資料，需輸入現在的密碼，以做確認)</label> <input class="form-control"
                                                                    name="old_MembersPwd" type="password"></input>
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
                                                                </div> ${MemberUsing.date}-->
                                                            
                                                            
                                                            <div class="col-md-12">
                                                                <button type="submit"
                                                                    class="btn btn-fill-out submit font-weight-bold"
                                                                    name="action" value="membersdataupdate">儲存修改</button>
                                                                <!-- 必填欄位尚未被填寫時↓ -->
                                                                <span style="color: red;">${warningDataMembersMsg}</span>
                                                                <!-- 密碼寫錯時↓ -->
                                                                <span style="color: red;">${errMembersPWMsg}</span>
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
    
                                                                <div class="col-md">
                                                                    <article class="box mb-3 bg-light">
                                                                        <a class="btn float-end btn-light rounded btn-sm font-md" href="<%=request.getContextPath()%>/nest-frontend/membersAccountDelete.jsp">刪除帳號</a>
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
                                        </div>
                                        <!-- ===============================帳戶資料=============================== -->

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
		<jsp:include page="/views/footer.jsp"/>
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
        <!-- Vendor JS-->
        <script src="assets/js/vendor/modernizr-3.6.0.min.js"></script>
        <script src="assets/js/vendor/jquery-3.6.0.min.js"></script>
        <script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
        <script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
        <script src="assets/js/plugins/slick.js"></script>
        <script src="assets/js/plugins/jquery.syotimer.min.js"></script>
        <script src="assets/js/plugins/wow.js"></script>
        <script src="assets/js/plugins/perfect-scrollbar.js"></script>
        <script src="assets/js/plugins/magnific-popup.js"></script>
        <script src="assets/js/plugins/select2.min.js"></script>
        <script src="assets/js/plugins/waypoints.js"></script>
        <script src="assets/js/plugins/counterup.js"></script>
        <script src="assets/js/plugins/jquery.countdown.min.js"></script>
        <script src="assets/js/plugins/images-loaded.js"></script>
        <script src="assets/js/plugins/isotope.js"></script>
        <script src="assets/js/plugins/scrollup.js"></script>
        <script src="assets/js/plugins/jquery.vticker-min.js"></script>
        <script src="assets/js/plugins/jquery.theia.sticky.js"></script>
        <script src="assets/js/plugins/jquery.elevatezoom.js"></script>
        <!-- Template  JS -->
        <script src="assets/js/main.js"></script>
        <script src="assets/js/shop.js"></script>
    </body>
</html>
