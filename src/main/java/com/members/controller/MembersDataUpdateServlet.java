package com.members.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.swing.JOptionPane;

import com.members.model.MembersDAO;
import com.members.model.MembersDAO_interface;
import com.members.model.MembersService;
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

		String id = req.getParameter("membersID");
		String newname = req.getParameter("membersName");
		String newnickname = req.getParameter("membersNickname");
		String email = req.getParameter("membersEmail");
		String newmobile = req.getParameter("membersMobile");
		String newphone = req.getParameter("membersPhone");
		String password = req.getParameter("membersPassword");			//現在的密碼
		String newaddress = req.getParameter("membersAddress");			//新的密碼
		String newpassword = req.getParameter("newnMembersPassword");
		String newpasswordrp = req.getParameter("newMembersPasswordRp");

		// 按出修改會員資料的鈕
		if ("membersdataupdate".equals(action)) { // accountCenter.jsp裡的請求
			// 撈DB的資料，查詢該會員的所有資料
			MembersDAO_interface membersDAOInterface = new MembersDAO();
			
			// 找出該會員的所有資料by id↓
			MembersVO membersbean = membersDAOInterface.select(id);
			
			//手機號碼的格式
			String mobileReg = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$"; 
			
			// 先檢查必填欄位們是否有被確實輸入
			if("newname".equals(null) || "newmobile".equals(null)) {
				
				req.setAttribute("warningDataMembersMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
				req.getRequestDispatcher("../nest-frontend/accounSetting.jsp").forward(req, res);
				
				//再檢查被輸入的mobile是否符合規定的格式
				}else if(newmobile.matches(mobileReg)) {
					
					//符合格式者，client端所填之內容可進入DS更新
					MembersService memberssvc = new MembersService();// 修改方法放在service所以用service
//					先成立新物件MembersVO()
					MembersVO membersVO = new MembersVO();
					
					//並將client端輸入的資料set進去MembersVO()
					membersVO.setName(newname);
					membersVO.setNickname(newnickname);
					membersVO.setMobile(newmobile);
					membersVO.setPhone(newphone);
					membersVO.setEmail(email);
					
					//修改完成，存入
					memberssvc.updateMember(newname, newmobile, newphone, newaddress, null, email, password,
							newnickname, newpasswordrp, null);
					
					// 跳轉顯示修改成功	
					req.setAttribute("DataupdateSuccessMembersMsg1", "會員資料修改成功!!");
					req.getRequestDispatcher("../nest-frontend/accounSetting.jsp").forward(req, res);
								
					
					}else {
						req.setAttribute("warningDataMembersMsg1", "不好意思!這不是個合格的手機號碼格式，請確實填寫，謝謝!!");
						req.getRequestDispatcher("../nest-frontend/accounSetting.jsp").forward(req, res);
				
				}
				
				
				return;
				
				
				
			}
			
		// 修改密碼
		if ("changepw".equals(action)) {
			//檢查三欄是否都有填寫
			if(!"password".equals(null) && !"newpassword".equals(null) && !"newpasswordrp".equals(null)) {
				
				//檢查新密碼是否輸入兩次都一致
				if(!"newpassword".equals(newpasswordrp)) {
					req.setAttribute("warningMembersPWMsg", "兩次密碼輸入不一致，請重新填寫，謝謝!!");
					req.getRequestDispatcher("../nest-frontend/membersChangePassword.jsp").forward(req, res);
					
				}else {
					MembersService memberssvc = new MembersService();// 修改方法放在service所以用service
//					先成立新物件MembersVO()
					MembersVO membersVO = new MembersVO();
					
					//並將client端輸入的資料set進去MembersVO()
					membersVO.setPassword(newpassword);
					
					memberssvc.updateMember(newname, newmobile, newphone, newaddress, null, email, newpassword, newnickname, newpassword, null);
					
					req.setAttribute("MembersPWupdateMsg", "密碼修改成功!!");
					req.getRequestDispatcher("../nest-frontend/accounSetting.jsp").forward(req, res);
					
					
				}
				
				
				
			}else{
				req.setAttribute("warningMembersPWMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
				req.getRequestDispatcher("../nest-frontend/membersChangePassword.jsp").forward(req, res);
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
//					java.sql.Date shelfDate =  java.sql.Date.valueOf(req.getParameter("shelfDate").trim());
//
//					// 取得商品價格
//					Integer price = null;
//					try {
//						price = Integer.parseInt(req.getParameter("price").trim());
//					} catch (NumberFormatException e) {
//						price = 0;
//						errorMsgs.add("金額請填數字!");
//					}
//
//
//				
//
//
//
//					// 到時需從登入頁面getSession取得busid(要改寫)
//					Integer busid = 1; 
////					try {
////					busid = new Integer(req.getParameter("busid"));
////					} catch(NumberFormatException e) {
////						errorMsgs.add("請填數字");
////					}
//					

	

