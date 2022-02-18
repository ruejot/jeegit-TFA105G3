package com.payment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentJDBCDAO implements PaymentDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
		
	private static final String INSERT_STMT = "INSERT INTO payment (PAYMENT_METHOD) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT  PATMENT_ID, PAYMENT_METHOD FROM payment order by PATMENT_ID";
	private static final String GET_ONE_STMT = "SELECT PATMENT_ID, PAYMENT_METHOD FROM payment where PATMENT_ID = ?";
	private static final String DELETE = "DELETE FROM payment where PATMENT_ID = ?";
	private static final String UPDATE = "UPDATE payment set PAYMENT_METHOD=? where PATMENT_ID = ?";



	@Override
	public void insert(PaymentVO paymentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, paymentVO.getPaymentmethod());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(PaymentVO paymentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, paymentVO.getPaymentmethod());
			pstmt.setInt(2, paymentVO.getPaymentid());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer paymentid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, paymentid);

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
	public PaymentVO findByPrimaryKey(Integer paymentid) {
		
		PaymentVO paymentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, paymentid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVo �]�٬� Domain objects
				paymentVO = new PaymentVO();
				paymentVO.setPaymentid(rs.getInt("paymentid"));
				paymentVO.setPaymentmethod(rs.getString("paymentmethod"));
				
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
		return paymentVO;
	}


	@Override
	public List<PaymentVO> getAll() {
		
		List<PaymentVO> list = new ArrayList<PaymentVO>();
		PaymentVO paymentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				paymentVO = new PaymentVO();
				paymentVO.setPaymentid(rs.getInt("paymentid"));
				paymentVO.setPaymentmethod(rs.getString("paymentmethod"));
				list.add(paymentVO); // Store the row in the list
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
	
	public static void main(String[] args) {

		PaymentJDBCDAO dao = new PaymentJDBCDAO();
		
		//新增
		PaymentVO paymentVO1 = new PaymentVO();
		paymentVO1.setPaymentmethod("LINEPAY");
		dao.insert(paymentVO1);
		
		//修改
		PaymentVO paymentVO2 = new PaymentVO();
		paymentVO2.setPaymentid(5);
		paymentVO2.setPaymentmethod("LINEPAYS");
		dao.update(paymentVO2);

		//刪除
		dao.delete(5);

		//查詢
		PaymentVO paymentVO3 = dao.findByPrimaryKey(1);
		System.out.print(paymentVO3.getPaymentid() + ",");
		System.out.print(paymentVO3.getPaymentmethod());
		System.out.println("---------------------");

		List<PaymentVO> list = dao.getAll();
		for (PaymentVO aPay : list) {
			System.out.print(aPay.getPaymentid() + ",");
			System.out.print(aPay.getPaymentmethod());
			System.out.println();
		}
	}

}
