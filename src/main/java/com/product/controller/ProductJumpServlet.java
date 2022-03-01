package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.HomePageService;
import com.product.model.ProductVO;

@WebServlet("/product/ProductJump")
public class ProductJumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomePageService SERVICE;
    public ProductJumpServlet() {
    	SERVICE = new HomePageService();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("product_jump".equals(action)) {
			Integer i = Integer.parseInt(req.getParameter("merid"));
			List<ProductVO> aProd = SERVICE.getAllByProductId(i);
			if(aProd!=null) {
			req.setAttribute("aProd", aProd);
			System.out.println(aProd.get(0).getName());
			System.out.println("$"+aProd.get(0).getPrice()+" console from PJS");
			req.getRequestDispatcher("../nest-frontend/ShopProductPage.jsp").forward(req,res);
			}else
				System.out.println("No data");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
