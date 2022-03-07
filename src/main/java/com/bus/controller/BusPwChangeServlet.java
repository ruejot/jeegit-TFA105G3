package com.bus.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bus.model.BusDAO;
import com.bus.model.BusDAO_interface;
import com.bus.model.BusService;
import com.bus.model.BusVO;

@WebServlet("/bus/BusPasswordChange")
public class BusPwChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		Integer id = Integer.parseInt(req.getParameter("busBusid"));
		
		String password = req.getParameter("busPassword"); 		// 現在(使用者輸入的)密碼
		String newpassword = req.getParameter("newBusPassword");// 新的密碼(確認密碼)
		String newpasswordRp = req.getParameter("newBusPasswordRp");
		
//		沒有要改，直接在hidden的input上get到值↓

		String name = req.getParameter("busName");
		String phone = req.getParameter("busPhone");
		String address = req.getParameter("busAddress");
		String taxid = req.getParameter("busTaxid");
		Timestamp date = Timestamp.valueOf(req.getParameter("busDate"));		
		String email = req.getParameter("busEmail");
		String intro = req.getParameter("busIntro");
		String FB = req.getParameter("busFB");
		String IG = req.getParameter("busIG");
		String Website = req.getParameter("busWebsite");
		String Paymentprovide = req.getParameter("busPaymentprovide");
		

		// 修改密碼		
		if ("changepw".equals(action)) {
			// 檢查三欄是否都有填寫
			if (!"password".equals(null) && !"newpassword".equals(null) && !"newpasswordrp".equals(null)) {

				BusDAO_interface busDAOInterface = new BusDAO();// 查帳號密碼方法放在DAO所以用DAO
				BusVO busbean = busDAOInterface.selectByEmailAndPassword(email, password);

				// 若email有get到資料庫中相對應的email跟password，表示client端的email跟password有match到
				if (busbean != null) {

					// 檢查新密碼是否輸入兩次都一致，若一致，則可存入DB
					if (newpassword.equals(newpasswordRp)) {
											
						BusService bussvc = new BusService();// 修改密碼方法放在service所以用service
						BusVO busVO = new BusVO();			//	先成立新物件BusVO()

						// 並將client端輸入的資料set進去BusVO()										
						busVO.setPassword(newpassword);
						
						busVO.setBusid(id);
						busVO.setName(name);
						busVO.setPhone(phone);
						busVO.setAddress(address);
						busVO.setTaxid(taxid);
						busVO.setEmail(email);
						busVO.setIntro(intro);
						busVO.setFb(FB);
						busVO.setIg(IG);
						busVO.setWebsite(Website);
						busVO.setPaymentprovide(Paymentprovide);

						busVO = bussvc.updateBus(id, name, phone, address, taxid,date, email, newpassword,
								intro, null,FB,IG,Website,Paymentprovide);
						
						// 資料庫update成功後,正確的的busVO物件,存入req
						req.setAttribute("busVO", busVO);
						
						req.setAttribute("BusPWupdateMsg", "密碼修改成功!!");
						req.getRequestDispatcher("../nest-backend/busPWChanged.jsp").forward(req, res);
						

					} else {

						req.setAttribute("warningBusPWDismatchMsg", "兩次密碼輸入不一致，請重新填寫，謝謝!!");
						req.getRequestDispatcher("../nest-backend/busChangePassword.jsp").forward(req, res);

					}

				} else {
					req.setAttribute("busPWErrMsg", "密碼輸入錯誤!!請再重新輸入謝謝");
					req.getRequestDispatcher("../nest-backend/busChangePassword.jsp").forward(req, res);

				}

			} else {
				req.setAttribute("warningBusPWMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
				req.getRequestDispatcher("../nest-frontend/busChangePassword.jsp").forward(req, res);
			}
			return;
		}

	}

}