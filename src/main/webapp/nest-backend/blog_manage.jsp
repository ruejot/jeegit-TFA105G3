<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memblogart.model.*"%>


<!-- getAllMyMember的數字到時候記得改成登入的使用者 -->
<%

MemBlogArtService artSvc = new MemBlogArtService();
List<MemBlogArtVO> list = artSvc.getAllByMember(1);
pageContext.setAttribute("list",list);

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
        <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/nest-backend/assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link href="<%= request.getContextPath() %>/nest-backend/assets/css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <jsp:include page="userHeader.jsp"></jsp:include>
    <body>
        <main class="main-wrap">
        
            <section class="content-main">
                <div class="content-header">
                    <div>
                        <h2 class="content-title card-title">文章管理</h2>
                        <p>Lorem ipsum dolor sit amet.</p>
                    </div>
                    <div>
                        <a href="blog_add-article.jsp" class="btn btn-primary btn-sm rounded">新增文章</a>
                    </div>
                </div>
                <div class="card mb-4">
                    <header class="card-header">
                        <div class="row align-items-center">
                            <div class="col col-check flex-grow-0">
                                <div class="form-check ms-2">
                                    <input class="form-check-input" type="checkbox" value="" />
                                </div>
                            </div>
                            <div class="col-md-3 col-12 me-auto mb-md-0 mb-3">
                                <select class="form-select">
                                    <option selected>All category</option>
                                    <option>Electronics</option>
                                    <option>Clothes</option>
                                    <option>Automobile</option>
                                </select>
                            </div>
                            <div class="col-md-2 col-6">
                                <input type="date" value="02.05.2021" class="form-control" />
                            </div>
                            <div class="col-md-2 col-6">
                                <select class="form-select">
                                    <option selected>文章狀態</option>
                                    <option>已發佈</option>
                                    <option>草稿</option>
                                    <option>全部狀態</option>
                                </select>
                            </div>
                        </div>
                    </header>
                    <!-- card-header end// -->
                    <div class="card-body">
<%@ include file="page1.file" %> 
<c:forEach var="memBlogArtVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                        <article class="itemlist">
                            <div class="row align-items-center">
                                <div class="col col-check flex-grow-0">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" />
                                    </div>
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
                                <div class="col-lg-1 col-sm-2 col-4 col-date">
                                    <span>02.11.2021</span>
                                </div>
                                <div class="col-lg-2 col-sm-2 col-4 col-action text-end">

                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemBlogArtServlet" style="margin-bottom: 0px;">
			                        <input type="hidden" name="artid"  value="${memBlogArtVO.artid}">
			                        <input type="hidden" name="action" value="edit">
                                    <button type="submit" class="btn btn-sm font-sm rounded btn-brand"><i class="material-icons md-edit"></i>編輯</button>
                                    
                                </FORM>
                                    
                                    
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemBlogArtServlet" style="margin-bottom: 0px;">
			                        <input type="hidden" name="artid"  value="${memBlogArtVO.artid}">
			                        <input type="hidden" name="action" value="delete">
                                    <button type="submit" class="btn btn-sm font-sm btn-light rounded"><i class="material-icons md-delete_forever"></i>刪除</button>
                                </FORM>
                                </div>
                            </div>
                            <!-- row .// -->
                        </article>
</c:forEach>
                    </div>
                    <!-- card-body end// -->
                </div>
                <!-- card end// -->
                
                <div class="pagination-area mt-30 mb-50">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-start">
                            <%if (pageNumber>1) {%>
                            <%for (int i=1; i<=pageNumber; i++){%>
                            <li class="page-item"><a class="page-link dot" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage=i%>"><%=i%></a></li>
                            <%}%> 
                                        <%}%>
                            <li class="page-item">
                                <a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>"><i class="material-icons md-chevron_right"></i></a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </section>
            <!-- content-main end// -->
            <footer class="main-footer font-xs">
                <div class="row pb-30 pt-15">
                    <div class="col-sm-6">
                        <script>
                            document.write(new Date().getFullYear());
                        </script>
                        &copy; Nest - HTML Ecommerce Template .
                    </div>
                    <div class="col-sm-6">
                        <div class="text-sm-end">All rights reserved</div>
                    </div>
                </div>
            </footer>
        </main>
        <script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="assets/js/vendors/select2.min.js"></script>
        <script src="assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="assets/js/main.js" type="text/javascript"></script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>