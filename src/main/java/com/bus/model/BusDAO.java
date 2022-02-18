package com.bus.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BusDAO implements BusBeanDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TFA105G3TestDB"); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO BUS (NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE BUS SET NAME=?, PHONE=?, ADDRESS=?, TAX_ID=?, DATE=?, EMAIL=?, PASSWORD=?, INTRO=?, PHOTO=?, FB=?, IG=?, WEBSITE=?, PAYMENT_PROVIDE=?"
		+ " WHERE BUS_ID=?";
	private static final String DELETE = 
		"DELETE FROM BUS WHERE BUS_ID=?";
	private static final String GET_ONE_STMT = 
		"SELECT BUS_ID, NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE FROM BUS WHERE BUS_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT BUS_ID, NAME, PHONE, ADDRESS, TAX_ID, DATE, EMAIL, PASSWORD, INTRO, PHOTO, FB, IG, WEBSITE, PAYMENT_PROVIDE FROM BUS ORDER BY BUS_ID";
	
	@Override
	public void insert(BusVO busBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, busBean.getName());
			pstmt.setString(2, busBean.getPhone());
			pstmt.setString(3, busBean.getAddress());
			pstmt.setString(4, busBean.getTaxid());
			pstmt.setDate(5, busBean.getDate());
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
	public void update(BusVO busBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, busBean.getName());
			pstmt.setString(2, busBean.getPhone());
			pstmt.setString(3, busBean.getAddress());
			pstmt.setString(4, busBean.getTaxid());
			pstmt.setDate(5, busBean.getDate());
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
	public void delete(Integer busid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, busid);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
	public BusVO select(Integer busid) {
		
		BusVO busBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
				busBean.setDate(rs.getDate("DATE"));
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
	public List<BusVO> selectAll() {
		List<BusVO> list = new ArrayList<BusVO>();
		
		BusVO busBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				busBean = new BusVO();
				
				busBean.setBusid(rs.getInt("BUS_ID"));
				busBean.setName(rs.getString("NAME"));
				busBean.setPhone(rs.getString("PHONE"));
				busBean.setAddress(rs.getString("ADDRESS"));
				busBean.setTaxid(rs.getString("TAX_ID"));
				busBean.setDate(rs.getDate("DATE"));
				busBean.setEmail(rs.getString("EMAIL"));
				busBean.setPassword(rs.getString("PASSWORD"));
				busBean.setIntro(rs.getString("INTRO"));
				busBean.setPhoto(rs.getBytes("PHOTO"));
				busBean.setFb(rs.getString("FB"));
				busBean.setIg(rs.getString("IG"));
				busBean.setWebsite(rs.getString("WEBSITE"));
				busBean.setPaymentprovide(rs.getString("PAYMENT_PROVIDE"));
				// 讀取完一筆資料就存到list，若rs.next()還有再讀取下一筆Bean
				list.add(busBean);
			}
			
			// Handle any driver errors
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
