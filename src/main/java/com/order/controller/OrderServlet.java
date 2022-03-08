package com.order.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.cart.model.JedisCartListService;
import com.members.model.MembersVO;
import com.order.model.*;
import com.orderDetail.model.OrderDetailVO;
import com.product.model.*;



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
				// 開始查詢資料
				OrderService orderSvc1 = new OrderService();
				OrderVO orderVO = orderSvc1.cancelOrder(orderId);
				// 取消完成，返回頁面
				String url = "accountCenter.jsp";
				res.sendRedirect(url);

				// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("orderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("confirmOrder".equals(action)) { // 來自orderCheckout.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/******************** 接收請求參數，進行錯誤處理 ********************/
				String name = req.getParameter("receiver");
				if (name == null || (name.trim()).length() == 0) {
					errorMsgs.add("請輸入收件人姓名");
				}
				
				String email = req.getParameter("email");
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.add("請輸入email");
				}
				
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				String zipcode = req.getParameter("zipcode");
				String road = req.getParameter("road");
				System.out.println(road);
				Integer shippingId = Integer.valueOf(req.getParameter("shipping"));
				if (shippingId == 1) {
					if (road == null || (road.trim()).length() == 0) {
						errorMsgs.add("選擇宅配到府，請務必輸入完整收件地址");
					}
				}
				
				String mobile = req.getParameter("email");
//				String mobileReg = "^[0-9]$"; 
				if (mobile == null || (mobile.trim()).length() == 0) {
					errorMsgs.add("請輸入聯絡電話");
				} 
//				else if (!mobile.trim().matches(mobileReg)) {
//					errorMsgs.add("電話輸入格式有誤，請重新輸入");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("name", name);
					req.setAttribute("email", email);
					req.setAttribute("county", county);
					req.setAttribute("district", district);
					req.setAttribute("zipcode", zipcode);
					req.setAttribute("road", road);
					req.setAttribute("mobile", mobile);
					RequestDispatcher failureView = req
							.getRequestDispatcher("orderCheckout.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/******************** 開始成立訂單 ********************/
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO)session.getAttribute("MemberUsing");
				Integer memberId = membersVO.getMemberid();
				Integer busId = 0;
				Integer total = 0;
				
				List<String> checkoutList = (List<String>) session.getAttribute("list");
				List<OrderDetailVO> orderList = new ArrayList<OrderDetailVO>();
				for (int i = 0; i < checkoutList.size(); i++) {
					JSONObject product;
					try {
						product = new JSONObject(checkoutList.get(i));
						busId = Integer.valueOf(product.getString("busId"));
						Integer merId = Integer.valueOf(product.getString("merId"));
						Integer qty = Integer.valueOf(product.getString("qty"));
						Integer price = Integer.valueOf(product.getString("price"));
						total += (qty * price);

						OrderDetailVO orderDetailVO = new OrderDetailVO();
						orderDetailVO.setMerId(merId);
						orderDetailVO.setQty(qty);
						orderDetailVO.setUnitPrice(price);
						orderDetailVO.setRanking(null);
						orderDetailVO.setComment("");
						orderList.add(orderDetailVO);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				OrderVO orderVO = new OrderVO();
				orderVO.setMemberId(memberId);
				orderVO.setBusId(busId);
				orderVO.setOrderTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
				orderVO.setOrderSum(total);
				orderVO.setPaymentId(Integer.valueOf(req.getParameter("payment")));
				orderVO.setShippingId(shippingId);
				orderVO.setOrderStatus(1);
				orderVO.setReceiver(name);
				orderVO.setReceiverPhone(mobile);
				if (shippingId == 1) {
					orderVO.setReceiverAddr(zipcode + county + district + road);
				} else {
					orderVO.setReceiverAddr("");
				}
				
				OrderDAO dao = new OrderDAO();
				String new_orderId = dao.insertWithOrderDetail(orderVO, orderList);
				// 訂單成立後
				// 移除 session 的結帳列表
				session.removeAttribute("list");
				// 從 Redis 移除已購買商品
				List<String> cartlist = JedisCartListService.getCartList(memberId.toString());
				JedisCartListService.deleteCartListbyBusId(memberId.toString(), cartlist, busId.toString());
				// 扣庫存
				ProductDAO prdDao = new ProductDAO();
				for (int i = 0; i < orderList.size(); i++) {
					OrderDetailVO orderDetailVO = orderList.get(i);
					prdDao.updateMerStockQty(orderDetailVO.getQty(), orderDetailVO.getMerId());
				}
				
				/******************** 訂單成立，準備轉交 ********************/
				req.setAttribute("new_orderId", new_orderId);
				req.setAttribute("orderVO", orderVO);
				req.setAttribute("orderList", orderList);
				String url = "invoice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/******************** 其他可能的錯誤處理 ********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("orderCheckout.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
