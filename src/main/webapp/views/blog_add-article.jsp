<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>

<%
MemBlogArtVO memBlogArtVO = (MemBlogArtVO) request.getAttribute("memBlogArtVO");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Nest Dashboard</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link href="<%= request.getContextPath() %>/assets/css/main_backend.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
     <jsp:include page="userHeader.jsp"></jsp:include>
        <div class="screen-overlay"></div>
        <main class="main-wrap">
            <section class="content-main">
                <div class="row">
                    <div class="col-9">
                        <div class="content-header">
                            <h2 class="content-title">新增文章</h2>
                         </div>
                    </div>
                    <form method="post" action="/TFA105G3/MemBlogArtServlet" enctype="multipart/form-data" accept="image/*">
                    <input type="hidden" name="action" value="insert">
                    <div class="col-lg-6">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="mb-4">
                                <%-- 錯誤表列 --%>
                                <c:if test="${not empty errorMsgs}">
	                                <font style="color:red">請修正以下錯誤:</font>
	                                <ul>
		                        <c:forEach var="message" items="${errorMsgs}">
			                        <li style="color:red">${message}</li>
		                        </c:forEach>
	                                </ul>
                                </c:if>
                                    <label for="product_title" class="form-label">文章標題</label>
                                    <input type="text" placeholder="Type here" class="form-control" id="product_title" name="title" value="<%= (memBlogArtVO==null)? "" : memBlogArtVO.getTitle()%>"/>
                                </div>
                            </div>
                        </div>
                        <!-- card end// -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <div>
                                    <label class="form-label">文章內容</label>
                                    <textarea placeholder="Type here" class="form-control" rows="20"  name="content" style="height: 300px"><%= (memBlogArtVO==null)? "" : memBlogArtVO.getContent() %>"</textarea>
                                </div>
                            </div>
                                                        <div class="card-body">


                                <div class="mb-4">
                                    <label class="form-label">#HashTags</label>
                                    <input type="text" placeholder="Type here" class="form-control" />
                                </div>
                                <span>熱門#HashTags</span>
                                <a href="#" class="font-xs hover-up mr-15"><i class="font-xs material-icons md-close text-body"></i> 兔兔這麼可愛</a>
                                <a href="#" class="font-xs hover-up mr-15"><i class="font-xs material-icons md-close text-body"></i> 喵主子</a>
                                <a href="#" class="font-xs hover-up mr-15"><i class="font-xs material-icons md-close text-body"></i> 黃金獵犬</a>

                            </div>
                        </div>
                        <!-- card end// -->


                            
                                                    <div class="card mb-4">
                            <div class="card-body">
                                <div>
                                
                                
                                    <label class="form-label">上傳圖片</label>
                                    <input class="form-control" type="file"/>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">
                        </div>
                        
                            <div>
                                <button class="btn btn-light rounded font-sm mr-5 text-body hover-up">儲存草稿</button>
                                <button type="submit" class="btn btn-md rounded font-sm hover-up">新增文章</button>
                            </div>
                            
                                 </form>                   


                        <!-- card end// -->
                    </div>

                                        
                        <!-- card end// -->
                </div>
              
              </hr>  
            </section>



        </main>
        <script src="../assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="../assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="../assets/js/vendors/select2.min.js"></script>
        <script src="../assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="../assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="../assets/js/main_backend.js" type="text/javascript"></script>
    <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
