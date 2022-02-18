package test.model;


import java.util.List;

import com.csdetail.model.CsDetailVO;
import com.csdetail.model.CsDetailJDBCDAO;


public class CsDetailJDBCDAOTest {
	public static void main(String[] args) {
		
		CsDetailJDBCDAO dao = null;
		
//		insertTest(dao);
//		updateTest(dao);
//		deleteTest(dao);
//		selectTest(dao);
		selectAllTest(dao);
		
	}
	
	
	private static void insertTest(CsDetailJDBCDAO dao) {
		dao = new CsDetailJDBCDAO();
		
		CsDetailVO csDetailBean_in = new CsDetailVO();
		csDetailBean_in.setMemberid(9);
		csDetailBean_in.setBusid(1);
		csDetailBean_in.setCasetime(java.sql.Date.valueOf("2022-02-10"));
		csDetailBean_in.setFeedback("測試測試CsDetailSSSSSSSSS~~~");
		csDetailBean_in.setReplystatus(1);
		
		dao.insert(csDetailBean_in);
		System.out.println("==log.executed.CsDetail_insertTest");
	}
	
	private static void updateTest(CsDetailJDBCDAO dao) {
		dao = new CsDetailJDBCDAO();
		
		CsDetailVO csDetailBean_up = new CsDetailVO();
		csDetailBean_up.setCaseid(1);
		csDetailBean_up.setMemberid(9);
		csDetailBean_up.setBusid(1);
		csDetailBean_up.setCasetime(java.sql.Date.valueOf("2022-02-10"));
//		update的column一定都要寫耶，不能為null
//		如果是null或沒有set，就會把null寫進去。
		csDetailBean_up.setFeedback("貓砂買5包有免運嗎?");
		csDetailBean_up.setReplystatus(2);
		csDetailBean_up.setReplycontent("春節沒有開店喔~我們休到2/28!");
		csDetailBean_up.setReplytime(java.sql.Date.valueOf("2022-02-04"));
		
		dao.update(csDetailBean_up);
		System.out.println("==log.executed.CsDetail_updateTest");
	}
	
	private static void deleteTest(CsDetailJDBCDAO dao) {
		dao = new CsDetailJDBCDAO();
		
		dao.delete(4);
		System.out.println("==log.executed.CsDetail_deleteTest");
	}
	
	private static void selectTest(CsDetailJDBCDAO dao) {
		dao = new CsDetailJDBCDAO();
		CsDetailVO csDetailBean_sel_one = dao.selectByCaseId(3);
		
		System.out.println(csDetailBean_sel_one);
		System.out.println("==log.executed.CsDetail_selectTest");
	}
	
	private static void selectAllTest(CsDetailJDBCDAO dao) {
		dao = new CsDetailJDBCDAO();
		
		List<CsDetailVO> list= dao.selectAll();
		for(CsDetailVO one_csDetail : list) {
			System.out.println(one_csDetail);
		}
		System.out.println("==log.executed.CsDetail_selectAllTest");
		
	}


}
