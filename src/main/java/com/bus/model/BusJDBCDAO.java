package com.bus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusJDBCDAO implements BusDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	//新增
	private static final String INSERT_STMT = 
		"INSERT INTO BUS (NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//修改
	private static final String UPDATE = 
		"UPDATE BUS SET NAME=?, PHONE=?, ADDRESS=?, TAX_ID=?, DATE=?, EMAIL=?, PASSWORD=?, INTRO=?, PHOTO=?, FB=?, IG=?, WEBSITE=?, PAYMENT_PROVIDE=?"
		+ " WHERE BUS_ID=?";
	//刪除
	private static final String DELETE = 
		"DELETE FROM BUS WHERE BUS_ID=?";
	//查詢
	private static final String GET_ONE_STMT = 
		"SELECT BUS_ID, NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE FROM BUS WHERE BUS_ID = ?";
	//查詢
	private static final String GET_TWO_STMT = 
			"SELECT BUS_ID, NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE FROM BUS WHERE EMAIL =? and PASSWORD = ?";
	//查詢全部
	private static final String GET_ALL_STMT = 
		"SELECT BUS_ID, NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE FROM BUS ORDER BY BUS_ID";
	
	//新增INSERT_STMT
	@Override
	public void insert(BusVO busBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, busBean.getName());
			pstmt.setString(2, busBean.getPhone());
			pstmt.setString(3, busBean.getAddress());
			pstmt.setString(4, busBean.getTaxid());
			pstmt.setTimestamp(5, busBean.getTimestamp());
			pstmt.setString(6, busBean.getEmail());
			pstmt.setString(7, busBean.getPassword());
			pstmt.setString(8, busBean.getIntro());
			pstmt.setBytes(9, busBean.getPhoto());
			pstmt.setString(10, busBean.getFb());
			pstmt.setString(11, busBean.getIg());
			pstmt.setString(12, busBean.getWebsite());
			pstmt.setString(13, busBean.getPaymentprovide());
			
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
	public void update(BusVO busBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, busBean.getName());
			pstmt.setString(2, busBean.getPhone());
			pstmt.setString(3, busBean.getAddress());
			pstmt.setString(4, busBean.getTaxid());
			pstmt.setTimestamp(5, busBean.getTimestamp());
			pstmt.setString(6, busBean.getEmail());
			pstmt.setString(7, busBean.getPassword());
			pstmt.setString(8, busBean.getIntro());
			pstmt.setBytes(9, busBean.getPhoto());
			pstmt.setString(10, busBean.getFb());
			pstmt.setString(11, busBean.getIg());
			pstmt.setString(12, busBean.getWebsite());
			pstmt.setString(13, busBean.getPaymentprovide());
			pstmt.setInt(14, busBean.getBusid());
			
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
	public void delete(Integer busid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, busid);
			
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

	//查詢單個欄位GET_ONE_STMT(此為busid)
	@Override
	public BusVO select(Integer busid) {
		
		BusVO busBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, busid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				busBean = new BusVO();
				
				busBean.setBusid(rs.getInt("BUS_ID"));
				busBean.setName(rs.getString("NAME"));
				busBean.setPhone(rs.getString("PHONE"));
				busBean.setAddress(rs.getString("ADDRESS"));
				busBean.setTaxid(rs.getString("TAX_ID"));
				busBean.setTimestamp(rs.getTimestamp("DATE"));
				busBean.setEmail(rs.getString("EMAIL"));
				busBean.setPassword(rs.getString("PASSWORD"));
				busBean.setIntro(rs.getString("INTRO"));
				busBean.setPhoto(rs.getBytes("PHOTO"));
				busBean.setFb(rs.getString("FB"));
				busBean.setIg(rs.getString("IG"));
				busBean.setWebsite(rs.getString("WEBSITE"));
				busBean.setPaymentprovide(rs.getString("PAYMENT_PROVIDE"));
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
		
		return busBean;
	}

	//查詢全部欄位GET_ALL_STMT
	@Override
	public List<BusVO> selectAll() {
		List<BusVO> list = new ArrayList<BusVO>();
		
		BusVO busBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				busBean = new BusVO();
				
				busBean.setBusid(rs.getInt("BUS_ID"));
				busBean.setName(rs.getString("NAME"));
				busBean.setPhone(rs.getString("PHONE"));
				busBean.setAddress(rs.getString("ADDRESS"));
				busBean.setTaxid(rs.getString("TAX_ID"));
				busBean.setTimestamp(rs.getTimestamp("DATE"));
				busBean.setEmail(rs.getString("EMAIL"));
				busBean.setPassword(rs.getString("PASSWORD"));
				busBean.setIntro(rs.getString("INTRO"));
				busBean.setPhoto(rs.getBytes("PHOTO"));
				busBean.setFb(rs.getString("FB"));
				busBean.setIg(rs.getString("IG"));
				busBean.setWebsite(rs.getString("WEBSITE"));
				busBean.setPaymentprovide(rs.getString("PAYMENT_PROVIDE"));
				// 霈�����蝑��停摮list嚗rs.next()���������蝑ean
				list.add(busBean);
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

	
	//查詢二個欄位GET_TWO_STMT(email、password)
	@Override
	public BusVO selectByEmailAndPassword(String email, String password) {
		BusVO busBean = null;
		
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
				busBean = new BusVO();
				
				busBean.setBusid(rs.getInt("BUS_ID"));
				busBean.setName(rs.getString("NAME"));
				busBean.setPhone(rs.getString("PHONE"));
				busBean.setAddress(rs.getString("ADDRESS"));
				busBean.setTaxid(rs.getString("TAX_ID"));
				busBean.setTimestamp(rs.getTimestamp("DATE"));
				busBean.setEmail(rs.getString("EMAIL"));
				busBean.setPassword(rs.getString("PASSWORD"));
				busBean.setIntro(rs.getString("INTRO"));
				busBean.setPhoto(rs.getBytes("PHOTO"));
				busBean.setFb(rs.getString("FB"));
				busBean.setIg(rs.getString("IG"));
				busBean.setWebsite(rs.getString("WEBSITE"));
				busBean.setPaymentprovide(rs.getString("PAYMENT_PROVIDE"));
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
		
		return busBean;
	}

	@Override
	public BusVO select(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
