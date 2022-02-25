package com.bus.controller;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BusLogout")
public class BusLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");
//
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");

		// 登出
		if ("logout".equals(action)) { // sellerHeaderLogged.jsp裡的請求
			
//			HttpSession session = req.getSession();
//			session.invalidate();	
			req.getSession().invalidate(); //消除session
			
//			res.sendRedirect("./views/login.jsp");


		}
	}
}
