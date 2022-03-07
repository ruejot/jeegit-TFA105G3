package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;



@WebServlet("/nest-frontend/cartServlet.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO)session.getAttribute("MemberUsing");
		String memberId = membersVO.getMemberid().toString();
		System.out.println(memberId);
		List<String> cartlist = JedisCartListService.getCartList(memberId);
		System.out.println(cartlist);
		Map<String, List<ProductVO>> map = new LinkedHashMap<>();
	    Map<Integer, Integer> qtyMap = new LinkedHashMap<>();
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
						System.out.println("CS 81 ");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("CS 84 ");
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
						System.out.println("CS 100 ");
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
			System.out.println("CS 112 "+savelist);
			String url = req.getParameter("location");
			System.out.println("url "+url);
			res.sendRedirect(url);
		}		
		// 結帳
		// 計算購物車總價
		else if (action.equals("checkout")) {
			
			String busId = req.getParameter("busIdtoCheckout");
			List<String> checkoutList = new ArrayList<>();
			
			for (int i = 0; i < cartlist.size(); i++) {
				JSONObject product;
				try {
					product = new JSONObject(cartlist.get(i));
					if (product.getString("busId").equals(busId)) {
						checkoutList.add(product.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

			Integer total = 0;
			Integer amount = 0;
			for (int j = 0; j < checkoutList.size(); j++) {
				JSONObject product;
				try {
					product = new JSONObject(checkoutList.get(j));
					Integer qty = Integer.valueOf(product.getString("qty"));
					Integer price = Integer.valueOf(product.getString("price"));

					total += (price*qty);
					amount += qty;

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			Integer memberid = Integer.valueOf(memberId);
			MembersService memSvc = new MembersService();
			MembersVO member = memSvc.select(memberid);
			
			req.setAttribute("total", String.valueOf(total));
			req.setAttribute("amount", String.valueOf(amount));
			session.setAttribute("list", checkoutList);
			req.setAttribute("name", member.getName());
			req.setAttribute("email", member.getEmail());
			req.setAttribute("mobile", member.getMobile());
			String url = "orderCheckout.jsp";
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