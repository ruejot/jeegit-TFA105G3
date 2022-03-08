package com.memheart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memfollow.model.MemFollowService;
import com.memfollow.model.MemFollowVO;
import com.memheart.model.MemHeartService;
import com.memheart.model.MemHeartVO;

@WebServlet("/MemHeartServlet")
public class MemHeartServlet extends HttpServlet {

	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
	// 將由底下的用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
//--------------------------------------判斷是否有按讚--------------------------------------------

		
		if ("if_liked".equals(action)) { // 來自blog-post-fullwidth2.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer heArtId = Integer.parseInt(req.getParameter("heArtId"));
				Integer heMemberId = Integer.parseInt(req.getParameter("heMemberId"));
				
				System.out.println("MHS 53: "+heArtId);
				System.out.println("MHS 54: "+heMemberId);
				
				/***************************2.開始查詢資料*****************************************/
				MemHeartService mhSvc = new MemHeartService();
				MemHeartVO memHeartVO = mhSvc.ifLiked(heArtId,heMemberId);
				
				System.out.println("MHS 60: "+ memHeartVO);
				
				if (memHeartVO==null) {
					
					System.out.println("按讚");
					
					
					try {
						/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
						heMemberId = Integer.parseInt(req.getParameter("heMemberId"));
						heArtId = Integer.parseInt(req.getParameter("heArtId"));
					
						MemHeartVO memHeartVO1 = new MemHeartVO();
						memHeartVO1.setHeMemberId(heMemberId);
						memHeartVO1.setHeArtId(heArtId);
						memHeartVO1.setTime(new Timestamp(System.currentTimeMillis()));

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("memHeartVO1", memHeartVO1); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
							failureView.forward(req, res);
							return;
						}

						/*************************** 2.開始新增資料 ***************************************/
						memHeartVO1 = mhSvc.addHeart(heArtId, heMemberId, new Timestamp(System.currentTimeMillis()));			
						
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//						String url = "nest-backend/page-user-detail.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page-user-detail.jsp
//						successView.forward(req, res);
//						res.sendRedirect(url);
						
						res.setContentType("application/json;charset=UTF-8;");
						PrintWriter out = res.getWriter();
						out.println("{\"success\": true, \"type\": \"insert\"}");
		//
//						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
				        errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
						failureView.forward(req, res);
					}
				
				} else {
					
					System.out.println("收回讚");
					
					System.out.println("=============");
					System.out.println(req.getParameter("heMemberId"));
					System.out.println(req.getParameter("heArtId"));

					try {
						/*************************** 1.接收請求參數 ***************************************/
						heMemberId = Integer.parseInt(req.getParameter("heMemberId"));
						heArtId = Integer.parseInt(req.getParameter("heArtId"));
					
						

						/*************************** 2.開始刪除資料 ***************************************/
						mhSvc.delete(heArtId,heMemberId);

						/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//						String url = "nest-backend/blog_manage.jsp";
////						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////						successView.forward(req, res);
//						res.sendRedirect(url);
						
						res.setContentType("application/json;charset=UTF-8;");
						PrintWriter out = res.getWriter();
						out.println("{\"success\": true, \"type\": \"delete\"}");

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
						failureView.forward(req, res);
					}					
					
				}
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫取出的empVO物件,存入req
//				String url = "/nest-frontend/blog-post-fullwidth2.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 blog-post-fullwidth2.jsp
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/nest-frontend/page-user-detail.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------判斷是否有追蹤--------------------------------------------
		
		
		

		if ("delete".equals(action)) { // 來自page-user-detail.jsp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("刪除");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer memberId = Integer.parseInt(req.getParameter("memberId"));

				Integer followee = Integer.parseInt(req.getParameter("followee"));
				

				/*************************** 2.開始刪除資料 ***************************************/
				MemFollowService mfSvc = new MemFollowService();
				mfSvc.deleteMemFollow(memberId,followee);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "nest-backend/blog_manage.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////				successView.forward(req, res);
//				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_manage.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------刪除文章結束--------------------------------------------        

	}


}

