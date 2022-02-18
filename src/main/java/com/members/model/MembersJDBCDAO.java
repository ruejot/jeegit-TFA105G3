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
	
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBERS (NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		"INSERT INTO MEMBERS (NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO)"
//		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE MEMBERS SET NAME=?, MOBILE=?, PHONE=?, ADDRESS=?, DATE=?, EMAIL=?, PASSWORD=?, NICKNAME=?, INTRO=?, PHOTO=?"
		+ " WHERE MEMBER_ID=?";
	private static final String DELETE = 
		"DELETE FROM MEMBERS WHERE MEMBER_ID=?";
	private static final String GET_ONE_STMT = 
		"SELECT MEMBER_ID, NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO FROM MEMBERS WHERE MEMBER_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT MEMBER_ID, NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD, NICKNAME, INTRO, PHOTO FROM MEMBERS ORDER BY MEMBER_ID";
	
	@Override
	public void insert(MembersVO membersBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, membersBean.getName());
			pstmt.setString(2, membersBean.getMobile());
			pstmt.setString(3, membersBean.getPhone());
			pstmt.setString(4, membersBean.getAddress());
			pstmt.setDate(5, membersBean.getDate());
			pstmt.setString(6, membersBean.getEmail());
			pstmt.setString(7, membersBean.getPassword());
			pstmt.setString(8, membersBean.getNickname());
			pstmt.setString(9, membersBean.getIntro());
			pstmt.setBytes(10, membersBean.getPhoto());
			
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
	public void update(MembersVO membersBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, membersBean.getName());
			pstmt.setString(2, membersBean.getMobile());
			pstmt.setString(3, membersBean.getPhone());
			pstmt.setString(4, membersBean.getAddress());
			pstmt.setDate(5, membersBean.getDate());
			pstmt.setString(6, membersBean.getEmail());
			pstmt.setString(7, membersBean.getPassword());
			pstmt.setString(8, membersBean.getNickname());
			pstmt.setString(9, membersBean.getIntro());
			pstmt.setBytes(10, membersBean.getPhoto());
			pstmt.setInt(11, membersBean.getMemberid());
			
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
	
	@Override
	public MembersVO select(Integer memberid) {
		
		MembersVO membersBean = null;
		
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
				membersBean = new MembersVO();
				
				membersBean.setMemberid(rs.getInt("MEMBER_ID"));
				membersBean.setName(rs.getString("NAME"));
				membersBean.setMobile(rs.getString("MOBILE"));
				membersBean.setPhone(rs.getString("PHONE"));
				membersBean.setAddress(rs.getString("ADDRESS"));
				membersBean.setDate(rs.getDate("DATE"));
				membersBean.setEmail(rs.getString("EMAIL"));
				membersBean.setPassword(rs.getString("PASSWORD"));
				membersBean.setNickname(rs.getString("NICKNAME"));
				membersBean.setIntro(rs.getString("INTRO"));
				membersBean.setPhoto(rs.getBytes("PHOTO"));
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
		return membersBean;
	}
	
	@Override
	public List<MembersVO> selectAll() {
		List<MembersVO> list = new ArrayList<MembersVO>();
		
		MembersVO membersBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				membersBean = new MembersVO();
				
				membersBean.setMemberid(rs.getInt("MEMBER_ID"));
				membersBean.setName(rs.getString("NAME"));
				membersBean.setMobile(rs.getString("MOBILE"));
				membersBean.setPhone(rs.getString("PHONE"));
				membersBean.setAddress(rs.getString("ADDRESS"));
				membersBean.setDate(rs.getDate("DATE"));
				membersBean.setEmail(rs.getString("EMAIL"));
				membersBean.setPassword(rs.getString("PASSWORD"));
				membersBean.setNickname(rs.getString("NICKNAME"));
				membersBean.setIntro(rs.getString("INTRO"));
				membersBean.setPhoto(rs.getBytes("PHOTO"));
				// 讀取完一筆資料就存到list，若rs.next()還有再讀取下一個
				list.add(membersBean);
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
