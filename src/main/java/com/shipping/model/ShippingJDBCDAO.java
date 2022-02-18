package com.shipping.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShippingJDBCDAO implements ShippingDAO_interface{
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	private static final String INSERT_STMT = "INSERT INTO SHIPPING(SHIPPING_ID, SHIPPING_METHOD) VALUES (?, ?)";
	private static final String UPDATE_STMT = "UPDATE SHIPPING SET SHIPPING_METHOD = ? WHERE SHIPPING_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM SHIPPING WHERE SHIPPING_ID = ?";
	private static final String FIND_BY_SHIPPING_ID = "SELECT * FROM SHIPPING WHERE SHIPPING_ID = ?";
	private static final String GET_ALL = "SELECT * FROM SHIPPING";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ShippingVO shippingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, shippingVO.getShippingId());
			pstmt.setString(2, shippingVO.getShippingMethod());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(ShippingVO shippingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, shippingVO.getShippingMethod());
			pstmt.setInt(2, shippingVO.getShippingId());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer shippingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, shippingId);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}

	@Override
	public ShippingVO findByShippingId(Integer shippingId) {
		ShippingVO shippingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SHIPPING_ID);
			
			pstmt.setInt(1, shippingId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shippingVO = new ShippingVO();
				shippingVO.setShippingId(rs.getInt("SHIPPING_ID"));
				shippingVO.setShippingMethod(rs.getString("SHIPPING_METHOD"));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return shippingVO;
	}

	@Override
	public List<ShippingVO> getAll() {
		List<ShippingVO> list = new ArrayList<ShippingVO>();
		ShippingVO shippingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shippingVO = new ShippingVO();
				shippingVO.setShippingId(rs.getInt("SHIPPING_ID"));
				shippingVO.setShippingMethod(rs.getString("SHIPPING_METHOD"));
				list.add(shippingVO);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
//		// 新增測試
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入要新增的出貨方式編號");
//		Integer shippingId = sc.nextInt();
//		System.out.println("請輸入要新增的出貨方法");
//		String shippingMethod = sc.next();
//		ShippingVO shippingVO = new ShippingVO(shippingId, shippingMethod);
//		ShippingJDBCDAO dao = new ShippingJDBCDAO();
//		dao.insert(shippingVO);
//		System.out.println("新增成功");
		
//		// 更新測試
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入要更新的出貨方式編號");
//		Integer shippingId = sc.nextInt();
//		System.out.println("請輸入要更新的出貨方法");
//		String shippingMethod = sc.next();
//		ShippingVO shippingVO = new ShippingVO(shippingId, shippingMethod);
//		ShippingJDBCDAO dao = new ShippingJDBCDAO();
//		dao.update(shippingVO);
//		System.out.println("更新成功");
		
//		// 刪除測試
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入要刪除的出貨方式編號");
//		Integer shippingId = sc.nextInt();
//		ShippingJDBCDAO dao = new ShippingJDBCDAO();
//		dao.delete(shippingId);
//		System.out.println("刪除成功");
		
//		// 單筆查詢測試
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入要查詢的出貨方式編號");
//		int shippingId = sc.nextInt();
//		ShippingJDBCDAO dao = new ShippingJDBCDAO();
//		ShippingVO shippingVO = dao.findByShippingId(shippingId);
//		System.out.println(shippingVO);
		
		// getAll測試
		ShippingJDBCDAO dao = new ShippingJDBCDAO();
		List<ShippingVO> list = dao.getAll();
		for (ShippingVO shippingVO : list) {
			System.out.println(shippingVO);
		}

	}

}
