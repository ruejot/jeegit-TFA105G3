package com.csdetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bus.model.BusService;
import com.bus.model.BusVO;

/**
 * Servlet implementation class ToShopMainpage
 */
@WebServlet("/nest-frontend/ShopMainpage.do")
public class ShowShopMainpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//    public ToShopMainpage() {
//        super();
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("show_Shop_Mainpage".equals(action)) {
			// 錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 用傳遞過來的busid取busVO物件
				Integer busid = Integer.parseInt(req.getParameter("busid"));
				
				BusService busSvc = new BusService();
				BusVO busVO = busSvc.select(busid);
				
				// 取到的busVO物件存到Attribute
				req.setAttribute("busVO_From_ShopProductPage", busVO);
				
				// 成功時，轉跳並顯示"/nest-frontend/fendshop_mainpage.jsp"
				String url = "/nest-frontend/fendshop_mainpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);
				
			} catch(Exception e) {
				// 如果失敗。停留在當前頁面"/nest-frontend/ShopProductPage.jsp"
				System.out.println("無法取得要修改的資料:" + e.getMessage());
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/ShopProductPage.jsp");
				failureView.forward(req, res);
			}
			
		}
	}

}
