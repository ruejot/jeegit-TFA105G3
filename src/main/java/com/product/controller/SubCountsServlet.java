package com.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.HomePageService;

@WebServlet("/HomePage")
public class SubCountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomePageService SERVICE;

	public SubCountsServlet() {
		SERVICE = new HomePageService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("counts".equals(action)) {
			String subCount = req.getParameter("subCount");
			Integer subCounts = SERVICE.getCountsBySubCategory(subCount);

			try {
				if (subCounts != null || subCounts > 1) {
					req.setAttribute("subCounts", subCounts);
					req.getRequestDispatcher("/HomePage.jsp").forward(req, res);
				}
			} catch (ServletException | IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}
}
