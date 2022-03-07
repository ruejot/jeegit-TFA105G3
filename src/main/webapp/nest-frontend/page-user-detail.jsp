<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*" %>
<%@ page import="com.members.model.*" %>


<% 
MemBlogArtVO memBlogArtVO = (MemBlogArtVO) request.getAttribute("memBlogArtVO");
// MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");

MembersVO membersVO = (MembersVO) request.getAttribute("membersVO");

MembersVO membersVO2 = (MembersVO) session.getAttribute("MemberUsing");

MemBlogArtService artSvc = new MemBlogArtService(); 
List<MemBlogArtVO> list = artSvc.getAllByMember(membersVO.getMemberid());
pageContext.setAttribute("list",list);


%>


<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8" />
<title>FrontEnd板模-Petting</title>
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
<jsp:include page="/views/userHeader.jsp" />
</head>


    <body>
    
    <style>
    .btn-primary {
		font-size: 14px;
		font-weight: 500;
		padding: 10px 40px;
		color: #ffffff;
		border: none;
		background-color: #386641;
		border-radius: 4px;
	}
        
    .follow{
		color: #000000;
		border: #386641;
		background-color: #FB8500;
		border-radius: 4px;
	}




        </style>
    
        <main>
        
             <section class="content-main">
                <div class="content-header">
                </div>
                <div class="card mb-4">
                    <div class="card-header bg-brand-2" style="height: 150px"></div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl col-lg flex-grow-0" style="flex-basis: 230px">
                                <div class="img-thumbnail shadow w-100 bg-white position-relative text-center" style="height: 190px; width: 200px; margin-top: -120px">
                                    <img src="<%=request.getContextPath()%>/nest-backend/assets/imgs/people/cat_img_01.png" class="center-xy img-fluid" alt="Logo Brand" />
                                </div>
                            </div>
                            <!--  col.// -->
                            <div class="col-xl col-lg">
                                <h3>${membersVO.nickname}</h3>
<!--                                 <p>使用者簡介</p> -->
                            </div>
                            <!--  col.// -->
                            <div class="col-xl-4 text-md-end">
<!--                                 <select class="form-select w-auto d-inline-block"> -->
<!--                                     <option>Actions</option> -->
<!--                                     <option>Disable shop</option> -->
<!--                                     <option>Analyze</option> -->
<!--                                     <option>Something</option> -->
<!--                                 </select> -->
                                <button type="button" class="btn btn-primary"> 追蹤此用戶 <i class="material-icons md-person_add"></i> </button>
                            </div>
                            <!--  col.// -->
                        </div>
                        <!-- card-body.// -->
                        <hr class="my-4" />
                        <div class="row g-4">
                            <div class="col-md-12 col-lg-4 col-xl-2">
                                <article class="box">
                                    <p class="mb-0 text-muted">總發文數:</p>
                                    <h5 class="text-success">238</h5>
                                    <p class="mb-0 text-muted">總收到的愛心數:</p>
                                    <h5 class="text-success mb-0">2380</h5>
                                </article>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-lg-4 col-xl-3">
                                <h6>Information</h6>
                                <p>${membersVO.intro}
                                </p>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-lg-4 col-xl-3">
                                <h6>Contacts</h6>
                                <p>${membersVO.email}
                                </p>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-xl-4 text-xl-end">
                            </div>
                            <!--  col.// -->
                        </div>
                        <!--  row.// -->
                    </div>
                    <!--  card-body.// -->
                </div>
                <!--  card.// -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h3 class="card-title">部落格文章列表</h3>
                        <div class="card-body">
                            <c:forEach var="memBlogArtVO" items="${list}">
                            <article class="itemlist">
                                <div class="row align-items-center">
                                    <div class="col col-check flex-grow-0">
                                    </div>
                                    <div class="col-lg-4 col-sm-4 col-8 flex-grow-1 col-name">
                                        <a class="itemside" href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">
                                            <div class="left">
                                                <img src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}" class="img-sm img-thumbnail" alt="Item" />
                                            </div>
                                            <div class="info">
                                                <h6 class="mb-0">${memBlogArtVO.title}</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-lg-2 col-sm-2 col-6 col-price"></div>
                                    <div class="col-lg-2 col-sm-2 col-6 col-date">
                                        <span>02.11.2021</span>
                                    </div>
                                    <div class="col-lg-2 col-sm-2 col-4 col-action text-end">
                                        <a href="#" class="btn btn-sm font-sm rounded btn-brand"> <i class="material-icons md-favorite_border"></i> like </a>
                                        <a href="#" class="btn btn-sm font-sm btn-light rounded"> <i class="material-icons md-bookmark_border"></i> bookmark </a>
                                    </div>
                                </div>
                                <!-- row .// -->
                            </article>
                            </c:forEach>
                        </div>
                            </div>
                        <!-- row.// -->
                    </div>
                    <!--  card-body.// -->
                </div>
                <!--  card.// -->
                <div class="pagination-area mt-30 mb-50">
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
            </section>
            <!-- content-main end// -->

        </main>
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



        <script>
            $('button.btn-primary').click(function(){
                console.log("有執行");
                $('button.btn-primary').text("已追蹤用戶").toggleClass("follow");

                $.ajax({
                    method: "POST",
                    url: "<%=request.getContextPath()%>/MemFollowServlet",
                    
                    data: {
                        action: "if_friend",
                        memberId:"<%=membersVO.getMemberid()%>",
                        followee:"<%=membersVO2.getMemberid()%>",
                        },

                    dataType : 'json',

                    complete: function(jqXHR){
                    	console.log(jqXHR.readyState)
                        if(jqXHR.readyState === 4) {
                        	console.log(jqXHR.responseText)
                        	data = jqXHR.responseText
                        	console.log(JSON.parse(data).success);
                            console.log(JSON.parse(data).type);

                            if(JSON.parse(data).success && JSON.parse(data).type==="insert"){
                                $('button.btn-primary').text("已追蹤用戶").toggleClass("follow");
                            } else {
                                $('button.btn-primary').text("追蹤此用戶").toggleClass("follow")
                            }



                        }
                    }


                    });
                        
//                     complete: function(data){
                        
//                         console.log(data)
//                         }
//                     });

            })


        </script>


        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
