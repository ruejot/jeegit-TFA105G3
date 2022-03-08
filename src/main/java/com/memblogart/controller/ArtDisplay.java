package com.memblogart.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memblogart.model.MemBlogArtService;
import com.memblogart.model.MemBlogArtVO;
import com.memreply.model.MemReplyService;
import com.memreply.model.MemReplyVO;

    @WebServlet("/ArtDisplay")
	public class ArtDisplay extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	if ("getOne_For_Display".equals(action)) { // 來自blog_category-big2.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			Integer artid = Integer.parseInt(req.getParameter("artid"));

			Integer reArtId = artid;
			
			
			/***************************2.開始查詢資料*****************************************/
			MemBlogArtService mbaSvc = new MemBlogArtService();
			MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);
			
			MemReplyService mrSvc = new MemReplyService();
			List<MemReplyVO> memReplyVO = mrSvc.getAllByArtId(reArtId);
			
			
			if (memBlogArtVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (memReplyVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/nest-frontend/blog_category-big2.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫取出的empVO物件,存入req
			String url = "/nest-frontend/blog-post-fullwidth2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 blog-post-fullwidth2.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/nest-frontend/blog_category-big2.jsp");
			failureView.forward(req, res);
		}
	}

	
}

}
	