package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO_interface{
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.ORDER(ORDER_ID, MEMBER_ID, BUS_ID, ORDER_TIME, ORDER_SUM, PAYMENT_ID, SHIPPING_ID, TRACKING, ORDER_STATUS, INVOICE_ID, RECEIVER, RECEIVER_ADDR, RECEIVER_PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE pet_g3db_tfa105.ORDER SET MEMBER_ID = ?, BUS_ID = ?, ORDER_SUM = ?, PAYMENT_ID = ?, SHIPPING_ID = ?, TRACKING = ?, ORDER_STATUS = ?, INVOICE_ID = ?, RECEIVER = ?, RECEIVER_ADDR = ?, RECEIVER_PHONE= ? WHERE ORDER_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM pet_g3db_tfa105.ORDER WHERE ORDER_ID = ?";
	private static final String FIND_BY_ORDER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE ORDER_ID = ?";
	private static final String FIND_BY_BUS_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE BUS_ID = ? ORDER BY ORDER_TIME DESC";
	private static final String FIND_BY_MEMBER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE MEMBER_ID = ?  ORDER BY ORDER_TIME DESC";
	private static final String GET_ALL = "SELECT * FROM pet_g3db_tfa105.ORDER";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orderVO.getOrderId());
			pstmt.setInt(2, orderVO.getMemberId());
			pstmt.setInt(3, orderVO.getBusId());
			pstmt.setTimestamp(4, orderVO.getOrderTime());
			pstmt.setInt(5, orderVO.getOrderSum());
			pstmt.setInt(6, orderVO.getPaymentId());
			pstmt.setInt(7, orderVO.getShippingId());
			
			if (orderVO.getTracking() == null) {
				pstmt.setNull(8, Types.INTEGER);
			} else {
				pstmt.setInt(8, orderVO.getTracking());
			}
			
			pstmt.setInt(9, orderVO.getOrderStatus());
			pstmt.setString(10, orderVO.getInvoiceId());
			pstmt.setString(11, orderVO.getReceiver());
			pstmt.setString(12, orderVO.getReceiverAddr());
			pstmt.setString(13, orderVO.getReceiverPhone());
			
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
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, orderVO.getMemberId());
			pstmt.setInt(2, orderVO.getBusId());
			pstmt.setInt(3, orderVO.getOrderSum());
			pstmt.setInt(4, orderVO.getPaymentId());
			pstmt.setInt(5, orderVO.getShippingId());
		
			if (orderVO.getTracking() == null) {
				pstmt.setNull(6, Types.INTEGER);
			} else {
				pstmt.setInt(6, orderVO.getTracking());
			}
			
			pstmt.setInt(7, orderVO.getOrderStatus());
			pstmt.setString(8, orderVO.getInvoiceId());
			pstmt.setString(9, orderVO.getReceiver());
			pstmt.setString(10, orderVO.getReceiverAddr());
			pstmt.setString(11, orderVO.getReceiverPhone());
			pstmt.setInt(12, orderVO.getOrderId());
			
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
	public void delete(Integer orderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, orderId);
			
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
	public OrderVO findByOrderId(Integer orderId) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ORDER_ID);
			
			pstmt.setInt(1, orderId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setMemberId(rs.getInt("MEMBER_ID"));
				orderVO.setBusId(rs.getInt("BUS_ID"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderSum(rs.getInt("ORDER_SUM"));
				orderVO.setPaymentId(rs.getInt("PAYMENT_ID"));
				orderVO.setShippingId(rs.getInt("SHIPPING_ID"));
				orderVO.setTracking(rs.getInt("TRACKING"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setInvoiceId(rs.getString("INVOICE_ID"));
				orderVO.setReceiver(rs.getString("RECEIVER"));
				orderVO.setReceiverAddr(rs.getString("RECEIVER_ADDR"));
				orderVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
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
		return orderVO;
	}

	@Override
	public List<OrderVO> findByBusId(Integer busId) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_BUS_ID);
			
			pstmt.setInt(1, busId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setMemberId(rs.getInt("MEMBER_ID"));
				orderVO.setBusId(rs.getInt("BUS_ID"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderSum(rs.getInt("ORDER_SUM"));
				orderVO.setPaymentId(rs.getInt("PAYMENT_ID"));
				orderVO.setShippingId(rs.getInt("SHIPPING_ID"));
				orderVO.setTracking(rs.getInt("TRACKING"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setInvoiceId(rs.getString("INVOICE_ID"));
				orderVO.setReceiver(rs.getString("RECEIVER"));
				orderVO.setReceiverAddr(rs.getString("RECEIVER_ADDR"));
				orderVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				list.add(orderVO);
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
	public List<OrderVO> findByMemberId(Integer memberId) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMBER_ID);
			
			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setMemberId(rs.getInt("MEMBER_ID"));
				orderVO.setBusId(rs.getInt("BUS_ID"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderSum(rs.getInt("ORDER_SUM"));
				orderVO.setPaymentId(rs.getInt("PAYMENT_ID"));
				orderVO.setShippingId(rs.getInt("SHIPPING_ID"));
				orderVO.setTracking(rs.getInt("TRACKING"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setInvoiceId(rs.getString("INVOICE_ID"));
				orderVO.setReceiver(rs.getString("RECEIVER"));
				orderVO.setReceiverAddr(rs.getString("RECEIVER_ADDR"));
				orderVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				list.add(orderVO);
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
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setMemberId(rs.getInt("MEMBER_ID"));
				orderVO.setBusId(rs.getInt("BUS_ID"));
				orderVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				orderVO.setOrderSum(rs.getInt("ORDER_SUM"));
				orderVO.setPaymentId(rs.getInt("PAYMENT_ID"));
				orderVO.setShippingId(rs.getInt("SHIPPING_ID"));
				orderVO.setTracking(rs.getInt("TRACKING"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setInvoiceId(rs.getString("INVOICE_ID"));
				orderVO.setReceiver(rs.getString("RECEIVER"));
				orderVO.setReceiverAddr(rs.getString("RECEIVER_ADDR"));
				orderVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				list.add(orderVO);
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
		OrderJDBCDAO dao = new OrderJDBCDAO();
//		// 新增測試
		OrderVO orderVO1 = new OrderVO();
		orderVO1.setOrderId(5);
		orderVO1.setMemberId(22);
		orderVO1.setBusId(1);
		orderVO1.setOrderTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		orderVO1.setOrderSum(1500);
		orderVO1.setPaymentId(1);
		orderVO1.setShippingId(3);
		orderVO1.setTracking(null);
		orderVO1.setOrderStatus(1);
		orderVO1.setInvoiceId("");
		orderVO1.setReceiver("");
		orderVO1.setReceiverAddr("");
		orderVO1.setReceiverPhone("");
		dao.insert(orderVO1);
		System.out.println("新增成功");
		
//		// 更新測試
//		OrderVO orderVO2 = new OrderVO();
//		orderVO2.setMemberId(22);
//		orderVO2.setBusId(3);
//		orderVO2.setOrderSum(500);
//		orderVO2.setPaymentId(1);
//		orderVO2.setShippingId(1);
//		orderVO2.setTracking(null);
//		orderVO2.setOrderStatus(1);
//		orderVO2.setInvoiceId("");
//		orderVO2.setReceiver("木木梟2");
//		orderVO2.setReceiverAddr("神奇寶貝球2");
//		orderVO2.setReceiverPhone("95279527");
//		orderVO2.setOrderId(1);
//		dao.update(orderVO2);
//		System.out.println("更新成功");
		
//		// 刪除測試
//		dao.delete(2);
//		System.out.println("刪除成功");
//		
//		// 單筆查詢測試 (OrderId)
//		OrderVO orderVO3 = dao.findByOrderId(1);
//		System.out.println(orderVO3);
//		
//		// 多筆查詢測試 (BusId)
//		List<OrderVO> list = dao.findByBusId(3);
//		for (OrderVO orderVO : list) {
//			System.out.println(orderVO);
//		}
		
//		// 多筆查詢測試 (MemeberId)
//		List<OrderVO> list = dao.findByMemberId(22);
//		for (OrderVO orderVO : list) {
//			System.out.println(orderVO);
//		}
//		
//		// getAll測試
//		List<OrderVO> list = dao.getAll();
//		for (OrderVO orderVO : list) {
//			System.out.println(orderVO);
//		}

	}

}
