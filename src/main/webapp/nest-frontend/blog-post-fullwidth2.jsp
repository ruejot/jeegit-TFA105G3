<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>
<%@ page import="com.memreply.model.*"%>
<%@ page import="com.members.model.*" %>


<%
MemBlogArtVO memBlogArtVO = (MemBlogArtVO) request.getAttribute("memBlogArtVO"); //MemBlogArtServlet.java(Controller), 存入req的empVO物件
MemReplyVO memreplyVO = (MemReplyVO) request.getAttribute("MemReplyVO");

MembersService memSvc = new MembersService();

//找出文章作者名稱
MembersVO membersVOinfo = memSvc.select(memBlogArtVO.getMemberId());

//找出留言作者名稱
List<MembersVO> list3 = memSvc.selectAll();
pageContext.setAttribute("list3",list3);

MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");


MemReplyService mrSvc = new MemReplyService();
List<MemReplyVO> list2 = mrSvc.getAllByArtId(memBlogArtVO.getArtid());
pageContext.setAttribute("list2",list2);


%>

<jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />

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
<jsp:include page="/views/userHeader.jsp"></jsp:include>
</head>

<body>
    <main class="main">
        <div class="page-content mb-50">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 m-auto">
                        <div class="single-page pt-50 pr-30">
                            <div class="single-header style-2">
                                <div class="row">
                                    <div class="col-xl-10 col-lg-12 m-auto">
<!--                                         <h6 class="mb-10"><a href="#">Recipes</a></h6> -->
                                        <h2 class="mb-10">${memBlogArtVO.title}</h2>
                                        <div class="single-header-meta">
                                            <div class="entry-meta meta-1 font-xs mt-15 mb-15">
                                                <a class="author-avatar" href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getMem_For_Display&memberId=${memBlogArtVO.memberId}">
                                                    <img class="img-circle" src="assets/imgs/blog/author-1.png" alt="">
                                                </a>
                                                <span class="post-by">By <a href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getMem_For_Display&memberId=${memBlogArtVO.memberId}"><%=membersVOinfo.getName()%></a></span>
                                                <span class="post-on has-dot">${memBlogArtVO.posttime}</span>
                                            </div>
                                            <div class="social-icons single-share">
                                                <ul class="text-grey-5 d-inline-block">
                                                    <li class="mr-5"><img class="bookmark" src="assets/imgs/theme/icons/icon-bookmark.svg" alt=""><a class="saved" href="#" >收藏此文章</a></li>
                                                    <li><a href="#"><img src="assets/imgs/theme/icons/icon-heart-2.svg" alt=""></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <figure class="single-thumbnail">
                                <img src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}" alt="">
                            </figure>
                            <div class="single-content">
                                <div class="row">
                                    <div class="col-xl-10 col-lg-12 m-auto">
                                    
                                    <p style="white-space: pre-wrap;">${memBlogArtVO.content}</p>
                             
<!--                                         <p class="single-excerpt">Helping everyone live happier, healthier lives at home through their kitchen. Kitchn is a daily food magazine on the Web celebrating life in the kitchen through home cooking and kitchen intelligence.</p> -->
<!--                                         <p>We've reviewed and ranked all of the best smartwatches on the market right now, and we've made a definitive list of the top 10 devices you can buy today. One of the 10 picks below may just be your perfect next smartwatch.</p> -->
<!--                                         <p>Those top-end wearables span from the Apple Watch to Fitbits, Garmin watches to Tizen-sporting Samsung watches. There's also Wear OS which is Google's own wearable operating system in the vein of Apple's watchOS - you’ll see it show up in a lot of these devices.</p> -->
<!--                                         <h5 class="mt-50">Lorem ipsum dolor sit amet cons</h5> -->
<!--                                         <p>Throughout our review process, we look at the design, features, battery life, spec, price and more for each smartwatch, rank it against the competition and enter it into the list you'll find below.</p> -->
<!--                                         <img class="mb-30" src="assets/imgs/blog/blog-21.png" alt=""> -->
<!--                                         <p>Tortor, lobortis semper viverra ac, molestie tortor laoreet amet euismod et diam quis aliquam consequat porttitor integer a nisl, in faucibus nunc et aenean turpis dui dignissim nec scelerisque ullamcorper eu neque, augue quam quis lacus pretium eros est amet turpis nunc in turpis massa et eget facilisis ante molestie penatibus dolor volutpat, porta pellentesque scelerisque at ornare dui tincidunt cras feugiat tempor lectus</p> -->
<!--                                         <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet id enim, libero sit. Est donec lobortis cursus amet, cras elementum libero convallis feugiat. Nulla faucibus facilisi tincidunt a arcu, sem donec sed sed. Tincidunt morbi scelerisque lectus non. At leo mauris, vel augue. Facilisi diam consequat amet, commodo lorem nisl, odio malesuada cras. Tempus lectus sed libero viverra ut. Facilisi rhoncus elit sit sit.</p> -->
                                        <!--Entry bottom-->
                                        <div class="entry-bottom mt-50 mb-30">
                                            <div class="tags w-50 w-sm-100">
                                                <a href="blog-category-big.html" rel="tag" class="hover-up btn btn-sm btn-rounded mr-10">deer</a>
                                                <a href="blog-category-big.html" rel="tag" class="hover-up btn btn-sm btn-rounded mr-10">nature</a>
                                                <a href="blog-category-big.html" rel="tag" class="hover-up btn btn-sm btn-rounded mr-10">conserve</a>
                                            </div>
                                            <div class="social-icons single-share">
                                                <ul class="text-grey-5 d-inline-block">
                                                    <li><strong class="mr-10">Share this:</strong></li>
                                                    <li class="social-facebook"><a href="#"><img src="assets/imgs/theme/icons/icon-facebook.svg" alt=""></a></li>
                                                    <li class="social-twitter"> <a href="#"><img src="assets/imgs/theme/icons/icon-twitter.svg" alt=""></a></li>
                                                    <li class="social-instagram"><a href="#"><img src="assets/imgs/theme/icons/icon-instagram.svg" alt=""></a></li>
                                                    <li class="social-linkedin"><a href="#"><img src="assets/imgs/theme/icons/icon-pinterest.svg" alt=""></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!--Author box-->
                                        <div class="author-bio p-30 mt-50 border-radius-15 bg-white">
                                            <div class="author-image mb-30">
                                            
                                                <a href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getMem_For_Display&memberId=${memBlogArtVO.memberId}"><img src="assets/imgs/blog/author-1.png" alt="" class="avatar"></a>
                                                <div class="author-infor">
                                                    <h5 class="mb-5"><%=membersVOinfo.getName()%></h5>
                                                    <p class="mb-0 text-muted font-xs">
                                                        <span class="mr-10">306 posts</span>
                                                        <span class="has-dot">Since 2012</span>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="author-des">
                                                <p><%=membersVOinfo.getIntro()%></p>
                                            </div>
                                        </div>
                                        <!--Comment form-->
                                        <div class="comment-form">
                                            
<!--                                                 <input type="hidden" name="action" value="insert"> -->
                                        
                                            <h3 class="mb-15 text-center mb-30">回應文章</h3>
                                            <div class="row">
                                                <div class="col-lg-9 col-md-12  m-auto">
                                                    <form class="form-contact comment_form mb-50" action="<%=request.getContextPath() %>/MemReplyServlet" id="commentForm" method="post">                                          
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="form-group">
                                                                    <input type="hidden" name="reArtId" value="<%=memBlogArtVO.getArtid()%>">
                                                                    <input type="hidden" name="action" value="insert">
                                                                    <textarea class="form-control w-100" name="re" id="comment" cols="30" rows="9" placeholder="請在此輸入回應內容"></textarea>
                                                                </div>
                                                            </div>
<!--                                                             <div class="col-sm-6"> -->
<!--                                                                 <div class="form-group"> -->
<!--                                                                     <input class="form-control" name="name" id="name" type="text" placeholder="Name"> -->
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="col-sm-6"> -->
<!--                                                                 <div class="form-group"> -->
<!--                                                                     <input class="form-control" name="email" id="email" type="email" placeholder="Email"> -->
<!--                                                                 </div> -->
<!--                                                             </div> -->
<!--                                                             <div class="col-12"> -->
<!--                                                                 <div class="form-group"> -->
<!--                                                                     <input class="form-control" name="website" id="website" type="text" placeholder="Website"> -->
<!--                                                                 </div> -->
<!--                                                             </div> -->
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                            <button type="submit" class="button button-contactForm">回應文章</button>
                                                        </div>
                                                    </form>
                                                    <div class="comments-area">
                                                        <h3 class="mb-30">留言：</h3>
                                                        <div class="comment-list   m-auto"">
                                                        
                                                        <c:forEach var="memReplyVO" items="${list2}">
                                                            <div class=" single-comment justify-content-between d-flex mb-30">
                                                            <div class="user justify-content-between d-flex">
                                                                <div class="thumb text-center" style="width:150px;">
                                                                    <img src="assets/imgs/blog/author-2.png" alt="">
<!--                                                                     TODO:member名稱要用id進資料庫找出來 -->
                                                                    <c:forEach var="membersVOinfo" items="${list3}">
                                                                        
                                                                    <a href="#" class="font-heading text-brand">
                                                                    <c:if test="${memReplyVO.reMemberId==membersVOinfo.memberid}">
                                                                    ${membersVOinfo.nickname}
                                                                    </c:if>
                                                                    </a>
                                                                        
                                                                    </c:forEach>
                                                                </div>
                                                                <div class="desc">
                                                                    <div class="d-flex justify-content-between mb-10">
                                                                        <div class="d-flex align-items-center">
                                                                            <span class="font-xs text-muted">${memReplyVO.time}</span>
                                                                        </div>
                                                                        <div class="product-rate d-inline-block">
                                                                            <div class="product-rating" style="width:80%">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <p class="mb-10">${memReplyVO.re}<a href="#" class="reply">Reply</a></p>
                                                                    <% %>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </c:forEach>
                                                        
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


    <script>
        $('li.mr-5').click(function(){
            console.log("有執行");
            $('li.mr-5').toggleClass("saved");
//             $('img.bookmark').attr("src", "assets/imgs/theme/icons/bookmark_black_24dp.svg");

            $.ajax({
                method: "POST",
                url: "<%=request.getContextPath()%>/MemSavedArtServlet",
                
                data: {
                    action: "if_saved",
                    savMemberId:"<%=membersVO.getMemberid()%>",
                    savArtId:"<%=memBlogArtVO.getArtid()%>",
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
                            $('li.mr-5').toggleClass("saved");
                            $('a.saved').text("已收藏文章");
                            
                        } else {
                            $('li.mr-5').toggleClass("saved")
                            $('a.saved').text("收藏此文章");
                        }



                    }
                }


                });
        })
    </script>



</body>

</html>