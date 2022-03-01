package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;



@WebServlet("/nest-frontend/order.do")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//會員中心
		//訂單取消 (訂單狀態為處理中才可以進行的動作)
		if ("cancel_Ord".equals(action)) { // 來自accountCenter的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求參數
				// 取得訂單id
				Integer orderId = new Integer(req.getParameter("orderId"));
				
//				// 開始查詢資料
				OrderService orderSvc1 = new OrderService();
				OrderVO orderVO = orderSvc1.cancelOrder(orderId);
				
//				// 取消完成，準備轉交
				String url = "accountCenter.jsp";
				res.sendRedirect(url);

				// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("accountCenter.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
