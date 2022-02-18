package com.memblogart.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.memartpic.model.MemArtPicVO;
import com.memblogart.model.*;


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

		if ("upload".equals(action)) {}

//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("empno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		

		if ("edit".equals(action)) { // 來自blog_manage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer artid = new Integer(req.getParameter("artid"));
//				System.out.println(artid + "安安");
				req.setAttribute("artid", artid);

				/*************************** 2.開始查詢資料 ****************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫取出的empVO物件,存入req
				String url = "/views/blog_edit-article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自blog_edit-article.jsp的請求

			System.out.println("12345");
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
				} else if (content.trim().length() >= 1000) {
					errorMsgs.add("文章內容: 請勿輸入超過1000個字元");
				}

				Timestamp posttime = new Timestamp(System.currentTimeMillis());

				Integer artid = new Integer(req.getParameter("artid"));
//Integer artid= (Integer) req.getAttribute("artid");
				System.out.println(artid);

				
				MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
				memBlogArtVO.setTitle(title);
				memBlogArtVO.setContent(content);
				memBlogArtVO.setPosttime(posttime);
				memBlogArtVO.setArtid(artid);
							

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memBlogArtVO", memBlogArtVO); // 含有輸入格式錯誤的memBlogArtVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

//				/***************************2.開始修改資料*****************************************/

				MemBlogArtService mbaSvc = new MemBlogArtService();
				memBlogArtVO = mbaSvc.updateBlog(title, posttime, content, artid);

//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memBlogArtVO", memBlogArtVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/views/blog_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

//				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
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
				} else if (content.trim().length() >= 1000) {
					errorMsgs.add("文章內容: 請勿輸入超過1000個字元");
				}

				Timestamp posttime = new Timestamp(System.currentTimeMillis());

				// TODO 等到整合時用session取得登入者的memberId再塞進去!
				Integer memberId = 1;
				
				
				//以下是上傳圖片
				
				System.out.println("上傳!");
				req.setCharacterEncoding("Big5"); // 處理中文檔名
				res.setContentType("text/html; charset=Big5");
				PrintWriter out = res.getWriter();
				System.out.println("ContentType=" + req.getContentType()); // 測試用

				String realPath = getServletContext().getRealPath(saveDirectory);
				System.out.println("realPath=" + realPath); // 測試用
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists())
					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				out.write("<h2> Total parts : " + parts.size() + "</h2>");

				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					if (filename != null && part.getContentType() != null) {
						out.println("<PRE>");
						String name = part.getName();
						String ContentType = part.getContentType();
						long size = part.getSize();
						File f = new File(fsaveDirectory, filename);

						out.println("name: " + name);
						out.println("filename: " + filename);
						out.println("ContentType: " + ContentType);
						out.println("size: " + size);
						out.println("File: " + f);

						// 利用File物件,寫入目地目錄,上傳成功
						part.write(f.toString());

						// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
						InputStream in = part.getInputStream();
						byte[] blArtPic = new byte[in.available()];
						in.read(blArtPic);
						in.close();
						out.println("buffer length: " + blArtPic.length);

						// 額外測試秀圖
						out.println("<br><img src=\"" + req.getContextPath() + saveDirectory + "/" + filename + "\">");

						out.println();
						out.println("</PRE>");
					}
				}
						
			
				MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
				memBlogArtVO.setTitle(title);
				memBlogArtVO.setContent(content);
				memBlogArtVO.setPosttime(posttime);
				memBlogArtVO.setMemberId(memberId);

				
				//
				
				Timestamp time = new Timestamp(System.currentTimeMillis());
				
//				
				MemArtPicVO memArtPicVO = new MemArtPicVO();
//				memArtPicVO.setBlArtId(artid);
//				memArtPicVO.setBlArtPic(blArtPic);
				memArtPicVO.setTime(time);
//				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memBlogArtVO", memBlogArtVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_add-article.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				memBlogArtVO = mbaSvc.addBlog(memberId, title, posttime, content);
				
				
				
				
				
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/views/blog_add-article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_add-article.jsp");
				failureView.forward(req, res);
			}
		}

//--------------------------------------新增文章結束--------------------------------------------        

//---------------------------------------刪除文章--------------------------------------------        

		if ("delete".equals(action)) { // 來自blog_manage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("yeah");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer artid = new Integer(req.getParameter("artid"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				mbaSvc.delete(artid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/views/blog_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
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
