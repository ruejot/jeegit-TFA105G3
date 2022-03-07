package com.members.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		Integer id = Integer.parseInt(req.getParameter("membersMemberid"));
		String newname = req.getParameter("membersName");
		String newmobile = req.getParameter("membersMobile");
		String newphone = req.getParameter("membersPhone");
		String newaddress = req.getParameter("membersAddress");
		Timestamp date = Timestamp.valueOf(req.getParameter("membersDate"));
		String email = req.getParameter("membersEmail");
		String newnickname = req.getParameter("membersNickname");
		String newintro = req.getParameter("membersIntro");

		String old_MembersPwd = req.getParameter("old_MembersPwd"); // (使用者輸入的)舊密碼


		// 按出修改會員資料的鈕
		if ("membersdataupdate".equals(action)) { // accountCenter.jsp裡的請求
			// 撈DB的資料，查詢該會員的所有資料，看輸入之email跟舊密碼，是否可在DB上GET到
			MembersDAO_interface membersDAOInterface = new MembersDAO();
			MembersVO memberbean = membersDAOInterface.selectByEmailAndPassword(email, old_MembersPwd);
			// 若有membersbean表示密碼有輸入正確
			if (memberbean != null) {
				// 必填輸入欄的資料有符合格式的話，client端所填之內容可進入DB更新
				if (!"null".equals(newname) && !"null".equals(newmobile)) {

					MembersService memberssvc = new MembersService();// 修改方法放在service所以用service
//					先成立新物件MembersVO()
					MembersVO membersVO = new MembersVO();

					// 並將client端輸入的資料set進去MembersVO()
					membersVO.setMemberid(id);
					membersVO.setName(newname);
					membersVO.setMobile(newmobile);
					membersVO.setPhone(newphone);
					membersVO.setAddress(newaddress);
					membersVO.setEmail(email);
					membersVO.setPassword(old_MembersPwd);
					membersVO.setNickname(newnickname);
					membersVO.setIntro(newintro);

					// 修改完成，存入
					// updateMember(String name, String mobile, String phone,String address,
					// Timestamp date
//					, String email, String password, String nickname, String intro, byte[] photo)
					// String, String, String, String, Timestamp, String, String, String, String,
					// byte[]

					// 只有10個欄位，沒有圖片。 Service、DAO、SQL(private static final String UPDATE)語法也有對應拿掉。
					// java.sql.Timestamp.valueOf("2022-02-10 12:12:12")
					membersVO = memberssvc.updateMember(id, newname, newmobile, newphone, newaddress, date, email,
							old_MembersPwd, newnickname, newintro, null);
//					updateMember(newname,newmobile,newmobile,newaddress,null, email,password,newnickname,newintro,null);

					// 資料庫update成功後,正確的的membersVO物件,存入req
					req.setAttribute("membersVO", membersVO);

					// 跳轉顯示修改成功
					req.setAttribute("DataupdateSuccessMembersMsg1", "會員資料修改成功!!");
					req.getRequestDispatcher("../nest-frontend/aaccountSetting.jsp").forward(req, res);

				} else {
					// 必填欄位(姓名跟手機)有未填寫者
					req.setAttribute("warningDataMembersMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
					req.getRequestDispatcher("../nest-frontend/accountSetting.jsp").forward(req, res);

				}

			} else {
				req.setAttribute("errMembersPWMsg", "不好意思!您密碼輸入錯誤!需輸入正確密碼後方可儲存修改資料!!");
				req.getRequestDispatcher("../nest-frontend/accountSetting.jsp").forward(req, res);
			}
			//
			// 再檢查被輸入的mobile是否符合規定的格式
//			if(newmobile.matches(mobileReg)) {
			// 手機號碼的格式
//			String mobileReg = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$"; 

			// 先檢查必填欄位(姓名跟手機)是否有被確實輸入
			// 若二欄均非null
			// }else {
//
//						req.setAttribute("warningDataMembersMsg1", "不好意思!這不是個合格的手機號碼格式，請確實填寫，謝謝!!");
//						req.getRequestDispatcher("../nest-frontend/accountSetting.jsp").forward(req, res);

//			}

		}

//		// 修改密碼
//		if ("changepw".equals(action)) {
//			//檢查三欄是否都有填寫
//			if(!"password".equals(null) && !"newpassword".equals(null) && !"newpasswordrp".equals(null)) {
//				
//				MembersDAO_interface memberDAOInterface = new MembersDAO();// 查帳號密碼方法放在DAO所以用DAO
////				
//				MembersVO memberbean = memberDAOInterface.selectByEmailAndPassword(newemail, old_MembersPwd);
//				
//				// 若email有get到資料庫中相對應的email跟password，表示client端的email跟password有match到
//				if (memberbean != null) {
//					//檢查新密碼是否輸入兩次都一致
//					if(!"newpassword".equals(newpasswordrp)) {
//						req.setAttribute("warningMembersPWMsg", "兩次密碼輸入不一致，請重新填寫，謝謝!!");
//						req.getRequestDispatcher("../nest-frontend/membersChangePassword.jsp").forward(req, res);
//						
//					}else {
//						
//						MembersService memberssvc = new MembersService();// 修改方法放在service所以用service
////						先成立新物件MembersVO()
//						MembersVO membersVO = new MembersVO();
//						
//						//並將client端輸入的資料set進去MembersVO()
//						membersVO.setPassword(newpassword);
//						
//						memberssvc.updateMember(null, newname, newmobile, newphone, newaddress, null, newemail, newpassword, newnickname, newpassword, null);
//						
//						req.setAttribute("MembersPWupdateMsg", "密碼修改成功!!");
//						req.getRequestDispatcher("../nest-frontend/accountSetting.jsp").forward(req, res);
//						
//						
//					}
//					
//				}else{
//					
//				}
//				
//				
//				
//				
//				
//			}else{
//				req.setAttribute("warningMembersPWMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
//				req.getRequestDispatcher("../nest-frontend/membersChangePassword.jsp").forward(req, res);
//			}
//			return;
//		}

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
