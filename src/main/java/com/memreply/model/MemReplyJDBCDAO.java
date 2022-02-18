package com.memreply.model;

import java.util.*;

import com.memsavedart.model.MemSavedArtVO;

import java.sql.*;


public class MemReplyJDBCDAO implements MemReplyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "5525";

	private static final String INSERT_STMT = "INSERT INTO MEM_REPLY (RE_ART_ID,RE_MEMBER_ID,RE,TIME) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT RE_ID,RE_ART_ID,RE_MEMBER_ID,RE,TIME FROM MEM_REPLY";
	private static final String GET_ONE_STMT = "SELECT RE_ID,RE_ART_ID,RE_MEMBER_ID,RE,TIME FROM MEM_REPLY where RE_ART_ID = ?";
	private static final String GET_time_ByMemberId_STMT = "SELECT RE_ID,RE_ART_ID,RE_MEMBER_ID,RE,TIME FROM MEM_REPLY where RE_ART_ID = ? order by POSTTIME";
	
	private static final String DELETE = "DELETE FROM MEM_REPLY where RE_ID = ?";
	
	//private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
	//private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";	
	
	private static final String UPDATE = "UPDATE MEM_REPLY set RE=?, TIME=? where RE_ID=?";

	@Override
	public void insert(MemReplyVO memReplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memReplyVO.getReArtId());
			pstmt.setInt(2,memReplyVO.getReMemberId());	
			pstmt.setString(3, memReplyVO.getRe());
			pstmt.setTimestamp(4, memReplyVO.getTime());

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
	public void update(MemReplyVO memReplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,memReplyVO.getRe());
			pstmt.setTimestamp(2, memReplyVO.getTime());
			pstmt.setInt(3, memReplyVO.getReId());
			
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
	public void delete(Integer reId) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, reId);
			
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
	public MemReplyVO findByPrimaryKey(Integer reArtId) {

		MemReplyVO memReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reArtId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memReplyVO = new MemReplyVO();
				memReplyVO.setReId(rs.getInt("RE_ID"));
				memReplyVO.setReArtId(rs.getInt("RE_ART_ID"));
				memReplyVO.setReMemberId(rs.getInt("RE_MEMBER_ID"));
				memReplyVO.setRe(rs.getString("RE"));
				memReplyVO.setTime(rs.getTimestamp("TIME"));
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
		return memReplyVO;
	}

	@Override
	public List<MemReplyVO> getAll() {
		List<MemReplyVO> list = new ArrayList<MemReplyVO>();
		MemReplyVO memReplyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				memReplyVO = new MemReplyVO();
				memReplyVO.setReId(rs.getInt("RE_ID"));
				memReplyVO.setReArtId(rs.getInt("RE_ART_ID"));
				memReplyVO.setReMemberId(rs.getInt("RE_MEMBER_ID"));
				memReplyVO.setRe(rs.getString("RE"));
				memReplyVO.setTime(rs.getTimestamp("TIME"));
				
				list.add(memReplyVO); // Store the row in the list
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
	public Set<MemReplyVO> getReByArtId(Integer reMemberId) {
		Set<MemReplyVO> set = new LinkedHashSet<MemReplyVO>();
		MemReplyVO memReplyVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_time_ByMemberId_STMT);
			pstmt.setInt(1, reMemberId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				memReplyVO = new MemReplyVO();
				memReplyVO.setReId(rs.getInt("RE_ID"));
				memReplyVO.setReArtId(rs.getInt("RE_ART_ID"));
				memReplyVO.setReMemberId(rs.getInt("RE_MEMBER_ID"));
				memReplyVO.setRe(rs.getString("RE"));
				memReplyVO.setTime(rs.getTimestamp("TIME"));
				
				set.add(memReplyVO); // Store the row in the vector
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

		MemReplyService service = new MemReplyService();
		
		service.addRe(20, 5, "沒看過這麼可愛的狗", new Timestamp(System.currentTimeMillis()));
		
		service.updateRe("卡哇伊", new Timestamp(System.currentTimeMillis()), 4);
		
		service.delete(8);
		
		MemReplyVO memReplyVO3 = service.findByPrimaryKey(5);
		System.out.print(memReplyVO3.getReId() + ",");
		System.out.print(memReplyVO3.getReArtId() + ",");
		System.out.print(memReplyVO3.getReMemberId() + ",");
		System.out.print(memReplyVO3.getRe() + ",");
		System.out.println(memReplyVO3.getTime());
		System.out.println("---------------------");

		List<MemReplyVO> list = service.getAll();
		for (MemReplyVO memReplyVO4 : list) {
			System.out.print(memReplyVO4.getReId() + ",");
			System.out.print(memReplyVO4.getReArtId() + ",");
			System.out.print(memReplyVO4.getReMemberId() + ",");
			System.out.print(memReplyVO4.getRe() + ",");
			System.out.println(memReplyVO4.getTime());
			System.out.println();
		}
		
//		// 新增
//		MemReplyVO memReplyVO1 = new MemReplyVO();
//		
//		//memberblogartVO1.setReId(1);
//		memReplyVO1.setReArtId(5);
//		memReplyVO1.setReMemberId(1);
//		memReplyVO1.setRe("太可愛了啦><");
//		memReplyVO1.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.insert(memReplyVO1);

		// 修改
//		MemReplyVO memReplyVO2 = new MemReplyVO();
//		memReplyVO2.setReId(4);
//		memReplyVO2.setReArtId(5);
//		memReplyVO2.setReMemberId(1);
//		memReplyVO2.setRe("萌萌的<3");
//		memReplyVO2.setTime(new Timestamp(System.currentTimeMillis()));
//
//		dao.update(memReplyVO2);

//		// 刪除
//		dao.delete(7);
//
		// 查詢
//		MemReplyVO memReplyVO3 = dao.findByPrimaryKey(5);
//		System.out.print(memReplyVO3.getReId() + ",");
//		System.out.print(memReplyVO3.getReArtId() + ",");
//		System.out.print(memReplyVO3.getReMemberId() + ",");
//		System.out.print(memReplyVO3.getRe() + ",");
//		System.out.println(memReplyVO3.getTime());
//		System.out.println("---------------------");
//
//		// 查詢部門
//		List<MemReplyVO> list = dao.getAll();
//		for (MemReplyVO memReplyVO4 : list) {
//			System.out.print(memReplyVO4.getReId() + ",");
//			System.out.print(memReplyVO4.getReArtId() + ",");
//			System.out.print(memReplyVO4.getReMemberId() + ",");
//			System.out.print(memReplyVO4.getRe() + ",");
//			System.out.println(memReplyVO4.getTime());
//			System.out.println();
//		}
}
}