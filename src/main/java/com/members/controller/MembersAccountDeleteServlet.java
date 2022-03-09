package com.members.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.members.model.MembersDAO;
import com.members.model.MembersDAO_interface;
import com.members.model.MembersService;
import com.members.model.MembersVO;

@WebServlet("/members/MembersAccountDelete")
public class MembersAccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		Integer id = Integer.parseInt(req.getParameter("membersMemberid"));
		
		String password = req.getParameter("membersPassword"); 		// 現在的密碼
		String passwordrp = req.getParameter("membersPasswordRp");	// 確認密碼
		
//		沒有要改，直接在hidden的input上get到值↓
//		String name = req.getParameter("membersName");
//		String mobile = req.getParameter("membersMobile");
//		String phone = req.getParameter("membersPhone");
//		String address = req.getParameter("membersAddress");
//		Timestamp date = Timestamp.valueOf(req.getParameter("membersDate"));	
//		
		String email = req.getParameter("membersEmail");
//		String nickname = req.getParameter("membersNickname");
//		String intro = req.getParameter("membersIntro");
		

		// 刪除帳號
		if ("deleteMember".equals(action)) {
			// 檢查二欄是否都有填寫
			if (!"password".equals(null) && !"passwordRp".equals(null)) {

				MembersDAO_interface memberDAOInterface = new MembersDAO();// 查帳號密碼方法放在DAO所以用DAO
				MembersVO memberbean = memberDAOInterface.selectByEmailAndPassword(email, password);

				// 若email有get到資料庫中相對應的email跟password，表示client端的email跟password有match到
				if (memberbean != null) {

					// 檢查新密碼是否輸入兩次都一致，若一致，則可更新至DB
					if (password.equals(passwordrp)) {
											
						MembersService memberssvc = new MembersService();// 刪帳號方法放在service所以用service
						
						memberssvc.deleteMember(id);

						req.setAttribute("MembersDeleteMsg", "帳號已刪除成功!!");
						req.getSession().invalidate();
						req.getRequestDispatcher("../nest-frontend/membersAccDeletedSuccess.jsp").forward(req, res);
						

					} else {

						req.setAttribute("warningMemberPWDismatchMsg", "兩次密碼輸入不一致，請重新填寫，謝謝!!");
						req.getRequestDispatcher("../nest-frontend/membersAccountDelete.jsp").forward(req, res);

					}

				} else {
					req.setAttribute("memberPWErrMsg", "密碼輸入錯誤!!請再重新輸入謝謝");
					req.getRequestDispatcher("../nest-frontend/membersAccountDelete.jsp").forward(req, res);

				}

			} else {
				req.setAttribute("warningMembersDeleteMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
				req.getRequestDispatcher("../nest-frontend/membersAccountDelete.jsp").forward(req, res);
			}
			return;
		}

	}

}

//
//			try {
//				//取得membersID及其資料
//				Integer merid = Integer.parseInt(req.getParameter("merid").trim());
//				String name = req.getParameter("name");
//				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9\\s.)]{2,25}$";
//				if (name == null || name.trim().length() == 0) {
//						errorMsgs.add("請填寫商品名稱!");
//				} else if (!name.trim().matches(nameReg)) {
//						errorMsgs.add("商品名稱必須是中英文，且長度需在2-25之間!");
//				}
//
//				// 取得商品描述
//				String description = req.getParameter("description").trim();
//				if (description == null || description.trim().length() == 0) {
//						errorMsgs.add("請填寫商品描述!");
//				
//				
//				}catch{
//
//
//			}
//
//
//					

