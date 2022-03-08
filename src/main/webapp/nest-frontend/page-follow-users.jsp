<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memfollow.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.memblogart.model.*"%>

<%

MembersVO membersVO = (MembersVO) session.getAttribute("MemberUsing");

MemFollowService mfSvc = new MemFollowService();
List<MemFollowVO> list = mfSvc.getAllByFollowee(membersVO.getMemberid());
pageContext.setAttribute("list",list);

MembersService memSvc = new MembersService();
List<MembersVO> list2 = memSvc.selectAll();
pageContext.setAttribute("list2",list2);

MemBlogArtVO memBlogArtVO = (MemBlogArtVO) request.getAttribute("memBlogArtVO");
%>

<jsp:useBean id="memberSvc" scope="page" class="com.members.model.MembersService" />

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
        <link rel="shortcut icon" type="image/x-icon" href="assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link href="assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="screen-overlay"></div>
         <main class="main-wrap">
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
                        <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4">
                            <!-- col.// -->
                            <c:forEach var="memFollowVO" items="${list}">
                            <div class="col">
                                <div class="card card-user">
                                    <div class="card-header">
                                        <img class="img-md img-avatar" src="assets/imgs/people/avatar-2.png" alt="User pic" />
                                    </div>
                                    <div class="card-body">
                                        <c:forEach var="membersVO" items="${list2}">
                                            <c:if test="${memFollowVO.memberId==membersVO.memberid}">
                                                <h5 class="card-title mt-50">${membersVO.nickname}</h5>
                                            </c:if>
                                        </c:forEach>
                                        <div class="card-text text-muted">
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
            <!-- content-main end// -->
        </main>
        <script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="assets/js/vendors/select2.min.js"></script>
        <script src="assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="assets/js/main.js?v=1.1" type="text/javascript"></script>
    </body>
</html>
