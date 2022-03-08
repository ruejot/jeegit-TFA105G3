package com.csdetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bus.model.BusService;
import com.bus.model.BusVO;
import com.csdetail.model.CsDetailService;
import com.csdetail.model.CsDetailVO;

@WebServlet("/nest-backend/CsServletOnlyMember.do")
public class CsServletOnlyMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
//	private CsDetailService csDetailService;
//	@Override
//	public void init() throws ServletException {
//		csDetailService = new CsDetailService();
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");


		// 新增一筆
		if ("insert".equals(action)) {

			// List存錯誤訊息(LinkedList可變長度，允許刪中間元素)
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			// === 1 CsDetail bean ACTION need these columns
			// Integer memberid = null;
			// Integer busid = null;
			// Integer merid = null;
			// Integer orderid = null;
			// java.sql.Date casetime = null;
			// String feedback = null;
			// Integer replystatus = null;
			// String replycontent = null;
			// java.sql.Date replytime = null;

			// 會員ID(回報者)
			Integer memberid = null;
//			memberid = 1; //(測試用)
			try {
				memberid = Integer.parseInt(req.getParameter("member_id"));
			} catch (NumberFormatException e) {
				errorMsgs.add("CsDetail_memberid是數字形式");
			}

			// 商家ID
			Integer busid = null;
//			busid = 5; //(測試用)
			try {
//				System.out.println(Integer.parseInt(req.getParameter("bus_id")));
				busid = Integer.parseInt(req.getParameter("bus_id"));
			} catch (NumberFormatException e) {
				errorMsgs.add("CsDetail_busid是數字形式");
			}
			
			// 商品ID
			// 無法為null，先設定到一個大數
			Integer merid = 1111111;
			
			// 訂單ID
			// 無法為null，先設定到一個大數
			Integer orderid = 1111111;
			
			// 立案時間
			java.sql.Date casetime = null;
			try {
				casetime = java.sql.Date.valueOf(req.getParameter("case_time").trim());
			} catch (IllegalArgumentException e) {
				// 設定為NOW()
				casetime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("這是立案時間唷!");
			}

			// 取得CS意見內容
			String feedback = null;
			feedback = req.getParameter("feedback").trim();
			if (feedback == null || feedback.trim().length() == 0) {
				errorMsgs.add("留言內容必填!");
			}
			
			// 可以null，就不需要吧
//			try {
//				feedback = req.getParameter("feedback").trim();
//				
//			} catch (Exception e) {
//				errorMsgs.add("留言內容必須填寫!");
//			}

			// 取得上架狀態
			Integer replystatus = 1;
//			try {
//				replystatus = Integer.parseInt(req.getParameter("replystatus"));
//			} catch (Exception e) {
//				// 預設值，設定為1
//				replystatus = 1;
//			}

			// 取得回覆內容
			String replycontent = null;
//			replycontent = req.getParameter("replycontent").trim();
			// 可以null，就不需要吧
//			if (replycontent == null || replycontent.trim().length() == 0) {
//				errorMsgs.add("請填意見內容唷!");
//			}

			// 回覆時間
			java.sql.Date replytime = null;
//			try {
//				replytime = java.sql.Date.valueOf(req.getParameter("replytime").trim());
//			} catch (IllegalArgumentException e) {
//				// 設定為NOW()
//				replytime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("這是立案時間唷!");
//			}

			// 將新物件存入CsDetailVO
			CsDetailVO csDetailVO = new CsDetailVO();
			csDetailVO.setMemberid(memberid);
			csDetailVO.setBusid(busid);
			csDetailVO.setMerid(merid);
			csDetailVO.setOrderid(orderid);
			csDetailVO.setCasetime(casetime);
			csDetailVO.setFeedback(feedback);
			csDetailVO.setReplystatus(replystatus);
//			csDetailVO.setReplycontent(replycontent);
//			csDetailVO.setReplytime(replytime);

			// 到此接收完成，如果有錯誤訊息，就回傳
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("csDetailVO", csDetailVO);// 含有輸入格式錯誤的proVO物件,也存入req
				
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/fendcs_reply.jsp");
				failureView.forward(req, res);
				return;
			}

			// 開始新增資料
			// replycontent 為 null
			// replytime 為 null
			CsDetailService csDeSvc = new CsDetailService();
			csDetailVO = csDeSvc.insertCsDetail(memberid, busid, merid, orderid, casetime, feedback, replystatus, null, null);

			// 填充完成，準備轉跳頁面
			// 這裡將來可以用，記錄前一頁面，回到前一頁的寫法。
			String url = "/nest-frontend/fendshop_mainpage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}


	}

}
