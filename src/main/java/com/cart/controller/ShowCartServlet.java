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
import com.members.model.MembersVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/nest-frontend/showCartServlet.do")
public class ShowCartServlet extends HttpServlet {
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
		
		if (action.equals("showcart")) { 
			System.out.println("1234567");
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
			session.setAttribute("list", savelist);
			session.setAttribute("qtymap", qtyMap);
			session.setAttribute("map", map);
			String url = "shopCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

}
