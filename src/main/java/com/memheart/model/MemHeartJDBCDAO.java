package com.memheart.model;

import java.util.*;

import com.memblogart.model.MemBlogArtVO;

import java.sql.*;


public class MemHeartJDBCDAO implements MemHeartDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "5525";

	private static final String INSERT_STMT = "INSERT INTO MEM_HEART (HE_ART_ID,HE_MEMBER_ID,TIME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT HE_ART_ID,HE_MEMBER_ID,TIME FROM MEM_HEART";
	private static final String GET_ONE_STMT = "SELECT HE_ART_ID,HE_MEMBER_ID,TIME FROM MEM_HEART where HE_ART_ID = ? and HE_MEMBER_ID = ?";
	private static final String GET_count_ByMemberId_STMT = "select COUNT(*) from MEM_HEART WHERE HE_MEMBER_ID = ?";
	
	private static final String DELETE = "DELETE FROM MEM_HEART where HE_ART_ID = ? and HE_MEMBER_ID = ?";
	
	//private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
	//private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";	
	
	private static final String UPDATE = "UPDATE MEM_HEART HE_MEMBER_ID=? , TIME=? where HE_ART_ID = ?";

	@Override
	public void insert(MemHeartVO memHeartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memHeartVO.getHeArtId());
			pstmt.setInt(2,memHeartVO.getHeMemberId());	
			pstmt.setTimestamp(3, memHeartVO.getTime());

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
	public void update(MemHeartVO memHeartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memHeartVO.getHeMemberId());
			pstmt.setTimestamp(2, memHeartVO.getTime());
			pstmt.setInt(3,memHeartVO.getHeArtId());


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
	public void delete(Integer heArtId,Integer heMemberId) {
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
			pstmt.setInt(1, heArtId);
			pstmt.setInt(2, heMemberId);
			
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
	public MemHeartVO findByPrimaryKey(Integer heArtId,Integer heMemberId) {

		MemHeartVO memheartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, heArtId);
			pstmt.setInt(2, heMemberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memheartVO = new MemHeartVO();
				memheartVO.setHeArtId(rs.getInt("HE_ART_ID"));
				memheartVO.setHeMemberId(rs.getInt("HE_MEMBER_ID"));
				memheartVO.setTime(rs.getTimestamp("TIME"));
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
		return memheartVO;
	}

	@Override
	public List<MemHeartVO> getAll() {
		List<MemHeartVO> list = new ArrayList<MemHeartVO>();
		MemHeartVO memheartVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memheartVO = new MemHeartVO();
				memheartVO.setHeArtId(rs.getInt("HE_ART_ID"));
				memheartVO.setHeMemberId(rs.getInt("HE_MEMBER_ID"));
				memheartVO.setTime(rs.getTimestamp("TIME"));
				list.add(memheartVO); // Store the row in the list
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
	public Set<MemHeartVO> getCountByMember(Integer heMemberId) {
		Set<MemHeartVO> set = new LinkedHashSet<MemHeartVO>();
		MemHeartVO memheartVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_count_ByMemberId_STMT);
			pstmt.setInt(1, heMemberId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memheartVO = new MemHeartVO();
				memheartVO.setHeArtId(rs.getInt("HE_ART_ID"));
				memheartVO.setHeMemberId(rs.getInt("HE_MEMBER_ID"));
				memheartVO.setTime(rs.getTimestamp("TIME"));
				set.add(memheartVO); // Store the row in the vector
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

		MemHeartService service = new MemHeartService();
		
		service.addHeart(9, 2, new Timestamp(System.currentTimeMillis()));
		
		service.updateHeart(1, new Timestamp(System.currentTimeMillis()),9);
		
		service.delete(9,2);
		
		MemHeartVO memheartVO3 = service.findByPrimaryKey(6, 1);
		System.out.print(memheartVO3.getHeArtId() + ",");
		System.out.print(memheartVO3.getHeMemberId() + ",");
		System.out.println(memheartVO3.getTime());
		System.out.println("---------------------");
		
		
		List<MemHeartVO> list = service.getAll();
		for (MemHeartVO memheartVO4 : list) {
			System.out.print(memheartVO4.getHeArtId() + ",");
			System.out.print(memheartVO4.getHeMemberId() + ",");
			System.out.println(memheartVO4.getTime());
			System.out.println();
		}
		
		
		// 新增

//		memheartVO1.setHeArtId(6);
//		memheartVO1.setHeMemberId(1);
//		memheartVO1.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.insert(memheartVO1);

		// 修改
//		MemHeartVO memheartVO2 = new MemHeartVO();
//		memheartVO2.setHeArtId(9);
//		memheartVO2.setHeMemberId(2);
//		memheartVO2.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.update(memheartVO2);
//
		// 刪除
//		dao.delete(9);
//
		// 查詢
//		MemHeartVO memheartVO3 = dao.findByPrimaryKey(6,1);
//		System.out.print(memheartVO3.getHeArtId() + ",");
//		System.out.print(memheartVO3.getHeMemberId() + ",");
//		System.out.println(memheartVO3.getTime());
//		System.out.println("---------------------");
//
//		// 查詢部門
//		List<MemHeartVO> list = dao.getAll();
//		for (MemHeartVO memheartVO4 : list) {
//			System.out.print(memheartVO4.getHeArtId() + ",");
//			System.out.print(memheartVO4.getHeMemberId() + ",");
//			System.out.println(memheartVO4.getTime());
//			System.out.println();
//		}
}
}