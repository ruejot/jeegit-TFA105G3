package com.memsavedart.model;

import java.util.*;
import java.sql.*;


public class MemSavedArtJDBCDAO implements MemSavedArtDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "5525";

	private static final String INSERT_STMT = "INSERT INTO MEM_SAVED_ART (SAV_MEMBER_ID,SAV_ART_ID,TIME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SAV_ID,SAV_MEMBER_ID,SAV_ART_ID,TIME FROM MEM_SAVED_ART";
	private static final String GET_ONE_STMT = "SELECT SAV_ID,SAV_MEMBER_ID,SAV_ART_ID,TIME FROM MEM_SAVED_ART where SAV_ID = ?";
	private static final String GET_art_BySavedMemberId_STMT = "SAV_ID,SAV_MEMBER_ID,SAV_ART_ID,TIME FROM MEM_BLOG_ART FROM MEM_SAVED_ART where MEMBER_ID = ? order by POSTTIME";
	
	private static final String DELETE = "DELETE FROM MEM_SAVED_ART where SAV_ID = ?";
	
	//private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
	//private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";	
	
	private static final String UPDATE = "UPDATE MEM_SAVED_ART set SAV_MEMBER_ID=? , SAV_ART_ID=? , TIME=? where SAV_ID = ?";

	@Override
	public void insert(MemSavedArtVO memSavedArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memSavedArtVO.getSavMemberId());
			pstmt.setInt(2, memSavedArtVO.getSavArtId());	
			pstmt.setTimestamp(3, memSavedArtVO.getTime());

pstmt.executeUpdate("set auto_increment_offset=1;");
pstmt.executeUpdate("set auto_increment_increment=1;");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MemSavedArtVO memSavedArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,memSavedArtVO.getSavMemberId());	
			pstmt.setInt(2, memSavedArtVO.getSavArtId());
			pstmt.setTimestamp(3, memSavedArtVO.getTime());
			pstmt.setInt(4, memSavedArtVO.getSavId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer savId) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, savId);
			
			updateCount_EMPs = pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("刪除部門編號" + deptno + "時,共有員工" + updateCount_EMPs
//					+ "人同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MemSavedArtVO findByPrimaryKey(Integer artid) {

		MemSavedArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				artVO = new MemSavedArtVO();
				artVO.setSavId(rs.getInt("SAV_ID"));
				artVO.setSavMemberId(rs.getInt("SAV_MEMBER_ID"));
				artVO.setSavArtId(rs.getInt("SAV_ART_ID"));
				artVO.setTime(rs.getTimestamp("TIME"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return artVO;
	}

	@Override
	public List<MemSavedArtVO> getAll() {
		List<MemSavedArtVO> list = new ArrayList<MemSavedArtVO>();
		MemSavedArtVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				artVO = new MemSavedArtVO();
				artVO.setSavId(rs.getInt("SAV_ID"));
				artVO.setSavMemberId(rs.getInt("SAV_MEMBER_ID"));
				artVO.setSavArtId(rs.getInt("SAV_ART_ID"));
				artVO.setTime(rs.getTimestamp("TIME"));
				
				list.add(artVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Set<MemSavedArtVO> getArtByMemberId(Integer savMemberId) {
		Set<MemSavedArtVO> set = new LinkedHashSet<MemSavedArtVO>();
		MemSavedArtVO memberVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_art_BySavedMemberId_STMT);
			pstmt.setInt(1, savMemberId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memberVO = new MemSavedArtVO();
				memberVO.setSavId(rs.getInt("SAV_ID"));
				memberVO.setSavMemberId(rs.getInt("SAV_MEMBER_ID"));
				memberVO.setSavArtId(rs.getInt("SAV_ART_ID"));
				memberVO.setTime(rs.getTimestamp("TIME"));
				set.add(memberVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {

		
		MemSavedArtService service = new MemSavedArtService();
		
		service.addSaved(3, 20, new Timestamp(System.currentTimeMillis()));
		
		service.updateSaved(3, 20, new Timestamp(System.currentTimeMillis()) , 8);
		
		service.delete(9);
		
		
		
		MemSavedArtVO memsavedartVO3 = service.findByPrimaryKey(3);
		System.out.print(memsavedartVO3.getSavId() + ",");
		System.out.print(memsavedartVO3.getSavMemberId() + ",");
		System.out.print(memsavedartVO3.getSavArtId() + ",");
		System.out.println(memsavedartVO3.getTime());
		System.out.println("---------------------");
		

		List<MemSavedArtVO> list = service.getAll();
		for (MemSavedArtVO memsavedartVO4 : list) {
			System.out.print(memsavedartVO4.getSavId() + ",");
			System.out.print(memsavedartVO4.getSavMemberId() + ",");
			System.out.print(memsavedartVO4.getSavArtId() + ",");
			System.out.println(memsavedartVO4.getTime());
			System.out.println();
		}
		
//		MemSavedArtJDBCDAO dao = new MemSavedArtJDBCDAO();
		
		// 新增
//		MemSavedArtVO memsavedartVO1 = new MemSavedArtVO();
		
//		memsavedartVO1.setSavId(1);
//		memsavedartVO1.setSavMemberId(3);
//		memsavedartVO1.setSavArtId(17);
//		memsavedartVO1.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.insert(memsavedartVO1);
		
		// 修改
//		MemSavedArtVO memsavedartVO2 = new MemSavedArtVO();
//		memsavedartVO2.setSavId(1);
//		memsavedartVO2.setSavMemberId(1);
//		memsavedartVO2.setSavArtId(1);
//		memsavedartVO2.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.update(memsavedartVO2);
//
		// 刪除
//		dao.delete(6);
//
//		// 查詢
//		MemSavedArtVO memsavedartVO3 = dao.findByPrimaryKey(3);
//		System.out.print(memsavedartVO3.getSavId() + ",");
//		System.out.print(memsavedartVO3.getSavMemberId() + ",");
//		System.out.print(memsavedartVO3.getSavArtId() + ",");
//		System.out.println(memsavedartVO3.getTime());
//		System.out.println("---------------------");
//
//		// 查詢部門
//		List<MemSavedArtVO> list = dao.getAll();
//		for (MemSavedArtVO memsavedartVO4 : list) {
//			System.out.print(memsavedartVO4.getSavId() + ",");
//			System.out.print(memsavedartVO4.getSavMemberId() + ",");
//			System.out.print(memsavedartVO4.getSavArtId() + ",");
//			System.out.println(memsavedartVO4.getTime());
//			System.out.println();
//		}
}
}

