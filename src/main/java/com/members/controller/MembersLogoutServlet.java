package com.members.controller;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/members/MembersLogout")
public class MembersLogoutServlet extends HttpServlet {
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
		if ("logout".equals(action)) { // userHeaderLogged.jsp裡的請求
			
			req.getSession().invalidate(); //消除session
			
			res.sendRedirect("../nest-frontend/Login.jsp");


		}
	}
}
