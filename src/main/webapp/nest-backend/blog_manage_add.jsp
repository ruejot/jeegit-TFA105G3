<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.memfollow.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.memsavedart.model.*"%>

<%
MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");

MemBlogArtService artSvc = new MemBlogArtService();
//使用session取值再做所有使用者發文的查詢
List<MemBlogArtVO> list = artSvc.getAllByMember(membersVO.getMemberid());
Collections.reverse(list);
pageContext.setAttribute("list", list);

MemFollowService mfSvc = new MemFollowService();
List<MemFollowVO> list2 = mfSvc.getAllByFollowee(membersVO.getMemberid());
pageContext.setAttribute("list2",list2);

MembersService memSvc = new MembersService();
List<MembersVO> list3 = memSvc.selectAll();
pageContext.setAttribute("list3",list3);

MemSavedArtService msaSvc = new MemSavedArtService();
List<MemSavedArtVO> list4 = msaSvc.getArtBySavMem(membersVO.getMemberid());
pageContext.setAttribute("list4",list4);



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
	<jsp:include page="/views/userHeader.jsp" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/main_frontend.css" />
<link
	href="<%=request.getContextPath()%>/nest-backend/assets/css/main.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
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
										<li class="nav-item"><a class="nav-link"
											id="orders-tab" href="<%=request.getContextPath()%>/nest-backend/blog_manage_article.jsp"
											role="tab" ><i
												class="fi-rs-settings mr-10"></i>文章管理</a></li>
										<!--
                                            <li class="nav-item">
                                                <a class="nav-link" id="track-orders-tab" data-bs-toggle="tab" href="#track-orders" role="tab" aria-controls="track-orders" aria-selected="false"><i class="fi-rs-shopping-cart-check mr-10"></i>Track Your Order</a>
                                            </li>
                                             -->
										<li class="nav-item"><a class="nav-link active"
											id="dashboard-tab" href="<%=request.getContextPath()%>/nest-backend/blog_manage_add.jsp"
											role="tab"><i
												class="fi-rs-pencil mr-10"></i>發表新文</a></li>

										<li class="nav-item"><a class="nav-link" id="address-tab"
											href="<%=request.getContextPath()%>/nest-backend/blog_manage_saved.jsp" role="tab"
											><i
												class="fi-rs-heart mr-10"></i>收藏文章</a></li>

										<li class="nav-item"><a class="nav-link"
											id="account-detail-tab" 
											href="<%=request.getContextPath()%>/nest-backend/blog_manage_follow.jsp" role="tab"
											><i
												class="fi-rs-following mr-10"></i>關注中</a></li>
									</ul>
								</div>
							</div>
							<div class="col-md-9">
											<%@ include file="../nest-backend/blog_manage_page.file"%>
									
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


<!-- 								<div class="mb-4"> -->
<!-- 									<label class="form-label">#HashTags</label> <input type="text" -->
<!-- 										placeholder="Type here" class="form-control" /> -->
<!-- 								</div> -->
<!-- 								<span>熱門#HashTags</span> <a href="#" -->
<!-- 									class="font-xs hover-up mr-15"><i -->
<!-- 									class="font-xs material-icons md-close text-body"></i> 兔兔這麼可愛</a> <a -->
<!-- 									href="#" class="font-xs hover-up mr-15"><i -->
<!-- 									class="font-xs material-icons md-close text-body"></i> 喵主子</a> <a -->
<!-- 									href="#" class="font-xs hover-up mr-15"><i -->
<!-- 									class="font-xs material-icons md-close text-body"></i> 黃金獵犬</a> -->

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
												</div>
											</header>
											<div class="card-body">
												<%-- 												<%@ include file="../nest-backend/blog_manage_page.file"%> --%>
												<c:forEach var="memSavedArtVO" items="${list4}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<article class="itemlist">
														<div class="row align-items-center">
															<div class="col col-check flex-grow-0">
																<div class="form-check">
																	<input class="form-check-input checkbox-inner"
																		type="checkbox" name="artid[]"
																		value="${memSavedArtVO.savArtId}" />
																</div>
															</div>
															<div class="col-lg-4 col-sm-4 col-6 flex-grow-1 col-name">
																<a class="itemside"
																	href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memSavedArtVO.savArtId}">
																	<div class="left">
																		<img
																			src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memSavedArtVO.savArtId}"
																			class="img-sm img-thumbnail" alt="Item" />
																	</div>
																	<div class="info">
																		<h6 class="mb-0">${memBlogArtVO.title}</h6>
																	</div>
																</a>
															</div>
															<div class="col-lg-1 col-sm-2 col-6 col-date"
																style="width: 125px;">
																<span>${memBlogArtVO.posttime}</span>
															</div>
														<div class="col">
														</div>
														<!-- row .// -->
													</article>
												</c:forEach>
												
												<div class="pagination-area mt-30 mb-50">
													<nav aria-label="Page navigation example">
														<ul class="pagination justify-content-start">
															<%if (pageNumber>1) {%>
																<%for (int i=1; i<=pageNumber; i++){%>
																	<li class="page-item"><a class="page-link dot"
																			href="<%=request.getRequestURI()%>?whichPage=<%=whichPage=i%>">
																			<%=i%>
																		</a></li>
																	<%}%>
																		<%}%>
																			<li class="page-item">
																				<a class="page-link"
																					href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>"><i
																						class="material-icons md-chevron_right"></i></a>
																			</li>
														</ul>
													</nav>
												
												</div>
											</div>
										</div>
									</div>

									<!-- ===============================追蹤的用戶=============================== -->
									<div class="tab-pane fade" id="account-detail" role="tabpanel"
										aria-labelledby="account-detail-tab">
										<jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />
                                        
                <div class="content-header">
                    <h2 class="content-title">我追蹤的用戶</h2>
                    <div>
                        <a href="#" class="btn btn-primary"><i class="material-icons md-plus"></i> Create new</a>
                    </div>
                </div>
                <div class="card mb-4">
                    <header class="card-header">
                        <div class="row gx-3">
                            <div class="col-lg-4 col-md-6 me-auto">
                                <input type="text" placeholder="Search..." class="form-control" />
                            </div>
                            <div class="col-lg-2 col-6 col-md-3">
                                <select class="form-select">
                                    <option>Show 8</option>
                                    <option>Show 10</option>
                                    <option>Show 15</option>
                                    <option>Show all</option>
                                </select>
                            </div>
                            <div class="col-lg-2 col-6 col-md-3">
                                <select class="form-select">
                                    <option>Status: all</option>
                                    <option>Active only</option>
                                    <option>Disabled</option>
                                </select>
                            </div>
                        </div>
                    </header>
                    <!-- card-header end// -->
                    <div class="card-body">
                        <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-3">
                            <!-- col.// -->
                            <c:forEach var="memFollowVO" items="${list2}">
                            <div class="col">
                                <div class="card card-user">
                                    <div class="card-header">
                                        <img class="img-md img-avatar" src="assets/imgs/people/avatar-2.png" alt="User pic" />
                                    </div>
                                    <div class="card-body">
                                        <c:forEach var="membersVO" items="${list3}">
                                            <c:if test="${memFollowVO.memberId==membersVO.memberid}">
                                                <h5 class="card-title mt-50">${membersVO.nickname}</h5>
                                            </c:if>
                                        </c:forEach>
                                        <div class="card-text text-muted" style="margin-top:50px;">
                                            <p class="m-0">Seller ID: #${memFollowVO.memberId}</p>
<!--                                             <p>leslie@example.com</p> -->
                                            <a href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getMem_For_Display&memberId=${memFollowVO.memberId}" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-3.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">Leslie Alexander</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #478</p> -->
<!--                                             <p>leslie@example.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-4.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">Floyd Miles</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #171</p> -->
<!--                                             <p>fedor12@example.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-1.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">John Alexander</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #987</p> -->
<!--                                             <p>john@mymail.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-3.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">Albert Flores</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #478</p> -->
<!--                                             <p>leslie@example.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-4.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">Leslie Alexander</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #478</p> -->
<!--                                             <p>leslie@example.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!-- <!--                             </div> --> -->
<!--                             col.// -->
<!--                             <div class="col"> -->
<!--                                 <div class="card card-user"> -->
<!--                                     <div class="card-header"> -->
<!--                                         <img class="img-md img-avatar" src="assets/imgs/people/avatar-1.png" alt="User pic" /> -->
<!--                                     </div> -->
<!--                                     <div class="card-body"> -->
<!--                                         <h5 class="card-title mt-50">Marx Alberto</h5> -->
<!--                                         <div class="card-text text-muted"> -->
<!--                                             <p class="m-0">Seller ID: #478</p> -->
<!--                                             <p>leslie@example.com</p> -->
<!--                                             <a href="#" class="btn btn-sm btn-brand rounded font-sm mt-15">View details</a> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
                            <!-- col.// -->
                        </div>
                        <!-- row.// -->
                    </div>
                    <!-- card-body end// -->
                </div>
                <!-- card end// -->
                <div class="pagination-area mt-15 mb-50">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-start">
                            <li class="page-item active"><a class="page-link" href="#">01</a></li>
                            <li class="page-item"><a class="page-link" href="#">02</a></li>
                            <li class="page-item"><a class="page-link" href="#">03</a></li>
                            <li class="page-item"><a class="page-link dot" href="#">...</a></li>
                            <li class="page-item"><a class="page-link" href="#">16</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#"><i class="material-icons md-chevron_right"></i></a>
                            </li>
                        </ul>
                    </nav>
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
<!-- 	<script> -->
<!-- // 		$("input#product_title").blur(function(){ -->
<!-- // 			if ($("input#product_title").val().trim()=='') -->
<!-- // 				$("#errMsg").html("<div class='text-danger'>文章標題不得空白</div>") -->
<!-- // 			else -->
<!-- // 				$("#errMsg").html("") -->
<!-- // 		}) -->
<!-- 	</script> -->

<script>
	$("textarea#article_content").blur(function(){
		if ($("textarea#article_content").val().trim()=='')
			$("#errMsg2").html("<div class='text-danger'>文章內容不得空白</div>")
		else
			$("#errMsg2").html("")
	})
</script>
                            <script>
                                $('#form-check-input').change(function () {
                                    if ($('#form-check-input').is(':checked')) {
                                        $('.checkbox-inner').attr("checked", "checked")
                                        $('#batch_delete_btn').html("<button class='btn btn-danger' type='button' onclick='batch_delete()'>刪除所選文章</button>")
                                    }
                                    else {
                                        $('.checkbox-inner').removeAttr("checked")
                                        $('#batch_delete_btn').html("")
                                    }
                                })

                                $('.checkbox-inner').each((_, element) => {
                                    $(element).change(function () {
                                        if ($(element).is(':checked')) {
                                            $('#batch_delete_btn').html("<button class='btn btn-danger' type='button' onclick='batch_delete()'>刪除所選文章</button>")
                                        }
                                        else {
                                            $('#batch_delete_btn').html("")
                                        }
                                    })
                                });
                                function batch_delete() {
                                    if(confirm("確定要刪除所選取的文章嗎?")){
                                        console.log()
                                        $.ajax({
                                            type: "POST",
                                            url: "<%=request.getContextPath()%>/MemBlogArtServlet",
                                            success: function(){
                                                window.location.reload();
                                            },
                                            data: {
                                                action: "batch_delete",
                                                artid: $('.checkbox-inner:checked').map((_, event) => event.value).get().join(",")
                                            }
                                        });
                                    }
                                }

                            </script>


<script type="text/javascript">
	window.onload=function(){
		//如果location存有資料,跳到錨點
		var location_id='${location}';
		if(location_id!=''){
			document.getElementById(location_id).click();
		}
	
	}
	</script>


</html>
