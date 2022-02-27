package com.bus.controller;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.swing.JOptionPane;

import com.bus.model.BusVO;
import com.bus.model.BusDAO_interface;
import com.bus.model.BusDAO;

@WebServlet("/bus/BusLogin")
public class BusLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String busaction = req.getParameter("busaction");

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// 登入
		if ("login".equals(busaction)) { // login.jsp裡的請求
			BusDAO_interface busDAOInterface = new BusDAO();
			BusVO busbean = busDAOInterface.selectByEmailAndPassword(email, password);	
//			System.out.println(busDAOInterface);
//			System.out.println(busbean);
			// 若email有get到資料庫中相對應的email跟password，登入成功，否則登入失敗
			if (busbean != null) {
				// 為了資安考量，通常會在登入成功後再產製一個新的session
				req.changeSessionId();
				//將該商業會員的busbean已經登入過的紀錄存入session，並取key名為BusUsing
				//若之後要引用該商業會員的資料，直接在該頁面jsp檔用el取值即可(例如需要該商業會員的名稱就寫${BusUsing.name})
				HttpSession session = req.getSession();
				session.setAttribute("BusUsing", busbean);

				res.sendRedirect("../views/nest-frontend/HomePage.jsp");
//				res.sendRedirect(req.getContextPath() + "/views/busHomePage.jsp");

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
				req.setAttribute("errMsg1", "資料有誤，請重新輸入!!");
				req.getRequestDispatcher("../nest-frontend/Login.jsp").forward(req, res);
				
				//方法2.彈跳提醒錯誤的視窗
//				JOptionPane.showMessageDialog(null, "資料有誤，請重新輸入", "Error", JOptionPane.ERROR_MESSAGE);
//				req.getRequestDispatcher("../views/busLogin.jsp").forward(req, res);
			}
			return;
		}

		// 檢查登入狀態
//		Optional<Object> token = Optional.ofNullable(req.getSession().getAttribute("BusID"));
//
//		if (token.isPresent()) {
//			userHtml(req, res);
//		} else {
//			res.sendRedirect("../views/busLogin.jsp");
//		}
//
//	}
//
//
//
//private void userHtml(HttpServletRequest req, HttpServletResponse res)
//        throws ServletException, IOException {
//	req.setCharacterEncoding("UTF-8");
//	res.setContentType("text/html;charset=utf-8");
//	
//    PrintWriter out = res.getWriter();
//    out.println("<!DOCTYPE html>");
//    out.println("<html>");
//    out.println("<head>");
//    out.println("<meta charset='UTF-8'>");
//    out.println("</head>");
//    out.println("<body>");
//    out.println("<h1>" + req.getSession().getAttribute("login") + "已登入</h1><br>");
//    out.println("<a href='logout'>登出</a>");
//    out.println("</body>");
//    out.println("</html>");
//    }

	}
}
