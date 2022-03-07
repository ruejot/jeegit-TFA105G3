package com.bus.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bus.model.BusDAO;
import com.bus.model.BusDAO_interface;
import com.bus.model.BusService;
import com.bus.model.BusVO;

@WebServlet("/bus/BusDataUpdate")
public class BusDataUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");

		String action = req.getParameter("action");

		Integer id = Integer.parseInt(req.getParameter("busBusid"));
		String newname = req.getParameter("busName");
		String newphone = req.getParameter("busPhone");
		String newaddress = req.getParameter("busAddress");
		String newtaxid = req.getParameter("busTaxid");
		Timestamp date = Timestamp.valueOf(req.getParameter("busDate"));
		String email = req.getParameter("busEmail");
		String newintro = req.getParameter("busIntro");
		String newFB = req.getParameter("busFB");
		String newIG = req.getParameter("busIG");
		String newWebsite = req.getParameter("busWebsite");
		String newPaymentprovide = req.getParameter("busPaymentprovide");
		
		String old_BusPwd = req.getParameter("old_BusPwd"); // (使用者輸入的)舊密碼

		// 按出修改會員資料的鈕
		if ("busdataupdate".equals(action)) { // accountCenter.jsp裡的請求
			// 撈DB的資料，查詢該會員的所有資料，看輸入之email跟舊密碼，是否可在DB上GET到
			BusDAO_interface busDAOInterface = new BusDAO();
			BusVO busbean = busDAOInterface.selectByEmailAndPassword(email, old_BusPwd);
			// 若有busbean表示密碼有輸入正確
			if (busbean != null) {
				// 必填輸入欄的資料有符合格式的話，client端所填之內容可進入DB更新
				if (!"null".equals(newname) && !"null".equals(newphone) && !"null".equals(newphone)) {

					BusService bussvc = new BusService();// 修改方法放在service所以用service
//					先成立新物件BusVO()
					BusVO busVO = new BusVO();

					// 並將client端輸入的資料set進去BusVO()
					busVO.setBusid(id);
					busVO.setName(newname);
					busVO.setPhone(newphone);
					busVO.setAddress(newaddress);
					busVO.setTaxid(newtaxid);
					busVO.setEmail(email);
					busVO.setIntro(newintro);
					busVO.setFb(newFB);
					busVO.setIg(newIG);
					busVO.setWebsite(newWebsite);
					busVO.setPaymentprovide(newPaymentprovide);

					// 沒有圖片。 Service、DAO、SQL(private static final String UPDATE)語法也有對應拿掉。
					// java.sql.Timestamp.valueOf("2022-02-10 12:12:12")
					busVO = bussvc.updateBus(id, newname, newphone, newaddress, newtaxid,date, email, old_BusPwd,
							newintro, null,newFB,newIG,newWebsite,newPaymentprovide);
//					

					// 資料庫update成功後,正確的的busVO物件,存入req
					req.setAttribute("busVO", busVO);

					// 跳轉顯示修改成功
					req.setAttribute("DataupdateSuccessBusMsg1", "會員資料修改成功!!");
					req.getRequestDispatcher("../back-frontend/busAccountSetting.jsp").forward(req, res);

				} else {
					// 必填欄位(姓名跟手機)有未填寫者
					req.setAttribute("warningDataBusMsg", "不好意思!尚有必填欄位未填，請確實填寫，謝謝!!");
					req.getRequestDispatcher("../back-frontend/busAccountSetting.jsp").forward(req, res);

				}

			} else {
				req.setAttribute("errBusPWMsg", "不好意思!您密碼輸入錯誤!需輸入正確密碼後方可儲存修改資料!!");
				req.getRequestDispatcher("../nest-frontend/accountSetting.jsp").forward(req, res);
			}

		}
	}
}
