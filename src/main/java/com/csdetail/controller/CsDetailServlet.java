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
import com.order.model.OrderService;
import com.order.model.OrderVO;

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

		
		// fendshop_mainpage選取目前busid，轉跳到fendcs_reply頁
		if ("from_shopmain_to_CsReply_with_Busid".equals(action)) {

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
				req.setAttribute("busVO_from_ShopMainpage", busVO); // 資料庫取出的busVO物件,存入req
				String url = "/nest-frontend/fendcs_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/fendshop_mainpage.jsp");
				failureView.forward(req, res);
			}
		}
		
		// fendshop_mainpage選取目前busid，轉跳到fendcs_reply頁
		if ("from_Order_to_CsReply".equals(action)) {

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
				req.setAttribute("busVO_from_ShopMainpage", busVO); // 資料庫取出的busVO物件,存入req
				String url = "/nest-frontend/fendcs_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/fendshop_mainpage.jsp");
				failureView.forward(req, res);
			}
		}
		
//		// 聯繫客服 (待測試)
//		if ("contact_Ord_Detail".equals(action)) { // 來自orderDetail的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to send the ErrorPage
//			// view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				// 接收請求參數
//				// 取得訂單id
//				Integer orderId = new Integer(req.getParameter("orderId"));
//				// 開始查詢資料
//				OrderService orderSvc = new OrderService();
//				OrderVO orderVO = orderSvc.getOneByOrderId(orderId);
//				// 查詢完成，準備轉交
//				req.setAttribute("orderVO", orderVO); // 資料庫取出orderVO物件,存入req
//				String url = "fendcs_reply.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交頁面
//				successView.forward(req, res);
//
//				// 其他錯誤處理
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("accountCenter.jsp");
//				failureView.forward(req, res);
//			}
//		}


	}

}
