<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.csdetail.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.bus.model.*"%>

<%
// 已經透過csdetail controller 從 shop_mainpage 接收 busid並在 controller中 setAttribute存入busVO
// 但用【${busVO_from_ShopMainpage.name}】的EL是直接取Attribute key name。

// 其實不用BusVO busVO =，這行。
BusVO busVO = (BusVO) request.getAttribute("busVO_from_ShopMainpage"); // attribute 來自CsDetailServlet.java

// 此行測試
request.setAttribute("busVO_thisPage", busVO);

// 您將傳訊息給 - 【${busVO_from_ShopMainpage.name}】
// 等同下面
// 您將傳訊息給 - 【< %=busVO.getName()% >】


// 例如<jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />
// 這裡的memberSvc 也是Attribute key name。
%>


<%
MembersService membersSvc = new MembersService();

//(測試用)如果沒登入，默認顯示memberid = 1的個人用戶;
MembersVO membersVO = membersSvc.select(2);
if(session.getAttribute("MemberUsing")!=null){
	membersVO = ((MembersVO)session.getAttribute("MemberUsing"));
}

// 可以直接用EL取用session內的 attribute name ${MemberUsing}
// 這裡是以防無登入者的情況，所以多寫一層用membersVO擷取MemberUsing

// membersVO存成Attribute，如此可用EL語法存取
request.setAttribute("membersVO_thisPage", membersVO);
%>

<%
// 如果從訂單頁面傳過來(但有訂單資訊)
%>

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>郵件商家-Petting</title>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
</head>

<body>
	<jsp:include page="/views/userHeader.jsp" />
	<!--End userHeader-->
	<main class="main pages">
		<div class="page-content pt-50">


			<div class="container">
				<div class="row">
					<div class="col-xl-10 col-lg-12 m-auto">
						<section class="mb-50">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">下列項目必須填寫，請您寫好再送出:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
							<div class="row">
								<div class="col-xl-8">
									<div class="contact-from-area padding-20-row-col">
										<h5 class="text-brand mb-10">E-mail聯絡商家</h5>
										<h2 class="mb-10">寄送您的詢問給 ${busVO_shopMpage.name}</h2>
										<p class="text-muted mb-30 font-sm">將以郵件通知商家關於您的需求。</p>
										<form method="post" action="<%=request.getContextPath()%>/nest-backend/CsMailServlet" class="contact-form-style mt-30" id="contact-form" >
											<div class="row">
												<div class="col-lg-6 col-md-6">
													<div class="text ml-20">
														會員姓名
													</div>
													<div class="input-style mb-20">
														<input type="text" readonly name="memberName" value="${membersVO_thisPage.name}" placeholder="會員名稱" style="background-color: #e9ecef;"/>
													</div>
												</div>
												<div class="col-lg-6 col-md-6">
													<div class="text ml-20">
														今日日期
													</div>
													<div class="input-style mb-20">
														<input type="text" readonly name="case_time" id="today" placeholder="回報時間" style="background-color: #e9ecef;"/>
													</div>
												</div>
<!-- 												<div class="col-lg-6 col-md-6"> -->
<!-- 													<div class="input-style mb-20"> -->
<!-- 														<input name="subject" placeholder="標題" type="text" /> -->
<!-- 													</div> -->
<!-- 												</div> -->
												<div class="col-lg-12 col-md-12">
													<div class="textarea-style mb-30">
														<textarea name="mailContent" placeholder="您的信件內容(必填) *"></textarea>
													</div>
													<button type="submit" class="submit submit-auto-width">傳送郵件</button>
													<input type="hidden" placeholder="電子信箱 emaill address" name="BusEmail"/>
													<input type="hidden" name="action" value="sendmail">
													<input type="hidden" name="member_id" value="${membersVO_thisPage.memberid}">
													<input type="hidden" name="bus_id" value="${busVO_shopMpage.busid}">
												</div>
											</div>
										</form>
										<p class="form-messege"></p>
									</div>
								</div>
								
								<div class="col-lg-4 pl-50 d-lg-block d-none">
									<img class="border-radius-15 mt-50" src="assets/imgs/page/contact-2.png" alt="" />
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/views/footer.jsp" />

	<!-- Preloader Start -->
	<!-- Vendor JS-->
	<script src="<%=request.getContextPath()%>/assets/js/vendors/modernizr-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/slick.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.syotimer.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/wow.js"></script>
	<!--slider-range.js, jquery-ui.js , never appear at sametime-->
	<script src="<%=request.getContextPath()%>/assets/js/plugins/slider-range.js"></script>
	<!-- index-3.html, index-4, shop-*.html，板模的這幾頁有用到jquery-ui.js -->
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script>
	<!-- blog-post-fullwidth.html, shop-*.html，板模的這幾頁有用到perfect-scrollbar.js -->
	<script src="<%=request.getContextPath()%>/assets/js/plugins/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/magnific-popup.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/waypoints.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/counterup.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.countdown.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/images-loaded.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/isotope.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/scrollup.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.vticker-min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.theia.sticky.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/jquery.elevatezoom.js"></script>

	<!-- Invoice page's JS -->
	<!-- <script src="assets/js/invoice/jspdf.min.js"></script> -->
	<!-- <script src="assets/js/invoice/invoice.js"></script> -->

	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
</body>

	<!-- 以下為日期設定 -->

<% 
  java.sql.Date case_time = new java.sql.Date(System.currentTimeMillis());

//   out.println("case_time is:" + case_time);
%>

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
		   value: '<%=case_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>

</html>

