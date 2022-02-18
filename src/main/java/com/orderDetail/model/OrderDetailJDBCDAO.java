package com.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface{
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.ORDER_DETAIL(ORDER_ID, MER_ID, QTY, UNIT_PRICE, RANKING, COMMENT) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE pet_g3db_tfa105.ORDER_DETAIL SET QTY = ?, UNIT_PRICE = ?, RANKING = ?, COMMENT = ? WHERE ORDER_ID = ? and MER_ID = ?";
	private static final String FIND_BY_ORDER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER_DETAIL WHERE ORDER_ID = ?";
	private static final String FIND_BY_MER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER_DETAIL WHERE MER_ID = ?";
	private static final String GET_ALL = "SELECT * FROM pet_g3db_tfa105.ORDER_DETAIL";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orderDetailVO.getOrderId());
			pstmt.setInt(2, orderDetailVO.getMerId());
			pstmt.setInt(3, orderDetailVO.getQty());
			pstmt.setInt(4, orderDetailVO.getUnitPrice());
			if (orderDetailVO.getRanking() == null) {
				pstmt.setNull(5, Types.INTEGER);
			} else {
				pstmt.setInt(5, orderDetailVO.getRanking());
			}
			pstmt.setString(6, orderDetailVO.getComment());
			
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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, orderDetailVO.getQty());
			pstmt.setInt(2, orderDetailVO.getUnitPrice());
			if (orderDetailVO.getRanking() == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, orderDetailVO.getRanking());
			}
			pstmt.setString(4, orderDetailVO.getComment());
			pstmt.setInt(5, orderDetailVO.getOrderId());
			pstmt.setInt(6, orderDetailVO.getMerId());
			
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
	public List<OrderDetailVO> findByOrderId(Integer orderId) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ORDER_ID);
			
			pstmt.setInt(1, orderId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setMerId(rs.getInt("MER_ID"));
				orderDetailVO.setQty(rs.getInt("QTY"));
				orderDetailVO.setUnitPrice(rs.getInt("UNIT_PRICE"));
				orderDetailVO.setRanking(rs.getInt("RANKING"));
				orderDetailVO.setComment(rs.getString("COMMENT"));
				list.add(orderDetailVO);
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

	@Override
	public List<OrderDetailVO> findByMerId(Integer merId) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MER_ID);
			
			pstmt.setInt(1, merId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setMerId(rs.getInt("MER_ID"));
				orderDetailVO.setQty(rs.getInt("QTY"));
				orderDetailVO.setUnitPrice(rs.getInt("UNIT_PRICE"));
				orderDetailVO.setRanking(rs.getInt("RANKING"));
				orderDetailVO.setComment(rs.getString("COMMENT"));
				list.add(orderDetailVO);
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

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setMerId(rs.getInt("MER_ID"));
				orderDetailVO.setQty(rs.getInt("QTY"));
				orderDetailVO.setUnitPrice(rs.getInt("UNIT_PRICE"));
				orderDetailVO.setRanking(rs.getInt("RANKING"));
				orderDetailVO.setComment(rs.getString("COMMENT"));
				list.add(orderDetailVO);
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
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
//		// 新增測試
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setOrderId(2);
//		orderDetailVO1.setMerId(3);
//		orderDetailVO1.setQty(2);
//		orderDetailVO1.setUnitPrice(500);
//		orderDetailVO1.setRanking(null);
//		orderDetailVO1.setComment("");
//		dao.insert(orderDetailVO1);
//		System.out.println("新增成功");
		
//		// 更新測試
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setQty(2);
//		orderDetailVO2.setUnitPrice(500);
//		orderDetailVO2.setRanking(5);
//		orderDetailVO2.setComment("商品品質優，店家服務讚讚");
//		orderDetailVO2.setOrderId(2);
//		orderDetailVO2.setMerId(3);
//		dao.update(orderDetailVO2);
//		System.out.println("更新成功");
		
//		// 多筆查詢測試 (OrderId)
//		List<OrderDetailVO> list = dao.findByOrderId(1);
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO);
//		}
		
//		// 多筆查詢測試 (MerId)
//		List<OrderDetailVO> list = dao.findByMerId(3);
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO);
//		}

//		// getAll測試
		List<OrderDetailVO> list = dao.getAll();
		for (OrderDetailVO orderDetailVO : list) {
			System.out.println(orderDetailVO);
		}

	}

}
