<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");

MemBlogArtService artSvc = new MemBlogArtService();
//使用session取值再做所有使用者發文的查詢
List<MemBlogArtVO> list = artSvc.getAllByMember(membersVO.getMemberid());
Collections.reverse(list);
pageContext.setAttribute("list", list);

// Timestamp ts = new Timestamp(MemBlogArtVO.getPosttime());
// Date date = new Date(ts.getTime());
%>

<%
MemBlogArtVO memBlogArtVO = (MemBlogArtVO) request.getAttribute("memBlogArtVO");
%>


<!DOCTYPE html>
<html class="no-js" lang="zh-Hant">
<head>
<meta charset="utf-8" />
<title>文章管理 Petting</title>
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta property="og:title" content="" />
<meta property="og:type" content="" />
<meta property="og:url" content="" />
<meta property="og:image" content="" />
<!-- Template CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/slider-range.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/plugins/animate.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<link
	href="<%=request.getContextPath()%>/nest-backend/assets/css/main.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="/views/userHeader.jsp" />
	<main class="main pages">
		<div class="page-content pt-100 pb-100">
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
										<li class="nav-item"><a class="nav-link active"
											id="orders-tab" data-bs-toggle="tab" href="#orders"
											role="tab" aria-controls="orders" aria-selected="false"><i
												class="fi-rs-shopping-bag mr-10"></i>文章管理</a></li>
										<!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
										<li class="nav-item"><a class="nav-link"
											id="dashboard-tab" data-bs-toggle="tab" href="#dashboard"
											role="tab" aria-controls="dashboard" aria-selected="false"><i
												class="fi-rs-settings-sliders mr-10"></i>發表新文</a></li>

										<li class="nav-item"><a class="nav-link" id="address-tab"
											data-bs-toggle="tab" href="#address" role="tab"
											aria-controls="address" aria-selected="true"><i
												class="fi-rs-marker mr-10"></i>收藏文章</a></li>

										<li class="nav-item"><a class="nav-link"
											id="account-detail-tab" data-bs-toggle="tab"
											href="#account-detail" role="tab"
											aria-controls="account-detail" aria-selected="true"><i
												class="fi-rs-user mr-10"></i>關注中</a></li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
								<div class="tab-content account dashboard-content pl-50">
									<div class="tab-pane fade active show" id="orders"
										role="tabpanel" aria-labelledby="orders-tab">
										<div class="card">
											<div class="content-header">
												<div class="col-md-9">
													<h2 class="content-title card-title">文章管理</h2>
													<p>Lorem ipsum dolor sit amet.</p>
												</div>
												<!-- 												<div class="col-md-2"> -->
												<!-- 													<a -->
												<%-- 														href="<%=request.getContextPath()%>/nest-backend/blog_add-article.jsp" --%>
												<!-- 														class="btn btn-primary btn-sm rounded">新增文章</a> -->
												<!-- 												</div> -->
											</div>
											<header class="card-header">
												<div class="row align-items-center">
													<div class="col-2 col-check flex-grow-0">
														<div class="form-check ms-2">
															全選 <input id="form-check-input" class="form-check-input"
																type="checkbox" value="" />
														</div>
													</div>
													<div class="col-4"">
														<span id="batch_delete_btn" />
													</div>
													<div class="col-3"">
														<input type="date" value="02.05.2021" class="form-control" />
													</div>
													<div class="col-3">
														<select class="form-select">
															<option selected>文章狀態</option>
															<option>已發佈</option>
															<option>草稿</option>
															<option>全部狀態</option>
														</select>
													</div>
												</div>
											</header>
											<div class="card-body">
												<%@ include file="../nest-backend/blog_manage_page.file"%>
												<c:forEach var="memBlogArtVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<article class="itemlist">
														<div class="row align-items-center">
															<div class="col col-check flex-grow-0">
																<div class="form-check">
																	<input class="form-check-input checkbox-inner"
																		type="checkbox" name="artid[]"
																		value="${memBlogArtVO.artid}" />
																</div>
															</div>
															<div class="col-lg-4 col-sm-4 col-8 flex-grow-1 col-name">
																<a class="itemside"
																	href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">
																	<div class="left">
																		<img
																			src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}"
																			class="img-sm img-thumbnail" alt="Item" />
																	</div>
																	<div class="info">
																		<h6 class="mb-0">${memBlogArtVO.title}</h6>
																	</div>
																</a>
															</div>
															<div class="col-lg-1 col-sm-2 col-4 col-date"
																style="width: 125px;">
																<span>${memBlogArtVO.posttime}</span>
															</div>
															<div class="col-lg-2 col-sm-2 col-4 col-action text-end">

																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="edit">
																	<button type="submit"
																		class="btn btn-sm font-sm rounded btn-brand">
																		<i class="material-icons md-edit"></i>編輯
																	</button>

																</FORM>


																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="delete">
																	<button type="submit"
																		class="btn btn-sm font-sm btn-light rounded">
																		<i class="material-icons md-delete_forever"></i>刪除
																	</button>
																</FORM>
															</div>
														</div>
														<!-- row .// -->
													</article>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="tab-pane fade" id="dashboard" role="tabpanel"
										aria-labelledby="dashboard-tab">
										<div class="row">
				<div class="col-9">
					<div class="content-header">
						<h2 class="content-title">新增文章</h2>
					</div>
				</div>
				<form action="<%=request.getContextPath()%>/MemBlogArtServlet"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="merberusing"
                        value="<%=membersVO.getMemberid()%>">

					<div class="col-lg-10">
						<div class="card mb-7">
							<div class="card-body">
								<div class="mb-4">
									<label for="product_title" class="form-label">文章標題</label> 
									　　<input
										type="text" placeholder="Type here" class="form-control"
										id="product_title" name="title"
										value="<%=(memBlogArtVO == null) ? "" : memBlogArtVO.getTitle()%>"/>
								</div>
								<div id="errMsg"></div>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</div>
						<!-- card end// -->
						<div class="card mb-7">
							<div class="card-body">
								<div>
									<label for="article_content" class="form-label">文章內容</label>
									<textarea id="article_content" placeholder="Type here" class="form-control"
										rows="20" name="content" style="height: 300px"><%=(memBlogArtVO == null) ? "" : memBlogArtVO.getContent()%></textarea>
								</div>
								<div id="errMsg2"/></div>
							</div>
							<div class="card-body">


								<div class="mb-4">
									<label class="form-label">#HashTags</label> <input type="text"
										placeholder="Type here" class="form-control" />
								</div>
								<span>熱門#HashTags</span> <a href="#"
									class="font-xs hover-up mr-15"><i
									class="font-xs material-icons md-close text-body"></i> 兔兔這麼可愛</a> <a
									href="#" class="font-xs hover-up mr-15"><i
									class="font-xs material-icons md-close text-body"></i> 喵主子</a> <a
									href="#" class="font-xs hover-up mr-15"><i
									class="font-xs material-icons md-close text-body"></i> 黃金獵犬</a>

							</div>
						</div>
						<!-- card end// -->



						<div class="card mb-7">
							<div class="card-body">
								<div>


									<label class="form-label">上傳圖片</label> <input
										class="form-control" type="file" name="file" />
								</div>
							</div>
						</div>
						<div class="card mb-7"></div>

						<div>
							<button
								class="btn btn-light rounded font-sm mr-5 text-body hover-up">儲存草稿</button>
							<button type="submit" class="btn btn-md rounded font-sm hover-up">新增文章</button>
						</div>
					</div>
				</form>


				<!-- card end// -->
			</div>


			<!-- card end// -->
									</div>
									<div class="tab-pane fade" id="address" role="tabpanel"
										aria-labelledby="address-tab">
										<div class="card">
											<div class="content-header">
												<div class="col-md-10">
													<h2 class="content-title card-title">收藏文章</h2>
													<p>Lorem ipsum dolor sit amet.</p>
												</div>
											</div>
											<header class="card-header">
												<div class="row align-items-center">
													<div class="col-2 col-check flex-grow-0">
														<div class="form-check ms-2">
															全選 <input id="form-check-input" class="form-check-input"
																type="checkbox" value="" />
														</div>
													</div>
													<div class="col-4"">
														<span id="batch_delete_btn" />
													</div>
													<div class="col-3"">
														<input type="date" value="02.05.2021" class="form-control" />
													</div>
													<div class="col-3">
														<select class="form-select">
															<option selected>文章狀態</option>
															<option>已發佈</option>
															<option>草稿</option>
															<option>全部狀態</option>
														</select>
													</div>
												</div>
											</header>
											<div class="card-body">
												<%-- 												<%@ include file="../nest-backend/blog_manage_page.file"%> --%>
												<c:forEach var="memBlogArtVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<article class="itemlist">
														<div class="row align-items-center">
															<div class="col col-check flex-grow-0">
																<div class="form-check">
																	<input class="form-check-input checkbox-inner"
																		type="checkbox" name="artid[]"
																		value="${memBlogArtVO.artid}" />
																</div>
															</div>
															<div class="col-lg-4 col-sm-4 col-8 flex-grow-1 col-name">
																<a class="itemside"
																	href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">
																	<div class="left">
																		<img
																			src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}"
																			class="img-sm img-thumbnail" alt="Item" />
																	</div>
																	<div class="info">
																		<h6 class="mb-0">${memBlogArtVO.title}</h6>
																	</div>
																</a>
															</div>
															<div class="col-lg-1 col-sm-2 col-4 col-date"
																style="width: 125px;">
																<span>${memBlogArtVO.posttime}</span>
															</div>
															<div class="col-lg-2 col-sm-2 col-4 col-action text-end">

																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="edit">
																	<button type="submit"
																		class="btn btn-sm font-sm rounded btn-brand">
																		<i class="material-icons md-edit"></i>編輯
																	</button>

																</FORM>


																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="delete">
																	<button type="submit"
																		class="btn btn-sm font-sm btn-light rounded">
																		<i class="material-icons md-delete_forever"></i>刪除
																	</button>
																</FORM>
															</div>
														</div>
														<!-- row .// -->
													</article>
												</c:forEach>
											</div>
										</div>
									</div>

									<!-- ===============================追蹤的用戶=============================== -->
									<div class="tab-pane fade" id="account-detail" role="tabpanel"
										aria-labelledby="account-detail-tab">
										<div class="card">
											<div class="content-header">
												<div class="col-md-10">
													<h2 class="content-title card-title">關注中</h2>
													<p>Lorem ipsum dolor sit amet.</p>
												</div>
											</div>
											<header class="card-header">
												<div class="row align-items-center">
													<div class="col-2 col-check flex-grow-0">
														<div class="form-check ms-2">
															全選 <input id="form-check-input" class="form-check-input"
																type="checkbox" value="" />
														</div>
													</div>
													<div class="col-4"">
														<span id="batch_delete_btn" />
													</div>
													<div class="col-3"">
														<input type="date" value="02.05.2021" class="form-control" />
													</div>
													<div class="col-3">
														<select class="form-select">
															<option selected>文章狀態</option>
															<option>已發佈</option>
															<option>草稿</option>
															<option>全部狀態</option>
														</select>
													</div>
												</div>
											</header>
											<div class="card-body">
												<%-- 												<%@ include file="../nest-backend/blog_manage_page.file"%> --%>
												<c:forEach var="memBlogArtVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<article class="itemlist">
														<div class="row align-items-center">
															<div class="col col-check flex-grow-0">
																<div class="form-check">
																	<input class="form-check-input checkbox-inner"
																		type="checkbox" name="artid[]"
																		value="${memBlogArtVO.artid}" />
																</div>
															</div>
															<div class="col-lg-4 col-sm-4 col-8 flex-grow-1 col-name">
																<a class="itemside"
																	href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">
																	<div class="left">
																		<img
																			src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}"
																			class="img-sm img-thumbnail" alt="Item" />
																	</div>
																	<div class="info">
																		<h6 class="mb-0">${memBlogArtVO.title}</h6>
																	</div>
																</a>
															</div>
															<div class="col-lg-1 col-sm-2 col-4 col-date"
																style="width: 125px;">
																<span>${memBlogArtVO.posttime}</span>
															</div>
															<div class="col-lg-2 col-sm-2 col-4 col-action text-end">

																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="edit">
																	<button type="submit"
																		class="btn btn-sm font-sm rounded btn-brand">
																		<i class="material-icons md-edit"></i>編輯
																	</button>

																</FORM>


																<FORM METHOD="post"
																	ACTION="<%=request.getContextPath()%>/MemBlogArtServlet"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="artid"
																		value="${memBlogArtVO.artid}"> <input
																		type="hidden" name="action" value="delete">
																	<button type="submit"
																		class="btn btn-sm font-sm btn-light rounded">
																		<i class="material-icons md-delete_forever"></i>刪除
																	</button>
																</FORM>
															</div>
														</div>
														<!-- row .// -->
													</article>
												</c:forEach>
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
	<!-- Preloader Start -->
	<!-- Vendor JS-->
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/modernizr-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/jquery-migrate-3.3.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/vendors/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/slick.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.syotimer.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/plugins/wow.js"></script>
	<!--slider-range.js, jquery-ui.js , never appear at sametime-->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/slider-range.js"></script>
	<!-- index-3.html, index-4, shop-*.html，板模的這幾頁有用到jquery-ui.js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery-ui.js"></script>
	<!-- blog-post-fullwidth.html, shop-*.html，板模的這幾頁有用到perfect-scrollbar.js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/perfect-scrollbar.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/magnific-popup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/waypoints.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/counterup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.countdown.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/images-loaded.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/isotope.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/scrollup.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.vticker-min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.theia.sticky.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/plugins/jquery.elevatezoom.js"></script>

	<!-- Invoice page's JS -->
	<!-- <script src="assets/js/invoice/jspdf.min.js"></script> -->
	<!-- <script src="assets/js/invoice/invoice.js"></script> -->

	<!-- Template  JS -->
	<script src="<%=request.getContextPath()%>/assets/js/main_frontend.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
</body>
	<script>
		$("input#product_title").blur(function(){
			if ($("input#product_title").val().trim()=='')
				$("#errMsg").html("<div class='text-danger'>文章標題不得空白</div>")
			else
				$("#errMsg").html("")
		})
	</script>

<script>
	$("textarea#article_content").blur(function(){
		if ($("textarea#article_content").val().trim()=='')
			$("#errMsg2").html("<div class='text-danger'>文章內容不得空白</div>")
		else
			$("#errMsg2").html("")
	})
</script>
</html>
