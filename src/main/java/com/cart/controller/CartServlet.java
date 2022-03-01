package com.cart.controller;

import java.io.IOException;
import java.util.Vector;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.Products;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Vector<Products> cartlist = (Vector<Products>) session.getAttribute("cart");
		String action = req.getParameter("action");
		
		if (!action.equals("checkout")) {
			
			// 刪除商品
			if (action.equals("delete")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				cartlist.removeElementAt(d);
			}
			
			// 新增商品到購物車中
			else if (action.equals("add")) {
				boolean match = false;
				
				// 取得新增商品
				Products addProducts = getProducts(req);
				
				// 新增第一項商品到購物車時
				if(cartlist == null) {
					cartlist = new Vector<Products>();
					cartlist.add(addProducts);
				} else {
					for (int i = 0; i < cartlist.size(); i++) {
						Products products = cartlist.get(i);
						
						// 假如新增商品購物車裡已經有了
						if (products.getMerId().equals(addProducts.getMerId())) {
							products.setQty(products.getQty() + addProducts.getQty());
							cartlist.setElementAt(products, i);
							match = true;
						}
					}
					
					// 假如新增商品購物車裡沒有
					if (!match) {
						cartlist.add(addProducts);
					}	
				}
			}
			
			session.setAttribute("cart", cartlist);
			String url = req.getRequestURI();
			res.sendRedirect(url);
		}
		
		// 結帳
		// 計算購物車總價
		else if (action.equals("checkout")) {
			int total = 0;
			for (int i = 0; i < cartlist.size(); i++) {
				Products order = cartlist.get(i);
				int price = order.getPrice();
				int qty = order.getQty();
				total += (price*qty);
			}
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "shopCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private Products getProducts(HttpServletRequest req) {
		String qty = req.getParameter("qty");
		String merId = req.getParameter("merId");
		String busId = req.getParameter("busId");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		
		Products products = new Products();
		
		products.setMerId((new Integer(merId)).intValue());
		products.setBusId((new Integer(busId)).intValue());
		products.setName(name);
		products.setQty((new Integer(qty)).intValue());
		products.setPrice((new Integer(price)).intValue());
		
		return products;
	}

}
