package com.memsavedart.controller;

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
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.memblogart.model.*;
import com.memfollow.model.MemFollowService;
import com.memfollow.model.MemFollowVO;
import com.memreply.model.MemReplyService;
import com.memreply.model.MemReplyVO;
import com.memsavedart.model.MemSavedArtService;
import com.memsavedart.model.MemSavedArtVO;
import com.mysql.cj.Session;

@WebServlet("/MemSavedArtServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemSavedArtServlet extends HttpServlet {

	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
	// 將由底下的用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
//--------------------------------------判斷是否有追蹤--------------------------------------------

		
		if ("if_saved".equals(action)) { // 來自page-user-detail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer savMemberId = Integer.parseInt(req.getParameter("savMemberId"));
				Integer savArtId = Integer.parseInt(req.getParameter("savArtId"));
				
				/***************************2.開始查詢資料*****************************************/
				MemSavedArtService msaSvc = new MemSavedArtService();
				MemSavedArtVO memSavedArtVO = msaSvc.ifSaved(savMemberId,savArtId);
				
				System.out.println(memSavedArtVO);
				
				if (memSavedArtVO==null) {
					
					System.out.println("新增追蹤關係");
					
					System.out.println("=============");
					System.out.println(req.getParameter("savMemberId"));
					System.out.println(req.getParameter("savArtId"));
					
					try {
						/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
						savMemberId = Integer.parseInt(req.getParameter("savMemberId"));
						savArtId = Integer.parseInt(req.getParameter("savArtId"));
						Timestamp time = new Timestamp(System.currentTimeMillis());
					
						MemSavedArtVO memSavedArtVO1 = new MemSavedArtVO();
						memSavedArtVO1.setSavMemberId(savMemberId);
						memSavedArtVO1.setSavArtId(savArtId);
						memSavedArtVO1.setTime(time);
						

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("memSavedArtVO", memSavedArtVO1); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
							failureView.forward(req, res);
							return;
						}

						/*************************** 2.開始新增資料 ***************************************/
						memSavedArtVO1 = msaSvc.addSaved(savMemberId, savArtId, time);			
						
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
					
					System.out.println("刪除追蹤關係");
					
					System.out.println("=============");
					System.out.println(req.getParameter("savMemberId"));
					System.out.println(req.getParameter("savArtId"));

					try {
						/*************************** 1.接收請求參數 ***************************************/
						savMemberId = Integer.parseInt(req.getParameter("savMemberId"));

						savArtId = Integer.parseInt(req.getParameter("savArtId"));
						

						/*************************** 2.開始刪除資料 ***************************************/
						msaSvc.delete(savMemberId,savArtId);

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
						.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------判斷是否有追蹤--------------------------------------------
		
		
		
		
//--------------------------------------新增追蹤--------------------------------------------

//		if ("insert".equals(action)) { // 來自page-user-detail.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			System.out.println("拿到惹");
//			System.out.println(req.getParameter("memberId"));
//			System.out.println(req.getParameter("followee"));
//			
//			
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				Integer memberId = Integer.parseInt(req.getParameter("memberId"));
//
//				Integer followee = Integer.parseInt(req.getParameter("followee"));
//				
//				String friendship = "1";
//			
//				MemFollowVO memFollowVO = new MemFollowVO();
//				memFollowVO.setMemberId(memberId);
//				memFollowVO.setFollowee(followee);
//				memFollowVO.setFriendship(friendship);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memFollowVO", memFollowVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/page-user-detail.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				MemFollowService mfSvc = new MemFollowService();
//				memFollowVO = mfSvc.insertMemFollow(memberId, followee, friendship);			
//				
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
////				String url = "nest-backend/page-user-detail.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page-user-detail.jsp
////				successView.forward(req, res);
////				res.sendRedirect(url);
//				
//				res.setContentType("application/json;charset=UTF-8;");
//				PrintWriter out = res.getWriter();;
//				out.println("{\"success\": true, \"type\": \"insert\"}");
////
////				/*************************** 其他可能的錯誤處理 **********************************/
//			    } catch (Exception e) {
//				    errorMsgs.add(e.getMessage());
//				    RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/page-user-detail.jsp");
//				    failureView.forward(req, res);
//			}
//		}

//--------------------------------------新增文章結束--------------------------------------------        

		

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
