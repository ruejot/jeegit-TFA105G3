package com.orderDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;

@WebServlet("/nest-frontend/orderDetail.do")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//會員中心
		//訂單明細查詢
		if ("get_Ord_Detail".equals(action)) { // 來自accountCenter的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求參數
				// 取得訂單id
				Integer orderId = new Integer(req.getParameter("orderId"));
				
				// 開始查詢資料
				OrderDetailService orderDetailSvc = new OrderDetailService();
				List<OrderDetailVO> orderDetailList = orderDetailSvc.getOrdersByOrderId(orderId);
				
				// 查詢完成，準備轉交
				req.setAttribute("orderDetailList", orderDetailList); // 資料庫取出orderDetail list物件,存入req
				String url = "orderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交頁面
				successView.forward(req, res);

				// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("accountCenter.jsp");
				failureView.forward(req, res);
			}
		
		}
	}

}

