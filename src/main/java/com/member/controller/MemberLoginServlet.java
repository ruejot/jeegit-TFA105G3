package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.swing.JOptionPane;

import com.member.model.MemberVO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberDAO;

@WebServlet("/member/MemberLogin")
public class MemberLoginServlet extends HttpServlet {
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

		// 登入
		if ("login".equals(action)) { // login.jsp裡的請求
			MemberDAO_interface memberDAOInterface = new MemberDAO();
			MemberVO memberbean = memberDAOInterface.selectByEmailAndPassword(email, password);

			// 若email有get到資料庫中相對應的email跟password，登入成功，否則登入失敗
			if (memberbean != null) {
				req.changeSessionId();// 為了資安考量，通常會在登入成功後再產製一個新的session

				HttpSession session = req.getSession();
				session.setAttribute("MemberUsing", memberbean);
				//將該個人會員的memberbean已經登入過的紀錄存入session，並取key名為MemberUsing
				//若之後要引用該個人會員的資料，直接在該頁面jsp檔用el取值即可(例如需要該個人會員的名稱就寫${MemberUsing.name})
				
///				req.getRequestDispatcher("memberIndex.jsp").forward(req, res);
				res.sendRedirect("../views/nest-frontend/HomePage.jsp");

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
//				req.getRequestDispatcher("../views/Memberlogin.jsp").forward(req, res);
			}
			return;
		}
	}
}
