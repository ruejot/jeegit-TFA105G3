package com.cart.controller;

import java.io.IOException;
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

import com.cart.model.*;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String memberId = (String) session.getAttribute("memberId");
		List<String> cartlist = JedisCartListService.getCartList(memberId);
		String action = req.getParameter("action");
		
		if (!action.equals("checkout")) {
			
			// 刪除商品
			if (action.equals("delete")) {
				String merId = (String) req.getParameter("del");
				try {
					JedisCartListService.deleteCartList(memberId, cartlist, merId);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// 新增商品到購物車中
			else if (action.equals("add")) {
				boolean match = false;
				
				// 取得新增商品
				JSONObject addProducts = null;
				try {
					addProducts = getProducts(req);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 新增第一項商品到購物車時
				if(cartlist == null) {
					try {
						JedisCartListService.addCartList(memberId, addProducts);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// 假如新增商品購物車裡已經有了
					String qty = req.getParameter("qty");
					String merId = req.getParameter("merId");
					try {
						match = JedisCartListService.updateCartList(memberId, cartlist, merId, qty, match);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// 假如新增商品購物車裡沒有(回傳的 match 沒有變 true)
					if (!match) {
						try {
							JedisCartListService.addCartList(memberId, addProducts);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
				}
			}
			List<String> savelist = JedisCartListService.getCartList(memberId);
			session.setAttribute("cart", savelist);
			String url = req.getRequestURI();
			res.sendRedirect(url);
		}		
		// 結帳
		// 計算購物車總價
		else if (action.equals("checkout")) {
			int total = 0;
			for (int i = 0; i < cartlist.size(); i++) {
				JSONObject product;
				try {
					product = new JSONObject(cartlist.get(i));
					Integer qty = Integer.valueOf(product.getString("qty"));
					Integer price = Integer.valueOf(product.getString("price"));
					
					total += (price*qty);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "shopCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private JSONObject getProducts(HttpServletRequest req) throws JSONException {
		String qty = req.getParameter("qty");
		String merId = req.getParameter("merId");
		String busId = req.getParameter("busId");
		String price = req.getParameter("price");
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("merId", merId);
		jsonObject.put("busId", busId);
		jsonObject.put("qty", qty);
		jsonObject.put("price", price);
		
		return jsonObject;
	}

}
