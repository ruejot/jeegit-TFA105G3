<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside class="navbar-aside" id="offcanvas_aside">
	<div class="aside-top">
		<a href="index.html" class="brand-wrap"> <img
			src="<%=request.getContextPath()%>/assets/imgs/theme/logo_Petting.svg"
			class="logo" alt="Nest Dashboard" />
		</a>
		<div>
			<button class="btn btn-icon btn-aside-minimize">
				<i class="text-muted material-icons md-menu_open"></i>
			</button>
		</div>
	</div>
	<nav>
		<ul class="menu-aside">
			<li class="menu-item has-submenu"><a class="menu-link"
				href="page-orders-1.html"> <i
					class="icon material-icons md-shopping_cart"></i> <span
					class="text">管理訂單</span>
			</a>
				<div class="submenu">
					<a href="page-orders-2.html">全部訂單列表</a> <a
						href="page-orders-detail.html">訂單明細</a>
				</div></li>
			<li class="menu-item active"><a class="menu-link"
				href="<%=request.getContextPath()%>/nest-backend/BEnd_CsDetail_ListAll.jsp">
					<i class="icon material-icons md-comment"></i> <span class="text">回應客服</span>
			</a></li>
		</ul>
		<hr />
		<ul class="menu-aside">
			<li class="menu-item has-submenu"><a class="menu-link" href="#">
					<i class="icon material-icons md-settings"></i> <span class="text">設定</span>
			</a>
				<div class="submenu">
					<a href="page-settings-1.html">設定樣本 1</a> <a
						href="page-settings-2.html">設定樣本 2</a>
				</div></li>
			<!--                     <li class="menu-item"> -->
			<!--                         <a class="menu-link" href="page-blank.html"> -->
			<!--                             <i class="icon material-icons md-local_offer"></i> -->
			<!--                             <span class="text">商家管理後臺首頁</span> -->
			<!--                         </a> -->
			<!--                     </li> -->
		</ul>
		<br /> <br />
	</nav>
</aside>