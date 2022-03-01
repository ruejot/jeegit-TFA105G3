<%@page import="com.memreply.model.MemReplyVO"%>
<%@page import="com.memreply.model.MemReplyService"%>
<%@page import="com.memartpic.model.MemArtPicService"%>
<%@page import="com.memartpic.controller.GetPic"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>
<%@ page import="com.memartpic.model.*"%>

<%

MemBlogArtService artSvc = new MemBlogArtService();
List<MemBlogArtVO> list = artSvc.getAll();
pageContext.setAttribute("list",list);

MemArtPicService mapSvc = new MemArtPicService();
List<MemArtPicVO> list2 = mapSvc.getAll();
pageContext.setAttribute("list2",list2);

%>




<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Nest - Multipurpose eCommerce HTML Template</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link rel="stylesheet" href="assets/css/main.css" />
    </head>
    <jsp:include page="userHeader.jsp"></jsp:include>
    <body>
        <main class="main">
            <div class="page-header mt-30 mb-75">
                <div class="container">
                    <div class="archive-header">
                        <div class="row align-items-center">
                            <div class="col-xl-3">
                                <h1 class="mb-15">部落格文章</h1>
                                <div class="breadcrumb">
                                    <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>首頁</a>
                                    <span></span> 部落格文章
                                </div>
                            </div>
                            <div class="col-xl-9 text-end d-none d-xl-block">
                                <ul class="tags-list">
                                    <li class="hover-up">
                                        <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>貓貓</a>
                                    </li>
                                    <li class="hover-up active">
                                        <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>寵物知識</a>
                                    </li>
                                    <li class="hover-up">
                                        <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>米克斯</a>
                                    </li>
                                    <li class="hover-up">
                                        <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>天竺鼠車車</a>
                                    </li>
                                    <li class="hover-up mr-0">
                                        <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>木木梟</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-content mb-50">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-8 col-lg-10 col-md-12 m-auto">
                            <div class="shop-product-fillter mb-50">
                                <div class="totall-product">
                                    <h2>
                                        <img class="w-36px mr-10" src="assets/imgs/theme/icons/category-1.svg" alt="" />
                                        最新文章
                                    </h2>
                                </div>
                                <div class="sort-by-product-area">
                                    <div class="sort-by-cover mr-10">
                                        <div class="sort-by-product-wrap">
                                            <div class="sort-by">
                                                <span><i class="fi-rs-apps"></i>Show:</span>
                                            </div>
                                            <div class="sort-by-dropdown-wrap">
                                                <span> 50 <i class="fi-rs-angle-small-down"></i></span>
                                            </div>
                                        </div>
                                        <div class="sort-by-dropdown">
                                            <ul>
                                                <li><a class="active" href="#">3</a></li>
                                                <li><a href="#">5</a></li>
                                                <li><a href="#">7</a></li>
                                                <li><a href="#">10</a></li>
                                                <li><a href="#">All</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="sort-by-cover">
                                        <div class="sort-by-product-wrap">
                                            <div class="sort-by">
                                                <span><i class="fi-rs-apps-sort"></i>Sort:</span>
                                            </div>
                                            <div class="sort-by-dropdown-wrap">
                                                <span>Featured <i class="fi-rs-angle-small-down"></i></span>
                                            </div>
                                        </div>
                                        <div class="sort-by-dropdown">
                                            <ul>
                                                <li><a class="active" href="#">Featured</a></li>
                                                <li><a href="#">Newest</a></li>
                                                <li><a href="#">Most comments</a></li>
                                                <li><a href="#">Release Date</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="loop-grid loop-big">
                                <div class="row">
                                <%@ include file="/pages/blog_catagory_page.file" %>
                                <c:forEach var="memBlogArtVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									
                                    <article class="first-post mb-30 hover-up animated" style="visibility: visible">
                                        <div class="position-relative overflow-hidden">
                                            <div class="post-thumb border-radius-15">
                                                 <a href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">            

<%--                                                 	<c:forEach var="MemArtPicVO" items="${list2}"> --%>
<%--                    										 <c:if test="${memBlogArtVO.artid==memArtPicVO.blArtId}"> --%>
               <img class="border-radius-15" src="<%= request.getContextPath() %>/GetPic?blArtPicId=${memBlogArtVO.artid}" alt="" />
	                 									
<%--                    										 </c:if> --%>
<%--                 									</c:forEach> --%>
                									
                                                </a>
                                            </div>
                                        </div>
                                        <div class="entry-content">
                                            <h2 class="post-title mb-20">
                                                <a href="<%=request.getContextPath()%>/MemBlogArtServlet?action=getOne_For_Display&artid=${memBlogArtVO.artid}">${memBlogArtVO.title}</a>
                                            </h2>
                                            <div style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
                                            <p class="post-exerpt font-medium text-muted mb-30">${memBlogArtVO.content}</p>
                                            </div>
                                            <div class="mb-20 entry-meta meta-2">
                                                <div class="entry-meta meta-1 mb-30">
                                                    <div class="font-sm">
                                                        <span
                                                            ><span class="mr-10 text-muted"><i class="fi-rs-eye"></i></span>23k</span
                                                        >
                                                        <span class="ml-30"
                                                            ><span class="mr-10 text-muted"><i class="fi-rs-comment-alt"></i></span>17k</span
                                                        >
                                                        <span class="ml-30"
                                                            ><span class="mr-10 text-muted"><i class="fi-rs-share"></i></span>18k</span
                                                        >
                                                    </div>
                                                </div>
                                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemBlogArtServlet" style="margin-bottom: 0px;">
                                                <input type="hidden" name="artid"  value="${memBlogArtVO.artid}">
                                                <input type="hidden" name="action" value="getOne_For_Display">
                                                <button type="submit" class="btn btn-sm"><i class="fi-rs-arrow-right ml-10"></i>更多...</button>
                                                </FORM>
                                                
                                            </div>
                                        </div>
                                    </article>
                                   
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="pagination-area mt-15 mb-sm-5 mb-lg-0">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-start">
                                        <li class="page-item">
                                            <a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>"><i class="fi-rs-arrow-small-left"></i></a>
                                        </li>
                                        <%if (pageNumber>1) {%>
                                        
                                        <%for (int i=1; i<=pageNumber; i++){%>
                                        <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage=i%>"><%=i%></a></li>
                                        <%}%> 
                                        <%}%>
                                        <li class="page-item">
                                            <a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>"><i class="fi-rs-arrow-small-right"></i></a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
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
        <script src="./assets/js/main.js?v=4.0"></script>
        <script src="./assets/js/shop.js?v=4.0"></script>
    </body>
</html>
