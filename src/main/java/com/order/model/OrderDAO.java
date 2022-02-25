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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orderDetail.model.OrderDetailJDBCDAO;
import com.orderDetail.model.OrderDetailVO;

public class OrderDAO implements OrderDAO_interface{
	
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
	
	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.ORDER(MEMBER_ID, BUS_ID, ORDER_TIME, ORDER_SUM, PAYMENT_ID, SHIPPING_ID, TRACKING, ORDER_STATUS, INVOICE_ID, RECEIVER, RECEIVER_ADDR, RECEIVER_PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE pet_g3db_tfa105.ORDER SET TRACKING = ?, ORDER_STATUS = ?, INVOICE_ID = ?, RECEIVER = ?, RECEIVER_ADDR = ?, RECEIVER_PHONE= ? WHERE ORDER_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM pet_g3db_tfa105.ORDER WHERE ORDER_ID = ?";
	private static final String CANCEL_STMT = "UPDATE pet_g3db_tfa105.ORDER SET ORDER_STATUS = 4 WHERE ORDER_ID = ?";
	private static final String FIND_BY_ORDER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE ORDER_ID = ?";
	private static final String FIND_BY_BUS_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE BUS_ID = ? ORDER BY ORDER_TIME DESC";
	private static final String FIND_BY_MEMBER_ID = "SELECT * FROM pet_g3db_tfa105.ORDER WHERE MEMBER_ID = ?  ORDER BY ORDER_TIME DESC";
	private static final String GET_ALL = "SELECT * FROM pet_g3db_tfa105.ORDER";
	
//	static {
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}

	public void insertWithOrderDetail(OrderVO orderVO, List<OrderDetailVO> list) {
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			// 1.設定於 pstmt.executeUpdate()之前
    		con.setAutoCommit(false);
    		
    		// 先新增訂單主檔
			String cols[] = {"ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setInt(1, orderVO.getMemberId());
			pstmt.setInt(2, orderVO.getBusId());
			pstmt.setTimestamp(3, orderVO.getOrderTime());
			pstmt.setInt(4, orderVO.getOrderSum());
			pstmt.setInt(5, orderVO.getPaymentId());
			pstmt.setInt(6, orderVO.getShippingId());
			
			if (orderVO.getTracking() == null) {
				pstmt.setNull(7, Types.INTEGER);
			} else {
				pstmt.setInt(7, orderVO.getTracking());
			}
			
			pstmt.setInt(8, orderVO.getOrderStatus());
			pstmt.setString(9, orderVO.getInvoiceId());
			pstmt.setString(10, orderVO.getReceiver());
			pstmt.setString(11, orderVO.getReceiverAddr());
			pstmt.setString(12, orderVO.getReceiverPhone());
			
			pstmt.executeUpdate();
			
			//獲取對應的自增主鍵值
			String next_orderId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderId +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			//再同時新增訂單明細
			OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrderDetailVO addOrderDetail : list) {
				addOrderDetail.setOrderId(new Integer(next_orderId));
				dao.insert(addOrderDetail, con);
			}
			
			// 2.設定於 pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_orderId + "時,共有" + list.size()
					+ "筆訂單明細同時被新增");
			
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
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

/*	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
*/
	@Override
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			if (orderVO.getTracking() == null) {
				pstmt.setNull(1, Types.INTEGER);
			} else {
				pstmt.setInt(1, orderVO.getTracking());
			}
			
			pstmt.setInt(2, orderVO.getOrderStatus());
			pstmt.setString(3, orderVO.getInvoiceId());
			pstmt.setString(4, orderVO.getReceiver());
			pstmt.setString(5, orderVO.getReceiverAddr());
			pstmt.setString(6, orderVO.getReceiverPhone());
			pstmt.setInt(7, orderVO.getOrderId());
			
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
	
	public void cancel(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CANCEL_STMT);
			
			pstmt.setInt(1, orderVO.getOrderId());
			
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
		OrderDAO dao = new OrderDAO();
//		// 新增訂單主檔跟明細測試
//		OrderVO orderVO = new OrderVO();
//		orderVO.setMemberId(1);
//		orderVO.setBusId(2);
//		orderVO.setOrderTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
//		orderVO.setOrderSum(1800);
//		orderVO.setPaymentId(4);
//		orderVO.setShippingId(2);
//		orderVO.setTracking(null);
//		orderVO.setOrderStatus(1);
//		orderVO.setInvoiceId("");
//		orderVO.setReceiver("");
//		orderVO.setReceiverAddr("");
//		orderVO.setReceiverPhone("");
//		
//		List<OrderDetailVO> testList = new ArrayList<OrderDetailVO>();
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setMerId(5);
//		orderDetailVO1.setQty(2);
//		orderDetailVO1.setUnitPrice(500);
//		orderDetailVO1.setRanking(null);
//		orderDetailVO1.setComment("");
//		
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setMerId(2);
//		orderDetailVO2.setQty(2);
//		orderDetailVO2.setUnitPrice(400);
//		orderDetailVO2.setRanking(null);
//		orderDetailVO2.setComment("");
//		
//		testList.add(orderDetailVO1);
//		testList.add(orderDetailVO2);
//		
//		dao.insertWithOrderDetail(orderVO, testList);
//		
		
		
//		// 新增測試 (非正式版)
//		OrderVO orderVO1 = new OrderVO();
//		orderVO1.setOrderId(5);
//		orderVO1.setMemberId(22);
//		orderVO1.setBusId(1);
//		orderVO1.setOrderTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
//		orderVO1.setOrderSum(1500);
//		orderVO1.setPaymentId(1);
//		orderVO1.setShippingId(3);
//		orderVO1.setTracking(null);
//		orderVO1.setOrderStatus(1);
//		orderVO1.setInvoiceId("");
//		orderVO1.setReceiver("");
//		orderVO1.setReceiverAddr("");
//		orderVO1.setReceiverPhone("");
//		dao.insert(orderVO1);
//		System.out.println("新增成功");
		
//		// 更新測試
//		OrderVO orderVO2 = new OrderVO();
//		orderVO2.setTracking(34001200);
//		orderVO2.setOrderStatus(2);
//		orderVO2.setInvoiceId("");
//		orderVO2.setReceiver("木木梟來啦");
//		orderVO2.setReceiverAddr("神奇寶貝球球球");
//		orderVO2.setReceiverPhone("95279527");
//		orderVO2.setOrderId(2);
//		dao.update(orderVO2);
//		System.out.println("更新成功");
		
//		// 取消測試
//		OrderVO orderVO3 = new OrderVO();
//		orderVO3.setOrderId(5);
//		dao.cancel(orderVO3);
//		System.out.println("取消成功");
		
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
