package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;

public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 商家訂單管理頁面資料修改
				if ("get_Ord_Update".equals(action)) { // 來自orderManage的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to send the ErrorPage
					// view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						// 接收請求參數
						// 取得訂單id
						Integer orderId = new Integer(req.getParameter("orderId"));
						// 開始查詢資料
						OrderService ordSvc = new OrderService();
						OrderVO ordVO = ordSvc.getOneByOrderId(orderId);
						// 查詢完成，準備轉交
						req.setAttribute("orderVO", ordVO); // 資料庫取出ordVO物件,存入req
						String url = "/nest-backend/orderDetail.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交頁面
						successView.forward(req, res);

						// 其他錯誤處理
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/orderManage.jsp");
						failureView.forward(req, res);
					}
				}
		
	}

}
