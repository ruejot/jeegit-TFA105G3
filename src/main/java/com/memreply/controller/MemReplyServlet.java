package com.memreply.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.memartpic.model.MemArtPicService;
import com.memartpic.model.MemArtPicVO;
import com.memblogart.model.*;
import com.memreply.model.MemReplyService;
import com.memreply.model.MemReplyVO;


@WebServlet("/MemReplyServlet")
public class MemReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("upload".equals(action)) {}

		
		if ("getOne_For_Display".equals(action)) { // 來自blog_category-big2.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer artid = Integer.parseInt(req.getParameter("artid"));
						
				/***************************2.開始查詢資料*****************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);
				if (memBlogArtVO == null) {
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
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/nest-frontend/blog_category-big2.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		
//
//		if ("edit".equals(action)) { // 來自blog_manage.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				Integer artid = new Integer(req.getParameter("artid"));
////				System.out.println(artid + "安安");
//				req.setAttribute("artid", artid);
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫取出的empVO物件,存入req
//				String url = "/views/blog_edit-article.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("update".equals(action)) { // 來自blog_edit-article.jsp的請求
//
//			System.out.println("12345");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
////				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//
//				String title = req.getParameter("title");
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.add("文章標題: 請勿空白");
//				} else if (title.trim().length() >= 100) {
//					errorMsgs.add("文章標題: 請勿輸入超過100個字元");
//				}
//
//				String content = req.getParameter("content");
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.add("文章內容: 請勿空白");
//				} else if (content.trim().length() >= 1000) {
//					errorMsgs.add("文章內容: 請勿輸入超過1000個字元");
//				}
//
//				Timestamp posttime = new Timestamp(System.currentTimeMillis());
//
//				Integer artid = new Integer(req.getParameter("artid"));
////Integer artid= (Integer) req.getAttribute("artid");
//				System.out.println(artid);
//
//				
//				MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
//				memBlogArtVO.setTitle(title);
//				memBlogArtVO.setContent(content);
//				memBlogArtVO.setPosttime(posttime);
//				memBlogArtVO.setArtid(artid);
//							
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memBlogArtVO", memBlogArtVO); // 含有輸入格式錯誤的memBlogArtVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
////				/***************************2.開始修改資料*****************************************/
//
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				memBlogArtVO = mbaSvc.updateBlog(title, posttime, content, artid);
//
////				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/views/blog_manage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
////				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
//				failureView.forward(req, res);
//				e.printStackTrace();
//			}
//		}
//
//--------------------------------------新增留言--------------------------------------------

		if ("insert".equals(action)) { //blog-post-fullwidth2.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer reArtId = Integer.valueOf(req.getParameter("reArtId"));
				
				System.out.println(reArtId+"回應的程式");
				
				// TODO 等到整合時用session取得登入者的memberId再塞進去!
				Integer reMemberId = 1;
				
				String re = req.getParameter("re");
				if (re == null || re.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
				} else if (re.trim().length() >= 255) {
					errorMsgs.add("留言內容: 請勿輸入超過255個字元");
				}
				
				System.out.println(re);
				
				Timestamp posttime = new Timestamp(System.currentTimeMillis());
			
				MemReplyVO memReplyVO = new MemReplyVO();
				memReplyVO.setReArtId(reArtId);
				memReplyVO.setReMemberId(reMemberId);
				memReplyVO.setRe(re);
				memReplyVO.setTime(posttime);

				
				Timestamp time = new Timestamp(System.currentTimeMillis());			
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memReplyVO", memReplyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemReplyService mrsSvc = new MemReplyService();
				memReplyVO = mrsSvc.addRe(reArtId, reMemberId, re, time);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(reArtId);
				req.setAttribute("memBlogArtVO", memBlogArtVO);
				
				System.out.println(memBlogArtVO+"印出VO");
				
				String url = "/nest-frontend/blog-post-fullwidth2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
//				res.sendRedirect(url);

//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
//				failureView.forward(req, res);
//			}
		}

//--------------------------------------新增留言結束--------------------------------------------        
//
////---------------------------------------刪除文章--------------------------------------------        
//
//		if ("delete".equals(action)) { // 來自blog_manage.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("yeah");
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				Integer artid = Integer.valueOf(req.getParameter("artid"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				mbaSvc.delete(artid);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "nest-backend/blog_manage.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////				successView.forward(req, res);
//				res.sendRedirect(url);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////--------------------------------------刪除文章結束--------------------------------------------        
//
	}

}
