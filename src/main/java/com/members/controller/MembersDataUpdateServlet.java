package com.members.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.swing.JOptionPane;

import com.members.model.MembersDAO;
import com.members.model.MembersDAO_interface;
import com.members.model.MembersVO;

@WebServlet("/members/MembersDataUpdate")
public class MembersDataUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// 修改會員資料
		if ("membersdataupdate".equals(action)) { // login.jsp裡的請求
			MembersDAO_interface memberDAOInterface = new MembersDAO();
			MembersVO memberbean = memberDAOInterface.selectByEmailAndPassword(email, password);

			// 若email有get到資料庫中相對應的email跟password，表示確有其會員
			if (memberbean != null) {
				
				
///				req.getRequestDispatcher("memberIndex.jsp").forward(req, res);
				res.sendRedirect("../nest-frontend/HomePage.jsp");

//			      try {                                                        
//			          String location = (String) session.getAttribute("location");
//			          if (location != null) {
//			            session.removeAttribute("location");   //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//			            res.sendRedirect(location);            
//			            return;
//			          }
//			        }catch (Exception ignored) { }
//			      
//			      
			} else {
//				將錯誤訊息("資料有誤，請重新輸入!!")取key名為errMsg，放在login.jsp頁面上，以${errMsg}呈現
				req.setAttribute("errMsg", "資料有誤，請重新輸入!!");
				req.getRequestDispatcher("../nest-frontend/Login.jsp").forward(req, res);
							
				//方法2.彈跳提醒錯誤的視窗
//				JOptionPane.showMessageDialog(null, "資料有誤，請重新輸入", "Error", JOptionPane.ERROR_MESSAGE);
//				req.getRequestDispatcher("../nest-frontend/Login.jsp").forward(req, res);
			}
			return;
		}
	}
}
