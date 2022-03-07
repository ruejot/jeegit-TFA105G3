package com.cart.controller;

import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.bus.model.*;
import com.cart.model.*;
import com.members.model.*;
import com.product.model.*;



@WebServlet("/nest-frontend/cartServlet.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
//		String memberId = (String) session.getAttribute("MemberUsing");
//		List<String> cartlist = JedisCartListService.getCartList(memberId);
		String memberId = "22";
		List<String> cartlist = JedisCartListService.getCartList(memberId);
		Map<String, List<ProductVO>> map = new LinkedHashMap<>();
	    Map<Integer, Integer> qtyMap = new LinkedHashMap<>();
		String action = req.getParameter("action");
		
		if (action.equals("showcart")) { 
			// 待測試
			if (cartlist != null && cartlist.size() != 0) {
				 for (int index = 0; index < cartlist.size(); index++){
		         	JSONObject jsonProduct;
		         	Integer merid=0;
		         	Integer qty=0;
		         	Integer busid=0;
		         	String busidString = null;
					try {
						jsonProduct = new JSONObject(cartlist.get(index));
						merid = Integer.valueOf(jsonProduct.getString("merId"));
			         	qty = Integer.valueOf(jsonProduct.getString("qty"));
			         	busid = Integer.valueOf(jsonProduct.getString("busId"));
			         	busidString = jsonProduct.getString("busId");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         	
		         			
		         	ProductService proSvc = new ProductService();
		         	ProductVO product = proSvc.getOneProduct(merid);
		         	qtyMap.put(merid, qty);
//		         	BusService busSvc = new BusService();
//		        	BusVO bus = busSvc.select(busid);
//		        	String busName = bus.getName();
		         			        	
//		        	if (map.containsKey(busName)) {
//		        		List<ProductVO> list = map.get(busName);
//		        		list.add(product);
//		        	} else {
//		        		List<ProductVO> list = new ArrayList<>();
//		        		list.add(product);
//		        		map.put(busName, list);
//		        	}
		         	
		         	if (map.containsKey(busidString)) {
		        		List<ProductVO> list = map.get(busidString);
		        		list.add(product);
		        	} else {
		        		List<ProductVO> list = new ArrayList<>();
		        		list.add(product);
		        		map.put(busidString, list);
		        	}
		         	
				 }
			}
			List<String> savelist = JedisCartListService.getCartList(memberId);
			session.setAttribute("cart", savelist);
			session.setAttribute("qtymap", qtyMap);
			session.setAttribute("map", map);
			String url = "shopCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
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