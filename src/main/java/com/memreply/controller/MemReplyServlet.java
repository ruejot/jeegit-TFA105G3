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

		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�blog_category-big2.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				Integer artid = Integer.parseInt(req.getParameter("artid"));
						
				/***************************2.�}�l�d�߸��*****************************************/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);
				if (memBlogArtVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/nest-frontend/blog_category-big2.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memBlogArtVO", memBlogArtVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/nest-frontend/blog-post-fullwidth2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/nest-frontend/blog_category-big2.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		
//
//		if ("edit".equals(action)) { // �Ӧ�blog_manage.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				Integer artid = new Integer(req.getParameter("artid"));
////				System.out.println(artid + "�w�w");
//				req.setAttribute("artid", artid);
//
//				/*************************** 2.�}�l�d�߸�� ****************************************/
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(artid);
//
//				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
//				req.setAttribute("memBlogArtVO", memBlogArtVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/views/blog_edit-article.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("update".equals(action)) { // �Ӧ�blog_edit-article.jsp���ШD
//
//			System.out.println("12345");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
////				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//
//				String title = req.getParameter("title");
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.add("�峹���D: �ФŪť�");
//				} else if (title.trim().length() >= 100) {
//					errorMsgs.add("�峹���D: �Фſ�J�W�L100�Ӧr��");
//				}
//
//				String content = req.getParameter("content");
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.add("�峹���e: �ФŪť�");
//				} else if (content.trim().length() >= 1000) {
//					errorMsgs.add("�峹���e: �Фſ�J�W�L1000�Ӧr��");
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
//					req.setAttribute("memBlogArtVO", memBlogArtVO); // �t����J�榡���~��memBlogArtVO����,�]�s�Jreq
//					RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
//					failureView.forward(req, res);
//					return; // �{�����_
//				}
//
////				/***************************2.�}�l�ק���*****************************************/
//
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				memBlogArtVO = mbaSvc.updateBlog(title, posttime, content, artid);
//
////				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("memBlogArtVO", memBlogArtVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/views/blog_manage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
////				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_edit-article.jsp");
//				failureView.forward(req, res);
//				e.printStackTrace();
//			}
//		}
//
//--------------------------------------�s�W�d��--------------------------------------------

		if ("insert".equals(action)) { //blog-post-fullwidth2.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				Integer reArtId = Integer.valueOf(req.getParameter("reArtId"));
				
				System.out.println(reArtId+"留言");
				
				// 用session拿memberID
				Integer reMemberId = Integer.valueOf(req.getParameter("reMemberId"));
				
				String re = req.getParameter("re");
				if (re == null || re.trim().length() == 0) {
					errorMsgs.add("請輸入留言內容");
				} else if (re.trim().length() >= 255) {
					errorMsgs.add("請勿輸入超過255個字元");
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
					req.setAttribute("memReplyVO", memReplyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				MemReplyService mrsSvc = new MemReplyService();
				memReplyVO = mrsSvc.addRe(reArtId, reMemberId, re, time);
				
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				MemBlogArtService mbaSvc = new MemBlogArtService();
				MemBlogArtVO memBlogArtVO = mbaSvc.findByPrimaryKey(reArtId);
				req.setAttribute("memBlogArtVO", memBlogArtVO);
				
				System.out.println(memBlogArtVO+"�L�XVO");
				
				String url = "/nest-frontend/blog-post-fullwidth2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
//				res.sendRedirect(url);

//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/nest-frontend/blog-post-fullwidth2.jsp");
//				failureView.forward(req, res);
//			}
		}

//--------------------------------------�s�W�d������--------------------------------------------        
//
////---------------------------------------�R���峹--------------------------------------------        
//
//		if ("delete".equals(action)) { // �Ӧ�blog_manage.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("yeah");
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� ***************************************/
//				Integer artid = Integer.valueOf(req.getParameter("artid"));
//
//				/*************************** 2.�}�l�R����� ***************************************/
//				MemBlogArtService mbaSvc = new MemBlogArtService();
//				mbaSvc.delete(artid);
//
//				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//				String url = "nest-backend/blog_manage.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
////				successView.forward(req, res);
//				res.sendRedirect(url);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/views/blog_manage.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
////--------------------------------------�R���峹����--------------------------------------------        
//
	}

}
