package com.csdetail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commonUtil.SendMail;
import com.members.model.MembersVO;

/**
 * Servlet implementation class sendMailServlet
 */
@WebServlet("/nest-backend/CsMailServlet")
public class CsMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		String snederName = req.getParameter("memberName");
		String mailContent = req.getParameter("mailContent");
		
		
//		==== if need to get manberVO use session to get it. ===
//		MembersVO mb = (MembersVO) req.getSession().getAttribute("MemberUsing");
		if ("sendmail".equals(action)) {
			String content = "會員【" + snederName + "】的回應訊息：\r\n"
					+ "\r\n"
					+ mailContent;
//					+ "📣🇯🇵日本、🇰🇷韓國、🇮🇩印尼、🇵🇭菲律賓、🇻🇳越南📣🇹🇭泰國/東南亞食品代購/台灣南北雜貨。\r\n"
//					+ "📣居家生活百貨類。\r\n"
//					+ "\r\n"
//					+ "🛒想買、想賣、批發都歡迎本賣場商品皆為現貨，\r\n"
//					+ "     歡迎聊聊詢問。\r\n"
//					+ "\r\n"
//					+ "📣營業時間週一-週日 08：00-22：000";
			SendMail sendmail = new SendMail("gooberlul@gmail.com", "[Petting客服郵件] 來自會員【" + snederName + "】的訊息", content);
			sendmail.send();
			req.getRequestDispatcher("../nest-frontend/HomePage.jsp").forward(req, res);
		}
	}


}
