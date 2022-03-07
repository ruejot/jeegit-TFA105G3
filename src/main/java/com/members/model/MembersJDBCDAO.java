package com.members.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembersJDBCDAO implements MembersDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	//新增
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBERS (NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//修改
	private static final String UPDATE = 
		"UPDATE MEMBERS SET NAME=?, MOBILE=?, PHONE=?, ADDRESS=?, DATE=?, EMAIL=?, PASSWORD=?, NICKNAME=?, INTRO=? WHERE MEMBER_ID=?";
//	"UPDATE MEMBERS SET NAME=?, MOBILE=?, PHONE=?, ADDRESS=?, DATE=?, EMAIL=?, PASSWORD=?, NICKNAME=?, INTRO=?, PHOTO=?"
//	+ " WHERE MEMBER_ID=?";
	//刪除
	private static final String DELETE = 
		"DELETE FROM MEMBERS WHERE MEMBER_ID=?";
	//查詢by Bus_ID
	private static final String GET_ONE_STMT = 
		"SELECT MEMBER_ID, NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO FROM MEMBERS WHERE MEMBER_ID = ?";
	//查詢by EMAIL and PASSWORD
	private static final String GET_TWO_STMT = 
			"SELECT MEMBER_ID, NAME, MOBILE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO FROM MEMBERS WHERE EMAIL =? and PASSWORD = ?";
	//查詢全部
	private static final String GET_ALL_STMT = 
		"SELECT MEMBER_ID, NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO FROM MEMBERS ORDER BY MEMBER_ID";
	
	//新增INSERT_STMT
	@Override
	public void insert(MembersVO memberBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberBean.getName());
			pstmt.setString(2, memberBean.getMobile());
			pstmt.setString(3, memberBean.getPhone());
			pstmt.setString(4, memberBean.getAddress());
			pstmt.setTimestamp(5, memberBean.getDate());
			pstmt.setString(6, memberBean.getEmail());
			pstmt.setString(7, memberBean.getPassword());
			pstmt.setString(8, memberBean.getNickname());
			pstmt.setString(9, memberBean.getIntro());
			pstmt.setBytes(10, memberBean.getPhoto());
			
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
	
	//修改UPDATE
	@Override
	public void update(MembersVO memberBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberBean.getName());
			pstmt.setString(2, memberBean.getMobile());
			pstmt.setString(3, memberBean.getPhone());
			pstmt.setString(4, memberBean.getAddress());
			pstmt.setTimestamp(5, memberBean.getDate());
			pstmt.setString(6, memberBean.getEmail());
			pstmt.setString(7, memberBean.getPassword());
			pstmt.setString(8, memberBean.getNickname());
			pstmt.setString(9, memberBean.getIntro());
//			pstmt.setBytes(10, memberBean.getPhoto());
			pstmt.setInt(10, memberBean.getMemberid());
			
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
	
	//刪除DELETE
	@Override
	public void delete(Integer memberid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, memberid);
			
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
	
	//查詢單個欄位GET_ONE_STMT(此為memberid)
	@Override
	public MembersVO select(Integer memberid) {
		
		MembersVO memberBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, memberid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberBean = new MembersVO();
				
				memberBean.setMemberid(rs.getInt("MEMBER_ID"));
				memberBean.setName(rs.getString("NAME"));
				memberBean.setMobile(rs.getString("MOBILE"));
				memberBean.setPhone(rs.getString("PHONE"));
				memberBean.setAddress(rs.getString("ADDRESS"));
				memberBean.setDate(rs.getTimestamp("DATE"));
				memberBean.setEmail(rs.getString("EMAIL"));
				memberBean.setPassword(rs.getString("PASSWORD"));
				memberBean.setNickname(rs.getString("NICKNAME"));
				memberBean.setIntro(rs.getString("INTRO"));
				memberBean.setPhoto(rs.getBytes("PHOTO"));
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
		return memberBean;
	}
	
	//查詢二個欄位GET_ONE_STMT(email和密碼)
	@Override
	public MembersVO selectByEmailAndPassword(String email, String password) {
		
		MembersVO memberBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_TWO_STMT);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberBean = new MembersVO();
				
				memberBean.setMemberid(rs.getInt("MEMBER_ID"));
				memberBean.setName(rs.getString("NAME"));
				memberBean.setMobile(rs.getString("MOBILE"));
				memberBean.setPhone(rs.getString("PHONE"));
				memberBean.setAddress(rs.getString("ADDRESS"));
				memberBean.setDate(rs.getTimestamp("DATE"));
				memberBean.setEmail(rs.getString("EMAIL"));
				memberBean.setPassword(rs.getString("PASSWORD"));
				memberBean.setNickname(rs.getString("NICKNAME"));
				memberBean.setIntro(rs.getString("INTRO"));
				memberBean.setPhoto(rs.getBytes("PHOTO"));
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
		return memberBean;
	}
	
	//查詢全部欄位GET_ALL_STMT
	@Override
	public List<MembersVO> selectAll() {
		List<MembersVO> list = new ArrayList<MembersVO>();
		
		MembersVO memberBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberBean = new MembersVO();
				
				memberBean.setMemberid(rs.getInt("MEMBER_ID"));
				memberBean.setName(rs.getString("NAME"));
				memberBean.setMobile(rs.getString("MOBILE"));
				memberBean.setPhone(rs.getString("PHONE"));
				memberBean.setAddress(rs.getString("ADDRESS"));
				memberBean.setDate(rs.getTimestamp("DATE"));
				memberBean.setEmail(rs.getString("EMAIL"));
				memberBean.setPassword(rs.getString("PASSWORD"));
				memberBean.setNickname(rs.getString("NICKNAME"));
				memberBean.setIntro(rs.getString("INTRO"));
				memberBean.setPhoto(rs.getBytes("PHOTO"));
				
				// 讀取完一筆資料就存到list，若rs.next()還有再讀取下一個
				list.add(memberBean);
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
	public MembersVO select(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MembersVO selectMemberID(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
