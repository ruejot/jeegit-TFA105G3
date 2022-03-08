<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <body> -->
<!-- 	<div class="screen-overlay"></div> -->
	<aside class="navbar-aside" id="offcanvas_aside">
		<div class="aside-top">
			<a href="<%=request.getContextPath()%>/nest-frontend/HomePage.jsp" class="brand-wrap"> 
			<img src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg" class="logo" alt="logo" />
			</a>
			<div>
				<button class="btn btn-icon btn-aside-minimize">
					<i class="text-muted material-icons md-menu_open"></i>
				</button>
			</div>
		</div>
		<nav>
			<ul class="menu-aside">
				<li class="menu-item"><a class="menu-link"
					href="<%=request.getContextPath()%>/nest-backend/productManage.jsp"> 
					<i class="icon material-icons md-shopping_bag"></i> <span
						class="text">商品管理</span>
				</a></li>
				<li class="menu-item"><a class="menu-link"
					href="<%=request.getContextPath()%>/nest-backend/orderManage.jsp"> 
					<i class="icon material-icons md-shopping_cart"></i> <span
						class="text">訂單管理</span>
				</a></li>
				<li class="menu-item"><a class="menu-link"
					href="<%=request.getContextPath()%>/nest-backend/addProduct.jsp"> <i
						class="icon material-icons md-add_box"></i> <span class="text">商品上架</span>
				</a></li>
				<li class="menu-item"><a class="menu-link" 
					href="<%=request.getContextPath()%>/nest-backend/busAccountCenter.jsp">
						<i class="icon material-icons md-person"></i> <span class="text">廠商資料</span>
				</a></li>
				<li class="menu-item"><a class="menu-link" 
					href="<%=request.getContextPath()%>/nest-frontend/fendshop_mainpage.jsp?action=fromBackendSelf">
						<i class="icon material-icons md-home"></i> <span class="text">我的商店</span>
				</a></li>
				<li class="menu-item"><a class="menu-link"
					href="<%=request.getContextPath()%>/nest-backend/bendcsdetail_listall.jsp"> <i
						class="icon material-icons md-comment"></i> <span class="text">留言評價</span>
				</a></li>
<!-- 				<li class="menu-item"><a class="menu-link" href="#"> <i -->
<!-- 						class="icon material-icons md-pie_chart"></i> <span class="text">數據中心</span> -->
<!-- 				</a></li> -->
			</ul>
			<hr />
<!-- 			<ul class="menu-aside"> -->
<%-- 				<li class="menu-item"><a class="menu-link" href="<%=request.getContextPath()%>/nest-backend/addProduct.jsp"> --%>
<!-- 						<i class="icon material-icons md-settings"></i> <span class="text">相關設定</span> -->
<!-- 				</a></li> -->
<!-- 			</ul> -->
			<br /> <br />
		</nav>
	</aside>
<!-- </body> -->