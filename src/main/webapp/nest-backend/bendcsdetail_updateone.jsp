<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.csdetail.model.*"%>
<%@ page import="java.util.List"%>
<%-- <%@ page import="java.util.*"%> --%>

<%
System.out.println(request.getAttribute("csDetailVO_z"));
Object obj = request.getAttribute("csDetailVO_z");
CsDetailVO csDetailVO = null;
if (obj != null) {
	csDetailVO = (CsDetailVO) obj; //CsDetailServlet.java (Concroller) 存入req的csDetailBean物件 (包括幫忙取出的csDetailBean, 也包括輸入資料錯誤時的csDetailBean物件)
}else {
	//only測試用，直接開bendcsdetail_updateone.jsp的情況。
// 	csDetailVO = new CsDetailVO();
// 	csDetailVO.setCaseid(7575);
// 	csDetailVO.setMemberid(567);
// 	csDetailVO.setBusid(84);
// 	csDetailVO.setMerid(1111111);
// 	csDetailVO.setOrderid(1111111);
// 	csDetailVO.setCasetime(java.sql.Date.valueOf("2022-03-04"));
// 	csDetailVO.setFeedback("測試用留言_測試用留言_測試用留言_測試用留言_測試用留言");
// 	csDetailVO.setReplystatus(1);
}


%>

<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MembersService" />

<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>客服明細-Petting</title>
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
<%-- <jsp:include page="/views/sellerHeader_doing.jsp"/> --%>
<!-- Template CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_frontend.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main_backend.css" type="text/css" />
</head>

<body>
	<div class="screen-overlay"></div>
	<jsp:include page="/views/sellerAside.jsp" />
	<main class="main-wrap">
		<%-- 		<jsp:include page="/views/sellerHeader.jsp" /> --%>
		<jsp:include page="/views/sellerHeader_2.jsp" />
		<section class="content-main">
			<!-- 在<section>content-main start開始寫此後台頁內容 -->
			<div class="row">
				<div class="col-9">
					<div class="content-header">
						<h2 class="content-title">
							會員【${membersSvc.select(csDetailVO_z.memberid).name}】的客服單
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
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/nest-backend/CsServletOnlyBus.do">
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
													<label class="mb-2 form-check form-check-inline" style="width: 45%;"> 
													<input class="form-check-input" name="reply_status" type="radio"
														value="1" <%=csDetailVO.getReplystatus() == 1 ? "checked" : ""  %>/> 
													<span class="col-form-label"> 1_待處理 </span></label>
													<label class="mb-2 form-check form-check-inline" style="width: 45%;">
													<input class="form-check-input" name="reply_status" type="radio"
														value="2" <%=csDetailVO.getReplystatus() == 2 ? "checked" : ""  %>/>
													<span class="col-form-label"> 2_處理中 </span></label>
													<label class="mb-2 form-check form-check-inline" style="width: 45%;">
													<input class="form-check-input" name="reply_status" type="radio"
														value="3" <%=csDetailVO.getReplystatus() == 3 ? "checked" : ""  %>/>
													<span class="col-form-label"> 3_已完成 </span></label>
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">詢問-商品編號</label>
												<div class="col-lg-9">
												
													<label class="col-form-label">
													<c:choose>
														<c:when test="${csDetailVO_z.merid != 1111111 && csDetailVO_z.merid != 0}">
															<a href="<%=request.getContextPath()%>/product/ProductJump?merid=${csDetailVO_z.merid}&action=product_jump">
															商品編號 ${csDetailVO_z.merid}
															</a>
														</c:when>
														<c:otherwise>
															此筆沒有詢問
														</c:otherwise>
													</c:choose>
													</label>
													<!-- <input type="text" class="form-control" placeholder="Type here" /> -->
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">詢問-訂單編號</label>
												<div class="col-lg-9">
													<label class="col-form-label"><%=(csDetailVO.getOrderid() != 1111111 && csDetailVO.getOrderid() != 0)?csDetailVO.getOrderid():"此筆沒有詢問"%></label>
													<!-- <input type="text" class="form-control" placeholder="Type here" /> -->
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">回覆內容</label>
												<div class="col-lg-9">
													<textarea name="reply_content" class="form-control" placeholder="輸入回應..." rows="3"><%=(csDetailVO.getReplycontent()==null)? "幫您確認，晚點回覆您，請稍等!" : csDetailVO.getReplycontent()%></textarea>
													<%-- <input type="hidden" name="reply_content_q" value="<%=(csDetailVO==null)? "幫您確認，晚點回覆您，請稍等!~" : csDetailVO.getReplycontent()%>"/> --%>
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<div class="row mb-4">
												<label class="col-lg-3 col-form-label">回覆時間</label>
												<div class="col-lg-4">
													<input type="text" name="reply_time" class="form-control" id="today" placeholder="YYYY-MM-DD" value="" />
												</div>
												<!-- col.// -->
											</div>
											<!-- row.// -->
											<!-- row.// -->
											<br />
											<button class="btn btn-primary" type="submit">完成回應更新</button>
											<input type="hidden" name="action" value="update">
											<input type="hidden" name="case_id" value="<%=csDetailVO.getCaseid()%>">
											<input type="hidden" name="member_id" value="<%=csDetailVO.getMemberid()%>">
											<input type="hidden" name="bus_id" value="<%=csDetailVO.getBusid()%>">
											<input type="hidden" name="mer_id" value="<%=csDetailVO.getMerid()%>">
											<input type="hidden" name="order_id" value="<%=csDetailVO.getOrderid()%>">
											<input type="hidden" name="case_time" value="<%=csDetailVO.getCasetime()%>">
											<input type="hidden" name="feedback" value="<%=csDetailVO.getFeedback()%>">
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
		<jsp:include page="/views/sellerFooter.jsp" />
	</main>
	<!-- 此頁<main>結束 -->
	
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/perfect-scrollbar.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/vendors/jquery.fullscreen.min.js"></script>
	<!-- Main Script -->
	<script src="<%=request.getContextPath()%>/assets/js/main_backend.js?v=1.1" type="text/javascript"></script>
</body>

	<!-- 以下為日期設定 -->
<%-- <%
//   java.sql.Date replyTime = null;
//   try {
// 	  replyTime = csDetailVO.getReplytime();
//    } catch (Exception e) {
// 	  replyTime = new java.sql.Date(System.currentTimeMillis());
//    }
%> --%>

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
		   value: '<%=csDetailVO.getReplytime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>
</html>
