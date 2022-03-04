package com.commonUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;

/**
 * Servlet implementation class sendMailServlet
 */
@WebServlet("/commonUtil/sendMailServlet")
public class HomepageSendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
//		==== if need to get manberVO use session to get it. ===
//		MembersVO mb = (MembersVO) req.getSession().getAttribute("MemberUsing");
		if ("sendmail".equals(action)) {
			String content = "📣歡迎光臨本賣場 專營：\r\n"
					+ "📣🇯🇵日本、🇰🇷韓國、🇮🇩印尼、🇵🇭菲律賓、🇻🇳越南📣🇹🇭泰國/東南亞食品代購/台灣南北雜貨。\r\n"
					+ "📣居家生活百貨類。\r\n"
					+ "\r\n"
					+ "🛒想買、想賣、批發都歡迎本賣場商品皆為現貨，\r\n"
					+ "     歡迎聊聊詢問。\r\n"
					+ "\r\n"
					+ "📣營業時間週一-週日 08：00-22：000";
			SendMail sendmail = new SendMail(req.getParameter("email"), "3/18 Petting購物網站特賣會", content);
			sendmail.send();
			req.getRequestDispatcher("../nest-frontend/HomePage.jsp").forward(req, res);
		}
	
	}


}
