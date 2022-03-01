package com.csdetail.model;

import java.util.List;


public class CsDetailService {
	private CsDetailDAO_interface dao;

	public CsDetailService() {
		dao = new CsDetailDAO();
	}
	
//Integer caseid, Integer memberid, Integer busid, java.sql.Date casetime, String feedback, Integer replystatus, String replycontent, java.sql.Date replytime
	
	public CsDetailVO insertCsDetail(Integer memberid
			, Integer busid, Integer merid, Integer orderid, java.sql.Date casetime, String feedback
			, Integer replystatus, String replycontent, java.sql.Date replytime) {
		
		CsDetailVO csDetailBean = new CsDetailVO();
		
		csDetailBean.setMemberid(memberid);
		csDetailBean.setBusid(busid);
		csDetailBean.setMerid(merid);
		csDetailBean.setOrderid(orderid);
		csDetailBean.setCasetime(casetime);
		csDetailBean.setFeedback(feedback);
		csDetailBean.setReplystatus(replystatus);
		csDetailBean.setReplycontent(replycontent);
		csDetailBean.setReplytime(replytime);
		
		dao.insert(csDetailBean);
		return csDetailBean;
	}
	
	public CsDetailVO updateCsDetail(Integer caseid, Integer memberid, 
			Integer busid, Integer merid, Integer orderid, java.sql.Date casetime, String feedback, 
			Integer replystatus, String replycontent, java.sql.Date replytime) {
		
		CsDetailVO csDetailBean = new CsDetailVO();
		
		csDetailBean.setCaseid(caseid);
		csDetailBean.setMemberid(memberid);
		csDetailBean.setBusid(busid);
		csDetailBean.setMerid(merid);
		csDetailBean.setOrderid(orderid);
		csDetailBean.setCasetime(casetime);
		csDetailBean.setFeedback(feedback);
		csDetailBean.setReplystatus(replystatus);
		csDetailBean.setReplycontent(replycontent);
		csDetailBean.setReplytime(replytime);
		
		dao.update(csDetailBean);
		return csDetailBean;
	}
	
	public void deleteCsDetail(Integer caseid) {
		dao.delete(caseid);
	}

	public CsDetailVO selectByCaseId(Integer caseid) {
		return dao.selectByCaseId(caseid);
	}
	
	public List<CsDetailVO> selectAll() {
		return dao.selectAll();
	}

	public List<CsDetailVO> selectCsDetailListByMemberid(Integer memberid) {
		return dao.selectCsDetailListByMemberid(memberid);
	}
	
	public List<CsDetailVO> selectCsDetailListByBusid(Integer busid) {
		return dao.selectCsDetailListByBusid(busid);
	}
	
}
