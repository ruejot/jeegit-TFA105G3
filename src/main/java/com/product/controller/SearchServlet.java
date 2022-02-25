package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.HomePageService;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/product/SearchServlet")
public class SearchServlet extends HttpServlet {
	{
		
	}
	private HomePageService SERVICE;
	public SearchServlet() {
		SERVICE = new HomePageService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

//		=========↓ 來自sellerHeader.jsp的請求↓==============
		if ("search_from_header".equals(action)) {
			String usersearch = req.getParameter("usersearch");
			List<ProductVO> searchlist = SERVICE.getAllByProductName(usersearch);
			if (searchlist == null) {
				req.getRequestDispatcher("../nest-frontend/HomePage.jsp").forward(req, res);
				
			} else {
				req.setAttribute("searchlist", searchlist);
				req.setAttribute("usersearch", usersearch);
				req.getRequestDispatcher("../nest-frontend/ProductGridlist.jsp").forward(req, res);
			}
		}

//        =========↓ 來自HomePage.jsp specialClass的請求↓==============
		if ("HomeTag".equals(action)) {
			String mainCategory = req.getParameter("mainCategory");
			List<ProductVO> searchlist = SERVICE.getSpecialClassByMainCategory(mainCategory);
			if (searchlist != null) {
				req.setAttribute("searchlist", searchlist);
				req.setAttribute("mainCategory", mainCategory);
				req.getRequestDispatcher("../nest-frontend/ProductGridlist.jsp").forward(req, res);
			}
		}
	}
}
