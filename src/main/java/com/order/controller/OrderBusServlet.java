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
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/nest-backend/orderDetail.do")
public class OrderBusServlet extends HttpServlet {

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
		
				// 商家訂單管理頁面查詢
				if ("get_Ord_Detail".equals(action)) { // 來自orderManage的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to send the ErrorPage
					// view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						// 接收請求參數
						// 取得訂單id
						Integer orderId = Integer.parseInt(req.getParameter("orderId"));
						// 開始查詢資料
						OrderService ordSvc = new OrderService();
						OrderVO orderVO = ordSvc.getOneByOrderId(orderId);
						// 查詢完成，準備轉交
						req.setAttribute("orderVO", orderVO); // 資料庫取出ordVO物件,存入req
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
				
				// 商家訂單管理頁面資料修改
				if ("get_Ord_Update".equals(action)) { // 來自orderManage的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to send the ErrorPage
					// view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						// 接收請求參數
						// 取得訂單Id
						Integer orderId = Integer.parseInt(req.getParameter("orderId"));
						// 開始查詢資料
						OrderService ordSvc = new OrderService();
						OrderVO orderVO = ordSvc.getOneByOrderId(orderId);
						// 查詢完成，準備轉交
						req.setAttribute("orderVO", orderVO); // 資料庫取出ordVO物件,存入req
						String url = "/nest-backend/orderRevise.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交頁面
						successView.forward(req, res);

						// 其他錯誤處理
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/orderManage.jsp");
						failureView.forward(req, res);
					}
				}
				
				// 商品資料更新
				if ("update".equals(action)) { // 來自update_pro_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to send the ErrorPage views.
					req.setAttribute("errorMsgs", errorMsgs);
//					try {
						// 接收請求參數
						// 取得訂單Id
						Integer orderId = Integer.parseInt(req.getParameter("orderId"));
						
						//取得訂單狀態
						Integer orderStatus = Integer.parseInt(req.getParameter("orderStatus"));
						
						//取得Tracking
						Integer tracking = null;
						String string = req.getParameter("tracking");
						try {
							tracking = Integer.parseInt(req.getParameter("tracking"));
						} catch (Exception e) {
							System.out.println("tracking not number");
						}
						
						
						//取得發票號碼
						String invoiceId = req.getParameter("invoiceId");
						
						//取得Receiver Info
						String receiver = req.getParameter("receiver");
						if (receiver == null) {
							errorMsgs.add("請填收件人");
						}
						
						String receiverAddr = req.getParameter("receiverAddr");
						if (receiverAddr == null) {
							errorMsgs.add("請填收件地址");
						}
						
						String receiverPhone = req.getParameter("receiverPhone");
						if (receiverPhone == null) {
							errorMsgs.add("請填收件人電話");
						}

						// 將新物件存入OrderVO
						OrderVO orderVO = new OrderVO();
						orderVO.setOrderId(orderId);
						orderVO.setTracking(tracking);
						orderVO.setOrderStatus(orderStatus);
						orderVO.setInvoiceId(invoiceId);
						orderVO.setReceiver(receiver);
						orderVO.setReceiverAddr(receiverAddr);
						orderVO.setReceiverPhone(receiverPhone);

						// Send the user back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("ordVO", orderVO);// 含有輸入格式錯誤的proVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/orderRevise.jsp");
							failureView.forward(req, res);
							return;
						}

						// 開始修改資料
						OrderService ordSvc = new OrderService();
						orderVO = ordSvc.updateOrder(orderId, tracking, orderStatus, invoiceId, receiver, receiverAddr, receiverPhone);

						// 修改完成，準備轉交
						req.setAttribute("orderVO", orderVO);// 資料庫update成功後,正確的的ordVO物件,存入req
						String url = "/nest-backend/orderManage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						// 其他可能錯誤處理
//					} catch (Exception e) {
//						RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/update_pro_input.jsp");
//						failureView.forward(req, res);
//					}

				}
	}

}
