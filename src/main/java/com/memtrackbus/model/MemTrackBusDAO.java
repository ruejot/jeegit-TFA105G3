package com.memtrackbus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemTrackBusDAO implements MemTrackBusDAO_interface{

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
	
	//BUSFOLLOW_ID, MEMBER_ID, BUS_ID
	private static final String INSERT_STMT = "INSERT INTO MEM_TRACK_BUS (MEMBER_ID, BUS_ID)"
			+ " VALUES (?, ?)";
	private static final String UPDATE = "UPDATE MEM_TRACK_BUS  SET MEMBER_ID=?, BUS_ID=?"
			+ " WHERE BUSFOLLOW_ID=?";
	private static final String DELETE = "DELETE FROM MEM_TRACK_BUS WHERE BUSFOLLOW_ID=?";
	private static final String GET_ONE_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS WHERE BUSFOLLOW_ID = ?";
	private static final String GET_ALL_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS ORDER BY BUSFOLLOW_ID";
	private static final String GET_ALL_BY_MEMBER_ID_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS WHERE MEMBER_ID = ?";
	
	@Override
	public void insert(MemTrackBusVO memFollowBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getBusId());
			
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
	public void update(MemTrackBusVO memFollowBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getBusId());
			pstmt.setInt(3, memFollowBean.getBusfollowId());
			
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
	public void delete(Integer busfollowId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, busfollowId);

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
	public MemTrackBusVO findByPrimaryKey(Integer busfollowId) {
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, busfollowId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				//BUSFOLLOW_ID, MEMBER_ID, BUS_ID, FRIENDSHIP
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
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
		
		return memFollowBean;
	}

	@Override
	public List<MemTrackBusVO> getAll() {
		List<MemTrackBusVO> list = new ArrayList<MemTrackBusVO>();
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(memFollowBean);
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

	@Override
	public List<MemTrackBusVO> getAllByMemberId(Integer memberId) {
		List<MemTrackBusVO> list = new ArrayList<MemTrackBusVO>();
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER_ID_STMT);
			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(memFollowBean);
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
