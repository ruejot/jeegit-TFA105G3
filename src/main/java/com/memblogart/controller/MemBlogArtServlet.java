package com.memblogart.controller;

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
import com.memreply.model.MemReplyService;
import com.memreply.model.MemReplyVO;
import com.mysql.cj.Session;

@WebServlet("/MemBlogArtServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemBlogArtServlet extends HttpServlet {

	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
	// 將由底下的用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("upload".equals(action)) {}

		//查詢blog個人資料
		if ("getMem_For_Display".equals(action)) { // 來自page-user-detail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer memberId = Integer.parseInt(req.getParameter("memberId"));
				
				
				/***************************2.開始查詢資料*****************************************/
				MembersService memSvc = new MembersService();
				MembersVO membersVO = memSvc.select(memberId);
//				
//				MemReplyService mrSvc = new MemReplyService();
//				List<MemReplyVO> memReplyVO = mrSvc.getAllByArtId(reArtId);
//				
				if (membersVO == null) {
					errorMsgs.add("查無資料");
				}
//				
//				if (memReplyVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/nest-frontend/page-user-detail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membersVO", membersVO); // 資料庫取出的empVO物件,存入req
				String url = "/nest-frontend/page-user-detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 blog-post-fullwidth2.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/nest-frontend/page-user-detail.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		
		
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
		
		

		if ("edit".equals(action)) { // 來自blog_manage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer artid = new Integer(req.getParameter("artid"));
				System.out.println(artid + "編輯");
				req.setAttribute("artid", artid);

				/*************************** 2.開始查詢資料 ****************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫取出的empVO物件,存入req
				String url = "/nest-backend/blog_edit-article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_manage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自blog_edit-article.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				String title = req.getParameter("title");
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if (title.trim().length() >= 100) {
					errorMsgs.add("文章標題: 請勿輸入超過100個字元");
				}

				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} else if (content.trim().length() >= 2000) {
					errorMsgs.add("文章內容: 請勿輸入超過2000個字元");
				}

				Timestamp posttime = new Timestamp(System.currentTimeMillis());

				Integer artid = new Integer(req.getParameter("artid"));
//Integer artid= (Integer) req.getAttribute("artid");
				System.out.println(artid+"修改");

				
				MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
				memBlogArtVO.setTitle(title);
				memBlogArtVO.setContent(content);
				memBlogArtVO.setPosttime(posttime);
				memBlogArtVO.setArtid(artid);
							

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memBlogArtVO", memBlogArtVO); // 含有輸入格式錯誤的memBlogArtVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_edit-article.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

//				/***************************2.開始修改資料*****************************************/

				MemBlogArtService mbaSvc = new MemBlogArtService();
				memBlogArtVO = mbaSvc.updateBlog(title, posttime, content, artid);

//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/nest-backend/blog_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交blog_edit-article.jsp
				successView.forward(req, res);

//				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_edit-article.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

//--------------------------------------新增文章--------------------------------------------

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String title = req.getParameter("title");
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if (title.trim().length() >= 100) {
					errorMsgs.add("文章標題: 請勿輸入超過100個字元");
				}

				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				} else if (content.trim().length() >= 2000) {
					errorMsgs.add("文章內容: 請勿輸入超過2000個字元");
				}

				Timestamp posttime = new Timestamp(System.currentTimeMillis());

				// TODO 等到整合時用session取得登入者的memberId再塞進去!
				
				Integer memberId = Integer.parseInt(req.getParameter("merberusing"));
			
				MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
				memBlogArtVO.setTitle(title);
				memBlogArtVO.setContent(content);
				memBlogArtVO.setPosttime(posttime);
				memBlogArtVO.setMemberId(memberId);

				
				Timestamp time = new Timestamp(System.currentTimeMillis());			
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memBlogArtVO", memBlogArtVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_add-article.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				memBlogArtVO = mbaSvc.addBlog(memberId, title, posttime, content);
				
				
				/*************************** 3.新增完圖片取得剛剛的產生的PK ***************************************/
				Connection con = null;
				PreparedStatement pstmt = null;
				Integer artid=0;
				try {

					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei", "root", "5525");
					pstmt = con.prepareStatement("select ART_ID from MEM_BLOG_ART order by ART_ID desc limit 0 , 1 ");
					ResultSet rs= pstmt.executeQuery();
	
					while (rs.next()) {
						artid = rs.getInt(1);
					}
//					System.out.println(artid + "安安");
					// Handle any driver errors
				} catch (Exception e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
				} finally {
					
//					pstmt.close();
					con.close();
				}
				
				
				Timestamp timepic = new Timestamp(System.currentTimeMillis());		
								
				byte[] blArtPic = null;
				
				//以下是上傳圖片

				req.setCharacterEncoding("Big5"); // 處理中文檔名
				res.setContentType("text/html; charset=Big5");
//				PrintWriter out = res.getWriter();
//				System.out.println("ContentType=" + req.getContentType()); // 測試用

//				String realPath = getServletContext().getRealPath(saveDirectory);
//				System.out.println("realPath=" + realPath); // 測試用
//				File fsaveDirectory = new File(realPath);
//				if (!fsaveDirectory.exists())
//					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄


//				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				Part part =  req.getPart("file");
//				System.out.println(part);

//				for (Part part : parts) {
//					String filename = getFileNameFromPart(part);
//					System.out.println(filename+"sssssssssssss");
//					if (filename != null && part.getContentType() != null) {
//						
//						String name = part.getName();
//						String ContentType = part.getContentType();
//						long size = part.getSize();

						// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
						
//						InputStream in = part.getInputStream();
//						blArtPic = new byte[in.available()];
//						in.read(blArtPic);
//						in.close();
		

						// 額外測試秀圖
//						out.println("<br><img src=\"" + req.getContextPath() + saveDirectory + "/" + filename + "\">");
//
//						out.println();
//						out.println("</PRE>");
//					}
//				}
				
				String filename = getFileNameFromPart(part);
//				System.out.println(filename+"sssssssssssss");
				if (filename != null && part.getContentType() != null) {
					
					String name = part.getName();
					String ContentType = part.getContentType();
					long size = part.getSize();

//					 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
					
					InputStream in = part.getInputStream();
					blArtPic = new byte[in.available()];
					in.read(blArtPic);
					in.close();
				}
				
				
				MemArtPicVO memArtPicVO = new MemArtPicVO();
				memArtPicVO.setBlArtId(artid);
				memArtPicVO.setBlArtPic(blArtPic);
				memArtPicVO.setTime(timepic);
					
//				
				
				MemArtPicService mapSvc = new MemArtPicService();
				memArtPicVO = mapSvc.addArtPic(artid, blArtPic, timepic);
			
//				
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "nest-backend/blog_manage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
				res.sendRedirect(url);
				
//
//				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_add-article.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------新增文章結束--------------------------------------------        

//---------------------------------------刪除文章--------------------------------------------        
		if ("batch_delete".equals(action)) {
			String artids = req.getParameter("artid");
			
			String[] arr = artids.split(",");
			for(int i =0; i<arr.length;i++) {
				System.out.println(arr[i]);

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
	
				try {
					/*************************** 1.接收請求參數 ***************************************/
					Integer artid = Integer.parseInt(arr[i]);
	
					/*************************** 2.開始刪除資料 ***************************************/
					MemBlogArtService mbaSvc = new MemBlogArtService();
					mbaSvc.delete(artid);
	
					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_manage.jsp");
					failureView.forward(req, res);
				}
			}
			
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/

//			String url = "nest-backend/blog_manage.jsp";
////			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////			successView.forward(req, res);
//			res.sendRedirect(url);
			
		}
		
		if ("delete".equals(action)) { // 來自blog_manage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("yeah");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer artid = Integer.valueOf(req.getParameter("artid"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				mbaSvc.delete(artid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "nest-backend/blog_manage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/nest-backend/blog_manage.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------刪除文章結束--------------------------------------------        

	}

	// 取出上傳的檔案名稱 (因為API未提供此method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;

	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
