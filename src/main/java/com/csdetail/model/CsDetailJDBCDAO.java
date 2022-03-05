package com.csdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CsDetailJDBCDAO implements CsDetailDAO_interface{
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String userid = "root";
	private static final String passwd = "password";
	
	private static final String INSERT_STMT = 
		"INSERT INTO CS_DETAIL (MEMBER_ID, BUS_ID, MER_ID, ORDER_ID, CASE_TIME, FEEDBACK, REPLY_STATUS, REPLY_CONTENT, REPLY_TIME)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE CS_DETAIL SET MEMBER_ID=?, BUS_ID=?, MER_ID=?, ORDER_ID=?, CASE_TIME=?, FEEDBACK=?, REPLY_STATUS=?, REPLY_CONTENT=?, REPLY_TIME=?"
		+ " WHERE CASE_ID=?";
	private static final String DELETE_STMT = 
		"DELETE FROM CS_DETAIL WHERE CASE_ID=?";
	private static final String GET_ONE_STMT = 
		"SELECT CASE_ID, MEMBER_ID, BUS_ID, MER_ID, ORDER_ID, CASE_TIME, FEEDBACK, REPLY_STATUS, REPLY_CONTENT, REPLY_TIME FROM CS_DETAIL WHERE CASE_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT CASE_ID, MEMBER_ID, BUS_ID, MER_ID, ORDER_ID, CASE_TIME, FEEDBACK, REPLY_STATUS, REPLY_CONTENT, REPLY_TIME FROM CS_DETAIL ORDER BY CASE_ID";
	private static final String GET_ALL_BY_MEMBER_ID_STMT =
		"SELECT * FROM CS_DETAIL WHERE MEMBER_ID = ?";
	private static final String GET_ALL_BY_BUS_ID_STMT =
		"SELECT * FROM CS_DETAIL WHERE BUS_ID = ?";
	
	@Override
	public void insert(CsDetailVO csDetailBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, csDetailBean.getMemberid());
			pstmt.setInt(2, csDetailBean.getBusid());
			pstmt.setInt(3, csDetailBean.getMerid());
			pstmt.setInt(4, csDetailBean.getOrderid());
			pstmt.setDate(5, csDetailBean.getCasetime());
			pstmt.setString(6, csDetailBean.getFeedback());
			pstmt.setInt(7, csDetailBean.getReplystatus());
			pstmt.setString(8, csDetailBean.getReplycontent());
			pstmt.setDate(9, csDetailBean.getReplytime());
			
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
	public void update(CsDetailVO csDetailBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, csDetailBean.getMemberid());
			pstmt.setInt(2, csDetailBean.getBusid());
			pstmt.setInt(3, csDetailBean.getMerid());
			pstmt.setInt(4, csDetailBean.getOrderid());
			pstmt.setDate(5, csDetailBean.getCasetime());
			pstmt.setString(6, csDetailBean.getFeedback());
			pstmt.setInt(7, csDetailBean.getReplystatus());
			pstmt.setString(8, csDetailBean.getReplycontent());
			pstmt.setDate(9, csDetailBean.getReplytime());
			pstmt.setInt(10, csDetailBean.getCaseid());
							
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
	public void delete(Integer caseid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, caseid);

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
	public CsDetailVO selectByCaseId(Integer caseid) {
		
		CsDetailVO csDetailBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, caseid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				csDetailBean = new CsDetailVO();

				csDetailBean.setCaseid(rs.getInt("CASE_ID"));
				csDetailBean.setMemberid(rs.getInt("MEMBER_ID"));
				csDetailBean.setBusid(rs.getInt("BUS_ID"));
				csDetailBean.setMerid(rs.getInt("MER_ID"));
				csDetailBean.setOrderid(rs.getInt("ORDER_ID"));
				csDetailBean.setCasetime(rs.getDate("CASE_TIME"));
				csDetailBean.setFeedback(rs.getString("FEEDBACK"));
				csDetailBean.setReplystatus(rs.getInt("REPLY_STATUS"));
				csDetailBean.setReplycontent(rs.getString("REPLY_CONTENT"));
				csDetailBean.setReplytime(rs.getDate("REPLY_TIME"));
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
		
		return csDetailBean;
	}
	
	@Override
	public List<CsDetailVO> selectAll() {
		List<CsDetailVO> list = new ArrayList<CsDetailVO>();
		
		CsDetailVO csDetailBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				csDetailBean = new CsDetailVO();
				
				csDetailBean.setCaseid(rs.getInt("CASE_ID"));
				csDetailBean.setMemberid(rs.getInt("MEMBER_ID"));
				csDetailBean.setBusid(rs.getInt("BUS_ID"));
				csDetailBean.setMerid(rs.getInt("MER_ID"));
				csDetailBean.setOrderid(rs.getInt("ORDER_ID"));
				csDetailBean.setCasetime(rs.getDate("CASE_TIME"));
				csDetailBean.setFeedback(rs.getString("FEEDBACK"));
				csDetailBean.setReplystatus(rs.getInt("REPLY_STATUS"));
				csDetailBean.setReplycontent(rs.getString("REPLY_CONTENT"));
				csDetailBean.setReplytime(rs.getDate("REPLY_TIME"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(csDetailBean);
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
		
		return list;
	}

	@Override
	public List<CsDetailVO> selectCsDetailListByMemberid(Integer memberid) {
		List<CsDetailVO> list = new ArrayList<CsDetailVO>();
		
		CsDetailVO csDetailBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER_ID_STMT);
			
			pstmt.setInt(1, memberid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				csDetailBean = new CsDetailVO();
				
				csDetailBean.setCaseid(rs.getInt("CASE_ID"));
				csDetailBean.setMemberid(rs.getInt("MEMBER_ID"));
				csDetailBean.setBusid(rs.getInt("BUS_ID"));
				csDetailBean.setMerid(rs.getInt("MER_ID"));
				csDetailBean.setOrderid(rs.getInt("ORDER_ID"));
				csDetailBean.setCasetime(rs.getDate("CASE_TIME"));
				csDetailBean.setFeedback(rs.getString("FEEDBACK"));
				csDetailBean.setReplystatus(rs.getInt("REPLY_STATUS"));
				csDetailBean.setReplycontent(rs.getString("REPLY_CONTENT"));
				csDetailBean.setReplytime(rs.getDate("REPLY_TIME"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(csDetailBean);
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
		
		return list;
	}

	@Override
	public List<CsDetailVO> selectCsDetailListByBusid(Integer busid) {
		List<CsDetailVO> list = new ArrayList<CsDetailVO>();
		
		CsDetailVO csDetailBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_BUS_ID_STMT);
			
			pstmt.setInt(1, busid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				csDetailBean = new CsDetailVO();
				
				csDetailBean.setCaseid(rs.getInt("CASE_ID"));
				csDetailBean.setMemberid(rs.getInt("MEMBER_ID"));
				csDetailBean.setBusid(rs.getInt("BUS_ID"));
				csDetailBean.setMerid(rs.getInt("MER_ID"));
				csDetailBean.setOrderid(rs.getInt("ORDER_ID"));
				csDetailBean.setCasetime(rs.getDate("CASE_TIME"));
				csDetailBean.setFeedback(rs.getString("FEEDBACK"));
				csDetailBean.setReplystatus(rs.getInt("REPLY_STATUS"));
				csDetailBean.setReplycontent(rs.getString("REPLY_CONTENT"));
				csDetailBean.setReplytime(rs.getDate("REPLY_TIME"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(csDetailBean);
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
		
		return list;
	}
	
}
