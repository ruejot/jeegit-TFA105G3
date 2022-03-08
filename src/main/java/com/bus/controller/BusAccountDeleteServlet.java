package com.bus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bus.model.BusDAO;
import com.bus.model.BusDAO_interface;
import com.bus.model.BusService;
import com.bus.model.BusVO;

@WebServlet("/bus/BusAccountDelete")
public class BusAccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		Integer id = Integer.parseInt(req.getParameter("busBusid"));
		String email = req.getParameter("busEmail");
		
		String password = req.getParameter("busPassword"); 		// 現在的密碼
		String passwordrp = req.getParameter("busPasswordRp");	// 確認密碼
				

		// 刪除帳號
		if ("deleteBus".equals(action)) {
			// 檢查二欄是否都有填寫
			if (!"password".equals(null) && !"passwordRp".equals(null)) {

				BusDAO_interface busDAOInterface = new BusDAO();// 查帳號密碼方法放在DAO所以用DAO
				BusVO busbean =busDAOInterface.selectByEmailAndPassword(email, password);

				// 若email有get到資料庫中相對應的email跟password，表示client端的email跟password有match到
				if (busbean != null) {

					// 檢查新密碼是否輸入兩次都一致，若一致，則可更新至DB
					if (password.equals(passwordrp)) {
											
						BusService bussvc = new BusService();// 刪帳號方法放在service所以用service
						
						bussvc.deleteBus(id);

						req.setAttribute("BusDeleteMsg", "帳號已刪除成功!!");
						req.getRequestDispatcher("../nest-backend/busAccDeletedSuccess.jsp").forward(req, res);
						

					} else {

						req.setAttribute("warningBusPWDismatchMsg", "兩次密碼輸入不一致，請重新填寫，謝謝!!");
						req.getRequestDispatcher("../nest-backend/busAccountDelete.jsp").forward(req, res);

					}

				} else {
					req.setAttribute("busPWErrMsg", "密碼輸入錯誤!!請再重新輸入謝謝");
					req.getRequestDispatcher("../nest-backend/busAccountDelete.jsp").forward(req, res);

				}

			} else {
				req.setAttribute("warningBusDeleteMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
				req.getRequestDispatcher("../nest-backend/busAccountDelete.jsp").forward(req, res);
			}
			return;
		}

	}

}