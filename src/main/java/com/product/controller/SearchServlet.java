package com.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.HomePageService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/product/SearchServlet")
public class SearchServlet extends HttpServlet {
	private HomePageService SERVICE;

	public SearchServlet() {
		SERVICE = new HomePageService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

//		=========↓ 來自userHeader.jsp的請求↓==============
		if ("search_from_header".equals(action)) {
			String usersearch = req.getParameter("usersearch");
			List<ProductVO> searchlistSet = SERVICE.getAllByProductName(usersearch);
			Integer counts = SERVICE.getCountsBySearchBox(usersearch);
			if (searchlistSet == null) {
				req.getRequestDispatcher("../nest-frontend/HomePage.jsp").forward(req, res);

			} else {
				req.getSession().setAttribute("counts", counts);
				req.getSession().setAttribute("searchlist", searchlistSet);
				req.setAttribute("usersearch", usersearch);
				req.getRequestDispatcher("../nest-frontend/ProductGridlist.jsp").forward(req, res);
			}
		}

//        =========↓ 來自HomePage.jsp HomeTag的請求↓==============
		if ("HomeTag".equals(action)) {
			String mainCategory = req.getParameter("mainCategory");
			List<ProductVO> searchlist = SERVICE.getSpecialClassByMainCategory(mainCategory);
			Integer counts = SERVICE.getCountsByMainCategory(mainCategory); 
			if (searchlist != null)  {
				req.getSession().setAttribute("counts", counts);
				req.getSession().setAttribute("searchlist", searchlist);
				req.setAttribute("mainCategory", mainCategory);
				req.getRequestDispatcher("../nest-frontend/ProductGridlist.jsp").forward(req, res);
			}
		}
//        =========↓ 來自HomePage.jsp 特色子類別subcategory的請求↓==============
		if ("sub".equals(action)) {
			String subCategory = req.getParameter("subCategory");
			List<ProductVO> searchlist = SERVICE.getSubCategoryName(subCategory);
			Integer counts = SERVICE.getCountsBySubCategory(subCategory); 
			if (searchlist != null) {
				req.getSession().setAttribute("counts", counts);
				req.getSession().setAttribute("searchlist", searchlist);
				req.setAttribute("subCategory", subCategory);
				req.getRequestDispatcher("../nest-frontend/ProductGridlist.jsp").forward(req, res);
			}
		}
	}
}
