package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class maintitleServlet
 */
@WebServlet("/product/maintitleServlet")
public class mainTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService SERVICE;
	
    public mainTitleServlet() {
    	SERVICE = new ProductService();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("maintitle".equals(action)) {
			String title = req.getParameter("title");
			List<ProductVO> mainlist = SERVICE.getSpecialClassByMainCategory(title);
			req.getSession().setAttribute("mainlist", mainlist);
			req.getRequestDispatcher("../nest-frontend/HomePage.jsp").forward(req, res);
		}
	
	}


}
