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

@WebServlet("/nest-backend/CsDetail.do")
public class CsDetailServlet extends HttpServlet {
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

		// bendscdetail_listall選取一項，轉跳到bendcsdetail_update編輯頁
		if ("getOne_For_Update".equals(action)) { // 來自bendcsdetail_listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer caseid = Integer.parseInt(req.getParameter("caseid"));
				//= new Integer(req.getParameter("caseid"));

				/*************************** 2.開始查詢資料 ****************************************/
				CsDetailService csDetailSvc = new CsDetailService();
				CsDetailVO csDetailVO = csDetailSvc.selectByCaseId(caseid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("csDetailVO", csDetailVO); // 資料庫取出的csDetailVO物件,存入req
				String url = "/nest-backend/bendcsdetail_updateone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		// fendshop_mainpage選取目前busid，轉跳到fendcs_reply頁
		if ("set_Cs_By_Busid".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer busid = Integer.parseInt(req.getParameter("busid"));
				//= new Integer(req.getParameter("caseid"));

				/*************************** 2.開始查詢資料 ****************************************/
				BusService busSvc = new BusService();
				BusVO busVO = busSvc.select(busid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("busVO", busVO); // 資料庫取出的busVO物件,存入req
				String url = "/nest-frontend/fendcs_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

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
			memberid = 1;
//			try {
//				memberid = new Integer(req.getParameter("memberid"));
//			} catch (NumberFormatException e) {
//				errorMsgs.add("CsDetail_memberid是數字形式");
//			}

			// 商家ID
			Integer busid = null;
			busid = 5;
//			try {
//				memberid = new Integer(req.getParameter("busid"));
//			} catch (NumberFormatException e) {
//				errorMsgs.add("CsDetail_busid是數字形式");
//			}
			
			// 商品ID
			Integer merid = null;
			
			// 訂單ID
			Integer orderid = null;

			// 立案時間
			java.sql.Date casetime = null;
			try {
				casetime = java.sql.Date.valueOf(req.getParameter("casetime").trim());
			} catch (IllegalArgumentException e) {
				// 設定為NOW()
				casetime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("這是立案時間唷!");
			}

			// 取得CS意見內容
			String feedback = null;
			feedback = req.getParameter("feedback").trim();
			// 可以null，就不需要吧
//			if (feedback == null || feedback.trim().length() == 0) {
//				errorMsgs.add("請填意見內容唷!");
//			}

			// 取得上架狀態
			Integer replystatus = null;
			replystatus = new Integer(req.getParameter("replystatus"));

			// 取得回覆內容
			String replycontent = null;
			replycontent = req.getParameter("replycontent").trim();
			// 可以null，就不需要吧
//			if (replycontent == null || replycontent.trim().length() == 0) {
//				errorMsgs.add("請填意見內容唷!");
//			}

			// 回覆時間
			java.sql.Date replytime = null;
			try {
				replytime = java.sql.Date.valueOf(req.getParameter("replytime").trim());
			} catch (IllegalArgumentException e) {
				// 設定為NOW()
				replytime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("這是立案時間唷!");
			}

			// 將新物件存入CsDetailVO
			CsDetailVO csDetailVO = new CsDetailVO();
			csDetailVO.setMemberid(memberid);
			csDetailVO.setBusid(busid);
			csDetailVO.setCasetime(casetime);
			csDetailVO.setFeedback(feedback);
			csDetailVO.setReplystatus(replystatus);
			csDetailVO.setReplycontent(replycontent);
			csDetailVO.setReplytime(replytime);

			// 到此接收完成，如果有錯誤訊息，就回傳
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("csDetailVO", csDetailVO);// 含有輸入格式錯誤的proVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/fendcs_reply.jsp");
				failureView.forward(req, res);
				return;
			}

			// 開始新增資料
			CsDetailService csDeSvc = new CsDetailService();
			csDetailVO = csDeSvc.insertCsDetail(memberid, busid, merid, orderid, casetime, feedback, replystatus, replycontent, replytime);

			// 填充完成，準備轉跳頁面
			// 這裡將來可以用，記錄前一頁面，回到前一頁的寫法。
			String url = "/nest-frontend/fendshop_mainpage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// delete在servlet不實作，代表不允許刪除CsDetail紀錄的動作
//		if ("delete".equals(action)) {
//			
//		}

		//update一筆CsDetail
		if ("update".equals(action)) {
			// List存錯誤訊息
			// LinkedList可變長度，允許刪中間元素
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			// ===UPDATE 1 CsDetail bean ACTION need these columns
			// Integer caseid = null;
			// Integer memberid = null;
			// Integer busid = null;
			// java.sql.Date casetime = null;
			// String feedback = null;
			// Integer replystatus = null;
			// String replycontent = null;
			// java.sql.Date replytime = null;

			// 客服單編號
			Integer caseid = Integer.parseInt(req.getParameter("case_id"));

			// 會員ID(回報者)
			Integer memberid = Integer.parseInt(req.getParameter("member_id"));
//			memberid = 1;
//						try {
//							memberid = new Integer(req.getParameter("memberid"));
//						} catch (NumberFormatException e) {
//							errorMsgs.add("是數字形式");
//						}

			// 商家ID
			Integer busid = Integer.parseInt(req.getParameter("bus_id"));
//			busid = 5;
//						try {
//							memberid = new Integer(req.getParameter("busid"));
//						} catch (NumberFormatException e) {
//							errorMsgs.add("是數字形式");
//						}
			
			// 商品ID
			Integer merid = Integer.parseInt(req.getParameter("mer_id"));
			
			// 訂單ID
			Integer orderid = Integer.parseInt(req.getParameter("order_id"));
			
			// 立案時間
			java.sql.Date casetime = java.sql.Date.valueOf(req.getParameter("case_time").trim());
//			try {
//				casetime = java.sql.Date.valueOf(req.getParameter("case_time").trim());
//			} catch (IllegalArgumentException e) {
//				// 設定為NOW()
//				casetime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("這是立案時間唷!");
//			}

			// 取得CS意見內容
			String feedback = null;
			feedback = req.getParameter("feedback").trim();
			// 可以null，就不需要吧
//						if (feedback == null || feedback.trim().length() == 0) {
//							errorMsgs.add("請填意見內容唷!");
//						}

			// 取得上架狀態
			Integer replystatus = null;
			try {
				replystatus = Integer.parseInt(req.getParameter("reply_status"));
			} catch(NumberFormatException e) {
				errorMsgs.add("csDetail_請填上架狀態!");
			}

			// 取得回覆內容
			String replycontent = null;
			replycontent = req.getParameter("reply_content").trim();
			// 可以null，就不需要吧
//						if (replycontent == null || replycontent.trim().length() == 0) {
//							errorMsgs.add("請填意見內容唷!");
//						}

			// 回覆時間
			java.sql.Date replytime = null;
			try {
				replytime = java.sql.Date.valueOf(req.getParameter("reply_time").trim());
			} catch (IllegalArgumentException e) {
				// 設定為NOW()
				replytime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("這是立案時間唷!");
			}

			// 將update資訊存入指定orderID的CsDetailVO
			CsDetailVO csDetailVO = new CsDetailVO();
			csDetailVO.setCaseid(caseid);
			csDetailVO.setMemberid(memberid);
			csDetailVO.setBusid(busid);
			csDetailVO.setMerid(merid);
			csDetailVO.setOrderid(orderid);
			csDetailVO.setCasetime(casetime);
			csDetailVO.setFeedback(feedback);
			csDetailVO.setReplystatus(replystatus);
			csDetailVO.setReplycontent(replycontent);
			csDetailVO.setReplytime(replytime);

			// 到此接收完成，如果有錯誤訊息，就回傳
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("csDetailVO", csDetailVO);// 含有輸入格式錯誤的proVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/bendcsdetail_updateone.jsp");
				failureView.forward(req, res);
				return;
			}

			// 開始寫回某一caseid去update資料
			CsDetailService csDeSvc = new CsDetailService();
			csDetailVO = csDeSvc.updateCsDetail(caseid, memberid, busid, merid, orderid, casetime, feedback, replystatus, replycontent, replytime);

			// 填充完成，準備轉跳頁面
			// 這裡將來可以用，記錄前一頁面，回到前一頁的寫法。
			String url = "/nest-backend/bendcsdetail_listall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

}

//		if ("getOne_For_Display".equals(action)) {
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("caseid");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入客服單編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, resp);
//					return;//程式中斷
//				}
//				
//				
//				
//				
//				
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, resp);
//			}
//		}