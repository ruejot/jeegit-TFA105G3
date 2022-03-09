package com.bus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bus.model.BusService;
import com.bus.model.BusVO;
import com.commonUtil.ForgetPWMailService;

@WebServlet("/bus/BusForgetPassword")
public class BusForgetPWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		String email = req.getParameter("busEmail"); // 使用者輸入的email

		// 修改密碼
		if ("forgetpw".equals(action)) { // BusForgetPassword.jsp

			// 判斷輸入的email是否真有其busID跟EMAIL
			BusService bussvc = new BusService();// 查詢BusID+email方法放在service所以用service
			BusVO busbean = bussvc.selectBusIDEmail(email);
			System.out.println(busbean);

//			有從輸入的email get到busid表示該帳號確實為註冊的會員
			if (busbean != null) {

//				try {
					// 取得jsp傳進來的email取key值為email(前面已取)
//					req.setAttribute("email", email);
//					BusService bussvcJSP = new BusService();
					BusVO busbean2 = bussvc.selectBusIDEmail(email);
					
					// 亂數取一個新密碼(方法詳如下)
					String randompassword = getRandomString(8);
					// 取得這個Email的busid
					Integer busid = busbean2.getBusid();
					
					System.out.println("新的密碼："+randompassword);
					System.out.println("會員id："+busid);
					
					// 把亂數產生的新密碼更新至DB
					busbean2 = bussvc.updateBusPW(busid,randompassword);

					/****************************
					 * 3.寄送新密碼資料給使用者
					 *****************************************/
					String to = email;
					String subject = "【Petting】密碼通知";
					String ch_name = "親愛的【Petting】用戶(合作商家)您好~";
					String passRandom = randompassword;
					String messageText = "Hello! " + ch_name + "<br/>"+ "<br/>"+"&emsp;&emsp;"+" 請使用此密碼 :【 " + passRandom + "】"
							+ " 登入，提醒您盡快登入後更改 貴公司的密碼，謝謝!!";
					ForgetPWMailService busmailsvc = new ForgetPWMailService();
									//收件者	、主旨	、內容
					busmailsvc.sendMail(to, subject, messageText);

					/*************************** 4.修改完成,準備轉交(Send the Success view) *************/
					req.setAttribute("BusForgetMsg", "驗證信已發送成功!!");
					req.getRequestDispatcher("../nest-backend/busForgetPassword.jsp").forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
//				} catch (Exception e) {
//					req.setAttribute("failureBusPWMsg", "修改失敗："+e.getMessage());
//					req.getRequestDispatcher("../nest-backend/busForgetPassword.jsp").forward(req, res);
//				}

			} else {
				req.setAttribute("warningBusNullMsg", "查無此註冊會員之資料，請再確認!謝謝!");
				req.getRequestDispatcher("../nest-backend/busForgetPassword.jsp").forward(req, res);

			}

		}

	}
	
	//產生亂數密碼的方法
	static String getRandomString(int i) {
		String theAlphaNumericS;
		StringBuilder builder;

		theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "_!@" + "0123456789";

		// create the StringBuffer
		builder = new StringBuilder(i);

		for (int m = 0; m < i; m++) {

			// generate numeric
			int myindex = (int) (theAlphaNumericS.length() * Math.random());

			// add the characters
			builder.append(theAlphaNumericS.charAt(myindex));
		}

		return builder.toString();
	}
	
	
	

}
