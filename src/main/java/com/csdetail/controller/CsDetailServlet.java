package com.csdetail.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csdetail.model.*;

@WebServlet("/CsDetail.do")
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

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAll的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer caseid = new Integer(req.getParameter("caseid"));

				/*************************** 2.開始查詢資料 ****************************************/
				CsDetailService csDetailSvc = new CsDetailService();
				CsDetailVO csDetailVO = csDetailSvc.selectByCaseId(caseid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("csDetailVO", csDetailVO); // 資料庫取出的csDetailVO物件,存入req
				String url = "/nest-backend/BEnd_CsDetail_UpdateOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
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